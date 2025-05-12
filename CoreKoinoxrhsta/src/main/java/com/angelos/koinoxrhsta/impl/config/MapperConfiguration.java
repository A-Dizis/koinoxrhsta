package com.angelos.koinoxrhsta.impl.config;

import java.util.HashMap;
import java.util.Map;

import com.angelos.koinoxrhsta.def.dto.mappers.Mapper;
import com.angelos.koinoxrhsta.impl.dto.mappers.BuildingMapper;
import com.angelos.koinoxrhsta.impl.dto.mappers.FlatMapper;
import com.angelos.koinoxrhsta.impl.dto.mappers.OwnerMapper;
import com.angelos.koinoxrhsta.impl.po.Building;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.Owner;

import lombok.Getter;

public final class MapperConfiguration {
    
    @Getter
    private static Map<Class<?>, Class<? extends Mapper<?,?>>> EntityKeyMap = new HashMap<>();

    private MapperConfiguration(){
    }

    static{
        // EntityKeyMap.put(Bill.class, BillMapper.class);
        EntityKeyMap.put(Building.class, BuildingMapper.class);
        EntityKeyMap.put(Flat.class, FlatMapper.class);
        // EntityKeyMap.put(FlatSpec.class, FlatSpecMapper.class);
        // EntityKeyMap.put(Issuer.class, IssuerMapper.class);
        EntityKeyMap.put(Owner.class, OwnerMapper.class);
        // EntityKeyMap.put(Parking.class, ParkingMapper.class);
        // EntityKeyMap.put(Warehouse.class, WarehouseMapper.class);
    }

}
