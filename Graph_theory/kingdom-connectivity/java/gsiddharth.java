/* Enter your code here. Read input from STDIN. Print output to STDOUT */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author siddharth
 */
public class Solution {

    private static Map<Integer, List<Integer>> paths = new HashMap<Integer, List<Integer>>();
    private static Map<Integer, Map<Integer, Integer>> connectivity = new HashMap<Integer, Map<Integer, Integer>>();
    private static Set<Integer> underConsideration = new HashSet<Integer>();

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] nm = reader.readLine().split(" ");
            int N = Integer.parseInt(nm[0]);
            int M = Integer.parseInt(nm[1]);

            for (int i = 0; i < M; i++) {
                String[] ab = reader.readLine().split(" ");
                int a = Integer.parseInt(ab[0]);
                int b = Integer.parseInt(ab[1]);

                if (!paths.containsKey(a)) {
                    paths.put(a, new ArrayList<Integer>());
                }
                paths.get(a).add(b);
            }

            int con = getConnectivity(1, N);

            if (con == -2) {
                System.out.println(0);
            } else if (con == -1) {
                System.out.println("INFINITE PATHS");
            } else {
                System.out.println(con);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getConnectivity(int start, int target) {

        if (start == target) {
            return 1;
        }

        if (connectivity.containsKey(start)) {
            Map<Integer, Integer> m = connectivity.get(start);
            if (m.containsKey(target)) {
                return m.get(target);
            }
        }

        int res = 0;

        List<Integer> nodes = paths.get(start);
        if (nodes == null) {
            return 0;
        }

        underConsideration.add(start);

        boolean loop = false;
        boolean infinityPossible = false;
        for (Integer n : nodes) {
            if (underConsideration.contains(n)) {
                if (res > 0) {
                    res = -1;
                    break;
                } else {
                    infinityPossible = true;
                    loop = true;
                }
            } else {
                int c = getConnectivity(n, target);
                if (c == -2) {
                    infinityPossible = true;
                }
                c = c % 1000000000;
                if (c >= -1) {
                    if (!connectivity.containsKey(n)) {
                        connectivity.put(n, new HashMap<Integer, Integer>());
                    }
                    connectivity.get(n).put(target, c);
                }
                if (c > 0 && !infinityPossible) {
                    res += c;
                    res = res % 1000000000;
                } else if (c > 0 && infinityPossible) {
                    res = -1;
                    break;
                } else if (c == -1) {
                    res = -1;
                    break;
                }
            }
        }


        underConsideration.remove(start);
        
        if (res == 0 && infinityPossible && loop) {
            return -2;
        } else {
            return res;
        }
    }
}