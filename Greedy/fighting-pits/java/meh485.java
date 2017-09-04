import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
      Scanner scanner = new Scanner(System.in);
      int numFighters = scanner.nextInt();
      int numTeams = scanner.nextInt();
      int numQueries = scanner.nextInt();
      Team[] teams = new Team[numTeams];
      for (int i = 0; i<numTeams;i++){
        teams[i] = new Team();
        teams[i].setTeamNumber(i+1);
      }
      for (int i = 0; i < numFighters; i++){
        int s = scanner.nextInt();
        int t = scanner.nextInt();
        Fighter f = new Solution.Fighter(s);
        teams[t-1].addFighter(f);
      }
/*      for (int i=0;i<teams.length;i++){
          Iterator<Fighter> iterator = teams[i].fighters.iterator();
          while(iterator.hasNext()){
            System.out.println("Team " + teams[i].getTeamNumber() + " " + iterator.next().getStrength());
          }
      }
*/
      for (int i=0;i<numQueries;i++){
        int query = scanner.nextInt();
        if (query == 1){
          int p = scanner.nextInt();
          int x = scanner.nextInt();
          teams[x-1].addFighter(new Fighter(p));
        }
        else if (query == 2){
          int x = scanner.nextInt();
          int y = scanner.nextInt();
          int winningTeam = fight(teams[x-1],teams[y-1]);
          System.out.println(winningTeam);
        }
      }
      scanner.close();
    }
    public static int fight(Team teamX, Team teamY){
      Team x = teamX.clone();
      Team y = teamY.clone();
      while(x.size() > 0 && y.size() > 0){
        for (int i=0;i<x.getStrongestFighter().getStrength();i++){
          y.killFighter(y.getStrongestFighter());
        }
        Team temp = x;
        x = y;
        y = temp;
      }
      if (x.size() > 0)
        return x.getTeamNumber();
      else
        return y.getTeamNumber();
    }
    protected static class Team implements Cloneable{
      public void addFighter(Fighter f){
        if (fighters.size() == 0)
          fighters.add(f);
        else if (fighters.get(0).getStrength() > f.getStrength())
          fighters.add(0, f);
        else if (fighters.get(fighters.size()-1).getStrength() < f.getStrength())
          fighters.add(fighters.size(),f);
        else{
          int i = 0;
          while (fighters.get(i).getStrength() < f.getStrength()){
            i++;
          }
          fighters.add(i, f);
        }
      }
      public void killFighter(Fighter f){
        fighters.remove(f);
      }
      public int size(){
        return fighters.size();
      }
      public Fighter getStrongestFighter(){
        return fighters.getLast();
      }
      protected Team clone(){
        Team t = new Team();
        t.setTeamNumber(this.getTeamNumber());
        Iterator<Fighter> iterator = fighters.iterator();
        while (iterator.hasNext()){
          t.addFighter(iterator.next());
        }
        return t;
      }
      public int getTeamNumber(){return teamNumber;}
      public void setTeamNumber(int teamNumber){
        this.teamNumber = teamNumber;
      }
      private LinkedList<Fighter> fighters = new LinkedList<Fighter>();
      int teamNumber;
    }
    protected static class Fighter implements Cloneable{
      public Fighter(int strength){
        try{
          this.setStrength(strength);
        }
        catch(Exception e){}
      }
      public int getStrength(){return strength;}
      public void setStrength(int strength) throws Exception{
        if (strength >= 1 && strength <= 2*Math.pow(10, 5)){
          this.strength = strength;
        }
        else{
          throw new Exception();
        }
      }
      public Fighter clone(){
        Fighter f = new Fighter(this.strength);
        return f;
      }
      private int strength;
    }
}