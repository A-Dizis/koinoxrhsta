package com.angelos.koinoxrhsta.impl.infrastructure;

import java.lang.reflect.InvocationTargetException;

import org.springframework.stereotype.Service;

import com.angelos.koinoxrhsta.def.dto.DTO;
import com.angelos.koinoxrhsta.def.dto.mappers.Mapper;
import com.angelos.koinoxrhsta.impl.config.MapperConfiguration;
import com.angelos.koinoxrhsta.impl.exception.MapperException;

@Service
public class GenericMapperFactory {
    

    @SuppressWarnings("unchecked")
    public <T, P extends DTO<T>> GenericMapper<T,P> create(Class<T> entityClazz) throws MapperException {
        
        Class<? extends Mapper<?, ?>> mapperClazz = MapperConfiguration.getEntityKeyMap().get(entityClazz);
        if(mapperClazz == null) {
            throw new MapperException("Mapper for " + entityClazz.getName() + " was not found");
        }

        Mapper<?, ?> concreteMapper;
        try {
            concreteMapper  = mapperClazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new MapperException("Instantiation of mapper class was not possible.");
        }

        GenericMapper<T, P> genericMapper = new GenericMapper<>();
        genericMapper.setMapper((Mapper<T, P>) concreteMapper);
        return genericMapper;
    }
}
