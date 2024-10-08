package com.angelos.koinoxrhsta.impl.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.angelos.koinoxrhsta.def.infrastructure.Key;
import com.angelos.koinoxrhsta.impl.exception.RepositoryException;

import lombok.Setter;

/**
 * Generic Persister abstracts the idea of seperate repositories in the
 * application
 * 
 * T is entity Class and K is the Class of the key of the entity.
 */
@Service
public class GenericPersister<T extends Key<K>, K> {

    @Setter
    private JpaRepository<T, K> jpaRepository;

    /**
     * 
     * @param <T>    Entity
     * @param <K>    Entity Key
     * @param entity
     * @return
     */
    public T save(T entity) {
        return (T) jpaRepository.save(entity);
    }

    /**
     * 
     * @param <T>    Entity
     * @param <K>    Entity Key
     * @param entity
     * @return
     * @throws RepositoryException 
     */
    public T update(T entity) throws RepositoryException {
        if(!jpaRepository.findById(entity.getKey()).isPresent()) {
            throw new RepositoryException("No entity was found to update.");
        }

        return (T) this.save(entity);
    }

    /**
     * 
     * @param <T>    Entity
     * @param <K>    Entity Key
     * @param entity
     * @return
     */
    public void delete(T entity) {
        jpaRepository.delete(entity);
    }

    /**
     * 
     * @param <T>    Entity
     * @param <K>    Entity Key
     * @param entity
     * @return
     */
    public T read(T entity) {
        
        Optional<T> optionalEntity = jpaRepository.findById(entity.getKey());

        return optionalEntity.isPresent() ? optionalEntity.get() : null;
    }

    /**
     * 
     * @param <T>    Entity
     * @param <K>    Entity Key
     * @param entity
     * @return
     */
    public T read(K entityKey) {
        
        Optional<T> optionalEntity = jpaRepository.findById(entityKey);

        return optionalEntity.isPresent() ? optionalEntity.get() : null;
    }

    /**
     * 
     * @return
     */
    public List<T> findAll() {
        return jpaRepository.findAll();
    }
}
