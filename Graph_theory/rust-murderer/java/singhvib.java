import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by bharat.s on 7/3/15.
 */
public class RustAndMurderer {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            String input[] = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            int M = Integer.parseInt(input[1]);
            HashMap<Integer, HashSet<Integer>> road = new HashMap<Integer, HashSet<Integer>>();
            HashSet<Integer> nodes = new HashSet<Integer>();
            for(int i=0; i<M; i++) {
                input = br.readLine().split(" ");
                int x = Integer.parseInt(input[0]);
                int y = Integer.parseInt(input[1]);
                nodes.add(x);
                nodes.add(y);
                if(!road.containsKey(x)) {
                    road.put(x, new HashSet<Integer>());
                }
                road.get(x).add(y);
                if(!road.containsKey(y)) {
                    road.put(y, new HashSet<Integer>());
                }
                road.get(y).add(x);
            }
            int S = Integer.parseInt(br.readLine());
            StringBuilder result = new StringBuilder("");

            // Interesting BFS
            Queue<Integer> queue = new LinkedList<Integer>();
            HashSet<Integer> L1 = new HashSet<Integer>();
            HashSet<Integer> L2 = new HashSet<Integer>();
            boolean visited[] = new boolean[N+1];
            for(int i=1; i<=N; i++) {
                L1.add(i);
            }
            queue.add(S);
            int distance[] = new int[N+1];
            distance[S] = 0;
            L1.remove((Integer) S);
            while(!queue.isEmpty()) {
                int u = queue.poll();
                if(road.containsKey(u)) {
                    for (int v : road.get(u)) {
                        if(!visited[v]) {
                            L1.remove((Integer) v);
                            L2.add(v);
                        }
                    }
                }
                ArrayList<Integer> temp = new ArrayList<Integer>();
                for(int v: L1) {
                    if(!visited[v]) {
                        visited[v] = true;
                        temp.add(v);
                        distance[v] = distance[u] + 1;
                        queue.add(v);
                    }
                }
                for(int t: temp) {
                    L1.remove((Integer) t);
                }
                L1.addAll(L2);
                L2 = new HashSet<Integer>();
            }

            for (int i=1; i<=N; i++) {
                if(S == i) continue;
                result.append(distance[i]).append(" ");
            }
            System.out.println(result.toString());
        }
    }

}
//
//
//        2  -  5
//       /        \
//     1    ---    6
//       \         /
//        3  -  4
//