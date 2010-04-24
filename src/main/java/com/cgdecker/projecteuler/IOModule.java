package com.cgdecker.projecteuler;

import java.io.PrintStream;
import java.util.Scanner;

import com.google.inject.AbstractModule;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class IOModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(PrintStream.class).toInstance(System.out);
    bind(Scanner.class).toInstance(new Scanner(System.in));
  }
}
