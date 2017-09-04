import java.util.*;

class Main {

 private static class CompareCities implements Comparator<Integer> {

  Integer[] citiesDist;

  public CompareCities(Integer[] citiesDist) {
   this.citiesDist = citiesDist;
  }

  @Override
  public int compare(Integer a, Integer b) {
   return citiesDist[a] - citiesDist[b];
  }
 }

 private static Integer[][] sort(Integer[][] allPairsPath, int N) {
  Integer[][] sorted = new Integer[N][N];
  for (int i = 0; i < N; i++)
   for (int j = 0; j < N; j++)
    sorted[i][j] = new Integer(j);

  for (int i = 0; i < N; i++) {
   CompareCities howToCompare = new CompareCities(allPairsPath[i]);
   Arrays.sort(sorted[i], howToCompare);
  }

  return sorted;
 }

 private static class Edge {
  int u, v, d;
 }

 private static Integer[][] buildAllPairs(Edge[] edges, int N) {
  Integer[][] allPairsPath = new Integer[N][N];

  for (int i = 0; i < N; i++) {
   for (int j = 0; j < N; j++) {
    if (i == j) allPairsPath[i][j] = 0;
    else allPairsPath[i][j] = -1;
   }
  }

  for (Edge e : edges) {
   allPairsPath[e.u][e.v] = e.d;
   allPairsPath[e.v][e.u] = e.d;
  }

  for (int middle = 0; middle < N; middle++) {
   for (int source = 0; source < N; source++) {
    for (int dest = 0; dest < N; dest++) {
     if (allPairsPath[source][middle] > 0 && allPairsPath[middle][dest] > 0) {
      if (allPairsPath[source][dest] < 0 || 
       allPairsPath[source][dest] > allPairsPath[source][middle] + allPairsPath[middle][dest]) {
       allPairsPath[source][dest] = allPairsPath[source][middle] + allPairsPath[middle][dest];
      }
     }
    }
   }
  }

  return allPairsPath;
 }

 private static Set<Integer> buildSet(Integer[] citiesToVisit, int source) {
  Set<Integer> set = new HashSet<>(citiesToVisit.length);
  for (Integer city : citiesToVisit) {
   if (city != source) {
    set.add(city);
   }
  }

  return set;
 }

 private static int minPath(Integer[] citiesToVisit, Edge[] edges, int N) {
  Integer[][] allPairsPath = buildAllPairs(edges, N); 
  Integer[][] sortedAllPairs = sort(allPairsPath, N);


  int shortestPath = Integer.MAX_VALUE;

  for (int city : citiesToVisit) {
   Set<Integer> remainingLetters = buildSet(citiesToVisit, city);
   
   int path = 0;
   int currentCity = city;

   while (!remainingLetters.isEmpty()) {
    int nextCity = 0;
    for (int i = 0; ; i++) {
     nextCity = sortedAllPairs[currentCity][i];
     if (remainingLetters.contains(sortedAllPairs[currentCity][i])) break;
    }

    path += allPairsPath[currentCity][nextCity];
    currentCity = nextCity;
    remainingLetters.remove(Integer.valueOf(currentCity));
   }

   shortestPath = Math.min(shortestPath, path);
  }

  return shortestPath;
 }

 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);

  int N = in.nextInt();
  int K = in.nextInt();

  Integer[] citiesToVisit = new Integer[K];
  for (int i = 0; i < K; i++) {
   citiesToVisit[i] = in.nextInt() - 1;
  }

  Edge[] edges = new Edge[N - 1];
  for (int i = 0; i < N - 1; i++) {
   Edge e = new Edge();
   e.u = in.nextInt() - 1;
   e.v = in.nextInt() - 1;
   e.d = in.nextInt();
   edges[i] = e;
  }

  int minPath = minPath(citiesToVisit, edges, N);
  System.out.println(minPath);
 }
 
}