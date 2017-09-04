import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private static int max = 0;
    private static Set<Set<Integer>> visited = new HashSet<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int[] scores = new int[n];
        for (int i = 0; i < n; i++) {
            scores[i] = scan.nextInt();
        }
        
        Map<Integer, Set<Integer>> G = new HashMap<>();
        for (int i = 0; i < n; i++) {
            G.put(i, new HashSet<Integer>());
        }
        
        for (int i = 0; i < m; i++) {
            int from = scan.nextInt() - 1;
            int to = scan.nextInt() - 1;
            G.get(from).add(to);
            G.get(to).add(from);
        }
        
        int groupId = 1;
        int[] groups = new int[n];
        for (int i = 0; i < n; i++) {
            if (groups[i] != 0) continue;
            dfs(i, G, groups, groupId);
            groupId++;
        }
        
        long resultSum = 0;
        long resultCnt = 1;
        
        for (int groupI = 1; groupI < groupId; groupI++) {
            Set<Integer> groupNodes = new HashSet<>();
            int start;
            for (start = 0; groups[start] != groupI; start++);
            for (int i = 0; i < n; i++) {
                if (groups[i] == groupI) {
                    groupNodes.add(i);
                }
            }
            max = 0;
            visited = new HashSet<>();
            long cnt = countPaths(start, groupNodes, G, scores);
            resultSum += max;
            resultCnt *= visited.size();
        }
        
        System.out.println(String.format("%d %d", resultSum, resultCnt));
    }
    
    public static int countPaths(int start, Set<Integer> groupNodes, Map<Integer, Set<Integer>> G, int[] scores) {
        Set<Integer> path = new HashSet<>();
        path.add(start);
        Set<Integer> constraints = new HashSet<>();
        constraints.add(start);
        constraints.addAll(G.get(start));
        traverse(path, groupNodes, constraints, G, scores);
        
        for (int i : G.get(start)) {
            int startNode = i;
            path = new HashSet<>();
            path.add(startNode);
            constraints = new HashSet<>();
            constraints.add(startNode);
            constraints.addAll(G.get(startNode));
            traverse(path, groupNodes, constraints, G, scores);
        }
        
        postProcess(visited, scores);
        
        return visited.size();
    }
    
    public static void dfs(int start, Map<Integer, Set<Integer>> G, int[] groups, int groupId) {
        groups[start] = groupId;
        for (int v : G.get(start)) {
            if (groups[v] != 0) continue;
            dfs(v, G, groups, groupId);
        }
    }
    
    private static void postProcess(Set<Set<Integer>> visited, int[] scores) {
        for (Set<Integer> path : new HashSet<>(visited)) {
            zeroConditionProcess(path, scores);
        }
    }
    
    private static void zeroConditionProcess(Set<Integer> path, int[] scores) {
        for (int i : path) {
            if (scores[i] == 0) {
                Set<Integer> newPath = new HashSet<>(path);
                newPath.remove(i);
                visited.add(newPath);
                zeroConditionProcess(newPath, scores);
            }
        }
    }
    
    public static long power(int num, int idx) {
        long result = 1l;
        for (int i = 0; i < idx; i++) {
            result *= num;
        }
        return result;
    }
    
    public static void traverse(Set<Integer> path, Set<Integer> groupNodes, Set<Integer> constraints, Map<Integer, Set<Integer>> G, int[] scores) {
        Set<Integer> canVisit = new HashSet<>(groupNodes);
        canVisit.removeAll(constraints);
        if (canVisit.isEmpty()) {
            int sum = 0;
            for (int node : path) {
                sum += scores[node];
            }
            if (sum > max) {
                max = sum;
                visited = new HashSet<>();
                visited.add(path);
            } else if (sum == max) {
                visited.add(path);
            }
        } else {
            for (int v : canVisit) {
                Set<Integer> newPath = new HashSet<>(path);
                newPath.add(v);
                Set<Integer> newConstraints = new HashSet<>(constraints);
                newConstraints.add(v);
                newConstraints.addAll(G.get(v));
                traverse(newPath, groupNodes, newConstraints, G, scores);
            }
        }
    }
}