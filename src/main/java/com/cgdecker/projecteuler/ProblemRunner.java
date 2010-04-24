package com.cgdecker.projecteuler;

import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.cgdecker.projecteuler.util.time.Stopwatch;
import com.google.common.base.Throwables;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Runs problems on a separate thread, waiting up to one minute for them to complete.
 * Prints information on its running and the result to a PrintStream.
 *
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class ProblemRunner {
  private final ExecutorService executor;
  private final Provider<Stopwatch> stopwatchProvider;
  private final PrintStream output;

  @Inject public ProblemRunner(ExecutorService executor, Provider<Stopwatch> stopwatchProvider,
                               PrintStream output) {
    this.executor = executor;
    this.stopwatchProvider = stopwatchProvider;
    this.output = output;
  }

  public void run(Problem<?> problem) {
    output.println();
    output.println("Running problem " + problem.getId() + "...");
    Stopwatch stopwatch = stopwatchProvider.get();

    stopwatch.start();
    Future<?> resultFuture = executor.submit(problem);

    try {
      Object result = resultFuture.get(1, TimeUnit.MINUTES);
      stopwatch.stop();

      output.println("   Result: " + result);
      output.println("   Took " + stopwatch.getElapsedTimeMillis() + "ms to solve.\n");
    }
    catch (TimeoutException e) {
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
