/* Enter your code here. Read input from STDIN. Print output to STDOUT */
import java.util.Scanner;


public class Solution {

 public static void main (String args[]) {
  Scanner sc = new Scanner(System.in);
  String line=sc.nextLine();
  String[] nk=line.split(" ");
        int n=Integer.parseInt(nk[0]);
        int k=Integer.parseInt(nk[1]);
  long[] p  = new long[n];
        for (int i=0;i<n;i++) {
            line = sc.nextLine();
            p[i] = Long.parseLong(line);
        }
        

        long[] notUse = new long[n];
        long[] best = new long[n];
        notUse[0]=0;
        best[0]=p[0];
        long runningChain=p[0];
        int cL=1;
        for (int i=1;i<n;i++) {
         notUse[i]=best[i-1];
         best[i]=best[i-1];
         if (cL<k) {
          cL++;
          runningChain+=p[i];
          if (i-cL<0) {
           best[i]=runningChain;
          } else {
           best[i]=runningChain+notUse[i-cL];
          }
         } else {
          runningChain+=p[i];
          //Tricky part
          int bestCL=0;
          long bestRunningChain=0;
          for (int j=i-k;j<i;j++) {
              runningChain-=p[j];
              if (runningChain+notUse[j] > best[i]) {
               best[i]=runningChain+notUse[j];
               bestCL=i-j;
               bestRunningChain=runningChain;
              }
          }
          runningChain=bestRunningChain;
          cL=bestCL;
         }
         //System.out.println (notUse[i] + " " + best[i] + " " + cL + " " + runningChain);
        }
        System.out.println(best[n-1]);
 }
 
}