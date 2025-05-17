package com.angelos.koinoxrhsta.impl.op;

import com.angelos.koinoxrhsta.def.infrastructure.Operation;
import com.angelos.koinoxrhsta.impl.po.keys.FlatKey;

public class DeleteBillsOfFlatOperation  extends Operation {

    FlatKey flatKey;

    DeleteBillsOfFlatOperation(FlatKey flatKey) {
        this.flatKey = flatKey;
    }

    @Override
    public void execute() throws RuntimeException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
