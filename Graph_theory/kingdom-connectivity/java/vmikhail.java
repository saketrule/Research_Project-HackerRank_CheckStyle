import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Vector;
import java.util.regex.Pattern;

public class Solution {
    private static final Pattern COMMA = Pattern.compile("\\s");

    static class DFS {
        private short index = 0;
        private Stack<Short> stack = new Stack<Short>();
        private Map<Short, Short> indexes = new HashMap<Short, Short>();
        private LinkedList<Short> topologicalIndex = new LinkedList<Short>();
        private Map<Short, Short> lowlinks = new HashMap<Short, Short>();
        private final List<Short>[] nodes;
        private short topIndex;
        private long[] result;

        private void tarjan(short node) {
            indexes.put(node, index);
            lowlinks.put(node, index);
            index++;
            stack.push(node);
            if (nodes[node] != null) {
                for (Short nextNode : nodes[node]) {
                    if (indexes.get(nextNode) == null) {
                        tarjan(nextNode);
                        lowlinks.put(node, (short) Math.min(lowlinks.get(node), lowlinks.get(nextNode)));
                    } else if (stack.contains(nextNode)) {
                        lowlinks.put(node, (short) Math.min(indexes.get(nextNode), lowlinks.get(node)));
                    }
                }
            }
            topIndex++;
            topologicalIndex.addFirst(node);
            short nodeLowlink = lowlinks.get(node);
            short nodeIndex = indexes.get(node);
            if (nodeIndex == nodeLowlink) {
                short n;
                List<Short> cyclic = new ArrayList<Short>();
                do {
                    n = stack.pop();
                    cyclic.add(n);
                } while (n != node);
                if (cyclic.size() > 1) {
                    for (short cyclicNode : cyclic) {
                        result[cyclicNode] = -1;
                    }
                }
            }
        }

        public DFS(List<Short>[] nodes) {
            this.nodes = nodes;
            this.result = new long[nodes.length];
        }

        public void walk() {
            tarjan((short) 1);
        }

        public void countPaths() {
            result[1] = 1;
            for (Short entry : topologicalIndex) {
                if (nodes[entry] != null) {
                    for (short node : nodes[entry]) {
                        if (result[entry] < 0 || result[node] < 0) {
                            result[node] = -1;
                        } else {
                            result[node] = sum(result[node], result[entry]);
                        }
                    }
                }
            }
        }
    }

    private static final long MODULE = 1000000000L;

    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        List<Short>[] nodes = load(inputStreamReader);
        long solved = solve(nodes);
        if (solved < 0) {
            System.out.println("INFINITE PATHS");
        } else {
            System.out.println(solved);
        }
    }

    public static long sum(long s, long t) {
        long sum = s + t;
        if (sum >= MODULE) {
            sum -= MODULE;
        }
        return sum;
    }

    static long solve(List<Short>[] nodes) {
        DFS dfs = new DFS(nodes);
        dfs.walk();
        dfs.countPaths();
        return dfs.result[nodes.length - 1];
    }

    static List<Short>[] load(InputStreamReader inputStreamReader) throws IOException {
        BufferedReader br = new BufferedReader(inputStreamReader);
        String readLine = br.readLine();
        String[] split = COMMA.split(readLine);
        int N = Integer.parseInt(split[0]);
        List<Short>[] nodes = new List[N + 1];
        String line;
        while ((line = br.readLine()) != null) {
            split = COMMA.split(line);
            short source = Short.parseShort(split[0]);
            short dest = Short.parseShort(split[1]);

            if (nodes[source] == null) {
                nodes[source] = new ArrayList<Short>();
            }

            nodes[source].add(dest);
        }
        return nodes;
    }
}