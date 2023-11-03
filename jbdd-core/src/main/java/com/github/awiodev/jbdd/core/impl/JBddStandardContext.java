package com.github.awiodev.jbdd.core.impl;

import com.github.awiodev.jbdd.core.definition.JBddContext;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class JBddStandardContext implements JBddContext {

    private final Map<String, Object> context;

    private JBddStandardContext(Map<String, Object> context) {
        this.context = context;
    }

    public void store(String key, Object value) {
        context.put(key, value);
    }

    public void store(Object value) {
        String key = value.getClass().getName();
        context.put(key, value);
    }

    public <T> T get(String key, Class<T> clazz) {
        Object value = context.get(key);
        if (value == null) {
            throw new NoSuchElementException(
                String.format("Context does not contain value for the given key: %s", key));
        }
        return clazz.cast(value);
    }

    public <T> T get(Class<T> clazz) {
        return get(clazz.getName(), clazz);
    }

    public void cleanup() {
        context.clear();
    }

    public static JBddStandardContextBuilder builder() {
        return new JBddStandardContextBuilder();
    }

    public static final class JBddStandardContextBuilder {
        private Map<String, Object> context;

        private JBddStandardContextBuilder() {
        }

        public JBddStandardContextBuilder withContext(Map<String, Object> context) {
            this.context = context;
            return this;
        }

        public JBddStandardContext build() {

            if (context == null) {
                context = new HashMap<>();
            }

            return new JBddStandardContext(context);
        }
    }
}
