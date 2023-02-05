package com.github.awiodev.jbdd.allure;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class JBddAllureRunTest {

    @Nested
    class WithJBddAllureRun {
        @Test
        void itIsPossibleToChainSteps() {
            var run = new JBddAllureRun(new JBddAllureSteps());

            run.steps().given("My run just started", () -> {
                // No step implementation required
            }).and("I know steps order", () -> {
                // No step implementation required
            }).when("I create scenario", () -> {
                // No step implementation required
            }).then("My steps are chained", () -> {
                // No step implementation required
            });
        }
    }
}
