package com.kunkun.sharding;

import com.kunkun.sharding.dao.UserDao;
import com.kunkun.sharding.domain.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yankun on 2017/3/10.
 */
public class UserDaoTest extends BaseTest{

    Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

    @Autowired
    private UserDao userDao;

    @Test
    public void testInsert() {

        User user = new User();
        user.setName("testname");

        boolean result = userDao.insertUser(user);
        logger.info("testInsert result {}", result);
    }

    @Test
    public void testGetById() {

        User user = userDao.getUserById(4);
        logger.info("testInsert result {}", user);
    }
}
