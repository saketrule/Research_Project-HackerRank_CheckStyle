import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

public class Solution {
 final static long[] nonCollinearPointsLongestWays = new long[16 + 1];
 static {
  nonCollinearPointsLongestWays[1] = 1;
  nonCollinearPointsLongestWays[2] = 1;
  nonCollinearPointsLongestWays[3] = 6;
  nonCollinearPointsLongestWays[4] = 6;
  for (int j = 5; j <= 16; j++) {
   nonCollinearPointsLongestWays[j] = (j * (j - 1)) / 2
     * nonCollinearPointsLongestWays[j - 2];
  }
 }

 static class Node {
  Node parent;
  List<LongLine> data = new ArrayList<LongLine>();
  List<Node> children = new ArrayList<Node>();
  int level;

  Node(final int level, final Node parent) {
   this.level = level;
   this.parent = parent;
  }

  List<Node> getChildren() {
   return children;
  }

  void add(final Node node) {
   children.add(node);
  }
 }

 static class LongLine {
  final int maxLen;
  final int[] pointIndexes;
  final double slope;
  final double intercept;
  int len;
  LongLine parentLine;

  public LongLine(final int[] pointIndexes, final double slope,
    final double intercept) {
   this.pointIndexes = pointIndexes;
   this.slope = slope;
   this.intercept = intercept;
   maxLen = pointIndexes.length;
   len = maxLen;
  }

  @Override
  public String toString() {
   return "len=" + maxLen + " m=" + slope + " b=" + intercept + " "
     + hashCode() + " parent="
     + (parentLine == null ? "" : "" + parentLine.hashCode());
  }

  final boolean isParentFor(final LongLine lineB) {
   if (lineB.parentLine == this) {
    return true;
   }
   if (lineB.maxLen > this.len) {
    return false;
   }

   if (slope != lineB.slope) {
    return false;
   }

   final int pointsCountB = lineB.pointIndexes.length;
   for (int i = 0; i < pointsCountB; i++) {
    final int foundIndex = Arrays.binarySearch(pointIndexes,
      lineB.pointIndexes[i]);
    if (foundIndex < 0) {
     return false;
    }
   }

   return true;
  }

  // i..e N=11, we have line len 4, line len 3, line len 3.
  // 11=4+3+3+1 OR 3+3+3+2 where line len 3 is one of 4 sublines of line
  // with len 4
  List<LongLine> getSubLines(final int len) {
   final List<LongLine> result = new ArrayList<LongLine>();
   final CombinationGenerator gen = new CombinationGenerator(
     pointIndexes.length, len);
   int[] indices;
   while (gen.hasMore()) {
    indices = gen.getNext();
    final int[] subLinePointIndexes = new int[len];
    for (int i = 0; i < indices.length; i++) {
     subLinePointIndexes[i] = pointIndexes[indices[i]];
    }
    final LongLine line = new LongLine(subLinePointIndexes,
      this.slope, this.intercept);
    line.parentLine = this;
    result.add(line);

   }
   return result;
  }

  public final boolean isIntersectsInAnyOurPointWith(final LongLine lineB) {
   final LongLine lineA = this;
   if (lineA == lineB || lineA == lineB.parentLine) {
    return true;
   }
   if (lineA.slope == lineB.slope) { // parallel
    return false;
   }
   if (lineA.parentLine != null
     && lineA.parentLine == lineB.parentLine) {
    return true;
   }

   final int pointsCountA = lineA.pointIndexes.length;
   for (int i = 0; i < pointsCountA; i++) {
    final int foundIndex = Arrays.binarySearch(lineB.pointIndexes,
      lineA.pointIndexes[i]);
    if (foundIndex >= 0) {
     return true;
    }
   }

   return false;
  }

  public final boolean isIntersectsInAnyOurPointWithAllLines(
    final List<LongLine> lines) {
   for (final LongLine lineB : lines) {
    if (!isIntersectsInAnyOurPointWith(lineB)) {
     return false;
    }
   }
   return true;
  }
 }

 public static void main(final String[] args) {
  final InputStreamReader isr = new InputStreamReader(System.in);
  final BufferedReader br = new BufferedReader(isr);
  final String newLine = System.getProperty("line.separator");
  try {
   String str = br.readLine();
   final int T = Integer.parseInt(str);
   if (T < 1 || T > 50) {
    System.err
      .println("T is not in [1,50] range.  Please try again.");
    return;
   }
   final StringBuffer buffer = new StringBuffer(6 * T);
   for (int i = 0; i < T; i++) {
    str = br.readLine();
    final int N = Integer.parseInt(str);
    if (N < 1 || N > 16) {
     System.err
       .println("N is not in [1,16] range.  Please try again.");
     return;
    }

    final byte[][] xy = new byte[N][2];
    for (int j = 0; j < N; j++) {
     str = br.readLine();
     final String[] strings = str.split(" ");
     if (strings.length != 2) {
      System.err.println("Enter " + 2 + " integers!");
      return;
     }

     final int x = Integer.parseInt(strings[0]);
     if (x < 0 || x > 100) {
      System.err
        .println("x["
          + j
          + "] is not in [0,100] range. Please try again.");
      return;
     }
     xy[j][0] = (byte) x;

     final int y = Integer.parseInt(strings[1]);
     if (y < 0 || y > 100) {
      System.err
        .println("y["
          + j
          + "] is not in [0,100] range. Please try again.");
      return;
     }
     xy[j][1] = (byte) y;
    }

    // printArray(xy);

    final ArrayList<LongLine> lines = new ArrayList<LongLine>();
    final String result = getResult(N, xy, lines);
    // System.out.println("RESULT:" + buffer + " i=" + i);
    buffer.append(result).append(newLine);
   }
   System.out.println(buffer);
  } catch (final Exception e) {
   e.printStackTrace();
  }
 }

 private static String getResult(final int N, final byte xy[][],
   final ArrayList<LongLine> lines) {
  final boolean[] removedIndexes = new boolean[N];
  final double slopes[][] = new double[N][N];
  final double intercepts[][] = new double[N][N];

  for (int i = 0; i < N; i++) {
   final int x1 = xy[i][0];
   final int y1 = xy[i][1];

   for (int j = i + 1; j < N; j++) {
    final int x2 = xy[j][0];
    final int y2 = xy[j][1];
    final int dx = x1 - x2;
    if (dx == 0) {
     slopes[i][j] = Double.NEGATIVE_INFINITY;
     intercepts[i][j] = x1;
    } else {
     slopes[i][j] = (y1 - y2) * 1.0 / dx;
     intercepts[i][j] = y1 - slopes[i][j] * x1;
    }
   }
  }

  int linesCounter = 0;// lines with more then 2 lines
  int longestLineLen;
  while ((longestLineLen = longest(xy, N, removedIndexes, slopes,
    intercepts, linesCounter, lines)) > 2) {
   linesCounter++;
   // System.out.println("longestLineLen=" + longestLineLen);
  }

  // printLines(lines, xy);

  int countOfPointsWhichAreLeft = 0;
  for (int i = 0; i < removedIndexes.length; i++) {
   if (!removedIndexes[i]) {
    countOfPointsWhichAreLeft++;
   }
  }
  final int bestNumberOfWays = (countOfPointsWhichAreLeft + 1) / 2
    + linesCounter;

  if (lines.size() == 0) {
   final long totalCombinations = nonCollinearPointsLongestWays[N];
   final String s = "" + bestNumberOfWays + " " + totalCombinations
     % 1000000007L;
   return s;
  }

  final ArrayList<Integer> input = new ArrayList<Integer>();
  for (final LongLine line : lines) {
   int i = line.maxLen;
   while (i > 2) {
    input.add(i);
    i--;
   }
  }
  input.add(2);
  input.add(2);
  input.add(2);
  input.add(2);
  input.add(2);
  input.add(1);

  // System.out.println(input);

  final ArrayList<ArrayList<Integer>> sumCombinations = sum_up(N,
    bestNumberOfWays, input);
  // System.out.println("SUM: Potential sums:");
  // for (final ArrayList<Integer> combination : sumCombinations) {
  // System.out.println(combination);
  // }
  // System.out.println();
  // System.out.println("SUM with multiplicators:");

  BigInteger totalCombinations = BigInteger.ZERO;
  for (final ArrayList<Integer> combination : sumCombinations) {
   final int pointsWhichAreLeft = getLeftPointsCount(combination, N);
   final long nonCollinearCombinations = nonCollinearPointsLongestWays[pointsWhichAreLeft];

   if (pointsWhichAreLeft == N) {
    totalCombinations = totalCombinations.add(BigInteger
      .valueOf(nonCollinearCombinations));
    continue;
   }
   final ArrayList<Integer> multiplicators = getActualPossibleCombination(
     combination, lines);
   if (multiplicators != null) {
    // System.out.println("sum:  " + combination);
    // System.out.println("mult: " + multiplicators);
    // System.out.println();

    BigInteger z1 = BigInteger.ONE;
    int i = 0;
    for (final Integer m : multiplicators) {
     z1 = z1.multiply(BigInteger.valueOf(m));
     i++;
    }
    final BigInteger f = factorial(combination.size()).divide(
      factorial(combination.size() - i)).divide(factorial(i));
    z1 = z1.multiply(f);
    z1 = z1.multiply(BigInteger.valueOf(nonCollinearCombinations));
    totalCombinations = totalCombinations.add(z1);
   }
  }
  final BigInteger remainder = totalCombinations.remainder(BigInteger
    .valueOf(1000000007L));
  // System.out.println("countOfPointsWhichAreLeft="
  // + countOfPointsWhichAreLeft + " shortest answer="
  // + bestNumberOfWays + " linesCounter=" + linesCounter
  // + " remainder=" + remainder);
  final String s = "" + bestNumberOfWays + " " + remainder.toString();
  return s;

 }

 private static ArrayList<LongLine> getLinesWithLen(
   final ArrayList<LongLine> lines, final int len) {
  final ArrayList<LongLine> result = new ArrayList<LongLine>(lines.size());
  for (final LongLine longLine : lines) {
   if (longLine.maxLen > len) {
    result.addAll(longLine.getSubLines(len));
   } else if (longLine.maxLen == len) {
    result.add(longLine);
   }
  }
  return result;
 }

 private static int getLeftPointsCount(final ArrayList<Integer> combination,
   final int N) {
  int s = 0;
  for (final Integer bucket : combination) {
   if (bucket > 2) {
    s += bucket;
   }
  }
  return N - s;
 }

 private static void buldPossibleCombinationsTree(
   final ArrayList<Integer> buckets, final ArrayList<LongLine> lines,
   final Node parent, final int level) {

  final Integer lenForBucket = buckets.get(level + 1);
  if (lenForBucket <= 2) {
   return;
  }

  final ArrayList<LongLine> linesForBucket = getLinesWithLen(lines,
    lenForBucket);
  for (final ListIterator<LongLine> lstIterator = linesForBucket
    .listIterator(); lstIterator.hasNext();) {

   final LongLine candidateLine = lstIterator.next();
   Node currentParent = parent;
   boolean isRemoved = false;
   while (true) {
    for (final LongLine parentLine : currentParent.data) {
     final boolean isFromSameParent = parentLine.parentLine == null ? false
       : parentLine.parentLine == candidateLine.parentLine;
     if (isFromSameParent || parentLine == candidateLine
       || parentLine.isParentFor(candidateLine)) {
      lstIterator.remove();
      isRemoved = true;
      break;
     }
    }

    if (!isRemoved
      && !currentParent.data.isEmpty()
      && candidateLine
        .isIntersectsInAnyOurPointWithAllLines(currentParent.data)) {
     lstIterator.remove();
    }
    currentParent = currentParent.parent;
    if (currentParent == null) {
     break;
    }
   }
  }
  final int newLevel = level + 1;
  for (final LongLine line : linesForBucket) {
   final Node node = new Node(newLevel, parent);
   node.data.addAll(parent.data);
   node.data.add(line);
   parent.add(node);
   if (newLevel < buckets.size() - 1) {
    buldPossibleCombinationsTree(buckets, lines, node, newLevel);
   }
  }
 }

 // returns array list, every element indicate how many lines of requested
 // sizes actually are possible
 // null if combination is not possible
 private static ArrayList<Integer> getActualPossibleCombination(
   final ArrayList<Integer> buckets, final ArrayList<LongLine> lines) {
  final ArrayList<Integer> bucketsReduced = new ArrayList<Integer>();
  for (final Integer bucket : buckets) {
   if (bucket > 2) {
    bucketsReduced.add(bucket);
   }
  }

  if (bucketsReduced.isEmpty()) {
   return null;
  }

  if (bucketsReduced.size() == 1) {
   final int bucketIndex = 0; // all lines are possible for first
          // bucket, for
   // other buckets we need to check intersections
   final Integer lenForFirstBucket = bucketsReduced.get(bucketIndex);
   final ArrayList<LongLine> linesForFirstBucketLen = getLinesWithLen(
     lines, lenForFirstBucket);

   final int possibleLinesForFirstBucketLen = linesForFirstBucketLen
     .size();

   final ArrayList<Integer> result = new ArrayList<Integer>();
   result.add(possibleLinesForFirstBucketLen);
   return result;
  }

  final Node root = new Node(-1, null);
  buldPossibleCombinationsTree(bucketsReduced, lines, root, -1);
  return getMultipliersForPossibleCombinations(bucketsReduced, root);
 }

 private static ArrayList<Integer> getMultipliersForPossibleCombinations(
   final ArrayList<Integer> buckets, final Node root) {
  return breadthFirst(root, buckets);
 }

 private static final ArrayList<Integer> breadthFirst(final Node root,
   final ArrayList<Integer> buckets) {
  final ArrayList<Integer> result = new ArrayList<Integer>();
  final int[] res = new int[buckets.size()];

  // System.out.println("!!!!TREEE breadthFirst");
  final Queue<Node> queue = new java.util.LinkedList<Node>();
  queue.offer(root);
  while (!queue.isEmpty()) {
   final Node node = queue.poll();
   // System.out.println(" " + node.level + " " + node.data);
   if (node.level >= 0) {
    res[node.level]++;
   }
   for (final Node next : node.children) {
    queue.offer(next);
   }
  }

  for (int i = 0; i < res.length; i++) {
   result.add(res[i]);
  }
  return result;
 }

 static int longest(final byte[][] xy, final int N,
   final boolean[] removedIndexes, final double[][] slopes,
   final double[][] bs, final int level,
   final ArrayList<LongLine> lines) {

  int iterations = 0;
  int bestLen = 2;
  double bestSlope = Double.NaN;
  double bestB = Double.NaN;
  int[] pointIndexes = null;
  // double slopes[]=new double[N-1];
  for (int i = 0; i < N && removedIndexes[i] != true; ++i) {
   final int x1 = xy[i][0];
   final int y1 = xy[i][1];

   for (int j = i + 1; j < N && removedIndexes[j] != true; j++) {
    final int x2 = xy[j][0];
    final int y2 = xy[j][1];
    int count = 2;
    // for (int k = 0; k < N; ++k) {
    // if (k == i || k == j) {
    // continue;
    // }
    if (level == 0) {
     pointIndexes = new int[N];
     // Arrays.fill(pointIndexes, -1);
     pointIndexes[0] = i;
     pointIndexes[1] = j;
    }

    for (int k = j + 1; k < N; ++k) {

     iterations++;
     // check that points i, j and k are collinear,increment
     // count.
     final int x3 = xy[k][0];
     final int y3 = xy[k][1];
     if (isCollinear(x1, y1, x2, y2, x3, y3)) {
      if (level == 0) {
       // System.out.println("!!! isCollinear k=" + k
       // + " count-" + count);
       pointIndexes[count] = k;

      }
      // System.out.println("!!! isCollinear k=" + k +
      // " count-"
      // + count + " level=" + level + " i=" + i + " j="
      // + j);

      count++;
     }
    }

    if (level == 0 && count > 2) {
     final int[] copy = new int[count];
     System.arraycopy(pointIndexes, 0, copy, 0, count);
     // Arrays.sort(copy);
     final LongLine line = new LongLine(copy, slopes[i][j],
       bs[i][j]);
     boolean isParentAlreadyPresent = false;
     for (final LongLine addedLine : lines) {
      if (addedLine.isParentFor(line)) {
       isParentAlreadyPresent = true;
       break;
      }
     }
     if (!isParentAlreadyPresent) {
      lines.add(line);
     }
    }

    if (count > bestLen) {
     bestSlope = slopes[i][j];
     bestB = bs[i][j];
     bestLen = count;
    }
   }
  }

  // System.out.println("iterations=" + iterations + " bestSlope="
  // + bestSlope + " bestB=" + bestB + " best=" + bestLen
  // + " LEVEL=" + level);

  markLinePointsAsRemoved(N, removedIndexes, slopes, bs, bestLen,
    bestSlope, bestB);

  return bestLen;
 }

 public static void markLinePointsAsRemoved(final int N,
   final boolean[] removedIndexes, final double[][] slopes,
   final double[][] bs, final int best, final double bestSlope,
   final double bestB) {
  if (best > 2) {
   for (int i = 0; i < N && removedIndexes[i] != true; ++i) {
    for (int j = i + 1; j < N; j++) {
     if (i != j && bestSlope == slopes[i][j]
       && bs[i][j] == bestB) {
      removedIndexes[j] = true;
      removedIndexes[i] = true;
     }
    }
   }
   // for (int i = 0; i < N; ++i) {
   // System.out.println("i" + i + " " + removedIndexes[i]);
   // }
  }
 }

 public static ArrayList<ArrayList<Integer>> sum_up(final int target,
   final int count, final ArrayList<Integer> input) {
  final ArrayList<ArrayList<Integer>> allUniqueCombination = new ArrayList<ArrayList<Integer>>();
  final HashSet<String> set = new HashSet<String>();
  sum_up_recursive(input, target, new ArrayList<Integer>(), count,
    allUniqueCombination, set);
  return allUniqueCombination;
 }

 private static void sum_up_recursive(final List<Integer> numbers,
   final int target, final ArrayList<Integer> partial,
   final int count,
   final ArrayList<ArrayList<Integer>> allCombination,
   final HashSet<String> set) {
  int s = 0;
  for (final int x : partial) {
   s += x;
  }
  if (s == target) {
   if (partial.size() <= 1 // target itself
     || partial.size() != count) {
    return;
   }

   Collections.sort(partial);
   Collections.reverse(partial);
   final String asString = Arrays.toString(partial.toArray());
   if (!set.contains(asString)) {
    set.add(asString);
    allCombination.add(partial);
   }
  }

  if (s >= target) {
   return;
  }
  for (int i = 0; i < numbers.size(); i++) {
   final ArrayList<Integer> remaining = new ArrayList<Integer>();
   final int n = numbers.get(i);
   for (int j = i + 1; j < numbers.size(); j++) {
    remaining.add(numbers.get(j));
   }
   final ArrayList<Integer> partial_rec = new ArrayList<Integer>(
     partial);
   partial_rec.add(n);
   sum_up_recursive(remaining, target, partial_rec, count,
     allCombination, set);
  }
 }

 private static void printLines(final ArrayList<LongLine> lines,
   final byte[][] xy) {
  System.out.println("ALL LINES: ");
  for (final LongLine line : lines) {
   System.out.print("mlen=" + line.maxLen + " points: ");
   for (int i = 0; i < line.maxLen; i++) {
    final int index = line.pointIndexes[i];
    if (index >= 0) {
     System.out.print("{" + xy[index][0] + ", " + xy[index][1]
       + "} ");
    }
   }
   System.out.println();
  }
  System.out.println();
 }

 // Print content of array a
 public static void printArray(final byte[][] a) {
  System.out.print("{");
  for (int i = 0; i < a.length; i++) {
   System.out.print("{");
   for (int k = 0; k < 2; k++) {
    System.out.print(a[i][k] + (k == 1 ? "" : ", "));
   }
   System.out.println("}" + (i == (a.length - 1) ? "" : ", "));
  }
  System.out.println("}");
 }

 // Returns whether 3 points are collinear. cross product ==0
 static boolean isCollinear(final int x1, final int y1, final int x2,
   final int y2, final int x3, final int y3) {
  return (y1 - y2) * (x1 - x3) == (y1 - y3) * (x1 - x2);
 }

 // ////////////////////////////////////////////////////////////////////////////////////
 public static BigInteger factorial(final int a) {
  BigInteger res = BigInteger.ONE;
  for (int i = 2; i <= a; i++) {
   res = res.multiply(BigInteger.valueOf(i));
  }
  return res;
 }

 public static BigInteger getPermutedCombinationsCount(final int n,
   final int r) {
  final BigInteger nFact = factorial(n);
  final BigInteger rFact = factorial(r);
  final BigInteger nminusrFact = factorial(n - r);
  return nFact.divide(rFact.multiply(nminusrFact));
 }

 static class CombinationGenerator {
  private final int[] a;
  private final int n;
  private final int r;
  private BigInteger numLeft;
  private final BigInteger total;

  public CombinationGenerator(final int n, final int r) {
   if (r > n) {
    throw new IllegalArgumentException();
   }
   if (n < 1) {
    throw new IllegalArgumentException();
   }
   this.n = n;
   this.r = r;
   a = new int[r];
   total = getPermutedCombinationsCount(n, r);
   reset();
  }

  public void reset() {
   for (int i = 0; i < a.length; i++) {
    a[i] = i;
   }
   numLeft = BigInteger.valueOf(total.longValue());
  }

  public BigInteger getNumLeft() {
   return numLeft;
  }

  public boolean hasMore() {
   return numLeft.compareTo(BigInteger.ZERO) == 1;
  }

  public BigInteger getTotal() {
   return total;
  }

  public int[] getNext() {
   if (numLeft.equals(total)) {
    numLeft = numLeft.subtract(BigInteger.ONE);
    return a;
   }

   int i = r - 1;
   while (a[i] == n - r + i) {
    i--;
   }
   a[i] = a[i] + 1;
   for (int j = i + 1; j < r; j++) {
    a[j] = a[i] + j - i;
   }

   numLeft = numLeft.subtract(BigInteger.ONE);
   return a;
  }
 }
}