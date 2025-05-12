package com.angelos.koinoxrhsta.def.infrastructure;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.angelos.koinoxrhsta.impl.exception.AbstractQueryException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.Getter;

public abstract class AbstractQuery<T> extends Operation {

    /**
     * The {@link Type} of the argument T of the {@link AbstractQuery}.
     */
    @Getter
    private Type type;

    /**
     * {@link EntityManager} instance.
     */
    @PersistenceContext
    EntityManager entityManager;

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
        Type superClass = getClass().getGenericSuperclass();
        this.type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    /**
     * Set param for the query. A pair of the parameter name and the actual input. Eg setParam("numberLong", 1L);
     * 
     * @param paramName
     * @param param
     */
    public void setParam(String paramName, Object param) {
        params.put(paramName, param);
    }

    @Transactional
    public void execute() {
        setSql();
        doChecks();

        Query query = entityManager.createNativeQuery(sql, type.getClass());
        params.forEach((u, v) -> query.setParameter(u, v));
        query.executeUpdate();
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

            for (Entry<String, Object> param : params.entrySet()) {
                if (param.getValue() == null) {
                    throw new AbstractQueryException(String.format("Argument %s cannot be null", param.getKey()));
                }
            }
        } catch(AbstractQueryException e) {
            throw new RuntimeException(e);
        }
    } 

    /**
     * Hook in order to set the {@link AbstractQuery#sql} of the query.
     */
    protected abstract void setSql();
}
