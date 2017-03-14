package com.kunkun.sharding.shard.strategy;

import java.util.Map;

/**
 * Created by yankun on 2017/3/14.
 */


public class ModShardStrategy implements ShardStrategy {

    private Map<String, String> shardKey2TableName;

    @Override
    public String getFinalTableName(String shardKey, Object shardValue) {
        String tablePrefix = shardKey2TableName.get(shardKey);
        if (tablePrefix == null) {
            throw new IllegalArgumentException("未配置表前缀路由信息");
        }
        return tablePrefix + ((int) shardValue % 2);
    }

    public void setShardKey2TableName(Map<String, String> shardKey2TableName) {
        this.shardKey2TableName = shardKey2TableName;
    }
}
