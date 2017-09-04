import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int q = in.nextInt();
        List<Integer>[] teams = new List[k];
        List<Integer>[] total = new List[k];
        for (int i = 0; i < k; ++i) {
            teams[i] = new ArrayList<Integer>();
            total[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; ++i) {
            int s = in.nextInt();
            int t = in.nextInt() - 1;
            teams[t].add(s);
        }
        for (int i = 0; i < k; ++i) {
            Collections.sort(teams[i]);
            List<Integer> tot = total[i];
            int sum = 0;
            for (int s : teams[i]) {
                sum += s;
                tot.add(sum);
            }
        }
        for (int i = 0; i < q; ++i) {
            int t = in.nextInt();
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            if (t == 1) {
                teams[b].add(a + 1);
                List<Integer> tot = total[b];
                if (tot.isEmpty()) {
                    tot.add(a + 1);
                } else {
                    tot.add(tot.get(tot.size() - 1) + a + 1);
                }
            } else {
                int aPos = teams[a].size() - 1;
                int bPos = teams[b].size() - 1;
                int step = 0;
                List<Integer> totA = total[a];
                List<Integer> totB = total[b];
                while (aPos > -1 && bPos > -1) {
                    if (step == 0) {
                        if (totA.get(aPos) >= totB.get(bPos)) {
                            System.out.println(a + 1);
                            step = -1;
                            break;
                        }
                        bPos -= teams[a].get(aPos);
                        step = 1;
                    } else {
                        if (totB.get(bPos) >= totA.get(aPos)) {
                            System.out.println(b + 1);
                            step = -1;
                            break;
                        }
                        aPos -= teams[b].get(bPos);
                        step = 0;
                    }
                }
                if (step != -1)
                    System.out.println(step == 0 ? b + 1 : a + 1);
            }
        }
    }
}