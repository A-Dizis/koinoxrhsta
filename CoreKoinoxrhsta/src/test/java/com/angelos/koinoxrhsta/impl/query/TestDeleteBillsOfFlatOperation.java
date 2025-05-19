package com.angelos.koinoxrhsta.impl.query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.angelos.koinoxrhsta.impl.exception.RepositoryException;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersister;
import com.angelos.koinoxrhsta.impl.infrastructure.GenericPersisterFactory;
import com.angelos.koinoxrhsta.impl.op.DeleteBillsOfFlatOperation;
import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;

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
    public void testDeleteFlatOp() throws RepositoryException {
        GenericPersister<Flat, FlatKey> gpFlat = gpf.create(Flat.class);
        Flat flat = new Flat();
        flat.setBuildingId(100073L);
        flat.setFlatId(1000067L);
        flat = gpFlat.read(flat);

        op.setFlat(flat);
        op.execute();
    }

}
