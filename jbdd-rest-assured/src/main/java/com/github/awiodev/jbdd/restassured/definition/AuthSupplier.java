package com.github.awiodev.jbdd.restassured.definition;

import io.restassured.builder.RequestSpecBuilder;

/**
 * Provides possibility to compliment request specification with authorization
 */
@FunctionalInterface
public interface AuthSupplier {

    void supply(RequestSpecBuilder requestSpecBuilder);
}
