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

    public <T extends Key<K>, K> GenericPersister<T, K> create(Class<T> persisterClass) throws RepositoryException {
        if(!repositoryUtils.getAllAvailableRepositories().contains(persisterClass.getName())) {
            throw new RepositoryException("Persister for class " + persisterClass.getName() + " was not found.");
        }

        GenericPersister<T, K> genericPersister = new GenericPersister<>();
        genericPersister.setJpaRepository(repositoryUtils.getRepoFor(persisterClass));
        
        return genericPersister;
    }
}
