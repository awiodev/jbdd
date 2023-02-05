package com.github.awiodev.jbdd.core;

public class JBddBasicRun extends JBddRun<JBddBasicSteps> {
    public JBddBasicRun(JBddBasicSteps steps) {
        super(steps);
    }

    @Override
    protected void clean() {
    }
}
