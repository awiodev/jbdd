package com.github.awiodev.jbdd.core.impl;

import com.github.awiodev.jbdd.core.definition.JBddContext;
import com.github.awiodev.jbdd.core.definition.ObjectsDatabase;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JBddStandardContext implements JBddContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(JBddStandardContext.class);

    private final String sessionId;
    private final ObjectsDatabase database;

    private JBddStandardContext(String sessionId, ObjectsDatabase database) {
        this.sessionId = sessionId;
        this.database = database;
    }

    public void store(String key, Object value) {
        database.save(sessionId, key, value);
    }

    public void store(Object value) {
        String key = value.getClass().getName();
        database.save(sessionId, key, value);
    }

    public <T> T get(String key, Class<T> clazz) {
        return database.get(sessionId, key, clazz);
    }

    public <T> T get(Class<T> clazz) {
        return get(clazz.getName(), clazz);
    }

    public void cleanup() {
        database.deleteSession(sessionId);
    }

    public static JBddStandardContextBuilder builder() {
        return new JBddStandardContextBuilder();
    }

    public static final class JBddStandardContextBuilder {
        private ObjectsDatabase database;

        private JBddStandardContextBuilder() {
        }

        public JBddStandardContextBuilder withDatabase(ObjectsDatabase database) {
            this.database = database;
            LOGGER.debug("Provided database instance: {}", database);
            return this;
        }

        public JBddStandardContext build() {

            if (database == null) {
                LOGGER.info("Database not provided. ObjectsMapDatabase will be used as default");
                database = ObjectsMapDatabase.builder().build();
            }

            String sessionId = UUID.randomUUID().toString();
            LOGGER.info("Generated context session id: {}", sessionId);
            return new JBddStandardContext(sessionId, database);
        }
    }
}
