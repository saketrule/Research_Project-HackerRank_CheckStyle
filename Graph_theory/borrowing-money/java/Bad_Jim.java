import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //try{in = new Scanner(new FileReader("test2.txt"));}catch(Exception e){}
        
        int numHouses = in.nextInt();
        int numRoads = in.nextInt();
        
        house houses[] = new house[numHouses];
        
        
        for(int i=0; i<numHouses; i++){ // input houses
            houses[i] = new house();
            houses[i].money = in.nextInt();
        }
        
        for(int i=0; i<numRoads; i++){ // input roads
            int A = in.nextInt() - 1;
            int B = in.nextInt() - 1;
            houses[A].neighbours.add(houses[B]);
            houses[B].neighbours.add(houses[A]);
        }
        
        
        int defaultprofit = 0;
        long multiplier = 1;
        for(house h : houses){
            if(h.neighbours.isEmpty()){
                defaultprofit += h.money;
                h.left = -2;
                if(0 == h.money){multiplier *= 2;}
            }
        }
        
        
        plunder(houses,0,0);
        
        System.out.print(bestprofit + defaultprofit);
        System.out.print(" ");
        System.out.println(bestprofitperms * multiplier);
        //System.out.println(combinations);
        
    }
    
    static int bestprofit=0;
    static long bestprofitperms=0;
    static long combinations=0;
    
    static void plunder(house[] houses, int depth, int profit){
        if(depth >= houses.length){
            if(bestprofit == profit){bestprofitperms++;}
            
            if(bestprofit < profit){
                bestprofit = profit;
                bestprofitperms = 1;
            }
            //combinations++;
            return;
        }
        
        house h = houses[depth];
        
        plunder(houses, depth+1, profit);
        
        if(-1 == h.left){
            for(house n : h.neighbours){
                if(-1 == n.left){n.left = depth;}
            }
            plunder(houses,depth+1,profit + h.money);
            for(house n : h.neighbours){
                if(depth == n.left){n.left = -1;}
            }
        }
    }
}




class house{
    int money;
    int left;
    ArrayList<house> neighbours;
    
    house(){
        left = -1;
        neighbours = new ArrayList();
    }
}