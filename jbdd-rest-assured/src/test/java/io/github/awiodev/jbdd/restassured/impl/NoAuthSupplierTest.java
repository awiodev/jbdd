package io.github.awiodev.jbdd.restassured.impl;

import io.restassured.builder.RequestSpecBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NoAuthSupplierTest {

    @Test
    void NoAuthSupplierDoesNotEnrichRequest() {
        var request = Mockito.mock(RequestSpecBuilder.class);
        NoAuthSupplier noAuthSupplier = new NoAuthSupplier();
        noAuthSupplier.supply(request);
        Mockito.verifyNoInteractions(request);
    }
}
