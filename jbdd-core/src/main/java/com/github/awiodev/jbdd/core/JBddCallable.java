package com.github.awiodev.jbdd.core;

@FunctionalInterface
public interface JBddCallable<T> {
    T perform();
}
