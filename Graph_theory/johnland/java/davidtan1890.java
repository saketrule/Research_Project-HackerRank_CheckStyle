import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static int NULL = -1;
    public static void main(String[] args) {
        int n = ni();
        int m = ni();
        int[][] edges = new int[m][2];
        int a, b, c;
        Map<Long, Integer> mm = new HashMap<Long,Integer>();
        int xxx;
        long xx; //TODO: short time, don't think about name
        
        List[] tree = new List[n];
        for(int i=0; i<m; i++){
            a = ni()-1; b = ni()-1; c = ni();
            
            if(a> b){
                xxx = a;
                a = b;
                b = xxx;
            } 
            xx = (((long)a) <<18) + ((long)b);           
               
            if(mm.containsKey(xx)) {
                xxx = mm.get(xx);
                if(xxx > c){
                    edges[xxx][0] = 0;
                    edges[xxx][1] = 0;
                    mm.put(xx, c);
                    edges[c][0] = a; 
                    edges[c][1] = b;
                }
                continue;
            }
            mm.put(xx, c);
            edges[c][0] = a; 
            edges[c][1] = b;
        }
        
        Node[] map = new Node[n];
        int[] parent = new int[n];
        int[] degree = new int[n]; //number of children
        int[] des = new int[n]; //number of con chau'
        int[] weight = new int[n];
        
        for(int i=0; i<n; i++) {
            parent[i] = NULL;
            map[i] = new Node();
        }
        
        //build directed-tree
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(0);
        
        Node aNode, bNode;
        for(int i=0; i<m; i++){
            a = edges[i][0]; b = edges[i][1];
            if(a == b) continue;
            aNode = find(map[a]); bNode = find(map[b]);
            if(aNode!= bNode){
                if(tree[a] == null) tree[a] = new LinkedList<Integer>();
                if(tree[b] == null) tree[b] = new LinkedList<Integer>();
                tree[a].add(b); tree[b].add(a);
            }
            union(map[a], map[b]);
            /*
            a = edges[i][0]; b = edges[i][1];
            aNode = find(map[a]); bNode = find(map[b]);
            if(aNode != bNode) {
                if(parent[a] == NULL) {
                    parent[a] = b;
                    degree[b]++;
                    weight[a] = i;
                }
                else if(parent[b]==NULL) {
                    parent[b] = a;
                    degree[a]++;
                    weight[b] = i;
                }else {
                    //println("Oops...:" + a + " " + b);
                }
            }else {
                //println("Found the same");
            }
            union(map[a], map[b]);
            */
        }
        /*
        for(int i=0; i<n; i++){
            degree[i] = tree[i].size() - 1;
        }
        degree[0] = tree[0].size();
        */
        int it;
        while(!queue.isEmpty()){
            xxx = queue.poll();
            for(Object o: tree[xxx]){
                it = ((Integer) o).intValue();
                if(parent[it] == NULL && it!=0){
                    queue.offer(it);
                    parent[it] = xxx;
                    degree[xxx]++;
                    weight[it] = mm.get(((long)(Math.min(it,xxx) ) <<18) + ((long)Math.max(it, xxx))); 
                }
            }
        }
        
        queue = new LinkedList<Integer>();
        for(int i=0; i<n; i++){
            if(degree[i] == 0) queue.offer(i);
        }
        
        while(!queue.isEmpty()){
            a = queue.poll();
            b = parent[a];
            if(b == NULL) continue;
            degree[b]--;
            des[b] += des[a] + 1;
            if(degree[b] == 0) queue.offer(b);
        }
        //long sum = 0;
        int[] sumArray = new int[1000+m];
        long t;
        for(int i=0; i<n; i++){
            b = parent[i];
            if(b == NULL) {
                //println(i + " => NULL");
                continue;
            }else {
                //println(i + " => " + b);
            }
            t = ((long)(des[i]+1)) * ((long)(n-des[i]-1));
            
            //add to sum
            //sum += t * (1<<weight[i]);
            add(sumArray, t, weight[i]);
        }
        
        //println(sum +"");
        int i = 0;
        for(i=sumArray.length-1; i>=0; i--){
            if(sumArray[i] != 0) break;
        }
        if(i == -1) print("0");
        else {
            for(int k = i; k>=0; k--){
                print(Integer.toString(sumArray[k]));
            }
        }
        
        close();
        //test();
    }
    
    public static void add(int[] sum,long t, int w){
        
        int carry = 0;
        int temp;
        for(int i=0; i<40; i++,w++){
            sum[w] += (t & 1) + carry;
            if(sum[w] > 1) {
                sum[w] &= 1; 
                carry = 1;
            }else {
                carry = 0;
            }
            t >>= 1;
        }
        while(carry != 0){
            sum[w] += carry;
            if(sum[w] > 1) {
                sum[w] &= 1; 
                carry = 1;
            }else {
                carry = 0;
            } 
            w++;
        }
    }
    
    private static void test(){
        int[] s = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, 1,1,1,1,1, 0,0,0,0};
        add(s, 1, 2);
        for(int i=0; i<s.length; i++) System.out.print(s[i]);
    }
    
    static class Node {
        Node parent;
        int rank;
        //int size = 1; //size of the disjoint set
        //String value; //this to store the internal string that determine the alphebatical order
        
        Node(){
            parent = this;
            rank = 0;
        }
        /*
        Node(String v) {
            value = v;
            parent = this;
            rank = 0;
        } */
    }
    
    public static Node find(Node x){
        if( x.parent != x) {
            x.parent = find(x.parent);
        }
        return x.parent;
    }
    
    public static void update(Node x, Node y){

        //x will be root

        //we only need to maintain the size of the root
        //x.size += y.size;

        //so we pick the element having smaller value
        //if(x.value.compareTo(y.value) > 0) x.value = y.value;
    }
    
    public static void union(Node x, Node y){
        Node xRoot = find(x);
        Node yRoot = find(y);

        if(xRoot == yRoot) return;
        if (xRoot.rank < yRoot.rank){
            xRoot.parent = yRoot;
            //update(yRoot, xRoot);
            //return yRoot;
        }            
        else if (xRoot.rank > yRoot.rank){
            yRoot.parent = xRoot;
            //update(xRoot, yRoot);
            //return xRoot;
        }
        else {
            yRoot.parent = xRoot;
            xRoot.rank = xRoot.rank + 1;
            //update(xRoot, yRoot);
            //return yRoot;
        }
    }
    
    public static Node makeSet(int x){
        return new Node();
    }
    
    public static void print(String s){
        bd.append(s);
    }
    public static void println(String s){
        bd.append(s);
        bd.append("\n");
    }
    
    public static void close(){
        System.out.println(bd.toString());
    }
    
    static StringBuilder bd = new StringBuilder();
    static Scanner sc = new Scanner(System.in);
    public static int ni() { return sc.nextInt(); }
    public static long nl() { return sc.nextLong(); }
    public static String n() { return sc.next(); }
}