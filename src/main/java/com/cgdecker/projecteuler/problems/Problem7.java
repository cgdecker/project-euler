package com.cgdecker.projecteuler.problems;

import com.cgdecker.projecteuler.Problem;
import com.cgdecker.projecteuler.util.Maths;
import com.google.common.collect.Iterables;


public class Problem7 implements Problem<Long> {
  public int getId() {
    return 7;
  }


  public Long solve() {
    return Iterables.get(Maths.primeNumbers(), 10001);
  }
}
