package com.github.awiodev.jbdd.junit5.custom;

import com.github.awiodev.jbdd.core.JBddBaseRun;
import com.github.awiodev.jbdd.core.JBddRun;
import com.github.awiodev.jbdd.core.JBddSteps;
import com.github.awiodev.jbdd.junit5.JBddBaseExtension;

public class MyCustomExtension extends JBddBaseExtension<MyJBddRun> {

    private JBddSetup<MyJBddRun> setup;
    private JBddTearDown<MyJBddRun> teardown;

    public MyCustomExtension() {
        var steps = new MyJBddSteps();
        setup = () -> new MyJBddRun(steps);
        teardown = JBddBaseRun::cleanup;
    }

    @Override
    protected JBddSetup<MyJBddRun> setup() {
        return setup;
    }

    @Override
    protected JBddTearDown<MyJBddRun> teardown() {
        return teardown;
    }
}
