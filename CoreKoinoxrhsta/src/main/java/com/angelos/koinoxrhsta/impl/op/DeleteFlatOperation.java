package com.angelos.koinoxrhsta.impl.op;

import org.springframework.stereotype.Component;

import com.angelos.koinoxrhsta.def.infrastructure.Operation;
import com.angelos.koinoxrhsta.impl.exception.DataException;
import com.angelos.koinoxrhsta.impl.exception.NullArgumentException;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersisterFactory;
import com.angelos.koinoxrhsta.impl.po.Flat;

@Component
public class DeleteFlatOperation extends Operation {

    Flat flat;

    GenericPersisterFactory gpf;
    DeleteBillsOfFlatOperation billDelOp;

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public DeleteFlatOperation(GenericPersisterFactory gpf, DeleteBillsOfFlatOperation billDelOp) {
        this.billDelOp = billDelOp;
        this.gpf = gpf;
    }

    @Override
    public void execute() throws DataException {
        if (flat == null) {
            throw new RuntimeException("No flat is selected for deletion");
        }

        billDelOp.setFlat(flat);
        billDelOp.execute();

        try {
            gpf.create(Flat.class).delete(flat);
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }
    }

}
