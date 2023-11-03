package com.github.awiodev.jbdd.junit5.custom;

import com.github.awiodev.jbdd.core.definition.JBddRun;
import com.github.awiodev.jbdd.core.impl.JBddStandardContext;

public class MyJBddRun implements JBddRun<MyJBddSteps, JBddStandardContext> {

    private final MyJBddSteps steps;
    private final JBddStandardContext context;

    protected MyJBddRun(MyJBddSteps steps, JBddStandardContext context) {
        this.steps = steps;
        this.context = context;
    }

    public String customRunMethod() {
        return "Hello world";
    }

    @Override
    public MyJBddSteps scenario() {
        return steps;
    }

    @Override
    public JBddStandardContext context() {
        return context;
    }

    @Override
    public void clean() {
    }
}
