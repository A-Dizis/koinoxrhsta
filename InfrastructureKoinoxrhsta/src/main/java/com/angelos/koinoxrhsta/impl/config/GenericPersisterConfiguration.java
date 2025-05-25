package com.angelos.koinoxrhsta.impl.config;

import java.util.HashMap;
import java.util.Map;

import com.angelos.koinoxrhsta.def.infrastructure.GenericPersister;
import com.angelos.koinoxrhsta.def.infrastructure.Key;

import jakarta.annotation.PostConstruct;
import lombok.Getter;

public abstract class GenericPersisterConfiguration {
    
    @Getter
    private static Map<Class<?>, Class<? extends GenericPersister<?,?>>> GenericPersisterKeyMap = new HashMap<>();

    public static <E> void addGenericPersister(Class<? extends Key<E>> clazz, Class<? extends GenericPersister<? extends Key<E>, ? extends E>> clazzMapper) {
        getGenericPersisterKeyMap().put(clazz, clazzMapper);
    }

    @PostConstruct
    abstract void configure();
}
