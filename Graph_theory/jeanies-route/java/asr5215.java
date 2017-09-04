import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        int cities = sc.nextInt();
        int letters = sc.nextInt();
        HashMap <Integer, ArrayList <Integer>> hm = new HashMap <Integer, ArrayList<Integer>> ();
        ArrayList <Integer> al = new ArrayList <Integer> ();
        for (int i = 0; i < letters; i++){
            al.add(sc.nextInt());
        }
        int [][] array = new int [cities][cities];
        for (int i = 1; i < cities; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            array[a][b] = c;
            array[b][a] = c;
            if (!hm.containsKey(a)){
                hm.put(a, new ArrayList <Integer> ());
            }
            if (!hm.containsKey(b)){
                hm.put(b, new ArrayList <Integer> ());
            }
            hm.get(a).add(b);
            hm.get(b).add(a);
        }
        PQsort pqs = new PQsort();
        PriorityQueue <Node> pq = new PriorityQueue <Node> (100, pqs);
    }
    
    static class PQsort implements Comparator<Node> {
 
  public int compare(Node one, Node two) {
   if (one.path < two.path){
                return -1;
            }
            else if ((one.path == two.path) && (one.items > two.items)){
                return -1;
            }
            else if ((one.path == two.path) && (one.items < two.items)){
                return 1;
            }
            if (one.path > two.path){
                return 1;
            }
            return 0;
  }
 }
    public static class Node{
        int items = 0;
        int path = 0;
        int currentplace = 1;
        public Node (int items, int path, int currentplace){
            this.items = items;
            this.path = path;
            this.currentplace = currentplace;
        }
    }
}