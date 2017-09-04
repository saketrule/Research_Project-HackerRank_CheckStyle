import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int j = in.nextInt();

        Node[] graph = new Node[n+1];
        HashSet<Integer> houses = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            int c = in.nextInt();
            graph[i] = new Node(c);
            houses.add(i);
        }

        for (int i = 0; i < j; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            graph[a].addChild(b);
            graph[b].addChild(a);
        }

        Set<Set<Integer>> allHouses = getHouses(graph, houses);
        Set<Set<Integer>> maxSet = getMaxSet(graph, allHouses);
        int max = 0;
        Iterator<Set<Integer>> it = maxSet.iterator();
        Set<Integer> aset = it.next();
        for (int i: aset) {
            max += graph[i].money;
        }
        System.out.format("%d %d", max, maxSet.size());
    }

    public static class Node {
        int money;
        Set<Integer> child = new HashSet<>();
        public Node(int m) {
            money = m;
        }
        public void addChild(int ch) {
            child.add(ch);
        }
    }

    public static Set<Set<Integer>> getHouses(Node[] graph, Set<Integer> aset) {
        Set<Set<Integer>>  houses = new HashSet<>();
        Set<Integer> visited = new HashSet<>();

        if (aset.size() <= 0) {
            houses.add(aset);
            return houses;
        }
        for (int i: aset) {
            Set<Integer> bset = new HashSet(aset);
            bset.removeAll(visited);
            bset.removeAll(graph[i].child);
            visited.add(i);
            if (bset.size() == 1) {
                houses.add(bset);
                continue;
            }
            bset.remove(i);
            Set<Set<Integer>> newHouses = getHouses(graph, bset);
            for (Set ahouse: newHouses) {
                ahouse.add(i);
            }
            houses.addAll(newHouses);
            Set<Integer> one = new HashSet<>();
            one.add(i);
            houses.add(one);
        }

        //return max only
        Set<Set<Integer>> maxSet = getMaxSet(graph, houses);
        return maxSet;
    }

    public static Set<Set<Integer>> getMaxSet(Node[] graph, Set<Set<Integer>>  allHouses) {
        Set<Set<Integer>> maxSet = new HashSet<>();
        int max = 0;
        for (Set<Integer> aset: allHouses) {
            int sum = 0;
            for (int i: aset) {
                sum += graph[i].money;
            }
            if (sum > max) {
                maxSet.clear();
                maxSet.add(aset);
                max = sum;
            } else if (sum == max) {
                maxSet.add(aset);
            }
        }
        return maxSet;
    }

}
    