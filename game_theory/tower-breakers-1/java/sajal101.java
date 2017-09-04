import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
  int t = scan.nextInt();
  int numTowers,heightTower;
  while(t>0) {
   numTowers = scan.nextInt();
   heightTower = scan.nextInt();
   System.out.println(heightTower==1||numTowers%2==0?"2":"1");
   t--;
  }
    }
}