package com.cgdecker.projecteuler;

import java.io.PrintStream;
import java.util.Scanner;

import com.google.inject.Inject;

/**
 * The Project Euler application runner. Gets user input and gets and runs
 * problems based on that input.
 *
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class Application {
  private final PrintStream output;
  private final Scanner scanner;
  private final ProblemFactory problemFactory;
  private final ProblemRunner problemRunner;

  @Inject public Application(PrintStream output, Scanner scanner,
                     ProblemFactory problemFactory,
                     ProblemRunner problemRunner) {
    this.output = output;
    this.scanner = scanner;
    this.problemFactory = problemFactory;
    this.problemRunner = problemRunner;
  }

  public void start() {
    output.print("Enter problem number: ");
    int problemNumber = scanner.nextInt();

    Problem<?> problem = problemFactory.getProblem(problemNumber);
    problemRunner.run(problem);
  }
}
