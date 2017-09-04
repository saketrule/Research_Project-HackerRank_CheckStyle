import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] s = in.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        
        H h = new H(n);
        
        String[] money = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            h.setMoney(i, Integer.parseInt(money[i]));
        }
        
        for (int i = 0; i < m; i++) {
            String[] edge = in.readLine().split(" ");
            h.addEdge(Integer.parseInt(edge[0])-1, Integer.parseInt(edge[1])-1);
        }
        
        h.solve();
        h.printSolution();
    }
}

class H {
    private int maxSum;
    private int count;
    
    private int n;
    private int[] v;
    private int[] m;
    private int[] maxPartSum;
    private List<Integer>[] e;
    
    H (int n) {
        this.n = n;
        v = new int[n];
        e = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            v[i] = -1;
            e[i] = new ArrayList<Integer>();
        }
        m = new int[n];
        //maxPartSum = new int[n];
        
        maxSum = 0;
        count = 0;
    }
    
    private void processNode(int node, int sum) {
        sum += m[node];
        
        //System.out.println(sum + " " + maxSum + " " + maxPartSum[node]);
        
        List list = disable(node);
        
        //if (sum >= maxPartSum[node]) {
            //System.out.println("  pokracujume");
            //printV();
            //if (noMoreFreeNodes(node)) {
                //System.out.println("    ukladame");
                if (sum > maxSum) {
                    //System.out.println("  save: " + sum + " " + maxSum);
                    maxSum = sum;
                    count = 1;
                } else if(sum == maxSum) {
                    count++;
                } else {
                    //System.out.println("    NIC");
                }
            //}
            
            //maxPartSum[node] = sum;
            
            

            for (int i = node + 1; i < n; i++) {
                if (v[i] == -1) {
                    processNode(i, sum);
                }
            }

            enable(list);
        //}
        sum -= m[node];
    }
    
    private boolean noMoreFreeNodes(int node) {
        for (int i = node + 1; i < n; i++) {
            if (v[i] == -1) {
                return false;
            }
        }
        return true;
    }
    
    public void printSolution() {
        System.out.println(maxSum + " " + count);
    }
    
    private List disable(int node) {
        List<Integer> list = new ArrayList<Integer>();
        for (int adj : e[node]) {
            if (adj > node && v[adj] == -1) {
                v[adj] = node;
                list.add(adj);
            }
        }
        return list;
    }
    
    private void enable(List<Integer> list) {
        for (int p : list) {
            v[p] = -1;
        }
    }
    
    private void printV() {
        for (int vv : v) {
            System.out.print(vv + " ");
        }
        System.out.println();
    }
    
    public void solve() {
        for (int i = 0; i < n; i++) {
            ///System.out.println("## NODE " + i + " ##");
            processNode(i, 0);
        }
    }
    
    public void setMoney(int p, int a) {
        m[p] = a;
    }
    
    public void addEdge(int e1, int e2) {
        e[e1].add(e2);
        e[e2].add(e1);
    }
    
}