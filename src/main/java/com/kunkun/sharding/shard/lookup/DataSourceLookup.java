package com.kunkun.sharding.shard.lookup;

/**
 * Created by yankun on 2017/3/10.
 */
public class DataSourceLookup {

    private String dataSourceKey;
    private String tableName;
    private boolean shard = false;

    public DataSourceLookup(String dataSourceKey) {
        this.dataSourceKey = dataSourceKey;
        this.tableName = null;
    }

    public DataSourceLookup(String dataSourceKey, String tableName) {
        shard = true;
        this.dataSourceKey = dataSourceKey;
        this.tableName = tableName;
    }

    public String getDataSourceKey() {
        return dataSourceKey;
    }

    public void setDataSourceKey(String dataSourceKey) {
        this.dataSourceKey = dataSourceKey;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean isShard() {
        return shard;
    }

    public void setShard(boolean shard) {
        this.shard = shard;
    }
}
