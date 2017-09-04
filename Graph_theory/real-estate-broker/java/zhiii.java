import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int bn = in.nextInt();      
        int hn = in.nextInt();

        int [] hareas = new int[hn];
        int [] hprices = new int[hn];
        int [] bareas = new int[bn];
        int [] bprices = new int[bn];
        
        HashMap<Integer, ArrayList<Integer>> adj = new HashMap();
        
        boolean[][] adjM = new boolean[hn][bn]; 
        
        for (int i = 0; i < bn; i++) {
            bareas[i] = in.nextInt();
            bprices[i] = in.nextInt();  
        }
        
        for (int i = 0; i < hn; i++) {
            hareas[i] = in.nextInt();
            hprices[i] = in.nextInt();
         for (int j = 0; j < bn; j++) {
           //  System.out.println("House: areas "+ bareas[j] + " "  + hareas[i]);
             //System.out.println("House: prices " + bprices[j] + " " + hprices[i]);
                if (bareas[j] <= hareas[i] && bprices[j] >= hprices[i]) {
                    adjM[i][j] = true;   
                }
            }
//            adj.put(i, vs);
        }
        
      /*  for(int i=0; i<adjM.length; i++) {
            for (int j = 0; j<adjM[0].length; j++) {
                System.out.print(adjM[i][j] + " ");
            }
            System.out.println();
        }*/
        
        System.out.println(maxBPM(adjM));
    }
    
    static boolean dfs(boolean graph[][], int u, boolean seen[], int matchR[]){
        for (int v = 0; v < graph[0].length; v++){
            if (graph[u][v] && !seen[v]){
                seen[v] = true;
                if (matchR[v] < 0 || dfs(graph, matchR[v], seen, matchR)){
                        matchR[v] = u;
                        return true;
                    }
                }
            }
        return false;
    }
    
     static int maxBPM(boolean[][] graph) {
        int matchR[] = new int[graph[0].length];
        for(int i=0; i<graph[0].length; ++i) {
            matchR[i] = -1;
        }
 
        int result = 0;
        for (int u = 0; u < graph.length; u++){
            boolean seen[] = new boolean[graph[0].length];
            for(int i=0; i<graph[0].length; ++i){
              seen[i] = false;
            }
            if (dfs(graph, u, seen, matchR)){
                result++;
            }     
        }
        return result;
    }
}