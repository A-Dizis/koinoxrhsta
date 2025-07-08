package com.angelos.koinoxrhsta.impl.query;

import org.springframework.stereotype.Service;

import com.angelos.koinoxrhsta.impl.infrastructure.AbstractQuery;
import com.angelos.koinoxrhsta.impl.po.Building;
import com.angelos.koinoxrhsta.impl.po.Flat;

import lombok.Getter;
import lombok.Setter;

@Service
public class FindFlatsOfBuildingQuery extends AbstractQuery {

    @Setter @Getter
    Building building;

    @Override
    protected void prepareQuery() {
        setSql("SELECT * FROM koinoxrhsta.TBFLAT WHERE building_id =:buildingId");

        setParam("buildingId", building.getBuildingId());

        setResultClazz(Flat.class);
    }
}
