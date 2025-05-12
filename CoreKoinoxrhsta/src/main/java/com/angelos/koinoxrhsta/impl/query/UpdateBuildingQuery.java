package com.angelos.koinoxrhsta.impl.query;

import org.springframework.stereotype.Service;

import com.angelos.koinoxrhsta.def.infrastructure.AbstractQuery;
import com.angelos.koinoxrhsta.impl.po.Building;

@Service
public class UpdateBuildingQuery extends AbstractQuery<Building> {

    @Override
    protected void setSql() {
       sql = "update tbbuilding set address_name=:name where building_id=:buildingId";
    }
}
