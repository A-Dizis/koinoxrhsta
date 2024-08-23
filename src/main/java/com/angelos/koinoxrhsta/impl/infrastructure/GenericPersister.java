package com.angelos.koinoxrhsta.impl.infrastructure;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

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
     * @param <T> Entity
     * @param <K> Entity Key
     * @param object
     * @return
     */
    @SuppressWarnings("unchecked")
    public T save(T object) {
        Object repository = repositories.getRepositoryFor(object.getClass()).orElseThrow(
                () -> new IllegalStateException(
                        "Can't find repository for entity of type " + object.getClass()));
        JpaRepository<T, K> repo = (JpaRepository<T, K>) repository;

        return (T) repo.save(object);
    }

    /**
     * 
     * @param <T> Entity
     * @param <K> Entity Key
     * @param object
     * @return
     */
    public T update(T object) {
        return (T) this.save(object);
    }

    /**
     * 
     * @param <T> Entity
     * @param <K> Entity Key
     * @param object
     * @return
     */
    @SuppressWarnings("unchecked")
    public void delete(T object) {
        Object repository = repositories.getRepositoryFor(object.getClass()).orElseThrow(
                () -> new IllegalStateException(
                        "Can't find repository for entity of type " + object.getClass()));
        JpaRepository<T, K> repo = (JpaRepository<T, K>) repository;

        repo.delete(object);
    }

    /**
     * 
     * @param <T> Entity
     * @param <K> Entity Key
     * @param object
     * @return
     */
    @SuppressWarnings("unchecked")
    public T read(T object, Class<K> keyClazz) {
        Object repository = repositories.getRepositoryFor(object.getClass()).orElseThrow(
                () -> new IllegalStateException(
                        "Can't find repository for entity of type " + object.getClass()));
        JpaRepository<T, K> repo = (JpaRepository<T, K>) repository;

        Optional<T> optionalEntity;
        try {
            optionalEntity = repo.findById(object.getKey(keyClazz));
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
        
        return optionalEntity.isPresent() ? optionalEntity.get() : null;
    }
}
