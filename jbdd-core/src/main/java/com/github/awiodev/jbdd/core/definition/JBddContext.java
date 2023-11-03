package com.github.awiodev.jbdd.core.definition;

/**
 * Represents unique test context per run.
 */
public interface JBddContext {

    /**
     * Stores value under given unique key.
     * @param key for value key name
     * @param value for value object
     */
    void store(String key, Object value);

    /**
     * Stores value under the key obtained from value object type.
     * @param value for value object
     */
    void store(Object value);

    /**
     * Retrieves stored key value and casts to provided class type.
     * Throws NoSuchElementException when value is not present in context.
     * @param key for key name
     * @param clazz for object type that should be returned
     * @return value for given key
     * @param <T> for type of the returned object
     */
    <T> T get(String key, Class<T> clazz);

    /**
     * Retrieves stored value and casts to provided class type.
     * Key os obtained from given class name.
     * @param clazz for object type that should be returned
     * @return value
     * @param <T> for type of the returned object
     */
    <T> T get(Class<T> clazz);

    /**
     * Performs context cleanup.
     */
    void cleanup();
}
