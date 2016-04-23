package com.dsecet.foundation.model;

import java.io.Serializable;

/**
 * @author Fablenas
 *
 */
public interface Token<K extends Serializable> {

	K getKey();

    boolean isExpired(Long expiredTime);

}
