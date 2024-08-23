package com.angelos.koinoxrhsta.impl.dto.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.angelos.koinoxrhsta.def.dto.mappers.Mapper;
import com.angelos.koinoxrhsta.impl.dto.BuildingDTO;
import com.angelos.koinoxrhsta.impl.po.Building;

@Service
public class BuildingMapper implements Mapper<Building, BuildingDTO> {

    @Override
    public Building mapFromDto(BuildingDTO p) {
        Building building = new Building();
        BeanUtils.copyProperties(p, building);
        return building;
    }

    @Override
    public BuildingDTO mapToDto(Building t) {
        BuildingDTO dto = new BuildingDTO();
        BeanUtils.copyProperties(t, dto);
        return dto;
    }

}
