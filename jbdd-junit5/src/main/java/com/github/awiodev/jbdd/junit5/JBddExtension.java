package com.github.awiodev.jbdd.junit5;

import com.github.awiodev.jbdd.core.definition.JBddRun;
import com.github.awiodev.jbdd.core.impl.JBdd;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 * Junit5 extension that enables injection of JBddRun into test methods.
 * Both automatic and manual extension registrations are supported.
 */
public class JBddExtension implements BeforeEachCallback,
    AfterEachCallback, BeforeAllCallback, AfterAllCallback, ParameterResolver {

    /**
     * Represents unique run setup.
     * @param <TRun> as a run type
     */
    @FunctionalInterface
    public interface JBddSetup<TRun extends JBddRun<?, ?>> {
        TRun perform();
    }

    /**
     * Represents unique run tear down after test.
     * @param <TRun> as a run type
     */
    @FunctionalInterface
    public interface JBddTearDown<TRun extends JBddRun<?, ?>> {
        void perform(TRun run);
    }

    private final Map<String, JBddRun<?, ?>> runs = new ConcurrentHashMap<>();
    private final JBddSetup<JBddRun<?, ?>> setup;
    private final JBddTearDown<JBddRun<?, ?>> teardown;

    /**
     * For automatic registration.
     */
    public JBddExtension() {
        setup = () -> JBdd.builder().build();
        teardown = JBddRun::clean;
    }

    /**
     * For manual registration.
     */
    public JBddExtension(JBddSetup<JBddRun<?, ?>> setup,
                         JBddTearDown<JBddRun<?, ?>> teardown) {
        this.setup = setup;
        this.teardown = teardown;
    }

    @Override
    public void afterAll(ExtensionContext context) {
    }

    @Override
    public void beforeAll(ExtensionContext context) {
    }

    @Override
    public void afterEach(ExtensionContext context) {
        try {
            JBddRun<?, ?> run = setup(context.getUniqueId());
            teardown.perform(run);
        } catch (Exception e) {
            // ignore
        } finally {
            runs.remove(context.getUniqueId());
        }
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        setup(context.getUniqueId());
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext)
        throws ParameterResolutionException {

        return JBddRun.class.isAssignableFrom(parameterContext.getParameter().getType());
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext)
        throws ParameterResolutionException {
        return setup(extensionContext.getUniqueId());
    }

    /**
     * Sets up unique run or returns exestring run
     * @param uniqueId as a run key
     * @return JBdd run object
     */
    private JBddRun<?, ?> setup(String uniqueId) {
        if (!runs.containsKey(uniqueId)) {
            JBddRun<?, ?> run = setup.perform();
            runs.put(uniqueId, run);
        }
        return runs.get(uniqueId);
    }

    public static JBddExtensionBuilder builder() {
        return new JBddExtensionBuilder();
    }

    public static final class JBddExtensionBuilder {
        private JBddSetup<JBddRun<?, ?>> setup;
        private JBddTearDown<JBddRun<?, ?>> teardown;

        private JBddExtensionBuilder() {
        }

        public JBddExtensionBuilder withSetupAndTearDown(JBddSetup<JBddRun<?, ?>> setup,
                                                         JBddTearDown<JBddRun<?, ?>> teardown) {
            this.setup = setup;
            this.teardown = teardown;
            return this;
        }

        public JBddExtension build() {

            if (setup == null && teardown == null) {
                return new JBddExtension();
            }

            return new JBddExtension(setup, teardown);
        }
    }
}
