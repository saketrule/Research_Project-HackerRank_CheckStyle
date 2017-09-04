import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
        String[] input = in.nextLine().split(" ");
        int numOfFighters = Integer.parseInt(input[0]);
        int numOfTeams = Integer.parseInt(input[1]);
        int numOfQueries = Integer.parseInt(input[2]);
        
        Map<Integer, Team> teams = new HashMap<Integer, Team>();
        for(int i=1; i<=numOfTeams; i++){
         teams.put(i, new Team());
        }
        
        for(int i = 0; i < numOfFighters; i++){
         input = in.nextLine().split(" ");
         teams.get(Integer.parseInt(input[1])).fighters.add(Integer.parseInt(input[0])); 
        }
        
        int[][] queries = new int[numOfQueries][3];
        for(int i = 0; i < numOfQueries; i++){
         input = in.nextLine().split(" ");
         queries[i][0] = Integer.parseInt(input[0]);
         queries[i][1] = Integer.parseInt(input[1]);
         queries[i][2] = Integer.parseInt(input[2]);
        }
        
        for(int i=0 ; i < queries.length; i++){
         if(queries[i][0] == 1){
          teams.get(queries[i][2]).fighters.add(queries[i][1]);
         }
         if(queries[i][0] == 2){
          List<Integer> xfighters = teams.get(queries[i][1]).fighters;
          
          int max = xfighters.isEmpty() ? 0 : Collections.max(xfighters);
          
          List<Integer> yfighters = teams.get(queries[i][2]).fighters;
          for(int fight=0; fight < max && !yfighters.isEmpty(); fight++){
           yfighters.remove(Collections.max(yfighters));
          }
          if(xfighters.size() < yfighters.size() || max == 0)
           System.out.println(queries[i][2]);
          else
           System.out.println(queries[i][1]);
         }
        }
    }

 private static class Team{
  ArrayList<Integer> fighters = new ArrayList<Integer>();
 }
}