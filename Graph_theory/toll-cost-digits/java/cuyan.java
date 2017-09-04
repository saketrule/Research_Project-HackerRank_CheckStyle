import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    static class T3 {
    
        public Integer tollSoFar;
        public Integer junc;
        public Integer toll;
        public T3 ( int aSoFar, int aJunc, int aTollVal) {
            tollSoFar = aSoFar;
            junc = aJunc;
            toll=aTollVal;
        }
        
        public String toString() {
            return "T3[" + tollSoFar + "," + junc + "," + toll + "]";
        }
    }

    static HashMap<Integer, HashMap<Integer,Integer>> cityMap ;
   
    static HashSet<T3> getNeighbSet(T3 t3src) {
        
        HashMap<Integer,Integer> nmap = cityMap.get(t3src.junc);
        //System.out.printf("nmap.size() [%d]", nmap.size());
        
        HashSet<T3> s  = new HashSet<T3>(nmap.size());
        //Set<Integer> keyset = nmap.keySet();
        //System.out.printf("keyset.size() [%d]", keyset.size());
        
        for(Integer c: nmap.keySet()) {
           // System.out.println("adding neigh:" + c);
            s.add(new T3(t3src.tollSoFar + t3src.toll, c, nmap.get(c)));
        }
        
        return s;
    }
    
    static HashSet<Integer> visited = new HashSet<Integer>();
    static ArrayList<Integer> tollList = new ArrayList<Integer>();
    
    // @return: list of the total toll between src and dst,null if no path
    static void findPathToll(int src , int dst) {

        visited.add(src);
        

        T3 t3src = new T3(0,src,0) ;
        //System.out.println(t3src);
            
        HashSet<T3> nset = getNeighbSet(t3src);
        //System.out.println("nset.size:" + nset.size());
        
        LinkedList<T3> pool = new LinkedList<T3>(nset);
       // System.out.println("new pool.size:" + pool.size());
                
        
        do {
             T3 next = pool.poll();
            
             if (next != null) {
                // System.out.println(next);
                 
                 if (!visited.contains(next.junc)) {
                     
                    // System.out.println("visiting:" + next.junc);
                     if (next.junc == dst) {
                         tollList.add(next.tollSoFar + next.toll);
                     }
                     else {
                         visited.add(next.junc);
                     }
                     pool.addAll(getNeighbSet(next));
                 }
             }
            
        } while (pool.size() >0);
         
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int e = in.nextInt();
        
        cityMap = new HashMap<Integer, HashMap<Integer,Integer>>(n); //key: junction
        
        for(int a0 = 0; a0 < e; a0++){
            int x = in.nextInt(); //junc
            int y = in.nextInt();
            int r = in.nextInt();
            
            HashMap<Integer,Integer> crossToll = cityMap.get(x);
            
            if (crossToll == null) {
                crossToll = new HashMap<Integer,Integer>();
                cityMap.put(x,crossToll);
            }
            crossToll.put(y,r);
            
            //The reverse dir
            crossToll = cityMap.get(y); 
            
            if (crossToll == null) {
                crossToll = new HashMap<Integer,Integer>();
                cityMap.put(y,crossToll);
            }
            crossToll.put(x,1000-r);
            
        }
        
        HashMap<Integer,Integer> retmap = new HashMap<Integer,Integer>(10);
        
        for (int i=0; i<10; i++) {
            retmap.put(new Integer(i), 0);
        }
        
        tollList.clear();
        int i=0;
        for (Integer src: cityMap.keySet()) {
            for (Integer dst: cityMap.keySet()) {
                
                if (src != dst) {
               // System.out.printf("i:[%d] [%d->%d]\n", i++ , src, dst);
                    
                    findPathToll(src,dst);
                    //System.out.printf("tl.size [%d] \n", tollList.size());
                    for(Integer t: tollList) {
                        int key = t % 10;
                        Integer keycnt = retmap.get(key);
                        
                        retmap.put(key, ++keycnt);
                        
                       // System.out.printf("     t[%d], key[%d]cnt [%d]\n", t, key, keycnt);
                    }
                    tollList.clear();
                    visited.clear();
                }
            }
        }
        //System.out.printf("cityMap.size() [%d]", cityMap.size());
       for (Integer key: retmap.keySet()) {
            System.out.println(retmap.get(key));
       }

    }
}