package com.cgdecker.projecteuler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.inject.AbstractModule;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @author cgdecker@gmail.com (Colin Decker)
 */
public class ApplicationModule extends AbstractModule {
  @Override
  protected void configure() {
    ThreadFactory threadFactory = new ThreadFactoryBuilder()
        .setDaemon(true)
        .setNameFormat("problem-thread-%d")
        .build();

    bind(ExecutorService.class).toInstance(newFixedThreadPool(4, threadFactory));
    bind(ProblemFactory.class);
    bind(ProblemRunner.class);
    bind(Application.class);
  }
}
