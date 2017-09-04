import java.util.*;
import java.math.*;
public class Solution {
 public static void main(String[] args){
  Scanner scan = new Scanner(System.in);
  int trial = Integer.parseInt(scan.next());
  for (int i=0;i<trial;i++){
   int no = Integer.parseInt(scan.next());
   int[] l1 = new int[no];
   for (int u=0;u<no;u++){
    l1[u]=Integer.parseInt(scan.next());
   }
   BigInteger fin = BigInteger.ONE;
   //System.out.println("fin1:"+fin);
   for (int u=0;u<no;u++){
    fin = fin.multiply(tree(l1[u]));
   }
   BigInteger temp2 = new BigInteger(""+1);
   BigInteger temp3 = new BigInteger(""+0);
   for (int u=0;u<no;u++){
    temp2 = temp2.multiply(new BigInteger(l1[u]+""));
    temp3 = temp3.add(new BigInteger(l1[u]+""));
   }
   if (no>1){
    temp3=temp3.pow(no-2);
    temp2=temp2.multiply(temp3);
    fin= fin.multiply(temp2);
   }
   
   //System.out.println("temp2: "+temp2);
   
   
   System.out.println(fin.mod(new BigInteger(""+1000000007)));
  }
 }
 public static BigInteger tree(int n1){
  if (n1<=2){
   return BigInteger.ONE;
  }
  else {
   BigInteger k1 = new BigInteger(""+n1);
   //System.out.println("k1: "+k1);
   k1=k1.pow(n1-2);
   //k1.mod(new BigInteger(""+1000000007));
   //System.out.println("k1: "+k1);
   return k1;
  }
 }
 public static BigInteger edge(int n1){
  BigInteger fin = new BigInteger(n1+"");
  fin = fin.pow(n1-2);
  fin = fin.multiply(new BigInteger(""+(n1-1)));
  fin = fin.multiply(new BigInteger(""+2));
  fin = fin.divide(new BigInteger(""+n1));
  return fin;
 }
}