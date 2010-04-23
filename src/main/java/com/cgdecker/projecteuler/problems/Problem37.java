package com.cgdecker.projecteuler.problems;


import static com.cgdecker.projecteuler.util.Maths.primeNumbers;
import static com.cgdecker.projecteuler.util.StringConversion.parseLong;
import static com.cgdecker.projecteuler.util.Strings.leftAndRightTruncatedPermutations;
import static com.google.common.collect.Collections2.transform;

import java.util.Set;

import com.cgdecker.projecteuler.Problem;
import com.cgdecker.projecteuler.util.Maths;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;


public class Problem37 implements Problem<Long>
{
   public int getId()
   {
      return 37;
   }
   

   public Long solve()
   {
      Set<Long> matches = Sets.newHashSet();
      Set<Long> misses = Sets.newHashSet();
      
      for( Long prime : primeNumbers() )
      {
         if( !matches.contains( prime ) && !misses.contains( prime ) )
         {
            String primeAsString = String.valueOf( prime );
            ImmutableSet<Long> permutations = ImmutableSet.copyOf( transform(
               leftAndRightTruncatedPermutations( primeAsString ), parseLong() ) );
            
            boolean allPrimes = true;
            for( Long value : permutations )
            {
               if( !prime.equals( value ) && !Maths.isPrime( value ) )
               {
                  allPrimes = false;
                  misses.addAll( permutations );
                  break;
               }
            }
            
            if( allPrimes && prime > 7 )
            {
               matches.add( prime );
            }
         }
         
         if( matches.size() == 11 )
            break;
      }
      
      long result = 0;
      for( Long match : matches )
      {
         result += match;
      }
      return result;
   }
}
