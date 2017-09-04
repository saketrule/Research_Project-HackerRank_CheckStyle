import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TollCostDigits {

    static boolean DEBUG = false;

    class Node {
        Integer id;

        List<Node> neighbors;

        List<Integer> neighborTolls;

        Map<Node, Set<Integer>> reachingTolls;

        boolean reached;

        public Node(int _id) {
            id = new Integer(_id);
            neighbors = new ArrayList<Node>();
            neighborTolls = new ArrayList<Integer>();
            reachingTolls = new HashMap<Node, Set<Integer>>();
            reached = false;
        }

        public void addNeighbor(Node neighbor, int toll) {
            neighbors.add(neighbor);
            neighborTolls.add(new Integer(toll));
        }

        public List<Node> getNeighbors() {
            return neighbors;
        }

        public List<Integer> getNeighborTolls() {
            return neighborTolls;
        }

        public Map<Node, Set<Integer>> getReachingTolls() {
            return reachingTolls;
        }

        public Map<Node, Set<Integer>> updateReachingTolls(Node neighbor,
                Integer toNeighborToll,
                Map<Node, Set<Integer>> neighborReachingTolls) {
            if (DEBUG) {
                System.out.println("Update reaching tolls: (" + getId() + ","
                        + neighbor.getId() + ")");
            }
            if (!reached)
                reached = true;

            Map<Node, Set<Integer>> ret = new HashMap<Node, Set<Integer>>();
            for (Map.Entry<Node, Set<Integer>> reachingToll : neighborReachingTolls
                    .entrySet()) {
                Node n = reachingToll.getKey();
                Set<Integer> neighborNewTolls = reachingToll.getValue();
                Set<Integer> tollList = reachingTolls.get(n);
                int a = toNeighborToll.intValue();
                for (Integer neighborNewToll : neighborNewTolls) {
                    int b = neighborNewToll.intValue();
                    int c = (a + b) % 10;
                    Integer newToll = new Integer(c);
                    if (tollList == null) {
                        tollList = new HashSet<Integer>();
                        reachingTolls.put(n, tollList);
                    }
                    if (!tollList.contains(newToll)) {
                        if (DEBUG) {
                            System.out.println("X(" + getId() + "," + n.getId()
                                    + ") reachable with toll "
                                    + newToll.intValue());
                        }
                        tollList.add(newToll);
                        Set<Integer> changedTollList = ret.get(n);
                        if (changedTollList == null) {
                            changedTollList = new HashSet<Integer>();
                            ret.put(n, changedTollList);
                        }
                        changedTollList.add(newToll);
                    }
                }
            }

            Set<Integer> tollList = reachingTolls.get(neighbor);
            if (tollList == null) {
                tollList = new HashSet<Integer>();
                reachingTolls.put(neighbor, tollList);
            }
            Integer newToll = new Integer(toNeighborToll.intValue() % 10);
            if (!tollList.contains(newToll)) {
                if (DEBUG) {
                    System.out.println("Y(" + getId() + "," + neighbor.getId()
                            + ") reachable with toll " + newToll.intValue());
                }
                tollList.add(newToll);
                Set<Integer> changedTollList = ret.get(neighbor);
                if (changedTollList == null) {
                    changedTollList = new HashSet<Integer>();
                    ret.put(neighbor, changedTollList);
                }
                changedTollList.add(newToll);
            }
            return ret;
        }

        boolean isReached() {
            return reached;
        }

        public int getId() {
            return id;
        }
    }

    void recurseComputeTolls(Node target,
            Map<Node, Set<Integer>> changedReachingTolls) {
        if (DEBUG) {
            System.out.println("Analyzing target " + target.getId());
        }
        List<Node> neighbors = target.getNeighbors();
        List<Integer> neighborTolls = target.getNeighborTolls();

        for (int i = 0; i < neighbors.size(); ++i) {
            Node neighbor = neighbors.get(i);
            if (DEBUG) {
                System.out.println("(Source,Target)=(" + neighbor.getId() + ","
                        + target.getId() + ")");
            }
            Integer neighborToll = neighborTolls.get(i);
            Map<Node, Set<Integer>> nextChangedReachingTolls = neighbor
                    .updateReachingTolls(target, neighborToll,
                            changedReachingTolls);
            if (!nextChangedReachingTolls.keySet().isEmpty()) {
                recurseComputeTolls(neighbor, nextChangedReachingTolls);
            }
        }
    }

    void run() {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.next());
        int e = Integer.parseInt(scanner.next());
        int j1, j2;
        Node[] nodeIdMap = new Node[n + 1];

        for (int i = 0; i < e; ++i) {
            j1 = Integer.parseInt(scanner.next());
            j2 = Integer.parseInt(scanner.next());
            int toll = Integer.parseInt(scanner.next());

            Node n1 = nodeIdMap[j1];
            if (n1 == null) {
                n1 = new Node(j1);
                nodeIdMap[j1] = n1;
            }
            Node n2 = nodeIdMap[j2];
            if (n2 == null) {
                n2 = new Node(j2);
                nodeIdMap[j2] = n2;
            }

            n1.addNeighbor(n2, toll % 10);
            n2.addNeighbor(n1, 10 - (toll % 10));
        }

        for (int i = 1; i < n + 1; ++i) {
            Node init = nodeIdMap[i];
            if (!init.isReached()) {
                Map<Node, Set<Integer>> emptyMap = new HashMap<Node, Set<Integer>>();
                recurseComputeTolls(init, emptyMap);
            }
        }

        int[] count = new int[10];
        for (int i = 1; i < n + 1; ++i) {
            Node node = nodeIdMap[i];
            Map<Node, Set<Integer>> reachingTolls = node.getReachingTolls();
            for (Node reaching : reachingTolls.keySet()) {
                if (reaching != node) {
                    Set<Integer> tolls = reachingTolls.get(reaching);
                    for (Integer x : tolls) {
                        count[x.intValue()]++;
                    }
                }
            }
        }

        for (int i = 0; i < 10; ++i) {
            System.out.println(count[i]);
        }
        scanner.close();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new TollCostDigits().run();
    }

}