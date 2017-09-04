import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution
{
 public static void main( String[] args) throws FileNotFoundException
 {
  Scanner in = new Scanner( System.in);
  
  int noOfGames = in.nextInt();
  int noOfPiles;
  int[] piles;
  
  for( int i = 0; i < noOfGames; i++)
  {
   noOfPiles = in.nextInt();
   piles = new int[noOfPiles];
   
   for( int j = 0; j < noOfPiles; j++)
   {
    piles[j] = in.nextInt();
   }
   
   if( nimMisere( piles, noOfPiles))
   {
    System.out.println( "First");
   }
   else
   {
    System.out.println( "Second");
   }
  }
  in.close();
 }
 
 public static boolean nimMisere( int[] piles, int noOfPiles)
 {
  boolean firstPlayer = true;
  
  while( true)
  {
   int result = 0;
   int max = 0;
   int maxIndex = 0;
   
   for( int i = 0; i < noOfPiles; i++)
   {
    result = result ^ piles[i];
    if( piles[i] > max)
    {
     max = piles[i];
     maxIndex = i;
    }
   }
   
   if( result == 0)
   {
    if( max > 1)
    {
     return !firstPlayer;
    }
    else
    {
     for( int i = 0; i < noOfPiles; i++)
     {
      if( piles[i] > 0)
      {
       piles[i] = 0;
       firstPlayer = !firstPlayer;
       break;
      }
     }
    }
   }
   else
   {
    int chosenPile = -1;
    int noOfStonesTaken = -1;
    int noOfPilesWithOneStone = 0;
    int noOfPilesWithTwoOrMoreStones = 0;
    for( int i = 0; i < noOfPiles; i++)
    {
     if( (result ^ piles[i]) < piles[i])
     {
      if( chosenPile == -1)
      {
       chosenPile = i;
      }
     }
     if( piles[i] == 1)
     {
      ++noOfPilesWithOneStone;
     }
     else if( piles[i] > 1)
     {
      ++noOfPilesWithTwoOrMoreStones;
     }
    }
    
    if( noOfPilesWithTwoOrMoreStones == 0)
    {
     if( noOfPilesWithOneStone % 2 == 0)
     {
      return firstPlayer;
     }
     else
     {
      return !firstPlayer;
     }
    }
    
    noOfStonesTaken = piles[chosenPile] - (result ^ piles[chosenPile]);
    if( piles[chosenPile] > 1 && piles[chosenPile] - noOfStonesTaken <= 1)
    {
     --noOfPilesWithTwoOrMoreStones;
    }
    
    if( noOfPilesWithTwoOrMoreStones == 0)
    {
     chosenPile = maxIndex;
     if( noOfPilesWithOneStone % 2 == 0)
     {
      noOfStonesTaken = piles[chosenPile] - 1;
     }
     else
     {
      noOfStonesTaken = piles[chosenPile];
     }
    }
    
    piles[chosenPile] = piles[chosenPile] - noOfStonesTaken;
    firstPlayer = !firstPlayer;
   }
  }
 }
}