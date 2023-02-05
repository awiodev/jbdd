package com.github.awiodev.jbdd.junit5;

import com.github.awiodev.jbdd.core.JBddBasicRun;
import com.github.awiodev.jbdd.core.JBddBasicSteps;
import com.github.awiodev.jbdd.core.JBddRun;

public class JBddBasicExtension extends JBddExtension<JBddBasicRun> {

    private final JBddBasicSteps steps;

    public JBddBasicExtension() {
        steps = new JBddBasicSteps();
    }

    @Override
    protected JBddSetup<JBddBasicRun> setup() {
        return () -> new JBddBasicRun(steps);
    }

    @Override
    protected JBddTearDown<JBddBasicRun> teardown() {
        return JBddRun::cleanup;
    }
}
