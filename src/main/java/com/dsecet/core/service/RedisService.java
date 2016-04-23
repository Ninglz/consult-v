package com.dsecet.core.service;

import java.util.concurrent.TimeUnit;

/**
 * @author: lxl
 */
public interface RedisService{

    void put(String key, String value);

    boolean putIfAbsent(String key, String value);

    void remove(String key);

    String get(String key);

    Long size(String key);
}
