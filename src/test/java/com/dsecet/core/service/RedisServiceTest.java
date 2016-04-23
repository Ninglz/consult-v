package com.dsecet.core.service;

import com.dsecet.ApplicationConfig;
import com.dsecet.api.security.SessionToken;
import com.dsecet.api.security.SessionTokenFactory;
import com.dsecet.foundation.model.Token;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @author: lxp
 * Date: 2015/7/2 14:28
 * safeguard-v1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@TransactionConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisServiceTest{

    @Autowired
    private RedisService sessionTokenRedisService;


    @Test
    public void test_put_01(){
        Long key = 101L;
        SessionToken token = new SessionToken(key);
        sessionTokenRedisService.put(String.valueOf(key), token.toString());
    }

    @Test
    public void test_get_02(){
        String sessionToken = sessionTokenRedisService.get(String.valueOf(101L));
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        System.out.println(sessionToken);
    }

    @Test
    public void test_sessionFactory_03(){
        SessionTokenFactory sessionTokenFactory = new SessionTokenFactory(sessionTokenRedisService);
        Token<Long> token = sessionTokenFactory.createToken(111L);
        System.out.println("Create session token:" + token);
    }

    @Test
    public void test_sessionFactory_04(){
        SessionTokenFactory sessionTokenFactory = new SessionTokenFactory(sessionTokenRedisService);
        Token<Long> token = sessionTokenFactory.get(111L);
        System.out.println("Get session token:" + token);
    }
}
