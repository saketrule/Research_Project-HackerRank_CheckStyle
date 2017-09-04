import java.util.*;

public class Solution {

    class Vertex{
        private int paths = 0;
        private int inRoutes = 0;
        
        private List<Edge> inbound = new ArrayList<Edge>();
        private List<Edge> outbound = new ArrayList<Edge>();

        public List<Edge> getOutbound() {
            return outbound;
        }

        public List<Edge> getInbound() {
            return inbound;
        }

        public void addOutbound(Edge e){
            outbound.add(e);
        }

        public void addInbound(Edge e){
            inbound.add(e);
        }

        public void addRoute(Edge e) {
            paths = (paths + e.paths) % 1000000000;
            inRoutes++;
        }

        private Integer reachableInboundSize = null;

        public boolean allRoutes(){
            lazyReachable();
            return inRoutes ==  reachableInboundSize;
        }

        public boolean moreThenAllRoutes(){
            lazyReachable();
            return inRoutes >  reachableInboundSize;
        }


        private void lazyReachable() {
            if (reachableInboundSize == null){
                reachableInboundSize = 0;
                for (Edge e : inbound)
                    if (e.isReachable())
                        reachableInboundSize++;
            }
        }
    }

    class Edge{
        private int paths = 0;
        private Vertex from;
        private Vertex to;
        public boolean reachable1 = false;
        public boolean reachable2  = false;

        public boolean isReachable(){
            return reachable1 && reachable2;
        }

        Edge(Vertex from, Vertex to) {
            this.from = from;
            this.to = to;
        }

        public Vertex getFrom() {
            return from;
        }

        public Vertex getTo() {
            return to;
        }

        public int getPaths() {
            return paths;
        }

        public void setPaths(int paths) {
            this.paths = paths;
        }
    }

    private int n;
    private Vertex[] vertices;

    public void read(){
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] split = line.split(" ");
        n = Integer.parseInt(split[0]);
        vertices = new Vertex[n];
        for (int i = 0; i < n; i++)
            vertices[i] = new Vertex();
        int m = Integer.parseInt(split[1]);
        for (int i = 0; i < m; i++){
            line = sc.nextLine();
            split = line.split(" ");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);

            Edge e = new Edge(vertices[a-1], vertices[b-1]);
            vertices[a-1].addOutbound(e);
            vertices[b-1].addInbound(e);
        }

    }


    private void visit1(Vertex v, Set<Vertex> visited){
        visited.add(v);
        for (Edge e : v.getOutbound()){
            e.reachable1 = true;
            if (!visited.contains(e.getTo())){
                visit1(e.getTo(),visited);
            }
        }
    }

    private void visit2(Vertex v, Set<Vertex> visited){
        visited.add(v);
        for (Edge e : v.getInbound()){
            e.reachable2 = true;
            if (!visited.contains(e.getFrom())){
                visit2(e.getFrom(),visited);
            }
        }
    }

    private boolean infinite = false;
    private boolean endReachable = false;

    private void visit3(Vertex v, Set<Vertex> visited, Set<Vertex> discovery){
        if (v.equals(vertices[n-1]))
            endReachable = true;
        visited.add(v);
        discovery.add(v);
        for (Edge e : v.getOutbound()){
            if (!e.isReachable())
                continue;
            
            if (discovery.contains(e.getTo())){
                infinite = true;
            }
            if (!visited.contains(e.getTo())){
                visit3(e.getTo(),visited, discovery);
            }
        }
        discovery.remove(v);
    }


    public void precompute(){
        visit1(vertices[0], new HashSet<Vertex>());
        visit2(vertices[n-1], new HashSet<Vertex>());
        visit3(vertices[0], new HashSet<Vertex>(), new HashSet<Vertex>());
    }

    public void compute(){
        if (infinite && endReachable){
            System.out.println("INFINITE PATHS");
            return;
        }

        Stack<Vertex> s = new Stack<Vertex>();
        s.push(vertices[0]);
        vertices[0].paths = 1;
        while (!s.empty()){
            Vertex v = s.pop();
            for (Edge e : v.getOutbound()){
                Vertex to = e.getTo();
                if (!e.isReachable())
                    continue;
                e.paths = v.paths;
                to.addRoute(e);
                if (to.allRoutes())
                    s.push(e.getTo());
            }
        }

        System.out.println(vertices[n-1].paths);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.read();
        s.precompute();
        s.compute();
    }
}