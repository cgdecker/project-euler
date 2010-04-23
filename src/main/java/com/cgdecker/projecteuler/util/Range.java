package com.cgdecker.projecteuler.util;

import java.util.Iterator;

import com.google.common.base.Objects;
import com.google.common.collect.AbstractIterator;
import com.google.common.primitives.Longs;


/**
 * An iterable range of numbers. May be lower to higher or higher to lower or even from one number
 * to itself. From and to are both inclusive.
 * 
 * <p>
 * A range is immutable and thread safe.
 * 
 * @author Colin Decker
 */
public class Range implements Iterable<Long>
{
   private final long from;
   private final long to;
   

   private Range( long from, long to )
   {
      this.from = from;
      this.to = to;
   }
   

   /**
    * Starts building a range by setting the value the range should start at.
    * 
    * @param from the value the range should start at.
    * @return a builder to set the "to" value on.
    */
   public static RangeBuilder from( long from )
   {
      return new RangeBuilder( from );
   }
   

   /**
    * Gets a range consisting of the given value only.
    * 
    * @param value the single value for the range.
    * @return a singleton range.
    */
   public static Range singletonRange( long value )
   {
      return from( value ).to( value );
   }


   /**
    * Gets whether or not the given value is within this range.
    */
   public boolean contains( long value )
   {
      if( isAscending() )
      {
         return value >= from && value <= to;
      }
      
      return value >= to && value <= from;
   }
   

   /**
    * Gets the size of this range.
    */
   public long size()
   {
      long lower = Longs.min( from, to );
      long higher = Longs.max( from, to );
      
      if( lower < 0 && higher > 0 )
      {
         return Math.abs( lower ) + higher + 1;
      }
      
      return higher - lower + 1;
   }
   

   /**
    * Gets whether the range goes from lower to higher (ascending) or not.
    * 
    * @return {@code true} if the range is from lower to higher OR consists of only a single value;
    *         {@code false} otherwise.
    */
   public boolean isAscending()
   {
      return to >= from;
   }


   public Iterator<Long> iterator()
   {
      return new RangeIterator( from, to );
   }
   

   public static class RangeBuilder
   {
      private final long from;
      

      private RangeBuilder( long from )
      {
         this.from = from;
      }
      

      /**
       * Builds a range from the previously specified "from" value to the given value which the
       * range should run to.
       * 
       * @param to the value the range should run to.
       * @return a new range.
       */
      public Range to( long to )
      {
         return new Range( from, to );
      }
   }
   

   @Override
   public boolean equals( Object obj )
   {
      if( obj instanceof Range )
      {
         Range other = (Range) obj;
         return from == other.from && to == other.to;
      }
      
      return false;
   }
   

   @Override
   public int hashCode()
   {
      return Objects.hashCode( from, to );
   }
   

   @Override
   public String toString()
   {
      return Objects.toStringHelper( this )
         .add( "from", from )
         .add( "to", to )
         .toString();
   }


   /**
    * Iterator over a range.
    * 
    * @author Colin Decker
    */
   private static class RangeIterator extends AbstractIterator<Long>
   {
      private Long current;
      private final Long increment;
      private final Long end;
      

      RangeIterator( Long from, Long to )
      {
         this.current = from;
         
         boolean ascending = to.compareTo( from ) >= 0;
         
         this.increment = ascending ? 1L : -1L;
         this.end = to + increment;
      }
      

      @Override
      protected Long computeNext()
      {
         if( isAtEnd() )
            return endOfData();
         
         Long result = current;
         current = current + increment;
         return result;
      }
      

      private boolean isAtEnd()
      {
         return current.equals( end );
      }
   }
}