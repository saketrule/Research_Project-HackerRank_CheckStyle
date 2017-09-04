import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int count(ArrayList<Road> travel, Road r){
        int c = 0;
        for(Road temp : travel)
            if(temp == r)
                c++;
        return c;
    }
    
    static ArrayList<Road> findNearCity(ArrayList<Road> roads, int city, ArrayList<Road> travel){
        ArrayList<Road> list = new ArrayList<>();
        for(Road r : roads){
            if(count(travel, r) >= 2)
                continue;
            
            if(r.s == city || r.e == city){
                list.add(r);
            }
        }
        
        return list;            
    }
    
    static ArrayList<Integer> clone(ArrayList<Integer> l){
        ArrayList<Integer> list = new ArrayList<>();
        for(Integer i : l)
            list.add(i);
        
        return list;        
    }
    
    static ArrayList<Road> cloneRoads(ArrayList<Road> l){
        ArrayList<Road> list = new ArrayList<>();
        for(Road i : l)
            list.add(i);
        
        return list;        
    }
    
    static int min(ArrayList<Integer> letters, ArrayList<Road> roads, int city, int length, ArrayList<Road> travel){
        //System.out.println("city: " + city + " length = " + length + " " + letters + " " + travel.size());
        
        letters.remove(Integer.valueOf(city));
        
        if(letters.isEmpty())
            return length;
        
        ArrayList<Road> near = findNearCity(roads, city, travel);        
        int min = Integer.MAX_VALUE;        
        
        for(Road r : near){            
            ArrayList<Road> temp = cloneRoads(travel);
            temp.add(r);
            int a = min(clone(letters), roads, (r.s == city ? r.e : r.s), length + r.l, temp);
            if(min > a){
                min = a;
            }
        }
        
        return min;
    }
    
    static int min(int[] letters, ArrayList<Road> roads){
        int min = Integer.MAX_VALUE;
        
        for(int i = 1; i <= roads.size() + 1; i++){
            ArrayList<Integer> list = new ArrayList<>();
            for(int j = 0; j < letters.length; j++)
                list.add(letters[j]);
            
            int a = min(list, roads, i, 0, new ArrayList<Road>());
            //System.out.println("start: " + i + " " + a);
            
            if(min > a){
                min = a;
            }
        }
        
        return min;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc=new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] l = new int[k];
        for(int i = 0; i < k; i++){
            l[i] = sc.nextInt();
        }
        
        ArrayList<Road> roads = new ArrayList<>();
        for(int i = 0; i < n - 1; i++)
            roads.add(new Road(sc.nextInt(), sc.nextInt(), sc.nextInt()));        
                
        System.out.println(min(l, roads));
    }
    
    public static class Road{
        public int s;
        public int e;
        public int l;
        
        public Road(int s, int e, int l){
            this.s = s;
            this.e = e;
            this.l = l;
        }
    }
}