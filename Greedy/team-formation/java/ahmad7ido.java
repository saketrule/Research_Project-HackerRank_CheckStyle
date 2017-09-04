import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(bf.readLine());
        for(int k = 1 ; k <= t ; k++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int n = Integer.parseInt(st.nextToken()), res = Integer.MAX_VALUE;
            int[] ar = new int[n];
            for(int i = 0 ; i < n ; i++) ar[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(ar);
            HashMap<Integer, List<Integer>> h = new HashMap<Integer, List<Integer>>();
            for(int i = 0 ; i < n ; i++){
               if(!h.containsKey(ar[i] - 1)){
                   if(h.containsKey(ar[i])) h.get(ar[i]).add(1);
                   else{
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(1);
                        h.put(ar[i], list);
                   }
               }else{
                   List<Integer> list = h.get(ar[i] - 1);
                   Collections.sort(list);
                   int min = list.get(0);
                   list.remove(0);
                   if(list.size() == 0) h.remove(ar[i] - 1);
                   if(h.containsKey(ar[i])) h.get(ar[i]).add(min+1);
                   else{
                       List<Integer> nList = new ArrayList<Integer>();
                       nList.add(min+1);
                       h.put(ar[i], nList);
                   }
               }
            }
            for(int i : h.keySet()){
                List<Integer> list = h.get(i);
                for(int size : list) res = Math.min(size, res);
            }
            out.println(res == Integer.MAX_VALUE?0:res);
        }
        out.close();
    }
}