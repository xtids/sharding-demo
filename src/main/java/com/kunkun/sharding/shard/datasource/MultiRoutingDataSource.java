package com.kunkun.sharding.shard.datasource;

import com.kunkun.sharding.shard.DatabaseContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by yankun on 2017/3/10.
 */
public class MultiRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DatabaseContextHolder.getDataSourceKey();
    }
}
