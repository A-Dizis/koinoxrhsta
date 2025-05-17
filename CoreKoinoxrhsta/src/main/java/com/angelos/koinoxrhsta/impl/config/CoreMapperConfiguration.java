package com.angelos.koinoxrhsta.impl.config;

import org.springframework.context.annotation.Configuration;

import com.angelos.koinoxrhsta.impl.dto.mappers.BuildingMapper;
import com.angelos.koinoxrhsta.impl.dto.mappers.FlatMapper;
import com.angelos.koinoxrhsta.impl.dto.mappers.OwnerMapper;
import com.angelos.koinoxrhsta.impl.po.Building;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.Owner;

@Configuration
public class CoreMapperConfiguration extends MapperConfiguration {

    @Override
    void configure() {
        // EntityKeyMap.put(Bill.class, BillMapper.class);
        addMapper(Building.class, BuildingMapper.class);
        addMapper(Flat.class, FlatMapper.class);
        // EntityKeyMap.put(FlatSpec.class, FlatSpecMapper.class);
        // EntityKeyMap.put(Issuer.class, IssuerMapper.class);
        addMapper(Owner.class, OwnerMapper.class);
        // EntityKeyMap.put(Parking.class, ParkingMapper.class);
        // EntityKeyMap.put(Warehouse.class, WarehouseMapper.class);
    }

}
