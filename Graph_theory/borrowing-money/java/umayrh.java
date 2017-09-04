import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private static final Comparator<BitSet> BITSET_COMPARATOR = new Comparator<BitSet>() {
        @Override
        public int compare(BitSet o1, BitSet o2) {
            return Integer.compare(o1.hashCode(), o2.hashCode());
        }
    };

    public static Result facilityLocation(
        int N,
        int M,
        int w[],
        Map<Integer, TreeSet<Integer>> map,
        Map<Integer, BitSet> adjacent
    ) {
        // all singletons
        if (M == 0) {
            if (N == 1) {
                return new Result(w[0], 1L);
            }
            return singletons(w);
        }
        // find the max and ways for each component in the graph
        int idx = 0;
        Result[] components = new Result[map.size()];
        for (Map.Entry<Integer, TreeSet<Integer>> component : map.entrySet()) {
            components[idx] = componentFacilityLocation(component.getValue(), w, adjacent);
            ++idx;
        }
        return components(components);
    }

    /*
     * For a given component, what's the max achieved and how many ways are there to achieve the max. It is guaranteed
     * that every node within a component is reachable from every other. This is the Maximum Independent Set problem.
     */
    private static Result componentFacilityLocation(
        TreeSet<Integer> component,
        int[] nodeWeights,
        Map<Integer, BitSet> adjacent
    ) {
        Result result = specialCases(component, nodeWeights, adjacent);
        if (result != null) {
            return result;
        }
        return maximumIndependentSet(component, nodeWeights, adjacent);
    }

    /*
     * @returns For a general connected and weighted graph, what's the max weighted independent set, and how many ways
     * are there to achieve the max. There are two ways to enumerate:
     *   1. select the node, v, from remnant component V that maximizes the values of w(v) +
     *   w(V - {v U N(v)}). V - {v U N(v)} is the remnant component for the next iteration.
     *   2. use power set expansion and check for independence for each subset
     */
    public static Result maximumIndependentSet(
        TreeSet<Integer> component,
        int[] nodeWeights,
        Map<Integer, BitSet> graphAdjacent
    ) {
        // map each node and neighbors in given component to a new range: 0 - (componentSize - 1). This makes
        // enumeration easier.
        int size = component.size();
        int[] compWeights = new int[size];
        int compNodeIdx = 0;
        Map<Integer, Integer> componentMap = new HashMap<>(size);
        for (int node : component) {
            compWeights[compNodeIdx] = nodeWeights[node];
            componentMap.put(node, compNodeIdx);
            ++compNodeIdx;
        }
        Map<Integer, BitSet> adjacent = new HashMap<>(size);
        for (int node : component) {
            BitSet adjacentSet = graphAdjacent.get(node);
            BitSet mappedAdjacent = new BitSet(size);
            for (int i = adjacentSet.length(); (i = adjacentSet.previousSetBit(i - 1)) >= 0;) {
                mappedAdjacent.set(componentMap.get(i));
            }
            adjacent.put(componentMap.get(node), mappedAdjacent);
        }
        return recursiveMethod(size, adjacent, compWeights);
        // return powerSetMethod(size, adjacent, compWeights);
    }

    /*
     * select the node, v, from remnant component V that maximizes the values of w(v) + w(V - {v U N(v)}). V - {v U
     * N(v)} is the remnant component for the next iteration.
     */
    private static Result recursiveMethod(int size, Map<Integer, BitSet> adjacent, int[] compWeights) {
        // all nodes to start with
        BitSet maxSet = new BitSet(size);
        maxSet.set(0, size);
        TreeSet<BitSet> stack = new TreeSet<>(BITSET_COMPARATOR);
        stack.add(maxSet);
        return recurse(stack, adjacent, compWeights);
    }

    /**
     * recursively find maximum weighted independent subset by enumerating all maximal independent subsets
     */
    private static Result recurse(
        TreeSet<BitSet> stack,
        Map<Integer, BitSet> adjacent,
        int[] compWeights
    ) {
        HashSet<BitSet> maximalSets = new HashSet<>();
        int maxSum = 0;
        while (!stack.isEmpty()) {
            boolean isIndependent = true;
            BitSet nodeSet = stack.pollFirst();
            // for a pair of adjacents, create two new
            for (int i = nodeSet.length(); (i = nodeSet.previousSetBit(i - 1)) >= 0;) {
                BitSet adjSet = adjacent.get(i);
                if (nodeSet.intersects(adjSet)) {
                    isIndependent = false;
                    BitSet newSet = (BitSet) nodeSet.clone();
                    newSet.clear(i); // keep neighbors
                    stack.add(newSet);
                    for (int j = adjSet.length(); (j = adjSet.previousSetBit(j - 1)) >= 0;) {
                        nodeSet.clear(j); // remove neighbors, reuse nodeSet
                    }
                    stack.add(nodeSet);
                    break;
                }
            }
            if (isIndependent) {
                int nodeSetSum = weightSum(nodeSet, compWeights);
                if (nodeSetSum > maxSum) {
                    maxSum = nodeSetSum;
                    maximalSets.clear();
                    maximalSets.add(nodeSet);
                } else if (nodeSetSum == maxSum) {
                    maximalSets.add(nodeSet);
                }
            }
        }
        HashSet<Integer> indepSets = new HashSet<>();
        for (BitSet nodeSet : maximalSets) {
            processMaximalSet(nodeSet, compWeights, indepSets);
        }
        return new Result(maxSum, indepSets.size());
    }

    private static void processMaximalSet(BitSet nodeSet, int[] compWeights, HashSet<Integer> indepSets) {
        // generate and store all non-maximal independent subsets from a given maximal
        List<Integer> nodesWithZeros = new ArrayList<>(nodeSet.length() + 1);
        BitSet nodeSetClone = (BitSet) nodeSet.clone();
        for (int i = nodeSet.length(); (i = nodeSet.previousSetBit(i - 1)) >= 0;) {
            if (compWeights[i] == 0) {
                nodesWithZeros.add(i);
                nodeSetClone.clear(i);
            }
        }
        if (nodesWithZeros.isEmpty()) {
            indepSets.add(nodeSetClone.hashCode());
        } else {
            // no empty sets, please
            if (nodeSetClone.cardinality() >= 1) {
                indepSets.add(nodeSetClone.hashCode());
            }
            long maxIdx = (long) (Math.pow(2, nodesWithZeros.size()) - 1);
            for (long idx = 1; idx <= maxIdx; ++idx) {
                BitSet idxSet = BitSet.valueOf(new long[] { idx });
                BitSet cloneWithZeros = (BitSet) nodeSetClone.clone();
                for (int i = idxSet.length(); (i = idxSet.previousSetBit(i - 1)) >= 0;) {
                    cloneWithZeros.set(nodesWithZeros.get(i));
                }
                indepSets.add(cloneWithZeros.hashCode());
            }
        }
    }

    /*
     * enumerate all sets - equivalent to finding all bit sets from 1 to 2^n, n = component size: O(n^2 2^n)
     */
    @SuppressWarnings("unused")
    private static Result powerSetMethod(int size, Map<Integer, BitSet> adjacent, int[] compWeights) {
        int maxSetSum = 0;
        int maxSetCount = 0;
        long maxIdx = (long) (Math.pow(2, size) - 1);
        for (long idx = 1; idx <= maxIdx; ++idx) {
            BitSet set = BitSet.valueOf(new long[] { idx });
            // can this check be memoized?
            if (isIndependent(set, adjacent)) {
                int setSum = weightSum(set, compWeights);
                if (setSum > maxSetSum) {
                    maxSetSum = setSum;
                    maxSetCount = 1;
                } else if (setSum == maxSetSum) {
                    ++maxSetCount;
                }
            }
        }
        // number of ways to get the max weight
        return new Result(maxSetSum, maxSetCount);
    }

    /*
     * @return true iff the nodes in a given set are independent. Assumes that all BitSets involved are of the same
     * size, and that a node is not adjacent to itself. O(n^2) really, but still much faster
     */
    private static boolean isIndependent(BitSet set, Map<Integer, BitSet> adjacent) {
        for (int i = set.length(); (i = set.previousSetBit(i - 1)) >= 0;) {
            if (set.intersects(adjacent.get(i))) {
                return false;
            }
        }
        return true;
    }

    /*
     * @returns the sum of the weights of all nodes in given set
     */
    private static int weightSum(BitSet set, int[] w) {
        int weight = 0;
        for (int i = set.length(); (i = set.previousSetBit(i - 1)) >= 0;) {
            weight += w[i];
        }
        return weight;
    }

    /*
     * Special graphs: handle singletons
     */
    private static Result specialCases(TreeSet<Integer> component, int[] nodeWeights, Map<Integer, BitSet> adjacent) {
        int componentSize = component.size();
        if (componentSize == 1) {
            return new Result(nodeWeights[component.first()], 1);
        }
        return null;
    }

    /*
     * For N components, each with weight w[i] >= 0, returns the sum of weights, and the number of ways to achieve
     * sum-weight, across components. For all non-zero components with ways = 1, there's only one way to achieve
     * sum-weight. For zeros, well, it's a little more complicated. E.g. given components with 0 max, and ways (a, b,
     * c), there're 2^3 combinations of combinations: a + b + c + (a * b) + (a * c) + (b * c) + (a * b * c) = (a + 1)(b
     * + 1)(c + 1) e.g. 1 + 2 + 3 + 1*2 + 2*3 + 1*3 + 1*2*3 + 1 (including null set)
     */
    private static Result components(Result[] c) {
        int zeros = 0, sum = 0;
        BigInteger nonzeroProduct = BigInteger.ONE;
        for (int i = 0; i < c.length; ++i) {
            if (c[i].max == 0) {
                ++zeros;
            } else {
                nonzeroProduct = nonzeroProduct.multiply(c[i].ways);
            }
            sum += c[i].max;
        }
        // all components have non-zero max
        if (zeros == 0) {
            return new Result(sum, nonzeroProduct);
        }
        // since we already have the number of ways to sum all non-zero components,
        // find the number of way to sum zero components by evaluating the product polynomial
        BigInteger zeroTotal = BigInteger.ONE;
        for (int i = 0; i < c.length; ++i) {
            if (c[i].max == 0) {
                BigInteger ways = c[i].ways;
                zeroTotal = zeroTotal.multiply(ways.add(BigInteger.ONE));
            }
        }
        if (zeros == c.length) {
            return new Result(sum, zeroTotal);
        }
        // now the total number is nonzeroProduct * (zeroTotal) since non-zero components together form a valid
        // combination without any zero components involved.
        return new Result(sum, nonzeroProduct.multiply(zeroTotal));
    }

    /*
     * For N singletons, each with weight w[i] >= 0, returns the sum of weights, and the number of ways to achieve
     * sum-weight, across singletons. For all non-zero components, there's only one way to achieve sum-weight.
     */
    private static Result singletons(int[] w) {
        int zeros = 0, sum = 0;
        for (int i = 0; i < w.length; ++i) {
            if (w[i] == 0) {
                ++zeros;
            }
            sum += w[i];
        }
        // zeros translates to power-set (including the null set)
        if (zeros == w.length) {
            return new Result(0, BigInteger.valueOf(2).pow(w.length));
        } else if (zeros == 0) {
            new Result(sum, 1);
        }
        // size of the union of non-zeros (treated as one set) and the powerset of zeros i.e. null set is accounted for
        return new Result(sum, BigInteger.valueOf(2).pow(zeros));
    }

    /**
     * Reads input, maintain reachability and neighborhood maps, solves location for each component, finds max across
     * components, and the number of ways to sum to that max across components
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        InputReader in = new InputReader(System.in);
        int N = in.readInt();
        int M = in.readInt();

        // node weights
        int weights[] = new int[N];
        for (int i = 0; i < N; ++i) {
            weights[i] = in.readInt();
        }

        // root to nodes: reachability set - won't be empty
        Map<Integer, TreeSet<Integer>> map = new HashMap<>(N);
        // node to root - may be empty
        Map<Integer, Integer> rev = new HashMap<>(N);
        // neighborhood sets - may be empty
        Map<Integer, BitSet> adjacent = new HashMap<>(N);

        for (int i = 0; i < M; i++) {
            int a = in.readInt() - 1;
            int b = in.readInt() - 1;
            /*
             * maintain neighborhood
             */
            if (!adjacent.containsKey(a)) {
                adjacent.put(a, new BitSet(N));
            }
            if (!adjacent.containsKey(b)) {
                adjacent.put(b, new BitSet(N));
            }
            adjacent.get(a).set(b);
            adjacent.get(b).set(a);
            /*
             * maintain connected components
             */
            // entirely new nodes
            if (!rev.containsKey(a) && !rev.containsKey(b)) {
                rev.put(a, a);
                rev.put(b, a);
                TreeSet<Integer> reach = new TreeSet<>();
                reach.add(a);
                reach.add(b);
                map.put(a, reach);
            } else if (rev.containsKey(a) && rev.containsKey(b)) {
                // case where two islands end up being connected, merge
                int root1 = rev.get(a);
                int root2 = rev.get(b);
                if (root1 != root2) {
                    int size1 = map.get(root1).size();
                    int size2 = map.get(root2).size();
                    int newRoot = size1 > size2 ? root1 : root2;
                    int oldRoot = size1 <= size2 ? root1 : root2;
                    // this might still be slow
                    TreeSet<Integer> old = map.remove(oldRoot);
                    for (Integer v : old) {
                        rev.put(v, newRoot);
                    }
                    rev.put(b, newRoot);
                    map.get(newRoot).addAll(old);
                }
            } else {
                // case where only one node was seen before
                int n = rev.containsKey(a) ? a : b;
                int root = rev.get(n);
                TreeSet<Integer> reach = map.get(root);
                reach.add(a);
                reach.add(b);
                if (!rev.containsKey(a)) {
                    rev.put(a, root);
                } else if (!rev.containsKey(b)) {
                    rev.put(b, root);
                }
            }
        }

        // Infer implicit nodes i.e. singletons. This can be optimized to avoid creating new objects
        TreeSet<Integer> universe = new TreeSet<>();
        for (TreeSet<Integer> reach : map.values()) {
            universe.addAll(reach);
        }
        if (universe.size() != N) {
            for (int i = 0; i < N; ++i) {
                if (!universe.contains(i)) {
                    TreeSet<Integer> set = new TreeSet<>();
                    set.add(i);
                    map.put(i, set);
                }
            }
        }

        Result res = facilityLocation(N, M, weights, map, adjacent);
        System.out.println(res.max + " " + res.ways);
    }

    /**
     * Stores result
     */
    static class Result {
        final int max;
        final BigInteger ways;

        Result(int max, long ways) {
            this(max, BigInteger.valueOf(ways));
        }

        Result(int max, BigInteger ways) {
            this.max = max;
            this.ways = ways;
        }
    }

    /**
     * Fast read
     */
    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024 * 1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
    }
}