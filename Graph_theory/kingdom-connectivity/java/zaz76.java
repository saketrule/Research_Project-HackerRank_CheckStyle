/* Enter your code here. Read input from STDIN. Print output to STDOUT */
import java.io.*;
import java.util.*;

public class Solution{
 public static void main(String[] args) throws Exception{
  boolean test = false;
  if(args.length > 0 && args[0].equals("test")){
   test = true;
  }


  Parser in = new Parser(System.in);
  int n = in.nextInt();
  int m = in.nextInt();

  ArrayList<ArrayList<Integer>> forward_graph = new ArrayList<ArrayList<Integer>>();
  ArrayList<ArrayList<Integer>> reverse_graph = new ArrayList<ArrayList<Integer>>();

  for(int i = 1;i<=n;++i){
   forward_graph.add(new ArrayList<Integer>());
   reverse_graph.add(new ArrayList<Integer>());
  }

  for(int i = 0;i<m;++i){
   int from = in.nextInt() - 1;
   int to = in.nextInt() - 1;

   forward_graph.get(from).add(to);
   reverse_graph.get(to).add(from);
  }

  // check if the graph has a cycle that causes infinite possible paths
  boolean bad_cycle = cycle_find(0,n-1,forward_graph,reverse_graph);

  if(test){
   for(int i = 0;i<n;++i){
    for(Integer j : forward_graph.get(i)){
     System.out.println(i + "  " + j);
    }
   }
  }

  if(bad_cycle){
   System.out.println("INFINITE PATHS");
  }
  else{
   System.out.println(solve(0,n-1,forward_graph));
  }
 }

 private static boolean cycle_find(int start, int end, ArrayList<ArrayList<Integer>> forward, ArrayList<ArrayList<Integer>> reverse){
  HashSet<Integer> forward_reachable = search_bfs(forward, start);
  HashSet<Integer> reverse_reachable = search_bfs(reverse, end);

  // find intersection of the reachable sets
  HashSet<Integer> intersection = new HashSet<Integer>();
  for(Integer i : forward_reachable){
   if(reverse_reachable.contains(i)){
    intersection.add(i);
   }
  }

  // remove all edges not in the intersection
  for(List<Integer> l : forward){
   HashSet<Integer> to_remove = new HashSet<Integer>();
   for(Integer i : l){
    if(!intersection.contains(i)){
     to_remove.add(i);
    }
   }
   for(Integer i : to_remove){
    l.remove(new Integer(i));
   }
  }

  return cycle_dfs(forward,0); 
 }

 private static HashSet<Integer> search_bfs(ArrayList<ArrayList<Integer>> graph, int start){
  HashSet<Integer> ret = new HashSet<Integer>();
  ret.add(start);

  Queue<Integer> queue = new LinkedList<Integer>();
  queue.add(start);
  boolean[] visited = new boolean[graph.size()];
  visited[start] = true;

  while(queue.peek() != null){
   int curr_node = queue.poll();
   List<Integer> neighbors = graph.get(curr_node);

   for(Integer i : neighbors){
    if(!visited[i]){
     visited[i] = true;
     ret.add(i);
     queue.add(i);
    }
   }
  }

  return ret;
 }

 private static int[] degree_bfs(ArrayList<ArrayList<Integer>> graph, int start){
  int[] degrees = new int[graph.size()];

  Queue<Integer> queue = new LinkedList<Integer>();
  queue.add(start);
  boolean[] visited = new boolean[graph.size()];
  visited[start] = true;

  while(queue.peek() != null){
   int curr_node = queue.poll();
   List<Integer> neighbors = graph.get(curr_node);

   for(Integer i : neighbors){
    degrees[i]++;
    if(!visited[i]){
     visited[i] = true;
     queue.add(i);
    }
   }
  }

  return degrees;
 }

 private static boolean cycle_dfs(ArrayList<ArrayList<Integer>> graph, int start){
  boolean ret = false;

  Stack<Integer> stack = new Stack<Integer>();
  stack.push(start);

  // 0 = unvisited, 1 = expanding, 2 = visited
  short[] colors = new short[graph.size()];
  colors[start] = 1;

  while(!stack.empty() && !ret){
   int curr_node = stack.peek();
   List<Integer> neighbors = graph.get(curr_node);
   
   boolean continuing = false;
   for(Integer i : neighbors){
    if(colors[i] == 0){
     continuing = true;
     stack.push(i);
    }
    else if(colors[i] == 1){
     ret = true;
    }
   }

   if(!continuing){
    colors[curr_node] = 2;
    stack.pop();
   }
   else{
    colors[curr_node] = 1;
   }
  }

  return ret;
 }

 private static int solve(int start, int end, ArrayList<ArrayList<Integer>> graph){
  int[] num_paths = new int[graph.size()];
  num_paths[start] = 1;

  final int[] in_degree = degree_bfs(graph,start);

  Comparator<Integer> c = new Comparator<Integer>(){
   public int compare(Integer a, Integer b){
    int ret = 0;
    if(in_degree[a] < in_degree[b]){
     ret = -1;
    }
    else if(in_degree[a] > in_degree[b]){
     ret = 1;
    }
    return ret;
   }

   // not used
   public boolean equals(Object obj){
    return true;
   }
  };

  PriorityQueue<Integer> queue = new PriorityQueue<Integer>(11,c);
  queue.add(start);

  boolean[] visited = new boolean[graph.size()];
  visited[start] = true;

  while(queue.peek() != null){
   int curr_node = queue.poll();
   List<Integer> neighbors = graph.get(curr_node);

   for(Integer i : neighbors){
    in_degree[i]--;
    num_paths[i] = (num_paths[curr_node] + num_paths[i]) % 1000000000;
    if(!visited[i]){
     visited[i] = true;
     queue.add(i);
    }
    else{
     queue.remove(new Integer(i));
     queue.add(i);
    }
   }
  }
  return num_paths[end];
 }

 static class Parser {
        final private int BUFFER_SIZE = 1 << 16;
 
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
 
        public Parser(InputStream in)
        {
                din = new DataInputStream(in);
                buffer = new byte[BUFFER_SIZE];
                bufferPointer = bytesRead = 0;
        }
 
        public int nextInt() throws Exception
        {
                int ret = 0;
                byte c = read();
                while (c <= ' ') c = read();
                //boolean neg = c == '-';
                //if (neg) c = read();
                do
                {
                        ret = ret * 10 + c - '0';
                        c = read();
                } while (c > ' ');
                //if (neg) return -ret;
                return ret;
        }

        public long nextLong() throws Exception
        {
                long ret = 0;
                byte c = read();
                while (c <= ' ') c = read();
                //boolean neg = c == '-';
                //if (neg) c = read();
                do
                {
                        ret = ret * 10 + c - '0';
                        c = read();
                } while (c > ' ');
                //if (neg) return -ret;
                return ret;
        }
 
        public double nextDouble() throws Exception {
                double toRet = 0.0;
                int ret = 0;
                byte c = read();
                while (c <= ' ') c = read();
                do
                {
                        ret = ret * 10 + c - '0';
                        c = read();
                } while (c > ' ' && c != '.');
                int ret2 = 0;
                double mult = 1.0;
                if (c == '.') {
                        c = read();
                        do {
                                ret2 = ret2 * 10 + c - '0';
                                mult *= .1;
                                c = read();
                        } while ( c > ' ');
                        toRet += ret2*mult;
                }
                return toRet + ret;
        }
 
        public String nextString(int length) throws Exception {
                StringBuilder br = new StringBuilder();
                byte c = read();
                while(c <= ' ') c = read();
                for(int i = 0; i < length; ++i) {
                        br.append((char)c);
                        c = read();
                }
                return br.toString();
        }
 
        public String next() throws Exception{
                StringBuilder br = new StringBuilder();
                byte c = read();
                while(c <= ' ') c = read();
                while(c > ' ') {
                        br.append((char)c);
                        c = read();
                }
                return br.toString();
        }
 
        private void fillBuffer() throws Exception
        {
                bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
                if (bytesRead == -1) buffer[0] = -1;
        }
 
        private byte read() throws Exception
        {
                if (bufferPointer == bytesRead) fillBuffer();
                return buffer[bufferPointer++];
        }
 }
}