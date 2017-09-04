import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int n = sc.nextInt();
        int faken = 1;
        int d = 1;
        while (faken < n) {
            faken <<= 1;
            d++;
        }
        sc.nextLine();
        String[] nums = sc.nextLine().split(" ");
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (int i = 0; i < 2*n-1; i++) {
            int j = Integer.parseInt(nums[i]);
            if (!hm.containsKey(j))
                hm.put(j, 0);
            hm.put(j, hm.get(j)+1);
        }
        ArrayList<TreeSet<Integer>> tms = new ArrayList<TreeSet<Integer>>();
        for (int i = 0; i <= d; i++)
            tms.add(new TreeSet<Integer>());
        for (int j : hm.keySet()) {
            if (hm.get(j) > d) {
                System.out.println("NO");
                return;
            }
            tms.get(hm.get(j)).add(j);
        }
        int arrind = 0;
        int[] tree = new int[2*n-1];
        int expectation = 1;
        for (int i = 0; i < d; i++) {
            if (tms.get(d-i).size()!=expectation) {
                System.out.println("NO");
                return;
            }
            for (int j = 0; j < expectation; j++) {
                Integer value;
                if (i==0) {
                    value = tms.get(d-i).first();
                } else {
                    value = tms.get(d-i).ceiling(tree[arrind-1]);
                }
                if (value == null) {
                    System.out.println("NO");
                    return;
                }
                tms.get(d-i).remove(value);
                int val = value;
                int temparrind = arrind;
                for (int k = 0; k < d-i; k++) {
                    tree[temparrind] = val;
                    temparrind = temparrind*2+1;
                }
                arrind += 2;
            }
            if (i > 0)
                expectation *= 2;
        }
        boolean valid = true;
        for (int i = 0; i < n-1; i++) {
            if (arrind<2*n-1||tree[i]!=tree[2*i+1]||tree[2*i+1]>=tree[2*i+2]) {
                valid = false;
                break;
            }
        }
        if (valid) {
            System.out.println("YES");
            StringBuilder ans = new StringBuilder();
            String space = "";
            for (int i = 0; i < 2*n-1; i++) {
                ans.append(space+tree[i]);
                space = " ";
            }
            System.out.println(ans);
        } else {
            System.out.println("NO");
        }
    }
}