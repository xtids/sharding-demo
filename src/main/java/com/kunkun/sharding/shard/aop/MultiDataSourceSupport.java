package com.kunkun.sharding.shard.aop;

import com.kunkun.sharding.shard.DatabaseContextHolder;
import com.kunkun.sharding.shard.annotation.MultiDataSource;
import com.kunkun.sharding.shard.lookup.DataSourceLookup;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;

/**
 * Created by yankun on 2017/3/10.
 */
public class MultiDataSourceSupport {

    private static final Logger logger = LoggerFactory.getLogger(MultiDataSourceSupport.class);

    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        logger.debug("enter in =======> MultiDataSourceSupport");

        Signature signature = pjp.getSignature();

        DataSourceLookup lookup = getLookup(signature, pjp.getArgs());
        if (lookup != null) {
            DatabaseContextHolder.setLookup(lookup);
        }
        return pjp.proceed();
    }

    private DataSourceLookup getLookup(Signature signature, Object[] args) {
        String dataSourceKey = null;
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        MultiDataSource multiDataSource = AnnotationUtils.findAnnotation(method, MultiDataSource.class);
        if (multiDataSource != null) {
            dataSourceKey = multiDataSource.value();
        }

        if (dataSourceKey != null) {
            return new DataSourceLookup(dataSourceKey);
        } else {
            return null;
        }
    }
}
