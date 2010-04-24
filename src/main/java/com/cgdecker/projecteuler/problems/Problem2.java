package com.cgdecker.projecteuler.problems;


import static com.google.common.collect.Iterables.filter;

import java.util.Iterator;

import com.cgdecker.projecteuler.Problem;
import com.google.common.base.Predicate;
import com.google.common.collect.AbstractIterator;


public class Problem2 implements Problem<Long> {
  public int getId() {
    return 2;
  }

  public Long solve() {
    long result = 0;
    for (long evenFibonacciNumber : filter(FIBONACCI_SEQUENCE, EVEN_NUMBERS)) {
      if (evenFibonacciNumber > 4000000)
        break;

      result += evenFibonacciNumber;
    }
    return result;
  }

  private static final Predicate<Long> EVEN_NUMBERS = new Predicate<Long>() {
    public boolean apply(Long input) {
      return input % 2 == 0;
    }
  };

  /**
   * The Fibonacci sequence. The sequence is infinite in nature, so be aware that looping over this will run forever
   * unless you break it yourself.
   */
  private static final Iterable<Long> FIBONACCI_SEQUENCE = new Iterable<Long>() {
    public Iterator<Long> iterator() {
      return new FibonacciSequenceGenerator();
    }
  };


  /**
   * Iterator that generates the numbers of the Fibonacci sequence.
   *
   * @author Colin Decker
   */
  private static class FibonacciSequenceGenerator extends AbstractIterator<Long> {
    private long firstPrev = 0;
    private long secondPrev = 1;

    @Override
    protected Long computeNext() {
      long next = firstPrev + secondPrev;
      firstPrev = secondPrev;
      secondPrev = next;
      return next;
    }
  }
}
