import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;


/*
 * 5/10 with wrong answer
 */
public class Solution {
    
    private static final boolean STD_IN = true;
    private static final String TEST_INPUT_FILE = "tests/kingdomConnectivity/input00.txt";
    private static final long MOD = 1000000000;
    
    private static Map<Long,Vertex> verticies = null;

    public static final void main(String[] args) {
        TestCases.populate();
        int numberOfCities = TestCases.numberOfCities;
        int[][] roads = TestCases.roads;
        
        verticies = new HashMap<Long,Vertex>();
        for (long v=1; v<=numberOfCities; v++) {
            Vertex vertex = new Vertex(v);
            verticies.put(v,vertex);
        }

        for (int[] road : roads) {
            long from = road[0];
            long to = road[1];
            Vertex fromVertex = verticies.get(from);
            Vertex toVertex = verticies.get(to);
            Edge edge = new Edge(fromVertex, toVertex);
            toVertex.edges.add(edge);
        }

        long distance = getNumberOfPaths(verticies.get((long)numberOfCities));
        if (distance!=Long.MAX_VALUE) System.out.println(distance);
        else System.out.println("INFINITE PATHS");
    }
    
    private static final void populateNumberOfPaths(Vertex vertex, Map<Long,Boolean> path) {
        for (Edge e : vertex.edges) {
            Vertex toVertex = e.from;
            
            //Cycle detection
            Boolean cycle = path.get(toVertex.value);
            if (cycle!=null && cycle) {
                vertex.partOfCycle = true;
                toVertex.partOfCycle = true;
                continue;
            }
            
            //Not in a cycle
            path.put(toVertex.value, true);
            
            //Calculate distance, if not already calculated
            if (!toVertex.computedDistance) {
                populateNumberOfPaths(toVertex,path);
            }
            
            //Check if we have found the source vertex
            if (toVertex.value==1) {
                toVertex.distance = (toVertex.partOfCycle) ? Long.MAX_VALUE : 1;
            }
            
            //Check if we have entered a cycle
            if (toVertex.distance==Long.MAX_VALUE) {
                vertex.distance = Long.MAX_VALUE;
                break;
            }
            
            //Update the distance
            vertex.distance = (vertex.distance+toVertex.distance)%MOD;
            
            path.put(toVertex.value, false);
        }
        
        if (vertex.partOfCycle && vertex.distance!=0) {
            vertex.distance = Long.MAX_VALUE;
        }
        
        vertex.computedDistance = true;
    }
    
    private static final long getNumberOfPaths(Vertex vertex) {
        Map<Long,Boolean> inPath = new HashMap<Long,Boolean>();
        inPath.put(vertex.value, true);
        populateNumberOfPaths(vertex,inPath);
        return verticies.get(vertex.value).distance;
    }

    public static final class Vertex implements Comparable<Vertex> {

        private long value = Long.MIN_VALUE;
        private List<Edge> edges = new CopyOnWriteArrayList<Edge>();

        private long distance = 0;
        private boolean computedDistance = false;
        private boolean partOfCycle = false;
        
        public Vertex(long value) {
            this.value = value;
        }

        public Vertex(Vertex vertex) {
            this(vertex.value);
            this.edges = new ArrayList<Edge>();
            for (Edge e : vertex.edges) {
                this.edges.add(new Edge(e));
            }
        }

        public long getValue() {
            return value;
        }

        public void addEdge(Edge e) {
            edges.add(e);
        }

        public List<Edge> getEdges() {
            return edges;
        }

        public boolean pathTo(Vertex v) {
            for (Edge e : edges) {
                if (e.to.equals(v)) return true;
            }
            return false;
        }

        public int compareTo(Vertex v) {
            if (this.value<v.value) return -1;
            if (this.value>v.value) return 1;
            return 0;
        }

        public boolean equals(Object v1) {
            if (!(v1 instanceof Vertex)) return false;

            Vertex v = (Vertex)v1;

            boolean values = this.value==v.value;
            if (!values) return false;

            return true;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("vertex:").append(" value=").append(value).append("\n");
            for (Edge e : edges) {
                builder.append("\t").append(e.toString());
            }
            return builder.toString();
        }
    }

    public static final class Edge {

        private Vertex from = null;
        private Vertex to = null;

        public Edge(Vertex from, Vertex to) {
            if (from==null || to==null) throw (new NullPointerException("Both 'to' and 'from' Verticies need to be non-NULL."));
            this.from = from;
            this.to = to;
        }

        public Edge(Edge e) {
            this(e.from, e.to);
        }

        public Vertex getFromVertex() {
            return from;
        }

        public Vertex getToVertex() {
            return to;
        }

        public boolean equals(Object e1) {
            if (!(e1 instanceof Edge)) return false;

            Edge e = (Edge)e1;

            boolean froms = this.from.equals(e.from);
            if (!froms) return false;

            boolean tos = this.to.equals(e.to);
            if (!tos) return false;

            return true;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("edge:").append(" [").append(from.value).append("]")
            .append(" -> ")
            .append("[").append(to.value).append("]")
            .append("\n");
            return builder.toString();
        }
    }

    private static final class TestCases {
        
        private static int numberOfCities = 0;
        private static int[][] roads = null;
        
        private static final void populate() {
            List<String> inputs = null;
            try {
                inputs = getInput(STD_IN);
                populateTests(inputs);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (inputs!=null) {
                
            }
        }
        
        private static final void populateTests(List<String> inputs) {
            String string = inputs.get(0);
            String[] split = string.split(" ");
            numberOfCities = Integer.parseInt(split[0]); //number of cities
            int numberOfRoads = Integer.parseInt(split[1]); //number of roads
            roads = new int[numberOfRoads][];
            int currentLine = 1;
            for (int road=0; road<numberOfRoads; road++) {
                string = inputs.get(currentLine++);
                split = string.split(" ");
                int from = Integer.parseInt(split[0]);
                int to = Integer.parseInt(split[1]);
                int[] roadArray = new int[]{from,to};
                roads[road] = roadArray;
            }
        }
        
        private static final List<String> getInput(boolean stdInput) throws IOException {
            BufferedReader stdin = null;
            if (stdInput) {
                stdin = new BufferedReader(new InputStreamReader(System.in));
            } else {
                stdin = new BufferedReader(new FileReader(TEST_INPUT_FILE));
            }
            List<String> inputs = new ArrayList<String>();
            String input = null;
            while((input = stdin.readLine())!=null) {
                inputs.add(input);
            }
            return inputs;
        }
    }
}