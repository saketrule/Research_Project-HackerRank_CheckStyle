import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] c = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            c[i] = in.nextInt();
        }
        Set<Set<Integer>> roads = new HashSet<>();
        for (int i = 0; i < m; i++) {
            Set<Integer> road = new HashSet<>();
            road.add(in.nextInt());
            road.add(in.nextInt());
            roads.add(road);
        }
        getMaxMoney(c, roads);
        System.out.println(maxMoney + " " + paths.size());
    }

    public static void getMaxMoney(int[] c, Set<Set<Integer>> roads) {
        byte[] visited = new byte[c.length];
        getMaxMoney(c, roads, visited, 1, 0);
    }

    public static void getMaxMoney(int[] c, Set<Set<Integer>> roads, byte[] visited, int startingIndex, int startingMoney) {
        for (int i = startingIndex; i < c.length; i++) {
            int money = startingMoney;
            if (visited[i] > 0) {
                continue;
            }
            byte[] visitedCopy = Arrays.copyOf(visited, visited.length);
            money += c[i];
            Set<Integer> connectedHouses = getConnectedHouses(i, roads);
            visitedCopy[i] = 1;
            for (int connectedHouse : connectedHouses) {
                visitedCopy[connectedHouse] = 2;
            }
            int nextStartingIndex = getFirstUnvisitedIndex(visitedCopy);
            if (money == maxMoney) {
                Set<Integer> burgledHouses = getBurgledHouses(visitedCopy);
                paths.add(burgledHouses);
            }
            if (nextStartingIndex < 0) {
                if (money > maxMoney) {
                    maxMoney = money;
                    paths.clear();
                }
                if (money == maxMoney) {
                    Set<Integer> burgledHouses = getBurgledHouses(visitedCopy);
                    paths.add(burgledHouses);
                }
            }
            else {
                getMaxMoney(c, roads, visitedCopy, nextStartingIndex, money);
            }
        }
    }

    public static Set<Integer> getConnectedHouses(int house, Set<Set<Integer>> roads) {
        Set<Integer> connectedHouses = new HashSet<>();
        for (Set<Integer> road : roads) {
            if (road.contains(house)) {
                connectedHouses.add(getConnectedHouse(house, road));
            }
        }
        return connectedHouses;
    }

    public static int getConnectedHouse(int house, Set<Integer> road) {
        for (int i : road) {
            if (house != i) {
                return i;
            }
        }
        return -1;
    }

    public static int getFirstUnvisitedIndex(byte[] visited) {
        for (int i = 1; i < visited.length; i++) {
            if (visited[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public static Set<Integer> getBurgledHouses(byte[] visited) {
        Set<Integer> burgledHouses = new HashSet<>();
        for (int i = 1; i < visited.length; i++) {
            if (visited[i] == 1) {
                burgledHouses.add(i);
            }
        }
        return burgledHouses;
    }
    
    public static int maxMoney = 0;
    public static Set<Set<Integer>> paths = new HashSet<>();
}