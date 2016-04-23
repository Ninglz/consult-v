package com.dsecet.api.cache;

import com.dsecet.foundation.model.Token;

import java.io.Serializable;

/**
 * @author: lxp
 * Date: 2015/6/11 14:50
 * safeguard-v1
 */
public interface Cache<K extends Serializable, V>{

    V get(K key);

    V put(K key, V v);

    V putIfAbsent(K key, V value);

    boolean remove(K key);

    void clear();
}
