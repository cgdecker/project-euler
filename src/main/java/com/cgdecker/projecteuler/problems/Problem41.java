package com.cgdecker.projecteuler.problems;

import static com.cgdecker.projecteuler.util.Numbers.pandigitalNumbers;
import static com.cgdecker.projecteuler.util.PrimeNumbers.isPrime;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class Problem41 extends AbstractProblem<Long> {
  public Long solve() {
    for (Long pandigital : pandigitalNumbers()) {
      if (isPrime(pandigital))
        return pandigital;
    }
    
    throw new AssertionError("a pandigital prime in the range exists");
  }
}
