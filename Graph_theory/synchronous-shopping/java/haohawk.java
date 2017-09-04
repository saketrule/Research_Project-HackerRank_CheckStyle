import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[][] ss = new int[n][];
        for (int i = 0; i < n; i++) {
            int s = in.nextInt();
            ss[i] = new int[s + 1];
            ss[i][0] = s;
            for (int j = 0; j < s; j++) {
                ss[i][j + 1] = in.nextInt();
            }
        }
        List<Integer[]> mm = new LinkedList<Integer[]>();
        for (int i = 0; i < m; i++) {
            Integer[] M = new Integer[3];
            M[0] = in.nextInt();
            M[1] = in.nextInt();
            M[2] = in.nextInt();
            mm.add(M);
        }

        mini(mm, n, ss, k);
    }

    static void visitAndBuy(List<Integer> route, int[][] ss, int[] K) {
        for (Integer aRoute : route) {
            int s = aRoute - 1;
            for (int j = 0; j < ss[s][0]; j++) {
                K[ss[s][1 + j] - 1] = 1;
            }
        }
    }

    static boolean isBuyAll(int[] K) {
        for (int aK : K) {
            if (aK != 1) return false;
        }
        return true;
    }

    static void addSubNodes(List<Integer[]> edges, int v, Queue<Integer> queue, Map<List<Integer>, Integer> routes) {
        List<Integer> parentRoute = null;
        int dist = 0;
        for (List<Integer> one : routes.keySet()) {
            if (one.get(one.size() - 1) == v) {
                parentRoute = one;
                dist = routes.get(one);
                break;
            }
        }

        for (Iterator<Integer[]> it = edges.iterator(); it.hasNext(); ) {
            Integer[] e = it.next();
            if (e[0] == v || e[1] == v) {
                int u = e[0] == v ? e[1] : e[0];
                queue.add(u);
                it.remove();

                List<Integer> route = new ArrayList<Integer>(parentRoute);
                route.add(u);
                routes.put(route, dist + e[2]);
                //System.out.println(Arrays.toString(route.toArray()) + ": " + (dist + e[2]));
            }
        }
    }

    static void mini(List<Integer[]> edges, int n, int[][] SS, int k) {
        Map<List<Integer>, Integer> routes = BSF(edges, n);

        TreeMap<Integer, List<Integer>> toNRoutes = new TreeMap<Integer, List<Integer>>();
        for (Iterator<List<Integer>> it = routes.keySet().iterator(); it.hasNext(); ) {
            List<Integer> route = it.next();
            if (route.get(route.size() - 1) == n) {
                toNRoutes.put(routes.get(route), route);
                it.remove();
            }
        }

        List<List<Integer>> mayRoutes = new ArrayList<List<Integer>>();
        for (Integer z : toNRoutes.keySet()) {
            List<Integer> toNRoute = toNRoutes.get(z);
            int[] K = new int[k];
            visitAndBuy(toNRoute, SS, K);
            if (isBuyAll(K)) {
                System.out.println(z);
                return;
            } else {
                for (int i = mayRoutes.size() - 1; i >= 0; i--) {
                    int[] bK = Arrays.copyOf(K, K.length);
                    visitAndBuy(mayRoutes.get(i), SS, bK);
                    if (isBuyAll(bK)) {
                        System.out.println(z);
                        return;
                    }
                }
                mayRoutes.add(toNRoute);
            }
        }

        //return path
        List<Map.Entry<List<Integer>, Integer>> sortedRoutes = new LinkedList<Map.Entry<List<Integer>, Integer>>(routes.entrySet());
        Collections.sort(sortedRoutes, new Comparator<Map.Entry<List<Integer>, Integer>>() {
            public int compare(Map.Entry<List<Integer>, Integer> o1,
                               Map.Entry<List<Integer>, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        List<Integer> ls = new ArrayList<Integer>();
        for (Integer z : toNRoutes.keySet()) {
            List<Integer> toNRoute = toNRoutes.get(z);
            int[] K = new int[k];
            visitAndBuy(toNRoute, SS, K);
            for (Map.Entry<List<Integer>, Integer> notToN : sortedRoutes) {
                visitAndBuy(notToN.getKey(), SS, K);
                if (isBuyAll(K)) {
                    System.out.println(z + notToN.getValue() * 2);
                    return;
                }
            }
        }
    }

    static Map<List<Integer>, Integer> BSF(List<Integer[]> edges, int n) {
        Map<List<Integer>, Integer> routes = new HashMap<List<Integer>, Integer>();
        List<Integer> root = new ArrayList<Integer>();
        root.add(1);
        routes.put(root, 0);
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        while (!queue.isEmpty()) {
            int v = queue.poll();
            if (v != n) {
                addSubNodes(edges, v, queue, routes);
            }
        }
        return routes;
    }
}