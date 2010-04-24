package com.cgdecker.projecteuler.util;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public final class Numbers {
  private Numbers() {}

  /**
   * Checks if the given number is pandigital: that is, it contains every number
   * from 1 up to its total number of digits exactly once.
   *
   * @param number the number to check.
   * @return {@code true} if it is pandigital; {@code false} otherwise.
   */
  public static boolean isPandigital(long number) {
    String numberString = String.valueOf(number);
    if(numberString.length() > 9)
      return false;

    for(int i = 1; i <= numberString.length(); i++) {
      if(!numberString.contains(String.valueOf(i)))
        return false;
    }

    return true;
  }
}
