import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Solution {

 public static void main(String[] args) {
  Scanner cin = new Scanner(System.in);
        
  int n = cin.nextInt();
        int a[] = new int[n];
        for (int i=0; i<n; i++) a[i] = cin.nextInt();
        int p = cin.nextInt(), q = cin.nextInt();
        
        Arrays.sort(a);
        
        List<Integer> cb = new ArrayList<Integer>();
        for (int i=0; i+1<n; i++) {
            if ((a[i] + a[i + 1]) % 2 == 0) cb.add((a[i] + a[i + 1]) / 2);
            else {
                cb.add((a[i] + a[i + 1]) / 2);
                cb.add((a[i] + a[i + 1]) / 2 + 1);
            }
        }
        cb.add(p);
        cb.add(q);
        Collections.sort(cb);
        
        long da = -1;
        long ans = 0;
        for (int i : cb) {
            if (i >= p && i <= q) {
             long min = Long.MAX_VALUE;
             for (int j : a) 
              min = Math.min(min, Math.abs(0L + i - j));
                
                if (min > da) {
                 da = min;
                 ans = i;
                }
            }
        }
        
        System.out.println(ans);
  
  cin.close();
 }
}