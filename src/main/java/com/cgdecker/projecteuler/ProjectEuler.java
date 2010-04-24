package com.cgdecker.projecteuler;

import com.google.inject.Guice;
import com.google.inject.Injector;


public class ProjectEuler {

  public static void main(String[] args) {
    Injector injector = Guice.createInjector(new IOModule(), new TimeModule(), new ApplicationModule());
    Application application = injector.getInstance(Application.class);

    application.start();
  }

}
