import java.util.*;

public class SynchronousShopping {
    private static class Connection {
        int v;
        int time;

        Connection(int v, int time) {
            this.v = v;
            this.time = time;
        }
    }

    private static class Point {
        int v;
        int mask;

        Point(int v, int mask) {
            this.v = v;
            this.mask = mask;
        }
    }

    private static class ConnectionList extends ArrayList<Connection>{};

    private static int countBits(int a) {
        int count = 0;

        while (a != 0) {
            count += (a & 1);
            a >>= 1;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();

        int[] types = new int[n];

        for (int i=0; i<n; i++) {
            int t = in.nextInt();
            for (int j=0; j<t; j++) {
                types[i] |= 1 << (in.nextInt() - 1);
            }
        }

        ConnectionList[] connections = new ConnectionList[n];
        int[][] min = new int[n][1 << k];

        for (int i=0; i<n; i++) {
            connections[i] = new ConnectionList();
            Arrays.fill(min[i], Integer.MAX_VALUE);
        }

        for (int i=0; i<m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            int z = in.nextInt();

            connections[x].add(new Connection(y, z));
            connections[y].add(new Connection(x, z));
        }


        min[0][types[0]] = 0;
        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(0, types[0]));

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            int time = min[p.v][p.mask];
            int mask = p.mask;

            for (Connection c : connections[p.v]) {
                int v1 = c.v;
                int time1 = time + c.time;
                int mask1 = mask | types[v1];

                if (time1 < min[v1][mask1]) {
                    min[v1][mask1] = time1;
                    queue.add(new Point(v1, mask1));
                }
            }
        }


        int res = min[n - 1][min[n-1].length-1];

        for (int i=0; i<min[n-1].length-2; i++) {
            for (int j=i+1; j<min[n-1].length-1; j++) {
                if (countBits(i | j) == k) {
                    int time = Math.max(min[n - 1][i], min[n - 1][j]);

                    if (time < res) {
                        res = time;
                    }
                }
            }
        }

        System.out.println(res);
    }
}