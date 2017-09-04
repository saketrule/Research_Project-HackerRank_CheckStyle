import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
 public static class House {
  protected int money;
  protected boolean visited;

  public House(int m) {
   money = m;
   visited = false;
  }

  public boolean isVisited() {
   return visited;
  }

  public boolean setVisit(boolean x) {
   boolean state = !(x ==visited);
   visited = x;
   return state;
  }

  public int getMoney() {
   return money;
  }
 }

 public static House[] house;
 public static boolean[][] roads;
 public static int maxMoney = 0;
 public static int counter = 0;
 public static HashSet<HashSet<Integer>> ways = new HashSet<HashSet<Integer>>();

 public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  int N = sc.nextInt();
  int M = sc.nextInt();
  house = new House[N];
  roads = new boolean[N][N];

  for (int i = 0; i < N; i++) {
   house[i] = new House(sc.nextInt());
  }
  for (int i = 0; i < M; i++) {
   int x = sc.nextInt() - 1;
   int y = sc.nextInt() - 1;
   roads[x][y] = true;
   roads[y][x] = true;
  }
  sc.close();
  for (int i = 0; i < N; i++) {
   HashSet<Integer> p = new HashSet<Integer>();
   p.add(i);
   colour(i, true);
   solve(house[i].getMoney(), p);
   colour(i, false);
  }
  System.out.println(maxMoney + " " + counter);
 }

 public static void colour(int i, boolean x, ArrayList<Integer> changes) {
  house[i].setVisit(x);
  if(x){
   for (int j = 0; j < roads.length; j++) {
    if (roads[i][j]) {
     if (house[j].setVisit(x)) {
      changes.add(j);
     }
    }
   }
  }else{
   for (int j = 0; j < changes.size(); j++) {
    house[changes.get(j)].setVisit(x);
   }
  }
  
 }

 public static void colour(int i, boolean x) {
  house[i].setVisit(x);
  for (int j = 0; j < roads.length; j++) {
   if (roads[i][j]) {
    house[j].setVisit(x);
   }
  }
 }

 public static void solve(int mon, HashSet<Integer> path) {
  if (mon > maxMoney) {
   maxMoney = mon;
   counter = 1;
   ways.add(new HashSet<Integer>(path));
  } else if (mon == maxMoney) {
   if (ways.add(new HashSet<Integer>(path))) {
    counter++;
   }
  }
  for (int i = 0; i < house.length; i++) {
   if (!house[i].isVisited() ) {
    path.add(i);
    ArrayList<Integer> changes = new ArrayList<Integer>(
      house.length);
    colour(i, true, changes);
    solve(mon + house[i].getMoney(), path);
    colour(i, false, changes);
    path.remove(i);
   }
  }
  
 }
}