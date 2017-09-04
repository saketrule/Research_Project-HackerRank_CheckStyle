import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


final class IO {
    //Standard IO
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer = null;

    static int ni() {
        return Integer.parseInt(ns());
    }

    static long nl() {
        return Long.parseLong(ns());
    }

    static double nd() {
        return Double.parseDouble(ns());
    }

    static String ns() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(br.readLine());
            } catch (IOException e) {
            }
        }
        return tokenizer.nextToken();
    }

    static String nline() {
        tokenizer = null;
        String ret = null;
        try {
            ret = br.readLine();
        } finally {
            return ret;
        }
    }
}

public class TeamFormation {
    static class Node {
        int currValue;
        int size;
        Node(int currValue) {
            this.currValue = currValue;
        }
    }

    static int f(Node[] n, PriorityQueue<Node> prior) {
        int min = Integer.MAX_VALUE;
        int i = 0;
        while (i < n.length) {
            Node v = n[i];
            if (prior.isEmpty()) {
                prior.add(v);
                i++;
            } else {
                Node top = prior.peek();
                if (top.currValue + 1 == v.currValue) {
                    top = prior.poll();
                    top.currValue = v.currValue;
                    top.size++;
                    i++;
                    prior.add(top);

                } else if (top.currValue == v.currValue) {
                    prior.add(v);
                    i++;

                } else {
                    top = prior.poll();
                    min = Math.min(min, top.size);

                }
            }
        }
        while (!prior.isEmpty()) {
            min = Math.min(min, prior.poll().size);
        }
        return min + 1;
    }

    public static void main(String[] args) {
        int T = IO.ni();
        for (int j = 0; j < T; j++) {
            int N = IO.ni();
            if (N == 0) {
                System.out.println(0);
                continue;
            }
            Node[] v = new Node[N];
            for (int i = 0; i < v.length; i++) {
                v[i] = new Node(IO.ni());
            }
            Arrays.sort(v, new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return Integer.compare(o1.currValue, o2.currValue);
                }
            });
            PriorityQueue<Node> prior = new PriorityQueue<>(N,
                    new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                            int v = Integer.compare(o1.currValue, o2.currValue);
                            return (v != 0) ? v : Integer.compare(o1.size, o2.size);
                }
                    });
            System.out.println(f(v, prior));

        }
    }
}