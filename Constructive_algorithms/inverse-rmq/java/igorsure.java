import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    TreeMap<Integer,PriorityQueue<Integer>> queue;
     TreeMap<Integer,Integer> map;
    int[] levels;
    int[] a;
    int LOGN;
    Solution(){
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        LOGN=log2(2*n);
        map=new  TreeMap<Integer,Integer>();
        a=new int[2*n];
        for(int i=0;i<2*n-1;i++){
            int next=in.nextInt();
            //a[i+1]=next;
            if (!map.containsKey(next)){
                map.put(next,1);
            } else {
                map.put(next,map.get(next)+1);
            }
        }
        levels=new int[LOGN+1];
        queue=new  TreeMap<Integer,PriorityQueue<Integer>>();
        for(int i=0;i<=LOGN;i++){
            queue.put(i,new PriorityQueue<Integer>());
        }
        //dumpMap();
        queue.get(1).add(1);
        
        for(int ind:map.keySet()) {
            int val=map.get(ind);
            //int lvl=takeMaxLevelFromQueue();
            int lvl=searchLevel(val);
            int amount=LOGN-lvl;
            if (amount>val) {
                //dumpGraph();
                System.out.println("NO");
                return;
            }
            //dumpGraph();
            //dumpQueue();
            //System.out.println(" before takeMaxNodeFromQueue  ind:"+ind+" lvl:"+lvl+" amount:"+amount+" val:"+val);
            int node=takeMaxNodeFromQueue(lvl);
            if (node==-1) {
                System.out.println("NO");
                return;
                
            }
                
            addNode(ind,node,lvl+1);
            //dumpGraph();
            //dumpQueue();
            val-=amount+1;
            //System.out.println("before ind:"+ind+" lvl:"+lvl+" amount:"+amount+" val:"+val+" node:"+node);
            while (val>0){
                lvl=searchLevel(val) ;
                //System.out.println(" lvl:"+lvl+" val:"+val);
                node=takeMaxNodeFromQueue(lvl+1);
                if (node==-1) {
                    System.out.println("NO");
                    return;
                }
                amount=LOGN-lvl;
                addNode(ind,node,lvl);
                val-=amount+1;
            }
            //dumpGraph();
            //dumpQueue();
        } 
        /*
        addNode(5,1,1);
        addNode(4,3,2);
        dumpGraph();
        dumpQueue();
        */
        //System.out.println(searchLevel(3));
        //System.out.println(searchLevel(2));
        //System.out.println(searchLevel(5));
        System.out.println("YES");
        dumpArr(a);
        //queue.add(1); // place in tree
            
    }
    void addNodes(int val,int node,int level,int amount){
        a[node]=val;
        node=node << 1 ;
        level--;
        while(node<a.length){
            a[node]=val;
            //System.out.println("  node:"+node);
            level++;
            PriorityQueue<Integer> q=queue.get(level+1);
            q.add(node+1);
            node=node << 1;
        }    
    }
    int searchLevel(int amount){
        for(int ind:queue.keySet()){
            if (queue.get(ind).isEmpty()) continue;
            int level=LOGN-ind+1;
            //System.out.println("SearchLevel amount:"+amount+" level:"+level);
            if (level<=amount) {
                return ind;
            }
        }
        return -1;
    }
    int takeMaxLevelFromQueue(){
        //dumpQueue();
        for(int ind:queue.keySet()){
            //System.out.println(ind);
            if (!queue.get(ind).isEmpty()) return ind;
        }
        return -1;
    }
    int takeMaxNodeFromQueue(int level){
        PriorityQueue<Integer> q=queue.get(level);
        if (q==null) {
            return -1; 
        }
        return q.remove();
    }
    void addNode(int val,int node,int level){
        //System.out.println(" val:"+val+" node:"+node+" level:"+level);
        a[node]=val;
        node=node << 1 ;
        level--;
        while(node<a.length){
            a[node]=val;
            //System.out.println("  node:"+node);
            level++;
            PriorityQueue<Integer> q=queue.get(level);
            q.add(node+1);
            node=node << 1;
        }    
    }
    void dumpGraph(){
        int level=0;
        while(level<LOGN){
            int base= 1 << level;
            int base1= base << 1;
            System.out.print(level+": ");
            for(int i=base;i<base1;i++){
                System.out.print(a[i]+" ");
            }
            System.out.println("");
            level++;
        }
    }
    void dumpQueue(){
        for(int ind:queue.keySet()){
            if (!queue.get(ind).isEmpty()) {
                System.out.print(ind+": ");
                System.out.print(queue.get(ind));
                System.out.println("");
            }
        }
    }
    void dumpMap(){
        for(int ind:map.keySet()){
                System.out.print(ind+": ");
                System.out.print(map.get(ind));
                System.out.println("");
        }
    }
    public static void main(String[] args) {
       Solution sol=new Solution();
    }
    public static int log2(int n){
        if(n <= 0) throw new IllegalArgumentException();
        return 31 - Integer.numberOfLeadingZeros(n);
    }
    void dumpArr(int[] ar){
        for(int i=1;i<ar.length;i++){
            System.out.print(ar[i]+" ");
        }
    }
}