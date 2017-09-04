import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

public class Solution {

 public static void main(String[] args) {
  /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
  try {
   BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
   StringBuilder output = new StringBuilder();
   int t, n, m ,x, y, pos;
   t = Integer.parseInt(in.readLine()); //number of testcases
   for(int i =0; i<t; i++){
    String line[] = in.readLine().split(" ");
    n = Integer.parseInt(line[0]);
    //number of cities
    TreeMap<Integer, City> arrayCities = new TreeMap();
    for(int z=0; z<n;z++){
     arrayCities.put(z, new City(z));
     arrayCities.get(z).addCityNumber(z);
    }
    m = Integer.parseInt(line[1]); //number of roads
    for(int j=0; j<m; j++){
     String linetwo[] = in.readLine().split(" ");
     x = Integer.parseInt(linetwo[0]) -1;
     y = Integer.parseInt(linetwo[1]) -1;
     arrayCities.get(x).addCityRoad(arrayCities.get(y));
     arrayCities.get(y).addCityRoad(arrayCities.get(x));
    }
    pos = Integer.parseInt(in.readLine()) -1;
    City rust = arrayCities.get(pos);
    rust.setVisited(true);
    arrayCities.remove(pos);

    int sizeArray = arrayCities.size();

    //At this point: arrayCities contains all cities. arrayVillage contains all cities.
    //All cities connected via city road have the attributes as of cityRoad
    TreeMap<Integer, City> arrayVillage = new TreeMap();
    //arrayVillage.putAll(arrayCities);
    arrayVillage = (TreeMap<Integer, City>) arrayCities.clone();

    //result array:
    Hashtable<Integer, Integer> res = new Hashtable<Integer, Integer>(n-1);

    //iterate through the City Tree to set the distances further to their neighbor by 1
    Queue<City> myQ = new LinkedList<City>();
    myQ.offer(rust); //enter Rust's city in the queue
    while(!myQ.isEmpty() && !arrayVillage.isEmpty()){
     //go through all of Rust's neighbor via CityRoad to visit them and suppress them from arrayVillage
     //and enter them into a temporary TreeMap
     City Qtop = myQ.poll();
     TreeMap<Integer, City> unvisitedRoad = new TreeMap();
     for(City city: Qtop.getCityRoad()){
      if(!city.isVisited()){
       unvisitedRoad.put(city.getCityNumber(), city);
       arrayVillage.remove(city.getCityNumber());
      }
     }
     for(City city: arrayVillage.values()){
      city.setVisited(true);
      city.setDistRust(Qtop.getDistRust() + 1);
      res.put(city.getCityNumber(), city.getDistRust());
      myQ.offer(city);
     }
     arrayVillage = unvisitedRoad;
    }
    //input results in the output STRING
    for(int u = 0; u<=sizeArray; u++){
     if(u!=rust.getCityNumber()){
      output.append(res.get(u) + " ");
     }
    }
    output.append("\r\n");
   }
   //END FORLOOP

   in.close();
   //printer(output);
   System.out.println(output);
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }

 public static void printer(StringBuilder output){
  File fileName = new File("rustandmurderer.txt");
  try
  {
   fileName.createNewFile();
   FileWriter fileWrite = new FileWriter( fileName );
   BufferedWriter bufferedWriter = new BufferedWriter( fileWrite );
   bufferedWriter.write(output + "");
   bufferedWriter.close();
  } catch ( IOException e )
  {
   //catch exception
  }
 }
}

class City{
 private LinkedList<City> cityRoad;
 private LinkedList<City> villageRoad;
 private int cityNumber;
 private boolean visited;
 private int distRust;

 public City(int number){
  cityRoad = new LinkedList<>();
  villageRoad = new LinkedList<>();
  this.cityNumber = number;
  visited = false;
  distRust = 0;
 }

 public LinkedList<City> getCityRoad() {
  return cityRoad;
 }

 public void addCityRoad(City neighbor){
  cityRoad.add(neighbor);
 }

 public LinkedList<City> getVillageRoad() {
  return villageRoad;
 }

 public void addVillageRoad(City neighbor){
  villageRoad.addFirst(neighbor);
 }

 public boolean isVisited() {
  return visited;
 }

 public void setVisited(boolean visited) {
  this.visited = visited;
 }

 public int getCityNumber() {
  return cityNumber;
 }

 public void addCityNumber(int cityNumber) {
  this.cityNumber = cityNumber;
 }

 public int getDistRust() {
  return distRust;
 }

 public void setDistRust(int distRust) {
  this.distRust = distRust;
 }

}