package io.github.awiodev.jbdd.junit5.custom;

import io.github.awiodev.jbdd.core.definition.JBddRun;
import io.github.awiodev.jbdd.core.impl.JBddStandardContextFactory;
import io.github.awiodev.jbdd.core.impl.ObjectsMapDatabase;
import io.github.awiodev.jbdd.junit5.JBddExtension;

public class MyCustomExtension extends JBddExtension {

    public MyCustomExtension() {
        super(ObjectsMapDatabase.builder().build(),
            () -> new MyJBddRun(MyJBddSteps.builder().build(),
                JBddStandardContextFactory.builder().build().create()), JBddRun::clean);
    }
}
