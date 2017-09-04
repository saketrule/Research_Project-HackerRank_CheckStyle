//package me.devsaki.test;

import java.math.BigInteger;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<City> cities = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
            cities.add(new City(i));
        }
        BigInteger bi2 = new BigInteger("2");
        for(int i = 0; i < m; i++){
            int a = sc.nextInt()-1;
            int b = sc.nextInt()-1;
            BigInteger c = bi2.pow(sc.nextInt());

            cities.get(a).n.put(cities.get(b), c);
            cities.get(b).n.put(cities.get(a), c);
            cities.get(a).rn.put(cities.get(b), c);
            cities.get(b).rn.put(cities.get(a), c);
        }
        Set<City> alreadyCityChecked = new HashSet<>();

        for (int i = 0; i < cities.size()-1; i++) {
            City city = cities.get(i);
            dijikstra(city);
            alreadyCityChecked.add(city);
        }

        BigInteger sum = new BigInteger("0");
        for(int i=0; i<cities.size()-1; i++){
            for(int j=(i+1); j<cities.size(); j++){
                BigInteger d = cities.get(i).n.get(cities.get(j));
                //System.out.println("D(" + (i+1) + ", " + (j+1) + ") : " + d);
                sum = sum.add(d);
            }
        }
        System.out.println(sum.toString(2));
    }

    public static void dijikstra(City s){
        Set<City> checkedCities = new HashSet<>();
        checkedCities.add(s);
        City nextCity = getMinimalNotChecked(s, checkedCities);
        while (nextCity!=null){
            check(s, nextCity, checkedCities);
            nextCity = getMinimalNotChecked(s, checkedCities);
        }

    }

    public static void check(City s, City target, Set<City> checkedCities){
        BigInteger aux = s.n.get(target);
        checkedCities.add(target);
        for(Map.Entry<City, BigInteger> t : target.rn.entrySet()){
            if(t.getKey()==s){
                continue;
            }
            BigInteger d = t.getValue();
            BigInteger currentD = s.n.get(t.getKey());
            BigInteger newValue = d.add(aux);
            if(currentD==null || currentD.compareTo(newValue)==1){
                s.n.put(t.getKey(), newValue);
                t.getKey().n.put(s, newValue);
            }
        }
    }

    private static City getMinimalNotChecked(City c, Set<City> cities){
        BigInteger min = null;
        City result = null;
        for (Map.Entry<City, BigInteger> e: c.n.entrySet()) {
            if(!cities.contains(e.getKey())&&(min==null||e.getValue().compareTo(min)==-1)){
                min = e.getValue();
                result = e.getKey();
            }
        }
        return result;
    }

    static class City{

        Map<City, BigInteger> n;
        Map<City, BigInteger> rn;
        int i = 0;

        public City(int i){
            this.i = i;
            n = new HashMap<>();
            rn = new HashMap<>();
        }

        @Override
        public String toString() {
            return "City{" +
                    "i=" + i +
                    '}';
        }
    }
}