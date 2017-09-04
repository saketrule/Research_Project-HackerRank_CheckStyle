import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Solution {

 public class Times {
  long times;
  public Times(long l) {
   times = l;
  }
 }
 public class Problem implements  Comparable<Problem>{
  int m;
  int lastFreeX ;
  int lastFreeY ;
  int [] rep ;
  char[][] array ;
  public Problem(int mm ,int lx , int ly) {
   m = mm;
   rep = new int[m];
  }
  public void setArray(char[][] ar,int n) {
   array = new char[m][n];
   for(int i = 0 ; i < m ; i++) {
    for(int j = 0 ; j < n ; j++) {
     array[i][j] = ar[i][j];
    }
   }
  }
  public void makeRep(char[][]array) {
   for(int i = 0 ; i < m ; i++) {
    rep[i] = Integer.valueOf(new String(array[i]), 2);
   }
  }
  public int compareTo(Problem p ){
   for(int j = 0 ; j < m ; j++) {
    int x = 0;
    if(rep[j] < p.rep[j]) {
     x = -1;
    }else if(rep[j]>p.rep[j]) {
     x = 1;
    }
    if(x!=0) {
     return x;
    }
   }
   return 0;
  }
 }
 public static void main(String[] args) throws Exception {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  BufferedOutputStream bos = new BufferedOutputStream(System.out);
  long specialNumber = 1000000007l;
  String eol = System.getProperty("line.separator");
  byte [] eolb = eol.getBytes();
  Solution mainObject = new Solution();
  int [] parity = new int[20];
  parity[0] = 1;
  parity[1] = 2;
  parity[2] = 4;
  parity[3] = 8;
  parity[4] = 16;
  parity[5] = 32;
  parity[6] = 64;
  parity[7] = 128;
  parity[8] = 256;
  parity[9] = 512;
  parity[10] = 1024;
  parity[11] = 2048;
  parity[12] = 4096;
  parity[13] = 8192;
  parity[14] = 16384;
  parity[15] = 32768;
  parity[16] = 65536;
  parity[17] = 131072;
  parity[18] = 262144;
  parity[19] = 524288;
  try {
   String str = br.readLine();
   int test = Integer.parseInt(str);
   int blank , n , m , j , k,empty ,iter,size,firstR,firstC,p;
   char[] temp;
   char[][] probArray;
   TreeMap<Problem, Times> oldMap,newMap;
   Problem prob;
   Iterator<Entry<Problem, Times>> iterator;
   boolean finish;
   Entry<Problem,Times> entry;
   Times times,l;
   final char zero = '0';
   final char one = '1';
   final char hash = '#';
   final String space = " ";
   for(int i = 0 ; i < test ; i++) {
    str = br.readLine();
    blank = str.indexOf(space);
    n = Integer.parseInt(str.substring(0,blank));
    m = Integer.parseInt(str.substring(blank+1));
    probArray = new char[m][n];
    for(j = 0 ; j < n ; j++) {
     str = br.readLine();
     temp = str.toCharArray();
     for(k = 0 ; k < m ; k++) {
      if(temp[k]==hash) {
       probArray[k][j] =  zero;
      } else {
       probArray[k][j] = one;
      }
     }
    }
    oldMap = new TreeMap<Problem,Times>();
    prob = mainObject.new Problem(m,0,0);
    prob.makeRep(probArray);
    prob.setArray(probArray, n);
    oldMap.put(prob, mainObject.new Times(1));
    empty = 0;
    for(j = 0 ; j < m ; j++) {
     for(k = 0 ; k < n ; k++) {
      if(probArray[j][k] == one) {
       empty++;
      }
     }
    }
    if((empty%4) ==0) {
     empty /= 4;
     iter = 0;
     while(iter < empty) {
      newMap = new TreeMap<Problem,Times>();
      iterator = oldMap.entrySet().iterator();
      size = oldMap.size();
      for(j = 0 ; j < size ; j++) {
       finish = false;
       entry = iterator.next();
       prob = entry.getKey();
       probArray = prob.array;
       times = entry.getValue();
       firstR = prob.lastFreeX;
       firstC = prob.lastFreeY;
       while(!finish) {
        if(probArray[firstC][firstR] == one) {
         Problem newProb ;
         if( (firstC > 0) && (firstR <(n-2))) {
          if( (probArray[firstC][firstR+1] == one) &&  (probArray[firstC][firstR+2] == one ) && ( probArray[firstC-1][firstR+2]==one)) {
           newProb = mainObject.new Problem(m,firstR,firstC);
           for(p = 0 ; p < m ; p++) {
            newProb.rep[p] = prob.rep[p];
           }
           newProb.rep[firstC] -= (parity[n-firstR-1] + parity[n-2-firstR] + parity[n-3-firstR]);
           newProb.rep[firstC-1] -= (parity[n-3-firstR]);
           l = newMap.get(newProb);
           if(l==null) {
            newMap.put(newProb, mainObject.new Times(times.times));
            newProb.setArray(probArray, n);
            newProb.array[firstC][firstR] = zero;
            newProb.array[firstC][firstR+1] = zero;
            newProb.array[firstC][firstR+2] = zero;
            newProb.array[firstC-1][firstR+2] = zero;
           } else {
            l.times = (times.times+l.times)%specialNumber;
           }
          }
         }
         if( (firstC > 1) && (firstR <(n-1))) {
          if( (probArray[firstC][firstR+1] == one) &&  (probArray[firstC-1][firstR+1] == one ) && ( probArray[firstC-2][firstR+1]==one)) {
           newProb = mainObject.new Problem(m,firstR,firstC);
           for(p = 0 ; p < m ; p++) {
            newProb.rep[p] = prob.rep[p];
           }
           newProb.rep[firstC-2] -= parity[n-2-firstR];
           newProb.rep[firstC-1] -= parity[n-2-firstR];
           newProb.rep[firstC] -= (parity[n-1-firstR]+parity[n-2-firstR]);
           l = newMap.get(newProb);
           if(l==null) {
            newMap.put(newProb, mainObject.new Times(times.times));
            newProb.setArray(probArray, n);
            newProb.array[firstC][firstR] = zero;
            newProb.array[firstC][firstR+1] = zero;
            newProb.array[firstC-1][firstR+1] = zero;
            newProb.array[firstC-2][firstR+1] = zero;
           } else {
            l.times = (times.times+l.times)%specialNumber;
           }
          }
         }
         if(firstC < (m-1)) {
          if(firstR < (n-2)) {
           if( (probArray[firstC+1][firstR] == one) &&  (probArray[firstC+1][firstR+1] == one ) && ( probArray[firstC+1][firstR+2]==one)) {
            newProb = mainObject.new Problem(m,firstR,firstC);
            for(p = 0 ; p < m ; p++) {
             newProb.rep[p] = prob.rep[p];
            }
            newProb.rep[firstC+1] -= (parity[n-firstR-1]+parity[n-2-firstR]+parity[n-3-firstR]);
            newProb.rep[firstC] -= parity[n-1-firstR];
            l = newMap.get(newProb);
            if(l==null) {
             newMap.put(newProb, mainObject.new Times(times.times));
             newProb.setArray(probArray, n);
             newProb.array[firstC][firstR] = zero;
             newProb.array[firstC+1][firstR] = zero;
             newProb.array[firstC+1][firstR+1] = zero;
             newProb.array[firstC+1][firstR+2] = zero;
            } else {
             l.times = (times.times+l.times)%specialNumber;
            }
           }
           if( (probArray[firstC+1][firstR] == one) && (probArray[firstC][firstR+1] ==one)  && ( probArray[firstC][firstR+2]==one)) {
            newProb = mainObject.new Problem(m,firstR,firstC);
            for(p = 0 ; p < m; p++) {
             newProb.rep[p] = prob.rep[p];
            }
            newProb.rep[firstC] -= (parity[n-1-firstR]+parity[n-2-firstR]+parity[n-3-firstR]);
            newProb.rep[firstC+1] -= parity[n-1-firstR];
            l = newMap.get(newProb);
            if(l==null) {
             newMap.put(newProb, mainObject.new Times(times.times));
             newProb.setArray(probArray, n);
             newProb.array[firstC][firstR] = zero;
             newProb.array[firstC+1][firstR] = zero;
             newProb.array[firstC][firstR+1] = zero;
             newProb.array[firstC][firstR+2] = zero;
            } else {
             l.times = (times.times+l.times)%specialNumber;
            }
           }
           if( (probArray[firstC] [firstR+1]== one) && (probArray[firstC][firstR+2] ==one)  && ( probArray[firstC+1][firstR+2]==one)) {
            newProb = mainObject.new Problem(m,firstR,firstC);
            for(p = 0 ; p < m ; p++) {
             newProb.rep[p] = prob.rep[p];
            }
            newProb.rep[firstC] -= (parity[n-1-firstR]+parity[n-2-firstR]+parity[n-3-firstR]);
            newProb.rep[firstC+1] -= parity[n-3-firstR];
            l = newMap.get(newProb);
            if(l==null) {
             newMap.put(newProb, mainObject.new Times(times.times));
             newProb.setArray(probArray, n);
             newProb.array[firstC][firstR] = zero;
             newProb.array[firstC][firstR+1] = zero;
             newProb.array[firstC][firstR+2] = zero;
             newProb.array[firstC+1][firstR+2] = zero;
            } else {
             l.times = (times.times+l.times)%specialNumber;
            }
           }
          }
          if( ( firstC < (m-2)) && ( firstR < (n-1)) ) {
           if( (probArray[firstC+1][firstR] == one) && (probArray[firstC+2][firstR] ==one) && (probArray[firstC][firstR+1] == one ) ) {
            newProb = mainObject.new Problem(m,firstR,firstC);
            for(p = 0 ; p < m ; p++) {
             newProb.rep[p] = prob.rep[p];
            }
            newProb.rep[firstC] -= (parity[n-1-firstR]+parity[n-2-firstR]);
            newProb.rep[firstC+1] -= parity[n-1-firstR];
            newProb.rep[firstC+2] -= parity[n-1-firstR];
            l = newMap.get(newProb);
            if(l==null) {
             newMap.put(newProb, mainObject.new Times(times.times));
             newProb.setArray(probArray, n);
             newProb.array[firstC][firstR] = zero;
             newProb.array[firstC+1][firstR] = zero;
             newProb.array[firstC+2][firstR] = zero;
             newProb.array[firstC][firstR+1] = zero;
            } else {
             l.times = (times.times+l.times)%specialNumber;
            }
           }
           if( (probArray[firstC+1][firstR+1] == one) && (probArray[firstC][firstR+1] ==one) && (probArray[firstC+2][firstR+1] == one ) ) {
            newProb = mainObject.new Problem(m,firstR,firstC);
            for(p = 0 ; p < m ; p++) {
             newProb.rep[p] = prob.rep[p];
            }
            newProb.rep[firstC] -= (parity[n-1-firstR]+parity[n-2-firstR]);
            newProb.rep[firstC+1] -= parity[n-2-firstR];
            newProb.rep[firstC+2] -= parity[n-2-firstR];
            l = newMap.get(newProb);
            if(l==null) {
             newMap.put(newProb, mainObject.new Times(times.times));
             newProb.setArray(probArray, n);
             newProb.array[firstC][firstR] = zero;
             newProb.array[firstC+1][firstR+1] = zero;
             newProb.array[firstC][firstR+1] = zero;
             newProb.array[firstC+2][firstR+1] = zero;
            } else {
             l.times = (times.times+l.times)%specialNumber;
            }
           }
           if( (probArray[firstC+1][firstR] == one) && (probArray[firstC+2][firstR] ==one) && (probArray[firstC+2][firstR+1] == one ) ) {
            newProb = mainObject.new Problem(m,firstR,firstC);
            for(p = 0 ; p < m ; p++) {
             newProb.rep[p] = prob.rep[p];
            }
            newProb.rep[firstC] -= parity[n-1-firstR];
            newProb.rep[firstC+1] -= parity[n-1-firstR];
            newProb.rep[firstC+2] -= (parity[n-1-firstR]+parity[n-2-firstR]);
            l = newMap.get(newProb);
            if(l==null) {
             newMap.put(newProb, mainObject.new Times(times.times));
             newProb.setArray(probArray, n);
             newProb.array[firstC][firstR] = zero;
             newProb.array[firstC+1][firstR] = zero;
             newProb.array[firstC+2][firstR] = zero;
             newProb.array[firstC+2][firstR+1] = zero;
            } else {
             l.times = (times.times+l.times)%specialNumber;
            }
           }
          }
         }
         finish = true;
        }
        firstC++;
        if(firstC == m) {
         firstC = 0;
         firstR++;
        }
        if(firstR == n) {
         finish = true;
        }
       }
      }
      iter++;
      oldMap = newMap;
     }
     if(oldMap.values().size()>0) {
      bos.write(new Long(oldMap.values().iterator().next().times).toString().getBytes());
      bos.write(eolb);
     } else {
      bos.write(new Long(0).toString().getBytes());
      bos.write(eolb);
     }
    } else {
     bos.write(new Long(0).toString().getBytes());
     bos.write(eolb);
    }

   }
   bos.flush();
  } catch(IOException ioe ) {
   ioe.printStackTrace();
  }
 }
}