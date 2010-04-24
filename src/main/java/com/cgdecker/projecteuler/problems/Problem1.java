package com.cgdecker.projecteuler.problems;


public class Problem1 extends AbstractProblem<Integer> {
  public Integer solve() {
    int result = 0;
    for (int i = 0; i < 1000; i++) {
      if (i % 3 == 0 || i % 5 == 0) {
        result += i;
      }
    }
    return result;
  }
}
