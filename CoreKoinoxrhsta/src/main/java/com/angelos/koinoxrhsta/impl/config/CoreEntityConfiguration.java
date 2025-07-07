package com.angelos.koinoxrhsta.impl.config;

import org.springframework.context.annotation.Configuration;

import com.angelos.koinoxrhsta.impl.po.Bill;
import com.angelos.koinoxrhsta.impl.po.Building;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.FlatSpec;
import com.angelos.koinoxrhsta.impl.po.Issuer;
import com.angelos.koinoxrhsta.impl.po.Owner;
import com.angelos.koinoxrhsta.impl.po.Page;
import com.angelos.koinoxrhsta.impl.po.Parking;
import com.angelos.koinoxrhsta.impl.po.Warehouse;
import com.angelos.koinoxrhsta.impl.po.keys.BillKey;
import com.angelos.koinoxrhsta.impl.po.keys.BuildingKey;
import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;
import com.angelos.koinoxrhsta.impl.po.keys.FlatSpecKey;
import com.angelos.koinoxrhsta.impl.po.keys.IssuerKey;
import com.angelos.koinoxrhsta.impl.po.keys.OwnerKey;
import com.angelos.koinoxrhsta.impl.po.keys.PageKey;
import com.angelos.koinoxrhsta.impl.po.keys.ParkingKey;
import com.angelos.koinoxrhsta.impl.po.keys.WarehouseKey;

@Configuration
public class CoreEntityConfiguration extends EntityConfiguration {

    @Override
    void configure() {
        addEntity(Bill.class, BillKey.class);
        addEntity(Building.class, BuildingKey.class);
        addEntity(Flat.class, FlatKey.class);
        addEntity(FlatSpec.class, FlatSpecKey.class);
        addEntity(Issuer.class, IssuerKey.class);
        addEntity(Owner.class, OwnerKey.class);
        addEntity(Parking.class, ParkingKey.class);
        addEntity(Warehouse.class, WarehouseKey.class);
        addEntity(Page.class, PageKey.class);
    }

}
