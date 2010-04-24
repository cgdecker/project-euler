package com.cgdecker.projecteuler;

import java.util.concurrent.Callable;

/**
 * A Project Euler problem. Calculates an answer to the problem in its {@link #solve()} method.
 *
 * <p>Is {@link Callable} so it can easily be used with {@code Executor}s.
 *
 * @param <T> the type of result for the problem.
 * @author cgdecker@gmail.com (Colin Decker)
 */
public interface Problem<T> extends Callable<T> {
  /**
   * Gets the problem number for this problem.
   *
   * @return the problem number.
   */
  int getId();

  /**
   * Solves the problem, returning an answer to it.
   *
   * @return an answer to the problem.
   */
  T solve();
}
