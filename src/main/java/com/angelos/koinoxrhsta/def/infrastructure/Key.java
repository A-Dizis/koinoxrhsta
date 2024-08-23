package com.angelos.koinoxrhsta.def.infrastructure;

/**
 * Key inteface.
 */
public interface Key<T> {

    /**
     * @return key.
     */
    public T getKey();

    /**
     * @param key
     */
    public void setKey(T key);
}
