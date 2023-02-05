package com.github.awiodev.jbdd.allure;

import com.github.awiodev.jbdd.core.JBddCallable;
import com.github.awiodev.jbdd.core.JBddRunnable;
import com.github.awiodev.jbdd.core.JBddSteps;
import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.qameta.allure.model.Parameter;

public class JBddAllureSteps extends JBddSteps<JBddAllureSteps> {
    @Override
    protected JBddAllureSteps child() {
        return this;
    }

    @Step("given {given}")
    public JBddAllureSteps given(@Param(mode = Parameter.Mode.HIDDEN) String given,
                                 @Param(mode = Parameter.Mode.HIDDEN) JBddRunnable runnable) {
        return super.given(given, runnable);
    }

    @Step("when {when}")
    public JBddAllureSteps when(@Param(mode = Parameter.Mode.HIDDEN) String when,
                                @Param(mode = Parameter.Mode.HIDDEN) JBddRunnable runnable) {
        return super.when(when, runnable);
    }

    @Step("then {then}")
    public JBddAllureSteps then(@Param(mode = Parameter.Mode.HIDDEN) String then,
                                @Param(mode = Parameter.Mode.HIDDEN) JBddRunnable runnable) {
        return super.then(then, runnable);
    }

    @Step("and {then}")
    public JBddAllureSteps and(@Param(mode = Parameter.Mode.HIDDEN) String and,
                               @Param(mode = Parameter.Mode.HIDDEN) JBddRunnable runnable) {
        return super.and(and, runnable);
    }

    @Step("given {given}")
    public <T> T given(@Param(mode = Parameter.Mode.HIDDEN) String given,
                       @Param(mode = Parameter.Mode.HIDDEN) JBddCallable<T> callable) {
        return super.given(given, callable);
    }

    @Step("when {when}")
    public <T> T when(@Param(mode = Parameter.Mode.HIDDEN) String when,
                      @Param(mode = Parameter.Mode.HIDDEN) JBddCallable<T> callable) {
        return super.when(when, callable);
    }

    @Step("then {then}")
    public <T> T then(@Param(mode = Parameter.Mode.HIDDEN) String then,
                      @Param(mode = Parameter.Mode.HIDDEN) JBddCallable<T> callable) {
        return super.then(then, callable);
    }

    @Step("and {and}")
    public <T> T and(@Param(mode = Parameter.Mode.HIDDEN) String and,
                     @Param(mode = Parameter.Mode.HIDDEN) JBddCallable<T> callable) {
        return super.and(and, callable);
    }
}
