package io.github.awiodev.jbdd.core.definition;

/**
 * Functional interface for a procedure to be executed inside step.
 */
@FunctionalInterface
public interface JBddRunnable {

  /**
   * Executes a procedure.
   */
  void perform();
}
