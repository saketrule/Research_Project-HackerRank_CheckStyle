import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static void addIntoPQ(PriorityQueue<Integer> heap, int strength) {
        heap.add(strength);
    }
    
    static int findWinner(PriorityQueue<Integer> x, PriorityQueue<Integer> y, int teamX, int teamY) {
        PriorityQueue<Integer> turn = x;
        PriorityQueue<Integer> other = y;
        while(x.size() != 0 && y.size() != 0) {
            int k = 0;
            int nominated = turn.peek();
            while(k++ != nominated) {
                other.poll();
            }
            if(turn == x) {
                turn = y;
                other = x;
            }
            else {
                turn = x;
                other = y;
            }
            
        }
        
        return x.size() == 0 ? teamY : teamX;
        
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int q = sc.nextInt();
        
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for(int i = 1;i <= k;i++) {
            PriorityQueue<Integer> heap = new PriorityQueue<>(4, Collections.reverseOrder());
            map.put(i, heap);
        }
        
        for(int i = 0;i < n;i++) {
            int strength = sc.nextInt();
            int team = sc.nextInt();
            map.get(team).add(strength);
        }
        
        for(int i = 0;i < q;i++) {
            int type = sc.nextInt();
            if(type == 1) {
                int strength = sc.nextInt();
                int team = sc.nextInt();
                addIntoPQ(map.get(team), strength);
            }
            else {
                int x = sc.nextInt();
                int y = sc.nextInt();
                PriorityQueue<Integer> xCopy = new PriorityQueue<>(map.get(x));
                PriorityQueue<Integer> yCopy = new PriorityQueue<>(map.get(y));
                System.out.println(findWinner(xCopy, yCopy, x, y));
            }
            
        }
        
    }
}