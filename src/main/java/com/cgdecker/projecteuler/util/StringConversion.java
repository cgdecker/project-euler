package com.cgdecker.projecteuler.util;


import com.google.common.base.Function;


public class StringConversion
{
   private enum ParseLong implements Function<String, Long>
   {
      INSTANCE;
      
      public Long apply( String from )
      {
         return Long.valueOf( from );
      }
   }


   public static Function<String, Long> parseLong()
   {
      return ParseLong.INSTANCE;
   }
}
