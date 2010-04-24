package com.cgdecker.projecteuler;


import static com.google.common.util.concurrent.Executors.daemonThreadFactory;

import java.util.Scanner;
import java.util.concurrent.*;

import com.google.common.base.Throwables;


public class ProjectEuler {
  private static final String PROBLEMS_PACKAGE = "com.cgdecker.projecteuler.problems";

  private static final ExecutorService EXECUTOR = Executors
      .newSingleThreadExecutor(daemonThreadFactory());


  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter problem number: ");
    int problemNumber = scanner.nextInt();

    Problem<?> problem = getProblem(problemNumber);
    run(problem);
  }


  private static Problem<?> getProblem(int problemNumber) {
    try {
      Class<?> problemClass = Class.forName(PROBLEMS_PACKAGE + ".Problem" + problemNumber);
      return (Problem<?>) problemClass.newInstance();
    }
    catch (Exception e) {
      throw Throwables.propagate(e);
    }
  }


  public static void run(Problem<?> problem) {
    System.out.println("\nRunning problem " + problem.getId() + "...");
    long start = System.nanoTime();

    Future<Object> future = EXECUTOR.submit(asCallable(problem));

    try {
      Object result = future.get(1, TimeUnit.MINUTES);

      long duration = System.nanoTime() - start;
      System.out.println("   Result: " + result);
      System.out.println("   Took " + TimeUnit.NANOSECONDS.toMillis(duration)
          + "ms to solve.\n");
    }
    catch (TimeoutException e) {
      System.out.println("   Failed to get a result in one minute.");
    }
    catch (Exception e) {
      throw Throwables.propagate(e);
    }
  }


  private static Callable<Object> asCallable(final Problem<?> problem) {
    return new Callable<Object>() {
      public Object call() throws Exception {
        return problem.solve();
      }
    };
  }
}
