package com.kunkun.sharding.shard.strategy;

/**
 * Created by yankun on 2017/3/14.
 */
public interface ShardStrategy {
    String getFinalTableName(String shardKey, Object shardValue);
}
