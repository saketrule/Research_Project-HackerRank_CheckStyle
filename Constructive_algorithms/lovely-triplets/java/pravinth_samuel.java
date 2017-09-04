import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static class Edge {
        int v1, v2;
        
        public Edge(int v1, int v2) {
            this.v1 = Math.min(v1, v2);
            this.v2 = Math.max(v1, v2);
        }
        
        public String toString() {
            return "(" + v1 + ", " + v2 + ")";
        }
    }
    
    public static List<Edge> branch(int numNodes, int dist) {
        //System.out.println(numNodes + "::" + dist);
        List<Edge> list = new ArrayList<Edge>();
        int curNode = 1;
        
        for (int i=0; i<dist-1; i++) {
            list.add(new Edge(curNode, curNode+1));
            curNode++;
        }
        
        int midNode = curNode++;
        
        for (int i=0; i<numNodes; i++) {
            list.add(new Edge(midNode, curNode++));
        }
        return list;
    }
    
    public static List<Edge> getMiniGraph2(int num1, int num2, int num3, int Q) {
        
        List<Edge> list = new ArrayList<Edge>();
        
        List<Edge> branchList1 = branch(num1, (Q-2)/2);
        List<Edge> branchList2 = branch(num2, (Q-2)/2);
        List<Edge> branchList3 = branch(num3, (Q-2)/2);
        
        int numNodes1 = branchList1.get(branchList1.size()-1).v2;
        int numNodes2 = branchList2.get(branchList2.size()-1).v2;
        int numNodes3 = branchList3.get(branchList3.size()-1).v2;
        
        int curNode = 0;
        for(Edge e: branchList1) {
            list.add(new Edge(curNode+e.v1, curNode+e.v2));
        }
        curNode+=numNodes1;
        for(Edge e: branchList2) {
            list.add(new Edge(curNode+e.v1, curNode+e.v2));
        }
        curNode+=numNodes2;
        for(Edge e: branchList3) {
            list.add(new Edge(curNode+e.v1, curNode+e.v2));
        }
        curNode+=numNodes3+1;
        
        if (Q%2==0) {
            list.add(new Edge(curNode, 1));
            list.add(new Edge(curNode, 1 + numNodes1));
            list.add(new Edge(curNode, 1 + numNodes1 + numNodes2));
        } else {
            if (Q!=3) {
                list.add(new Edge(curNode, curNode+1));
                list.add(new Edge(curNode, curNode+2));
                list.add(new Edge(curNode+1, curNode+2));

                list.add(new Edge(curNode, 1));
                list.add(new Edge(curNode+1, 1 + numNodes1));
                list.add(new Edge(curNode+2, 1 + numNodes1 + numNodes2));
            } else {
                list.add(0, new Edge(1, 1+numNodes1));
                list.add(0, new Edge(1, 1+numNodes1+numNodes2));
                list.add(0, new Edge(1+numNodes1+numNodes2, 1+numNodes1));
            }
        }
        
        return list;
    }
    
    /*public static void factorTriplets(int num) {
        for (int i=1; i*i<=num; i++) {
            if (num%i==0) {
                int factorNew = num/i;
                
                for (int j=Math.sqrt(factorNew); j<=factorNew; j++) {
                    if (factorNew%j==0) {
                        System.out.println(i + " " + j + " " + factorNew/j);
                    }
                }
            }
        }
    }*/
    
    public static List<Integer> factorTriplets(int num) {

        
        List<Integer> triplet = new ArrayList<Integer>();
        List<Integer> finalList;
        int minSum = 1000;
        
        if (num == 1) {
            triplet = new ArrayList<Integer>();
            triplet.add(1);
            triplet.add(1);
            triplet.add(1);
            minSum = 3;
        } else {
        
            for (int i=1; i*i<num; i++) {
                if (num%i==0) {
                    int factorNew = num/i;

                    for (int j=i; j<=Math.sqrt(factorNew); j++) {
                        if (factorNew%j==0) {
                            minSum = Math.min( (int)(i + j + factorNew/j), minSum);
                            triplet = new ArrayList<Integer>();
                            triplet.add(i);
                            triplet.add(j);
                            triplet.add(factorNew/j);
                        }   
                    }
                }
            }
        }
        
        finalList = new ArrayList(triplet);
        finalList.add(minSum);
        return finalList;
    }
    
    public static List<Edge> getGraph3(int P, int Q) {
        
        
        
        if (Q==2) {
            int curP = P;
            List<Edge> list = new ArrayList<Edge>();
            List<Integer> pvalues = new ArrayList<Integer>();
            
            for (int i=32; i>=3; i--) {
                int val = i*(i-1)*(i-2)/6;
                while (curP - val >= 0) {
                    pvalues.add(i);
                    curP -= val;
                }
            }
            
            int offset = 0;
            for (int p : pvalues) {
                for (int i=0; i<p; i++) {
                    list.add(new Edge(offset+1, offset + 2 + i));
                }
                offset += p+1;
            }
            
            return list;
        }
        
        List<Edge> list = new ArrayList<Edge>();
        int []nodes = new int[P];
        Edge []confs = new Edge[P];
                
        for (int i=1; i<=P; i++) {
            int minSum = factorTriplets(i).get(3) + 15;
            confs[i-1] = new Edge(i, -1);
            
            // 2 structures
            for (int j=1; j<i/2; j++) {
                if (nodes[j-1]+nodes[i-j-1] < minSum) {
                    minSum = nodes[j-1]+nodes[i-j-1];
                    confs[i-1] = new Edge(j, i-j);
                }
            }

            nodes[i-1] = minSum;
        }
        
        /*System.out.println("nodes: " + nodes[P-1]);
        System.out.println("conf: " + confs[P-1].v1 + " " + confs[P-1].v2);
        System.out.println("factorTriplet: " + factorTriplets(P));*/
        
        List<Integer> pvalues = new ArrayList<Integer>();
        Stack<Integer> stk = new Stack<Integer>();
        stk.push(P);
        
        while (!stk.isEmpty()) {
            int cur = stk.pop();
            
            Edge e = confs[cur-1];
            
            if (e.v2 == cur) {
                pvalues.add(cur);
            } else {
                stk.push(e.v1);
                stk.push(e.v2);
            }
        }
        
        int offset = 0;
        
        for (int p : pvalues) {
            List<Integer> factors = factorTriplets(p);
            
            List<Edge> miniGraph = getMiniGraph2(factors.get(0), factors.get(1), factors.get(2), Q);
            for(Edge e: miniGraph) {
                list.add(new Edge(offset+e.v1, offset+e.v2));
            }

            offset = list.get(list.size()-1).v2;
        }
        return list;
        
        
    }
    
    
    
    /*public static void listOfPrimeFactors(int num) {
        List<Integer> primes = new ArrayList<Integer>()
        for (int i=2; i<)
    }*/
    
    public static List<Edge> getMiniGraph(int num, int Q) {
        List<Edge> list = new ArrayList<Edge>();
        List<Edge> branchList = branch(num, Q/2);

        //System.out.println(branchList);

        int numNodes = branchList.get(branchList.size()-1).v2;

        int curNode = 0;
        for(Edge e: branchList) {
            list.add(new Edge(curNode+e.v1, curNode+e.v2));
        }
        curNode+=numNodes;
        for(Edge e: branchList) {
            list.add(new Edge(curNode+e.v1, curNode+e.v2));
        }
        curNode+=numNodes;
        for(Edge e: branchList) {
            list.add(new Edge(curNode+e.v1, curNode+e.v2));
        }
        curNode+=numNodes+1;
        
        if (Q%2==0) {
            list.add(new Edge(curNode, 1));
            list.add(new Edge(curNode, 1 + numNodes));
            list.add(new Edge(curNode, 1 + 2*numNodes));
        } else {
            list.add(new Edge(curNode, curNode+1));
            list.add(new Edge(curNode, curNode+2));
            list.add(new Edge(curNode+1, curNode+2));
            
            list.add(new Edge(curNode, 1));
            list.add(new Edge(curNode+1, 1 + numNodes));
            list.add(new Edge(curNode+2, 1 + 2*numNodes));
        }
        
        return list;
    }
    
    public static List<Edge> getGraph2 (int P, int Q) {
        int curP = P;
        int num = 18;
        int offset = 0;
        
        List<Edge> list = new ArrayList<Edge>();
        
        while (num >= 1) {
            while (curP - num*num*num>=0) {
                curP -= num*num*num;
                List<Edge> miniGraph = getMiniGraph2(num, num, num, Q);
                
                for(Edge e: miniGraph) {
                    list.add(new Edge(offset+e.v1, offset+e.v2));
                }
                
                offset = list.get(list.size()-1).v2;
            }
            num--;
        }
        
        //System.out.println("curP: " + curP);
        
        return list;
                
    }
    public static List<Edge> getGraph (int P, int Q) {
        int curP = P;
        int num = 18;
        int startNode = 1;
        
        List<Edge> list = new ArrayList<Edge>();
        
        while (num >= 2) {
            while (curP - num*num*num>=0) {
                curP -= num*num*num;
                if (Q%2==0) {
                    List<Edge> branchList = branch(num, Q/2);
                    
                    System.out.println(branchList);
                    
                    int numNodes = branchList.get(branchList.size()-1).v2;
                    
                    list.add(new Edge(startNode, startNode+1));
                    int curNode = startNode;
                    for(Edge e: branchList) {
                        list.add(new Edge(curNode+e.v1, curNode+e.v2));
                    }
                    curNode+=numNodes;
                    list.add(new Edge(startNode, curNode+1));
                    for(Edge e: branchList) {
                        list.add(new Edge(curNode+e.v1, curNode+e.v2));
                    }
                    curNode+=numNodes;
                    list.add(new Edge(startNode, curNode+1));
                    for(Edge e: branchList) {
                        list.add(new Edge(curNode+e.v1, curNode+e.v2));
                    }
                    curNode+=numNodes;
                    startNode = curNode+1;
                } else {
                    List<Edge> branchList = branch(num, (Q-1)/2);
                    
                    int numNodes = branchList.get(branchList.size()-1).v2;
                    list.add(new Edge(startNode, startNode+1));
                    list.add(new Edge(startNode, startNode+2));
                    list.add(new Edge(startNode+1, startNode+2));
                    
                    int curNode = startNode+2;
                    list.add(new Edge(startNode, curNode+1));
                    for(Edge e: branchList) {
                        list.add(new Edge(curNode+e.v1, curNode+e.v2));
                    }
                    curNode+=numNodes;
                    
                    list.add(new Edge(startNode+1, curNode+1));
                    for(Edge e: branchList) {
                        list.add(new Edge(curNode+e.v1, curNode+e.v2));
                    }
                    curNode+=numNodes;
                    
                    
                    list.add(new Edge(startNode+2, curNode+1));
                    for(Edge e: branchList) {
                        list.add(new Edge(curNode+e.v1, curNode+e.v2));
                    }
                    curNode+=numNodes;
                    startNode = curNode+1;
                }
            }
            num--;
        }
        
        return list;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int P = in.nextInt();
        int Q = in.nextInt();
        
        List<Edge> list = getGraph3(P, Q);
        
        //List<Edge> list = getMiniGraph2(3,3,4,5);

        System.out.println(list.get(list.size()-1).v2 + " " + list.size());
        for (Edge e : list) {
            System.out.println(e.v1 + " " + e.v2);
        }
        
    }
}