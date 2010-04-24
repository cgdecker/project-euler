package com.cgdecker.projecteuler.util.time;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.inject.Inject;

/**
 * A stopwatch is used for timing things. It measures the time that elapses between when its start
 * method is called and when its stop method is called.
 * 
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class Stopwatch {
  private final Clock clock;

  private AtomicBoolean running = new AtomicBoolean();

  private long startTime = 0;
  private long elapsedTime = 0;

  @Inject
  public Stopwatch(Clock clock) {
    this.clock = clock;
  }

  /**
   * Starts the stopwatch. Does nothing if the stopwatch is already running.
   */
  public void start() {
    if (running.compareAndSet(false, true)) {
      startTime = clock.getTimingNanos();
    }
  }

  /**
   * Stops the stopwatch. Does nothing if the stopwatch is not running.
   */
  public void stop() {
    if (running.compareAndSet(true, false)) {
      elapsedTime += clock.getTimingNanos() - startTime;
    }
  }

  /**
   * Resets the elapsed time on the stopwatch. Does nothing if the stopwatch is running.
   */
  public void reset() {
    if (!running.get()) {
      elapsedTime = 0;
    }
  }

  /**
   * Checks whether or not the stopwatch is currently running.
   * 
   * @return {@code true} if the stopwatch is running; {@code false} otherwise.
   */
  public boolean isRunning() {
    return running.get();
  }

  /**
   * Gets the time elapsed while the stopwatch was running and since it was last reset. If the
   * stopwatch is currently running, the elapsed time will include the time up to the moment.
   * 
   * @return the elapsed time, in nanoseconds.
   */
  public long getElapsedTimeNanos() {
    if (running.get()) {
      return elapsedTime + (clock.getTimingNanos() - startTime);
    }

    return elapsedTime;
  }

  /**
   * Gets the elapsed time in milliseconds.
   * 
   * @return the elapsed time, in milliseconds.
   * @see #getElapsedTimeNanos()
   */
  public long getElapsedTimeMillis() {
    return TimeUnit.NANOSECONDS.toMillis(getElapsedTimeNanos());
  }

  /**
   * Gets the elapsed time as a {@code double} representing the elapsed seconds.
   * 
   * @return the elapsed time, in seconds.
   * @see #getElapsedTimeNanos()
   */
  public double getElapsedTimeSeconds() {
    return getElapsedTimeMillis() / 1000.0;
  }
}
