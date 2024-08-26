package com.angelos.koinoxrhsta.impl.infrastructure;

import org.springframework.stereotype.Service;

import com.angelos.koinoxrhsta.def.infrastructure.Key;
import com.angelos.koinoxrhsta.impl.exception.RepositoryException;

@Service
public class GenericPersisterFactory {
    
    RepositoryUtils repositoryUtils;

    public GenericPersisterFactory(RepositoryUtils repositoryUtils){
        this.repositoryUtils = repositoryUtils;
    }

    /**
     * This method return a generic persister object (DAO)
     * for the given entity to be persisted.
     * 
     * @param <T>
     * @param <K>
     * @param entityClass
     * @return GenericPersister<T, K>
     * @throws RepositoryException
     */
    public <T extends Key<K>, K> GenericPersister<T, K> create(Class<T> entityClass) throws RepositoryException {
        if(!repositoryUtils.getAllAvailableRepositories().contains(entityClass.getName())) {
            throw new RepositoryException("Persister for class " + entityClass.getName() + " was not found.");
        }

        GenericPersister<T, K> genericPersister = new GenericPersister<>();
        genericPersister.setJpaRepository(repositoryUtils.getRepoFor(entityClass));
        
        return genericPersister;
    }
}
