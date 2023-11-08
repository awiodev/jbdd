package io.github.awiodev.jbdd.core;

import io.github.awiodev.jbdd.core.impl.JBddStandardContext;
import io.github.awiodev.jbdd.core.impl.JBddStandardContextFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class JBddStandardContextFactoryTest {

    @Test
    void contextFactoryCreatesStandardContextInstance() {
        JBddStandardContextFactory factory = JBddStandardContextFactory.builder().build();
        JBddStandardContext context = factory.create();
        Assertions.assertThat(context).isNotNull();
    }
}
