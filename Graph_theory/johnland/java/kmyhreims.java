import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static City[] cities;
    static HashMap<City, HashMap<City, BigInteger>> shortestPath = new HashMap<>();
    static class Road {
        final City to;
        final long l;
        public Road (long l, City to) {
            this.l=l;
            this.to = to;
        }
    }

    static class City {
        int num;
        boolean visited;
        long shortestPath;
        List<Road> roads = new LinkedList<>();
        public City(int num) {
            this.num=num;
        }
    }

    public static void clean(){
        for (City city : cities) {
            city.shortestPath = Long.MAX_VALUE;
        }
    }

    public static void findPath(City city, long path) {
        if (path<city.shortestPath){
            city.shortestPath = path;
            city.visited=true;
            for (Road road : city.roads) {
                if (!road.to.visited) {
                    findPath(road.to, path+road.l);
                }
            }
            city.visited=false;
        }
    }

    public static void printStats(int from){
        for (int x=0;x<cities.length;x++) {
            System.out.println("d("+from+","+(x+1)+") = "+cities[x].shortestPath);
        }
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner input = new Scanner(System.in);
        int cityCount = input.nextInt();
        int roadCount = input.nextInt();
        cities = new City[cityCount];

        for (int x=0;x<cityCount;x++) {
            cities[x] = new City(x+1);
        }

        while (input.hasNextInt()) {
            City a = cities[input.nextInt()-1];
            City b = cities[input.nextInt()-1];
            long l = (long) Math.pow(2,input.nextInt());

            a.roads.add(new Road(l, b));
            b.roads.add(new Road(l , a));
        }
        long totalSum=0;
        for (int x=0;x<cities.length;x++) {
            clean();
            findPath(cities[x],0);
            for (int y=x+1;y<cities.length;y++) {
                totalSum += cities[y].shortestPath;
            }    
        }
        System.out.println(Long.toBinaryString(totalSum));
    }
}