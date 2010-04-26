package com.cgdecker.projecteuler.util;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

/**
 * Does factorials.
 *
 * @author cgdecker@gmail.com (Colin Decker)
 */
public final class Factorial {
  private Factorial() {}

  /**
   * Gets the factorial of the given number.
   *
   * @param number the number.
   * @return the factorial of the number.
   */
  public static BigInteger of(long number) {
    return Factorial.of(BigInteger.valueOf(number));
  }

  /**
   * Gets the factorial of the given number.
   *
   * @param number the number.
   * @return the factorial of the number.
   */
  public static BigInteger of(BigInteger number) {
    if(number.equals(ZERO) || number.equals(ONE))
      return number;

    BigInteger result = number;
    for(BigInteger value = result.subtract(ONE); value.compareTo(ONE) > 0; value = value.subtract(ONE)) {
      result = result.multiply(value);
      System.out.println(result);
    }

    return result;
  }
}
