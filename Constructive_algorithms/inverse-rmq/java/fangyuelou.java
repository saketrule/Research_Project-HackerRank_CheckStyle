import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class InverseRMQ {
    
    private int sourceArrayLength = 0;
 private int [] shuffledST = null;
 
 public InverseRMQ(int sourceArrayLength, int [] shuffledST) {
  this.sourceArrayLength = sourceArrayLength;
  this.shuffledST = shuffledST;
 }
 
 private int leftChildIndex(int fatherIndex) {
  return 2*fatherIndex+1;
 }
 
 private int rightChildIndex(int fatherIndex) {
  return 2*fatherIndex+2;
 }
 
 private int levelCount(int level) {
  return (int)(Math.pow(2, level));
 }
 
 
 private Map<Integer, TreeSet<Integer>> getCount2Values(Map<Integer, Integer> value2Counts) {
  Map<Integer, TreeSet<Integer>> ret = new TreeMap<Integer, TreeSet<Integer>> (new Comparator<Integer> () {
   public int compare(Integer left, Integer right) {
    return right - left;
   }
  });
  for (Map.Entry<Integer, Integer> entry : value2Counts.entrySet()) {
   Integer value = entry.getKey();
   Integer count = entry.getValue();
   
   TreeSet<Integer> curValues = ret.get(count);
   if (curValues == null) {
    TreeSet<Integer> values = new TreeSet<Integer> ();
    values.add(value);
    ret.put(count, values);
   } else {
    curValues.add(value);
   }
  }
  return ret;
 }
 
 
 private int getBigger(int leftBrother, TreeSet<Integer> candidates) {
  int ret = leftBrother;
  for (Integer candidate : candidates) {
   if (candidate > leftBrother) {
    ret = candidate;
    candidates.remove(candidate);
    break;
   }
  }
  return ret;
 }
 
 public int [] constuctST() {
  int [] st = new int [2*sourceArrayLength-1];
  Map<Integer, Integer> value2Counts = new HashMap<Integer, Integer> ();
  for (int i=0; i<2*sourceArrayLength-1; i++) {
            int curValue = shuffledST[i];
            Integer count = value2Counts.get(curValue);
            if (count == null) {
                value2Counts.put(curValue, 1);
            } else {
                value2Counts.put(curValue, ++count);
            }
        }
  
  if (value2Counts.size() != sourceArrayLength) {//invalid st
   return null;
  }
  
  Map<Integer, TreeSet<Integer>> count2Values = getCount2Values(value2Counts);
  
  int index0 = 0;
  int stIndex = 0;
  int stHeight = (int)(Math.log(sourceArrayLength)/Math.log(2)) + 1;
  for (int level=0; level<stHeight; level++) {
   TreeSet<Integer> values = count2Values.get(stHeight-level);
   int curSize = values.size();
   if (curSize != levelCount(level)-levelCount(level-1)) {//invalid st
    return null;
   }
   
   for (int i=0; i<curSize; i++) {
    int leftBro = Integer.MIN_VALUE;
    if (index0 > 0) {
     leftBro = st[index0-1];
    }
    int curValue = getBigger(leftBro, values);
    if (curValue == leftBro) {//invalid st
     return null;
    }
    st[index0] = curValue;
    
    //fill next levels until lowest
    for (int index = leftChildIndex(index0); index < 2*sourceArrayLength-1; index = leftChildIndex(index)) {
     st[index] = curValue;
    }
    index0 = rightChildIndex(stIndex++);
   }
   if (!values.isEmpty()) {//invalid st
    return null;
   }
  }
  
  return st;
 }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int [] inputData = new int [2*n-1];
        for (int i=0; i<2*n-1; i++) {
            inputData[i] = in.nextInt();
        }
        InverseRMQ irmq = new InverseRMQ(n, inputData);
        int [] st = irmq.constuctST();
  if (st == null) {
   System.out.println("NO");
  } else {
   System.out.println("YES");
   for (int i=0; i<st.length; i++) {
    System.out.print(st[i] + " ");
   }
  }
    }
}