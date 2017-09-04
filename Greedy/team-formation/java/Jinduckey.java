import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        int T = Integer.parseInt(in.nextLine());
        for (int t = 0; t < T; t++) {
            String[] array = in.nextLine().split(" ");
            TreeMap<Integer, Integer> peopleTree = new TreeMap<Integer, Integer>();
            for (int i = 1; i < array.length; i++) {
                int x = Integer.parseInt(array[i]);
                if (peopleTree.containsKey(x))
                    peopleTree.put(x, peopleTree.get(x) + 1);
                else
                    peopleTree.put(x, 1);
            }
            List<Pair> people = new ArrayList<Pair>();
            for (Integer i : peopleTree.keySet()) {
                Pair p = new Pair(i, peopleTree.get(i));
                people.add(p);
            }
            people.add(new Pair(Integer.MAX_VALUE, 1));
            if (people.size() == 1)
                System.out.println("0");
            else
                System.out.println(maxMinTeamSize(people));
        }
    }

    public static int maxMinTeamSize(List<Pair> people) {
        // Pair.x = skill, Pair.y = # of people with that skill
        int min = Integer.MAX_VALUE;
        List<Integer> teamSizes = new ArrayList<Integer>(); // largest to smallest
        
        int currentSkill = people.get(0).x - 1;
        for (int i = 0; i < people.size(); i++) {
            Pair next = people.get(i);
            if (next.x > currentSkill + 1) {
                min = Math.min(min, teamSizes.get(teamSizes.size() - 1));
                teamSizes = new ArrayList<Integer>();
            }
            if (next.y < teamSizes.size()) {
                for (int j = 0; next.y < teamSizes.size(); j++) {
                    int newMin = teamSizes.remove(0);
                    min = Math.min(min, newMin);
                }
                for (int j = 0; j < teamSizes.size(); j++)
                    teamSizes.set(j, teamSizes.get(j) + 1);
            }
            else {
                for (int j = 0; j < teamSizes.size(); j++)
                    teamSizes.set(j, teamSizes.get(j) + 1);
                for (int j = 0; next.y > teamSizes.size(); j++) {
                    teamSizes.add(1);
                }
            }
            currentSkill = next.x;
        }
        return min;
    }
    
    public static class Pair {
        public int x;
        public int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}