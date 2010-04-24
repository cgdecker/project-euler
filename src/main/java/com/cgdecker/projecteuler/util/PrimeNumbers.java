package com.cgdecker.projecteuler.util;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;

import static com.google.common.collect.Iterables.filter;
import static java.lang.Math.floor;
import static java.lang.Math.sqrt;

/**
 * Methods related to prime numbers.
 *
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class PrimeNumbers {

  private PrimeNumbers() {}

  private static final ImmutableSet<Long> FIRST_PRIME_NUMBERS = ImmutableSet.of(2l, 3l, 5l, 7l,
      11l, 13l, 17l, 19l, 23l, 29l, 31l, 37l, 41l, 43l, 47l, 53l, 59l, 61l, 67l, 71l, 73l, 79l,
      83l, 89l, 97l);

  private static final Iterable<Long> PRIME_NUMBER_SEQUENCE = filter(Range.from(2).to(
      Long.MAX_VALUE), isPrimeNumber());

  private enum IsPrimeNumber implements Predicate<Long> {
    INSTANCE;

    public boolean apply(Long input) {
      return isPrime(input);
    }
  }


  /**
   * Determines whether or not the given number is prime.
   *
   * @param number the number.
   * @return {@code true} if it is prime; {@code false} otherwise.
   */
  public static boolean isPrime(long number) {
    if (number < 100L) {
      return FIRST_PRIME_NUMBERS.contains(number);
    }

    if (number % 2 == 0 || number % 3 == 0)
      return false;

    long squareRoot = (long) floor(sqrt(number));

    for (long k = 1; 6 * k - 1 <= squareRoot; k++) {
      long lowerPrime = 6l * k - 1l;
      long higherPrime = 6l * k + 1l;

      if (number % lowerPrime == 0)
        return false;

      if (number % higherPrime == 0)
        return false;
    }

    return true;
  }


  /**
   * The sequence of prime numbers, starting at 2. Will effectively iterate forever (until it reaches {@code
   * Long.MAX_VALUE}) and will take longer as the numbers get higher, so you should be sure to terminate it yourself
   * somehow.
   *
   * @return the sequence of prime numbers.
   */
  public static Iterable<Long> primeNumbers() {
    return PRIME_NUMBER_SEQUENCE;
  }


  /**
   * Gets a predicate that determines whether a number is prime.
   *
   * @return the prime number predicate.
   */
  public static Predicate<Long> isPrimeNumber() {
    return IsPrimeNumber.INSTANCE;
  }
}
