import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
  
  static boolean[][] edg;
  static boolean[] seen;
  static int[] matched;
  static int iN,iM;
  
  static boolean hasNext(int hou) {
    for (int i=0;i<iN;i++) {
      if ((!seen[i]) && edg[i][hou]) {
        seen[i]=true;
        if ((matched[i]==-1) || hasNext(matched[i])) {
          matched[i]=hou;
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
  /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    Scanner scanner=new Scanner(System.in);
    iN=scanner.nextInt();
    iM=scanner.nextInt();
    int i,j;
    int[] cl0=new int[iN];
    int[] cl1=new int[iN];
    for (i=0;i<iN;i++) {
      cl0[i]=scanner.nextInt();
      cl1[i]=scanner.nextInt();
    }
    int[] ho0=new int[iM];
    int[] ho1=new int[iM];
    for (i=0;i<iM;i++) {
      ho0[i]=scanner.nextInt();
      ho1[i]=scanner.nextInt();
    }
    edg=new boolean[iN][iM];
    matched=new int[iN];
    for (i=0;i<iN;i++) {
      matched[i]=-1;
      for (j=0;j<iM;j++) {
        if ((cl0[i]<=ho0[j]) && (cl1[i]>=ho1[j])) {
          edg[i][j]=true;
        }
      }
    }
    int iRes=0;
    for (i=0;i<iM;i++) {
      seen=new boolean[iN];
      if (hasNext(i)) {
        iRes+=1;
      }
    }
    System.out.println(""+iRes);
  }
}