package com.angelos.koinoxrhsta.def.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.angelos.koinoxrhsta.impl.exception.AbstractQueryException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.Setter;

public abstract class AbstractQuery extends Operation {

    /**
     * The class type of the resulting object of the {@link AbstractQuery}.
     */
    @Setter
    private Class resultClazz;

    /**
     * {@link EntityManager} instance.
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Result returned after the query execution.
     */
    List resultList;

    /**
     * The sql input to be executed.
     */
    protected String sql = "";

    /**
     * Parameters map of the arguments of the query.
     */
    protected Map<String, Object> params = new HashMap<>();
    

    /**
     * Actual Class Type extraction during runtime.
     */
    protected AbstractQuery() {
    }

    /**
     * Set param for the query. A pair of the parameter name and the actual input. Eg setParam("numberLong", 1L);
     * 
     * @param paramName
     * @param param
     */
    public final void setParam(String paramName, Object param) {
        params.put(paramName, param);
    }

    public void execute() {
        prepareQuery();
        doChecks();

        Query query = entityManager.createNativeQuery(sql, resultClazz);
        params.forEach((u, v) -> query.setParameter(u, v));
        setResultList(query.getResultList());
    }

    protected void setResultList(List resultList){
        this.resultList = resultList;
    }

    /**
     * Basic checks before query execution.
     * 
     * @throws RuntimeException
     */
    private void doChecks() throws RuntimeException {
        try {
            if (sql.isBlank()) {
                throw new AbstractQueryException("Query is empty.");
            }

            if(resultClazz == null) {
                throw new AbstractQueryException("Query must return an object type.");
            }

            for (Entry<String, Object> param : params.entrySet()) {
                if (param.getValue() == null) {
                    throw new AbstractQueryException(String.format("Argument %s cannot be null", param.getKey()));
                }
            }
        } catch(AbstractQueryException e) {
            throw new RuntimeException(e);
        }
    } 

    public final List getResultList() {
        return resultList;
    }

    /**
     * Hook in order to set the {@link AbstractQuery#sql}, {@link AbstractQuery#params} and {@link AbstractQuery#resultClazz} of the query.
     */
    protected abstract void prepareQuery();
}
