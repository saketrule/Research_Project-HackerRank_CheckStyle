import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Solution {

    public static String INFINITE_PATHS = "INFINITE PATHS";
    public static int mod = 1000000000;


    /**
     * The algorithm is O(N+M) in time and space.
     * It works in 3 steps
     * 1) Removes all nodes that don't point to N
     * 2) Finds a partial ordering for the DAG or returns an error if not found
     * 3) follows the partial ordering by adding the number of possible path to a certain edge
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();                               //Number of nodes
        int m = in.nextInt();                               //Number of edges

        ArrayList<Integer>[] inEdge  = new ArrayList[n];    //Incoming edges for node i
        ArrayList<Integer>[] outEdge = new ArrayList[n];    //Outgoing edges for node i
        int nOutEdges[] = new int[n];                       //number of outgoing edges not yet processed
        int partialOrdering[] = new int[n];                 //Partial ordering of the nodes
        int npathsp[] = new int[n];                         //number of paths up to i
        int state[] = new int[n];                           //0:not connected, 1:connected
        Stack<Integer> next = new Stack<Integer>();         //Stack used for recursion

        //Initialization
        for (int i = 0; i < n; i++) {
            inEdge[i] = new ArrayList<Integer>();
            outEdge[i]= new ArrayList<Integer>();
        }

        //Reads the input file
        for (int i = 0; i < m; i++) {
            int from = in.nextInt()-1;
            int to   = in.nextInt()-1;
            inEdge[to].add(from);
            outEdge[from].add(to);
            nOutEdges[from]++;
        }

        //finds all nodes connected to N, runs in O(N+M)
        Arrays.fill(state, 0);
        next.clear();
        next.add(n-1);
        state[n-1] = 1;
        while(! next.isEmpty()){
            int p = next.pop();
            for (Integer np : inEdge[p]) {
                if(state[np]==0){
                    state[np]=1;
                    next.push(np);
                }
            }
        }
        //Removes edges pointing to nodes that are not connected to N
        for (int i = 0; i < n; i++) {
            if(state[i]==1) continue;
            for (int j : inEdge[i]) {
                nOutEdges[j]--;
            }
            outEdge[i].clear();
            inEdge[i].clear();
        }

        //finds all nodes connected to 1, runs in O(N+M)
        Arrays.fill(state, 0);
        next.clear();
        next.add(0);
        state[0] = 1;
        while(! next.isEmpty()){
            int p = next.pop();
            for (Integer np : outEdge[p]) {
                if(state[np]==0){
                    state[np]=1;
                    next.push(np);
                }
            }
        }
        //Removes edges pointing to nodes that are not connected to 1
        for (int i = 0; i < n; i++) {
            if(state[i]==1) continue;
            for (int j : inEdge[i]) {
                nOutEdges[j]--;
            }
            outEdge[i].clear();
            inEdge[i].clear();
        }



        //Finds a partial ordering of the DAG or dies trying
        //The ordering must start from N and end at 1
        int poIndex = 0;
        next.clear();
        next.add(n-1);
        while(! next.isEmpty()){
            int p = next.pop();
            partialOrdering[poIndex++] = p;

            //Removes edges pointing to p
            for (int j : inEdge[p]) {
                if(inEdge[j].isEmpty() && outEdge[j].isEmpty()) continue; //skips excluded nodes
                nOutEdges[j]--;
                if(nOutEdges[j]==0) next.add(j);
            }
        }


        //If it did not get to node 1, there must be a cycle
        --poIndex;
        if(partialOrdering[poIndex] != 0){
            System.out.println(INFINITE_PATHS);
            return;
        }

        //Finally, use the partial ordering to find the number of paths in O(N+M)
        //The idea is that all incoming nodes are processed before any node, so
        //The number of paths to the current node are always correct
        npathsp[0]=1; //Starts from origin
        for (; poIndex>0; poIndex--) {
            int v = npathsp[partialOrdering[poIndex]];
            for (int to : outEdge[partialOrdering[poIndex]]) {
                npathsp[to] += v;
                npathsp[to] %= mod;
            }
        }

        System.out.println(npathsp[n-1] );
    }

}