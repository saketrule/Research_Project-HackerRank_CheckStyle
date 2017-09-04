import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    private static int[] singleFish;
    private static int allFishes;
    static void initFishes(int K){
        singleFish = new int[K+1];
        for(int i=1;i<=K;i++){
            singleFish[i] = 1 << (i-1);
            allFishes |= singleFish[i];
        }
    }
    private static class ShoppingCenter{
        int name;
        int fishes;
        List<ShoppingCenter> neighbors = new ArrayList<>(4);
        List<Integer> times = new ArrayList<>(4);
        ShoppingCenter(int name){
            this.name = name;
            
        }
        void addFish(int fishName){
            fishes |= singleFish[fishName];
        }
        void addNeighBor(ShoppingCenter neighbor, int travel){
            neighbors.add(neighbor);
            times.add(travel);
        }
        
        int getNbNeighbors(){
            return times.size();
        }
        ShoppingCenter getNeighbor(int index){
            return neighbors.get(index);
        }
        int getTravelTime(int index){
            return times.get(index);
        }
    }
    
    static ShoppingCenter[] shoppingCenters;
    static int N;
    private static class State implements Comparable<State>{
        int pos;// cat position
        int fishes;
        
        long totalTime;//totalTime so far.
        State(int pos, int fishes, long totalTime){
            this.pos = pos;
            this.fishes = fishes;
            this.totalTime = totalTime;
        }
        public int compareTo(State other){
            return Long.compare(this.totalTime, other.totalTime);
        }
        
    }
    
    static class VisitedState{
        long[][] time;
        VisitedState(){
            time = new long[N+1][];
            for(int i= 0;i<time.length;i++){
                time[i] = new long[allFishes + 1];Arrays.fill(time[i], -1);
            }
        }
        
        boolean hasMinTime(int node, int fishes){
            return time[node][fishes] != -1;
        }
        
        long getMinTimeAtEndFor(int fishes){
            return time[time.length -1][fishes];
        }
        
        void setTravelTime(int node, int fishes, long travelTime){
            int complementOfCurrent = ~fishes;
            long[] timeForFishes = time[node];
            for(int i=0;i<timeForFishes.length;i++){
                if((i & complementOfCurrent) == 0){//i describes a subset of current fishes set.
                    timeForFishes[i] = travelTime;//with some fishes and with totalTime to get those fishes.
                }  
            }
        }
    }
    
    static long min(){
        PriorityQueue<State> states = new PriorityQueue<>();
        //build already visited states
        states.add(new State(1,shoppingCenters[1].fishes,0));
        VisitedState travelTimes = new VisitedState();
        
        
        while(!states.isEmpty()){
            State current = states.poll();
            travelTimes.setTravelTime(current.pos,current.fishes,current.totalTime);
            
            if(current.pos == N){//cat is in final position
                //is there another cat that may have completed
                long mintravelTimeForCompletion = travelTimes.getMinTimeAtEndFor((~current.fishes) & allFishes);
                if(mintravelTimeForCompletion != -1){
                    return Math.max(mintravelTimeForCompletion, current.totalTime);
                }
            }
            ShoppingCenter sc = shoppingCenters[current.pos];
            int nbNeighbors = sc.getNbNeighbors();
            for(int i = 0;i< nbNeighbors;i++){
                ShoppingCenter nextPos = sc.getNeighbor(i);
                int travelTime = sc.getTravelTime(i);
                int nextFishSet = current.fishes | nextPos.fishes;
                if(!travelTimes.hasMinTime(nextPos.name,nextFishSet)){
                     states.offer(new State(nextPos.name,nextFishSet , current.totalTime + travelTime));  
                }              
            }
        }
        return -1;
    }
    

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");
        
        N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        initFishes(Integer.parseInt(line[2]));
        shoppingCenters = new ShoppingCenter[N +1];
        for(int a_i=0; a_i < N; a_i++){
            ShoppingCenter sc = new ShoppingCenter(a_i+1);
            line = in.readLine().split(" ");
            int nbFishes = Integer.parseInt(line[0]);
            for(int f = 0;f<nbFishes;f++){
                sc.addFish(Integer.parseInt(line[f+1]));
            }
            shoppingCenters[a_i +1] = sc;
        }
        for(int m = 0;m<M;m++){
            line = in.readLine().split(" ");
            int sc1 = Integer.parseInt(line[0]);
            int sc2 =Integer.parseInt(line[1]);
            int time = Integer.parseInt(line[2]);
            shoppingCenters[sc1].addNeighBor(shoppingCenters[sc2],time);
            shoppingCenters[sc2].addNeighBor(shoppingCenters[sc1],time);
        }
        System.out.println(min());
    }
}