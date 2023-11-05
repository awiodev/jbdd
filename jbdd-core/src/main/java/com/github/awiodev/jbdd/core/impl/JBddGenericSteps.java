package com.github.awiodev.jbdd.core.impl;

import com.github.awiodev.jbdd.core.definition.JBddCallable;
import com.github.awiodev.jbdd.core.definition.JBddRunnable;
import com.github.awiodev.jbdd.core.definition.JBddSteps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JBddGenericSteps<TSteps extends JBddSteps<?>>
    implements JBddSteps<TSteps> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JBddGenericSteps.class);

    private final TSteps chainParent;

    public JBddGenericSteps(TSteps chainParent) {
        LOGGER.debug("Chain parent instance: {}", chainParent);
        this.chainParent = chainParent;
    }

    public TSteps given(String given, JBddRunnable runnable) {
        LOGGER.debug("given {}", given);
        return runnable(runnable);
    }

    public TSteps when(String when, JBddRunnable runnable) {
        LOGGER.debug("when {}", when);
        return runnable(runnable);
    }

    public TSteps then(String then, JBddRunnable runnable) {
        LOGGER.debug("then {}", then);
        return runnable(runnable);
    }

    public TSteps and(String and, JBddRunnable runnable) {
        LOGGER.debug("and {}", and);
        return runnable(runnable);
    }

    private TSteps runnable(JBddRunnable runnable) {
        runnable.perform();
        return chainParent;
    }

    public <T> T given(String given, JBddCallable<T> callable) {
        LOGGER.debug("given {}", given);
        return callable(callable);
    }

    public <T> T when(String when, JBddCallable<T> callable) {
        LOGGER.debug("when {}", when);
        return callable(callable);
    }

    public <T> T then(String then, JBddCallable<T> callable) {
        LOGGER.debug("then {}", then);
        return callable(callable);
    }

    public <T> T and(String and, JBddCallable<T> callable) {
        LOGGER.debug("and {}", and);
        return callable(callable);
    }

    private <T> T callable(JBddCallable<T> callable) {
        return callable.perform();
    }
}
