package com.angelos.koinoxrhsta.impl.dto.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.angelos.koinoxrhsta.def.dto.mappers.Mapper;
import com.angelos.koinoxrhsta.impl.dto.OwnerDTO;
import com.angelos.koinoxrhsta.impl.po.Owner;

/**
 * OwnerMapper
 */
@Service
public class OwnerMapper implements Mapper<Owner, OwnerDTO>{

    @Override
    public Owner mapFromDto(OwnerDTO p) {
       Owner owner = new Owner();
       BeanUtils.copyProperties(p, owner);
       return owner;
    }

    @Override
    public OwnerDTO mapToDto(Owner t) {
        OwnerDTO dto = new OwnerDTO();
        BeanUtils.copyProperties(t, dto);
        return dto;
    }    
}