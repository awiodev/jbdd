package com.github.awiodev.jbdd.junit5;

import com.github.awiodev.jbdd.allure.JBddAllureRun;
import com.github.awiodev.jbdd.allure.JBddAllureSteps;
import com.github.awiodev.jbdd.core.JBddRun;

public class JBddAllureExtension extends JBddExtension<JBddAllureRun> {

    private JBddAllureSteps allureSteps;

    public JBddAllureExtension() {
        allureSteps = new JBddAllureSteps();
    }

    @Override
    protected JBddSetup<JBddAllureRun> setup() {
        return () -> new JBddAllureRun(allureSteps);
    }

    @Override
    protected JBddTearDown<JBddAllureRun> teardown() {
        return JBddRun::cleanup;
    }
}
