package com.cgdecker.projecteuler.problems;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * Marker for problems that are not yet successful.
 *
 * @author cgdecker@gmail.com (Colin Decker)
 */
@Target(TYPE)
@Documented
@interface Incomplete {
  public String value() default "";
}
