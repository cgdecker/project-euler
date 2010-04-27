package com.cgdecker.projecteuler;

import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import com.cgdecker.projecteuler.util.time.Stopwatch;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.TimeLimiter;
import com.google.common.util.concurrent.UncheckedTimeoutException;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Runs problems on a separate thread, waiting up to one minute for them to complete.
 * Prints information on its running and the result to a PrintStream.
 *
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class ProblemRunner {
  private final TimeLimiter timeLimiter;
  private final Provider<Stopwatch> stopwatchProvider;
  private final PrintStream output;

  @Inject public ProblemRunner(TimeLimiter timeLimiter, Provider<Stopwatch> stopwatchProvider,
                               PrintStream output) {
    this.timeLimiter = timeLimiter;
    this.stopwatchProvider = stopwatchProvider;
    this.output = output;
  }

  public void run(Problem<?> problem) {
    output.println();
    output.println("Running problem " + problem.getId() + "...");
    Stopwatch stopwatch = stopwatchProvider.get();

    Problem<?> timeLimitedProblem = timeLimiter.newProxy(problem, Problem.class, 1,
        TimeUnit.MINUTES);
    stopwatch.start();
    try {
      Object result = timeLimitedProblem.solve();
      stopwatch.stop();

      output.println("   Result: " + result);
      output.println("   Took " + stopwatch.getElapsedTimeMillis() + "ms to solve.\n");
    } catch (UncheckedTimeoutException e) {
      output.println("   Failed to get a result in one minute.");
    }
    catch (Exception e) {
      throw Throwables.propagate(e);
    }
    finally {
      output.println();
    }
  }
}
