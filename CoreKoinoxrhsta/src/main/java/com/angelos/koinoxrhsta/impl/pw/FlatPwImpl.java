package com.angelos.koinoxrhsta.impl.pw;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.angelos.koinoxrhsta.def.infrastructure.Key;
import com.angelos.koinoxrhsta.impl.exception.DataException;
import com.angelos.koinoxrhsta.impl.exception.NullArgumentException;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersisterImpl;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersisterUtils;
import com.angelos.koinoxrhsta.impl.infrastructure.RepositoryUtils;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.FlatSpec;
import com.angelos.koinoxrhsta.impl.po.Parking;
import com.angelos.koinoxrhsta.impl.po.Warehouse;
import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;
import com.angelos.koinoxrhsta.impl.po.keys.FlatSpecKey;
import com.angelos.koinoxrhsta.impl.po.keys.ParkingKey;
import com.angelos.koinoxrhsta.impl.po.keys.WarehouseKey;

@SuppressWarnings("unused")
public class FlatPwImpl extends GenericPersisterImpl<Flat, FlatKey> {

    @Override
    public void delete(Flat flat) throws DataException {
        try {
            GenericPersisterUtils.check(flat.getFlatSpec());
            JpaRepository<FlatSpec, FlatSpecKey> flatspecRepo =  getRepositoryUtils().getRepoFor(FlatSpec.class);
            flatspecRepo.delete(flat.getFlatSpec());
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }

        try {
            GenericPersisterUtils.check(flat.getParking());
            JpaRepository<Parking, ParkingKey> parkingRepo = getRepositoryUtils().getRepoFor(Parking.class);
            parkingRepo.delete(flat.getParking());
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }

        try {
            GenericPersisterUtils.check(flat.getWarehouse());
            JpaRepository<Warehouse, WarehouseKey> warehouseRepo = getRepositoryUtils().getRepoFor(Warehouse.class);
            warehouseRepo.delete(flat.getWarehouse());
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }

        getRepositoryUtils().getRepoFor(Flat.class).delete(flat);
    }

    @Override
    public Flat save(Flat flat) throws NullArgumentException, DataException {
        if (!GenericPersisterUtils.isNull(flat.getFlatSpec())) {
            JpaRepository<FlatSpec, FlatSpecKey> flatspecRepo = getRepositoryUtils().getRepoFor(FlatSpec.class);
            flatspecRepo.save(flat.getFlatSpec());
        }

        if (!GenericPersisterUtils.isNull(flat.getParking())) {
            JpaRepository<Parking, ParkingKey> parkingRepo = getRepositoryUtils().getRepoFor(Parking.class);
            parkingRepo.save(flat.getParking());
        }

        if (!GenericPersisterUtils.isNull(flat.getWarehouse())) {
            JpaRepository<Warehouse, WarehouseKey> warehouseRepo = getRepositoryUtils().getRepoFor(Warehouse.class);
            warehouseRepo.save(flat.getWarehouse());
        }

        return getRepositoryUtils().getRepoFor(Flat.class).save(flat);
    }
 }
