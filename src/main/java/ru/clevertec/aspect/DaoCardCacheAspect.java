package ru.clevertec.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.clevertec.annotation.CrudAnnotation;
import ru.clevertec.annotation.MethodType;
import ru.clevertec.cache.Cache;
import ru.clevertec.entity.DiscountCard;
import ru.clevertec.entity.Product;
import ru.clevertec.utils.cacheFactory.CacheFactory;

@Aspect
public class DaoCardCacheAspect {
    static private final Logger logger = LoggerFactory.getLogger(DaoCardCacheAspect.class);
    private final Cache<Integer, DiscountCard> cache;

    public DaoCardCacheAspect() {
        this.cache = new CacheFactory<Integer,DiscountCard>().cacheInitialize();
        logger.info("Initialize CardCache with type: "+cache.getType());
    }

    @Pointcut("execution(* ru.clevertec.dao.DiscountCardDao.*(..))")
    public void cardDaoMethod(){}

    @Pointcut("@annotation(ru.clevertec.annotation.CrudAnnotation)")
    public void cardDaoPUT(){}

    /**
     * In this method, synchronization occurs between the methods of the dao class and the cache
     * @param joinPoint the executable method that was intercepted by the aspect
     * @return returns the desired object
     * @throws Throwable
     */
    @Around("cardDaoMethod() && cardDaoPUT()")
    public Object cartDaoPUT(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodType methodType = ((MethodSignature)joinPoint.getSignature()).getMethod()
                .getAnnotation(CrudAnnotation.class).type();
        /**
         * determining the methods required for the call based on the annotations of the source method from dao
         */
        boolean b = switch (methodType){
            case PUT -> put(joinPoint);
            case GET -> false;
            case DELETE -> delete(joinPoint);
            case POST -> post(joinPoint);
        };

        if (!methodType.equals(MethodType.GET) && b){
            return joinPoint.proceed();
        }else if (methodType.equals(MethodType.GET)){
            return get(joinPoint);
        } else {
            return null;
        }
    }

    private boolean put(ProceedingJoinPoint joinPoint){
        DiscountCard discountCard = (DiscountCard) joinPoint.getArgs()[0];
        cache.remove(discountCard.getId());
        cache.add(discountCard.getId(),discountCard);
        logger.info("DiscountCard update and save in BD and CACHE - id: "+discountCard.getId());
        return true;
    }

    private boolean delete(ProceedingJoinPoint joinPoint){
        Class<?>[] classes = ((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();
        if (classes[0].equals(int.class)){
            cache.remove((Integer) joinPoint.getArgs()[0]);
            logger.info("DiscountCard delete if: "+joinPoint.getArgs()[0]);
            return true;
        } else if (classes[0].equals(Product.class)) {
            DiscountCard discountCard = (DiscountCard) joinPoint.getArgs()[0];
            cache.remove(discountCard.getId());
            logger.info("DiscountCard delete id: "+discountCard.getId());
            return true;
        }else return false;

    }

    private boolean post(ProceedingJoinPoint joinPoint){
        DiscountCard discountCard = (DiscountCard) joinPoint.getArgs()[0];
        cache.remove(discountCard.getId());
        cache.add(discountCard.getId(),discountCard);
        logger.info("DiscountCard save id: "+discountCard.getId());
        return true;
    }

    private DiscountCard get(ProceedingJoinPoint joinPoint) throws Throwable {
        Class<?>[] classes = ((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();
        if (classes[0].equals(int.class)){
            if (cache.containsKey((Integer) joinPoint.getArgs()[0])){
                logger.info("Get DiscountCard id: "+joinPoint.getArgs()[0]+" from CACHE");
                DiscountCard discountCard = cache.get((Integer) joinPoint.getArgs()[0]);
                return discountCard;
            }else {
                DiscountCard discountCard = (DiscountCard) joinPoint.proceed();
                cache.add(discountCard.getId(),discountCard);
                logger.info("Get DiscountCard id: "+discountCard.getId()+" from BD and add to CACHE");
                return discountCard;
            }
        } else if (classes[0].equals(Product.class)) {
            DiscountCard discountCard = (DiscountCard) joinPoint.getArgs()[0];
            if(cache.containsKey(discountCard.getId())){
                logger.info("Get DiscountCard id: "+discountCard.getId()+" from CACHE");
                DiscountCard discountCard1 = cache.get(discountCard.getId());
                return discountCard1;
            }else {
                DiscountCard discountCard1 = (DiscountCard) joinPoint.proceed();
                cache.add(discountCard1.getId(),discountCard1);
                logger.info("Get Cart id: "+discountCard1.getId()+" from BD and add to CACHE");
                return discountCard1;
            }
        }else return null;
    }
}
