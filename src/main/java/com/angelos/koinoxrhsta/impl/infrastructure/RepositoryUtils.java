package com.angelos.koinoxrhsta.impl.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.angelos.koinoxrhsta.def.infrastructure.Key;
import com.angelos.koinoxrhsta.impl.exception.RepositoryException;

import java.util.List;
import java.util.ArrayList;

@Service
public class RepositoryUtils {

    @SuppressWarnings("unused")
    private final WebApplicationContext applicationContext;
    private Repositories repositories;

    public RepositoryUtils(WebApplicationContext applicationContext) {
        repositories = new Repositories(applicationContext);
        this.applicationContext = applicationContext;
    }

    /**
     * Find the appropriate repo for the key.
     * 
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends Key<K>, K> JpaRepository<T, K> getRepoFor(Class<T> clazz) {

        try {
            return (JpaRepository<T, K>) repositories.getRepositoryFor(clazz)
                    .orElseThrow(() -> new RepositoryException("Can't find repository for type " + clazz));
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets a list of classNames available, based on the persistent entities of the system.
     * 
     * @return
     */
    public List<String> getAllAvailableRepositories() {
        List<String> repos = new ArrayList<>();
        repositories.forEach(u -> repos.add(u.getName()));

        System.out.println(repos);

        return repos;
    }
}
