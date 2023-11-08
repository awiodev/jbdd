package io.github.awiodev.jbdd.examples.restassured.custom;

import com.github.javafaker.Faker;
import io.github.awiodev.jbdd.core.definition.JBddRun;
import io.github.awiodev.jbdd.core.definition.JBddSteps;
import io.github.awiodev.jbdd.core.definition.Steps;
import io.github.awiodev.jbdd.core.impl.JBddStandardContext;
import io.github.awiodev.jbdd.core.impl.JBddStandardContextFactory;
import io.github.awiodev.jbdd.examples.restassured.steps.FakeUserServiceSteps;
import io.github.awiodev.jbdd.examples.restassured.steps.UserApiSteps;

public class JBddUserApiRun implements JBddRun<JBddSteps<? extends Steps>, JBddStandardContext> {

    private final JBddSteps<?> jBddStandardSteps;
    private final JBddStandardContext jBddStandardContext;
    private final UserApiSteps apiSteps;
    private final FakeUserServiceSteps fakeUserServiceSteps;
    private final Faker faker;

    private JBddUserApiRun(JBddSteps<?> jBddStandardSteps,
                           JBddStandardContextFactory standardContextFactory,
                           UserApiSteps apiSteps, FakeUserServiceSteps fakeUserServiceSteps,
                           Faker faker) {
        this.jBddStandardSteps = jBddStandardSteps;
        this.jBddStandardContext = standardContextFactory.create();
        this.apiSteps = apiSteps;
        this.fakeUserServiceSteps = fakeUserServiceSteps;
        this.faker = faker;
    }

    @Override
    public JBddSteps<?> scenario() {
        return jBddStandardSteps;
    }

    @Override
    public JBddStandardContext context() {
        return jBddStandardContext;
    }

    public UserApiSteps userApiSteps() {
        return apiSteps;
    }

    public Faker faker() {
        return faker;
    }

    @Override
    public void clean() {
        jBddStandardContext.cleanup();
        fakeUserServiceSteps.destroy();
    }

    /**
     * Steps only for the purpose of emulating User service.
     *
     * @return steps that make mocking possible
     */
    public FakeUserServiceSteps fakeUserServiceSteps() {
        return fakeUserServiceSteps;
    }

    public static JBddUserApiRunBuilder builder() {
        return new JBddUserApiRunBuilder();
    }

    public static final class JBddUserApiRunBuilder {
        private JBddSteps<?> jBddStandardSteps;
        private JBddStandardContextFactory jBddStandardContextFactory;
        private UserApiSteps apiSteps;
        private FakeUserServiceSteps fakeUserServiceSteps;
        private Faker faker;

        private JBddUserApiRunBuilder() {
        }

        public JBddUserApiRunBuilder withJBddSteps(JBddSteps<?> jBddStandardSteps) {
            this.jBddStandardSteps = jBddStandardSteps;
            return this;
        }

        public JBddUserApiRunBuilder withJBddStandardContextFactory(
            JBddStandardContextFactory jBddStandardContextFactory) {
            this.jBddStandardContextFactory = jBddStandardContextFactory;
            return this;
        }

        public JBddUserApiRunBuilder withApiSteps(UserApiSteps apiSteps) {
            this.apiSteps = apiSteps;
            return this;
        }

        public JBddUserApiRunBuilder withFakeUserServiceSteps(
            FakeUserServiceSteps fakeUserServiceSteps) {
            this.fakeUserServiceSteps = fakeUserServiceSteps;
            return this;
        }

        public JBddUserApiRunBuilder withFaker(Faker faker) {
            this.faker = faker;
            return this;
        }

        public JBddUserApiRun build() {
            return new JBddUserApiRun(jBddStandardSteps, jBddStandardContextFactory, apiSteps,
                fakeUserServiceSteps, faker);
        }
    }
}
