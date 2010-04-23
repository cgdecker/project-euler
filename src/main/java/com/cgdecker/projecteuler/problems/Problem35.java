package com.cgdecker.projecteuler.problems;


import static com.cgdecker.projecteuler.util.Maths.primeNumbers;
import static com.cgdecker.projecteuler.util.StringConversion.parseLong;
import static com.cgdecker.projecteuler.util.Strings.rotatedPermutations;
import static com.google.common.collect.Lists.transform;

import java.util.List;
import java.util.Set;

import com.cgdecker.projecteuler.Problem;
import com.cgdecker.projecteuler.util.Maths;
import com.google.common.collect.Sets;


public class Problem35 implements Problem<Integer>
{
   public int getId()
   {
      return 35;
   }
   

   public Integer solve()
   {
      Set<Long> matches = Sets.newHashSet();
      Set<Long> misses = Sets.newHashSet();

      for( Long prime : primeNumbers() )
      {
         if( prime >= 1000000L )
            break;
         
         if( !matches.contains( prime ) && !misses.contains( prime ) )
         {
            String primeAsString = String.valueOf( prime );
            List<Long> permutations = transform( rotatedPermutations( primeAsString ), parseLong() );
            
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
            
            if( allPrimes )
            {
               matches.addAll( permutations );
            }
         }
      }

      return matches.size();
   }
}
