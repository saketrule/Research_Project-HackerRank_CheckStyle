import java.util.*;
import java.io.*;

public class Solution /*implements Runnable*/{

    BufferedReader in;
    StringTokenizer str = null;

    private int nextInt() throws Exception{
 if (str == null || !str.hasMoreElements())
     str = new StringTokenizer(in.readLine());
 return Integer.parseInt(str.nextToken());
    }


    public void run(){
 try{
     solve();
 }catch(Exception ex){
     ex.printStackTrace();
 }
    }
    int n,m, mod = 1000000000;
    List<pair> []g, gt;
    int []color, prev;
    boolean []cycle;
    ArrayList<Integer> topol;
    
    private void solve() throws Exception{

 in = new BufferedReader(new InputStreamReader(System.in));
 n = nextInt();
 g = new ArrayList[n];
 gt = new ArrayList[n];
 for(int i=0;i<n;i++){
     g[i] = new ArrayList<pair>();
     gt[i] = new ArrayList<pair>();
 }
 m = nextInt();
 pair ps[] = new pair[m];
 for(int i=0;i<m;i++)
     ps[i] = new pair(nextInt()-1, nextInt()-1);
 Arrays.sort(ps);

 int cura = ps[0].x;
 int curb = ps[0].val;
 int count = 1;
 for(int i=1;i<m;i++){
     if (cura == ps[i].x && curb == ps[i].val){
  count++;
     }else{
  g[cura].add(new pair(curb, count));
  gt[curb].add(new pair(cura, count));
  count = 1;
     }
     cura = ps[i].x;
     curb = ps[i].val;
 }

 g[cura].add(new pair(curb, count));
 gt[curb].add(new pair(cura, count));
 
 color = new int[n];
 topol = new ArrayList<Integer>();
 prev = new int[n];
 Arrays.fill(prev, -1);
 cycle = new boolean[n];
 dfs(0);

 Collections.reverse(topol);
       long d[] = new long[n];
 d[0] = 1;
 for(int i : topol){
     for(pair j : gt[i]){
  if (cycle[j.x]) cycle[i] = true;
  d[i]+=d[j.x] * j.val;
  d[i]%=mod;
     }
 } 
 if (cycle[n-1]){
     System.out.println("INFINITE PATHS");
 }else{
     System.out.println(d[n-1]);
 }
    }

    private void dfs(int x){
 color[x] = 1;
 for(pair p : g[x]){
     if (color[p.x] == 0){
  prev[p.x] = x;
  dfs(p.x);
     }else if (color[p.x] == 1){
  markCycle(p.x, x);
     }
 }
 color[x] = 2;
 topol.add(x);
    }

    private void markCycle(int end, int st){
 cycle[end] = true;
 while(st != end){
     cycle[st] = true;
     st = prev[st];
 }
    }
    
    public static void main(String args[]){
 /*new Thread(null, new Solution(), "kingdom", 1<<24).start();*/
 new Solution().run();
    }
}

class pair implements Comparable<pair>{
    public int x, val;
    public pair(int x, int val){
 this.x = x;
 this.val = val;
    }
    
    public int compareTo(pair p){
 if (this.x > p.x) return 1;
 if (this.x < p.x) return -1;
 if (this.val > p.val) return 1;
 return -1;
    }
}