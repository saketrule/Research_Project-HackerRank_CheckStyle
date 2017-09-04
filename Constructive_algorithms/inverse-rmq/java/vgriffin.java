import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 // number of leaf elements
 int n;
 
 // space for array
 int[] inVals;
    Scanner in;
    
    // levels for output array, including leaves
    int levels = 0;
    int[][] aLevels;
    
    static final int MAX_LEVELS = 19;
 
 // read size and create empty array
 public Solution(Scanner in) {
  this.in = in;
  n = in.nextInt();
  inVals = new int[2 * n - 1];
 }
 
 // read scrambled array and print results to System.out
 public boolean solve() {
  // read and sort values (do a quick return if there's just a single
  // element)
  for (int i1 = 0; i1 < inVals.length; i1++) {
   inVals[i1] = in.nextInt();
  }
  
  if (n == 1) {
   levels = 1;
   aLevels = new int[1][1];
   aLevels[0][0] = inVals[0];
   return true;
  }
  Arrays.sort(inVals);
  
  // the nodes require a level for the powers of 2 in the leaf portion
  // the leaves are also a level. if there are 2^k leaves, we'll have
  // levels 0 - k, where level 0 is the root and level k contains leaves
  // that are not nodes.
  //
  // build input levels, using the number of times we find the value to
  // determine the level. if we get an index out of range, we don't have
  // the right number of nodes at some level.
  //
  // the input levels only include values that aren't closer to the root
  //
  // the root value must be the minimum value in the entire array. It
  // it should be the first value in each level. we assume the first
  // value is the root and allocate the levels correspondingly. building
  // the levels will fail if it's not.
  
  int prevVal = inVals[0];   // to count matches
  int cnt = 0;      // matches for char
  int currIn = 0;      // next char to look at
  
  while (currIn < inVals.length && inVals[currIn] == prevVal) {
   currIn++;
   cnt++;
  }
  
  if (cnt > MAX_LEVELS || cnt == 1)
   return false;
  
  levels = cnt;     // remember how many we've got
  aLevels = new int[levels][];
  aLevels[0] = new int[1];  // root node
  aLevels[0][0] = prevVal;
  
  int size = 1;     // new elements in next level
  for (int i1 = 1; i1 < levels; i1++) {
   size *= 2;
   aLevels[i1] = new int[size];
  }
  
  if (size != n)
   return false;    // not valid root entry
  
  // now, fill in the remaining input levels
  // initially, we only fill in new nodes at each level.
  
  // because the non-root nodes may not be in order in the unscrambled
  // table, we'll use a (sorted) linked list for those entries.
  // (the code is optimized to assume leaf-only entries are mostly sorted
  // in the unscrambled table)
  
  LinkedList<Integer>[] inLevels = new LinkedList[levels - 1]; // no root
  for (int i1 = 0; i1 < levels - 1; i1++)
   inLevels[i1] = new LinkedList<Integer>();
  
  try {
   while (currIn < inVals.length) {
    prevVal = inVals[currIn++];
    cnt = 1;
    while (currIn < inVals.length && inVals[currIn] == prevVal) {
     currIn++;
     cnt++;
    }
    inLevels[levels - cnt - 1].add(prevVal);
   }
  }
  catch (ArrayIndexOutOfBoundsException ex) {
   clearA();
   return false;
  }
  
  // make sure we've got the right number of nodes at each level
  // remember that inLevels only includes new entries and doesn't
  // include root
  
  for (int i1 = 0; i1 < levels - 1; i1++) {
   if (inLevels[i1].size() != aLevels[i1].length) {
    clearA();
    return false;
   }
  }
   
  // trickle values down the tree, filling in the even-numbered nodes
  // from upper levels. we're copying the parent (min) node to the left
  // side of its child-pair. Fill in the odd values from the linked lists
  // the root is already in place
  
  for (int to = 1; to < levels; to++) {
   int from = to - 1;
   for (int ixFrom = 0, ixTo = 0; ixFrom < aLevels[from].length;
     ixFrom++) {
    int val = aLevels[from][ixFrom];
    aLevels[to][ixTo++] = val;
    if (val < inLevels[from].getFirst()) {
     aLevels[to][ixTo++] = inLevels[from].removeFirst();
    } else {
     if (inLevels[from].getLast() > val) {
      // we know something will work
      Iterator<Integer> it = inLevels[from].iterator();
      while (true) {
       int nextVal = it.next();
       if (nextVal > val) {
        aLevels[to][ixTo++] = nextVal;
        it.remove();
        break;
       }
      }
     } else {
      clearA();   // didn't work
      return false;
     }
    }
   }
  }
  
  // if we're here, everything worked!
  
  return true;
 }
 
 // clear (invalidate) the tree stored in aLevels
 private void clearA() {
  levels = 0;     // invalidate
  aLevels = null;
 }
 
 public void printSoln(PrintStream out) {
  String sep = "";
  boolean first = true;
  
  for (int i1 = 0; i1 < levels; i1++) {
   for (int i2 = 0; i2 < aLevels[i1].length; i2++) {
    out.print(sep + aLevels[i1][i2]);
    if (first) {
     sep = " ";
     first = false;
    }
   }
  }
  out.println();
 }
 
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Solution soln = new Solution(in);
        if (soln.solve()) {
         System.out.println("YES");
         soln.printSoln(System.out);
        }
        else {
         System.out.println("NO");
        }
    }
}