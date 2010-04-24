package com.cgdecker.projecteuler.problems;

import com.cgdecker.projecteuler.Problem;
import com.cgdecker.projecteuler.util.Maths;
import com.cgdecker.projecteuler.util.PrimeNumbers;


public class Problem10 implements Problem<Long> {
  public int getId() {
    return 10;
  }

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
