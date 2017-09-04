import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static void increase_val(Map<Integer, Integer> m, int key) {
        int newVal = 1;
        if (m.containsKey(key)) {
            newVal = m.get(key) + 1;
        }
        m.put(key, newVal);
    }
    
    public static void decrease_val(Map<Integer, Integer> m, int key) {
        int val = m.get(key);
        if (val == 1) {
            m.remove(key);
        } else {
            m.put(key, val - 1);
        }
    }
    
    public static void team_formation(int n, int[] levels) {
        Map<Integer, Map<Integer, Integer>> m = new TreeMap<Integer, Map<Integer, Integer>>();
        
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> mPre = m.get(levels[i] - 1);
            Map<Integer, Integer> mCur = m.get(levels[i]);
            if (mCur == null) {
                mCur = new TreeMap<Integer, Integer>();
                m.put(levels[i], mCur);
            }
            
            if (mPre == null || mPre.size() == 0) {
                increase_val(mCur, 1);
            } else {
                Iterator it = mPre.entrySet().iterator();
                if (it.hasNext()) {
                    Map.Entry pairs = (Map.Entry)it.next();         
                    increase_val(mCur, ((int)pairs.getKey())+1);
                    decrease_val(mPre, (int)pairs.getKey());
                }
            }   
        }
        
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> mCur = m.get(levels[i]);
            Iterator it = mCur.entrySet().iterator();
            if (it.hasNext()) {
                Map.Entry pairs = (Map.Entry)it.next(); 
                if ((int)pairs.getKey() < result) {
                    result = (int)pairs.getKey();
                }
            }
        }
        if (result == Integer.MAX_VALUE) {
            result = 0;
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            int nTest = Integer.parseInt(in.readLine());
            for (int t = 0; t < nTest; t++) {
                String[] tmp = in.readLine().split(" ");
                int n = Integer.parseInt(tmp[0]);
                int[] levels = new int[n];
                for (int i = 1; i <= n; i++) {
                    levels[i-1] = Integer.parseInt(tmp[i]); 
                }
                Arrays.sort(levels);
                
                team_formation(n, levels);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}