import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class TeamFormation {

    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        int T = myScan.nextInt();
        while(T--!=0) {
            int n = myScan.nextInt();
            if (n == 0) {
                System.out.println("0");
            } else {
                ArrayList<Integer> a = new ArrayList<Integer>();
                for (int i = 0; i < n; i++) {
                    a.add(myScan.nextInt());
                }
                Collections.sort(a);
                HashMap<Integer, PriorityQueue<Integer>> sizes = new HashMap<Integer, PriorityQueue<Integer>>();
                for (Integer j : a) {
                    int size = getSize(sizes, j - 1);
                    addSize(sizes, j, size + 1);
                }

                Integer result = null;
                for (PriorityQueue<Integer> q : sizes.values()) {
                    Integer s = q.peek();
                    if (s != null) {
                        if (result == null || result.compareTo(s) > 0) {
                            result = s;
                        }
                    }
                }
                System.out.println(result);
            }
        }
    }

    public static int getSize(HashMap<Integer, PriorityQueue<Integer>> q,int a){
        PriorityQueue<Integer> temp = q.get(a);
        if(temp==null || temp.peek()==null){
            return 0;
        }else{
            return temp.poll();
        }
    }

    public static void addSize(HashMap<Integer,PriorityQueue<Integer>> sizes, int key, int val ){
        PriorityQueue<Integer> queue = sizes.get(key);
        if(queue == null){
            queue = new PriorityQueue<Integer>();
            sizes.put(key,queue);
        }
        queue.add(val);
    }
}