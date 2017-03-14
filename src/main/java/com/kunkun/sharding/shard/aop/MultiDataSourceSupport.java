package com.kunkun.sharding.shard.aop;

import com.kunkun.sharding.shard.DatabaseContextHolder;
import com.kunkun.sharding.shard.annotation.MultiDataSource;
import com.kunkun.sharding.shard.annotation.ShardOn;
import com.kunkun.sharding.shard.lookup.DataSourceLookup;
import com.kunkun.sharding.shard.strategy.ShardStrategy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by yankun on 2017/3/10.
 */
public class MultiDataSourceSupport {
    private static final Logger logger = LoggerFactory.getLogger(MultiDataSourceSupport.class);

    @Autowired
    private ShardStrategy shardStrategy;

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
        } else {
            throw new IllegalArgumentException("未添加MultiDataSource数据源注解");
        }

        Annotation [][] annotations = method.getParameterAnnotations();
        int index = -1;
        String shardKey = null;
        for (int i = 0; i < annotations.length; i ++) {
            Annotation [] paramAnnotations = annotations[i];
            for (int j = 0; j < paramAnnotations.length; j ++) {
                Annotation paramAnno = paramAnnotations[j];
                if (paramAnno instanceof ShardOn) {
                    index = i;
                    shardKey = (String) AnnotationUtils.getValue(paramAnno, "value");
                }
            }
        }
        if (index == -1) {
            return new DataSourceLookup(dataSourceKey);
        } else {
            String tableName = shardStrategy.getFinalTableName(shardKey, args[index]);
            return new DataSourceLookup(dataSourceKey, tableName);
        }

    }
}
