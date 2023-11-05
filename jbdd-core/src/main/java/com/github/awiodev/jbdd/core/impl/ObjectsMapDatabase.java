package com.github.awiodev.jbdd.core.impl;

import com.github.awiodev.jbdd.core.definition.ObjectsDatabase;
import com.github.awiodev.jbdd.core.exceptions.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectsMapDatabase implements ObjectsDatabase {

    private final Map<String, Object> map;

    private ObjectsMapDatabase(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public boolean contains(String sessionId, String key) {
        return map.containsKey(uniqueKey(sessionId, key));
    }

    @Override
    public void save(String sessionId, String key, Object value) {
        map.put(uniqueKey(sessionId, key), value);
    }

    private String uniqueKey(String sessionId, String key) {
        return String.format("%s_%s", sessionId, key);
    }

    @Override
    public <T> T get(String sessionId, String key, Class<T> clazz) {
        Object value = map.get(uniqueKey(sessionId, key));
        if (value == null) {
            throw new NotFoundException(key);
        }
        return clazz.cast(value);
    }

    @Override
    public void delete(String sessionId, String key) {
        map.remove(uniqueKey(sessionId, key));
    }

    @Override
    public void deleteSession(String sessionId) {
        List<String> toRemove = new ArrayList<>();
        map.keySet().forEach(key -> {
            if (key.startsWith(sessionId)) {
                toRemove.add(key);
            }
        });
        toRemove.forEach(map::remove);
    }

    @Override
    public void deleteAll() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }

    public static ObjectsMapDatabaseBuilder builder() {
        return new ObjectsMapDatabaseBuilder();
    }

    public static final class ObjectsMapDatabaseBuilder {

        private Map<String, Object> map;

        private ObjectsMapDatabaseBuilder() {
        }

        /**
         * Provides map instance. If not provided default ConcurrentHashMap will be created.
         *
         * @param map for map instance
         * @return builder
         */
        public ObjectsMapDatabaseBuilder withMap(Map<String, Object> map) {
            this.map = map;
            return this;
        }

        public ObjectsMapDatabase build() {

            if (map == null) {
                map = new ConcurrentHashMap<>();
            }

            return new ObjectsMapDatabase(map);
        }
    }
}
