package com.github.awiodev.jbdd.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class JBddRunTest {

    private final JBddSteps steps = new JBddSteps();

    @Nested
    class Steps {

        @Test
        void returnsStepsObjectWhenCalled() {
            var run = new JBddRun(steps);
            Assertions.assertThat(run.scenario()).isEqualTo(steps);
        }
    }

    @Nested
    class StoreAndGet {

        @Test
        void storesAndRetrievesObjectByKeyAndValue() {
            var run = new JBddRun(steps);
            String key = "myKey";
            var expected = 123L;
            run.store(key, expected);
            Assertions.assertThat(run.get(key, Long.class)).isEqualTo(expected);
        }

        @Test
        void storesAndRetrievesObjectByType() {
            var run = new JBddRun(steps);
            var expected = new MyTestObject();
            run.store(expected);
            MyTestObject actual = run.get(MyTestObject.class);
            Assertions.assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class Cleanup {

        @Test
        void removesItemsFromContext() {
            var run = new JBddRun(steps);
            var object = new MyTestObject();
            run.store(object);
            run.cleanup();
            Assertions.assertThatThrownBy(() ->
                    run.get(MyTestObject.class)).isInstanceOf(NoSuchElementException.class);
        }
    }

    private static class MyTestObject {
        private final String hello = "world";
    }
}
