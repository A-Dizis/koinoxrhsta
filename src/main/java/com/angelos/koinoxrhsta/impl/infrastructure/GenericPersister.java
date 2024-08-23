package com.angelos.koinoxrhsta.impl.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.angelos.koinoxrhsta.def.infrastructure.Key;
import com.angelos.koinoxrhsta.impl.config.EntityConfiguration;

/**
 * Generic Persister abstracts the idea of seperate repositories in the
 * application
 * 
 * T is entity Class and K is the Class of the key of the entity.
 */
@Service
public class GenericPersister<T extends Key<K>, K> {

    @SuppressWarnings("unused")
    private final WebApplicationContext applicationContext;
    private Repositories repositories;

    public GenericPersister(WebApplicationContext applicationContext) {
        repositories = new Repositories(applicationContext);
        this.applicationContext = applicationContext;
    }

    /**
     * 
     * @param <T>    Entity
     * @param <K>    Entity Key
     * @param entity
     * @return
     */
    public T save(T entity) {
        return (T) getRepoFor(entity).save(entity);
    }

    /**
     * 
     * @param <T>    Entity
     * @param <K>    Entity Key
     * @param entity
     * @return
     */
    public T update(T entity) {
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
        getRepoFor(entity).delete(entity);
    }

    /**
     * 
     * @param <T>    Entity
     * @param <K>    Entity Key
     * @param entity
     * @return
     */
    public T read(T entity) {
        
        Optional<T> optionalEntity = getRepoFor(entity).findById(((Key<K>) entity).getKey());

        return optionalEntity.isPresent() ? optionalEntity.get() : null;
    }

    /**
     * 
     * @param <T>    Entity
     * @param <K>    Entity Key
     * @param entity
     * @return
     */
    public T read(K key) {

        Optional<T> optionalEntity = getRepoFor(key).findById(key);

        return optionalEntity.isPresent() ? optionalEntity.get() : null;
    }

    /**
     * Find the appropriate repo for the key.
     * 
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    private JpaRepository<T, K> getRepoFor(K key) {

        return (JpaRepository<T, K>) repositories.getRepositoryFor(EntityConfiguration.getEntityKeyMap().entrySet().stream()
                .filter(u -> u.getValue().equals(key.getClass())).findFirst().get().getKey())
                .orElseThrow(() -> new IllegalStateException("Can't find repository for key of type " + key.getClass()));
    }

    /**
     * Find the appropriate repo for the entity
     * 
     * @param entity
     * @return
     */
    @SuppressWarnings("unchecked")
    private JpaRepository<T, K> getRepoFor(T entity) {

        return (JpaRepository<T, K>) repositories.getRepositoryFor(entity.getClass())
                .orElseThrow(() -> new IllegalStateException("Can't find repository for entity of type " + entity.getClass()));
    }
}
