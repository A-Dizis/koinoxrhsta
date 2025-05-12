package com.angelos.koinoxrhsta.def.infrastructure;

public abstract class Operation {
    
    /**
     * This is the method that logic should be executed. 
     * {@link Operation} class should be extends in units 
     * that are supposed to execute some kind of work.
     * 
     * @throws RuntimeException
     */
    public abstract void execute() throws RuntimeException;

}
