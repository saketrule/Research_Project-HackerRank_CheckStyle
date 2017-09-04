import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
     new Solution();
    }
 
    List<Road> roads = new ArrayList<Road>();
    
    int N = 0;
    int M = 0;
    
    public Solution(){
        
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt(); // Number of Cities
        M = sc.nextInt(); // Number of Roads
        
        for(int i = 0; i < M; i++){
            int A = sc.nextInt();
            int B = sc.nextInt();
            int C = (int) Math.pow(2, sc.nextInt());
            
            roads.add(new Road(A,B,C));
        }
        
        int sum = 0;
        
        for(int x = 1; x < N; x++){
            for(int y = x + 1; y <= N; y++){
                sum += d(x,y);
            }
        }
        
        System.out.println(Integer.toBinaryString(sum));
    }
    
    public int d(int start, int end){
     List<Road> rest = new ArrayList<Road>(roads);
     return d(start, end, rest);
    }
    
    public int d(int start, int end, List<Road> rest){
     List<Integer> ways = new ArrayList<Integer>();
     
     for(int i = 0; i < rest.size(); i++){
      Road road = rest.get(i);
      
      if(road.start == start){
       if(road.end == end){
        ways.add(road.cost);
       } else {
        List<Road> r = new ArrayList<Road>(rest);
        r.remove(i);
        int w = d(road.end, end, r);
        if(w != 0)
         ways.add(road.cost + w);
       }
      } else if(road.end == start){
       if(road.start == end){
        ways.add(road.cost);
       } else {
        List<Road> r = new ArrayList<Road>(rest);
        r.remove(i);
        int w = d(road.start, end, r);
        if(w != 0)
         ways.add(road.cost + w);
       }
      }
     }
     
  int value = 0;
  
  for(int i = 0; i < ways.size(); i++){
   if(value == 0 || value > ways.get(i)){
    value = ways.get(i);
   }
  }
  
  return value;     
    }
    
    class Road{
     
     public int start;
     public int end;
     public int cost;
     
     public Road(int start, int end, int cost){
      this.start = start;
      this.end = end;
      this.cost = cost;
     }
     
    }
}