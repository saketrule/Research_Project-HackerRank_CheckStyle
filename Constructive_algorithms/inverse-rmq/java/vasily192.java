import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = Integer.parseInt(input.nextLine());
        Integer[] arr = new Integer[2*n - 1];        
        int[] res = new int[2*n - 1];
        final HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
        for (int i = 0; i < 2*n - 1; i++) {
            int a = input.nextInt();
            arr[i] = a;
            if (m.containsKey(a)) {
                m.put(a, m.get(a) + 1);
            } else {
                m.put(a, 1);
            }
            res[i] = 0;
        }
        Arrays.sort(arr, new Comparator<Integer> () {
            public int compare( Integer o1,Integer o2 )
            {
                return (m.get(o2)).compareTo( m.get(o1) );
            }
        });
        
        int curr = arr[0];
        int pos = 0;
        res[0] = curr;
        int distinct = 0;
        for (int i = 1; i < 2*n - 1; i++) {
            if (arr[i] == curr) {
                pos = 2*pos + 1;
                if (pos > res.length - 1) {
                    System.out.println("NO");
                    return;
                }
                res[pos] = arr[i];
            } else {
                distinct++;
                curr = arr[i];
                pos = 2*distinct;
                if (pos >= res.length) {
                    System.out.println("NO");
                    return;
                }
                res[pos] = arr[i];
            }
        }
        String s = "";
        for (int i = 0; i < n - 2; i++) {
            if (res[i] > res[2*i + 1] || res[i] > res[2*i + 2]) {
                System.out.println("NO");
                return;
            }
            s += res[i]+ " ";
        }
        for (int i = n-2; i < 2*n - 1; i++) {
            s += res[i]+ " ";            
        }
        System.out.println("YES");
        System.out.println(s);
    }
}