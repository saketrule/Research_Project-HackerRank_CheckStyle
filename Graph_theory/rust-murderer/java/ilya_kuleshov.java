import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class hr_RustMurderer {

 class Graph {

  int size;
  List<Integer>[] cityRoads;

  int villages;

  Graph(int size) {
   cityRoads = new List[size];
   this.size = size;
  }

  void addCityRoad(int x, int y) {

   if (cityRoads[x] == null)
    cityRoads[x] = new LinkedList<Integer>();

   if (cityRoads[y] == null)
    cityRoads[y] = new LinkedList<Integer>();

   cityRoads[x].add(y);
   cityRoads[y].add(x);
  }

  List<Integer> getAdjacentCities(int x) {
   return cityRoads[x];
  }

  boolean isCityRoad(int x, int y) {
   return cityRoads[x] != null && cityRoads[x].contains(y);
  }

  int getVillagesCount() {
   int villages = 0;
   for (int i = 0; i < size(); i++)
    if (cityRoads[i] == null)
     return 1;
   return villages;
  }

  int size() {
   return size;
  }

 }

 class DistanceFinder {
  Graph g;
  int start;
  int distTo[];

  boolean villageFound = false;

  DistanceFinder(Graph g, int start) {
   this.g = g;
   this.start = start;

   if (g.getVillagesCount() > 0) {
    villageFound = true;
    distTo = new int[g.size()];
    Arrays.fill(distTo, 1);
    
    List<Integer> adj = g.getAdjacentCities(start);
    if (adj == null)
     return;

    for (int i : adj)
     distTo[i] = 2;

    return;
   }

   distTo = new int[g.size()];
   for (int i = 0; i < g.size(); i++)
    distTo[i] = Integer.MAX_VALUE;
   distTo[start] = 0;

   Queue<Integer> q = new LinkedList<>();
   q.add(start);

   while (!q.isEmpty()) {

    Integer v = q.remove();

    List<Integer> adjCities = g.getAdjacentCities(v);

    for (int i = 0; i < g.size; i++) {

     if (v == i || adjCities.contains(i))
      continue;

     if (distTo[i] > distTo[v] + 1) {
      distTo[i] = distTo[v] + 1;
      q.add(i);
     }
    }
   }
  }

  int getDistanceTo(int v) {
   return distTo[v];
  }

  int getStart() {
   return start;
  }
 }

 public void printSolutions() {
  try (Scanner scanner = new Scanner(System.in)) {
   int testsCount = scanner.nextInt();

   for (int test = 0; test < testsCount; test++) {
    Graph graph;
    int start;

    int cities = scanner.nextInt();
    graph = new Graph(cities);

    int edges = scanner.nextInt();
    for (int i = 0; i < edges; i++) {
     int x = scanner.nextInt() - 1;
     int y = scanner.nextInt() - 1;
     graph.addCityRoad(x, y);
    }

    start = scanner.nextInt() - 1;
    List<Integer> adj = graph.getAdjacentCities(start);
    if (adj == null) {
     for (int i = 0; i < graph.size() - 1; i++) 
      System.out.print("1 ");

    } else {
     DistanceFinder df = new DistanceFinder(graph, start);
     for (int i = 0; i < graph.size(); i++) {
      if (i == df.getStart())
       continue;

      System.out.print(df.getDistanceTo(i) + " ");
     }
    }
    System.out.println();
   }

  }

 }

 public static void main(String[] args) {
  hr_RustMurderer solution = new hr_RustMurderer();
  solution.printSolutions();
 }

}