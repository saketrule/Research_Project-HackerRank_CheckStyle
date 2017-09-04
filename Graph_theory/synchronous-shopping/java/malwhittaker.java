import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 *
 */
public class Solution {

    static boolean DEBUG = false;
    static boolean SHOW_TIME = false;

    static String data = "12 13 2\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "1 2\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "1 1\n" +
            "0\n" +
            "1 2 1\n" +
            "2 3 1\n" +
            "4 5 1\n" +
            "5 6 1\n" +
            "6 7 1\n" +
            "7 8 1\n" +
            "7 3 1\n" +
            "6 8 0\n" +
            "6 4 0\n" +
            "1 9 100\n" +
            "9 10 1\n" +
            "10 12 1\n" +
            "11 12 1\n";

    static String data2 = "6 10 3\n" + // result == 792
            "2 1 2\n" +
            "1 3\n" +
            "0\n" +
            "2 1 3\n" +
            "1 2\n" +
            "1 3\n" +
            "1 2 572\n" +
            "4 2 913\n" +
            "2 6 220\n" +
            "1 3 579\n" +
            "2 3 808\n" +
            "5 3 298\n" +
            "6 1 927\n" +
            "4 5 171\n" +
            "1 5 671\n" +
            "2 5 463";

    static String data1 = "5 5 5\n" +
            "1 1\n" +
            "1 2\n" +
            "1 3\n" +
            "1 4\n" +
            "1 5\n" +
            "1 2 10\n" +
            "1 3 10\n" +
            "2 4 10\n" +
            "3 5 10\n" +
            "4 5 10";

    static int _initialNode = 1;
    static int _finalNode;
    static List<Node> _nodes;
    static Set<Road> _roads;
    static int _numberOfFish;

    static PriorityQueue<Path> _pathQueue;
    static Map<Set<Integer>,Path> _fishPathMap;

    static class Road {
        int _from;
        int _to;
        int _cost;
        Road _inverse;

        Road(int from, int to, int cost) {
            _from = from;
            _to = to;
            _cost = cost;
            _inverse = null;
        }

        Road getInverse() {
            if (_inverse == null) {
                _inverse = new Road(_to, _from, _cost);
            }
            return _inverse;
        }

        public int getFrom() {
            return _from;
        }

        public int getTo() {
            return _to;
        }

        public int getCost() {
            return _cost;
        }

        public void setCost(int cost) {
            _cost = cost;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Road road = (Road) o;

            if (_from != road._from) return false;
            return _to == road._to;

        }

        @Override
        public int hashCode() {
            int result = _from;
            result = 97 * result + _to;
            return result;
        }

        @Override
        public String toString() {
            return "{R" +
                    _from +
                    "->" + _to +
                    '}';
        }
    }

    static class Node {
        private Set<Integer> _fish;
        private List<Road> _roads;

        Node(Set<Integer> fish) {
            _fish = fish;
            _roads = new ArrayList<>();
        }

        void add(Road road) {
            _roads.add(road);
        }

        public Set<Integer> getFish() {
            return _fish;
        }

        public List<Road> getRoads() {
            return _roads;
        }

        public Road findRoadTo(int index) {
            Road found = null;
            for (Road road : _roads) {
                if (road.getTo() == index) {
                    found = road;
                    break;
                }
            }
            return found;
        }

        public void removeRoadTo(int index) {
            Road found = findRoadTo(index);
            if (found == null) {
                throw new RuntimeException("No road to " + index + " found.");
            }
            _roads.remove(found);
        }

        @Override
        public String toString() {
            return "{class: 'Node'" +
                    ", _fish:" + _fish +
                    ", _roads:" + _roads +
                    '}';
        }
    }

    static class Path {
        int _currentNode;
        int _cost;
        List<Integer> _nodeSequence;
        List<Integer> _newFish;
        Set<Road> _roads;
        Set<Integer> _fishCollected;
//        boolean _backtrackingPermitted;
//        boolean _newFishFoundAtCurrentNode;

        Path() {
            _currentNode = _initialNode;
            _cost = 0;
            _nodeSequence = new ArrayList<>();
            _newFish = new ArrayList<>();
            _nodeSequence.add(_initialNode);
            _newFish.add(_nodes.get(_initialNode).getFish().size());
            _roads = new HashSet<>();
            _fishCollected = new HashSet<>();
            _fishCollected.addAll(_nodes.get(_initialNode).getFish());
//            _backtrackingPermitted = false;
//            _newFishFoundAtCurrentNode = false;
        }

        Path(Path other) {
            _currentNode = other._currentNode;
            _cost = other._cost;
            _nodeSequence = new ArrayList<>();
            _nodeSequence.addAll(other._nodeSequence);
            _newFish = new ArrayList<>();
            _newFish.addAll(other._newFish);
            _roads = new HashSet<>();
            _roads.addAll(other._roads);
            _fishCollected = new HashSet<>();
            _fishCollected.addAll(other._fishCollected);
//            _backtrackingPermitted = other.isBacktrackingPermitted();
//            _newFishFoundAtCurrentNode = false;
        }

        int previousNode() {
            if (_nodeSequence.size() < 2) {
                return -1;
            }
            return _nodeSequence.get(_nodeSequence.size() - 2);
        }

        void add(Road r) {
            if (_roads.contains(r)) {
                throw new RuntimeException("Already travelled on  " + r + " from " +_currentNode);
            }
            _roads.add(r);
            _nodeSequence.add(r._to);
            _currentNode = r._to;
            _cost = _cost + r.getCost();
            int fishCountBefore = _fishCollected.size();
            _fishCollected.addAll(_nodes.get(_currentNode)._fish);
            int fishCountAfter = _fishCollected.size();
            _newFish.add(fishCountAfter - fishCountBefore);
//            _newFishFoundAtCurrentNode = fishCountAfter > fishCountBefore;

            // Once we find some new fish backtracking makes sense
//            _backtrackingPermitted = _backtrackingPermitted || _newFishFoundAtCurrentNode;

        }

        boolean isComplete() {
            return _currentNode == _finalNode;
        }

        boolean containsAllFish() {

            for (int f = 1; f <= _numberOfFish; f++) {
                if (!_fishCollected.contains(f)) {
                    return false;
                }
            }
            return true;
        }

        //        boolean isBacktrackingPermitted() {
//            return _backtrackingPermitted;
//        }
//
        boolean newFishSinceLastVisitToNode(int node) {
            for (int index = _nodeSequence.size() - 1; index >= 0; index--) {
                if (node == _nodeSequence.get(index)) {
                    return false;
                }
                if (_newFish.get(index) > 0) {
                    return true;
                }
            }
            throw new RuntimeException("No previous vist to " + node);
        }

        List<Road> findNextRoadOptions() {
            List<Road> options = new ArrayList<>();
            for (Road r : _nodes.get(_currentNode).getRoads()) {
                if (_roads.contains(r)) {
                    // r is not an option
                }
                else if (_nodeSequence.contains(r.getTo()) && !newFishSinceLastVisitToNode(r.getTo())) {
                    if (DEBUG) System.out.printf("Road %s not considered; pointleess return.\n", r);
                }
//                else if (!backtrackingPermitted && _roads.contains(r.getInverse())) {
//                    // inverse r is present
//                }
                else {
                    options.add(r);
                }
            }
            return options;
        }

        public int getCurrentNode() {
            return _currentNode;
        }

        public int getCost() {
            return _cost;
        }

        public List<Integer> getNodeSequence() {
            return _nodeSequence;
        }

        public Set<Integer> getFishCollected() {
            return _fishCollected;
        }

        @Override
        public String toString() {
            StringBuilder buffer = new StringBuilder();
            boolean first = true;
            buffer.append("[seq=");
            for (Integer node : _nodeSequence) {
                if (!first) {
                    buffer.append(",");
                }
                buffer.append(node);
                first = false;
            }
            buffer.append(String.format(" cost=%d, fish=%s]", _cost, _fishCollected));
            return buffer.toString();
        }

    }

    /**
     * Comparator for ordering Paths. The best path is the one with smallest cost; where costs are equal path with most
     * fish should be favoured.
     */
    static private class PathComparator implements Comparator<Path> {

        @Override
        public int compare(Path a, Path b) {
            // return negative value where a.cost is smaller
            int result = a.getCost() - b.getCost();
            if (result == 0) {
                // return negative value where a.fish.size is LARGER
                result = b._fishCollected.size() - a._fishCollected.size();
            }
            return result;
        }
    }

    static private class PathPair {
        private Path _a;
        private Path _b;

        /**
         *
         * @param a Must not be null
         * @param b
         */
        PathPair(Path a, Path b) {
            _a = a;
            _b = b;
        }

        int getCost() {
            return Math.max(_a.getCost(), _b == null ? 0 : _b.getCost());
        }

        @Override
        public String toString() {
            StringBuilder buffer = new StringBuilder();
            buffer.append("[\n");
            buffer.append("a=");
            buffer.append(_a);
            buffer.append("\nb=");
            buffer.append(_b);
            buffer.append("\n]");
            return buffer.toString();
        }
    }

    /**
     * Take the best option from the from of the queue, find alternate roads (not already used in this path), and create
     * a new path for each such road.
     * Note that it may be that this path has zero untried options (ie we've already travelled every edge from this node).
     * In this case the path is removed and no path is returned to the queue.
     */
    static private PathPair explorePaths() {
        _fishPathMap = new HashMap<>();
        _pathQueue = new PriorityQueue<>(_roads.size(), new PathComparator());
        _pathQueue.add(new Path());

        PathPair bestSolution = null;
        while (!_pathQueue.isEmpty()) {
            Path path = _pathQueue.poll();
            // Stop if the path to be examined is not as fast as a completed path we've already found
            if (bestSolution != null && bestSolution.getCost() < path.getCost()) {
                break;
            }
            List<Road> options = path.findNextRoadOptions();
            if (DEBUG) System.out.printf("explorePaths(): path selected; found %d options from %s\n", options.size(), path);
            for (Road next : options) {
                Path modifiedPath = new Path(path);
                modifiedPath.add(next);
                if (modifiedPath.isComplete()) {
                    PathPair solution = checkForSolution(modifiedPath);
                    if (solution != null) {
                        if (bestSolution == null || bestSolution.getCost() > solution.getCost()) {
                            bestSolution = solution;
                        }
                    }
                }
                _pathQueue.add(modifiedPath);
            }
        }
        return bestSolution;
    }

    /**
     * Register a new completed path.
     * If this path is complete, OR we can find a complementary path (one that collects all the fish that the given path
     * doesn't have) then we're done.
     *
     * @param path A newly discovered complete path.
     * @return A new PathPair that collects all the fish, or null if we have not yet identified such a pair.
     */
    static private PathPair checkForSolution(Path path) {
        if (path.containsAllFish()) {
            return new PathPair(path, null);
        }
        Path bestPrevious = _fishPathMap.get(path.getFishCollected());
        if (bestPrevious != null && bestPrevious.getCost() <= path.getCost()) {
            // Nothing has changed we found another path but we've already checked for complement paths
            return null;
        }
        _fishPathMap.put(path.getFishCollected(), path);
        Set<Integer> complementSet = createComplementaryFishSet(path.getFishCollected());
        Path complementPath = findComplementaryPath(complementSet, _fishPathMap);
        if (complementPath != null) {
            return new PathPair(path, complementPath);
        }
        // No complementary path
        return null;
    }

    private static Path findComplementaryPath(Set<Integer> fish, Map<Set<Integer>,Path> map) {
//        System.out.printf("findComplementaryPath(): enter; set=%s\n", fish);
        Path bestPath = null;
        for (Set<Integer> fishSet : map.keySet()) {
            if (fishSet.containsAll(fish)) {
                Path path = map.get(fishSet);
                if (bestPath == null || path.getCost() < bestPath.getCost()) {
                    bestPath = path;
                }
            }
        }
        return bestPath;
    }

    private static Set<Integer> createComplementaryFishSet(Set<Integer> fishSet) {
        Set<Integer> complementarySet = new HashSet<>();
        for (int f = 1; f <= _numberOfFish; f++) {
            if (!fishSet.contains(f)) {
                complementarySet.add(f);
            }
        }
//        System.out.printf("createComplimentaryFishSet(): exit; set=%s, complement=%s\n", fishSet, complementarySet);
        return complementarySet;
    }

    private static void initializeRoad(int from, int to, int cost) {
        Road road = new Road(from, to, cost);
        _roads.add(road);
        _nodes.get(from).add(road);
    }

    private static void trimGraph() {
        for (int index = 1 ; index < _nodes.size(); index++) {
            trimNode(index);
        }
    }

    /**
     * Examine the node at the given index, and if all these conditions are met, remove the road(s) between the node and
     * its only neighbor.
     *
     * * the node has exactly one road,
     * * the node is not the start node or the end node,
     * * the node has no fish.
     *
     * NOTE: If the node is removed, the neighbor might have been changed to a dead end, so check that neighbor.
     *
     * @param index to a node to be checked and possibly have its road removed.
     */
    private static void trimNode(int index) {
        if (index != _initialNode && index != _finalNode) {
            Node node = _nodes.get(index);
            if (node.getFish().isEmpty()) {
                if (node.getRoads().size() == 1) {
                    trimDeadEnd(index, node);
                }
                if (node.getRoads().size() == 2) {
                    trimPipeNodeFromGraph(index);
                }
            }
        }
    }

    private static void trimDeadEnd(int index, Node node) {
        //System.out.printf("trimDeadEnd(): enter; index=%d, node=%s\n", index, node);
        int neighborIndex = node.getRoads().get(0).getTo();
        Node neighbor = _nodes.get(neighborIndex);
        node.removeRoadTo(neighborIndex);
        neighbor.removeRoadTo(index);
        trimNode(neighborIndex);
    }

    private static void trimPipeNodeFromGraph(int index) {
        //System.out.printf("trimPipeNodeFromGraph(): enter; index=%d\n", index);
        Node node = _nodes.get(index);

        if (node.getRoads().size() == 2 && node.getFish().isEmpty()) {
            int neighbor0 = node.getRoads().get(0).getTo();
            int cost0 = node.getRoads().get(0).getCost();

            int neighbor1 = node.getRoads().get(1).getTo();
            int cost1 = node.getRoads().get(1).getCost();

            //System.out.printf("trimPipeNodeFromGraph(): changing; index=%d, node=%s, n0=%s, n1=%s\n", index, node, _nodes.get(neighbor0), _nodes.get(neighbor1));

            node.removeRoadTo(neighbor0);
            _nodes.get(neighbor0).removeRoadTo(index);

            node.removeRoadTo(neighbor1);
            _nodes.get(neighbor1).removeRoadTo(index);

            Road existingRoad = _nodes.get(neighbor0).findRoadTo(neighbor1);
            if (existingRoad != null) {
                if (existingRoad.getCost() > cost0 + cost1) {
                    existingRoad.setCost(cost0 + cost1);
                }
                // We've reduced the number roads to the neighbors, so check them in turn
                trimNode(neighbor0);
                trimNode(neighbor1);
            }
            else {
                initializeRoad(neighbor0, neighbor1, cost0 + cost1);
                initializeRoad(neighbor1, neighbor0, cost0 + cost1);
            }
            //System.out.printf("trimPipeNodeFromGraph(): changed; index=%d, node=%s, n0=%s, n1=%s\n", index, node, _nodes.get(neighbor0), _nodes.get(neighbor1));
        }
    }

    public static void main(String[] args) throws IOException {

//        Scanner scanner = new Scanner(new FileInputStream("synchronous-shopping-data-20.txt"));
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(new ByteArrayInputStream(data.getBytes()));
        int nodes = scanner.nextInt();
        int roads = scanner.nextInt();
        _numberOfFish = scanner.nextInt();
        if (DEBUG) System.out.printf("%d nodes, %d roads, %d fish\n", nodes, roads, _numberOfFish);

        _nodes = new ArrayList<>();
        _nodes.add(new Node(null)); // Dummy node[0] so node corresponds to list index
        for (int node = 1; node <= nodes; node++) {
            int nFish = scanner.nextInt();
            Set<Integer> fish = new HashSet<>();
            for (int f = 0; f < nFish; f++) {
                int typeOfFish = scanner.nextInt();
                assert(0 < typeOfFish && typeOfFish <= _numberOfFish);
                fish.add(typeOfFish);
            }
            _nodes.add(new Node(fish));
        }
        _finalNode = nodes;
        if (DEBUG) System.out.printf("%d nodes loaded\n", nodes);

        _roads = new HashSet<>();
        for (int r = 0; r < roads; r++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int cost = scanner.nextInt();

            initializeRoad(a, b, cost);
            initializeRoad(b, a, cost);
        }
        if (DEBUG) {
            System.out.printf("%d (x2) = % d roads loaded\n", roads, _roads.size());
            System.out.printf("\n");
            summarizeNetwork();
        }
        trimGraph();
        if (DEBUG) {
            System.out.printf("After trim...\n");
            summarizeNetwork();
        }


        long t0 = System.currentTimeMillis();
        PathPair solution = explorePaths();
        long t1 = System.currentTimeMillis();
        System.out.printf("%d\n", solution == null ? -1 : solution.getCost());
        if (SHOW_TIME) System.out.printf("Solution found in %.3f seconds\n", (t1 - t0) / 1000.0);
        if (SHOW_TIME) System.out.printf("%s\n", solution);
    }

    private static void summarizeNetwork() {
//        if (DEBUG) {
//            Map<Integer,Integer> roadsFrequencyMap = new HashMap<>();
            Map<Integer,List<Node>> degreeNodeMap = new HashMap<>();
            for (Node node : _nodes) {
                Integer roadCount = node.getRoads().size();
//                Integer frequency = roadsFrequencyMap.get(roadCount);
                List<Node> list = degreeNodeMap.get(roadCount);
                if (list == null) {
                    list = new ArrayList<>();
                    degreeNodeMap.put(roadCount, list);
                }
                list.add(node);
//                if (frequency == null) {
//                    frequency = 1;
//                }
//                else {
//                    frequency = frequency + 1;
//                }
//                roadsFrequencyMap.put(roadCount, frequency);
            }
            for (Integer count : degreeNodeMap.keySet()) {
                Integer frequency = degreeNodeMap.get(count).size();
                System.out.printf("%d nodes have %d roads\n", frequency, count);
            }
//        }
    }

}