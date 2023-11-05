package com.github.awiodev.jbdd.core.definition;

/**
 * Describes how JBdd stores objects.
 */
public interface ObjectsDatabase {

    /**
     * Checks whether there is entry in database for given sessionId and key.
     * @param sessionId for session id
     * @param key for key name
     * @return true if exists or false when not exists
     */
    boolean contains(String sessionId, String key);

    /**
     * Saves value under given unique key. Overwrites existing.
     *
     * @param sessionId for if of the session
     * @param key       for value key name
     * @param value     for value object
     */
    void save(String sessionId, String key, Object value);

    /**
     * Retrieves stored key value and casts to provided class type.
     * Throws NoSuchElementException when value is not present in context.
     *
     * @param sessionId for if of the session
     * @param key       for key name
     * @param clazz     for object type that should be returned
     * @param <T>       for type of the returned object
     * @return value for given key
     */
    <T> T get(String sessionId, String key, Class<T> clazz);

    /**
     * Deletes entry from database.
     *
     * @param sessionId for if of the session
     * @param key       for key name
     */
    void delete(String sessionId, String key);

    /**
     * Deletes all values from database for given session.
     *
     * @param sessionId for if of the session
     */
    void deleteSession(String sessionId);

    /**
     * Deletes all values from database.
     */
    void deleteAll();

    /**
     * Returns total number of items in the database.
     * @return size
     */
    int size();
}
