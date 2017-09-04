import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        TreeMap<Integer,Integer> tree = new TreeMap<Integer,Integer>(); 
        for (int i=0; i<2*n-1; i++) {
            int key = sc.nextInt();
            if (!tree.containsKey(key)) {
                tree.put(key,1);
            }
            else {
                tree.put(key, tree.get(key) + 1);
            }
        }
        if (tree.size() != n) {
            System.out.println("NO");
            return;
        }
        LinkedList<Integer>[] counts = (LinkedList<Integer>[]) new LinkedList[(int)(Math.log(n)/Math.log(2))+2];
        for (int i=0; i<counts.length; i++) {
            counts[i] = new LinkedList<Integer>();
        }
        int[] tree_vals = new int[2*n];
        for (int i=0; i<2*n; i++) {
            tree_vals[i] = Integer.MAX_VALUE;
        }
        for (int key : tree.keySet()) {
            int val = tree.get(key);
            counts[val].add(key);
            if (Math.log(n)/Math.log(2) >= val && counts[val].size() > (int)Math.pow(2,(Math.log(n)/Math.log(2))-val)) {
                System.out.println("NO");
                return;
            }
        }
        tree_vals[1] = counts[counts.length-1].poll();
        for (int i=counts.length-2; i>0; i--) {
            for (int j=(int)Math.pow(2,counts.length-i-1); j<(int)Math.pow(2,counts.length-i); j +=2) {
                tree_vals[j] = tree_vals[j/2];
                for (int k=0; k<counts[i].size(); k++) {
                    if (counts[i].get(k) >= tree_vals[j/2]) {
                        tree_vals[j+1] = counts[i].remove(k);
                        break;
                    }
                }
            }
        }
        System.out.println("YES");
        for (int i=1; i<tree_vals.length; i++) { 
            System.out.print(tree_vals[i] + " ");
        }
    }
}