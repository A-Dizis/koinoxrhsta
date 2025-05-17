package com.angelos.koinoxrhsta.impl.op;

import org.springframework.stereotype.Component;

import com.angelos.koinoxrhsta.def.infrastructure.Operation;
import com.angelos.koinoxrhsta.impl.exception.RepositoryException;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersister;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersisterFactory;
import com.angelos.koinoxrhsta.impl.po.Bill;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.FlatSpec;
import com.angelos.koinoxrhsta.impl.po.Parking;
import com.angelos.koinoxrhsta.impl.po.Warehouse;
import com.angelos.koinoxrhsta.impl.po.keys.BillKey;
import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;
import com.angelos.koinoxrhsta.impl.po.keys.FlatSpecKey;
import com.angelos.koinoxrhsta.impl.po.keys.ParkingKey;
import com.angelos.koinoxrhsta.impl.po.keys.WarehouseKey;

@Component
public class DeleteFlatOperation extends Operation {

    GenericPersisterFactory gpf;

    Flat flat;

    private GenericPersister<Flat, FlatKey> gpFlat;
    private GenericPersister<FlatSpec, FlatSpecKey> gpFlatSpec;
    private GenericPersister<Parking, ParkingKey> gpParking;
    private GenericPersister<Warehouse, WarehouseKey> gpWarehouse;
    private GenericPersister<Bill, BillKey> gpBill;

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public DeleteFlatOperation(GenericPersisterFactory gpf) {
        this.gpf = gpf;
    }

    @Override
    public void execute() throws RuntimeException {
        if(flat == null) {
            throw new RuntimeException("No flat is selected for deletion");
        }


        try {
            gpFlat = gpf.create(Flat.class);
            gpFlatSpec = gpf.create(FlatSpec.class);
            gpParking = gpf.create(Parking.class);
            gpWarehouse = gpf.create(Warehouse.class);
            gpBill = gpf.create(Bill.class);
        } catch (RepositoryException e) {
            throw new RuntimeException(e.getMessage());
        }

        flat = gpFlat.read(flat);
        gpFlatSpec.delete(flat.getFlatSpec());
        gpParking.delete(flat.getParking());
        gpWarehouse.delete(flat.getWarehouse());
        //@TODO Run bill deletion flow
        gpFlat.delete(gpFlat.read(flat));
    }
    
}
