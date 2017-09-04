import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class Solution {

 public class DegreeSeq implements Comparable<DegreeSeq>{
  int [] seq;
  public DegreeSeq(int [] s) {
   seq = s;
  }
  public int compareTo(DegreeSeq d) {
   for(int i = 0 ; i < seq.length ; i++) {
    int comp = new Integer(seq[i]).compareTo(d.seq[i]);
    if(comp!=0) {
     return comp;
    }
   }
   return 0;
  }
 }

 public class Times {
  long times;
  public Times(long t ) {
   times = t;
  }
 }

 static long sp ;

 static Solution main;

 public static void main(String [] args ) {
  sp = 1000000007;
  main = new Solution();
  ArrayList<TreeMap<DegreeSeq,Times>> sequences = new ArrayList<TreeMap<DegreeSeq,Times>>(10);
  for(int i = 0 ; i < 10 ; i++) {
   sequences.add(new TreeMap<DegreeSeq,Times>());
  }
  int [] ar = new int[1];
  ar[0] = 0;
  sequences.get(0).put(main.new DegreeSeq(ar),main.new Times(1));
  for(int i = 1 ; i < 10 ; i++) {
   Iterator<DegreeSeq> iterator = sequences.get(i-1).keySet().iterator();
   while(iterator.hasNext()) {
    DegreeSeq next = iterator.next();
    Times times =sequences.get(i-1).get(next);
    int [] old = next.seq;
    for(int j = 0 ; j < i ; j++) {   
     for(int p = 0 ; p <= i ; p++) {
      int [] newSeq = new int[i+1];
      for(int k = 0 ; k<=i ;k++) {
       newSeq[k] = 0;
      }
      if(j<p) {
       newSeq[j]++;
      } else {
       newSeq[j+1]++;
      }
      newSeq[p] += 1;
      for(int k = 0 ; k < p ; k++) {
       newSeq[k] += old[k];
      }
      boolean valid = true;
      for(int k = p ; k < i ; k++) {
       newSeq[k+1] += old[k];
       if(newSeq[k+1]==1) {
        valid= false;
       }
      }
      if(valid) {
       DegreeSeq mySeq = main.new DegreeSeq(newSeq);
       Times t = sequences.get(i).get(mySeq);
       if(t==null) {
        sequences.get(i).put(mySeq, main.new Times(times.times) );
       } else {
        sequences.get(i).put(mySeq,main.new Times(t.times+times.times));
       }
      }
     }
    }
   }
  }
  try{
   String str;   
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   BufferedOutputStream bos = new BufferedOutputStream(System.out);
   String eol = System.getProperty("line.separator");
   byte [] eolb = eol.getBytes();
   str  = br.readLine();
   int test = Integer.parseInt(str);
   for(int i = 0 ; i < test ; i++) {
    str  = br.readLine();
    int n = Integer.parseInt(str);
    int [] beads = new int[n];
    int j=0;
    int s=0;
    int k =0;
    str = br.readLine();
    int length = str.length();
    while(j<length) {
     while(j<length) {
      if(str.charAt(j) == ' ') {
       break;
      }else {
       j++;
      }
     }
     beads[k] = Integer.parseInt(str.substring(s,j)) ;
     k++;
     j++;
     s=j;   
    }
    long ans = 1;
    for(int p = 0 ; p < n ; p++) {
     ans *= getTrees(beads[p]);
     ans %= sp;
    }
    long sum = 0;
    Iterator<DegreeSeq> iterator = sequences.get(n-1).keySet().iterator();
    while(iterator.hasNext()){
     DegreeSeq dSeq = iterator.next();
     long pr = 1;
     for(int q = 0 ; q < n ; q++) {
      long temp = getPow(beads[q],dSeq.seq[q]);
      pr *= temp;
      pr %= sp;
     }
     pr *= sequences.get(n-1).get(dSeq).times;
     pr %= sp;
     sum += pr;
     sum %= sp;
    }
    ans *= sum;
    ans%= sp;
    bos.write(new Long(ans).toString().getBytes());
    bos.write(eolb);
   }
   bos.flush();
  }  catch(IOException ioe) {
   ioe.printStackTrace();
  }
 }

 public static long getTrees(int a ) {
  return getPow(a,a-2);
 }

 public static long getPow(int a , int b) {
  long ans = 1;
  long pow = a;
  while(b>0) {
   if((b%2)==1) {
    ans*=pow;
    ans%=sp;
   }
   pow *= pow;
   pow %= sp;
   b/=2;
  }
  return ans;
 }
}