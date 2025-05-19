package com.angelos.koinoxrhsta.impl.op;

import java.util.List;

import org.springframework.stereotype.Service;

import com.angelos.koinoxrhsta.def.infrastructure.Operation;
import com.angelos.koinoxrhsta.impl.exception.RepositoryException;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersister;
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

    @Override
    public void execute() throws RuntimeException {
        query.setFlatKey(flat.getKey());
        query.execute();
        bills = query.getResultList();

        GenericPersister<Bill, BillKey> gpBill;
        try {
            gpBill = gpf.create(Bill.class);
        } catch (RepositoryException e) {
            throw new RuntimeException(e.getMessage());
        }

        for (Bill bill : bills) {
            gpBill.delete(bill);
        }
    }
}
