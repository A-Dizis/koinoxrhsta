package com.angelos.koinoxrhsta.impl.infrastructure;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.BeanUtils;

import com.angelos.koinoxrhsta.def.infrastructure.Key;

import jakarta.persistence.Transient;

/**
 * This class should be used in entities and when a key property 
 * is needed. E.g. for repository usage. The {@link Key} interface
 * should be implemented in order to implement correctly the setKey(T entity)
 * and getKey() method. 
 * 
 * <b>Example:</b>
 * <pre>
 * {@code
 * public class Bill extends KeyImpl<BillKey> implements Key<BillKey>{
 * ...
 * }
 * </pre>
 */
public abstract class KeyImpl<T> implements Serializable{

    @Transient
	private T key;

	/**
	 * Can create a concrete implementation of a key within a given entity.
	 * Can be called with supper to help abstract the getKey() method
	 * of an entity.
	 * 
	 * <b>EXAMPLE:</b>
	 * <pre>
	 * {@code
	 *	public BillKey getKey() {
	 *	return super.getKey(BillKey.class);
	 * }
	 * </pre>
	 * 
	 * @param keyClazz
	 * @return
	 */
	protected T getKey(Class<T> keyClazz) {
		T key;
		try {
			key = keyClazz.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
		BeanUtils.copyProperties(this, key);
		return key;
	}

	/**
	 * Can create a concrete implementation of a key within a given entity. 
	 * Can be called with supper to help abstract the setKey(T entity) 
	 * method of an entity.
	 * 
	 * <b>EXAMPLE:</b>
	 * <pre>
	 * {@code
	 *	public void setKey(BillKey key) {
	 *	super.setKey(key);
	 * }
	 * </pre>
	 * @param key
	 */
    protected void setKey(T key) {
		this.key = key;
		BeanUtils.copyProperties(key, this);
	}
}
