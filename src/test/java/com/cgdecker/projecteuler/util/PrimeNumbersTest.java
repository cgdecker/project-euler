package com.cgdecker.projecteuler.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.common.collect.ImmutableSet;

public class PrimeNumbersTest {
  private final ImmutableSet<Long> FIRST_PRIME_NUMBERS = ImmutableSet.of(2l, 3l, 5l, 7l, 11l,
      13l, 17l, 19l, 23l, 29l, 31l, 37l, 41l, 43l, 47l, 53l, 59l, 61l, 67l, 71l, 73l, 79l, 83l,
      89l, 97l, 101l, 103l, 107l, 109l, 113l, 127l, 131l, 137l, 139l, 149l, 151l, 157l, 163l, 167l,
      173l, 179l, 181l, 191l, 193l, 197l, 199l);


  @Test
  public void isPrimeMatchesAllPrimeNumbersUpTo200() {
    for (long i = 0; i <= 200; i++) {
      boolean prime = FIRST_PRIME_NUMBERS.contains(i);
      assertEquals("expected isPrime(" + i + ") to be " + prime + " but it was not", prime,
          PrimeNumbers.isPrime(i));
    }
  }

}
