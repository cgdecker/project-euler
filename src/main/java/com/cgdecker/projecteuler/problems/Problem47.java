package com.cgdecker.projecteuler.problems;

import java.util.Collections;
import java.util.Set;

import com.cgdecker.projecteuler.util.PrimeFactors;

import static com.google.common.collect.Sets.newHashSet;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class Problem47 extends AbstractProblem<Long> {
  public Long solve() {
    Set<Long> matches = newHashSet();
    for(long number = 2; matches.size() < 4; number++) {
      PrimeFactors primeFactors = PrimeFactors.of(number);
      if(primeFactors.countDistinct() == 4) {
        matches.add(number);
      } else {
        matches.clear();
      }
    }
    return Collections.min(matches);
  }
}
