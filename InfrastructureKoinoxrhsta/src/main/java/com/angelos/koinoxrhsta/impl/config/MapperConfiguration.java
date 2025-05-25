package com.angelos.koinoxrhsta.impl.config;

import java.util.HashMap;
import java.util.Map;

import com.angelos.koinoxrhsta.def.dto.DTO;
import com.angelos.koinoxrhsta.def.dto.mappers.Mapper;

import jakarta.annotation.PostConstruct;
import lombok.Getter;

public abstract class MapperConfiguration {
    
    @Getter
    private static Map<Class<?>, Class<? extends Mapper<?,?>>> MapperKeyMap = new HashMap<>();

    public static <E> void addMapper(Class<E> clazz, Class<? extends Mapper<E, ? extends DTO<E>>> clazzMapper) {
        getMapperKeyMap().put(clazz, clazzMapper);
    }

    @PostConstruct
    abstract void configure();
}
