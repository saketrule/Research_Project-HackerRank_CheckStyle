import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static class Group {
        int peek;
        int size;
        
        public Group(int peek) {
            this.peek = peek;
            this.size = 1;
        }
        
        public int peek() {
            return this.peek;
        }
        
        public void push(int el) {
            this.peek = el;
            this.size++;
        }
        
        public int size() {
            return this.size;
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int tests = in.nextInt();
        
        for (int test = 0; test < tests; test++) {
            int n = in.nextInt();
            
            if (n == 0) System.out.println(0);
            else {
                int[] ar = new int[n];
            
                for (int i = 0; i < n; i++) ar[i] = in.nextInt();
                
                Arrays.sort(ar);
                //printArray(ar);
                System.out.println(smallestGroup(ar));
            }
        }
    }
    
    private static int smallestGroup(int[] ar) {
        int smallest = Integer.MAX_VALUE;
        Group[] ll = new Group[ar.length+1];
        int head = 0, tail = 0;
        
        for (int number : ar) {
            if (head == tail) {
                Group g = new Group(number);
                ll[tail++] = g;
            }
            else {
                while (tail > head && ll[head].peek()+1 < number) {
                    Group removing = ll[head++];
                    if (removing.size() < smallest) smallest = removing.size();
                }
                Group min = null;
                for (int i = head; i < tail; i++) {
                    if (ll[i].peek()+1 == number && (min == null || ll[i].size() < min.size())) min = ll[i];
                }
                
                if (min == null) {
                    Group g = new Group(number);
                    ll[tail++] = g;
                }
                else {
                    min.push(number);
                }
            }
        }
        
        while (tail > head) {
            Group removing = ll[head++];
            if (removing.size() < smallest) smallest = removing.size();
        }
        
        return smallest;
    }
    
    private static void printArray(int[] ar) {
        for (int number : ar) System.out.print(number + " ");
        System.out.println();
    }
}