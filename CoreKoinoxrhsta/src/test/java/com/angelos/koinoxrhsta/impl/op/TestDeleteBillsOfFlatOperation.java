package com.angelos.koinoxrhsta.impl.op;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.angelos.koinoxrhsta.def.infrastructure.GenericPersister;
import com.angelos.koinoxrhsta.impl.exception.DataException;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersisterFactory;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDeleteBillsOfFlatOperation {
 
    private GenericPersisterFactory gpf;
    DeleteBillsOfFlatOperation op;

    @Autowired
	public void injectDependencies(GenericPersisterFactory gpf, DeleteBillsOfFlatOperation op) {
        this.gpf = gpf;
        this.op = op;
    }

    @Test
    public void testDeleteFlatOp() throws DataException {
        GenericPersister<Flat, FlatKey> gpFlat = gpf.create(Flat.class);
        Flat flat = new Flat();
        flat.setBuildingId(100073L);
        flat.setFlatId(1000067L);
        flat = gpFlat.read(flat);

        op.setFlat(flat);
        op.execute();
    }

}
