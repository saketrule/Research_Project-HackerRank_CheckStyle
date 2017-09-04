import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] roads = new int[m][3];
        Town[] towns = new Town[n];
        for (int i = 0; i < n; i++) {
            towns[i] = new Town();
        }
        for (int i = 0; i < m; i++) {
            int[] arr = new int[3];
            arr[0] = in.nextInt();
            arr[1] = in.nextInt();
            arr[2] = in.nextInt();
            roads[i] = arr;
            towns[arr[0] - 1].buildRoad(towns[arr[1] - 1],(int)Math.pow(2,arr[2]));
            towns[arr[1] - 1].buildRoad(towns[arr[0] - 1],(int)Math.pow(2,arr[2]));
        }
        for (int i = 0; i < n; i++) {
            expandAll(towns);
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Integer dist = towns[i].distTo(towns[j]);
                if (dist == null) {
                    expandAll(towns);
                    j--;
                } else {
                    //System.out.println("d(" + (i+1) + ", " + (j+1) + ") = " + dist);
                    sum += dist;
                }
            }
        }
        System.out.println(Integer.toBinaryString(sum));
    }
    public static void expandAll(Town[] towns) {
        for (int i = 0; i < towns.length; i++) {
            towns[i].expand();
        }
    }
}

class Town {
    
    private Map<Town, Integer> map;
    public Town() {
        map = new HashMap<Town, Integer>();
    }
    
    public void buildRoad(Town city, int dist) {
        if ((!map.containsKey(city) ||  dist < distTo(city) ) && city != this) {
            map.put(city,dist);
        }
    }
    
    public void expand() {
        Map<Town, Integer> newRoads = new HashMap<Town, Integer>();
        for (Town a: map.keySet()) {
            for (Town b: a.map.keySet()) {
                if (newRoads.containsKey(b)) {
                    if (distTo(a) + a.distTo(b) < newRoads.get(b)) {
                        newRoads.put(b,distTo(a) + a.distTo(b));
                    }
                } else {
                    newRoads.put(b,distTo(a) + a.distTo(b));
                }
            }
        }
        for (Town t: newRoads.keySet()) {
            buildRoad(t, newRoads.get(t));
        }
    }
    public void expandAll() {
        for (Town a: map.keySet()) {
            a.expand();
        }
    }
    public Integer distTo(Town t) {
        return map.get(t);
    }
}