import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static Scanner cons = new Scanner(System.in);
    public static int N;
    public static int M;
    public static Node[] nodes;
    public static int[] cost;
    public static int[] src;
    public static void main(String[] args) {
        // TODO code application logic here
        
        N = cons.nextInt();
        M = cons.nextInt();
        nodes = new Node[N+1];
        cost = new int[N+1];
        src = new int[N+1];
        for(int i=0;i<M; i++){
            int u = cons.nextInt();
            int v = cons.nextInt();
            int c = cons.nextInt();
            if(nodes[u]==null){
                nodes[u] = new Node();
            }
            nodes[u].addEd(v, c);
            if(nodes[v]==null){
                nodes[v] = new Node();
            }
            nodes[v].addEd(u, c);
        }
        int a = cons.nextInt();
//        System.out.println(a);
        int b = cons.nextInt();
        for(int i=0;i<N+1;i++){
            cost[i] = -1;
        }
        cost[a] = 0;
        Stack stack = new Stack();
        stack.push(a);
        while(!stack.empty()){
            int cur = stack.pop();
//            System.out.println(cur);
            if(nodes[cur]==null){
//                System.out.println(cur);
                break;}
            int[][] eds = nodes[cur].edges;
            for(int j=0;j<nodes[cur].edsize;j++){
                if(eds[j][0]!=src[cur]){
                    if(cost[eds[j][0]]==-1){
                        cost[eds[j][0]] = cost[cur]|eds[j][1];
                        stack.push(eds[j][0]);
                        src[eds[j][0]] = cur;
                    } else if (cost[eds[j][0]]>-1){
                        if((cost[cur]|eds[j][1])<cost[eds[j][0]]){
                            cost[eds[j][0]] = cost[cur]|eds[j][1];
                            stack.push(eds[j][0]);
                            src[eds[j][0]] = cur;
                        }
                    }
                }
            }
        }
        
        System.out.println(cost[b]);
        
    }
    public static class Node {
        public int[][] edges;
        public int edsize=0;
        
        public Node(){
            edges = new int[10][2];
        }
        
        public void addEd(int v, int c){
            if(edsize==edges.length){
                ensCap();
            }
            edges[edsize][0] = v;
            edges[edsize][1] = c;
            edsize++;
        }
        
        public void ensCap(){
            int[][] tmp = new int[edsize*3][2];
            for(int i=0;i<edsize;i++){
                tmp[i][0] = edges[i][0];
                tmp[i][1] = edges[i][1];
              
            }
            edges = tmp;
        }
    }
    public static class Stack {
        public int size =0;
        public int[] elems;
        public Stack(){
            elems = new int[10];
        }
        public void push(int e){
            if(size==elems.length){
                ensCap();
            }
            elems[size++] = e;
        }
        public int pop(){
            int tmp = elems[size-1];
            size-=1;
            return tmp;
        }
        public void ensCap(){
            int[] tmp = new int[size*3];
            for(int i=0;i<size;i++){
                tmp[i] = elems[i];
                
            }
            elems = tmp;
        }
        public boolean empty(){
            return size ==0;
        }
    }
}