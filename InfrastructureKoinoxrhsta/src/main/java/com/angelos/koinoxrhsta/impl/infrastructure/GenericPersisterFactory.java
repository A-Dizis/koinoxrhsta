package com.angelos.koinoxrhsta.impl.infrastructure;

import java.lang.reflect.InvocationTargetException;

import org.springframework.stereotype.Service;

import com.angelos.koinoxrhsta.def.infrastructure.GenericPersister;
import com.angelos.koinoxrhsta.def.infrastructure.Key;
import com.angelos.koinoxrhsta.impl.config.GenericPersisterConfiguration;
import com.angelos.koinoxrhsta.impl.exception.DataException;
import com.angelos.koinoxrhsta.impl.exception.GenericPersisterCreationException;

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
     * @throws DataException
     * @throws GenericPersisterCreationException 
     */
    @SuppressWarnings("unchecked")
    public <T extends Key<K>, K> GenericPersister<T, K> create(Class<T> entityClass) throws DataException {
        if(!repositoryUtils.getAllAvailableRepositories().contains(entityClass.getName())) {
            throw new DataException("Persister for class " + entityClass.getName() + " was not found.");
        }

        Class<? extends GenericPersister<?, ?>> persisterClazz = GenericPersisterConfiguration.getGenericPersisterKeyMap().get(entityClass);
        if(persisterClazz == null) {
            GenericPersister<T, K> genericPersister = new GenericPersisterImpl<>();
            genericPersister.setJpaRepository(repositoryUtils.getRepoFor(entityClass));
            genericPersister.setRepositoryUtils(repositoryUtils);
            
            return genericPersister;
        }
        
        GenericPersister<T, K> concreteGenericPersister;
        try {
            concreteGenericPersister = (GenericPersister<T, K>) persisterClazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(new GenericPersisterCreationException("Persister mapping for " + entityClass.getName() + " failed."));
        }
        concreteGenericPersister.setJpaRepository(repositoryUtils.getRepoFor(entityClass));
        concreteGenericPersister.setRepositoryUtils(repositoryUtils);
        return concreteGenericPersister;
    }
}
