import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static int n,m,k,max;
    static int[] t;
    static int[][] a;
    static int[] bin;
    static int[][] g;
    static int min;
    static int[][] c;
    static LinkedList<Integer> current;
    static LinkedList<Integer> visited;

    public static void main(String[] args) {
        new Solution().run();
    }

    public void run(){
        Scanner in = new Scanner(System.in);
        int i,j,u,v;
        n = in.nextInt();//<=1000
        m = in.nextInt();
        k = in.nextInt();//<=10
        max = 1<<k; //2^(k+1); max-1 = 11...11
        t = new int[n+1];
        a = new int[n+1][k+1];
        bin = new int[n+1];

        for(i=1;i<=n;i++){
            t[i] = in.nextInt(); //<=k
            for(j=1;j<=t[i];j++){
                u = in.nextInt();//1 <= a <= k
                a[i][u] = 1;
            }
            u = 0;
            for(j=0;j<k;j++){
                if(a[i][j+1]==1){
                    u+=1<<j;
                }
            }
            bin[i]=u;
        }

        g = new int[n+1][n+1];

        for(i=1;i<=m;i++){
            u = in.nextInt(); 
            v = in.nextInt();
            g[u][v] = in.nextInt(); //<=1000
            g[v][u] = g[u][v]; //<=1000
        }

        solve();

        System.out.println(min);
    }

    public void solve(){
        //for each city mark
        c = new int[n+1][max];
        
        visited = new LinkedList<Integer>();
        current = new LinkedList<Integer>();
        
        c[n][bin[n]] = 0;//can buy bin[n] from here!
        current.addLast(n);
        
        int i, u, v, j, buy, cost;
        while (!current.isEmpty()){//BFS
            u = current.pop();
            //System.out.println("Marking "+u);

            //search & mark all neighbors with current knowledge
            for(v=1;v<=n;v++){
                if(g[u][v]>0 
                   //&& !visited.contains(v)
                  ){
                    for(j = 0; j < max;j++){
                        if(c[u][j]>0 || (u == n && j==bin[n])){
                            //from v (excluding) through u to n
                            //one can buy j at c[u][j] cost
                            //and bin[v] here at an additional g[v][u] cost
                            buy = bin[v] | j;
                            cost = c[u][j]+g[u][v];
                            //System.out.println("After "+v+" buy-cost "+(new BigInteger(""+buy).toString(2))+" "+cost+" buy"+buy);
                            if(c[v][buy]==0){//no possibility before
                                c[v][buy] = cost;
                            } else {//another road possible
                                c[v][buy] = Math.min(c[v][buy], cost);
                            }
                        }
                    }
                }
            }

            visited.addLast(u);

            //print();

            //continue BFS
            for(v=1;v<=n;v++){
                if(g[u][v]>0 && !current.contains(v) && !visited.contains(v)){
                   // System.out.println("Edge "+u+" "+v);
                    current.addLast(v);
                }
            }
        }

        //compute
        min = -1;
        //print();
        //System.out.println("should be: "+(21|27)+" is "+(max-1)+"="+(new BigInteger(""+(max-1)).toString(2)));
        
        for(i=0;i<max;i++)
            for(j = i;j<max;j++){
            if((i | j) == max-1 && c[1][i]>0 && c[1][j]>0) //covers all
            {
                //System.out.println("equal: "+i+" "+j);
                //System.out.println("cost: "+c[1][i]+" "+c[1][j]);
                if(min==-1) min = Math.max(c[1][i],c[1][j]);
                else min = Math.min(min,Math.max(c[1][i],c[1][j]));
            }
        }
    }

    public void print(){
        //graph
        for(int i=1;i<=n;i++){
            for(int j = 0;j<max;j++){
                System.out.print(""+c[i][j]+" ");
            }
            System.out.println("----");
        }
    }
}