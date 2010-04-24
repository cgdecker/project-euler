package com.cgdecker.projecteuler.problems;

import java.util.concurrent.Callable;

import com.cgdecker.projecteuler.Problem;

/**
 * Abstract problem that gets its problem # from its name and implements {@link Callable}.
 * Class name of implementations <i>must</i> be of the form "Problem#" where # is the
 * problem number. 
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

  public final int getId() {
    return id;
  }

  public final T call() throws Exception {
    return solve();
  }
}
