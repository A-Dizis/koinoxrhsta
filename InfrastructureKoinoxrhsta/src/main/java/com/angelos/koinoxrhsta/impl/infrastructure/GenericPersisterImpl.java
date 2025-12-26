package com.angelos.koinoxrhsta.impl.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.angelos.koinoxrhsta.def.infrastructure.GenericPersister;
import com.angelos.koinoxrhsta.def.infrastructure.Key;
import com.angelos.koinoxrhsta.impl.exception.DataException;
import com.angelos.koinoxrhsta.impl.exception.NullArgumentException;

import lombok.Getter;
import lombok.Setter;

/**
 * Generic Persister abstracts the idea of seperate repositories in the
 * application.
 * 
 * T is entity Class and K is the Class of the key of the entity.
 * <p>
 * <p>
 * <b>Extenders of this class, should not implement any component scanning
 * annotation of the spring framework, they should be self contained and
 * be declared in a GenericPersister configuration class. The reason behind
 * it that framework injection is unwanted here (we use our injection) 
 * because it will lead to cyclic-dependency on repositories. </b>
 */
@Service
public class GenericPersisterImpl<T extends Key<K>, K> implements GenericPersister<T,K> {

    @Setter @Getter
    private RepositoryUtils repositoryUtils;

    /**
     * 
     */
    @Setter
    private JpaRepository<T, K> jpaRepository;

    /**
     * 
     * @param <T>    Entity
     * @param <K>    Entity Key
     * @param entity
     * @return
     * @throws DataException 
     * @throws NullArgumentException 
     */
    public T save(T entity) throws DataException, NullArgumentException {
        GenericPersisterUtils.check(entity);
        
        try {
            return (T) jpaRepository.save(entity);
        } catch (Exception e) {
            throw new DataException(e.getCause().getMessage()); 
        }
        
    }

    /**
     * 
     * @param <T>    Entity
     * @param <K>    Entity Key
     * @param entity
     * @return
     * @throws NullArgumentException 
     * @throws RepositoryException 
     */
    public T update(T entity) throws DataException, NullArgumentException {
        GenericPersisterUtils.check(entity);

        if(!jpaRepository.findById(entity.getKey()).isPresent()) {
            throw new DataException("No entity was found to update.");
        }

        return (T) jpaRepository.save(entity);
    }

    /**
     * 
     * @param <T>    Entity
     * @param <K>    Entity Key
     * @param entity
     * @return
     * @throws DataException 
     * @throws NullArgumentException 
     */
    public void delete(T entity) throws DataException, NullArgumentException {
        GenericPersisterUtils.check(entity);

        try{
            jpaRepository.delete(entity);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param <T>    Entity
     * @param <K>    Entity Key
     * @param entity
     * @return
     * @throws DataException 
     * @throws NullArgumentException 
     */
    public T read(T entity) throws DataException, NullArgumentException {
        GenericPersisterUtils.check(entity);
        
        Optional<T> optionalEntity = jpaRepository.findById(entity.getKey());

        if(optionalEntity.isPresent()) {
            return  optionalEntity.get();
        } else {
            throw new DataException("No entity found  on read.");
        }
    }

    /**
     * 
     * @param <T>    Entity
     * @param <K>    Entity Key
     * @param entity
     * @return
     * @throws DataException 
     * @throws NullArgumentException 
     */
    public T read(K entityKey) throws DataException, NullArgumentException {
        GenericPersisterUtils.check(entityKey);
        
        Optional<T> optionalEntity = jpaRepository.findById(entityKey);
        
        if(optionalEntity.isPresent()) {
            return  optionalEntity.get();
        } else {
            throw new DataException("No entity found on read.");
        }
    }

    /**
     * 
     * @return
     */
    public List<T> findAll(Example<T> example) {
        return jpaRepository.findAll(example);
    }

    /**
     * 
     * @return
     */
    public List<T> findAll() {
        return jpaRepository.findAll();
    }

    /**
     * 
     * @return
     */
    public List<T> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable).getContent();
    }
}
