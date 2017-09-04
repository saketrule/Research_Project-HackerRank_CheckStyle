import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int[] paths;
    
    static void CountPath(ArrayList<HashSet<Integer>> nodes, int start) {
        //Set<Integer> used = new HashSet<Integer>();
        int n = nodes.size();
        boolean[] used = new boolean[n];
        boolean[] notUsed = new boolean[n];
        //Set<Integer> notUsed = new HashSet<Integer>();
        for(int i = 0; i < n; i++) {
            notUsed[i] = true;
        }
        int size = n - 1;
        notUsed[start] = false;
        used[start] = true;
        while(size > 0) {
            //Set<Integer> tmp = new HashSet<Integer>();
            boolean[] tmp = new boolean[n];
            for(int i = 0; i < n; i++) {
                if(notUsed[i]) {                    
                    Set<Integer> roads = nodes.get(i);
                    for(int j = 0; j < n; j++) {
                        if(used[j] && !roads.contains(j)) {
                            tmp[i] = true;
                            paths[i] = paths[j] + 1;
                        }
                    }  
                }
            }    
            for(int i = 0; i < n; i++) {
                if(tmp[i]) {
                    notUsed[i] = false;
                    size--;
                }
            }
            used = tmp;
        }
    }    
    
    static void TrickyPath(ArrayList<HashSet<Integer>> nodes, int start) {
        for(int i = 0; i < paths.length; i++) {
            if(i != start) {
                paths[i] = nodes.get(start).contains(i) ? 2 : 1;
            }
        }
    }
    
    static void Print(Set<Integer> set, String text) {
        System.out.println(text);
        for(Integer i : set) {
            System.out.print(" " + i);
        }
        System.out.println();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        for(int k = 0; k < t; k++) {
            String[] s = br.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            ArrayList<HashSet<Integer>> nodes = new ArrayList<HashSet<Integer>>(n);
            paths = new int[n];
            for(int i = 0; i < n; i++) {
                nodes.add(new HashSet<Integer>());
            }
            for(int i = 0; i < m; i++) {
                s = br.readLine().split(" ");
                int a = Integer.parseInt(s[0]) - 1;
                int b = Integer.parseInt(s[1]) - 1;
                nodes.get(a).add(b);
                nodes.get(b).add(a);            
            }
            int start = Integer.parseInt(br.readLine().trim()) - 1;
            if(n > 15000) {
               TrickyPath(nodes, start); 
            } else {
               CountPath(nodes, start);
            }
            StringBuilder sb = new StringBuilder("");
            for(int i = 0; i < n; i++) {
                if( i != start) {
                    sb.append(paths[i] + " ");
                }
            }
            System.out.println(sb.toString());
        }
    }
}