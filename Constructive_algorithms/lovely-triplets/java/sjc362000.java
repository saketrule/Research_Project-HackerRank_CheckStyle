import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

  public static final int MAX_OVERHEAD = 12;

  public static class Pair {
    public int val1;
    public int val2;
    public Pair(int val1, int val2) {
      this.val1 = val1;
      this.val2 = val2;
    }
  }

  public static class LeafValues {
    public int i;
    public int j;
    public int k;
    public LeafValues(int i, int j, int k) {
      this.i = i;
      this.j = j;
      this.k = k;
    }

    public int sum() {
      return i + j + k;
    }
  }

  public static LeafValues[] getLeafValuesArray() {
    LeafValues[] leafValueArray = new LeafValues[5001];
    for (int i = 1; i <= 88; ++i) {
      for(int j = 1; j <= 88 - i; ++ j) {
        for(int k = 1; k <= 88 - i - j; ++k) {
          int multValue = i * j * k;
          if (multValue > 5000) {
            break;
          }
          LeafValues newLeafValues = new LeafValues(i, j, k);
          if (leafValueArray[multValue] == null || leafValueArray[multValue].sum() > newLeafValues.sum()) {
             leafValueArray[multValue] = newLeafValues;
          }
        }
      } 
    }
    return leafValueArray;
  }

  public static List<LeafValues> findLeavesThatWork(LeafValues[] leafValueArray, int P) {
    List<LeafValues> leafGraphs = new ArrayList<LeafValues>();
    if (leafValueArray[P] != null) {
      leafGraphs.add(leafValueArray[P]);
      return leafGraphs;
    }
    for (int m = 1; m <= P; ++m) {
      if (leafValueArray[m] != null && leafValueArray[P - m] != null && (leafValueArray[m].sum() + leafValueArray[P - m].sum() <= 100 - 2 * MAX_OVERHEAD)) {
        leafGraphs.add(leafValueArray[m]); 
        leafGraphs.add(leafValueArray[P - m]); 
        return leafGraphs;
      }
    }
    throw new RuntimeException("COULD NOT FIND GOOD NODES");
  }

  public static List<Pair> createSpecialGraph(int P) {
    LinkedList<Pair> myPairs = new LinkedList<Pair>();
    // just need to create multiple spokes for this one 
    int[] combinationArray = new int[36];
    for (int i = 3; i <= 35; ++i) {
      combinationArray[i] = i * (i - 1) * (i - 2) / 6;
    }
    int combosLeft = P;
    int startNodeValue = 1;
    while(combosLeft > 0) {
      int valueToUse = -1;
      for (int j = 35; j >=3; --j) {
        if (combosLeft >= combinationArray[j]) {
          valueToUse = j;
          break;
        }
      }
      if (valueToUse == -1) {
        throw new RuntimeException("COULD NOT FIND VALUE TO USE FOR SPECIAL GRAPH");
      }
      int currentPoint = startNodeValue + 1;
      for (int i = currentPoint; i < currentPoint + valueToUse; ++i) {
        myPairs.add(new Pair(startNodeValue, i)); 
      }
      startNodeValue = currentPoint + valueToUse;
      combosLeft -= combinationArray[valueToUse]; 
    }
    int currentLength = myPairs.size();
    myPairs.addFirst(new Pair(startNodeValue - 1, currentLength));
    return myPairs;
  }

  public static List<Pair> createOddGraphs(int P, int Q, List<LeafValues> leafGraphValues) {
    LinkedList<Pair> myPairs = new LinkedList<Pair>();
    int startNodeValue = 1;
    int sizeOfSpoke = (Q - 1) / 2 - 1;
    int currentPoint = startNodeValue;
    for (LeafValues leafValues : leafGraphValues) {
      List<Integer> endpointOfSpokes = new ArrayList<Integer>();
      List<Integer> innerTriangleOfSpokes = new ArrayList<Integer>();
      innerTriangleOfSpokes.add(startNodeValue);
      innerTriangleOfSpokes.add(startNodeValue + 1);
      innerTriangleOfSpokes.add(startNodeValue + 2);
      currentPoint = startNodeValue + 3;

      // write inner triangle
      myPairs.add(new Pair(innerTriangleOfSpokes.get(0), innerTriangleOfSpokes.get(1)));
      myPairs.add(new Pair(innerTriangleOfSpokes.get(0), innerTriangleOfSpokes.get(2)));
      myPairs.add(new Pair(innerTriangleOfSpokes.get(1), innerTriangleOfSpokes.get(2)));
      for(int i = 0; i <= 2; ++i) {
        int prevPoint = innerTriangleOfSpokes.get(i);
        for(int j = currentPoint; j < currentPoint + sizeOfSpoke; ++j) {
          myPairs.add(new Pair(prevPoint, j));
//          System.out.println(prevPoint + " " + j);
          prevPoint = j;
        }
        endpointOfSpokes.add(prevPoint);
        currentPoint = prevPoint + 1;
      }
      
      for (int i = 1; i <= leafValues.i; ++i) {
        myPairs.add(new Pair(endpointOfSpokes.get(0), currentPoint));
//        System.out.println(endpointOfSpokes.get(0) + " " + currentPoint);
        currentPoint++;
      }
      for (int j = 1; j <= leafValues.j; ++j) {
        myPairs.add(new Pair(endpointOfSpokes.get(1), currentPoint));
//        System.out.println(endpointOfSpokes.get(1) + " " + currentPoint);
        currentPoint++;
      }
      for (int k = 1; k <= leafValues.k; ++k) {
        myPairs.add(new Pair(endpointOfSpokes.get(2), currentPoint));
//        System.out.println(endpointOfSpokes.get(2) + " " + currentPoint);
        currentPoint++;
      }
      startNodeValue = currentPoint;
    }
    int currentLength = myPairs.size();
    myPairs.addFirst(new Pair(startNodeValue - 1, currentLength));
    return myPairs;
  }

  public static List<Pair> createEvenGraphs(int P, int Q, List<LeafValues> leafGraphValues) {
    LinkedList<Pair> myPairs = new LinkedList<Pair>();
    int startNodeValue = 1;
    int sizeOfSpoke = Q / 2 - 1;
    int currentNode = 2;
    for (LeafValues leafValues : leafGraphValues) {
      List<Integer> endpointOfSpokes = new ArrayList<Integer>();
      // create spokes 
      int currentPoint = startNodeValue + 1;
      for(int i = 1; i <= 3; ++i) {
        int prevPoint = startNodeValue;
        for(int j = currentPoint; j < currentPoint + sizeOfSpoke; ++j) {
          myPairs.add(new Pair(prevPoint, j));
//          System.out.println(prevPoint + " " + j);
          prevPoint = j;
        }
        endpointOfSpokes.add(prevPoint);
        currentPoint = prevPoint + 1;
      }
      for (int i = 1; i <= leafValues.i; ++i) {
        myPairs.add(new Pair(endpointOfSpokes.get(0), currentPoint));
//        System.out.println(endpointOfSpokes.get(0) + " " + currentPoint);
        currentPoint++;
      }
      for (int j = 1; j <= leafValues.j; ++j) {
        myPairs.add(new Pair(endpointOfSpokes.get(1), currentPoint));
//        System.out.println(endpointOfSpokes.get(1) + " " + currentPoint);
        currentPoint++;
      }
      for (int k = 1; k <= leafValues.k; ++k) {
        myPairs.add(new Pair(endpointOfSpokes.get(2), currentPoint));
//        System.out.println(endpointOfSpokes.get(2) + " " + currentPoint);
        currentPoint++;
      }
      startNodeValue = currentPoint;
    }
    int currentLength = myPairs.size();
    myPairs.addFirst(new Pair(startNodeValue - 1, currentLength));
    return myPairs;
  }

  public static void main(String args[] ) throws Exception {
    Scanner in = new Scanner(System.in);
    int P = in.nextInt();
    int Q = in.nextInt();

    List<Pair> pairs = null;
    if (Q == 2) {
      pairs = createSpecialGraph(P);
    } else {
      LeafValues[] leafValuesArray = getLeafValuesArray();
      List<LeafValues> leafGraphValues = findLeavesThatWork(leafValuesArray, P);
      if ((Q % 2) == 1) {
        pairs = createOddGraphs(P, Q, leafGraphValues);
      } else {
        pairs = createEvenGraphs(P, Q, leafGraphValues);
      }
    }
    for (Pair p : pairs) {
      System.out.println(p.val1 + " " + p.val2);
    }
  }
}