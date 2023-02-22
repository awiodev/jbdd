package com.github.awiodev.jbdd.junit5.custom;

import com.github.awiodev.jbdd.core.JBddBaseSteps;

public class MyJBddSteps extends JBddBaseSteps<MyJBddSteps> {

    @Override
    protected MyJBddSteps child() {
        return this;
    }

    public int myCustomStepMethod() {
        return 10;
    }
}
