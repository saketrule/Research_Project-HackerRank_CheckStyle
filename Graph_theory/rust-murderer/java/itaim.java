import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: itaim
 * Date: 09/04/15
 * Time: 15:11
 * To change this template use File | Settings | File Templates.
 */
public class RNM {
    static String INPUT = "";
    static PrintWriter out;
    static InputStream is;

    public static void main(String[] args) throws Exception {
        long S = System.currentTimeMillis();
        is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);

        solve();
        out.flush();
        long G = System.currentTimeMillis();
        tr(G - S + "ms");
    }

    private static void solve() {
        int t = ni();
        for (int i = 0; i < t; i++) {
            int[] arr = na(2);
            Map<Integer, Node> graph = initGraph(arr[0]);
            addMainRoads(arr[1], graph);
            solve(graph, ni() - 1);
        }
    }

    private static void solve(Map<Integer, Node> graph, int s) {
        Collection<Integer> result = mapShortestPathLengths(graph, s);
        out.println(asString(result));
    }

    private static Collection<Integer> mapShortestPathLengths(Map<Integer, Node> graph, int s) {
        final SortedMap<Integer, Integer> solutions = new TreeMap<>();
        Queue<Node> queue = new LinkedList<Node>();
        Node start = graph.remove(s);
        queue.add(start);
        start.depth = 0;
        while (!queue.isEmpty()) {
            Node parent = queue.remove();
            solutions.put(parent.key, parent.depth);
            int depth = parent.depth + 1;
            for (Node child : getSideRoads(parent, graph)) {
                child.depth = depth;
                queue.add(child);
            }
        }
        solutions.remove(s);
        return solutions.values();
    }

    private static List<Node> getSideRoads(Node parent, Map<Integer, Node> graph) {
        List<Node> list = new ArrayList<>();
        final Iterator<Map.Entry<Integer, Node>> iterator = graph.entrySet().iterator();
        while (iterator.hasNext()) {
            Node n = iterator.next().getValue();
            if (parent.edges.contains(n)) {
                continue;
            }
            iterator.remove();
            list.add(n);
        }
        return list;
    }

    private static void addMainRoads(int roads, Map<Integer, Node> graph) {
        for (int j = 0; j < roads; j++) {
            int[] edge = na(2);
            final Node xNode = graph.get(edge[0] - 1);
            final Node yNode = graph.get(edge[1] - 1);
            xNode.addEdge(yNode);
            yNode.addEdge(xNode);
        }
    }

    private static Map<Integer, Node> initGraph(int size) {
        Map<Integer, Node> graph = new HashMap<>();
        for (int j = 0; j < size; j++) {
            graph.put(j, new Node(j));
        }
        return graph;
    }

    private final static class Node {

        final int key;
        Set<Node> edges = new HashSet<>();
        int depth = -1;

        public Node(int key) {
            this.key = key;
        }

        public void addEdge(Node node) {
            edges.add(node);
        }

        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (key != node.key) return false;

            return true;
        }

        @Override
        public final int hashCode() {
            return key;
        }

        @Override
        public String toString() {
            return key + " [" + toString(edges) + "]";
        }

        private String toString(Set<Node> mainRoads) {
            StringBuilder builder = new StringBuilder();
            for (Node c : mainRoads) builder.append(c.key).append(" ");
            return builder.toString().trim();
        }
    }

    private static byte[] inbuf = new byte[1024];
    static int lenbuf = 0, ptrbuf = 0;

    private static int readByte() {
        if (lenbuf == -1) throw new InputMismatchException();
        if (ptrbuf >= lenbuf) {
            ptrbuf = 0;
            try {
                lenbuf = is.read(inbuf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (lenbuf <= 0) return -1;
        }
        return inbuf[ptrbuf++];
    }

    private static int[] na(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = ni();
        return a;
    }

    private static int ni() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private static void tr(Object... o) {
        if (INPUT.length() != 0) System.out.println(Arrays.deepToString(o));
    }

    private static <T> String asString(Collection<T> collection) {
        return asString(collection.toArray());
    }

    private static <T> String asString(T[] objects) {
        final String str = Arrays.deepToString(objects).replaceAll(",", "");
        return str.substring(1, str.length() - 1);
    }
}