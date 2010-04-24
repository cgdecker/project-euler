package com.cgdecker.projecteuler.problems;

import java.math.BigInteger;

import com.cgdecker.projecteuler.util.Factorial;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class Problem20 extends AbstractProblem<Long> {
  private static final long PROBLEM_NUMBER = 100L;

  public Long solve() {
    BigInteger factorial = Factorial.of(PROBLEM_NUMBER);
    String factorialString = factorial.toString();

    System.out.println(factorialString);
    long result = 0;
    for(char character : factorialString.toCharArray()) {
      int digit = Character.digit(character, 10);
      result += digit;
    }

    return result;
  }
}
