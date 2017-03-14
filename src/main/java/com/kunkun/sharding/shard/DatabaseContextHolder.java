package com.kunkun.sharding.shard;

import com.kunkun.sharding.shard.lookup.DataSourceLookup;
import com.kunkun.sharding.shard.strategy.ShardStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;

/**
 * Created by yankun on 2017/3/10.
 */
public class DatabaseContextHolder {

    private static final NamedThreadLocal<DataSourceLookup> contextHolder = new NamedThreadLocal<>("cache datasource");

    public static void setLookup(DataSourceLookup lookup) {
        contextHolder.set(lookup);
    }

    public static String getDataSourceKey() {
        DataSourceLookup lookup = contextHolder.get();
        if (lookup == null) {
            throw new IllegalArgumentException("未获取到数据源指定信息");
        }
        return lookup.getDataSourceKey();
    }

    public static String getTableName() {
        DataSourceLookup lookup = contextHolder.get();
        if (lookup == null) {
            throw new IllegalArgumentException("lookup为空");
        }
        if (lookup.getTableName() == null) {
            throw new IllegalArgumentException("未解析到表名");
        }
        return lookup.getTableName();
    }
}
