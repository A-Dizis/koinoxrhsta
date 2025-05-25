package com.angelos.koinoxrhsta.impl.config;

import org.springframework.context.annotation.Configuration;

import com.angelos.koinoxrhsta.impl.po.Flat;
import com.angelos.koinoxrhsta.impl.pw.FlatPwImpl;

@Configuration
public class CoreGenericPersisterConfiguration extends GenericPersisterConfiguration {

    @Override
    void configure() {
        addGenericPersister(Flat.class, FlatPwImpl.class);
    }
}
