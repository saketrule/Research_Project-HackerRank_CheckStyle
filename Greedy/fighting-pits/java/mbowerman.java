import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<Integer, List<Integer>> teams = new HashMap<>();
        int n = in.nextInt();
        int k = in.nextInt();
        int q = in.nextInt();
        for (int i = 0; i < n; i++) {
            int s = in.nextInt();
            int t = in.nextInt();
            List<Integer> team = teams.get(t);
            if (team == null) {
                team = new ArrayList<>();
                teams.put(t, team);
            }
            team.add(s);
        }
        for (List<Integer> team : teams.values()) {
            Collections.sort(team);
        }
        for (int i = 0; i < q; i++) {
            int queryType = in.nextInt();
            if (queryType == 1) {
                int p = in.nextInt();
                int x = in.nextInt();
                List<Integer> team = teams.get(x);
                if (team == null) {
                    team = new ArrayList<>();
                    teams.put(x, team);
                }
                team.add(p);
            }
            else {
                int x = in.nextInt();
                int y = in.nextInt();
                System.out.println(determineWinner(teams, x, y));
            }
        }
    }
    
    public static int determineWinner(Map<Integer, List<Integer>> teams, int x, int y) {
        List<Integer> teamX = teams.get(x);
        List<Integer> teamY = teams.get(y);
        
        boolean xTurn = true;
        
        int xIndex = teamX.size() - 1;
        int yIndex = teamY.size() - 1;
        
        while (true) {
            if (xTurn) {
                if (xIndex < 0) {
                    return y;
                }
                yIndex -= teamX.get(xIndex);
            }
            else {
                if (yIndex < 0) {
                    return x;
                }
                xIndex -= teamY.get(yIndex);
            }
            xTurn = !xTurn;
        }
    }
}