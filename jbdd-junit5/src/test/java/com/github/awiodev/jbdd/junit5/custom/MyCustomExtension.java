package com.github.awiodev.jbdd.junit5.custom;

import com.github.awiodev.jbdd.core.definition.JBddRun;
import com.github.awiodev.jbdd.core.impl.JBddStandardContextProvider;
import com.github.awiodev.jbdd.junit5.JBddExtension;

public class MyCustomExtension extends JBddExtension {

    public MyCustomExtension() {
        super(() -> new MyJBddRun(MyJBddSteps.builder().build(),
            new JBddStandardContextProvider().provide()), JBddRun::clean);
    }
}
