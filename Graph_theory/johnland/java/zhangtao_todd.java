import java.util.*;

public class Dijkstra {

    static List<Vertex> nodes;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nodeCount = scanner.nextInt();
        int edgeCount = scanner.nextInt();
        nodes = new ArrayList<>();
        for (int i=0; i<nodeCount; i++){
            Vertex v = new Vertex("Vertex_"+(i+1));
            nodes.add(v);
        }
        for (int i=0; i<edgeCount; i++){
            int from = scanner.nextInt()-1;
            int to = scanner.nextInt()-1;
            int weight = (int) Math.pow(2,scanner.nextInt());
            nodes.get(from).edges.add(new Edge(nodes.get(to), weight));
            nodes.get(to).edges.add(new Edge(nodes.get(from), weight));
        }

        int sum = addUpCombinations(nodeCount);
        System.err.println(sum);
        System.out.println(Integer.toBinaryString(sum));
    }

    public static int addUpCombinations(int nodeCount){
        int sum = 0;
        for (int i=0; i<nodeCount; i++){
            computePaths(nodes.get(i));
            for(int j=i+1; j<nodeCount; j++){
                sum += nodes.get(j).minDistance;
                System.err.println(nodes.get(i)+" to " +nodes.get(j) + ": " + nodes.get(j).minDistance);
                List<Vertex> path = getShortestPathTo(nodes.get(j));
                System.err.println("Path: " + path);
            }
            for (Vertex n : nodes) {
                n.reset();
            }
        }
        return sum;
    }

    public static void computePaths(Vertex source) {
        source.minDistance = 0;
        Queue<Vertex> vertices = new LinkedList<>();
        vertices.add(source);
        while (!vertices.isEmpty()) {
            Vertex current = vertices.remove();
            for (Edge edge : current.edges) {
                Vertex next = edge.target;
                int weight = edge.weight;
                int distance = current.minDistance + weight;
                if (distance < next.minDistance) {
                    next.minDistance = distance;
                    next.previous = current;
                    vertices.add(next);
                }
            }
        }
    }
    public static List<Vertex> getShortestPathTo(Vertex target) {
        List<Vertex> path = new ArrayList<>();
        for (Vertex v = target; v != null; v = v.previous)
            path.add(v);
        Collections.reverse(path);
        return path;
    }
}
class Edge {
    public final Vertex target;
    public final int weight;

    public Edge(Vertex target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}

class Vertex {
    public final String name;
    public List<Edge> edges = new ArrayList<>();
    public int minDistance = Integer.MAX_VALUE;
    public Vertex previous;

    public Vertex(String name) {
        this.name = name;
    }

    public void reset(){
        minDistance = Integer.MAX_VALUE;
        previous = null;
    }

    @Override
    public String toString() {
        return name;
    }
}