package com.angelos.koinoxrhsta.impl.op;

import java.util.List;

import org.springframework.stereotype.Service;

import com.angelos.koinoxrhsta.def.infrastructure.GenericPersister;
import com.angelos.koinoxrhsta.def.infrastructure.Operation;
import com.angelos.koinoxrhsta.impl.exception.DataException;
import com.angelos.koinoxrhsta.impl.exception.NullArgumentException;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersisterFactory;
import com.angelos.koinoxrhsta.impl.po.Bill;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.keys.BillKey;
import com.angelos.koinoxrhsta.impl.query.FindBillsOfFlatQuery;

import lombok.Getter;
import lombok.Setter;

@Service
public class DeleteBillsOfFlatOperation  extends Operation {

    @Setter @Getter
    Flat flat;

    List<Bill> bills;

    FindBillsOfFlatQuery query;

    private GenericPersisterFactory gpf;

    public DeleteBillsOfFlatOperation(FindBillsOfFlatQuery query, GenericPersisterFactory gpf) {
        this.query = query;
        this.gpf = gpf;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void execute() throws RuntimeException, DataException {
        query.setFlatKey(flat.getKey());
        query.execute();
        bills = query.getResultList();

        GenericPersister<Bill, BillKey> gpBill;
        try {
            gpBill = gpf.create(Bill.class);
        } catch (DataException e) {
            throw new RuntimeException(e.getMessage());
        }

        if(bills == null) {
            return;
        }

        for (Bill bill : bills) {
            try {
                gpBill.delete(bill);
            } catch (NullArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
