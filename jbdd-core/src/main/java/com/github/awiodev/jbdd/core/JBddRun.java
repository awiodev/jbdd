package com.github.awiodev.jbdd.core;

public class JBddRun extends JBddBaseRun<JBddBaseSteps<?>> {
    public JBddRun(JBddBaseSteps<?> steps) {
        super(steps);
    }

    @Override
    protected void clean() {
    }
}
