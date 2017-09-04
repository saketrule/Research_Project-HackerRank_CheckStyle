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
  int noOfFighters = in.nextInt();
  int noOfTeams = in.nextInt();
  int noOfQueries = in.nextInt();

  ArrayList<ArrayList<Integer>> teams = new ArrayList<ArrayList<Integer>>();
  ArrayList<ArrayList<Long>> teamTotals = new ArrayList<ArrayList<Long>>();
  for( int i = 0; i < noOfTeams; i++)
  {
   teams.add( new ArrayList<Integer>());
   teamTotals.add( new ArrayList<Long>());
  }
  
  int strength, team;
  for( int i = 0; i < noOfFighters; i++)
  {
   strength = in.nextInt();
   team = in.nextInt() - 1;
   teams.get( team).add( strength);
  }
  
  for( int i = 0; i < noOfTeams; i++)
  {
   Collections.sort( teams.get(i));
  }
  
  long sumSoFar;
  ArrayList<Integer> t;
  ArrayList<Long> teamTotal;
  for( int i = 0; i < noOfTeams; i++)
  {
   sumSoFar = 0;
   t = teams.get(i);
   teamTotal = teamTotals.get(i);
   for( Integer intgr : t)
   {
    sumSoFar += intgr;
    teamTotal.add( sumSoFar);
   }
  }
  
  int q1, q2, q3, index1, index2, addAfter;
  ArrayList<Integer> team1, team2;
  ArrayList<Long> teamTotal1, teamTotal2;
  boolean turn;
  for( int i = 0; i < noOfQueries; i++)
  {
   q1 = in.nextInt();
   q2 = in.nextInt() - 1;
   q3 = in.nextInt() - 1;
   
   if( q1 == 1)
   {
    ++q2;
    
    team1 = teams.get( q3);
    teamTotal1 = teamTotals.get( q3);
                if( team1.size() == 0)
    {
     team1.add( q2);
     teamTotal1.add( (long)q2);
    }
    else if( team1.get( team1.size()-1) <= q2)
    {
     team1.add( q2);
     teamTotal1.add( teamTotal1.get( teamTotal1.size()-1) + q2);
    }
    else if( team1.get(0) > q2)
    {
     team1.add( 0, q2);
     teamTotal1.add( 0, (long)q2);
     for( int j = 1; j < teamTotal1.size(); j++)
     {
      teamTotal1.set( j, teamTotal1.get(j) + q2);
     }
    }
    else
    {
     addAfter = team1.size() / 2;
     
     while( team1.get( addAfter) > q2 || team1.get( addAfter + 1) < q2)
     {
      if( team1.get( addAfter) > q2)
      {
       addAfter = addAfter / 2;
      }
      else if( team1.get( addAfter + 1) < q2)
      {
       addAfter = (addAfter + team1.size()) / 2;
      }
     }
     
     team1.add( addAfter + 1, q2);
     teamTotal1.add( addAfter + 1, teamTotal1.get( addAfter) + q2);
     for( int j = addAfter + 2; j < teamTotal1.size(); j++)
     {
      teamTotal1.set( j, teamTotal1.get(j) + q2);
     }
    }
   }
   else
   {
    team1 = teams.get( q2);
    teamTotal1 = teamTotals.get( q2);
    index1 = team1.size()-1;
    
    team2 = teams.get( q3);
    teamTotal2 = teamTotals.get( q3);
    index2 = team2.size()-1;
    
    turn = true;
    while( index1 >= 0 && index2 >= 0)
    {
     if( turn)
     {
      if( teamTotal1.get( index1).compareTo( teamTotal2.get( index2)) > 0)
      {
       index2 = -1;
      }
      else
      {
       index2 -= team1.get( index1);
      }
     }
     else
     {
      if( teamTotal2.get( index2).compareTo( teamTotal1.get( index1)) > 0)
      {
       index1 = -1;
      }
      else
      {
       index1 -= team2.get( index2);
      }
     }
     
     turn = !turn;
    }
    
    if( index1 < 0)
    {
     System.out.println( q3 + 1);
    }
    else
    {
     System.out.println( q2 + 1);
    }
   }
  }
  in.close();
 }
}