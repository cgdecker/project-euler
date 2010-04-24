package com.cgdecker.projecteuler.problems;

import com.cgdecker.projecteuler.AbstractProblem;
import com.cgdecker.projecteuler.util.PrimeFactors;


public class Problem3 extends AbstractProblem<Long> {
  private static final long PROBLEM_NUMBER = 600851475143L;

  public Long solve() {
    return PrimeFactors.of(PROBLEM_NUMBER).greatest();
  }
}
