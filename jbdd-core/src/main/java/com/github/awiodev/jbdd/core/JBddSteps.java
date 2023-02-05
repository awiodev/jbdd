package com.github.awiodev.jbdd.core;

public abstract class JBddSteps<TSteps extends JBddSteps<?>> {

    protected abstract TSteps child();

    public TSteps given(String given, JBddRunnable runnable) {
        return runnable(runnable);
    }

    public TSteps when(String when, JBddRunnable runnable) {
        return runnable(runnable);
    }

    public TSteps then(String then, JBddRunnable runnable) {
        return runnable(runnable);
    }

    public TSteps and(String and, JBddRunnable runnable) {
        return runnable(runnable);
    }

    protected TSteps runnable(JBddRunnable runnable) {
        runnable.perform();
        return child();
    }

    public <T> T given(String given, JBddCallable<T> callable) {
        return callable(callable);
    }

    public <T> T when(String when, JBddCallable<T> callable) {
        return callable(callable);
    }

    public <T> T then(String then, JBddCallable<T> callable) {
        return callable(callable);
    }

    public <T> T and(String and, JBddCallable<T> callable) {
        return callable(callable);
    }

    protected <T> T callable(JBddCallable<T> callable) {
        return callable.perform();
    }
}
