import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int operations = Integer.parseInt(in.readLine());
        for (int i = 0; i < operations; i++) {

            String[] tokens = in.readLine().split(" ");
            int members = Integer.parseInt(tokens[0]);
            if (members <= 1) {
                System.out.println(members);
                continue;
            }

            int smallestTeam = Integer.MAX_VALUE;

            List<Integer> all = new ArrayList<Integer>();
            for (int j = 1; j <= members; j++) {
                all.add(Integer.parseInt(tokens[j]));
            }

            Collections.sort(all, Collections.reverseOrder());

            List<List<Integer>> teams = new ArrayList<List<Integer>>();
            for (Integer val : all) {
                findTeam(teams, val);
                cleanup(teams, val);
            }

            for (List<Integer> team : teams) {
                if (team.size() < smallestTeam) {
                    smallestTeam = team.size();
                }
            }

            System.out.println(smallestTeam);
        }
    }

    public static void findTeam(List<List<Integer>> teams, Integer member) {
        List<Integer> team = findSmallestTeam(teams, member);
        if (team != null) {
            team.add(member);
            return;
        }

        List<Integer> newTeam = new ArrayList<Integer>();
        newTeam.add(member);
        teams.add(newTeam);
    }

    public static List<Integer> findSmallestTeam(List<List<Integer>> teams, Integer member) {
        Integer smallestTeamSize = null;
        List<Integer> smallestTeam = null;
        for (List<Integer> team : teams) {
            int size = team.size();
            Integer last = team.get(size - 1);
            if ((last - 1) == member) {
                if (smallestTeam == null || size < smallestTeamSize) {
                    smallestTeam = team;
                    smallestTeamSize = size;
                }
            }
        }

        return smallestTeam;
    }

    public static void cleanup(List<List<Integer>> teams, Integer member) {
        int previousLast = Integer.MAX_VALUE;
        int count = 0;
        Integer smallestTeam = Integer.MAX_VALUE;
        for (Iterator<List<Integer>> iter = teams.iterator(); iter.hasNext(); ) {
            List<Integer> team = iter.next();
            int size = team.size();
            int last = team.get(size - 1);
            if (last > (member + 1)) {
                if (size >= smallestTeam) {
                    iter.remove();
                } else {
                    smallestTeam = size;
                }

                previousLast = Integer.MAX_VALUE;
            }
            else {
                if (previousLast == last) {
                    if (count > 1000) {
                        iter.remove();
                    }

                    count++;
                }
                else {
                    previousLast = last;
                    count = 0;
                }
            }
        }
    }
}