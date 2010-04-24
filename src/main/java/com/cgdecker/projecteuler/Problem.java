package com.cgdecker.projecteuler;

public interface Problem<T> {
  int getId();


  T solve();
}
