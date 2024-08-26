package com.angelos.koinoxrhsta.impl.dto.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angelos.koinoxrhsta.def.dto.mappers.Mapper;
import com.angelos.koinoxrhsta.impl.dto.FlatDTO;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;

@Service
public class FlatMapper implements Mapper<Flat, FlatDTO> {

    @Autowired
    OwnerMapper ownerMapper;

    @Override
    public Flat mapFromDto(FlatDTO p) {
        Flat flat = new Flat();
        BeanUtils.copyProperties(p, flat);
        flat.setOwner(ownerMapper.mapFromDto(p.getOwner()));
        //MORE TO ADD

        FlatKey key = new FlatKey();
        BeanUtils.copyProperties(p, key);
        flat.setKey(key);

        return flat;
    }

    @Override
    public FlatDTO mapToDto(Flat t) {
        FlatDTO dto = new FlatDTO();
        BeanUtils.copyProperties(t, dto);
        dto.setOwner(ownerMapper.mapToDto(t.getOwner()));
        //MORE TO ADD
        
        return dto;
    }

}
