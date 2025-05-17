package com.angelos.koinoxrhsta.impl.config;

import java.util.HashMap;
import java.util.Map;

import com.angelos.koinoxrhsta.def.dto.mappers.Mapper;

import jakarta.annotation.PostConstruct;
import lombok.Getter;

public abstract class MapperConfiguration {
    
    @Getter
    private static Map<Class<?>, Class<? extends Mapper<?,?>>> EntityKeyMap = new HashMap<>();

    public static void addMapper(Class<?> clazz, Class<? extends Mapper<?,?>> clazzMapper) {
        getEntityKeyMap().put(clazz, clazzMapper);
    }

    @PostConstruct
    abstract void configure();
}
