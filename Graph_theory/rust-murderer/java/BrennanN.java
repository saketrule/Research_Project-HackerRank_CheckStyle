import java.util.*;

public class Solution {

 public static void main(String[] args) {
  Scanner input = new Scanner(System.in);
  
  int t, n, m, x, y, s;
  t = input.nextInt();
  String[] output = new String[t];
  for (int i = 0; i < t; i++) {
   
   StringBuilder sb = new StringBuilder();
   
   ArrayList<City> cities = new ArrayList<>();
   
   // Create n cities.
   n = input.nextInt();
   for (int j = 0; j < n; j++) {
    cities.add(new City(j+1));
   }
   // Create m roads.
   m = input.nextInt();
   for (int j = 0; j < m; j++) {
    x = input.nextInt();
    y = input.nextInt();
    // If a is adjacent to b, then by implication b is adjacent to a. 
    cities.get(x-1).addAdjacentCity(cities.get(y-1));
    cities.get(y-1).addAdjacentCity(cities.get(x-1));
   }
   
   // containsKey, get, put and remove operations run in O(log(n)) time. 
   TreeMap<Integer, City> citiesTree = new TreeMap<Integer, City>();
   for (City city : cities) {
    citiesTree.put(city.getCityNumber(), city);
   }
   
   Queue<City> cityQueue = new LinkedList<>();

   s = input.nextInt();
   City rustyCity = cities.get(s-1);
   rustyCity.setVisited(true);
   citiesTree.remove(rustyCity.getCityNumber());
   cityQueue.offer(rustyCity);
   
   // Only unvisited cities remain in the TreeMap.
   // Breadth first search.
   while (!cityQueue.isEmpty()) {
    City currentCity = cityQueue.poll();
    ArrayList<City> currentCityNeighbors = currentCity.getAdjacentCities();
    // Only cities that are not adjacent to the current city are connected by a village road.
    for (City city : currentCityNeighbors) {
     // Runs in O(log(n)) time.
     citiesTree.remove(city.getCityNumber());
    }
    for (City city : citiesTree.values()) {
     city.setDistance(currentCity.getDistance() + 1);
     city.setVisited(true);
     cityQueue.offer(city);
    }
    citiesTree.clear();
    // Only the cities that are in currentCityNeighbors can be unvisited.
    for (City city : currentCityNeighbors) {
     if (!city.isVisited())
      citiesTree.put(city.getCityNumber(), city);
    }
    // Once there are no cities in the TreeMap all cities have been visited.
    if (citiesTree.isEmpty())
     break;
   }
   
   // Remove the city where Rusty is currently.
   cities.remove(rustyCity);
   for (City city: cities) {
    sb.append(city.getDistance() + " ");
   }
   
   output[i] = sb.toString();
  
  }
  for (String line : output) {
   System.out.println(line);
  }
 }
}

class City {
 private ArrayList<City> adjacentCities;
 private int cityNumber;
 private int distance;
 private boolean visited;

 public City(int cityNumber) {
  adjacentCities = new ArrayList<>();
  this.cityNumber = cityNumber;
  this.distance = 0;
 }

 public ArrayList<City> getAdjacentCities() {
  return adjacentCities;
 }

 public void addAdjacentCity(City adjacentCity) {
  this.adjacentCities.add(adjacentCity);
 }

 public int getCityNumber() {
  return cityNumber;
 }

 public void setCityNumber(int cityNumber) {
  this.cityNumber = cityNumber;
 }

 public int getDistance() {
  return distance;
 }

 public void setDistance(int distance) {
  this.distance = distance;
 }

 public boolean isVisited() {
  return visited;
 }

 public void setVisited(boolean visited) {
  this.visited = visited;
 }
 

}