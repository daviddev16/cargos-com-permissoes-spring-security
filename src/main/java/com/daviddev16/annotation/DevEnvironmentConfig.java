package com.daviddev16.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Configuration
@Profile(value = "dev")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DevEnvironmentConfig { }
