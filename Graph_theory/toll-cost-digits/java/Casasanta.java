import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int e = in.nextInt();
        
        HashMap<Integer, HashSet<Road>> originRoad = new HashMap<Integer, HashSet<Road>>();
        
        for(int a0 = 0; a0 < e; a0++){
            int x = in.nextInt();
            int y = in.nextInt();
            int r = in.nextInt();
            
            Road roadX = new Road(x, y,r);
            Road roadY = new Road(y, x, 1000 - r);
            
            
            addRoad(x, roadX, originRoad);
            addRoad(y, roadY, originRoad);
        }
        
        HashMap<Integer, HashSet<Road>> costAndRoad = loadCombinations(originRoad);
            
        for(int i = 0; i < 10; i ++) {
            HashSet<Road> roads = costAndRoad.get(i);
            if(roads == null) {
                System.out.println("0");
            } else {
                System.out.println(roads.size());
            }
        }
        
    }
    
    private static HashMap<Integer, HashSet<Road>> loadCombinations(HashMap<Integer, HashSet<Road>> originRoad) {
        HashMap<Integer, HashSet<Road>> costAndRoad = new HashMap<Integer, HashSet<Road>>();
        
        Set<Integer> xs = originRoad.keySet();
        for(int x : xs) {
            HashSet<Road> roads = originRoad.get(x);
            for(Road road : roads) {
                int cost = road.cost;
                int mod = cost % 10;
                addRoad(mod, road, costAndRoad);   
                HashSet<Integer> origins = new HashSet<Integer>();
                origins.add(x);
                loadCombinationsX(x,cost,origins,road.y,originRoad,costAndRoad);
            }
        }
        
        
        return costAndRoad;
    }
    
    private static void loadCombinationsX(int initialOrigin,
                                          int currentCost,
                                          HashSet<Integer> origins, 
                                          int dest,
                                          HashMap<Integer, HashSet<Road>> originRoad,
                                          HashMap<Integer, HashSet<Road>> costAndRoad) {
        
       HashSet<Road> roads = originRoad.get(dest);
        origins.add(dest);
        for(Road road : roads) {
            if(origins.contains(road.y)) {
                continue;
            }
           
            int cost = road.cost + currentCost;
            int mod = cost % 10;
            
            Road newRoad = new Road(initialOrigin, road.y,cost);
            addRoad(mod, newRoad, costAndRoad); 
            loadCombinationsX(initialOrigin,cost, origins, road.y,originRoad,costAndRoad);
        }
    }
    
    private static void addRoad(int key, Road road, HashMap<Integer, HashSet<Road>> map) {
        HashSet<Road> roads = map.get(key);
        if(roads == null) {
            roads = new HashSet<Road>();
        }
        roads.add(road);
        
        map.put(key, roads);
    }
    
    
    private static class Road {
        int x;
        int y;
        int cost;
        
        public Road(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost  = cost;
        }
        
        
        @Override
        public int hashCode() {
            int hash = 7;
            hash = 73 * hash + this.x;
            hash = 73 * hash + this.y;
            hash = 73 * hash + this.cost;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Road other = (Road) obj;
            if (this.x != other.x) {
                return false;
            }
            if (this.y != other.y) {
                return false;
            }
            if (this.cost != other.cost) {
                return false;
            }
            return true;
        }
        
        
    }
}