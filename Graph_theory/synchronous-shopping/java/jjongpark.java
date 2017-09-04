import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {    
    static class State {
        final int city;
        final int fish;
        
        State(int city, int fish) {
            this.city = city;
            this.fish = fish;
        }
    }
    
    static class Road {
        final int city;
        final int distance;
        Road(int city, int distance) {
            this.city = city;
            this.distance = distance;
        }
    }
    
    static class RoadList {
        List<Road> roads = new ArrayList<>();
        void add(int city, int distance) {
            roads.add(new Road(city, distance));
        }
    }
    
    static int ALL_TYPES_MASK = 0;
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final int N = in.nextInt(); // num shops
        final int M = in.nextInt(); // num roads
        final int K = in.nextInt(); // num fish types
        ALL_TYPES_MASK = (0x1 << K) - 1;
        
        final RoadList[] neighbors = new RoadList[N];        
        final int[] fishes = new int[N];
            
        // add fishes
        for(int i=0; i<N; i++) {            
            final int T = in.nextInt();
            for(int t=0; t<T; t++) {
                fishes[i] |= (1 << (in.nextInt()-1));
            }
        }
        
        // add neighbors
        for(int i=0; i<M; i++) {
            int city1 = in.nextInt()-1;
            int city2 = in.nextInt()-1;
            int distance = in.nextInt();
            RoadList n1 = neighbors[city1];
            if (n1 == null) {
                n1 = new RoadList();
                neighbors[city1] = n1;
            }
            RoadList n2 = neighbors[city2];
            if (n2 == null) {
                n2 = new RoadList();
                neighbors[city2] = n2;
            }
            n1.add(city2, distance);
            n2.add(city1, distance);            
        }
        
        final int[][] dist = new int[N][1<<K];
        for(int i=0; i<N; i++) {
            for(int j=0; j<(1<<K); j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        
        PriorityQueue<State> queue = new PriorityQueue<State>(10000, new Comparator<State>() {
           public int compare(State s1, State s2) {
               return dist[s1.city][s1.fish] - dist[s2.city][s2.fish];
           } 
        });
        
        State initial = new State(0, fishes[0]);
        dist[0][fishes[0]] = 0;
        queue.add(initial);
        
        while(!queue.isEmpty()) {
            State s = queue.poll();
//            System.out.println(queue.size());
            int curCity = s.city;
            int curFish = s.fish;
            int curDistance = dist[curCity][curFish];
            for(int i=0; i<neighbors[curCity].roads.size(); i++) {
             Road r = neighbors[curCity].roads.get(i);
                int neighborCity = r.city;
                int distance = r.distance;
                int newDist = curDistance + distance;
                int newFish = curFish | fishes[neighborCity];
                if (newDist < dist[neighborCity][newFish]) {
                    State s2 = new State(neighborCity, newFish);
                    dist[neighborCity][newFish] = newDist;
                    //queue.remove(s2);
                    queue.add(s2);                    
                }
            }
        }
        
        int ret = Integer.MAX_VALUE;
        for(int i=0; i<(1<<K); i++) {
            for(int j=0; j<(1<<K); j++) {
                if ( (i|j) == ALL_TYPES_MASK) {
                    ret = Math.min(ret, Math.max(dist[N-1][i], dist[N-1][j]));
                }
            }
        }
        System.out.println(ret);
        in.close();
    }
}