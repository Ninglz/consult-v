package com.dsecet.foundation.model;

import java.io.Serializable;

/**
 * @author: Fablenas
 */
public interface VerificationCache<K extends Serializable, C extends Serializable, T extends Serializable>{

    C produceCode(K key);

    boolean consumeCode(K key, C code);

    T produceTicket(K key, C code);

    boolean consumeTicket(K key, T ticket);

    K guessKey(T ticket);
}
