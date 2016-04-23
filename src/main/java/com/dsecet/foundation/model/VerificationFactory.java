package com.dsecet.foundation.model;

import java.io.Serializable;

/**
 * @author: Fablenas
 */
public interface VerificationFactory<K extends Serializable, C extends Serializable, T extends Serializable>{

    C code(K key);

    T ticket(K key, C code);

    K guessKey(T ticket);
}
