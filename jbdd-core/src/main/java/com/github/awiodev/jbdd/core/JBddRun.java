package com.github.awiodev.jbdd.core;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public abstract class JBddRun<TSteps extends JBddSteps<?>> {

    private final Map<String, Object> context = new HashMap<>();
    private final TSteps steps;

    protected JBddRun(TSteps steps) {
        this.steps = steps;
    }

    public TSteps steps() {
        return steps;
    }

    protected abstract void clean();

    public void store(String key, Object value) {
        context.put(key, value);
    }

    public void store(Object value) {
        String key = value.getClass().getName();
        context.put(key, value);
    }

    public <T> T get(String key, Class<T> clazz) {
        Object value = context.get(clazz.getName());
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
        clean();
        context.clear();
    }
}
