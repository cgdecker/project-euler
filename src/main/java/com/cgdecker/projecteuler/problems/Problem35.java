package com.cgdecker.projecteuler.problems;


import java.util.List;
import java.util.Set;

import com.cgdecker.projecteuler.AbstractProblem;

import static com.cgdecker.projecteuler.util.PrimeNumbers.isPrime;
import static com.cgdecker.projecteuler.util.PrimeNumbers.primeNumbers;
import static com.cgdecker.projecteuler.util.StringConversion.parseLong;
import static com.cgdecker.projecteuler.util.Strings.rotatedPermutations;
import static com.google.common.collect.Lists.transform;
import static com.google.common.collect.Sets.newHashSet;


public class Problem35 extends AbstractProblem<Integer> {
  public Integer solve() {
    Set<Long> matches = newHashSet();
    Set<Long> misses = newHashSet();

    for (Long prime : primeNumbers()) {
      if (prime >= 1000000L)
        break;

      if (!matches.contains(prime) && !misses.contains(prime)) {
        String primeAsString = String.valueOf(prime);
        List<Long> permutations = transform(rotatedPermutations(primeAsString), parseLong());

        boolean allPrimes = true;
        for (Long value : permutations) {
          if (!prime.equals(value) && !isPrime(value)) {
            allPrimes = false;
            misses.addAll(permutations);
            break;
          }
        }

        if (allPrimes) {
          matches.addAll(permutations);
        }
      }
    }

    return matches.size();
  }
}
