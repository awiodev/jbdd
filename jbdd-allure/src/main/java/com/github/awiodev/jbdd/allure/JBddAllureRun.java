package com.github.awiodev.jbdd.allure;

import com.github.awiodev.jbdd.core.JBddRun;

public class JBddAllureRun extends JBddRun<JBddAllureSteps> {
    public JBddAllureRun(JBddAllureSteps allureSteps) {
        super(allureSteps);
    }

    @Override
    protected void clean() {
    }
}
