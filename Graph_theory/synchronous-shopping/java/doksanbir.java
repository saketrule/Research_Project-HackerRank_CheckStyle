import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


class Pair<First extends Comparable<First>, Second extends Comparable<Second>> implements Comparable<Pair<First, Second>> {
    private final First first;
    private final Second second;
    
    public Pair(First first, Second second) {
        this.first = first;
        this.second = second;
    }
    
    public First getFirst() {
        return first;
    }
    
    public Second getSecond() {
        return second;
    }
    
    @Override
    public String toString() {
        return "Pair {" +
                   "first=" + Objects.toString(first) + ", " +
                   "second=" + Objects.toString(second) +
               "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Pair)) {
            return false;
        }

        @SuppressWarnings("unchecked")
        Pair<First, Second> other = (Pair<First, Second>) obj;
        return Objects.equals(first, other.first) &&
               Objects.equals(second,  other.second);
    }

    @Override
    public int compareTo(Pair<First, Second> other) {
        if (!first.equals(other.first)) {
            return first.compareTo(other.first);
        }
        return second.compareTo(other.second);
    }
}

class Triplet<First extends Comparable<First>, Second extends Comparable<Second>, Third extends Comparable<Third>> 
        implements Comparable<Triplet<First, Second, Third>> {
    
    private final First first;
    private final Second second;
    private final Third third;
    
    public Triplet(First first, Second second, Third third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
    
    public First getFirst() {
        return first;
    }
    
    public Second getSecond() {
        return second;
    }
    
    public Third getThird() {
        return third;
    }
    
    @Override
    public String toString() {
        return "Triplet {" +
                   "first=" + Objects.toString(first) + ", " +
                   "second=" + Objects.toString(second) + ", " + 
                   "third=" + Objects.toString(third) +
               "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, third);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Triplet)) {
            return false;
        }

        @SuppressWarnings("unchecked")
        Triplet<First, Second, Third> other = (Triplet<First, Second, Third>) obj;
        return Objects.equals(first, other.first) &&
               Objects.equals(second,  other.second) &&
               Objects.equals(third, other.third);
    }

    @Override
    public int compareTo(Triplet<First, Second, Third> other) {
        if (!first.equals(other.first)) {
            return first.compareTo(other.first);
        }
        if (!second.equals(other.second)) {
            return second.compareTo(other.second);
        }
        return third.compareTo(other.third);
    }
}

class Graph<VertexType extends Comparable<VertexType>, WeightType extends Comparable<WeightType>> {
    
    private final boolean isBiDirectional;
    private Map<VertexType, Set<VertexType>> adjacencyMap = new HashMap<>();
    private Map<Pair<VertexType, VertexType>, WeightType> weightedMap = new HashMap<>();

    private Graph(boolean isBiDirectional) {
        this.isBiDirectional = isBiDirectional;
    }
    
    public static <VertextType extends Comparable<VertextType>, WeightType extends Comparable<WeightType>> Graph<VertextType, WeightType> biDirectional() {
        return new Graph<>(true);
    }
    
    public static <VertextType extends Comparable<VertextType>, WeightType extends Comparable<WeightType>> Graph<VertextType, WeightType> uniDirectional() {
        return new Graph<>(false);
    }
    
    public void add(VertexType from, VertexType to, WeightType w) {
        updateAdjacencyMap(from, to);
        weightedMap.put(new Pair<>(from, to), w);
        if (isBiDirectional) {
            updateAdjacencyMap(to, from);    
            weightedMap.put(new Pair<>(to, from), w);
        }
    }
    
    public WeightType getWeight(int from, int to) {
        return weightedMap.get(new Pair<>(from, to));
    }
    
    public Set<VertexType> toList(VertexType from) {
        Set<VertexType> result = adjacencyMap.get(from);
        if (result == null) {
            return Collections.emptySet();
        }
        return result;
    }
    
    private void updateAdjacencyMap(VertexType from, VertexType to) {
        Set<VertexType> adjacencyList = adjacencyMap.get(from);
        if (adjacencyList == null) {
            adjacencyList = new TreeSet<>();
            adjacencyMap.put(from, adjacencyList);
        }
        adjacencyList.add(to);
    }
    
    @Override
    public String toString() {
        return weightedMap.toString();
    }
}

public class Solution {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int N = scanner.nextInt();
            int M = scanner.nextInt();
            int K = scanner.nextInt();
            
            int T[] = new int[N];
            
            for (int i = 0; i < N; i++) {
                int fishCount = scanner.nextInt();
                for (int j = 0; j < fishCount; j++) {
                    int fishId = scanner.nextInt();
                    T[i] |= 1 << (fishId - 1);
                }
            }

            Graph<Integer, Integer> g = Graph.biDirectional();
            for (int i = 1; i <= M; i++) {
                int point1 = scanner.nextInt();
                int point2 = scanner.nextInt();
                int time = scanner.nextInt();
                g.add(point1 - 1, point2 - 1, time);
            }
            
            int F[][] = new int[N][1 << K];
            for (int i = 0; i < N; i++) {
                Arrays.fill(F[i], Integer.MAX_VALUE);
            }
            F[0][T[0]] = 0;
            Set<Triplet<Integer, Integer, Integer>> pendingList = new TreeSet<>();
            pendingList.add(new Triplet<>(0, 0, T[0]));
            
            while (!pendingList.isEmpty()) {
                Triplet<Integer, Integer, Integer> result = pendingList.iterator().next();
                pendingList.remove(result);
            
                int from = result.getSecond();
                int mask = result.getThird();
                for (int to : g.toList(from)) {
                    if (F[to][mask | T[to]] > F[from][mask] + g.getWeight(from, to)) {
                        pendingList.remove(new Triplet<>(F[to][mask | T[to]], to, mask | T[to]));
                        F[to][mask | T[to]] = Math.min(F[to][mask | T[to]], F[from][mask] + g.getWeight(from, to));
                        pendingList.add(new Triplet<>(F[to][mask | T[to]], to, mask | T[to]));
                    }
                }
            }
            
            int R[] = new int[1 << K];
            Arrays.fill(R, Integer.MAX_VALUE);
            
            int mask = (1 << K) - 1;
            int result = Integer.MAX_VALUE;
            for (int i = 0; i < 1 << K; i++) {
                for (int j = i; j < 1 << K; j++) {
                    if ((i | j) == mask) {
                        result = Math.min(result, Math.max(F[N - 1][i], F[N - 1][j]));
                    }
                }
            }
            System.out.println(result);
        }
    }
}