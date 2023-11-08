package io.github.awiodev.jbdd.core;

import io.github.awiodev.jbdd.core.definition.JBddContext;
import io.github.awiodev.jbdd.core.definition.JBddContextFactory;
import io.github.awiodev.jbdd.core.definition.ObjectsDatabase;
import io.github.awiodev.jbdd.core.exceptions.NotFoundException;
import io.github.awiodev.jbdd.core.impl.JBdd;
import io.github.awiodev.jbdd.core.impl.JBddStandardContext;
import io.github.awiodev.jbdd.core.impl.JBddStandardSteps;
import io.github.awiodev.jbdd.core.impl.ObjectsMapDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class JBddStandardContextTest {

    @Nested
    class Store {

        @Test
        void storesObjectByKeyAndValue() {

            HashMap<String, Object> map = new HashMap<>();

            var run = JBdd.builder()
                .withSteps(JBddStandardSteps.builder().build())
                .withContextFactory(() -> JBddStandardContext.builder()
                    .withDatabase(ObjectsMapDatabase.builder()
                        .withMap(map)
                        .build())
                    .build())
                .build();

            String key = "myKey";
            var expected = 123L;

            run.context().store(key, expected);
            Assertions.assertThat(map.keySet().size()).isEqualTo(1);
            String existingKey = new ArrayList<>(map.keySet()).get(0);
            Assertions.assertThat(existingKey).endsWith(key);
            Assertions.assertThat(map.get(existingKey)).isEqualTo(expected);
        }

        @Test
        void storesObjectByType() {
            var expected = new MyTestObject();

            HashMap<String, Object> map = new HashMap<>();

            var run = JBdd.builder()
                .withSteps(JBddStandardSteps.builder().build())
                .withContextFactory(() -> JBddStandardContext.builder()
                    .withDatabase(ObjectsMapDatabase.builder()
                        .withMap(map)
                        .build())
                    .build())
                .build();

            run.context().store(expected);
            Assertions.assertThat(map.keySet().size()).isEqualTo(1);
            String existingKey = new ArrayList<>(map.keySet()).get(0);
            Assertions.assertThat(existingKey).endsWith(MyTestObject.class.getName());
            Assertions.assertThat(map.get(existingKey)).isEqualTo(expected);
        }
    }

    @Nested
    class Get {

        @Test
        void retrievesObjectByKeyAndValue() {
            var run = JBdd.builder().withSteps(JBddStandardSteps.builder().build()).build();
            String key = "myKey";
            var expected = 123L;
            run.context().store(key, expected);
            Assertions.assertThat(run.context().get(key, Long.class)).isEqualTo(expected);
        }

        @Test
        void retrievesObjectByType() {
            var run = JBdd.builder().withSteps(JBddStandardSteps.builder().build()).build();
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
            var run = JBdd.builder().withSteps(JBddStandardSteps.builder().build()).build();
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
