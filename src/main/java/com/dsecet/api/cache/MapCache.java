package com.dsecet.api.cache;

import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.Map;

/**
 * @author: lxp
 * Date: 2015/6/11 14:46
 * safeguard-v1
 */
public abstract class MapCache<V extends Serializable> implements Cache<String, V>{

    private final Map<String, V> caches = Maps.newConcurrentMap();

    @Override
    public V get(String key){
        return caches.get(key);
    }

    @Override
    public V put(String key, V value){
        return caches.put(key, value);
    }

    @Override
    public V putIfAbsent(String key, V value){
        return caches.putIfAbsent(key, value);
    }

    public boolean remove(String key){
        return null != caches.remove(key);
    }

    @Override
    public void clear(){
        caches.clear();
    }
}
