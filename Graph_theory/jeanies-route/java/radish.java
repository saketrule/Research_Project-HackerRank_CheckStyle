import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;


class JeaniesRoute {
    private final Map<Integer, Vertex> idToVertex;

    public JeaniesRoute(Map<Integer, Vertex> idToVertex) {
        this.idToVertex = idToVertex;
    }

    private int computeSteinerGraph(Set<Integer> deliveryCities) {
        List<Integer> removeList = new ArrayList<Integer>();
        for (Vertex v : idToVertex.values()) {
            if (deliveryCities.contains(v.id)) {
                continue;
            }
            if (v.edges.size() == 1) {
                int neighbor = v.edges.keySet().iterator().next();
                v.remove(neighbor);
                Vertex neighborVertex = idToVertex.get(neighbor);
                neighborVertex.remove(v.id);
                removeList.add(v.id);
            }
        }
        for (Integer leaf : removeList) {
            // System.out.println("removing " + leaf);
            idToVertex.remove(leaf);
        }
        return removeList.size();
    }

    private long eulerTourLength() {
        long totalWeight = 0;
        for (Vertex v : idToVertex.values()) {
            for (Integer w : v.edges().values()) {
                totalWeight += w;
            }
        }
        return totalWeight;
    }

    private Vertex bfs(Vertex root) {
        Queue<Vertex> queue = new LinkedList<Vertex>();
        queue.add(root);

        root.setDistance(0);
        Vertex farthest = root;
        while (!queue.isEmpty()) {
            Vertex top = queue.remove();
            top.setVisited();
            if (top.distance > farthest.distance) {
                farthest = top;
            }
            for (Map.Entry<Integer, Integer> entry : top.edges.entrySet()) {
                Vertex neighbor = idToVertex.get(entry.getKey());
                if (neighbor.wasVisited()) {
                    continue;
                }
                // System.out.println("Setting " + neighbor.id + ": distance = " + top.distance + entry.getValue());
                neighbor.setDistance(top.distance + entry.getValue());
                queue.add(neighbor);
            }
            // System.out.println("farthest distance = " + farthest.distance);
        }
        return farthest;
    }

    private long diameter() {
        Vertex start = bfs(idToVertex.values().iterator().next());
        // System.out.println("start distance = " + start.distance);
        for (Vertex v : idToVertex.values()) {
            v.clearbfs();
        }
        Vertex end = bfs(start);
        // System.out.println("end distance = " + end.distance);
        return end.distance;
    }

    public long calculate(Set<Integer> deliveryCities) {
        while (computeSteinerGraph(deliveryCities) > 0) {
        }
        long eulerTourLength = eulerTourLength();
        // System.out.println("euler tour length = " + eulerTourLength);
        long diameter = diameter();
        return eulerTourLength - diameter;
    }

    private static class Vertex {
        private final Map<Integer, Vertex> idToVertex;
        private final int id;
        private final Map<Integer, Integer> edges;
        private boolean visited = false;
        private int distance;

        public Vertex(Map<Integer, Vertex> idToVertex, int id) {
            this.idToVertex = idToVertex;
            this.id = id;
            edges = new HashMap<Integer, Integer>();
        }

        public void add(int neighbor, int weight) {
            edges.put(neighbor, weight);
        }

        public void remove(int neighbor) {
            edges.remove(neighbor);
        }

        public Map<Integer, Integer> edges() {
            return edges;
        }

        public void setVisited() {
            visited = true;
        }

        public boolean wasVisited() {
            return visited;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public void clearbfs() {
            visited = false;
            distance = 0;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(id).append(":: ");
            for (Map.Entry<Integer, Integer> entry : edges.entrySet()) {
                builder.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
            }
            return builder.toString();
        }
    }

    public static class JeaniesRouteFactory {
        private final Map<Integer, Vertex> idToVertex;

        public JeaniesRouteFactory(int n) {
            idToVertex = new HashMap<Integer, Vertex>();
            for (int i = 1; i <= n; i++) {
                idToVertex.put(i, new Vertex(idToVertex, i));
            }
        }

        public void addPair(int left, int right, int weight) {
            idToVertex.get(left).add(right, weight);
            idToVertex.get(right).add(left, weight);
        }

        public JeaniesRoute build() {
            return new JeaniesRoute(idToVertex);
        }
    }

    public static void main(String[] args) throws Exception {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n < 2 || n > 100000) {
            sc.close();
            throw new IllegalArgumentException("N = " + n);
        }
        int k = sc.nextInt();
        if (k < 2 || k > 100000) {
            sc.close();
            throw new IllegalArgumentException("K = " + k);
        }

        Set<Integer> deliveryCities = new HashSet<Integer>();
        for (int i = 0; i < k; i++) {
            int deliveryCity = sc.nextInt();
            if (deliveryCity <= 0 || deliveryCity > n) {
                sc.close();
                throw new IllegalArgumentException("deliveryCity = " + deliveryCity);
            }
            deliveryCities.add(deliveryCity);
        }
        if (deliveryCities.size() != k) {
            sc.close();
            throw new IllegalArgumentException("num deliveryCities = " + deliveryCities.size());
        }
        JeaniesRouteFactory factory = new JeaniesRouteFactory(n);
        for (int i = 1; i < n; i++) {
            int left = sc.nextInt();
            if (left <= 0 || left > n) {
                sc.close();
                throw new IllegalArgumentException("left in pair = " + left);
            }
            int right = sc.nextInt();
            if (right <= 0 || right > n) {
                sc.close();
                throw new IllegalArgumentException("right in pair = " + right);
            }
            int weight = sc.nextInt();
            if (weight < 1 || weight > 1000) {
                sc.close();
                throw new IllegalArgumentException("weight = " + weight);
            }
            factory.addPair(left, right, weight);
        }

        JeaniesRoute jeaniesRoute = factory.build();
        // long startTime = System.currentTimeMillis();
        System.out.println(jeaniesRoute.calculate(deliveryCities));
        // long timeInMs = (System.currentTimeMillis() - startTime);
        // System.out.println("time (in ms) = " + timeInMs);
    }
}