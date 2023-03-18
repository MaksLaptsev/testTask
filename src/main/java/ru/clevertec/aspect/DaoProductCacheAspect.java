package ru.clevertec.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import ru.clevertec.annotation.CrudAnnotation;
import ru.clevertec.annotation.MethodType;
import ru.clevertec.cache.Cache;
import ru.clevertec.entity.Product;
import ru.clevertec.utils.cacheFactory.CacheFactory;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is used as an aspect to synchronize the operation of the dao with the cache
 */
@Aspect
public class DaoProductCacheAspect {
    static private final Logger logger = LoggerFactory.getLogger(DaoProductCacheAspect.class);
    private final Cache<Integer, Product> cache;

    public DaoProductCacheAspect() {
        this.cache = new CacheFactory<Integer,Product>().cacheInitialize();
        logger.info("Initialize ProductCache with type: "+cache.getType());
    }

    @Pointcut("execution(* ru.clevertec.dao.ProductDao.*(..))")
    public void productDaoMethod(){}

    @Pointcut("@annotation(ru.clevertec.annotation.CrudAnnotation)")
    public void productDaoPUT(){}

    /**
     * In this method, synchronization occurs between the methods of the dao class and the cache
     * @param joinPoint the executable method that was intercepted by the aspect
     * @return returns the desired object
     * @throws Throwable
     */
    @Around("productDaoMethod() && productDaoPUT()")
    public Object personDaoPUT(ProceedingJoinPoint joinPoint) throws Throwable {
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
        Product product = (Product) joinPoint.getArgs()[0];
        cache.remove(product.getId());
        cache.add(product.getId(),product);
        logger.info("Product update and save in BD and CACHE - id: "+product.getId());
        return true;
    }

    private boolean delete(ProceedingJoinPoint joinPoint){
        Class<?>[] classes = ((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();
        if (classes[0].equals(int.class)){
            cache.remove((Integer) joinPoint.getArgs()[0]);
            logger.info("Product delete if: "+joinPoint.getArgs()[0]);
            return true;
        } else if (classes[0].equals(Product.class)) {
            Product product = (Product) joinPoint.getArgs()[0];
            cache.remove(product.getId());
            logger.info("Product delete id: "+product.getId());
            return true;
        }else return false;

    }

    private boolean post(ProceedingJoinPoint joinPoint){
        Product product = (Product) joinPoint.getArgs()[0];
        cache.remove(product.getId());
        cache.add(product.getId(),product);
        logger.info("Product save id: "+product.getId());
        return true;
    }

    private Product get(ProceedingJoinPoint joinPoint) throws Throwable {
        Class<?>[] classes = ((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();
        if (classes[0].equals(int.class)){
            if (cache.containsKey((Integer) joinPoint.getArgs()[0])){
                logger.info("Get Product id: "+joinPoint.getArgs()[0]+" from CACHE");
                Product product = cache.get((Integer) joinPoint.getArgs()[0]);
                return product;
            }else {
                Product product = (Product) joinPoint.proceed();
                cache.add(product.getId(),product);
                logger.info("Get Product id: "+product.getId()+" from BD and add to CACHE");
                return product;
            }
        } else if (classes[0].equals(Product.class)) {
            Product product = (Product) joinPoint.getArgs()[0];
            if(cache.containsKey(product.getId())){
                logger.info("Get Product id: "+product.getId()+" from CACHE");
                Product product1 = cache.get(product.getId());
                return product1;
            }else {
                Product product1 = (Product) joinPoint.proceed();
                cache.add(product1.getId(),product1);
                logger.info("Get Product id: "+product1.getId()+" from BD and add to CACHE");
                return product1;
            }
        }else return null;
    }

}

