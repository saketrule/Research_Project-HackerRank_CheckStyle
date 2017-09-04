import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        List<Integer> houses = new LinkedList<Integer>();
        Map<Integer, Integer> money = new HashMap<Integer, Integer>();
        Map<Integer, Set<Integer>> links = new HashMap<Integer, Set<Integer>>();
        for(int i = 1; i<=n; i++){
            int c = in.nextInt();
            houses.add(i);
            money.put(i, c);
        }
        for(int i = 0; i<m; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            if(links.containsKey(a)){
             links.get(a).add(b);
            } else {
             Set<Integer> nodes = new HashSet<Integer>();
             nodes.add(a);
             nodes.add(b);
             links.put(a, nodes);
            }
            if(links.containsKey(b)){
             links.get(b).add(a);
            } else {
             Set<Integer> nodes = new HashSet<Integer>();
             nodes.add(b);
             nodes.add(a);
             links.put(b, nodes);
            }
        }

        List<Integer> unlinkedHouses = new LinkedList<Integer>(houses);
        unlinkedHouses.removeAll(links.keySet());
        int numOfUnlinkedZero = 0;
        long totalUnlinkedMoney = 0L;
        for(int unlinkedHouse : unlinkedHouses){
         if(money.get(unlinkedHouse) == 0){
          numOfUnlinkedZero++;
         }
         totalUnlinkedMoney += money.get(unlinkedHouse);
        }
        long additionalWays = 1L;
        if(numOfUnlinkedZero > 0){
         additionalWays = (long)Math.pow(2, numOfUnlinkedZero);
        }
        Set<Set<Integer>> ways = getNodes(new LinkedList<Integer>(links.keySet()), links);
        Map<Long, Long> options = new HashMap<Long, Long>();
        long max = 0;
        for(Set<Integer> way : ways){
         long totalMoney = 0;
         //System.out.println(way);
         for(int node : way){
          totalMoney += money.get(node);
         }
         if(options.containsKey(totalMoney)){
          options.put(totalMoney, options.get(totalMoney)+1L);
         } else {
          options.put(totalMoney, 1L);
         }
         if(totalMoney > max){
          max = totalMoney;
         }
        }
        System.out.println((max + totalUnlinkedMoney) + " " + options.get(max) * additionalWays);
    }
    
    private static Set<Set<Integer>> getNodes(List<Integer> nodeList, Map<Integer, Set<Integer>> links){
     Set<Set<Integer>> ways = new HashSet<Set<Integer>>();
     ways.add(new HashSet<Integer>());
     for(int node : nodeList){
      Set<Integer> nodes = new HashSet<Integer>();
      nodes.add(node);//pick a node
      List<Integer> subList = new LinkedList<Integer>(nodeList);
      subList.removeAll(links.get(node));//remove all linked nodes to work on sublist
      if(subList.size() == 0){
       ways.add(nodes);
      } else {
       Set<Set<Integer>> subWays = getNodes(subList, links);
       for(Set<Integer> subNodes: subWays){
        Set<Integer> notes2 = new HashSet<Integer>(nodes);
        notes2.addAll(subNodes);
        ways.add(notes2);
       }
      }
        }
     return ways;
    }
}