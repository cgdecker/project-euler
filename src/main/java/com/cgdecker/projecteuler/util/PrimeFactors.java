package com.cgdecker.projecteuler.util;


import java.util.Collections;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.*;
import com.google.common.collect.Multiset.Entry;


public class PrimeFactors
{
   private final ImmutableMultiset<Long> factors;
   

   PrimeFactors( Multiset<Long> factors )
   {
      this.factors = ImmutableMultiset.copyOf( factors );
   }
   

   public Long getOriginalValue()
   {
      long result = 1;
      for( Long factor : factors )
      {
         result *= factor;
      }
      return result;
   }


   public ImmutableMultiset<Long> getFactors()
   {
      return factors;
   }
   

   public Long greatest()
   {
      return Collections.max( factors );
   }


   @Override
   public boolean equals( Object obj )
   {
      if( obj instanceof PrimeFactors )
      {
         return Objects.equal( ( (PrimeFactors) obj ).factors, factors );
      }
      
      return false;
   }
   

   @Override
   public int hashCode()
   {
      return factors.hashCode();
   }
   

   @Override
   public String toString()
   {
      List<String> strings = Lists.newArrayList();
      for( Entry<Long> factor : factors.entrySet() )
      {
         String element = String.valueOf( factor.getElement() );
         if( factor.getCount() == 1 )
            strings.add( element );
         else
            strings.add( element + "^" + factor.getCount() );
      }
      return Joiner.on( " x " ).join( strings );
   }
}
