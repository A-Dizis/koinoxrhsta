package com.angelos.koinoxrhsta.impl.op;

import org.springframework.boot.test.context.TestConfiguration;

import com.angelos.koinoxrhsta.impl.config.CoreEntityConfiguration;
import com.angelos.koinoxrhsta.impl.config.CoreMapperConfiguration;

@TestConfiguration
public class TestConfig {
    
    public TestConfig () {
        new CoreEntityConfiguration();
        new CoreMapperConfiguration();
    }

}
