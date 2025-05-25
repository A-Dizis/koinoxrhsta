package com.angelos.koinoxrhsta.def.infrastructure;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.angelos.koinoxrhsta.impl.exception.DataException;
import com.angelos.koinoxrhsta.impl.exception.NullArgumentException;
import com.angelos.koinoxrhsta.impl.infrastructure.RepositoryUtils;

/**
 * Generic Persister abstracts the idea of seperate repositories in the
 * application
 * 
 * T is entity Class and K is the Class of the key of the entity.
 */
@Service
public interface GenericPersister<T extends Key<K>, K> {

    /**
     * 
     * @param repositoryUtils
     */
    public void setRepositoryUtils(RepositoryUtils repositoryUtils);

    /**
     * 
     * @param jpaRepository
     */
    public void setJpaRepository(JpaRepository<T, K> jpaRepository);

    /**
     * 
     * @param <T>    Entity
     * @param <K>    Entity Key
     * @param entity
     * @return
     * @throws DataException 
     * @throws NullArgumentException 
     */
    public T save(T entity) throws DataException, NullArgumentException;

    /**
     * 
     * @param <T>    Entity
     * @param <K>    Entity Key
     * @param entity
     * @return
     * @throws NullArgumentException 
     * @throws RepositoryException 
     */
    public T update(T entity) throws DataException, NullArgumentException;

    /**
     * 
     * @param <T>    Entity
     * @param <K>    Entity Key
     * @param entity
     * @return
     * @throws DataException 
     * @throws NullArgumentException 
     */
    public void delete(T entity) throws DataException, NullArgumentException;

    /**
     * 
     * @param <T>    Entity
     * @param <K>    Entity Key
     * @param entity
     * @return
     * @throws DataException 
     * @throws NullArgumentException 
     */
    public T read(T entity) throws DataException, NullArgumentException;

    /**
     * 
     * @param <T>    Entity
     * @param <K>    Entity Key
     * @param entity
     * @return
     * @throws DataException 
     * @throws NullArgumentException 
     */
    public T read(K entityKey) throws DataException, NullArgumentException;

    /**
     * 
     * @return
     */
    public List<T> findAll();

    /**
     * 
     * @return
     */
    public List<T> findAll(Pageable pageable);

}
