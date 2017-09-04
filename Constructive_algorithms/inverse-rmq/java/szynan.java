import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    static Map<Integer,Integer> map;
    static int[] result;
    static int numOfLevels;
    static int currInd = 0;
    
    public static boolean searchMap(int s, int l) {
        int curr = numOfLevels - l;
        List<Integer> list = new ArrayList<>();
        for (int a : map.keySet()) {
            //System.out.println(a);
            if(map.get(a) == curr) {
                list.add(a);
            } else {
                break;
            }
        }
        if (list.size() == s) {
            //System.out.println(Arrays.toString(result));
            int tmp = currInd;
            for (int j=s; j>0; j--) {
                result[tmp] = result[currInd-j];
                tmp += 2;
            }
            currInd++;
            //System.out.println(Arrays.toString(li));
            Collections.sort(list);
            int length = list.size();
            /*for (int i=0; i<length; i++) {
                result[currInd] = list.get(i);
                map.remove(list.get(i));
                currInd += 2;
            }*/
            //Set<Integer> set = new HashSet<>();
            list = new LinkedList(list);
            for(int j=0; j<s; j++) {
                for (int i=0; i<length; i++) {
                    int val = list.get(i);
                    if (result[currInd-1] < val) {
                        result[currInd] = val;
                        map.remove(val);
                        list.remove(i);
                        break;
                    }
                }
                length -= 1;
                currInd += 2;
            }
            currInd--;
            return true;
        } else {
            //System.out.println(Arrays.toString(result));
            return false;
        }
    }
    
    public static void sortMap() {
        List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>>( map.entrySet() );
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>()
        {
            public int compare( Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );

        Map<Integer, Integer> result = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        map = result;
        //for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        //    System.out.println(entry.getKey() + ": " + entry.getValue());
        //}
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        numOfLevels = (int) (Math.round((Math.log(2*n) / Math.log(2))));
        //System.out.println(numOfLevels);
        int nodes = 2*n - 1;
        result = new int[nodes];
        map = new HashMap<>();
        //int min = Integer.MAX_VALUE;
        for (int i=0; i<nodes; i++) {
            int t = in.nextInt();
            //if (t < min) { min = t; }
            if(map.containsKey(t)) {
                map.put(t, map.get(t)+1);
            } else {
                map.put(t, 1);
            }
        }
        sortMap();
        int min = map.entrySet().iterator().next().getKey();
        if(map.get(min) != numOfLevels) {
            System.out.println("NO");
        } else {
            result[0] = min;
            map.remove(min);
            int searching = 1;
            int level = 1;
            boolean possible = true;
            currInd = 1;
            while (level < numOfLevels) {
                possible = searchMap(searching, level);
                if (!possible) {
                    break;
                }
                searching *= 2;
                level++;
            }
            if (!possible) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
                StringBuilder sb = new StringBuilder();
                for (int i=0; i<nodes; i++) {
                    sb.append(result[i] + " ");
                }
                System.out.print(sb.toString());
            }
        } 
    }
}