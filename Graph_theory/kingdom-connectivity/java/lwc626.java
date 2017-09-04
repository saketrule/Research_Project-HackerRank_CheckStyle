import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collection;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author lwc626
 */
public class Solution {
 public static void main(String[] args) {
  InputStream inputStream = System.in;
  OutputStream outputStream = System.out;
  MyInputReader in = new MyInputReader(inputStream);
  MyOutputWriter out = new MyOutputWriter(outputStream);
  Kingdom_Connectivity solver = new Kingdom_Connectivity();
  solver.solve(1, in, out);
  out.close();
 }
}

class Kingdom_Connectivity {

    static final int mod = 1000000000;

    public void solve(int testNumber, MyInputReader in, MyOutputWriter out) {
        int N = in.nextInt(), M = in.nextInt();
        int[] from = new int[M], to = new int[M];
        IOUtils.readIntArrays(in, from, to);
        ArrayUtils.decreaseByOne(from, to);
        int[][] graph = GraphUtils.buildSimpleGraph(N, from, to, true);
        getDAG(out, graph);
        //IOUtils.printIntArrays( out,color );

        int[] belongCnt = new int[bcnt + 1];
        for (int i = 0; i < N; i++)
            belongCnt[color[i]]++;

        int[] dp = new int[bcnt + 1];
        Arrays.fill(dp, 0);
        HashMap<Integer, ArrayList<Integer>> G = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 1; i <= bcnt; i++) G.put(i, new ArrayList<Integer>());
        int[] degree = new int[bcnt + 1];
        Arrays.fill( degree , 0 );

        for (int i = 0; i < M; i++) {
            from[i] = color[from[i]];
            to[i] = color[to[i]];
            if (from[i] != to[i]) {
                G.get(from[i]).add(to[i]);
            }else dp[ from[i] ] = -1;
        }

        
        Queue<Integer> Q = new LinkedList<Integer>();

        Q.offer( color[0] );
        boolean [] mark = new boolean[ bcnt + 1];
        Arrays.fill( mark , false ) ;
        mark[ color[0] ] = true;
        while (!Q.isEmpty() ){
            int now = Q.poll();
            for (int v : G.get(now)) {
               ++degree[v];
                if( !mark[v] ){
                    Q.offer( v );
                    mark[v] = true;
                }
            }
        }

        if( dp[color[0] ] == 0 )
            dp[color[0]] = 1;

        Q.offer(color[0]);

        while (!Q.isEmpty()) {
            int now = Q.poll();
            for (int v : G.get(now)) {
                if (dp[now] == -1 || dp[v] == -1 )
                    dp[v] = -1;
                else
                    dp[v] = (dp[v] + dp[now]) % mod;
                if (--degree[v] == 0)
                    Q.offer(v);
            }
        }
        //out.printLine( dp[ color[N-1] ] );
        out.printLine(dp[color[N - 1]] != -1 ? dp[color[N - 1]] : "INFINITE PATHS");

    }

    static int dfsIndex, stackTop, bcnt;
    static int[] color, minDfsNumber, dfsNumber, stack;
    static boolean[] inStack;

    public static int[] getDAG(MyOutputWriter out, int[][] graph) {

        int vertexCount = graph.length;
        color = new int[vertexCount];
        minDfsNumber = new int[vertexCount];
        dfsNumber = new int[vertexCount];
        stack = new int[vertexCount + 1];
        inStack = new boolean[vertexCount];

        dfsIndex = 0;
        stackTop = 0;
        bcnt = 0;

        Arrays.fill(color, -1);
        Arrays.fill(dfsNumber, 0);
        for (int i = 0; i < vertexCount; i++)
            if (color[i] == -1) {
                tarjan(graph, i);
            }
        //System.out.println( dfsIndex +" " + stackTop +" "+ bcnt);
        //IOUtils.printIntArrays( out,dfsNumber ,minDfsNumber );
        return color;
    }

    public static void tarjan(int[][] graph, int u) {
        dfsNumber[u] = minDfsNumber[u] = ++dfsIndex;
        stack[++stackTop] = u;
        inStack[u] = true;
        for (int v : graph[u]) {
            if (dfsNumber[v] == 0) {
                tarjan(graph, v);
                minDfsNumber[u] = Math.min(minDfsNumber[u], minDfsNumber[v]);
            } else if (inStack[v]) {
                minDfsNumber[u] = Math.min(minDfsNumber[u], dfsNumber[v]);
            }
        }
        if (dfsNumber[u] == minDfsNumber[u]) {
            int v;
            ++bcnt;
            do {
                v = stack[stackTop--];
                inStack[v] = false;
                color[v] = bcnt;
            } while (v != u);
        }
    }
}

class MyInputReader {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public MyInputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream));
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }
    
    }

class MyOutputWriter {
    private final PrintWriter writer;

    public MyOutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(outputStream);
    }

    public MyOutputWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void print(Object...objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i != 0)
                writer.print(' ');
            writer.print(objects[i]);
        }
    }

    public void printLine(Object...objects) {
        print(objects);
        writer.println();
    }

    public void close() {
        writer.close();
    }

}

class IOUtils {
    public static void readIntArrays( MyInputReader in,int[]... arrays ){
        for(int i = 0 ; i < arrays[0].length; i++ )
            for( int j = 0 ; j < arrays.length ; j ++ )
                arrays[j][i] = in.nextInt();
    }
    }

class ArrayUtils {
    public static void decreaseByOne(int[]... arrays){
        for(int[] array:arrays)
            for( int i = 0 ; i < array.length ; i++ )
                array[i] --;
    }

}

class GraphUtils {
    public static int[][] buildGraph(int vertexCount, int[] from, int[] to ,boolean isDirected ) {
        int edgeCount = from.length;
        int[] degree = new int[vertexCount];
        for (int i = 0; i < edgeCount; i++) {
            degree[from[i]]++;
            if(!isDirected)degree[to[i]]++;
        }
        int[][] graph = new int[vertexCount][];
        for (int i = 0; i < vertexCount; i++) {
            graph[i] = new int[degree[i]];
        }
        for (int i = 0; i < edgeCount; i++) {
            graph[from[i]][--degree[from[i]]] = i;
            if(!isDirected)graph[to[i]][--degree[to[i]]] = i;
        }
        return graph;
    }

    public static int otherVertex(int vertex, int from, int to) {
        return from + to - vertex;
    }

    public static int[][] buildSimpleGraph(int vertexCount, int[] from, int[] to,boolean isDirected) {
        int[][] graph = buildGraph(vertexCount, from, to,isDirected);
        simplifyGraph(graph, from, to);
        return graph;
    }
    private static void simplifyGraph(int[][] graph, int[] from, int[] to) {
        for (int i = 0; i < graph.length; i++)
            for (int j = 0; j < graph[i].length; j++)
                graph[i][j] = otherVertex(i, from[graph[i][j]], to[graph[i][j]]);
    }
}