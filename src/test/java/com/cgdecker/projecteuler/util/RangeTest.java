package com.cgdecker.projecteuler.util;

import java.util.Iterator;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class RangeTest {
  private static final Range SINGLETON = Range.singleton(1);

  private static final Range ONE_TO_FOUR = Range.from(1).to(4);
  private static final Range FOUR_TO_ONE = Range.from(4).to(1);

  private static final Range NEGATIVE_ONE_TO_TWO = Range.from(-1).to(2);
  private static final Range TWO_TO_NEGATIVE_ONE = Range.from(2).to(-1);

  private static final Range NEGATIVE_ONE_TO_NEGATIVE_FOUR = Range.from(-1).to(-4);
  private static final Range NEGATIVE_FOUR_TO_NEGATIVE_ONE = Range.from(-4).to(-1);

  @Test
  public void rangesFrom1To4Contain1234() {
    assertRangeIs(ONE_TO_FOUR, 1, 2, 3, 4);
    assertRangeIs(FOUR_TO_ONE, 4, 3, 2, 1);
    assertTrue(ONE_TO_FOUR.isAscending());
    assertFalse(FOUR_TO_ONE.isAscending());
  }

  @Test
  public void rangeFromNeg1To2ContainNeg1012() {
    assertRangeIs(NEGATIVE_ONE_TO_TWO, -1, 0, 1, 2);
    assertRangeIs(TWO_TO_NEGATIVE_ONE, 2, 1, 0, -1);
    assertTrue(NEGATIVE_ONE_TO_TWO.isAscending());
    assertFalse(TWO_TO_NEGATIVE_ONE.isAscending());
  }

  @Test
  public void rangeFromNeg1ToNeg4ContainsNeg1234() {
    assertRangeIs(NEGATIVE_ONE_TO_NEGATIVE_FOUR, -1, -2, -3, -4);
    assertRangeIs(NEGATIVE_FOUR_TO_NEGATIVE_ONE, -4, -3, -2, -1);
    assertTrue(NEGATIVE_FOUR_TO_NEGATIVE_ONE.isAscending());
    assertFalse(NEGATIVE_ONE_TO_NEGATIVE_FOUR.isAscending());
  }

  @Test
  public void rangesWithOppositeFromAndToAreInverses() {
    assertInverseRanges(ONE_TO_FOUR, FOUR_TO_ONE);
    assertInverseRanges(NEGATIVE_ONE_TO_TWO, TWO_TO_NEGATIVE_ONE);
    assertInverseRanges(NEGATIVE_ONE_TO_NEGATIVE_FOUR, NEGATIVE_FOUR_TO_NEGATIVE_ONE);
  }

  @Test
  public void singletonAndEmptyRangesAreInversesOfThemselves() {
    assertInverseRanges(SINGLETON, SINGLETON);
    assertInverseRanges(Range.empty(), Range.empty());
  }

  @Test
  public void singletonContainsOneValue() {
    assertRangeIs(SINGLETON, 1);
    assertEquals(Range.from(1).to(1), SINGLETON);
    assertTrue(SINGLETON.isAscending());
  }

  @Test
  public void emptyRangeContainsNoValues() {
    assertRangeIs(Range.empty());
    assertEquals(Range.from(1).upTo(1), Range.empty());
    assertTrue(Range.empty().isAscending());
  }

  private static void assertRangeIs(Range range, long... values) {
    assertEquals(values.length, range.size());

    Iterator<Long> rangeIterator = range.iterator();
    for (Long value : values) {
      assertTrue(range.contains(value));
      assertEquals(value, rangeIterator.next());
    }
    assertFalse(rangeIterator.hasNext());
  }

  private static void assertInverseRanges(Range first, Range second) {
    assertEquals(second, first.inverse());
    assertEquals(first, second.inverse());
  }
}
