import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        readInput();
    }
    
    public static void readInput(){
  Scanner in = new Scanner(System.in);
  int cases = Integer.valueOf(in.nextLine().trim());
  for(int i = 0; i< cases; i++){
   String[] val = in.nextLine().trim().split(" ");
   int size = Integer.valueOf(val[0]);
   int[] a = new int[size];
   for(int j = 0; j<size; j++){
    a[j] = Integer.valueOf(val[j+1]);
   }
   sort(a, 0, a.length - 1);
   if(a.length == 0){
    System.out.println(0);
   }else{
    calc1(a);
   }
   
  }
 }
 
 public static void sort(int[] a, int p,int r){
  if(p<r){
   int q = (p+r)/2;
   sort(a, p, q);
   sort(a, q+1, r);
   merge(a, p, q, r);
  }
 }
 
 public static void merge(int[] a, int p , int q, int r){
  int n1 = q-p+1;
  int n2 = r-q;
  
  int[] l = new int[n1+1];
  int[] m = new int[n2+1];
  
  for(int i =0; i<n1; i++){
   l[i] = a[p+i];
  }
  
  for(int i = 0; i<n2; i++){
   m[i] = a[i+ q +1];
  }
  
  l[n1] = Integer.MAX_VALUE;
  m[n2] = Integer.MAX_VALUE;
  
  int j = 0;
  int k = 0;
  
  for(int i = p; i<=r; i++){
   if(l[j]<m[k]){
    a[i] = l[j];
    j++;
   }else{
    a[i] = m[k];
    k++;
   }
  }
 }
 
 public static void calc1(int[] a){
  
  List<List<Integer>> teams = new ArrayList<>();
  List<Integer> team = new ArrayList<>();
  int index = 0;
  team.add(a[0]);
  teams.add(team);
  for(int i = 1; i<a.length; i++){
   if(a[i] == a[i-1] + 1){
    index = teams.size() - 1;
    team = teams.get(index);
    team.add(a[i]);
   }else if(a[i] == a[i-1]){
    if(index == 0){
     team = new ArrayList<>();
     team.add(a[i]);
     teams.add(team);
    }else{
     team = teams.get(index-1);
     if(team.get(team.size() - 1) == a[i] - 1){
      team.add(a[i]);
      index = index -1;
     }else{
      team = new ArrayList<>();
      team.add(a[i]);
      teams.add(team);
     }
    }
   }else{
    team = new ArrayList<>();
    team.add(a[i]);
    teams.add(team);
   }
  }
  
  int minSize = Integer.MAX_VALUE;
  for(int i = 0; i<teams.size(); i++){
   if(minSize > teams.get(i).size()){
    minSize = teams.get(i).size();
   }
  }
  
  System.out.println(minSize);
 }
}