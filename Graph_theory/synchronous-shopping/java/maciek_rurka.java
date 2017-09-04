import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

public static class Input {

        // Number of shopping centers
        int N;
        // Number of roads
        int M;
        // Number of fish types
        int K;

        int[] typesInCenters;
        int[][] roads;
    }

    public static class Path {

        int pos = 0;
        long cost = 0;
        int items = 0;
        Path parent = null;
        long priority = 0;

        public Path(int pos, int items) {
            this(pos, null, 0, items);
        }

        public Path(int pos, Path parent, int cost, int items) {
            this.pos = pos;
            this.parent = parent;
            this.cost = parent != null ? parent.cost + cost : cost;
            this.items = parent != null ? (parent.items | items) : items;
        }

    }

    public Input read(InputStream in) {

        Scanner s = new Scanner(in);
        Input input = new Input();

        // Number of shopping centers
        input.N = s.nextInt();
        // Number of roads
        input.M = s.nextInt();
        // Number of fish types
        input.K = s.nextInt();

        input.typesInCenters = new int[input.N];

        for(int i=0; i<input.N; i++) {
            input.typesInCenters[i] = 0;
            int T = s.nextInt();
            for(int j=0; j<T; j++) {
                input.typesInCenters[i] = setBit(input.typesInCenters[i], s.nextInt()-1);
            }
        }

        input.roads = new int[input.N][input.N];

        for(int i=0; i<input.M; i++) {
            int c1 = s.nextInt() - 1;
            int c2 = s.nextInt() - 1;
            input.roads[c1][c2] = input.roads[c2][c1] = s.nextInt();
        }

        return input;

    }

    public static int setBit(int dest, int bitNo) {
        return dest | (1 << bitNo);
    }

    public static int bitCount(int dest) {
        int count = 0;
        for(int i=0; i<32; i++) {
            if((dest & (1 << i)) != 0) count++;
        }
        return count;
    }


    public long solution(Input in) {

        normalize(in);

        Queue<Path> queue = new LinkedList<>();

        final int ALL_ITEMS = (1 << in.K) - 1;
        final long[][] paths = new long[in.N][(1 << in.K)];
        for(int i=0; i<in.N; i++) {
            Arrays.fill(paths[i],Long.MAX_VALUE);
        }

        queue.add(new Path(0, in.typesInCenters[0]));
        long minCost = Long.MAX_VALUE;

        while(!queue.isEmpty()) {

            Path p = queue.poll();

            if(p.cost <= paths[p.pos][p.items]) {

                for (int i = 0; i < in.N; i++) {

                    if (in.roads[p.pos][i] > 0) {

                        long cost = in.roads[p.pos][i] + p.cost;
                        int items = p.items | in.typesInCenters[i];

                        if (cost < minCost && cost < paths[i][items])
                        {
                            Path p2 = new Path(i, p, in.roads[p.pos][i], in.typesInCenters[i]);

                            paths[p2.pos][p2.items] = p2.cost;
                            for (int j = 0; j <= p2.items; j++) {
                                if ((p2.items & j) == j) paths[p2.pos][j] = Math.min(paths[p2.pos][j], p2.cost);
                            }
                            queue.add(p2);

                            if (p2.pos == in.N - 1) {
                                for (int j = 0; j <= ALL_ITEMS; j++) {
                                    if ((p2.items | j) == ALL_ITEMS)
                                        minCost = Math.min(minCost, Math.max(paths[in.N - 1][j], p2.cost));
                                }
                            }
                        }
                    }
                }
            }
        }


        return minCost;

    }

    public void normalize(Input in) {

        for(int i=1; i<in.N-1; i++) {

            if(in.typesInCenters[i] == 0) {

                Map<Integer,Integer> connections = new HashMap<>();
                
                for(int dest=0; dest < in.N ; dest++) {
                    if(in.roads[i][dest] != 0) {
                        connections.put(dest, in.roads[i][dest]);
                        in.roads[dest][i] = in.roads[i][dest] = 0;
                    }
                }

                for(Map.Entry<Integer,Integer> src : connections.entrySet ()) {
                    for(Map.Entry<Integer,Integer> dest : connections.entrySet ()) {
                        int from = src.getKey();
                        int to = dest.getKey();
                        if(from != to) {
                            int dist = src.getValue() + dest.getValue();
                            if(in.roads[from][to] == 0 || in.roads[from][to] > dist) {
                                in.roads[from][to] = in.roads[to][from]= dist;
                            }
                        }
                    }
                }

            }

        }

    }

    public static void main(String[] args) {
        Solution s = new Solution();
        Input in = s.read(System.in);
        System.out.print(s.solution(in));
    }
}