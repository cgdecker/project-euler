package com.cgdecker.projecteuler.problems;

import com.cgdecker.projecteuler.util.Numbers;
import com.cgdecker.projecteuler.util.Range;

import static com.cgdecker.projecteuler.util.PrimeNumbers.isPrimeNumber;
import static com.google.common.collect.Iterables.filter;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
@Incomplete("obvious bad solution, need to write pandigital iterator")
public class Problem41 extends AbstractProblem<Long> {
  private static final long LARGEST_POSSIBLE_PANDIGITAL = 987654321L;
  private static final Range POSSIBLE_RANGE = Range.from(LARGEST_POSSIBLE_PANDIGITAL).to(2);
  private static final Iterable<Long> PRIMES_IN_RANGE = filter(POSSIBLE_RANGE, isPrimeNumber());

  public Long solve() {
    for(Long prime : PRIMES_IN_RANGE) {
      System.out.println("Checking " + prime);
      if(Numbers.isPandigital(prime))
        return prime;
    }
    
    throw new AssertionError("a pandigital prime in the range exists");
  }
}
