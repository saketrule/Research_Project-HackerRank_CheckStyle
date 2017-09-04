import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.Math;

public class Solution {

    public static boolean balance = true;
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args)  throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = bfr.readLine().split(" ");
        int N = Integer.parseInt(temp[0]); /* The number of nodes in the original graph */
        int M = Integer.parseInt(temp[1]); /* The number of edges in the decomposed graph */
      
        List<Set> edges = new ArrayList<Set>();

        for(int i = 0; i < M; i++){
            temp = bfr.readLine().split(" ");   /* Bidirectional edge between nodes u and v in the decomposed graph */
            int u = Integer.parseInt(temp[0]);  
            int v = Integer.parseInt(temp[1]);
            
            Set edge = new HashSet();
            edge.add(u);
            edge.add(v);
            edges.add(edge);
        }
        
        ///////////////////////////////////////
        /*for (int i = 0; i < edges.size(); i++) {
            System.out.println("Edge "+ i);
            for (Object el : edges.get(i)) {
                System.out.println((int)el);
            }
        }*/
        ///////////////////////////////////////
        
        Map<Integer, Character> nodeColoring = new HashMap<Integer, Character>();
        for (int i = 0; i < N; i++) {
            nodeColoring.put(i+1, 'n'); /* n - no color */
        }
        
        int startNode = 1;
        int previousNode = 0;
        for (int i = 0; i < N; i++) {
            recursiveColorNode(nodeColoring, edges, i+1, previousNode);
        }
        
        
        int countWhite = 0;
        int countBlack = 0;
        int countNone = 0;
        boolean gotBlack = false;
        boolean gotWhile = false;
        int black = 0;
        int white = 0;
        ArrayList<Integer> noneColoredNodes = new ArrayList<Integer>();
        
        for (Map.Entry<Integer, Character> entry : nodeColoring.entrySet()) {
            if (entry.getValue() == 'b') {
                countBlack++;
                if (gotBlack == false) {
                    black = entry.getKey();
                    gotBlack = true;
                }
            } 
            if (entry.getValue() == 'w') {
                countWhite++;
                if (gotWhile == false) {
                    white = entry.getKey();
                    gotWhile = true;
                }
            } 
            if (entry.getValue() == 'n') {
                countNone++;
                noneColoredNodes.add(entry.getKey());
            } 
            //String key = entry.getKey();
            //Object value = entry.getValue();
            // ...
        }
        
        ///////////////////////////////////////
        /*System.out.println("Nodes without color: ");
        for (int i = 0; i < noneColoredNodes.size(); i++) {
            System.out.println(noneColoredNodes.get(i));
            
        }
        System.out.println("---");*/
        ///////////////////////////////////////
        
        ///
        
        List<Set> newEdges = new ArrayList<Set>();
        while (true) {
            if (countWhite + countBlack == N) {
                break;
            }
            if (countWhite - countBlack >= 0) { /* More white nodes than black nodes => increase black nodes */
                nodeColoring.put(noneColoredNodes.get(0), 'b');
                
                Set edge = new HashSet();
                edge.add(noneColoredNodes.get(0));
                edge.add(white);
                newEdges.add(edge);
                
                noneColoredNodes.remove(0);
                countNone--;
                countBlack++;
            } else if (countWhite - countBlack < 0) {
                nodeColoring.put(noneColoredNodes.get(0), 'w');
                
                Set edge = new HashSet();
                edge.add(noneColoredNodes.get(0));
                edge.add(black);
                newEdges.add(edge);
                
                noneColoredNodes.remove(0);
                countNone--;
                countWhite++;
            } 
            
            if (countNone == 0) {
                break;
            }
        }
        
        /* subgraphs */
        List<Set> subgraphs = new ArrayList<Set>();
        edges.addAll(newEdges);
        for(int i = 0; i < edges.size(); i++) {
            int[] nodes = new int[2];
            int num = 0;
            for (Object element : edges.get(i)) {
                nodes[num] = (int)element;
                num++;
            }
            
            int a = nodes[0];
            int b = nodes[1];
            
            boolean containsA = false;                      /* One of the countries already has A? true/false */
            int iA = 0;                                     /* Save the country ID where A is from */
            boolean containsB = false;                      /* One of the countries already has B? true/false */
            int iB = 0;                                     /* Save the country ID where B is from */
            
            int j;
            for (j = 0; j < subgraphs.size(); j++) {
                
                if (subgraphs.get(j).contains(a)) {         /* Found the country with astronout A */
                    containsA = true;
                    iA = j;
                }
                
                if (subgraphs.get(j).contains(b)) {         /* Found the country with astronout B */
                    containsB = true;
                    iB = j;
                }
                
            }
            
            if ((containsA == true) && (containsB == true)) {   /* We have found A and B */
                if (iA == iB) {                                 /* - Their country IDs are the same */
                    continue;                                   /* then we don't need to do anything */
                } else {                                        /* - Their country IDs are different */
                    subgraphs.get(iA).add(b);                   /* then we need to unite these country sets */
                    subgraphs.get(iB).add(a);                   /* because they come from the same country */
                    Set<String> intersection = new HashSet<String>(subgraphs.get(iA)); //copy constructor
                    intersection.retainAll(subgraphs.get(iB));  

                    if (intersection.isEmpty() == false) {         
                        subgraphs.get(iA).addAll(subgraphs.get(iB));
                        subgraphs.remove(iB);
                    } 
                }
            } else if ((containsA == true) && (containsB == false)) {   /* We have found A, but haven't found B */
                subgraphs.get(iA).add(b);                               /* then we add B to A's country */
            } else if ((containsA == false) && (containsB == true)) {   /* We have found B, but haven't found A */
                subgraphs.get(iB).add(a);                               /* then we add A to B's country */
            } else if ((containsA == false) && (containsB == false)) {  /* We haven't found A nor B */
                Set graph = new HashSet();                            /* then we create new country for A and B */
                graph.add(a);
                graph.add(b);
                subgraphs.add(graph);
            } 
            
           
        }
        /* subgraphs end*/
        
        /* calculate subgraphs weight*/
        HashMap<Integer,Integer> graphWeight = new HashMap<Integer,Integer>();
        for (int j = 0; j < subgraphs.size(); j++) {
            /*System.out.println("Subgraph #" + j);*/
            int countWhiteNodes = 0;
            for (Object el : subgraphs.get(j)) {
                /*System.out.println((int)el);*/
                if (nodeColoring.get((int)el) == 'w') {
                    countWhiteNodes++;
                }
                
            }
            graphWeight.put(j,countWhiteNodes);
        }

        /* minimize their sum of weights */
        
        /* unite them all to 1 graph and take new edges */
        int graphNum = 0;
        while (true) {
            if (subgraphs.size()>=2){
                int firstNode = 0;
                char firstNodeColor = ' ';
                int secondNode = 0;
                char secondNodeColor = ' ';
                for (Object el : subgraphs.get(graphNum)) {
                    firstNode = (int)el;
                    firstNodeColor = nodeColoring.get(firstNode);
                }
                for (Object el : subgraphs.get(graphNum+1)) {
                    secondNode = (int)el;
                    if (firstNodeColor == nodeColoring.get(secondNode)){
                        continue;
                    } else {
                        secondNodeColor = nodeColoring.get(secondNode);
                        break;
                    }    
                }

                Set edge = new HashSet();
                edge.add(firstNode);
                edge.add(secondNode);
                newEdges.add(edge);

                graphNum++;
                if (graphNum < subgraphs.size()) {
                    break;
                }   
            } else {
                break;
            }
            
            
            
        }
        
        /*if (countWhite + countBlack == N) {
            System.out.println(java.lang.Math.abs(countWhite-countBlack) + " " + 0);
            return;
        }*/
        
        System.out.println(java.lang.Math.abs(countWhite-countBlack) + " " + newEdges.size());
        for (int i = 0; i < newEdges.size(); i++) {
            for (Object el : newEdges.get(i)) {
                System.out.print((int)el + " ");
            }
        }
        
    }
    
    public static void recursiveColorNode(Map<Integer, Character> nodeColoring, List<Set> edges, int nodeId, int previousNode) {
        boolean recursionCall = false;
        //System.out.println("****\nRecursion start ");
        if (nodeColoring.get(nodeId) == 'n') {        
            //nodeColoring.put(nodeId, 'b'); 
            //System.out.println("TRUE NON");
            //nodeColoring.put((int)secNode, 'w');
            if (previousNode == 0) {
                //nodeColoring.put(nodeId, 'b');
            } else {
                if (nodeColoring.get(previousNode)=='w') {
                    nodeColoring.put(nodeId, 'b');   
                }
                if (nodeColoring.get(previousNode)=='b') {
                    nodeColoring.put(nodeId, 'w');  
                }
            }
            //System.out.println("coloring node "+nodeId+ " has color " + nodeColoring.get(nodeId));

        } else {
            //System.out.println("Has color! for node "+nodeId);
            return;
        }
        //System.out.println("coloring node "+nodeId+ " has color " + nodeColoring.get(nodeId));
        
        for (int j = 0; j < edges.size(); j++) {        /* All edges in decomposed graph */
            //System.out.println("Edge #"+j);
            if (edges.get(j).contains(nodeId)) {           /* Find the edge with node we work with (i+1) */
                for (Object secNode : edges.get(j)){    /* Only 2 elements here */
                    if (((int)secNode) != nodeId) {
                        if ((int)secNode != previousNode) {
                            //System.out.println("Next node is " + (int)secNode);
                            
                            recursionCall = true;
                            if (nodeColoring.get(nodeId) == 'n') {   
                                if (previousNode == 0) {
                                    if (balance == true) {
                                        nodeColoring.put(nodeId, 'b');
                                        //balance = false;
                                        balance = true;
                                    } else {
                                        nodeColoring.put(nodeId, 'w');
                                        balance = true;
                                    }
                                    
                                }
                            }
                            
                            recursiveColorNode(nodeColoring, edges, (int)secNode, nodeId);
                            
                            
                            if (nodeColoring.get(nodeId) == 'w') {
                                nodeColoring.put((int)secNode, 'b');
                            }
                            if (nodeColoring.get(nodeId) == 'b') {
                                nodeColoring.put((int)secNode, 'w');
                            }
                            
                        }
                        
                    }

                }
            }
        }
        if (recursionCall == false) {
            if (nodeColoring.get(nodeId) == 'n') {   
                if (previousNode == 0) {
                    nodeColoring.put(nodeId, 'n');
                }
            }
        }
        /*System.out.println("coloring node "+nodeId+ " has color " + nodeColoring.get(nodeId));*/
    }
}