import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int[][] savep;
static int[][] np;
public static int savepeople(int N, int H, int I){
 int[] maxforfloor = new int[H+1];
 for(int floor = 1; floor <= H; floor++){
  for(int building = 1; building <= N; building++){
   if(floor == 1){
    savep[building][floor] = np[building][floor];
   }
   else if(floor < I){
    savep[building][floor] = savep[building][floor-1] + np[building][floor];
   }
   else{
    int maxp = 0;
    if(maxforfloor[floor-I] > maxp)
     maxp = maxforfloor[floor-I];
    if(savep[building][floor-1] > maxp)
     maxp = savep[building][floor-1];
    savep[building][floor] = maxp + np[building][floor];
   }
   if(maxforfloor[floor] < savep[building][floor])
    maxforfloor[floor] = savep[building][floor];
  } 
 }
 int val = 0;
 for(int l = 1; l <= N; l++){
  if(savep[l][H] > val)
   val = savep[l][H];
 }
 return val;
}

public static void main(String[] args) {
 // TODO Auto-generated method stub
 int N, H, I;
 Scanner sc = new Scanner(System.in);
 N = sc.nextInt();
 H = sc.nextInt();
 I = sc.nextInt();
 np = new int[N+1][H+1];
 savep = new int[N+1][H+1];
 for(int i = 1; i <= N; i++){
  int u = sc.nextInt();
  for(int j = 1; j <= u; j++){
   int t = sc.nextInt();
   np[i][t]++;
  }
 }
 int val = savepeople(N, H, I);
 System.out.println(val);
}
}