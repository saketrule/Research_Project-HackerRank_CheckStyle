import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class Solution implements Runnable { 
 
 
    public void solve() throws IOException {
     //System.out.println("ssup");
     int t = nextInt();
     
     while(t!=0) {
      t--;
      int n = nextInt();
      int m = nextInt();
      
      ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
      HashSet<Integer> l1 = new HashSet<Integer>();
      HashSet<Integer> l2 = new HashSet<Integer>();
      for(int i=0;i<=n;i++) {
       adj.add(new ArrayList<Integer>());
       l1.add(i);       
      }
      
      l1.remove(0);
      while(m!=0) {
       m--;
       int u = nextInt();
       int v = nextInt();
       adj.get(u).add(v);
       adj.get(v).add(u);
      }
      int s = nextInt();
      //System.out.println(adj);
      Queue<Integer> q = new LinkedList<Integer>();
      q.add(s);
      l1.remove(s);
      
      int[] dist = new int[n+1];
      int[] visited = new int[n+1];
      
      
      while(!q.isEmpty()) {       
       int cur = q.poll();
       visited[cur] = 1;
       //writer.println(Arrays.toString(dist));
       //writer.println(Arrays.toString(visited));
       for(int i: adj.get(cur)) {

         l1.remove(i);
         l2.add(i);

        
       }
       
       for(int i: l1) {

         if(visited[i] == 0) {
         dist[i] = dist[cur]+1;        
         q.add(i);
                            visited[i] = 1;
                        }

       }

       l1 = new HashSet<Integer>(l2);
                l2 = new HashSet<Integer>();
      }
      
      
      for(int i=1;i<=n;i++) {
       if(i!=s) {
        writer.print(dist[i]+" ");
       }
      }
      
      writer.println();
      
      
     }
    }


 public static void main(String[] args) { 
        new Solution().run();
    }

    BufferedReader reader;
    StringTokenizer tokenizer;
    PrintWriter writer;

    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
            writer = new PrintWriter(System.out);
            solve();
            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }

    String nextToken() throws IOException {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }
}