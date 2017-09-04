import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class MinPenalty {
    private final Map<Integer, Vertex> idToVertex;

    public MinPenalty(Map<Integer, Vertex> idToVertex) {
        this.idToVertex = idToVertex;
    }

    private static class Penalty {
        private final TreeSet<Integer> values;

        public Penalty() {
            values = new TreeSet<Integer>();
        }

        public void add(int weight) {
            boolean done = false;
            Set<Integer> removeSet = new HashSet<Integer>();
            for (Integer value : values) {
                if ((value | weight) == weight) {
                    done = true;
                    break;
                }
                if ((value | weight) == value) {
                    removeSet.add(value);
                }
            }
            if (!removeSet.isEmpty()) {
                values.removeAll(removeSet);
            }
            if (done) {
                return;
            }
            values.add(weight);
        }

        public int min() {
            return values.isEmpty() ? -1 : values.first();
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(min() + ": ");
            for (Integer value : values) {
                builder.append(value).append(":");
            }
            return builder.toString();
        }
    }

    private void crossProductBitOr(Penalty left, Penalty right, Penalty vertexPenalty, Penalty destPenalty) {
        if (!destPenalty.values.isEmpty()) {
            if (left.min() >= destPenalty.min() || right.min() >= destPenalty.min()) {
                return;
            }
        }
        if (left == vertexPenalty) {
            return;
        }

        for (Integer curLeft : left.values) {
            if (vertexPenalty.values.contains(curLeft)) {
                continue;
            }
            for (Integer curRight : right.values) {
                if (vertexPenalty.values.contains(curRight)) {
                    continue;
                }
                Integer bitOr = curLeft | curRight;
                vertexPenalty.add(bitOr);
            }
        }
    }

    private void updatePenalties(List<Penalty> penalties, int left, int right, Penalty rightPenalty, Penalty destPenalty) {
        Penalty vertexPenalty = penalties.get(right);
        Penalty leftPenalty = penalties.get(left);

        crossProductBitOr(leftPenalty, rightPenalty, vertexPenalty, destPenalty);
    }

    public int calculate(int source, int dest) {
        List<Penalty> penalties = new ArrayList<Penalty>();
        for (int i = 0; i <= idToVertex.size(); i++) {
            Penalty vertexPenalty = new Penalty();
            penalties.add(vertexPenalty);
        }
        Vertex sourceVertex = idToVertex.get(source);
        for (Map.Entry<Integer, Penalty> neighborEntry : sourceVertex.edges.entrySet()) {
            Penalty vertexPenalty = penalties.get(neighborEntry.getKey());
            for (Integer weight : neighborEntry.getValue().values) {
                vertexPenalty.add(weight);
            }
        }
        // System.out.println("k = " + 1 + ", dest = " + dest + ", cur penalty = " + penalties.get(dest));

        Penalty destPenalty = penalties.get(dest);
        for (int k = 2; k < idToVertex.size(); k++) {
            for (Vertex v : idToVertex.values()) {
                for (Map.Entry<Integer, Penalty> neighborEntry : v.edges.entrySet()) {
                    updatePenalties(penalties, v.id, neighborEntry.getKey(), neighborEntry.getValue(), destPenalty);
                }
            }
            // System.out.println("k = " + k  + ", dest = " + dest + ", cur penalty = " + penalties.get(dest));
        }

        return penalties.get(dest).min();
    }

    private static class Vertex {
        private int id;
        private Map<Integer, Penalty> edges;

        public Vertex(int id) {
            this.id = id;
            edges = new HashMap<Integer, Penalty>();
        }

        public void add(int neighbor, int weight) {
            Penalty neighborPenalty = edges.get(neighbor);
            if (neighborPenalty == null) {
                neighborPenalty = new Penalty();
                neighborPenalty.add(weight);
                edges.put(neighbor, neighborPenalty);
            } else {
                neighborPenalty.add(weight);
            }
        }
    }

    public static class MinPenaltyFactory {
        private final Map<Integer, Vertex> idToVertex;

        public MinPenaltyFactory(int n) {
            idToVertex = new HashMap<Integer, Vertex>();
            for (int i = 1; i <= n; i++) {
                idToVertex.put(i, new Vertex(i));
            }
        }

        public void addPair(int left, int right, int weight) {
            idToVertex.get(left).add(right, weight);
            idToVertex.get(right).add(left, weight);
        }

        public MinPenalty build() {
            MinPenalty minPenalty = new MinPenalty(idToVertex);
            return minPenalty;
        }
    }

    public static void main(String[] args) throws Exception {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n < 1 || n > 1000) {
            sc.close();
            throw new IllegalArgumentException("N = " + n);
        }
        int m = sc.nextInt();
        if (m < 1 || m > 10000) {
            sc.close();
            throw new IllegalArgumentException("M = " + m);
        }

        MinPenaltyFactory factory = new MinPenaltyFactory(n);
        for (int i = 0; i < m; i++) {
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
            if (weight < 1 || weight >= 1024) {
                sc.close();
                throw new IllegalArgumentException("weight = " + weight);
            }
            factory.addPair(left, right, weight);
        }
        int source = sc.nextInt();
        if (source <= 0 || source > n) {
            sc.close();
            throw new IllegalArgumentException("source = " + source);
        }
        int dest = sc.nextInt();
        if (dest <= 0 || dest > n || source == dest) {
            sc.close();
            throw new IllegalArgumentException("dest = " + dest);
        }

        MinPenalty minPenalty = factory.build();
        // long startTime = System.currentTimeMillis();
        System.out.println(minPenalty.calculate(source, dest));
        // long timeInMs = (System.currentTimeMillis() - startTime);
        // System.out.println("time (in ms) = " + timeInMs);
    }
}