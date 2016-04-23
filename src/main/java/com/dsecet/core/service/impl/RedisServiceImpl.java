package com.dsecet.core.service.impl;

import com.dsecet.api.security.SessionToken;
import com.dsecet.core.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * @author: lxl
 */
@Service
public class RedisServiceImpl implements RedisService{

    @Autowired
    private RedisTemplate redisTemplate;

    private ValueOperations<String, String> valueOperations;

    private void initOperations(){
        valueOperations = redisTemplate.opsForValue();
    }

    @Override
    public void put(String key, String value){
        initOperations();
        valueOperations.set(key, value);
    }

    @Override
    public boolean putIfAbsent(String key, String value){
        initOperations();
        return valueOperations.setIfAbsent(key, value);
    }

    @Override
    public void remove(String key){
         redisTemplate.delete(key);
    }

    @Override
    public String get(String key){
        initOperations();
        return valueOperations.get(key);
    }

    @Override
    public Long size(String key){
        initOperations();
        return valueOperations.size(key);
    }
}
