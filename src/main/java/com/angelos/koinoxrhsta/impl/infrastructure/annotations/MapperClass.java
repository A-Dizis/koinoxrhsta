package com.angelos.koinoxrhsta.impl.infrastructure.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface MapperClass {
    Class mapperClass();

    Class entityClass();
}
