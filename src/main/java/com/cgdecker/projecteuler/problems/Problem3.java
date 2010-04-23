package com.cgdecker.projecteuler.problems;

import com.cgdecker.projecteuler.Problem;
import com.cgdecker.projecteuler.util.Maths;


public class Problem3 implements Problem<Long>
{
   private static final long PROBLEM_NUMBER = 600851475143L;
   

   public int getId()
   {
      return 3;
   }


   public Long solve()
   {
      return Maths.findPrimeFactors( PROBLEM_NUMBER ).greatest();
   }
}
