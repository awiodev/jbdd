package com.github.awiodev.jbdd.junit5;

import com.github.awiodev.jbdd.core.JBddRun;
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

public abstract class JBddExtension<TRun extends JBddRun<?>> implements BeforeEachCallback,
    AfterEachCallback, BeforeAllCallback, AfterAllCallback, ParameterResolver {

    @FunctionalInterface
    public interface JBddSetup<TRun extends JBddRun<?>> {
        TRun perform();
    }

    @FunctionalInterface
    public interface JBddTearDown<TRun extends JBddRun<?>> {
        void perform(TRun run);
    }

    private final Map<String, TRun> runs = new ConcurrentHashMap<>();

    protected abstract JBddSetup<TRun> setup();

    protected abstract JBddTearDown<TRun> teardown();

    @Override
    public void afterAll(ExtensionContext context) {
    }

    @Override
    public void beforeAll(ExtensionContext context) {
    }

    @Override
    public void afterEach(ExtensionContext context) {
        TRun run = setup(context.getUniqueId());
        teardown().perform(run);
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

    private TRun setup(String uniqueId) {
        if (!runs.containsKey(uniqueId)) {
            TRun run = setup().perform();
            runs.put(uniqueId, run);
        }
        return runs.get(uniqueId);
    }
}
