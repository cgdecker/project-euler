package com.cgdecker.projecteuler.util.time;

import com.google.inject.Singleton;

/**
 * The default production implementation of {@link Clock}. Uses the current system time as retrieved
 * by {@link System#currentTimeMillis()}.
 * 
 * @author cgdecker@gmail.com (Colin Decker)
 */
@Singleton
public class SystemClock implements Clock {

  public long getCurrentTimeMillis() {
    return System.currentTimeMillis();
  }

  public long getTimingNanos() {
    return System.nanoTime();
  }

}
