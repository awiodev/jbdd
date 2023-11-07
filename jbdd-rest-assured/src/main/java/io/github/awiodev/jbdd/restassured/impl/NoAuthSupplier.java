package io.github.awiodev.jbdd.restassured.impl;

import io.github.awiodev.jbdd.restassured.definition.AuthSupplier;
import io.restassured.builder.RequestSpecBuilder;

/**
 * Does not provide any authorization to the request.
 * Used by default.
 */
public class NoAuthSupplier implements AuthSupplier {
    @Override
    public void supply(RequestSpecBuilder requestSpecBuilder) {
    }
}
