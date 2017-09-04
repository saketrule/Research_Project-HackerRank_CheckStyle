import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    private static class City {
        int number;
        List<Road> roads = new ArrayList<>();
    }

    private static class Road {
        int distance;
        City city1;
        City city2;

        public City getNextCity(City currentCity) {
            if (currentCity == city1) {
                return city2;
            } else {
                return city1;
            }
        }
    }

    static Map<Integer, City> cityMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cityCount = scanner.nextInt();
        for (int i = 0; i < cityCount; i++) {
            City city = new City();
            city.number = i + 1;
            cityMap.put(i + 1, city);
        }

        int lettersCount = scanner.nextInt();
        List<City> letterDestinations = new ArrayList<>(3);
        for (int i = 0; i < lettersCount; i++) {
            letterDestinations.add(cityMap.get(scanner.nextInt()));
        }

        for (int i = 0; i < cityCount - 1; i++) {
            int city1 = scanner.nextInt();
            int city2 = scanner.nextInt();
            int distance = scanner.nextInt();
            Road road = new Road();
            road.city1 = cityMap.get(city1);
            road.city2 = cityMap.get(city2);
            road.distance = distance;
            road.city1.roads.add(road);
            road.city2.roads.add(road);
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < letterDestinations.size(); i++) {
            List<City> destClones = new ArrayList<>(letterDestinations.size());
            for (int j = 0; j < letterDestinations.size(); j++) {
                destClones.add(letterDestinations.get(j));
            }
            int val = findMinDistance(cityMap.get(letterDestinations.get(i).number), destClones, 0, new HashSet<City>());
            min = min > val ? val : min;
        }

        System.out.println(min);

    }

    private static int findMinDistance(City startCity, List<City> letterDestinations, int distance, Set<City> citiesCovered) {

        if (letterDestinations.remove(startCity)) {
            citiesCovered.clear();
        }

        if (letterDestinations.isEmpty()) {
            return distance;
        }

        citiesCovered.add(startCity);
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < startCity.roads.size(); i++) {
            City nextCity = startCity.roads.get(i).getNextCity(startCity);
            if (!citiesCovered.contains(nextCity)) {
                Set<City> dupCitiesCovered = new HashSet<>();
                for (City city : citiesCovered) {
                    dupCitiesCovered.add(city);
                }

                List<City> dupDest = new ArrayList<>();
                for (City city : letterDestinations) {
                    dupDest.add(city);
                }

                int dist = findMinDistance(nextCity, dupDest, distance + startCity.roads.get(i).distance, dupCitiesCovered);
                if (dist != 0 && minDistance > dist) {
                    minDistance = dist;
                }
            }
        }
        if (minDistance != Integer.MAX_VALUE) {
            return minDistance;
        } else {
            return 0;
        }
    }
}