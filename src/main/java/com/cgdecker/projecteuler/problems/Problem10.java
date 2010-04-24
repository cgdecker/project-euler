package com.cgdecker.projecteuler.problems;

import com.cgdecker.projecteuler.AbstractProblem;
import com.cgdecker.projecteuler.util.PrimeNumbers;


public class Problem10 extends AbstractProblem<Long> {
  public Long solve() {
    long result = 0;
    for (Long prime : PrimeNumbers.primeNumbers()) {
      if (prime >= 2000000)
        break;

      result += prime;
    }
    return result;
  }
}
