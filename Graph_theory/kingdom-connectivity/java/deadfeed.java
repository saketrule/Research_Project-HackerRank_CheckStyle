/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class Solution {
    private final int MOD = 1000000000;
    
    private Map<Integer, Map<Integer, Integer>> paths;
    private Set<Integer> currPaths = new HashSet<Integer>();
    private Set<Integer> cycles = new HashSet<Integer>();
    private Set<Integer> canReach = new HashSet<Integer>();
    private Map<Integer, Long> routeTable = new HashMap<Integer, Long>();
    
    private int N, M;

    public static void main(String[] args) throws java.lang.Exception {
        new Solution().solve();
    }

    private void solve() {
        paths = input();
        long routeCount = count(1, N, currPaths);
        for (Integer node : cycles) {
            if (canReach.contains(node)) {
                routeCount = -1;
                break;
            }
        }
        if (paths.containsKey(N)) {
            for (Integer node : paths.get(N).keySet()) {
                long count = count(node, N, new HashSet<Integer>());
                if (count > 0) {
                    routeCount = -1;
                    break;
                }
            }
        }
        print(routeCount);
    }

    private void print(long routeCount) {
        String count = routeCount == -1 ? "INFINITE PATHS" : String.valueOf(routeCount);
        System.out.println(count);
    }

    private long count(int start, int end, Set<Integer> currentRoute) {
        currentRoute.add(start);
        long routeCount = 0;
        if (routeTable.containsKey(start)) {
            routeCount = routeTable.get(start);
        } else {
            Map<Integer, Integer> connections = paths.get(start);
            if (connections != null) {
                for (Integer connectedNode : connections.keySet()) {
                    if (currentRoute.contains(connectedNode)) {
                        cycles.add(connectedNode);
                    } else if (connectedNode == end) {
                        routeCount += connections.get(connectedNode); 
                        canReach.addAll(currentRoute);
                    } else {
                        long connectedNodeCount = count(connectedNode,
                                end, currentRoute);
                        routeCount += connectedNodeCount
                                * connections.get(connectedNode);
                    }
                }
            }
            routeTable.put(start, routeCount);
        }
        currentRoute.remove(start);
        return routeCount % MOD;
    }

    private Map<Integer, Map<Integer, Integer>> input() {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        Map<Integer, Map<Integer, Integer>> connections = new HashMap<Integer, Map<Integer, Integer>>();
        for (int i = 0; i < M; i++) {
            int source = scanner.nextInt();
            int dest = scanner.nextInt();
            if (!connections.containsKey(source)) {
                connections.put(source, new HashMap<Integer, Integer>());
            }
            Map<Integer, Integer> sourceToTargetMap = connections.get(source);
            if (!sourceToTargetMap.containsKey(dest)) {
                sourceToTargetMap.put(dest, 1);
            } else {
                sourceToTargetMap.put(dest, sourceToTargetMap.get(dest) + 1);
            }
        }

        return connections;
    }
}