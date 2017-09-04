import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        // Initialize houses with money
        House[] houses = new House[n];
        for (int i = 0; i < n; ++i) {
            houses[i] = new Solution.House(sc.nextInt());
        }
        
        // Add neighbors to houses
        for (int i = 0; i < m; ++i) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            houses[a].addNeighbor(b);
            houses[b].addNeighbor(a);
        }
        
        boolean[] canVisit = new boolean[n];
        Arrays.fill(canVisit, true);
        int[] visitInfo = amountAndWays(houses, canVisit);
        
        System.out.println(Integer.toString(visitInfo[0]) + " " + Integer.toString(visitInfo[1]));
        
    }
    
    public static int[] amountAndWays(House[] houses, boolean[] canVisit) {
        if (allFalse(canVisit)) {
            return new int[] {0, 1};
        }
        
        int first = firstVisitable(canVisit);
        
        // Visit first visitable house
        boolean[] canVisitFirst = Arrays.copyOf(canVisit, canVisit.length);
        canVisitFirst[first] = false;
        for (int neighbor : houses[first].neighbors) {
            canVisitFirst[neighbor] = false;
        }
        int[] visitFirst = amountAndWays(houses, canVisitFirst);
        visitFirst[0] += houses[first].money;
        
        // Do not visit first visitable house
        boolean[] cannotVisitFirst = Arrays.copyOf(canVisit, canVisit.length);
        cannotVisitFirst[first] = false;
        int[] noVisitFirst = amountAndWays(houses, cannotVisitFirst);
        
        int[] visitInfo = new int[2];
        if (visitFirst[0] > noVisitFirst[0]) {
            visitInfo[0] = visitFirst[0];
            visitInfo[1] = visitFirst[1];
        } else if (visitFirst[0] < noVisitFirst[0]) {
            visitInfo[0] = noVisitFirst[0];
            visitInfo[1] = noVisitFirst[1];
        } else {
            visitInfo[0] = visitFirst[0];
            visitInfo[1] = visitFirst[1] + noVisitFirst[1];
        }
        
        return visitInfo;
    }
    
    public static boolean allFalse(boolean[] a) {
        for (boolean b : a) {
            if (b) return false;
        }
        
        return true;
    }
    
    public static int firstVisitable(boolean[] a) {
        for (int i = 0; i < a.length; ++i) {
            if (a[i]) return i;
        }
        
        return -1;
    }
    
    private static class House {
        int number;
        int money;
        Set<Integer> neighbors = new HashSet<>();
        
        public House(int money) {
            this.money = money;
        }
        
        public void addNeighbor(int neighbor) {
            neighbors.add(neighbor);
        }
        
        @Override
        public boolean equals(Object o) {
            if (o == this) return true;
            if (!(o instanceof House)) return false;
            House h = (House) o;
            return h.number == this.number;
        }
    }
}