import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class DiGraph{
    static final List<List<Integer>> incrementsList = getIncrements();
    static List<List<Integer>> getIncrements(){
        List<List<Integer>> result = new ArrayList<>();
        result.add(0, null);
        //result.add(1, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        result.add(1, null);
        result.add(2, Arrays.asList(2, 4, 6, 8));
        //result.add(3, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        result.add(3, null);
        result.add(4, Arrays.asList(4, 8, 2, 6));
        result.add(5, Arrays.asList(5));
        result.add(6, Arrays.asList(6, 2, 8, 4));
        //result.add(7, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        result.add(7, null);
        result.add(8, Arrays.asList(8, 6, 4, 2));
        //result.add(9, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        result.add(9, null);
        return result;
    }

    private int V;
    private Map<Integer, Integer> adj[];//map dest->weight from src index in the array

    DiGraph(int V){
        this.V = V;
        adj = new Map[V + 1];
        for (int i = 1; i <= V; i++){
            adj[i] = new HashMap<>();
        }
    }
    void addEdge(int u, int v, int r){
        r %= 10;//only first digit matters
        adj[u].put(v, r);//can get reverse paths after finding normal path
        adj[v].put(u, (10 - r));//going backwards is diff cost
        // multiple src edges would make this inefficient
    }

    void dfs(int src, boolean[] visited, boolean[] processed, int[] parent, Set[] pathList){
        visited[src] = true;

        for (Integer dest : adj[src].keySet()){
            int weight = adj[src].get(dest);

            if (!visited[dest]){
                parent[dest] = src;
                processEdge(src, dest, weight, pathList);
                dfs(dest, visited, processed, parent, pathList);
            }
            else if (!processed[dest]){
                //visited but not processed - this is back edge to a cycle
                processCycle(dest, src, parent, pathList);

                //no parent check because
                //could be a cycle of length 2 if input puts 2 roads between 2 cities (2 edges in each direction)
            }
        }

        //mark processed
        processed[src] = true;

    }

    void processEdge(int src, int dest, int weight, Set[] pathList){
        Set<Integer> pathsToDest = pathList[dest];
        pathsToDest.add(weight);

        //also extend possible values to source by adding current edge weight and adding to paths set of the dest
        Set<Integer> pathsToSrc = pathList[src];
        for (Integer cost : pathsToSrc){
            pathsToDest.add((weight + cost) % 10);
        }
    }

    void processCycle(int src, int incToSrc, int[] parent, Set[] pathList){
        //first determine cycle length by adding all edge lengths
        //might just make parent be an object holding parent vertex, weight
        Set<Integer> cycleVertices = new HashSet<>();
        int par = incToSrc;
        int next = src;
        int length = 0;
        while (par != src){
            cycleVertices.add(par);
            int weight = adj[par].get(next);
            length += weight;

            next = par;
            par = parent[par];
        }
        cycleVertices.add(par);
        length += adj[par].get(next);//add the last link (src of the cycle)

        length %= 10;

        if ((length) == 0){
            return;//cycle with 0 at the first digit doesn't change anything
        }

        //cycle of these lengths result in all vertices being able to reach any number
        if (length == 1 || length == 3 || length == 7 || length == 9){
            for (Integer cycleVertex : cycleVertices){
                for (int i = 1; i <= 9; i++){
                    pathList[cycleVertex].add(i);
                }
            }
        }
        else{
            //get list of possible increments through this cycle
            List<Integer> increments = incrementsList.get(length);
            for (Integer cycleVertex : cycleVertices){
                for (Integer inc : increments){
                    Set<Integer> paths = new HashSet<Integer>(pathList[cycleVertex]);//copy to avoid concurrent modification
                    for (Integer key : paths){
                        pathList[cycleVertex].add(key + inc);
                    }
                }
            }
        }
    }

    void printPathDigits(){
        boolean[] visited = new boolean[V+1];
        boolean[] processed = new boolean[V+1];
        int[] parent = new int[V+1];
        //indexed by source, containing dest, digit of weight of path
        Set<Integer>[] pathList = new Set[V+1];//use arraylist for generics?
        for (int i = 1; i <= V; i++){
            pathList[i] = new HashSet<Integer>();
        }

//        //dfs
//        for (int i = 1; i <= V; i++){
//            if (!hasIncEdge[i] && !processed[i]){
//                dfs(i, visited, processed, parent, pathList);
//            }
//        }
        for (int i = 1; i <= V; i++){
            if (!processed[i]){
                dfs(i, visited, processed, parent, pathList);
            }
        }

        //go through all the vertices again and count the number of digits
        int[] digits = new int[10];
        for (int i = 1; i <= V; i++){
            Set<Integer> paths = pathList[i];//get paths ending at i, where value = cost
            for (Integer cost : paths){//cost digits ending at this index
                digits[cost % 10]++;
                digits[(10 - cost % 10) % 10]++;//add the reverse path
            }
        }
        for (int d = 0; d < 10; d++){
            System.out.println(digits[d]);
        }
    }
}

class toll_cost_digits {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int e = in.nextInt();

        DiGraph g = new DiGraph(n);

        for(int a0 = 0; a0 < e; a0++){
            int x = in.nextInt();
            int y = in.nextInt();
            int r = in.nextInt();

            g.addEdge(x, y, r);
        }
        g.printPathDigits();
    }
}