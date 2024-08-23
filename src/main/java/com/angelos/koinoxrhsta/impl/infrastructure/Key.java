package com.angelos.koinoxrhsta.impl.infrastructure;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.BeanUtils;

import jakarta.persistence.Transient;

public abstract class Key<T> implements Serializable{

    @Transient
	private T key;

	public T getKey(Class<T> keyClazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		T key = keyClazz.getDeclaredConstructor().newInstance();
		BeanUtils.copyProperties(this, key);
		return key;
	}
    
    public void setKey(T key) {
		this.key = key;
		BeanUtils.copyProperties(key, this);
	}
}
