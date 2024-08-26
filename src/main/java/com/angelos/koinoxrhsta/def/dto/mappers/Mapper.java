package com.angelos.koinoxrhsta.def.dto.mappers;

import com.angelos.koinoxrhsta.def.dto.DTO;

/**
 * Mapper interface between Entity class <T>
 * and DTO<T> class <P>. 
 */
public interface Mapper<T, P extends DTO<T>> {

    /**
     * Maps from a DTO to an entity.
     * 
     * @param p
     * @return entity
     */
    public T mapFromDto(P p);

    /**
     * Maps from an entity to a DTO.
     * 
     * @param p
     * @return entity
     */
    public P mapToDto(T t);
}
