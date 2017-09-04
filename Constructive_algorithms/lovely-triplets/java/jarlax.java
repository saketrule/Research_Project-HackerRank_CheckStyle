import java.util.*;

public class Solution {
    private static class Node {
        private static int n = 1;
        private static final Set<Integer> visited = new HashSet<>();

        private final int id;
        private final List<Node> children = new ArrayList<>();

        public Node() {
            id = n++;
        }

        public Node add() {
            Node n = new Node();
            children.add(n);
            n.children.add(this);
            return n;
        }

        public void add(Node n) {
            children.add(n);
        }

        public void fill(List<Integer> from, List<Integer> to) {
            if (visited.contains(id)) {
                return;
            }
            visited.add(id);
            for (Node c : children) {
                if (id < c.id) {
                    from.add(id);
                    to.add(c.id);
                }
            }
            for (Node c : children) {
                c.fill(from, to);
            }
        }

        public static void reset() {
            n = 1;
            visited.clear();
        }

        public static int N() {
            return n - 1;
        }
    }

    private static void add(Map<Integer, List<Integer>> container, int k, int v) {
        if (!container.containsKey(k)) {
            container.put(k, new ArrayList<Integer>());
        }
        container.get(k).add(v);
    }

    private static int[] split2(int p) {
        Map<Integer, List<Integer>> parts = new HashMap<>();
        int c = 3;
        while (true) {
            int num = c * (c - 1) * (c - 2) / 6;
            if (num > p) {
                break;
            }
            add(parts, num, c++);
        }

        while (!parts.containsKey(p)) {
            Map<Integer, List<Integer>> nparts = new HashMap<>(parts);
            for (int i = 1; i <= p; ++i) {
                if (!parts.containsKey(i)) continue;
                for (int j = 0; j <= p; ++j) {
                    if (i + j > p) continue;
                    if (!parts.containsKey(j)) continue;
                    if (nparts.containsKey(i + j)) continue;
                    for (int e : parts.get(i)) {
                        add(nparts, i + j, e);
                    }
                    for (int e : parts.get(j)) {
                        add(nparts, i + j, e);
                    }
                }
            }
            parts = nparts;
        }

        List<Integer> part = parts.get(p);
        int[] res = new int[part.size()];
        int idx = 0;
        for (int e : part) {
            res[idx++] = e;
        }
        return res;
    }

    private static int sum(List<Integer> l) {
        int res = 0;
        for (int e : l) {
            res += e;
        }
        return res;
    }

    private static int[] splitn(int p) {
        Map<Integer, List<Integer>> parts = new HashMap<>();
        for (int a = 1; a <= 30; ++a) {
            for (int b = 1; b <= 30; ++b) {
                for (int c = 1; c <= 30; ++c) {
                    int m = a * b * c;
                    if (m > p) continue;
                    if (parts.containsKey(m)) {
                        int s = 0;
                        for (int e : parts.get(m)) {
                            s += e;
                        }
                        if (s < a + b + c)
                            continue;
                    }
                    List<Integer> l = new ArrayList<>();
                    l.add(a);
                    l.add(b);
                    l.add(c);
                    parts.put(m, l);
                }
            }
        }

        List<Integer> keys = new ArrayList<>();
        for (int k : parts.keySet()) {
            keys.add(k);
        }
        for (int k : keys) {
            for (int k1 : keys) {
                int kn = k + k1;
                if (kn > p) continue;

                List<Integer> cmb = new ArrayList<>();
                for (int ee : parts.get(k)) {
                    cmb.add(ee);
                }
                for (int ee : parts.get(k1)) {
                    cmb.add(ee);
                }
                int n = sum(cmb);

                boolean add = true;
                if (parts.containsKey(kn)) {
                    if (sum(parts.get(kn)) <= n) {
                        add = false;
                    }
                }
                if (add) {
                    parts.put(kn, cmb);
                }
            }
        }

        List<Integer> part = parts.get(p);
        int[] res = new int[part.size()];
        for (int i = 0; i < res.length; ++i) {
            res[i] = part.get(i);
        }
        return res;
    }

    private static void solve(int p) {
        int[] s = split2(p);
        List<Integer> from = new ArrayList<>();
        List<Integer> to = new ArrayList<>();
        Node.reset();
        for (int e : s) {
            Node root = new Node();
            for (int i = 1; i <= e; ++i) {
                root.add();
            }
            root.fill(from, to);
        }

        out(from, to, Node.N());
    }

    private static Node[] skel(int q) {
        Node a, b, c;
        if (q % 2 == 1) {
            a = new Node();
            b = a.add();
            c = b.add();
            a.add(c);
        } else {
            a = b = c = new Node();
        }
        for (int i = 0; i < q/2 - 1; ++i) {
            a = a.add();
            b = b.add();
            c = c.add();
        }
        return new Node[]{a, b, c};
    }

    private static void solve(int p, int q) {
        int[] s = splitn(p);
        List<Integer> from = new ArrayList<>();
        List<Integer> to = new ArrayList<>();
        Node.reset();
        for (int i = 0; i < s.length; i += 3) {
            Node[] r = skel(q);
            for (int j = 0; j < 3; ++j) {
                Node root = r[j];
                int cnt = s[i + j];
                for (int k = 0; k < cnt; ++k) {
                    root.add();
                }
            }
            r[0].fill(from, to);
        }

        out(from, to, Node.N());
    }

    public static void out(List<Integer> from, List<Integer> to, int n) {
        int m = from.size();
        System.out.println(n + " " + m);
        for (int i = 0; i < m; ++i) {
            System.out.println(from.get(i) + " " + to.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int p = in.nextInt();
        int q = in.nextInt();
        if (q == 2) {
            solve(p);
        } else {
            solve(p, q);
        }
    }
}