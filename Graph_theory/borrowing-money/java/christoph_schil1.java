import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Solution {

 public static void main(String[] args) {
  // System.setOut(new PrintStream(new OutputStream() {
  // @Override
  // public void write(int b) throws IOException {
  // }
  // }));
  Scanner scan = new Scanner(System.in);
  try {
   int nrOfHouses = scan.nextInt();
   int nrOfRoads = scan.nextInt();
   checkLimitsInclusive(nrOfHouses, 1, 34);
   checkLimitsInclusive(nrOfRoads, 0, nrOfHouses * (nrOfHouses - 1) / 2);
   List<Integer> moneyPerHouse = new ArrayList<>();
   for (int i = 0; i < nrOfHouses; i++) {
    Integer money = scan.nextInt();
    checkLimitsInclusive(money, 0, 100);
    moneyPerHouse.add(money);
   }
   List<Set<Integer>> roadsPerHouse = new ArrayList<>();
   for (int i = 0; i < nrOfHouses; i++) {
    roadsPerHouse.add(null);
   }
   Set<Set<Integer>> pairs = new HashSet<>();
   for (int i = 0; i < nrOfRoads; i++) {
    Integer house1 = scan.nextInt();
    Integer house2 = scan.nextInt();
    checkLimitsInclusive(house1, 1, nrOfHouses);
    checkLimitsInclusive(house2, 1, nrOfHouses);
    Set<Integer> pair = new HashSet<>();
    pair.add(house1 - 1);
    pair.add(house2 - 1);
    if (pairs.contains(pair)) {
     throw new IllegalArgumentException();
    } else {
     pairs.add(pair);
    }
    getOrCreate(roadsPerHouse, house1 - 1).add(i);
    getOrCreate(roadsPerHouse, house2 - 1).add(i);
   }
   Map<Integer, Set<Set<Integer>>> solutions = new HashMap<>();
   findVisits(nrOfHouses, moneyPerHouse, roadsPerHouse, solutions, new HashSet<Integer>(),
     new HashSet<Integer>(), 0, 0, 0);
   Integer maxMoney = Collections.max(solutions.keySet());
   Integer numberOfSolutions = solutions.get(maxMoney).size();
   System.out.format("%d %d", maxMoney, numberOfSolutions);
  } catch (Exception e) {
   noResult();
  } finally {
   scan.close();
  }
 }

 public static void findVisits(int nrOfHouses, List<Integer> moneyPerHouse, List<Set<Integer>> roadsPerHouse,
   Map<Integer, Set<Set<Integer>>> solutions, Set<Integer> houses, Set<Integer> roads, int money,
   int startIndex, int visits) {
  for (int i = startIndex; i < nrOfHouses; i++) {
   Set<Integer> roadsForHouse = roadsPerHouse.get(i);
   boolean canVisit = false;
   if (roadsForHouse != null) {
    Set<Integer> intersection = new HashSet<>(roadsForHouse);
    intersection.retainAll(roads);
    if (intersection.isEmpty()) {
     canVisit = true;
    }
   } else {
    canVisit = true;
   }
   if (canVisit) {
    visitNext(nrOfHouses, moneyPerHouse, roadsPerHouse, solutions, houses, roads, money, startIndex, visits,
      i + 1);
    money += moneyPerHouse.get(i);
    visits++;
    houses.add(i);
    if (roadsForHouse != null) {
     roads.addAll(roadsForHouse);
    }
    // System.out.format("Visited house #%d, #visits %d, money %d,
    // houses visited %s, roads blocked %s%n", i,
    // visits, money, houses, roads);
    visitNext(nrOfHouses, moneyPerHouse, roadsPerHouse, solutions, houses, roads, money, startIndex, visits,
      i + 1);
   } else {
    // System.out.format("Cannot visit house #%d, connects to roads
    // %s, roads blocked %s%n", i, roadsForHouse,
    // roads);
   }
  }
  Set<Set<Integer>> solutionSet = solutions.get(money);
  if (solutionSet == null) {
   solutionSet = new HashSet<>();
   solutions.put(money, solutionSet);
  }
  if (!solutionSet.contains(houses)) {
   solutionSet.add(houses);
   // System.out.format("Found solution money %d, houses visited %s,
   // roads blocked %s%n", money, houses, roads);
   // System.out.format("Found solutions and occurrences %s%n",
   // solutions.toString());
  }
 }

 private static void visitNext(int nrOfHouses, List<Integer> moneyPerHouse, List<Set<Integer>> roadsPerHouse,
   Map<Integer, Set<Set<Integer>>> solutions, Set<Integer> houses, Set<Integer> roads, int money,
   int startIndex, int visits, int i) {
  if (i < nrOfHouses) {
   // System.out.format(
   // "Last visited house #%d, just visited house #%d, #visits %d,
   // money %d, houses visited %s, roads blocked %s%n",
   // startIndex, i + 1, visits, money, houses, roads);
   findVisits(nrOfHouses, moneyPerHouse, roadsPerHouse, solutions, new HashSet<Integer>(houses),
     new HashSet<Integer>(roads), money, i, visits);
  }
 }

 private static void checkLimitsInclusive(Integer number, int lowerLimit, int upperLimit) {
  if (number < lowerLimit || number > upperLimit) {
   throw new IllegalArgumentException();
  }
 }

 private static void noResult() {
  System.out.println("0 0");
 }

 private static Set<Integer> getOrCreate(List<Set<Integer>> list, int index) {
  Set<Integer> set = list.get(index);
  if (set == null) {
   set = new HashSet<>();
   list.set(index, set);
  }
  return set;
 }

}