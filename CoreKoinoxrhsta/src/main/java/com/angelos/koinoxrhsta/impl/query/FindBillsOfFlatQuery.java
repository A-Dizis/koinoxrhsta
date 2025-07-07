package com.angelos.koinoxrhsta.impl.query;

import org.springframework.stereotype.Service;

import com.angelos.koinoxrhsta.impl.infrastructure.AbstractQuery;
import com.angelos.koinoxrhsta.impl.po.Bill;
import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;

import lombok.Getter;
import lombok.Setter;

@Service
public class FindBillsOfFlatQuery extends AbstractQuery {

    @Setter @Getter
    FlatKey flatKey;

    @Override
    protected void prepareQuery() {
        setSql("SELECT * FROM koinoxrhsta.TBBILL WHERE flat_id =:flatId  AND building_id =:buildingId");

        setParam("flatId", flatKey.getFlatId());
        setParam("buildingId", flatKey.getBuildingId());

        setResultClazz(Bill.class);
    }
}
