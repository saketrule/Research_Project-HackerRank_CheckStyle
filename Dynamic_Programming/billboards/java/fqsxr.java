import java.util.Scanner;
import java.util.TreeMap;

public class Solution {

    public static void main(String args[]) {
        int n,k;

        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();
        
        long sum = 0;
        int a[] = new int[n];
        
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            sum += a[i];
        }

        if (k == n) {
         System.out.println(sum);
         return;
        }
         
        long f[] = new long[n];
        TreeMap<Long, Integer> q = new TreeMap<Long, Integer>();
        
        for (int i = 0; i < k+1; i++) {
         f[i] = a[i];
         add(q, f[i]);
        }
        
        for (int i = k+1; i < n; i++) {
         f[i] = q.firstKey() + a[i];
         remove(q, f[i-k-1]);
//         System.out.println(i + " " + f[i]);
         add(q, f[i]);
        }
        
        long x = q.firstKey();
        System.out.println(sum - x);
    }

    private static void add(TreeMap<Long, Integer> q, long x) {
     Integer a = q.get(x);
     if (a == null) a = 0;
     q.put(x, ++a);
    }
    
    private static void remove(TreeMap<Long, Integer> q, long x) {
     Integer a = q.get(x);
     if (a == null) return;
     a--;
     if (a == 0) q.remove(x);
     else q.put(x, a);
    }
}