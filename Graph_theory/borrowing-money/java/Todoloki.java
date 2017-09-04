import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        int[] values = new int[N];
        boolean[][] link = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            values[i] = in.nextInt();
        }
        for (int i = 0; i < M; i++) {
            int i1 = in.nextInt() - 1;
            int i2 = in.nextInt() - 1;
            link[i1][i2] = true;
            link[i2][i1] = true;
        }
        HashMap<ArrayList<Integer>, Integer> map = new HashMap<ArrayList<Integer>, Integer>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        helper(map, path, values, link, 0, 0);
        HashMap<Integer, Integer> map2 = new HashMap<Integer, Integer>();
        int maxvalue = 0;
        int times = 0;
        for (int v : map.values()) {
            if (map2.containsKey(v)) {
                map2.put(v, map2.get(v) + 1);
            }
            else {
                map2.put(v, 1);
            }
            if (v > maxvalue) {
                maxvalue = v;
                times = 1;
            }
            else if (v == maxvalue) {
                times++;
            }
        }
        System.out.println(maxvalue + " " + times);
    }
    public static void helper(HashMap<ArrayList<Integer>, Integer> map, ArrayList<Integer> path, int[] values, boolean[][] link, int sum, int start) {
        map.put(new ArrayList<Integer>(path), sum);
        for (int i = start; i < values.length; i++) {
            if (isValid(path, link, i)) {
                path.add(i);
                helper(map, path, values, link, sum + values[i], i + 1);
                path.remove(path.size() - 1);
            }
        }
    }
    public static boolean isValid(ArrayList<Integer> path, boolean[][] link, int k) {
        for (int i = 0; i < path.size(); i++) {
            int j = path.get(i);
            if (link[k][j]) {
                return false;
            }
        }
        return true;
    }
}