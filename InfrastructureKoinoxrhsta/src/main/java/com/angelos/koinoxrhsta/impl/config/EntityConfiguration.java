package com.angelos.koinoxrhsta.impl.config;

import java.util.HashMap;
import java.util.Map;

import com.angelos.koinoxrhsta.def.infrastructure.Key;

import jakarta.annotation.PostConstruct;
import lombok.Getter;

public abstract class EntityConfiguration {
    
    @Getter
    private static Map<Class<? extends Key<?>>, Class<?>> EntityKeyMap = new HashMap<>();

    public static <K> void addEntity(Class<? extends Key<K>> clazz, Class<K> clazzKey) {
        getEntityKeyMap().put(clazz, clazzKey);
    }

    @PostConstruct
    abstract void configure();
}
