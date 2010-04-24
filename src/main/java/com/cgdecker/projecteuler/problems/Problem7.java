package com.cgdecker.projecteuler.problems;

import com.cgdecker.projecteuler.AbstractProblem;

import static com.cgdecker.projecteuler.util.PrimeNumbers.primeNumbers;
import static com.google.common.collect.Iterables.get;


public class Problem7 extends AbstractProblem<Long> {
  public Long solve() {
    return get(primeNumbers(), 10001);
  }
}
