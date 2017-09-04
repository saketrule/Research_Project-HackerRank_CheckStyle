import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static Team[] teams ;
 public static void main(String[] args) {
  Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
  int n = in.nextInt();
  int k = in.nextInt();
  int q = in.nextInt();
  teams = new Team[k+1];
  for(int i = 0; i <= k ; i++){
   teams[i] = new Team();
  }
  int power ;
  int teamNumber;
  for(int i = 0;i < n; i++){
   power = in.nextInt();
   teamNumber = in.nextInt();
   teams[teamNumber].add(power);
  }
  
  for(int i = 1; i <= k; i++){
   reverseSort(i);
  }
  int queryType ;
  for(int i = 0;i < q; i++){
   queryType = in.nextInt();
   if(queryType == 1){
    power = in.nextInt();
    teamNumber  = in.nextInt();
    addPlayer(power, teamNumber);
   }else{
    int teamX = in.nextInt();
    int teamY = in.nextInt();
    winningTeam(teamX, teamY);
   }
  }
  
 }
 
 public static void addPlayer(int power, int teamNumber){
  teams[teamNumber].add(power);
  reverseSort(teamNumber);
 }
 
 public static void reverseSort(int teamNumber){
  Collections.sort(teams[teamNumber].players,Collections.reverseOrder());
 }
 
 
 public static void winningTeam(int teamX , int teamY){
 
  List<Integer> x = new ArrayList<Integer>();
  List<Integer> y = new ArrayList<Integer>();
  x.addAll(teams[teamX].players);
  y.addAll(teams[teamY].players);
  
  boolean xTurn = true;
  while(x.isEmpty() || y.isEmpty()){
   
   if(xTurn){
    
    int power = x.get(0);
    
    for(int i = 0; i < power ; i++){
     if(y.isEmpty()){
      break;
     }
     boolean removed = false;
     for(int j =0 ; j< y.size(); j++){
      if(power >= y.get(j)){
       y.remove(j);
       removed = true;
       break;
      }
     }
     
     if(removed = false){
      break;
     }
     
     
    }
    
    
   }else{
    int power = y.get(0);
    
    for(int i = 0; i < power ; i++){
     if(x.isEmpty()){
      break;
     }
     boolean removed = false;
     for(int j =0 ; j< x.size(); j++){
      if(power >= x.get(j)){
       x.remove(j);
       removed = true;
       break;
      }
     }
     
     if(removed = false){
      break;
     }
    }
    
   }
   xTurn = !xTurn;
   
  }
  
  if(x.isEmpty()){
   System.out.println(teamY);
  }else{
   System.out.println(teamX);
  }
 }
 

}

class Team {
 public List<Integer> players = new ArrayList<Integer>();
 void add(int x){
  players.add(x);
 }
}