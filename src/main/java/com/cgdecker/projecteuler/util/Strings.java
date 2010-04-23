package com.cgdecker.projecteuler.util;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;


/**
 * String helpers.
 * 
 * @author Colin Decker
 */
public class Strings
{
   /**
    * A version of the given string with all characters rotated 1 to the left is returned. The first
    * character becomes the last character.
    */
   public static String rotateLeft( String string )
   {
      if( string.length() < 2 )
         return string;
      
      return string.substring( 1 ) + string.substring( 0, 1 );
   }
   

   /**
    * Gets the given string with its leftmost character truncated.
    */
   public static String truncateLeft( String string )
   {
      if( string.length() < 2 )
         return string;
      
      return string.substring( 1 );
   }
   

   /**
    * Gets the given string with its rightmost character truncated.
    */
   public static String truncateRight( String string )
   {
      if( string.length() < 2 )
         return string;
      
      return string.substring( 0, string.length() - 1 );
   }
   

   public static ImmutableSet<String> leftTruncatedPermutations( String string )
   {
      ImmutableSet.Builder<String> builder = ImmutableSet.builder();
      while( string.length() > 1 )
      {
         string = truncateLeft( string );
         builder.add( string );
      }
      return builder.build();
   }
   

   public static ImmutableSet<String> rightTruncatedPermutations( String string )
   {
      ImmutableSet.Builder<String> builder = ImmutableSet.builder();
      while( string.length() > 1 )
      {
         string = truncateRight( string );
         builder.add( string );
      }
      return builder.build();
   }
   

   public static ImmutableSet<String> leftAndRightTruncatedPermutations( String string )
   {
      return ImmutableSet.<String> builder()
         .addAll( leftTruncatedPermutations( string ) )
         .addAll( rightTruncatedPermutations( string ) )
         .build();
   }


   /**
    * Returns all rotated permutations of the given string.
    */
   public static ImmutableList<String> rotatedPermutations( String string )
   {
      String currentPermutation = string;
      ImmutableList.Builder<String> builder = ImmutableList.builder();
      do
      {
         builder.add( currentPermutation );
         currentPermutation = rotateLeft( currentPermutation );
      }
      while( !string.equals( currentPermutation ) );
      
      return builder.build();
   }
}
