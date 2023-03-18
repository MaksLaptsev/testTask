package ru.clevertec.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import ru.clevertec.annotation.CrudAnnotation;
import ru.clevertec.annotation.MethodType;
import ru.clevertec.cache.Cache;
import ru.clevertec.entity.Cart;
import ru.clevertec.entity.Product;
import ru.clevertec.utils.cacheFactory.CacheFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Aspect
public class DaoCartCacheAspect {
    private static final Logger logger = LoggerFactory.getLogger(DaoCartCacheAspect.class);
    private final Cache<Integer, Cart> cache;

    public DaoCartCacheAspect() {
        this.cache = new CacheFactory<Integer,Cart>().cacheInitialize();
        logger.info("Initialize CartCache with type: "+cache.getType());
    }

    @Pointcut("execution(* ru.clevertec.dao.CartDao.*(..))")
    public void cartDaoMethod(){}

    @Pointcut("@annotation(ru.clevertec.annotation.CrudAnnotation)")
    public void cartDaoPUT(){}

    /**
     * In this method, synchronization occurs between the methods of the dao class and the cache
     * @param joinPoint the executable method that was intercepted by the aspect
     * @return returns the desired object
     * @throws Throwable
     */
    @Around("cartDaoMethod() && cartDaoPUT()")
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
        Cart cart = (Cart) joinPoint.getArgs()[0];
        cache.remove(cart.getId());
        cache.add(cart.getId(),cart);
        logger.info("Cart update and save in BD and CACHE - id: "+cart.getId());
        return true;
    }

    private boolean delete(ProceedingJoinPoint joinPoint){
        Class<?>[] classes = ((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();
        if (classes[0].equals(int.class)){
            cache.remove((Integer) joinPoint.getArgs()[0]);
            logger.info("Cart delete id: "+joinPoint.getArgs()[0]);
            return true;
        } else if (classes[0].equals(Product.class)) {
            Cart cart = (Cart) joinPoint.getArgs()[0];
            cache.remove(cart.getId());
            logger.info("Cart delete id: "+cart.getId());
            return true;
        }else return false;

    }

    private boolean post(ProceedingJoinPoint joinPoint){
        Cart cart = (Cart) joinPoint.getArgs()[0];
        cache.remove(cart.getId());
        cache.add(cart.getId(),cart);
        logger.info("Cart save id: "+cart.getId());
        return true;
    }

    private Cart get(ProceedingJoinPoint joinPoint) throws Throwable {
        Class<?>[] classes = ((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();
        if (classes[0].equals(int.class)){
            if (cache.containsKey((Integer) joinPoint.getArgs()[0])){
                logger.info("Get Cart id: "+joinPoint.getArgs()[0]+" from CACHE");
                Cart cart = cache.get((Integer) joinPoint.getArgs()[0]);
                return cart;
            }else {
                Cart cart = (Cart) joinPoint.proceed();
                cache.add(cart.getId(),cart);
                logger.info("Get Cart id: "+cart.getId()+" from BD and add to CACHE");
                return cart;
            }
        } else if (classes[0].equals(Product.class)) {
            Cart cart = (Cart) joinPoint.getArgs()[0];
            if(cache.containsKey(cart.getId())){
                logger.info("Get Cart id: "+cart.getId()+" from CACHE");
                Cart cart1 = cache.get(cart.getId());
                return cart1;
            }else {
                Cart cart1 = (Cart) joinPoint.proceed();
                cache.add(cart1.getId(),cart1);
                logger.info("Get Cart id: "+cart1.getId()+" from BD and add to CACHE");
                return cart1;
            }
        }else return null;
    }
}
