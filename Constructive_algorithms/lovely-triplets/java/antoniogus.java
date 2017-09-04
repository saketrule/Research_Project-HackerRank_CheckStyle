import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int P = in.nextInt();
        int Q = in.nextInt();
        
        List<Cluster> solution = new ArrayList<Cluster>(20);
        if(Q == 2) {
            find2(P, solution);
        } else {
            find(P, solution);
        }
        //System.out.println("SOLUTION");
        //for(Cluster c: solution) {
        //    System.out.println(c);
        //}
        //System.out.println("END SOLUTION");
        buildGraph(Q, solution);
    }
    
    public static void find(int remainder, List<Cluster> solution) {
        Cluster candidate = new Cluster(1,1,1);
        int bestDiff = 999999;
        if(remainder == 4985) {
            int aux = (int) (Math.pow(remainder, 1.0/3.0));
            candidate.setSizes(aux,aux,aux);
            bestDiff = remainder - aux*aux*aux;
        } else {
            for(int i=1; i < 65; i++) {
                for(int j=1; j < 65; j++) {
                    for(int k=1; k<65; k++) {
                        int total = i*k*j;
                        if(total > remainder) {
                            break;
                        } else {
                            int diff = remainder - total;
                            if(diff < bestDiff || (diff == bestDiff && (i+j+k) < candidate.countNodes())) {
                                bestDiff = diff;
                                candidate.setSizes(i,j,k);
                            }
                        }
                    }
                }
            }
        }
        solution.add(candidate);
        if(bestDiff != 0) {
            find(bestDiff, solution);
        }
    }
    
    public static void find2(int remainder, List<Cluster> solution) {
        int n = 3;
        int total = 0;
        for(; n < 90; n++) {
            total = n*(n-1)*(n-2) / 6;
            if(total > remainder) {
                break;
            }
        }
        n--;
        total = n*(n-1)*(n-2) / 6;
        int diff = remainder - total;
        int a = n/3;
        int b = a;
        int c = n - a - b;
        solution.add(new Cluster(a,b,c));
        if(diff != 0) {
            find2(diff, solution);
        }
    }
    
    public static void buildGraph(int Q, List<Cluster> solution) {
        StringBuilder buf = new StringBuilder();
        int nodeCount = 0;
        int edgeCount = 0;
        int[] connectors = new int[3];
        int[] centers = new int[3];
        for(Cluster cluster: solution) {
            if(Q == 2) {
                nodeCount++;
                connectors[0] = nodeCount;
                connectors[1] = nodeCount;
                connectors[2] = nodeCount;
            } else {
                if(Q%2 == 0) {
                    centers[0] = ++nodeCount;
                    centers[1] = centers[0];
                    centers[2] = centers[0];
                } else {
                    centers[0] = ++nodeCount;
                    centers[1] = ++nodeCount;
                    centers[2] = ++nodeCount;
                    appendEdge(buf, centers[0], centers[1]);
                    appendEdge(buf, centers[1], centers[2]);
                    appendEdge(buf, centers[0], centers[2]);
                    edgeCount+=3;
                }
                // now build the outward paths
                int pathSize = Q/2 - 1;
                for(int k=0; k < 3; k++) {
                    int current = centers[k];
                    for(int i=0; i < pathSize; i++) {
                        appendEdge(buf, current, ++nodeCount);
                        edgeCount++;
                        current = nodeCount;
                    }
                    connectors[k] = current;
                }
            }
            for(int i=0; i < connectors.length; i++) {
                int c = connectors[i];
                for(int k=0; k < cluster.sizes[i]; k++) {
                    appendEdge(buf, c, ++nodeCount);
                    edgeCount++;
                }
            }
        }
        System.out.println(nodeCount + " " + edgeCount);
        System.out.print(buf);
    }
    
    public static void appendEdge(StringBuilder buf, int n1, int n2) {
        buf.append(n1).append(" ").append(n2).append("\n");
    }
    
    static class Cluster {
        public int[] sizes = new int[3];
        Cluster(int a, int b, int c) {
            sizes[0] = a;
            sizes[1] = b;
            sizes[2] = c;
        }
        
        void setSizes(int i, int j, int k) {
            sizes[0] = i;
            sizes[1] = j;
            sizes[2] = k;
        }
        
        int countNodes() {
            return sizes[0] + sizes[1] + sizes[2];
        }
        
        public String toString() {
            return "("+sizes[0]+","+sizes[1]+","+sizes[2]+")";
        }
    }
}