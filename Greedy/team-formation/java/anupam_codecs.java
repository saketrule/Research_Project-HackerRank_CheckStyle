import java.io.*;
import java.util.*;

class TeamFormation { 
 void solve() {
        int T = nextInt();
        while (T-- > 0) {
         int N = nextInt();
         int[] A = new int[N];
         for (int i = 0; i < N; i++) {
          A[i] = nextInt();
         }
         if (N == 0) {
          out.println(0);
          continue;
         }
         TreeMap<Integer, Integer> skills = new TreeMap<>();
         for (int skillLevel: A) {
          Integer value = skills.get(skillLevel);
          if (value == null) {
           value = 0;
          }
          skills.put(skillLevel, value + 1);
         }
         int teamSize, smallestTeamSize = Integer.MAX_VALUE;
         while (!skills.isEmpty()) {
          teamSize = 1;
          Map.Entry<Integer, Integer> lastPair = skills.firstEntry();
          if (lastPair.getValue() == 1) {
           skills.remove(lastPair.getKey());
          }
          else {
           skills.put(lastPair.getKey(), lastPair.getValue() - 1);
          }
          while (!skills.isEmpty()) {
           Map.Entry<Integer, Integer> skillPair = skills.higherEntry(lastPair.getKey());
           if (skillPair != null && skillPair.getKey() - lastPair.getKey() == 1 && skillPair.getValue() >= lastPair.getValue()) {
            teamSize++;
            if (skillPair.getValue() == 1) {
             skills.remove(skillPair.getKey());
            }
            else {
             skills.put(skillPair.getKey(), skillPair.getValue() - 1);
            }
            lastPair = skillPair;
           }
           else {
            break;
           }
          }
          smallestTeamSize = Math.min(teamSize, smallestTeamSize);
         }
         out.println(smallestTeamSize);
        }
    }
    
    BufferedInputStream in = new BufferedInputStream(System.in);
    PrintWriter out = new PrintWriter(System.out);
    int nextInt() {
        int no = 0;
        boolean minus = false;
        try {
            int a = in.read();
            while (a == 32 || a == 10)
                a = in.read();
            if (a == '-') {
                minus = true;
                a = in.read();
            }
            while ('0' <= a && a <= '9') {
                no = no * 10 + (a - '0');
                a = in.read();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return minus ? -no: no;
    }
    public static void main (String[] args) {
     TeamFormation Solve = new TeamFormation();
     Solve.solve();
     Solve.out.flush();
 }
}