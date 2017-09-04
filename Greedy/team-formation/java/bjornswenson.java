import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int tests = stdin.nextInt();
        
        // For each test case
        for(int i = 0; i < tests; i++) {
            int n = stdin.nextInt();
            int skills[] = new int[n];
            
            // Read each contestant
            for(int j = 0; j < n; j++) {
                skills[j] = stdin.nextInt();
            }
            
            Arrays.sort(skills);
            
            if(skills.length <= 1) {
                System.out.println(skills.length);
            }
            else {
                HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<Integer, PriorityQueue<Integer>>();

                for(int j = 0; j < n; j++) {
                    int value;
                    
                    PriorityQueue<Integer> teams = map.get(skills[j] - 1);
                    if(teams == null) {
                        value = 1;
                    }
                    else {
                        value = teams.poll() + 1;
                        if(teams.size() == 0) {
                            map.remove(skills[j] - 1);
                        }
                    }
                    
                    PriorityQueue<Integer> newTeams = map.get(skills[j]);
                    if(newTeams == null) {
                        newTeams = new PriorityQueue<Integer>();
                        map.put(skills[j], newTeams);
                    }
                    newTeams.add(value);
                }

                int min = Integer.MAX_VALUE;
                for(int skill : map.keySet()) {
                    min = Math.min(min, map.get(skill).peek());
                }
                
                System.out.println(min);
            }
        }
    }
}