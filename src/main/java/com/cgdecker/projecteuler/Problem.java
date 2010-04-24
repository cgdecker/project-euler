package com.cgdecker.projecteuler;

import java.util.concurrent.Callable;

public interface Problem<T> extends Callable<T> {
  int getId();

  T solve();
}
