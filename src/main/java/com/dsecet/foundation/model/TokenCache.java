package com.dsecet.foundation.model;

import java.io.Serializable;

/**
 * 
 * @author Fablenas
 * 
 */
public interface TokenCache<K extends Serializable> {

	Token<K> get(K key);

	Token<K> put(Token<K> token);

	boolean remove(K key);
}