package io.github.awiodev.jbdd.junit5.custom;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MyCustomExtension.class)
public class CustomExtensionTest {

    @Test
    void customExtensionIsRegistered(MyJBddRun jBddRun) {
        int stepValue = jBddRun.scenario().myCustomStepMethod();
        Assertions.assertThat(stepValue).isEqualTo(10);
        String runValue = jBddRun.customRunMethod();
        Assertions.assertThat(runValue).isEqualTo("Hello world");
    }
}
