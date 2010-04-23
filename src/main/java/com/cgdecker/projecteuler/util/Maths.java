package com.cgdecker.projecteuler.util;


import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Iterables.filter;
import static java.lang.Math.floor;
import static java.lang.Math.sqrt;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Predicate;
import com.google.common.collect.*;


/**
 * Math helper, using the British spelling for fun and to be usable alongsite Math.
 * 
 * @author Colin Decker
 */
public final class Maths
{
   private Maths() {}
   
   private static final ImmutableSet<Long> FIRST_PRIME_NUMBERS = ImmutableSet.of( 2l, 3l, 5l, 7l,
      11l, 13l, 17l, 19l, 23l, 29l, 31l, 37l, 41l, 43l, 47l, 53l, 59l, 61l, 67l, 71l, 73l, 79l,
      83l, 89l, 97l );
   
   private static final Iterable<Long> PRIME_NUMBER_SEQUENCE = filter( Range.from( 2 ).to(
      Long.MAX_VALUE ), isPrimeNumber() );
   
   private enum IsPrimeNumber implements Predicate<Long>
   {
      INSTANCE;
      
      public boolean apply( Long input )
      {
         return isPrime( input );
      }
   }


   /**
    * Determines whether or not the given number is prime.
    * 
    * @param number the number.
    * @return {@code true} if it is prime; {@code false} otherwise.
    */
   public static boolean isPrime( long number )
   {
      if( number < 100L )
      {
         return FIRST_PRIME_NUMBERS.contains( number );
      }
      
      if( number % 2 == 0 || number % 3 == 0 )
         return false;
      
      long squareRoot = (long) floor( sqrt( number ) );
      
      for( long k = 1; 6 * k - 1 <= squareRoot; k++ )
      {
         long lowerPrime = 6l * k - 1l;
         long higherPrime = 6l * k + 1l;
         
         if( number % lowerPrime == 0 )
            return false;
         
         if( number % higherPrime == 0 )
            return false;
      }
      
      return true;
   }
   

   /**
    * The sequence of prime numbers, starting at 2. Will effectively iterate forever (until it
    * reaches {@code Long.MAX_VALUE}) and will take longer as the numbers get higher, so you should
    * be sure to terminate it yourself somehow.
    * 
    * @return the sequence of prime numbers.
    */
   public static Iterable<Long> primeNumbers()
   {
      return PRIME_NUMBER_SEQUENCE;
   }
   

   /**
    * Gets a predicate that determines whether a number is prime.
    * 
    * @return the prime number predicate.
    */
   public static Predicate<Long> isPrimeNumber()
   {
      return IsPrimeNumber.INSTANCE;
   }


   /**
    * Finds the prime factors of the given number.
    * 
    * @param number the number.
    * @return a list of its prime factors.
    */
   public static PrimeFactors findPrimeFactors( int number )
   {
      return findPrimeFactors( (long) number );
   }


   /**
    * Finds the prime factors of the given number.
    * 
    * @param number the number.
    * @return a list of its prime factors.
    */
   public static PrimeFactors findPrimeFactors( long number )
   {
      checkArgument( number > 1, "only works for values > 1 (was %s)", number );

      if( isPrime( number ) )
         return new PrimeFactors( ImmutableMultiset.of( number ) );

      ImmutableMultiset.Builder<Long> primeFactors = ImmutableMultiset.builder();
      long currentNumber = number;
      for( Long prime : primeNumbers() )
      {
         while( currentNumber % prime == 0 )
         {
            primeFactors.add( prime );
            currentNumber = currentNumber / prime;
         }
         
         if( currentNumber == 1 )
         {
            return new PrimeFactors( primeFactors.build() );
         }
         
         if( isPrime( currentNumber ) )
         {
            primeFactors.add( currentNumber );
            return new PrimeFactors( primeFactors.build() );
         }
      }
      
      throw new AssertionError();
   }
   

   public static void main( String[] args )
   {
      // timePrimeFactorization( Range.from( Long.MAX_VALUE ).to( Long.MAX_VALUE - 100L ) );
      // timePrimeIteration( 1000000 );
      /*for( Long prime : primeNumbers() )
      {
         System.out.println( prime );
      }*/
      printPrimeFactors( 19689673122342323L );
   }
   

   static void printPrimeFactors( Long number )
   {
      System.out.println( "Finding prime factors of: " + number );
      System.out.println( findPrimeFactors( number ) );
   }
   

   static void timePrimeIteration( long max )
   {
      long start = System.nanoTime();
      for( Long prime : primeNumbers() )
      {
         if( prime >= max )
            break;
      }
      long duration = System.nanoTime() - start;
      System.out.println( "Found all primes below " + max + " in "
         + TimeUnit.NANOSECONDS.toMillis( duration ) + "ms." );
   }


   static void timePrimeFactorization( Range range )
   {
      System.out.println( "Finding the prime factors of " + range.size() + " number(s) in " + range
         + "." );
      long start = System.nanoTime();
      for( long number : range )
      {
         findPrimeFactors( number );
      }
      long duration = System.nanoTime() - start;
      System.out.println( "Took " + TimeUnit.NANOSECONDS.toMillis( duration ) + "ms." );
   }
}
