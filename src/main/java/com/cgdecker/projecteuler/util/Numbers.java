package com.cgdecker.projecteuler.util;

import static com.google.common.collect.Lists.newArrayList;

import java.util.*;

import com.google.common.base.Joiner;
import com.google.common.collect.*;

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

  /**
   * Gets an iterable that starts at the number 987654321 and iterates through
   * all pandigital numbers all the way to down to 1. No numbers that are not
   * pandigital will be included.
   * 
   * @return a reverse-order pandigital numbers iterable.
   */
  public static Iterable<Long> pandigitalNumbers() {
    return new Iterable<Long>() {
      public Iterator<Long> iterator() {
        return new PandigitalIterator();
      }
    };
  }

  private static class PandigitalIterator extends AbstractIterator<Long> {
    private static final ImmutableList<Integer> START = ImmutableList.of(9, 8, 7, 6, 5, 4, 3, 2, 1);

    private OrderedDigits fullNumber = null;
    private OrderedDigits scoped = null;

    @Override protected Long computeNext() {
      if (fullNumber == null) {
        fullNumber = new OrderedDigits(newArrayList(START));
        scoped = fullNumber.lowestDigits(fullNumber.size());
        return fullNumber.toLong();
      }

      while (scoped.size() > 2 && !scoped.isAscendingOrder()) {
        scoped = scoped.withReducedScope();
      }

      if (!scoped.isAscendingOrder()) {
        scoped.swapHighestAndNextHighest();
        return fullNumber.toLong();
      }

      do {
        if (scoped.size() + 1 > fullNumber.size()) {
          fullNumber = fullNumber.withoutLastDigit();
          fullNumber.orderDescending();
          scoped = fullNumber.lowestDigits(fullNumber.size());

          if (fullNumber.size() == 0)
            return endOfData();

          return fullNumber.toLong();
        }

        scoped = fullNumber.lowestDigits(scoped.size() + 1);
      } while (scoped.isAscendingOrder());

      scoped.swapFirstWithNextLowerDigit();
      scoped = scoped.withReducedScope();
      scoped.orderDescending();

      return fullNumber.toLong();
    }
  }

  private static class OrderedDigits {
    private static final Joiner JOINER = Joiner.on("");
    private static final Ordering<Integer> ASCENDING_ORDER = Ordering.natural();
    private static final Ordering<Integer> DESCENDING_ORDER = ASCENDING_ORDER.reverse();
    
    private final List<Integer> digits;

    public OrderedDigits(List<Integer> digits) {
      this.digits = digits;
    }

    public OrderedDigits lowestDigits(int numDigits) {
      return new OrderedDigits(digits.subList(digits.size() - numDigits, digits.size()));
    }

    public OrderedDigits withReducedScope() {
      return new OrderedDigits(digits.subList(1, digits.size()));
    }

    public OrderedDigits withoutLastDigit() {
      return new OrderedDigits(newArrayList(digits.subList(0, digits.size() - 1)));
    }

    public int size() {
      return digits.size();
    }

    public boolean isAscendingOrder() {
      return ASCENDING_ORDER.isOrdered(digits);
    }

    public void orderDescending() {
      Collections.sort(digits, DESCENDING_ORDER);
    }

    public void swapHighestAndNextHighest() {
      List<Integer> sorted = DESCENDING_ORDER.sortedCopy(digits);
      Integer highest = sorted.get(0);
      swapDigitAndNextLowerDigit(highest, sorted);
    }

    public void swapFirstWithNextLowerDigit() {
      swapDigitAndNextLowerDigit(digits.get(0), DESCENDING_ORDER.sortedCopy(digits));
    }

    private void swapDigitAndNextLowerDigit(Integer digit, List<Integer> sorted) {
      int sortedDigitIndex = sorted.indexOf(digit);
      Integer next = sorted.get(sortedDigitIndex + 1);

      int highestIndex = digits.indexOf(digit);
      int nextIndex = digits.indexOf(next);

      digits.set(highestIndex, next);
      digits.set(nextIndex, digit);
    }

    public Long toLong() {
      return Long.valueOf(toString());
    }

    @Override public String toString() {
      return JOINER.join(digits);
    }
  }
}
