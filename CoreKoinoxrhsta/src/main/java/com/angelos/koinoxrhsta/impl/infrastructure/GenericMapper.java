package com.angelos.koinoxrhsta.impl.infrastructure;

import com.angelos.koinoxrhsta.def.dto.DTO;
import com.angelos.koinoxrhsta.def.dto.mappers.Mapper;

import lombok.Setter;

public class GenericMapper<T, P extends DTO<T>>{
    
    @Setter
    Mapper<T, P> mapper;

    public T mapFromDto(P p) {
        return mapper.mapFromDto(p);
    }

    public P mapToDto(T t) {
        return mapper.mapToDto(t);
    }
}
