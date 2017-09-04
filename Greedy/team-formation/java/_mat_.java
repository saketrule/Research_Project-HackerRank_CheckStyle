import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 
 public static int minTeamSize(int[] ar){
  Arrays.sort(ar);
  if(ar.length == 0){
   return 0;
  }
  

  //There's at least one team, that needs a skill level of 2 to add.
  int teamTail = ar[0];
  HashMap<Integer, List<Integer>> teams = new HashMap<Integer, List<Integer>>();
  

  for(int i = 0; i < ar.length; i++){
   int item = ar[i];
   int next = item +1;
   //Can we append this value to the last one?
//   System.out.println("----\nInsert " + item);
   if(teams.containsKey(item)){

    List<Integer> validTeams = teams.get(item);
//    System.out.println(validTeams.size() + " possible teams");
    int minsz = 0;
    boolean taken = false;
    //Are there actually any teams?
    if(!validTeams.isEmpty()){
     int mini = -1;
    
     //Allocate this member to the smallest team:
     for(int t = 0; t < validTeams.size(); t++){
      if(!taken || validTeams.get(t) < minsz){
       minsz = validTeams.get(t);
       mini = t;
       taken = true;
      }
     }
     //Move the team to the next entry:
//     System.out.println("Removing from team " + mini + " size " + minsz);
     validTeams.remove(mini);
    }
    if(!teams.containsKey(next)){
     teams.put(next, new ArrayList<Integer>());
    }
    teams.get(next).add(minsz+1);
   }else{
//    System.out.println("Create new team");
    if(!teams.containsKey(next))
     teams.put(next, new ArrayList<Integer>());
    teams.get(next).add(1);
   }
  }
  
  int minsz = -1;
  boolean taken = false;
  for(Integer i : teams.keySet()){
   for(Integer j : teams.get(i)){
    
//    System.out.println ("Next :" + i + " size: " + j);
    
    minsz = taken?Math.min(minsz, j):j;
    taken = true;
   }
  }
  
  return minsz;
  
 }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
  int t =input.nextInt();
  for(int i =0;i < t; i++){
   int n = input.nextInt();
   int[] ar = new int[n];
   for(int j = 0; j < n; j++){
    ar[j] = input.nextInt();
   }
   System.out.println(minTeamSize(ar));
  }
    }
}