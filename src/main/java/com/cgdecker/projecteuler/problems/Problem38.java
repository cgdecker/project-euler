package com.cgdecker.projecteuler.problems;

import static com.cgdecker.projecteuler.util.Numbers.pandigitalNumbers;

import java.util.Set;

import com.google.common.collect.*;

@Incomplete("not working, and obviously not a great solution to begin with")
public class Problem38 extends AbstractProblem<Long> {

  public Long solve() {
    for (Long pandigital : pandigitalNumbers()) {
      String stringValue = pandigital.toString();
      for (int i = 9; i < 999; i++) {
        if (!containsDuplicateDigits(i)) {
          String concatenatedValue = "";
          for (int n = 1; n < 6; n++) {
            concatenatedValue += String.valueOf(i * n);
            if (stringValue.equals(concatenatedValue))
              return pandigital;
            if (concatenatedValue.length() >= stringValue.length())
              break;
          }
        }
      }
    }
    return null;
  }

  private boolean containsDuplicateDigits(int number) {
    char[] digits = String.valueOf(number).toCharArray();
    Set<Character> set = Sets.newHashSet();
    for (char digit : digits) {
      if (set.contains(digit))
        return true;
      set.add(digit);
    }
    return false;
  }
}
