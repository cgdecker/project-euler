package com.cgdecker.projecteuler.problems;


import java.util.Set;

import com.cgdecker.projecteuler.AbstractProblem;
import com.google.common.collect.ImmutableSet;

import static com.cgdecker.projecteuler.util.PrimeNumbers.isPrime;
import static com.cgdecker.projecteuler.util.PrimeNumbers.primeNumbers;
import static com.cgdecker.projecteuler.util.StringConversion.parseLong;
import static com.cgdecker.projecteuler.util.Strings.leftAndRightTruncatedPermutations;
import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Sets.newHashSet;


public class Problem37 extends AbstractProblem<Long> {
  public Long solve() {
    Set<Long> matches = newHashSet();
    Set<Long> misses = newHashSet();

    for (Long prime : primeNumbers()) {
      if (!matches.contains(prime) && !misses.contains(prime)) {
        String primeAsString = String.valueOf(prime);
        ImmutableSet<Long> permutations = ImmutableSet.copyOf(transform(
            leftAndRightTruncatedPermutations(primeAsString), parseLong()));

        boolean allPrimes = true;
        for (Long value : permutations) {
          if (!prime.equals(value) && !isPrime(value)) {
            allPrimes = false;
            misses.addAll(permutations);
            break;
          }
        }

        if (allPrimes && prime > 7) {
          matches.add(prime);
        }
      }

      if (matches.size() == 11)
        break;
    }

    long result = 0;
    for (Long match : matches) {
      result += match;
    }
    return result;
  }
}
