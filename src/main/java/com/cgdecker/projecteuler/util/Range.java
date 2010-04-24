package com.cgdecker.projecteuler.util;

import java.util.Iterator;

import com.google.common.base.Objects;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterators;
import com.google.common.primitives.Longs;


/**
 * An iterable range of numbers. May be lower to higher or higher to lower or even from one number to itself. From and
 * to are both inclusive.
 * <p/>
 * <p/>
 * A range is immutable and thread safe.
 *
 * @author Colin Decker
 */
public class Range implements Iterable<Long> {
  private static final Range EMPTY = new Range();

  private final long from;
  private final long to;

  private final boolean empty;

  private Range(long from, long to) {
    this.from = from;
    this.to = to;
    this.empty = false;
  }

  /**
   * Empty range constructor.
   */
  private Range() {
    this.from = 0;
    this.to = 0;
    this.empty = true;
  }

  /**
   * Starts building a range by setting the value the range should start at.
   *
   * @param from the value the range should start at.
   * @return a builder to set the "to" value on.
   */
  public static RangeBuilder from(long from) {
    return new RangeBuilder(from);
  }

  /**
   * Gets a range consisting of the given value only.
   *
   * @param value the single value for the range.
   * @return a singleton range.
   */
  public static Range singleton(long value) {
    return from(value).to(value);
  }


  /**
   * Returns the empty range.
   *
   * @return the empty range.
   */
  public static Range empty() {
    return EMPTY;
  }


  /**
   * Gets whether or not the given value is within this range.
   */
  public boolean contains(long value) {
    if(empty)
      return false;

    if (isAscending()) {
      return value >= from && value <= to;
    }

    return value >= to && value <= from;
  }

  /**
   * Gets the size of this range.
   */
  public long size() {
    if(empty)
      return 0;

    long lower = Longs.min(from, to);
    long higher = Longs.max(from, to);

    if (lower < 0 && higher > 0) {
      return Math.abs(lower) + higher + 1;
    }

    return higher - lower + 1;
  }

  /**
   * Gets a range that is the inverse of this one.
   *
   * @return a range that runs from this range's "to" value to this range's "from" value.
   */
  public Range inverse() {
    return empty || size() == 1 ? this : Range.from(to).to(from);
  }

  /**
   * Gets whether the range goes from lower to higher (ascending) or not.
   *
   * @return {@code true} if the range is from lower to higher OR consists of only a single value; {@code false}
   *         otherwise.
   */
  public boolean isAscending() {
    return to >= from;
  }

  public Iterator<Long> iterator() {
    return empty ? Iterators.<Long>emptyIterator() : new RangeIterator(from, to);
  }

  public static class RangeBuilder {
    private final long from;

    private RangeBuilder(long from) {
      this.from = from;
    }

    /**
     * Builds a range from the previously specified "from" value to the given value which the
     * range should run to (inclusive). The given value will be the last element of the range.
     *
     * @param to the value the range should run to.
     * @return a new range.
     */
    public Range to(long to) {
      return new Range(from, to);
    }


    /**
     * Builds a range from the previously specified "from" value up to the given value which the
     * range should run to (exclusive). The given value is not included in the range.
     *
     * <p>If the given value is the same as the from value, an empty range will be returned which
     * has a size of 0, contains nothing and has no elements to iterate over.
     *
     * <p>If the given value is less than the from value, the range will run to the  given number + 1.
     *
     * @param to the number the range should run up to.
     * @return a new range.
     */
    public Range upTo(long to) {
      if(to == from)
        return empty();

      long increment = to > from ? 1 : -1;
      return new Range(from, to - increment);
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Range) {
      Range other = (Range) obj;
      return from == other.from && to == other.to;
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(from, to);
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
        .add("from", from)
        .add("to", to)
        .toString();
  }

  /**
   * Iterator over a range.
   *
   * @author Colin Decker
   */
  private static class RangeIterator extends AbstractIterator<Long> {
    private Long current;
    private final Long increment;
    private final Long end;

    RangeIterator(Long from, Long to) {
      this.current = from;

      boolean ascending = to.compareTo(from) >= 0;

      this.increment = ascending ? 1L : -1L;
      this.end = to + increment;
    }

    @Override
    protected Long computeNext() {
      if (isAtEnd())
        return endOfData();

      Long result = current;
      current = current + increment;
      return result;
    }

    private boolean isAtEnd() {
      return current.equals(end);
    }
  }
}