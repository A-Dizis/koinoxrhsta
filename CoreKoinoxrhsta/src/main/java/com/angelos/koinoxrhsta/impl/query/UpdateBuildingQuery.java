package com.angelos.koinoxrhsta.impl.query;

import org.springframework.stereotype.Service;

import com.angelos.koinoxrhsta.def.infrastructure.AbstractQuery;

@Service
public class UpdateBuildingQuery extends AbstractQuery {

    @Override
    protected void prepareQuery() {
       sql = "update tbbuilding set address_name=:name where building_id=:buildingId";
    }
}
