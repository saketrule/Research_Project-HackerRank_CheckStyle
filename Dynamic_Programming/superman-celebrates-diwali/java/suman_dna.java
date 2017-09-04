import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

     public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);

  //String line = sc.nextLine();
  //String split[] = line.split(" ");
  
  int b = sc.nextInt();
  int floor = sc.nextInt();
  int I = sc.nextInt();

  int people[][] = new int[b+1][floor+1];

  for (int i = 1; i <= b; i++) {
   int numP = sc.nextInt();
   for (int j = 1; j <= numP; j++) {
    people[i][sc.nextInt()] += 1;
   }
  }

  int sol[][] = new int[b+1][floor+1];

  int max [] = new int[floor+1];
  for (int j = 1; j <= floor; j++) {
   for (int i = 1; i <= b; i++) {
    if (j < I) {
     sol[i][j]=people[i][j]+sol[i][j-1];
     max[j]=Math.max(max[j], sol[i][j]);
    }
    if(j-I>=0){
     sol[i][j]=sol[i][j-1];
     sol[i][j]=Math.max(max[j-I], sol[i][j]);
     sol[i][j]+=people[i][j];
     max[j]=Math.max(sol[i][j],max[j]);
    }
   }
  }
  
  int numP=0;
  
  for(int i=1;i<=b;i++){
   numP = Math.max(numP, sol[i][floor]);
  }

  System.out.println(numP);
 }
}