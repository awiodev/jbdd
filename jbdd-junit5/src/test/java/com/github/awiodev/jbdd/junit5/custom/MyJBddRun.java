package com.github.awiodev.jbdd.junit5.custom;

import com.github.awiodev.jbdd.core.JBddBaseRun;

public class MyJBddRun extends JBddBaseRun<MyJBddSteps> {

    protected MyJBddRun(MyJBddSteps steps) {
        super(steps);
    }

    public String customRunMethod() {
        return "Hello world";
    }

    @Override
    protected void clean() {
    }
}
