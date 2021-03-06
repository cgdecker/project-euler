package com.cgdecker.projecteuler.util;


import java.util.Collections;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multiset.Entry;

import static com.google.common.base.Preconditions.checkArgument;


/**
 * Prime factors. Allows the prime factors of a number to be
 * determined using {@code PrimeFactors factors = PrimeFactors.of(number);}
 * as well as representing the results.
 *
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class PrimeFactors {
   /**
   * Finds the prime factors of the given number.
   *
   * @param number the number.
   * @return a list of its prime factors.
   */
  public static PrimeFactors of(int number) {
    return PrimeFactors.of((long) number);
  }


  /**
   * Finds the prime factors of the given number.
   *
   * @param number the number.
   * @return a list of its prime factors.
   */
  public static PrimeFactors of(long number) {
    checkArgument(number > 1, "only works for values > 1 (was %s)", number);

    if (PrimeNumbers.isPrime(number))
      return new PrimeFactors(ImmutableMultiset.of(number));

    ImmutableMultiset.Builder<Long> primeFactors = ImmutableMultiset.builder();
    long currentNumber = number;
    for (Long prime : PrimeNumbers.primeNumbers()) {
      while (currentNumber % prime == 0) {
        primeFactors.add(prime);
        currentNumber = currentNumber / prime;
      }

      if (currentNumber == 1) {
        return new PrimeFactors(primeFactors.build());
      }

      if (PrimeNumbers.isPrime(currentNumber)) {
        primeFactors.add(currentNumber);
        return new PrimeFactors(primeFactors.build());
      }
    }

    throw new AssertionError();
  }

  private final ImmutableMultiset<Long> factors;

  private PrimeFactors(Multiset<Long> factors) {
    this.factors = ImmutableMultiset.copyOf(factors);
  }

  /**
   * Gets the numeric value this is the prime factors of.
   *
   * @return the original value.
   */
  public long getOriginalValue() {
    long result = 1;
    for (Long factor : factors) {
      result *= factor;
    }
    return result;
  }

  /**
   * Gets the factors as an immutable multiset.
   *
   * @return the factors.
   */
  public ImmutableMultiset<Long> getFactors() {
    return factors;
  }

  /**
   * Gets the count of the distinct prime factors. So for
   * 2^2 x 3^2 x 5 this would be 3, despite there being 5
   * total prime factors.
   *
   * @return count of the distinct factors.
   */
  public int countDistinct() {
    return factors.entrySet().size();
  }

  /**
   * Gets the count of the total prime factors. For
   * 2^2 x 3^2 x 5 this would be 5.
   *
   * @return count of the total factors.
   */
  public int count() {
    return factors.size();
  }

  /**
   * Gets the greatest of the factors.
   *
   * @return the greatest factor.
   */
  public Long greatest() {
    return Collections.max(factors);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof PrimeFactors) {
      return Objects.equal(((PrimeFactors) obj).factors, factors);
    }

    return false;
  }

  @Override
  public int hashCode() {
    return factors.hashCode();
  }

  @Override
  public String toString() {
    List<String> strings = Lists.newArrayList();
    for (Entry<Long> factor : factors.entrySet()) {
      String element = String.valueOf(factor.getElement());
      if (factor.getCount() == 1)
        strings.add(element);
      else
        strings.add(element + "^" + factor.getCount());
    }
    return Joiner.on(" x ").join(strings);
  }
}
