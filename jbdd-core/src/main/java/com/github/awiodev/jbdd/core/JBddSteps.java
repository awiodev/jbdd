package com.github.awiodev.jbdd.core;

public class JBddSteps extends JBddBaseSteps<JBddSteps> {
    @Override
    protected JBddSteps child() {
        return this;
    }
}
