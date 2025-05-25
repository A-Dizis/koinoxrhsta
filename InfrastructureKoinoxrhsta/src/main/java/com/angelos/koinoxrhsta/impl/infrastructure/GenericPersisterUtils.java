package com.angelos.koinoxrhsta.impl.infrastructure;

import org.springframework.stereotype.Component;

import com.angelos.koinoxrhsta.def.infrastructure.Key;
import com.angelos.koinoxrhsta.impl.exception.NullArgumentException;

@Component
public class GenericPersisterUtils {

    public static <T extends Key<K>, K> boolean isNull(T t) {
        if (t == null) {
            return true;
        }
        return false;
    }

    public static <K> boolean isNull(K k) {
        if (k == null) {
            return true;
        }
        return false;
    }

    public static <T extends Key<K>, K> void check(T t) throws NullArgumentException {
        if (isNull(t)) {
            throw new NullArgumentException("Persister was given a null entity");
        }
    }

    public static <K> void check(K k) throws NullArgumentException {
        if (isNull(k)) {
            throw new NullArgumentException("Persister was given a null key");
        }
    }
}
