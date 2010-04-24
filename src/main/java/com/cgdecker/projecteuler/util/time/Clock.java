package com.cgdecker.projecteuler.util.time;

/**
 * A clock is a source for the current time. This interface allows classes with a dependency on a
 * source for the current time to depend on an abstraction for that rather than a something concrete
 * like the static {@link System#currentTimeMillis()}.
 * 
 * @author cgdecker@gmail.com (Colin Decker)
 */
public interface Clock {

  /**
   * Gets the current time as milliseconds. This should be used when you need the current wall clock
   * time, not for measuring elapsed time.
   * 
   * @return the current time as milliseconds.
   * @see System#currentTimeMillis()
   */
  long getCurrentTimeMillis();

  /**
   * Gets the current time as nanoseconds. This should be used only for measuring elapsed time, not
   * for getting the current wall clock time.
   * 
   * @return the current time as nanoseconds.
   * @see System#nanoTime()
   */
  long getTimingNanos();
}
