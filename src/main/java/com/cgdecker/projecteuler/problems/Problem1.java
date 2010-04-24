package com.cgdecker.projecteuler.problems;


import com.cgdecker.projecteuler.Problem;


public class Problem1 implements Problem<Integer> {
  public int getId() {
    return 1;
  }

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
