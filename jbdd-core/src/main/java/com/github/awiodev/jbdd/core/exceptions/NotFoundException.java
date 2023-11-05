package com.github.awiodev.jbdd.core.exceptions;

/**
 * Runtime exception thrown when no entry for given field or key
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String name) {
        super(String.format("Value for provided key: '%s' does not exists!", name));
    }
}
