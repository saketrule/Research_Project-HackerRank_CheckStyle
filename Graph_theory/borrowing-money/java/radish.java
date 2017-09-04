import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

class DemandingMoney {
    private static class Vertex {
        private enum State {
            UNVISITED,
            QUEUED,
            VISITED,
            SKIPPED
        };

        private Vertex parent = null;
        private State state = State.UNVISITED;

        private final int id;
        private final int money;
        private final Set<Vertex> neighbors = new HashSet<Vertex>();
        private Component component = null;

        public Vertex(int id, int money) {
            this.id = id;
            this.money = money;
        }

        public void clearState() {
            parent = null;
            component = null;
            state = State.UNVISITED;
        }

        public void setComponent(Component component) {
            this.component = component;
        }
    }

    private class Component {
        private int masterId;
        private int id;
        private Set<Set<Integer>> uniqueBits = new HashSet<>();
        private int maxMoney;
        private Set<Vertex> members = new HashSet<>();

        public Component(int id) {
            this.masterId = id;
            this.id = id;
        }

        public void setComponentId(int id) {
            setMasterComponentId(id);
            this.id = id;
        }

        public void setMasterComponentId(int id) {
            if (id < masterId) {
                masterId = id;
            }
        }

        private void clear() {
            uniqueBits.clear();
        }

        public void add(Vertex vertex) {
            members.add(vertex);
        }

        public void addBits(Set<Integer> bits, int maxMoney) {
            if (this.maxMoney > maxMoney) {
                return;
            }

            if (this.maxMoney < maxMoney) {
                clear();
                uniqueBits.add(bits);
                this.maxMoney = maxMoney;
                return;
            } else if (this.maxMoney == maxMoney) {
                uniqueBits.add(bits);

                /*
                System.out.println("addBits: id = " + id + ", maxMoney = "
                        + this.maxMoney + ", uniqueBits = "
                        + uniqueBits);
                */
            }
        }

        private void replaceMemberComponent(Component target) {
            for (Vertex member : members) {
                member.setComponent(target);
            }
        }

        public int merge(Component other) {
            int moneyIncrease = 0;
            if (maxMoney < other.maxMoney) {
                clear();
                uniqueBits = other.uniqueBits;
                moneyIncrease = other.maxMoney - maxMoney;
                maxMoney = other.maxMoney;
                other.replaceMemberComponent(this);
            } else if (maxMoney == other.maxMoney) {
                uniqueBits.addAll(other.uniqueBits);
                other.replaceMemberComponent(this);

                /*
                System.out.println("merge: id = " + id + ", maxMoney = "
                        + this.maxMoney + ", uniqueBits = "
                        + uniqueBits);
                */
            }
            return moneyIncrease;
        }

        public long maxCombinations() {
            long maxCombinations = 0;
            Map<Set<Integer>, Long> minSetToCombinations = new HashMap<>();
            for (Set<Integer> bits : uniqueBits) {
                int numZero = 0;
                Set<Integer> lookup = new HashSet<>(bits);
                for (Integer bit : bits) {
                    if (vertices[bit].money == 0) {
                        numZero++;
                        lookup.remove(bit);
                    }
                }
                Long lookupCombinations = minSetToCombinations.get(lookup);
                long computedCombinations = (long) Math.pow(2, numZero);
                if (lookupCombinations == null || lookupCombinations < computedCombinations) {
                    minSetToCombinations.put(lookup, computedCombinations);
                }
            }
            for (Long value : minSetToCombinations.values()) {
                maxCombinations += value;
            }
            // System.out.println("id = " + id + ", maxCombinations = " + maxCombinations);
            return maxCombinations;
        }
    }

    private class Graph {
        private int maxMoney = 0;
        private int numUnvisited = 0;
        private Map<Integer, Component> idToComponent = new HashMap<>();

        public Graph() {
        }

        public long maxCombinations() {
            long maxCombinations = (long) Math.pow(2, numUnvisited);
            Map<Integer, Long> masterIdToCombinations = new HashMap<>();
            for (Component component : idToComponent.values()) {
                // System.out.println("graph id: " + component.id + ", masterId = " + component.masterId + ", maxCombinations = " + component.maxCombinations() + ", uniqueBits = " + component.uniqueBits);
                Long currentCombinations = masterIdToCombinations.get(component.masterId);
                if (currentCombinations == null) {
                    masterIdToCombinations.put(component.masterId, component.maxCombinations());
                } else {
                    currentCombinations += component.maxCombinations();
                    masterIdToCombinations.put(component.masterId, currentCombinations);
                }
            }
            for (Long combinations : masterIdToCombinations.values()) {
                // System.out.println("master combinations = " + combinations);
                maxCombinations *= combinations;
            }

            return maxCombinations;
        }
    }

    private class Pair {
        private int left;
        private long right;

        public Pair(int left, long right) {
            this.left = left;
            this.right = right;
        }
    }

    private Vertex[] vertices;

    private DemandingMoney(Vertex[] vertices) {
        this.vertices = vertices;
    }

    private Component visit(Queue<Vertex> q, Vertex start) {
        // System.out.println("visiting " + start.id);
        Component component = new Component(start.id);
        int maxMoney = 0;
        Set<Integer> moneySet = new HashSet<>();
        start.state = Vertex.State.QUEUED;
        q.add(start);
        while (!q.isEmpty()) {
            Vertex current = q.poll();
            // System.out.println("current id = " + current.id);
            component.add(current);
            current.setComponent(component);
            if (current.id < component.id) {
                component.setComponentId(current.id);
            }
            if (current.parent == null
                    || current.parent.state == Vertex.State.SKIPPED) {
                current.state = Vertex.State.VISITED;
                // System.out.println(current.id + " : " +
                // current.money);
                maxMoney += current.money;
                moneySet.add(current.id);
            } else {
                current.state = Vertex.State.SKIPPED;
            }

            // Do not explore neighbors for vertices without money.
            if (current.money == 0) {
                continue;
            }

            for (Vertex v : current.neighbors) {
                // System.out.println("neighbor " + v.id + ", component id = " + (component != null ? component.id : null));
                if (v.component != null && v.component.id < component.id) {
                    if (v.money != 0) {
                        component.setComponentId(v.component.id);
                    } else {
                        component.setMasterComponentId(v.component.id);
                    }
                }
                if (v.state == Vertex.State.UNVISITED) {
                    v.parent = current;
                    v.state = Vertex.State.QUEUED;
                    q.add(v);
                } else if (current.state == Vertex.State.VISITED) {
                    v.parent = current;
                }
            }
        }

        component.addBits(moneySet, maxMoney);
        // System.out.println("start id = " + start.id + ", component id = " + component.id);
        return component;
    }

    private void visitAll(Vertex start, Graph graph) {
        Queue<Vertex> q = new LinkedList<Vertex>();

        int startId = start.id;
        // System.out.println("exploring " + startId);
        Vertex current = start;
        while (current != null) {
            Vertex next;
            if (current == start && start.id != 1) {
                next = vertices[1];
                start = null;
            } else {
                next = current.id < (vertices.length - 1) ? vertices[current.id + 1] : null;
            }
            // System.out.println("start = " + start + ", current vertex id = " + current.id + ", next = " + next);
            if (current.state != Vertex.State.UNVISITED || current.money == 0) {
                current = next;
                continue;
            }

            Component component = visit(q, current);
            Component currentComponent = graph.idToComponent.get(component.id);
            int moneyIncrease = 0;
            if (currentComponent == null) {
                moneyIncrease = component.maxMoney;
                graph.idToComponent.put(component.id, component);
            } else {
                moneyIncrease = currentComponent.merge(component);
            }
            graph.maxMoney += moneyIncrease;
            current = next;
        }
        int numUnvisited = 0;
        for (int k = 1; k < vertices.length; k++) {
            if (vertices[k].state == Vertex.State.UNVISITED) {
                numUnvisited++;
            }
            vertices[k].clearState();
        }
        graph.numUnvisited = numUnvisited;
    }

    public Pair computeMax() {
        Graph graph = new Graph();
        for (int i = 1; i < vertices.length; i++) {
            visitAll(vertices[i], graph);
            // System.out.println("maxMoney = " + graph.maxMoney + ", numMaxMoney = " + graph.maxCombinations());
        }

        return new Pair(graph.maxMoney, graph.maxCombinations());
    }

    public static void main(String[] args) throws Exception {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // long startTime = System.currentTimeMillis();
        if (n < 1 || n > 34) {
            throw new IllegalArgumentException("N = " + n);
        }

        int m = sc.nextInt();
        if (m < 0 || m > n*(n-1)/2) {
            throw new IllegalArgumentException("M = " + m);
        }
        // System.out.println("N = " + n + ", M = " + m);
        sc.nextLine();

        Vertex[] vertices = new Vertex[n + 1];
        for (int i = 1; i <= n; i++) {
            int money = sc.nextInt();
            if (money < 0 || money > 100) {
                throw new IllegalArgumentException("money[" + i + "] = " + money);
            }
            vertices[i] = new Vertex(i, money);
        }
        sc.nextLine();

        for (int i = 1; i <= m; i++) {
            int left = sc.nextInt();
            int right = sc.nextInt();
            if (left <= 0 || left > n || right <= 0 || right > n) {
                throw new IllegalArgumentException("left = " + left + ", right = " + right);
            }
            vertices[left].neighbors.add(vertices[right]);
            vertices[right].neighbors.add(vertices[left]);
            if (i != m) {
                sc.nextLine();
            }
        }
        sc.close();

        // long postInputStartTime = System.currentTimeMillis();
        DemandingMoney demandingMoney = new DemandingMoney(vertices);
        Pair maxMoney = demandingMoney.computeMax();
        System.out.println(maxMoney.left + " " + maxMoney.right);

        // long timeInMs = (System.currentTimeMillis() - startTime);
        // long postInputTimeInMs = (System.currentTimeMillis() - postInputStartTime);
        // System.out.println("time (in ms) = " + timeInMs);
    }
}