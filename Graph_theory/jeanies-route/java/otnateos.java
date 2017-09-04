import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  private static final int MAX_DEPTH = 20;
  private static final boolean DEBUG = false;
  private final int[] dest;
  private final int[][] distances;
  private final int[][] shortest;

  public Solution(final int[] dest, final int[][] distances) {
    this.dest = dest;
    this.distances = distances;
    this.shortest = new int[dest.length][dest.length];
    print(">>> DESTINATION <<<");
    printArray(dest);

    print(">>> DISTANCES <<<");
    printArray(distances);
  }

  private void print(final String s) {
    if (DEBUG) {
      System.out.println(s);
    }
  }

  private int[] getDefaultLabels(final int[][] array) {
    final int[] labels = new int[array.length];
    for (int i = 0; i < array.length; i++) {
      labels[i] = i+1;
    }
    return labels;
  }

  private void printArray(final int[][] array) {
    printArray(array, getDefaultLabels(array));
  }

  private void printArray(final int[][] array, int[] labels) {
    if (labels.length != array.length) {
      labels = getDefaultLabels(array);
    }
    final StringBuilder b = new StringBuilder();
    b.append("x\t|\t");
    for (int i = 0; i < array.length; i++) {
      b.append(labels[i]).append("|").append("\t");
    }
    b.append("\n");
    for (int i = 0; i < array.length; i++) {
      b.append("---------");
    }
    b.append("\n");
    for (int i = 0; i < array.length; i++) {
      b.append(labels[i]).append("\t|\t");
      for (int j = 0; j < array[i].length; j++) {
        b.append(array[i][j]).append("|").append("\t");
      }
      b.append("\n");
    }
    for (int i = 0; i < array.length; i++) {
      b.append("---------");
    }
    print(b.toString());
  }

  private void printArray(final int[] array) {
    final StringBuilder b = new StringBuilder();
    for (int i = 0; i < array.length; i++) {
      b.append(array[i]).append(",").append("\t");
    }
    b.append("\n----");
    print(b.toString());
  }

  public Solution calculateShortest() {
    int x;
    for (int i = 0; i < dest.length; i++) {
      for (int j = i+1; j < dest.length; j++) {
        x = i == j ? 0 : findShortest(dest[i], dest[j], 1, new ArrayList<Integer>());
        if (x == Integer.MAX_VALUE) {
          x = 0;
        }
        shortest[i][j] = x;
        shortest[j][i] = x;
      }
    }
    printArray(shortest, dest);
    return this;
  }

  public int getShortestDistance() {
    int min = Integer.MAX_VALUE;
    for (int start = 0; start < dest.length; start++) {
      final int city = dest[start];
      print("\n\n>>>> Start from City " + city);
      final int[] otherDestinations = exclude(dest, city);
      printArray(otherDestinations);
//      print(city, otherDestinations);
      int shortestFromStart = calculateAllShortest(city, otherDestinations);
      print("\n\n>>>> Shortest from City "+city + " = " + shortestFromStart);
//      System.out.println("Shortest from start "+shortestFromStart);
      if (shortestFromStart < min) {
//        System.out.println("Shortest is updated with " + shortestFromStart);
        min = shortestFromStart;
      }
    }
    return min;
  }

  private int calculateAllShortest(int a, int[] cities) {
    if (cities.length == 1) {
      int last = shortest[cityIndex(a)][cityIndex(cities[0])];
//      System.out.println("left 1, so return it " + last);
      return last;
    }
    final StringBuilder b = new StringBuilder();
    for (int i = 0; i < (dest.length-cities.length); i++) {
      b.append("   ");
    }
    final String ex = b.toString();
    int shortest = Integer.MAX_VALUE;
    for (int c : cities) {
      print(ex+"Path from " + a + " -> " + c);
      final int[] others = exclude(cities, c);
//      print(o, others);
      int a2c = dist(a, c);
      int loop = calculateAllShortest(c, others);
      int total = a2c + loop;
      print(ex+"Direct: "+ a2c + " Nested distance: "+loop + " Total distance: "+total);
      if (total < shortest) {
        print(ex+"Update shortest total to "+total);
        shortest = total;
      }
    }
    return shortest;
  }

  private int dist(int cityA, int cityB) {
    int aIdx = cityIndex(cityA);
    int bIdx = cityIndex(cityB);
    int aToO = shortest[aIdx][bIdx];
    if (aToO == 0) {
      aToO = shortest[bIdx][aIdx];
    }
    return aToO;
  }

  private int cityIndex(int city) {
    for (int i = 0; i < dest.length; i++) {
      if (dest[i] == city) {
        return i;
      }
    }
    return -1;
  }

  private int[] exclude(int[] array, int i) {
//    System.out.println("exclude "+i);

    int[] exclusive = new int[array.length-1];
    int idx = 0;
    for (int a : array) {
      if (i == a) {
        continue;
      }
//      System.out.println("a = " + a);
      exclusive[idx++] = a;
    }
    return exclusive;
  }



  private int findShortest(final int a, final int b, final int depth, final List<Integer> ignore) {
    if (depth == 1) {
//      print(">>> Find shortest from position " + a + " to " + b + " <<<");
    }
    int shortest = Integer.MAX_VALUE;
    if (depth == MAX_DEPTH) {
      return shortest;
    }
    int aPos = a - 1;
    int bPos = b - 1;
    StringBuilder ex = new StringBuilder();
    for (int i = 0; i < depth; i++) {
      ex.append("  ");
    }
//    print(ex+"City A = " + a + " , city B = " + b + " -> " + distances[aPos][bPos]);
    if (distances[aPos][bPos] != 0) {
      shortest = distances[aPos][bPos];
    }
    final int[] otherCities = distances[aPos];
//    print(ex+"Other city count: "+otherCities.length);

//    System.out.println("---- ok need to find next shortest at depth "+ depth);
//    print(ex+"Ignore list "+ignore);
    for (int i = 0; i < otherCities.length; i++) {
      if (i == aPos || i == bPos) {
        continue;
      }
//      print(ex+"Pos "+i);
      if (ignore.contains(i)) {
//        print(ex+"Ugh... ignoring");
        continue;
      }

      final int extraCity = i+1;
      final int delta = otherCities[i];

      if (delta == 0) {
//        print("No path to city "+ extraCity);
        continue;
      }
//      print(ex+"Potential path found from "+a+" -> "+extraCity+" -> "+b+" with distance "+ delta);
      int cToB = findShortest(extraCity, b, depth+1, add(ignore, aPos));
      if (cToB == Integer.MAX_VALUE) {
//        print(ex+"Hit max pos "+i);
        continue;
      }
      final int tempShortest = delta + cToB;
//      print(ex + "Temp shortest " + tempShortest);
      if (tempShortest < shortest) {
        shortest = tempShortest;
      }
    }
    return shortest;
  }

  private List<Integer> add(final List<Integer> l, final int add) {
    final List<Integer> newIgnore = new ArrayList<>(l);
    newIgnore.add(add);
    return newIgnore;
  }

  private static int[] readDestinations(final Scanner scanner, final int lettersCount) {
    final int[] dest = new int[lettersCount];
    for(int i=0; i<lettersCount; i++){
      dest[i] = scanner.nextInt();
    }
    return dest;
  }

  private static int[][] readCitiesDistance(final Scanner scan, final int cityCount) {
    final int[][] dist = new int[cityCount][cityCount];
    int city1, city2, distance;
    for (int i=0; i<cityCount-1; i++) {
      city1 = scan.nextInt();
      city2 = scan.nextInt();
      distance = scan.nextInt();
      dist[city1-1][city2-1] = distance;
      dist[city2-1][city1-1] = distance;
    }
    return dist;
  }

  public static void main(String[] args) throws Exception {
    final Scanner scan = new Scanner(System.in);
    final int cityCount = scan.nextInt();
    final int lettersCount = scan.nextInt();
    final int[] dest = readDestinations(scan, lettersCount);
    final int[][] dist = readCitiesDistance(scan, cityCount);
    final int min = new Solution(dest, dist)
        .calculateShortest()
        .getShortestDistance();
    System.out.println(min);
  }
}