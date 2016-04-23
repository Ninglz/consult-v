package com.dsecet.foundation.model;

import java.io.Serializable;

/**
 * 
 * @author Fablenas
 *
 */
public interface TokenFactory<K extends Serializable> {

	Token<K> createToken(K key);

	Token<K> get(K key);

	void remove(K key);

}