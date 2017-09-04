import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static class House {
        public int money;
        public int houseIndex;
        public int nHouse;
        public List<House> lHouse2;
        public List<Integer> lConnected;
        public List<Integer> lPossible;

        public House(int moneyInput, int houseIndexInput, int nHouseInput) {
            this.money = moneyInput;
            this.houseIndex = houseIndexInput;
            this.nHouse = nHouseInput;
            lConnected = new ArrayList<Integer>();
            lPossible = new ArrayList<Integer>();
        }

        public void buildPossibleList() {
            for (int i = houseIndex + 1; i <= nHouse; i++) {
                if (!lConnected.contains(i)) {
                    lPossible.add(i);
                }
            }
        } 

        public List <Integer> doIt(List<Integer> lNotAllowed) {
            List <Integer> result = new ArrayList <Integer>();
            result.add(0);
            result.add(0);
            int maxMoney = this.money;
            int numberTimes = 1;
            List<Integer> copyNotAllowed = copyList(lNotAllowed);
            mergeLists(this.lConnected, copyNotAllowed);
            List<Integer> copyPossible = copyList(this.lPossible);
            removeFromList(copyNotAllowed, copyPossible);
            
            if (copyPossible.size() == 0) {
                maxMoney = this.money;
                numberTimes = 1;
            } else {
                for (int i = 0; i < copyPossible.size(); i++) {
                    List<Integer> tempResult = lHouse2.get(copyPossible.get(i)).doIt(copyNotAllowed);
                    if (tempResult.get(0) + this.money > maxMoney) {
                        maxMoney = tempResult.get(0) + this.money;
                        numberTimes = tempResult.get(1);
                    } else if(tempResult.get(0) + this.money == maxMoney) {
                        numberTimes += tempResult.get(1);
                    }
                }
            }
            
            result.set(0, maxMoney);
            result.set(1, numberTimes);
            return result;
        }
    }

    public static void removeFromList(List<Integer> toRemove, List<Integer> total) {
        for (int i = 0; i < toRemove.size(); i++) {
            if (total.contains(toRemove.get(i))) {
                int index = total.indexOf(toRemove.get(i));
                total.remove(index);
            }
        }
    }

    public static List<Integer> copyList(List<Integer> toCopy) {
        List<Integer> copy = new ArrayList<Integer>();
        for (int i = 0; i < toCopy.size(); i++) {
            copy.add(toCopy.get(i));
        }
        return copy;
    }

    public static void mergeLists(List<Integer> toAdd, List<Integer> total) {
        for (int i = 0; i < toAdd.size(); i++) {
            if (!total.contains(toAdd.get(i))) {
                total.add(toAdd.get(i));
            }
        }
    }
    

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nHouse = in.nextInt();
        int nRoad = in.nextInt();

        List<House> lHouse = new ArrayList<House>();
        lHouse.add(new House(-1, -1, -1));
        for (int i = 0; i < nHouse; i++) {
            lHouse.add(new House(in.nextInt(), i + 1, nHouse));
        }

        for (int i = 0; i < nRoad; i++) {
            int house1 = in.nextInt();
            int house2 = in.nextInt();

            if (house1 != house2) {
                lHouse.get(Math.min(house1, house2)).lConnected.add(Math.max(house1, house2));
            }
        }

        for (int i = 1; i < lHouse.size(); i++) {
            lHouse.get(i).buildPossibleList();
            lHouse.get(i).lHouse2 = lHouse;
        }

        int maxMoney = -1;
        int numberTimes = -1;
        for (int i = 1; i < lHouse.size(); i++) {
            List<Integer> lNotAllowed = new ArrayList<Integer>();
            List<Integer> tempResult = lHouse.get(i).doIt(lNotAllowed);
            if (tempResult.get(0) > maxMoney) {
                maxMoney = tempResult.get(0);
                numberTimes = tempResult.get(1);
            } else if(tempResult.get(0) == maxMoney) {
                numberTimes += tempResult.get(1);
            }
        }

        System.out.println(maxMoney + " " + numberTimes);
    }
}