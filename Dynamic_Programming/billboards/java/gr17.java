/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/


import java.util.*;

public class Solution {
    
    static final int MAX_N = 100001;
    static int n, k;
    static int a[] = new int[MAX_N];
    static long sum[] = new long[MAX_N + 10];
    static long s1[] = new long[MAX_N + 10];
    static TreeMap<Long, Integer> mm = new TreeMap<Long, Integer>(new Comparator<Long>() {
        
        @Override
        public int compare(Long t, Long t1) {
            return t1.compareTo(t);
        }
    });
    
    static Long getFirst() {
        return mm.firstKey();
    }
    
    static void add(Long key) {
        Integer val = mm.remove(key);
        int newVal = 1;
        if (val != null) {
            newVal += val.intValue();
        }
        mm.put(key, newVal);
    }
    
    static void remove(Long key) {
        Integer val = mm.remove(key);
        
        int newVal = 0;
        if (val != null) {
            newVal = val.intValue() - 1;
        }
        if (newVal > 0) {
            mm.put(key, newVal);
        }
    }
    
    static void read_input() {
        
        Scanner scanner = new Scanner(System.in);
        
        Arrays.fill(sum, 0);
        
        n = scanner.nextInt();
        k = scanner.nextInt();
        for (int i = 0; i < n; ++i) {
            a[i] = scanner.nextInt();
        }
        
        s1[n + 1] = 0;
        s1[n] = 0;
        s1[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            s1[i] = (long) a[i] + s1[i + 1];
        }
        
        scanner.close();
        
    }
    
    static void process() {
        
        
        
        
        long s = 0;
        for (int i = 0; i < k; ++i) {
            s += a[i];
            sum[i] = s;
            
            add(((i > 0) ? sum[i - 1] : 0l) + s1[i + 1]);
            
        }
        
        for (int i = k; i < n; ++i) {
            
            sum[i] = getFirst() - s1[i + 1];
            add(sum[i - 1] + s1[i + 1]);
            
            remove(((i - k > 0) ? sum[i
                    - k - 1] : 0l) + s1[i - k + 1]);
            
            
            
        }
        
        System.out.println(sum[n - 1]);
        
    }
    
    public static void main(String args[]) {
        read_input();
        process();
//        PriorityQueue<Integer> q = new PriorityQueue<Integer>(16, new Comparator<Integer>() {
//
//            @Override
//            public int compare(Integer t, Integer t1) {
//                return t1.compareTo(t);
//            }
//        });
//        q.add(1);
//        q.add(12);
//        System.out.println(q.size());
//        
//        System.out.println(q.poll());
//        System.out.println(q.size());

    }
}