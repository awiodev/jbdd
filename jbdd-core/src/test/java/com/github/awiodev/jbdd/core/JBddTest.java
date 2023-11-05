package com.github.awiodev.jbdd.core;

import com.github.awiodev.jbdd.core.exceptions.NotFoundException;
import com.github.awiodev.jbdd.core.impl.JBdd;
import com.github.awiodev.jbdd.core.impl.JBddStandardSteps;
import java.util.NoSuchElementException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class JBddTest {

    private final JBddStandardSteps steps = JBddStandardSteps.builder().build();

    @Nested
    class Steps {

        @Test
        void returnsStepsObjectWhenCalled() {
            var run = JBdd.builder().withSteps(steps).build();
            Assertions.assertThat(run.scenario()).isEqualTo(steps);
        }
    }

    @Nested
    class StoreAndGet {

        @Test
        void storesAndRetrievesObjectByKeyAndValue() {
            var run = JBdd.builder().withSteps(steps).build();
            String key = "myKey";
            var expected = 123L;
            run.context().store(key, expected);
            Assertions.assertThat(run.context().get(key, Long.class)).isEqualTo(expected);
        }

        @Test
        void storesAndRetrievesObjectByType() {
            var run = JBdd.builder().withSteps(steps).build();
            var expected = new MyTestObject();
            run.context().store(expected);
            MyTestObject actual = run.context().get(MyTestObject.class);
            Assertions.assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class Cleanup {

        @Test
        void removesItemsFromContext() {
            var run = JBdd.builder().withSteps(steps).build();
            var object = new MyTestObject();
            run.context().store(object);
            run.context().cleanup();
            Assertions.assertThatThrownBy(() ->
                run.context().get(MyTestObject.class)).isInstanceOf(NotFoundException.class);
        }
    }

    private static class MyTestObject {
        private final String hello = "world";
    }
}
