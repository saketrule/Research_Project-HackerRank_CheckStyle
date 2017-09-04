import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static class Unit {
        int p = 0; int a = 0;
        public String toString() {
            return "[" + a + "," + p + "]";
        }
    }
    static class Pair {
        int h = 0; int c = 0;
        Pair( int c, int h) {this.h = h; this.c = c;}
        
        public String toString() {
            return "(" + c + "," + h + ")";
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Unit[] clients = new Unit[n];
        for (int i = 0 ; i < n ; i++) {
            clients[i] = new Unit(); clients[i].a = in.nextInt(); clients[i].p = in.nextInt();
        }
        //System.out.println(Arrays.toString(clients));
        Unit[] arr = new Unit[n];
        for (int i = 0 ; i < m ; i++) {
            arr[i] = new Unit(); arr[i].a = in.nextInt(); arr[i].p = in.nextInt();
        }
        //System.out.println(Arrays.toString(arr));
        
        //List<Pair> list = new ArrayList<Pair>();
        HashMap<Integer, ArrayList<Integer>> hMap = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < m ; j++) {            
                //System.out.println("comp -> " + clients[i] + " : " + arr[j] + " i " + i + "," + j);
                if ( clients[i].a < arr[j].a && arr[j].p <= clients[i].p) {
                    //System.out.println("adding -> " + clients[i] + " : " + arr[j] + " i " + i + "," + j);
                    //list.add(new Pair(i, j));
                    if (!hMap.containsKey(j)) hMap.put(j, new ArrayList<Integer>());
                    hMap.get(j).add(i);
                }
            }
        }
        //System.err.println(hMap);
        int ans = 0;
        HashMap<Integer, ArrayList<Integer>> cMap = new HashMap<Integer, ArrayList<Integer>>();
        for (int key : hMap.keySet()) {
            if ((hMap.get(key).size()) == 1) {
                int val = hMap.get(key).get(0);
                if (!cMap.containsKey(val)) cMap.put(val, new ArrayList<Integer>());
                cMap.get(val).add(key);
            } else {
                ++ans;
            }
        }
        //System.err.println(cMap);
        System.out.println(ans + cMap.keySet().size());
    }
}