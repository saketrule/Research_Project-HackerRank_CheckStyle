/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
//package solutionkingdomconnectivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

/**
 *
 * @author Fuel
 */
public class Solution {

    static final String INPUT_FILE_NAME = "kingdom5.txt";
    Map<Integer, Integer>[] routes;
    int n;
    Set<Integer> loopset = new HashSet<Integer>();
    boolean[] passed, dynamiccount;
    BigDecimal[] dynamicmap;

    private void readInput() {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            String[] a;
            a = bf.readLine().split(" ");
            n = Integer.parseInt(a[0]);
            int m = Integer.parseInt(a[1]);
            routes = new HashMap[n];
            dynamicmap = new BigDecimal[n];
            dynamiccount = new boolean[n];
            passed = new boolean[n];
            Integer I, J, Temp;
            for (int k = 0; k < m; k++) {
                a = bf.readLine().split(" ");
                I = Integer.parseInt(a[0]) - 1;
                J = Integer.parseInt(a[1]) - 1;
                if (routes[I] == null) {
                    routes[I] = new HashMap<Integer, Integer>();
                }
                Temp = routes[I].put(J, 1);
                if (Temp != null) {
                    routes[I].put(J, ++Temp);
                }
            }
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readInput(String fname) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(new File(fname)));
            String[] a;
            a = bf.readLine().split(" ");
            n = Integer.parseInt(a[0]);
            int m = Integer.parseInt(a[1]);
            routes = new HashMap[n];
            dynamicmap = new BigDecimal[n];
            dynamiccount = new boolean[n];
            passed = new boolean[n];
            Integer I, J, Temp;
            for (int k = 0; k < m; k++) {
                a = bf.readLine().split(" ");
                I = Integer.parseInt(a[0]) - 1;
                J = Integer.parseInt(a[1]) - 1;
                if (routes[I] == null) {
                    routes[I] = new HashMap<Integer, Integer>();
                }
                Temp = routes[I].put(J, 1);
                if (Temp != null) {
                    routes[I].put(J, ++Temp);
                }
            }
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BigDecimal doCalculation(int start) {

        if (start == n - 1) {
            dynamicmap[start] = BigDecimal.valueOf(1);
            dynamiccount[start] = true;
            return BigDecimal.valueOf(1);
        } else {
            passed[start] = true;
            BigDecimal result = BigDecimal.valueOf(0);
            if (routes[start] != null) {
                for (Map.Entry<Integer, Integer> entry : routes[start].entrySet()) {
                    int j = entry.getKey();
                    if (passed[j]) {
                        loopset.add(start);
                        loopset.add(j);
                        continue;
                    }
                    if (dynamiccount[j]) {
                        result = result.add(dynamicmap[j].multiply(BigDecimal.valueOf(entry.getValue())));
                    } else {
                        BigDecimal t = doCalculation(j);
                        result = result.add(t.multiply(BigDecimal.valueOf(entry.getValue())));
                        //result += t * entry.getValue();
                    }
                }
            }
            passed[start] = false;
            dynamicmap[start] = result;
            dynamiccount[start] = true;
            return result;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.readInput();
        //solution.readInput(INPUT_FILE_NAME);
        BigDecimal result = solution.doCalculation(0);
        for (Iterator<Integer> it = solution.loopset.iterator(); it.hasNext();) {
            int i = it.next();
            if (solution.dynamicmap[i].compareTo(BigDecimal.valueOf(0))>0) {
                System.out.println("INFINITE PATHS");
                System.exit(0);
            }
        }
        System.out.print(result.remainder(BigDecimal.valueOf(1000000000)));
        //System.out.print("done");
    }
}