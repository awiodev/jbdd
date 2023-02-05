package com.github.awiodev.jbdd.junit5;

import com.github.awiodev.jbdd.core.JBddRun;
import com.github.awiodev.jbdd.core.JBddSteps;
import com.github.awiodev.jbdd.core.JBddBaseRun;
import com.github.awiodev.jbdd.core.JBddBaseSteps;

public class JBddExtension extends JBddBaseExtension<JBddRun> {

    private final JBddBaseSteps<?> steps;
    private final JBddSetup<JBddRun> setup;
    private final JBddTearDown<JBddRun> teardown;

    public JBddExtension() {
        steps = new JBddSteps();
        setup = () -> new JBddRun(steps);
        teardown = JBddBaseRun::cleanup;
    }

    public JBddExtension(JBddBaseSteps<?> steps, JBddSetup<JBddRun> setup,
                         JBddTearDown<JBddRun> teardown) {
        this.steps = steps;
        this.setup = setup;
        this.teardown = teardown;
    }

    @Override
    protected JBddSetup<JBddRun> setup() {
        return setup;
    }

    @Override
    protected JBddTearDown<JBddRun> teardown() {
        return teardown;
    }

    public static JBddDefaultExtensionBuilder builder() {
        return new JBddDefaultExtensionBuilder();
    }

    public static final class JBddDefaultExtensionBuilder {
        private JBddBaseSteps<?> steps;
        private JBddSetup<JBddRun> setup;
        private JBddTearDown<JBddRun> teardown;

        private JBddDefaultExtensionBuilder() {
        }

        public JBddDefaultExtensionBuilder withSteps(JBddBaseSteps<?> steps) {
            this.steps = steps;
            return this;
        }

        public JBddDefaultExtensionBuilder withSetup(JBddSetup<JBddRun> setup) {
            this.setup = setup;
            return this;
        }

        public JBddDefaultExtensionBuilder withTeardown(JBddTearDown<JBddRun> teardown) {
            this.teardown = teardown;
            return this;
        }

        public JBddExtension build() {
            return new JBddExtension(steps, setup, teardown);
        }
    }
}
