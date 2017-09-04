import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        
        int[][] roads = new int[N+1][N+1];
        //initialize
        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= N; j++) {
                roads[i][j] = -1;
            }
        }
        // HashMap<Integer, HashMap<Integer, Integer>> roads = new HashMap<>();
        int start_city = -1;
        for(int m = 0; m < M; m++) {
            int city1 = scan.nextInt();
            int city2 = scan.nextInt();
            int power = scan.nextInt();
            if(power == 0) {
                start_city = city1;
            }
            roads[city1][city2] = power;
            roads[city2][city1] = power;
        }
        BigInteger[][] min_dist = new BigInteger[N+1][N+1];
        HashSet<Integer> seen = new HashSet<>();
        seen.add(start_city);
        while(seen.size() < N) {
            int min = M;
            int seen_city = -1;
            int dest_city = -1;
            for(int city : seen) {
                for(int i = 1; i <= N; i++) {
                    if(i != city && !seen.contains(i) && roads[city][i] != -1) {
                        if(min > roads[city][i]) {
                            min = roads[city][i];
                            seen_city = city;
                            dest_city = i;
                        }
                    }
                }
            }
            BigInteger dist = new BigInteger("2").pow(roads[seen_city][dest_city]);
            min_dist[seen_city][dest_city] = dist;
            min_dist[dest_city][seen_city] = dist;
            // update min_dist for other seen cities
            for(int city : seen) {
                if(city != seen_city) {
                    min_dist[city][dest_city] = dist.add(min_dist[city][seen_city]);
                    min_dist[dest_city][city] = dist.add(min_dist[city][seen_city]);
                }
            }
            seen.add(dest_city);
        }
        BigInteger sum = BigInteger.valueOf(0);
        for(int i = 1; i <=N; i++) {
            for(int j = i+1; j <=N; j++) {
                sum = sum.add(min_dist[i][j]);
            }
        }
        System.out.println(sum.toString(2));
    }
    /*static void printBinary(long number) {
        long remainder;
        if (number <= 1) {
            System.out.print(number);
            return;
        }
        remainder = number % 2; 
        printBinary(number >> 1);
        System.out.print(remainder);
    }*/
}

/*HashMap<Integer, Integer> network1;
            if(roads.containsKey(city1)) {
                network1 = roads.get(city1);
            } else {
                network1 = new HashMap<>();
                roads.put(city1, network1);
            }
            network1.put(city2, power);
            HashMap<Integer, Integer> network2;
            if(roads.containsKey(city1)) {
                network1 = roads.get(city1);
            } else {
                network1 = new HashMap<>();
                roads.put(city1, network1);
            }
            network1.put(city2, power);*/