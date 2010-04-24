package com.cgdecker.projecteuler;

import java.util.concurrent.Callable;

/**
 * Abstract problem that gets its problem # from its name and implements {@link Callable}.
 *
 * @author cgdecker@gmail.com (Colin Decker)
 */
public abstract class AbstractProblem<T> implements Problem<T> {
  private final int id;

  public AbstractProblem() {
    this.id = getIdFromName();
  }

  private int getIdFromName() {
    String className = getClass().getSimpleName();
    String number = className.replace("Problem", "");
    return Integer.valueOf(number);
  }

  public int getId() {
    return id;
  }

  public final T call() throws Exception {
    return solve();
  }
}
