package com.github.awiodev.jbdd.core;

public class JBddBasicSteps extends JBddSteps<JBddBasicSteps> {
    @Override
    protected JBddBasicSteps child() {
        return this;
    }
}
