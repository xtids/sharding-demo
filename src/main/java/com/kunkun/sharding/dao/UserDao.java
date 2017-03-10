package com.kunkun.sharding.dao;

import com.kunkun.sharding.domain.User;
import com.kunkun.sharding.shard.annotation.MultiDataSource;
import org.apache.ibatis.annotations.Param;

/**
 * Created by yankun on 2017/3/10.
 */
public interface UserDao {

    boolean insertUser(User user);

    @MultiDataSource("DSA")
    User getUserById(@Param("id") int id);
}
