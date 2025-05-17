package com.angelos.koinoxrhsta.impl.config;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public abstract class EntityConfiguration {
    
    @Getter
    private static Map<Class<?>, Class<?>> EntityKeyMap = new HashMap<>();

    public static void addEntity(Class<?> clazz, Class<?> clazzKey) {
        getEntityKeyMap().put(clazz, clazzKey);
    }
}
