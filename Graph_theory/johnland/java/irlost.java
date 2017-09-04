import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public City[] cities;
    BigInteger distanceTotals = BigInteger.ZERO;

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        new Solution(new Scanner(System.in));
    }

    public Solution(Scanner in) {
        int cityCount = in.nextInt();
        int roadCount = in.nextInt();
        LinkedList<Road> roads = new LinkedList<Road>();

        cities = new City[cityCount];
        for (int i=0; i<roadCount; i++) {
            // city ids are 1 based
            City cityA = getCity(in.nextInt()-1);
            City cityB = getCity(in.nextInt()-1);
            roads.add(new Road(BigInteger.valueOf(2).pow(in.nextInt()), cityA, cityB));
        }

        // sort roads
        Collections.sort(roads, new Comparator<Road>() {
            @Override
            public int compare(Road o1, Road o2) {
                return o1.distance.compareTo(o2.distance);
            }
        });

        // just need to go to cityCount -1 since we would have already found
        // all connections to last city
        while (roads.size() > 0) {
            Road road = roads.pop();
            City cityA = road.cityA;
            City cityB = road.cityB;
            // add cityB's connections to cityA's connections
            for (City connectionB : cityB.connections) {
                for (City connectionA : cityA.connections) {
                    addPair(connectionA,
                            connectionB,
                            road.distance
                                    .add(cityA.distances[connectionA.id])
                                    .add(cityB.distances[connectionB.id]));
                }
            }
            // add cityA's connections to cityB's connections
            for (City connectionA : cityA.connections) {
                for (City connectionB : cityB.connections) {
                    addPair(connectionA,
                            connectionB,
                            road.distance
                                    .add(cityA.distances[connectionA.id])
                                    .add(cityB.distances[connectionB.id]));
                }
            }

            // add cityB's connections to cityA
            for (City connection : cityB.connections) {
                addPair(cityA, connection, road.distance.add(cityB.distances[connection.id]));
            }
            // add cityA's connections to cityB
            for (City connection : cityA.connections) {
                addPair(cityB, connection, road.distance.add(cityA.distances[connection.id]));
            }

            // add cityA and cityB
            addPair(cityA, cityB, road.distance);
        }

        System.out.println(distanceTotals.toString(2));
    }

    public void addPair(City cityA, City cityB, BigInteger distance) {
        if (cityA.id == cityB.id) {
            return;
        }
        boolean added = false;
        added |= cityA.addConnection(cityB, distance);
        added |= cityB.addConnection(cityA, distance);
        if (added) {
            distanceTotals = distanceTotals.add(distance);
        }
    }


    public City getCity(int id) {
        City city = cities[id];
        if (city == null) {
            city = new City(id);
            cities[id] = city;
        }
        return city;
    }

    public class City {
        int id;

        LinkedList<City> connections = new LinkedList<City>();
        BigInteger[] distances = new BigInteger[cities.length];

        public City(int id) {
            this.id = id;
        }

        public boolean addConnection(City city, BigInteger distance) {
            if (distances[city.id] == null) {
                distances[city.id] = distance;
                connections.push(city);
                return true;
            }
            return false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            City city = (City) o;

            return id == city.id;

        }

        @Override
        public int hashCode() {
            return id;
        }
    }

    public class Road {
        BigInteger distance;
        City cityA;
        City cityB;

        public Road(BigInteger distance, City cityA, City cityB){
            this.distance = distance;
            this.cityA = cityA;
            this.cityB = cityB;
        }
    }
}