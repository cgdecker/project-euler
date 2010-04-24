package com.cgdecker.projecteuler;

import com.cgdecker.projecteuler.util.time.Clock;
import com.cgdecker.projecteuler.util.time.Stopwatch;
import com.cgdecker.projecteuler.util.time.SystemClock;
import com.google.inject.AbstractModule;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class TimeModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(Clock.class).to(SystemClock.class);
    bind(Stopwatch.class);
  }
}
