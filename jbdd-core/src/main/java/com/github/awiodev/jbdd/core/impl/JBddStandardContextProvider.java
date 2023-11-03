package com.github.awiodev.jbdd.core.impl;

import com.github.awiodev.jbdd.core.definition.JBddContextProvider;

public class JBddStandardContextProvider implements JBddContextProvider<JBddStandardContext> {
    @Override
    public JBddStandardContext provide() {
        return JBddStandardContext.builder().build();
    }
}
