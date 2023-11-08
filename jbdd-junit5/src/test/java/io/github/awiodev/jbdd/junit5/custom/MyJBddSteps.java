package io.github.awiodev.jbdd.junit5.custom;

import io.github.awiodev.jbdd.core.definition.JBddCallable;
import io.github.awiodev.jbdd.core.impl.JBddGenericSteps;
import io.github.awiodev.jbdd.core.definition.JBddRunnable;
import io.github.awiodev.jbdd.core.definition.JBddSteps;

public class MyJBddSteps implements JBddSteps<MyJBddSteps> {

    private JBddGenericSteps<MyJBddSteps> parent;

    private MyJBddSteps() {
        parent = new JBddGenericSteps<>(this);
    }

    public int myCustomStepMethod() {
        return 10;
    }

    @Override
    public MyJBddSteps given(String given, JBddRunnable runnable) {
        return parent.given(given, runnable);
    }

    @Override
    public MyJBddSteps when(String when, JBddRunnable runnable) {
        return parent.when(when, runnable);
    }

    @Override
    public MyJBddSteps then(String then, JBddRunnable runnable) {
        return parent.then(then, runnable);
    }

    @Override
    public MyJBddSteps and(String and, JBddRunnable runnable) {
        return parent.and(and, runnable);
    }

    @Override
    public <T> T given(String given, JBddCallable<T> callable) {
        return parent.given(given, callable);
    }

    @Override
    public <T> T when(String when, JBddCallable<T> callable) {
        return parent.when(when, callable);
    }

    @Override
    public <T> T then(String then, JBddCallable<T> callable) {
        return parent.then(then, callable);
    }

    @Override
    public <T> T and(String and, JBddCallable<T> callable) {
        return parent.and(and, callable);
    }

    public static MyJBddStepsBuilder builder() {
        return new MyJBddStepsBuilder();
    }

    public static final class MyJBddStepsBuilder {
        private JBddGenericSteps<MyJBddSteps> parent;

        private MyJBddStepsBuilder() {
        }

        public MyJBddSteps build() {
            return new MyJBddSteps();
        }
    }
}
