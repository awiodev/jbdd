package io.github.awiodev.jbdd.allure;

import io.github.awiodev.jbdd.core.impl.JBddGenericSteps;
import io.github.awiodev.jbdd.core.definition.JBddCallable;
import io.github.awiodev.jbdd.core.definition.JBddRunnable;
import io.github.awiodev.jbdd.core.definition.JBddSteps;
import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.qameta.allure.model.Parameter;

public class JBddAllureSteps implements JBddSteps<JBddAllureSteps> {

    private final JBddSteps<JBddAllureSteps> parent;

    private JBddAllureSteps() {
        parent = new JBddGenericSteps<>(this);
    }

    private JBddAllureSteps(JBddSteps<JBddAllureSteps> parent) {
        this.parent = parent;
    }

    @Step("given {given}")
    public JBddAllureSteps given(@Param(mode = Parameter.Mode.HIDDEN) String given,
                                 @Param(mode = Parameter.Mode.HIDDEN) JBddRunnable runnable) {
        return parent.given(given, runnable);
    }

    @Step("when {when}")
    public JBddAllureSteps when(@Param(mode = Parameter.Mode.HIDDEN) String when,
                                @Param(mode = Parameter.Mode.HIDDEN) JBddRunnable runnable) {
        return parent.when(when, runnable);
    }

    @Step("then {then}")
    public JBddAllureSteps then(@Param(mode = Parameter.Mode.HIDDEN) String then,
                                @Param(mode = Parameter.Mode.HIDDEN) JBddRunnable runnable) {
        return parent.then(then, runnable);
    }

    @Step("and {then}")
    public JBddAllureSteps and(@Param(mode = Parameter.Mode.HIDDEN) String and,
                               @Param(mode = Parameter.Mode.HIDDEN) JBddRunnable runnable) {
        return parent.and(and, runnable);
    }

    @Step("given {given}")
    public <T> T given(@Param(mode = Parameter.Mode.HIDDEN) String given,
                       @Param(mode = Parameter.Mode.HIDDEN) JBddCallable<T> callable) {
        return parent.given(given, callable);
    }

    @Step("when {when}")
    public <T> T when(@Param(mode = Parameter.Mode.HIDDEN) String when,
                      @Param(mode = Parameter.Mode.HIDDEN) JBddCallable<T> callable) {
        return parent.when(when, callable);
    }

    @Step("then {then}")
    public <T> T then(@Param(mode = Parameter.Mode.HIDDEN) String then,
                      @Param(mode = Parameter.Mode.HIDDEN) JBddCallable<T> callable) {
        return parent.then(then, callable);
    }

    @Step("and {and}")
    public <T> T and(@Param(mode = Parameter.Mode.HIDDEN) String and,
                     @Param(mode = Parameter.Mode.HIDDEN) JBddCallable<T> callable) {
        return parent.and(and, callable);
    }

    public static JBddAllureBaseStepsBuilder builder() {
        return new JBddAllureBaseStepsBuilder();
    }

    public static final class JBddAllureBaseStepsBuilder {
        private JBddSteps<JBddAllureSteps> parent;

        private JBddAllureBaseStepsBuilder() {
        }

        public JBddAllureBaseStepsBuilder withParent(
            JBddSteps<JBddAllureSteps> parent) {
            this.parent = parent;
            return this;
        }

        public JBddAllureSteps build() {

            if (parent == null) {
                return new JBddAllureSteps();
            }
            return new JBddAllureSteps(parent);
        }
    }
}
