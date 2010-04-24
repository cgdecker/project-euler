package com.cgdecker.projecteuler.util;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;

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
    BigInteger result = BigInteger.valueOf(number);
    if(number == 0 || number == 1)
      return result;

    for(BigInteger value = result.subtract(ONE); value.compareTo(ONE) > 0; value = value.subtract(ONE)) {
      result = result.multiply(value);
      System.out.println(result);
    }

    return result;
  }
}
