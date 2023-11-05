package com.github.awiodev.jbdd.junit5;

import com.github.awiodev.jbdd.core.definition.JBddRun;
import com.github.awiodev.jbdd.core.definition.ObjectsDatabase;
import com.github.awiodev.jbdd.core.impl.JBdd;
import com.github.awiodev.jbdd.core.impl.ObjectsMapDatabase;
import java.util.UUID;
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
     *
     * @param <TRun> as a run type
     */
    @FunctionalInterface
    public interface JBddSetup<TRun extends JBddRun<?, ?>> {
        TRun perform();
    }

    /**
     * Represents unique run tear down after test.
     *
     * @param <TRun> as a run type
     */
    @FunctionalInterface
    public interface JBddTearDown<TRun extends JBddRun<?, ?>> {
        void perform(TRun run);
    }

    private final String sessionId;
    private final ObjectsDatabase runs;
    private final JBddSetup<JBddRun<?, ?>> setup;
    private final JBddTearDown<JBddRun<?, ?>> teardown;

    /**
     * For automatic registration.
     */
    public JBddExtension() {
        sessionId = generateSessionId();
        setup = () -> JBdd.builder().build();
        teardown = JBddRun::clean;
        runs = ObjectsMapDatabase.builder().build();
    }

    /**
     * For manual registration.
     */
    public JBddExtension(ObjectsDatabase runs,
                         JBddSetup<JBddRun<?, ?>> setup,
                         JBddTearDown<JBddRun<?, ?>> teardown) {
        sessionId = generateSessionId();
        this.runs = runs;
        this.setup = setup;
        this.teardown = teardown;
    }

    private String generateSessionId() {
        return UUID.randomUUID().toString();
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
            runs.delete(sessionId, context.getUniqueId());
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
     *
     * @param uniqueId as a run key
     * @return JBdd run object
     */
    private JBddRun<?, ?> setup(String uniqueId) {
        if (!runs.contains(sessionId, uniqueId)) {
            JBddRun<?, ?> run = setup.perform();
            runs.save(sessionId, uniqueId, run);
        }
        return runs.get(sessionId, uniqueId, JBddRun.class);
    }

    public static JBddExtensionBuilder builder() {
        return new JBddExtensionBuilder();
    }

    public static final class JBddExtensionBuilder {
        private JBddSetup<JBddRun<?, ?>> setup;
        private JBddTearDown<JBddRun<?, ?>> teardown;

        private ObjectsDatabase runs;

        private JBddExtensionBuilder() {
        }

        public JBddExtensionBuilder withSetupAndTearDown(JBddSetup<JBddRun<?, ?>> setup,
                                                         JBddTearDown<JBddRun<?, ?>> teardown) {
            this.setup = setup;
            this.teardown = teardown;
            return this;
        }

        public JBddExtensionBuilder withObjectsDatabase(ObjectsDatabase objectsDatabase) {
            this.runs = objectsDatabase;
            return this;
        }

        public JBddExtension build() {

            if (setup == null && teardown == null) {
                return new JBddExtension();
            }

            if (runs == null) {
                runs = ObjectsMapDatabase.builder().build();
            }

            return new JBddExtension(runs, setup, teardown);
        }
    }
}
