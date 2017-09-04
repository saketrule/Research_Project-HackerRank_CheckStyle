import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

     public static class RoadMapType {
  
  private HashMap<Integer,HashSet<Integer>> connections;
  
  private ArrayList<Integer> lootOptions;
  
  private HashMap<Integer,Integer> loots;
  
  private int nonzeroLonersLoot;
  
  private int zeroLonersNum;
  
  private int topLoot;
  
   private void findRoads(int lootSoFar,HashSet<Integer> visited,HashSet<Integer> notVisited) {
   
   if (notVisited.isEmpty()) {
    if (!lootOptions.isEmpty()) {if (lootOptions.get(0).intValue()<=lootSoFar) {lootOptions.add(0,lootSoFar);}}
    else {lootOptions.add(lootSoFar);}
   }   
   else {  for (int h: notVisited)
           {            
      HashSet<Integer> newVisited = new HashSet<Integer>(visited);
      newVisited.add(h);
      HashSet<Integer> newNotVisited = new HashSet<Integer>(notVisited);
      newNotVisited.remove(h);
      findRoads(lootSoFar,visited,newNotVisited);
      newNotVisited.removeAll(connections.get(h));       
      findRoads(lootSoFar+loots.get(h),newVisited,newNotVisited);
      break;
           } 
    }   
    }
   
   private void looting(){
    nonzeroLonersLoot = 0;
    zeroLonersNum = 0;
    HashSet<Integer> connectedHouses=new HashSet<Integer>(connections.keySet());
    HashSet<Integer> loners=new HashSet<Integer>(loots.keySet());
    loners.removeAll(connectedHouses);
    for (int h:loners){
     int loot =loots.get(h);
     if (loot>0){nonzeroLonersLoot+=loot;}
     else {zeroLonersNum++;}
    }
    if (!connectedHouses.isEmpty()) {findRoads(0,new HashSet<Integer>(),connectedHouses);}
    
   }
   
   private RoadMapType(int numHouses){   
   connections = new HashMap<Integer,HashSet<Integer>>();
   loots = new HashMap<Integer,Integer>();   
   lootOptions = new ArrayList<Integer>();   
   }
  
   private void addRoad(int a, int b){
    addDirectedRoad(a,b);addDirectedRoad(b,a);
   }
      
   private void addDirectedRoad(int honnan, int hova){
     HashSet<Integer> dummy = new HashSet<Integer>();;
     if (connections.containsKey(honnan)) {dummy.addAll(connections.get(honnan));}
    dummy.add(hova);
    connections.put(honnan,dummy);
   }         
 
  private long topLootAndRoutes(){
   long returnValue = 1;
   if (lootOptions.isEmpty()) {topLoot =nonzeroLonersLoot;} else {topLoot=lootOptions.get(0).intValue();}
   for (int i=1; i<lootOptions.size();i++){
    if (lootOptions.get(i).intValue()==topLoot) {returnValue++;} else {break;}    
   }
   if (!lootOptions.isEmpty()) {topLoot+=nonzeroLonersLoot;}
   return returnValue*(long)Math.pow(2,zeroLonersNum);
  }
   
   }
  

    public static void main(String[] args) {
    
       Scanner scan = new Scanner(System.in);
       
          int numHouses = scan.nextInt();
          int numRoads = scan.nextInt();
          scan.nextLine();
          
          RoadMapType roadMap = new RoadMapType(numHouses);
          
          for (int i = 1; i <= numHouses; i++){           
           roadMap.loots.put(i, scan.nextInt());           
          } 
                  
          for (int i = 0; i < numRoads; i++){
           scan.nextLine();
           int a = scan.nextInt();
           int b = scan.nextInt();
           roadMap.addRoad(a,b);
          } 
          scan.close();                    
          
          //System.out.println(roadMap.loots);
          roadMap.looting();     
          
          String topLootRoutes = Long.toString(roadMap.topLootAndRoutes());          
                    
          System.out.println(Integer.toString(roadMap.topLoot)+" "+topLootRoutes);                    
    }
}