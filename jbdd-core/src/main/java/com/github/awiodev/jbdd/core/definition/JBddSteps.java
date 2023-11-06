package com.github.awiodev.jbdd.core.definition;

/**
 * Represents given/when/then steps object.
 * @param <TSteps> for steps type
 */
public interface JBddSteps<TSteps extends JBddSteps<?>> extends Steps {

    /**
     * Chained Given step definition.
     * @param given for step description
     * @param runnable for step body
     * @return steps type instance to enable steps chaining
     */
    TSteps given(String given, JBddRunnable runnable);

    /**
     * Chained When step definition.
     * @param when for step description
     * @param runnable for step body
     * @return steps type instance to enable steps chaining
     */
    TSteps when(String when, JBddRunnable runnable);

    /**
     * Chained Then step definition.
     * @param then for step description
     * @param runnable for step body
     * @return steps type instance to enable steps chaining
     */
    TSteps then(String then, JBddRunnable runnable);

    /**
     * Chained And step definition. And steps represents previous step type in the chain.
     * @param and for step description
     * @param runnable for step body
     * @return steps type instance to enable steps chaining
     */
    public TSteps and(String and, JBddRunnable runnable);

    /**
     * Returnable Given step definition.
     * @param given for step description
     * @param callable for function that returns value
     * @return a value for given type
     * @param <T> for a returned object type
     */
    <T> T given(String given, JBddCallable<T> callable);

    /**
     * Returnable When step definition.
     * @param when for step description
     * @param callable for function that returns value
     * @return a value for given type
     * @param <T> for a returned object type
     */
    <T> T when(String when, JBddCallable<T> callable);

    /**
     * Returnable Then step definition.
     * @param then for step description
     * @param callable for function that returns value
     * @return a value for given type
     * @param <T> for a returned object type
     */
    <T> T then(String then, JBddCallable<T> callable);

    /**
     * Returnable And step definition. And steps represents previous step type in the chain.
     * @param and for step description
     * @param callable for function that returns value
     * @return a value for given type
     * @param <T> for a returned object type
     */
    <T> T and(String and, JBddCallable<T> callable);
}
