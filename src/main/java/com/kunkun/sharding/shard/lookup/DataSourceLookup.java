package com.kunkun.sharding.shard.lookup;

/**
 * Created by yankun on 2017/3/10.
 */
public class DataSourceLookup {

    private String dataSourceKey;

    public DataSourceLookup(String dataSourceKey) {
        this.dataSourceKey = dataSourceKey;
    }

    public String getDataSourceKey() {
        return dataSourceKey;
    }

    public void setDataSourceKey(String dataSourceKey) {
        this.dataSourceKey = dataSourceKey;
    }
}
