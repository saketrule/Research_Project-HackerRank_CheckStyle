import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int numFighters = in.nextInt();
        int numTeams = in.nextInt();
        int numQueries = in.nextInt();
        ArrayList<ArrayList<Integer>> teams = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < numTeams; i++) {
            teams.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < numFighters; i++) {
            int strength = in.nextInt();
            int team = in.nextInt();
            teams.get(team).add(strength);
        }
        for (int i = 0; i < numQueries; i++) {
            int queryType = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            if (queryType == 1) {
                teams.get(y).add(x);
            } else {
                int deadX = 0;
                int deadY = 0;
                Collections.sort(teams.get(x));
                Collections.sort(teams.get(y));
                int turn = 0;
                while (deadX < teams.get(x).size() && deadY < teams.get(x).size()) {
                    if (turn == 0) {
                        deadY += teams.get(x).get(teams.get(x).size() - 1 - deadX);
                        turn = 1;
                    } else {
                        deadX += teams.get(y).get(teams.get(y).size() - 1 - deadY);
                        turn = 0;
                    }
                }
                if (deadX >= teams.get(x).size()) {
                    System.out.println(y);
                } else {
                    System.out.println(x);
                }
            }
        }
    }
}