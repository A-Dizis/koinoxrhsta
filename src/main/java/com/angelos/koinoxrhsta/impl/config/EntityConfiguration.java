package com.angelos.koinoxrhsta.impl.config;

import java.util.HashMap;
import java.util.Map;

import com.angelos.koinoxrhsta.impl.po.Bill;
import com.angelos.koinoxrhsta.impl.po.Building;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.FlatSpec;
import com.angelos.koinoxrhsta.impl.po.Issuer;
import com.angelos.koinoxrhsta.impl.po.Owner;
import com.angelos.koinoxrhsta.impl.po.Parking;
import com.angelos.koinoxrhsta.impl.po.Warehouse;
import com.angelos.koinoxrhsta.impl.po.keys.BillKey;
import com.angelos.koinoxrhsta.impl.po.keys.BuildingKey;
import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;
import com.angelos.koinoxrhsta.impl.po.keys.FlatSpecKey;
import com.angelos.koinoxrhsta.impl.po.keys.IssuerKey;
import com.angelos.koinoxrhsta.impl.po.keys.OwnerKey;
import com.angelos.koinoxrhsta.impl.po.keys.ParkingKey;
import com.angelos.koinoxrhsta.impl.po.keys.WarehouseKey;

import lombok.Getter;

public final class EntityConfiguration {
    
    @SuppressWarnings("rawtypes")
    @Getter
    private static Map<Class, Class> EntityKeyMap = new HashMap<>();

    private EntityConfiguration(){
    }

    static{
        EntityKeyMap.put(Bill.class, BillKey.class);
        EntityKeyMap.put(Building.class, BuildingKey.class);
        EntityKeyMap.put(Flat.class, FlatKey.class);
        EntityKeyMap.put(FlatSpec.class, FlatSpecKey.class);
        EntityKeyMap.put(Issuer.class, IssuerKey.class);
        EntityKeyMap.put(Owner.class, OwnerKey.class);
        EntityKeyMap.put(Parking.class, ParkingKey.class);
        EntityKeyMap.put(Warehouse.class, WarehouseKey.class);
    }

}
