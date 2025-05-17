package com.angelos.koinoxrhsta.def.dto.mappers;

import com.angelos.koinoxrhsta.def.dto.DTO;

/**
 * Mapper interface between Entity class <T>
 * and DTO<T> class <P>. 
 */
public abstract class Mapper<T, P extends DTO<T>> {

    /**
     * Maps from a DTO to an entity.
     * 
     * @param p
     * @return entity
     */
    public abstract T mapFromDto(P p);

    /**
     * Maps from an entity to a DTO.
     * 
     * @param p
     * @return entity
     */
    public abstract P mapToDto(T t);
}
