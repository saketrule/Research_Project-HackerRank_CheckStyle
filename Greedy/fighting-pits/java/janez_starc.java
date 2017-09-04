import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static boolean fight (ArrayList<Integer> fst, ArrayList<Integer> sec) {
        int fstIndex = fst.size() - 1;
        int secIndex = sec.size() - 1;
        
        boolean turn = true;
        while (true) {
            //if (fst.get(fstIndex) == 1 && sec.get(secIndex) == 1) {
              //  int dif = fstIndex - secIndex;
                //return dif > 0 || dif == 0 && turn;    
            //}
                
            if (turn) {
                if (fst.get(fstIndex) >= sec.get(secIndex))
                    return true;
                else {
                    int current = fstIndex == 0 ? fst.get(0) : fst.get(fstIndex) - fst.get(fstIndex - 1);
                    secIndex -= current;
                    if (secIndex < 0)
                        return true;
                }
                
            } else {
                if (sec.get(secIndex) >= fst.get(fstIndex))
                    return false;
                else {
                    int current = secIndex == 0 ? sec.get(0) : sec.get(secIndex) - sec.get(secIndex - 1);
                    fstIndex -= current;
                    if (fstIndex < 0)
                        return false;
                }
            }

            turn = !turn;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int q = sc.nextInt();
        
        ArrayList<ArrayList<Integer>> teams = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < k; i++) {
            teams.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < n; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            teams.get(t - 1).add(s);
        }
        
        for (int i = 0; i < k; i++) {
            Collections.sort(teams.get(i));
            int sum = 0;
            for (int j = 0; j < teams.get(i).size(); j++) {
                sum += teams.get(i).get(j);
                teams.get(i).set(j, sum);
            }
                
        }
        
        
        for (int i = 0; i < q; i++) {
            int type = sc.nextInt();
            if (type == 1) {
                int s = sc.nextInt();
                int t = sc.nextInt();
                ArrayList<Integer> list = teams.get(t - 1);
                int size = list.size();
                int cum = size == 0 ? s : s + list.get(size - 1); 
                teams.get(t - 1).add(cum);
            } else {
                int fst = sc.nextInt();
                int sec = sc.nextInt();
                boolean fstWin = fight(teams.get(fst - 1), teams.get(sec - 1));
                System.out.println(fstWin ? fst : sec);
            }
        }
            
    }
}