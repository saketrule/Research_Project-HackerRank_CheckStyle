import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static boolean[] available;
    public static int[] C;
    public static HashSet<String> roads;
    public static int N;
    public static int M;
    public static int[] possibleValues;
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        N = in.nextInt();
        M = in.nextInt();
        
        C = new int[N];
        available = new boolean[N];
        setAvailable(0);
        
        int maxPossible = 0;
        for (int i = 0; i < N; i++)
        {
            int a = in.nextInt();
            maxPossible += a;
            C[i] = a;
        }
        
        // counts the number of ways to create a certain total.
        possibleValues = new int[maxPossible + 1];
        
        roads = new HashSet<String>();
        
        //when ingesting roads, subtract one from the value to make it 0-based.
        for (int i = 0; i < M; i++) {
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            
            roads.add("" + a + " " + b);
            roads.add("" + b + " " + a);
        }
        ///.... I give up on being clever.
        // let's just brute force this.
        // this might be slow... since in the worst possible case, it will have to check 2^34 - 1 sets..
        
        // number of houses that can be in a set of looted houses
        recursiveCheck(0);
        
        int max = 0;
        int count = 0;
        
        for (int i = 0; i < possibleValues.length; i++) {
            if (possibleValues[i] > 0) {
                max = i;
                count = possibleValues[i];
            }
        }
        System.out.println("" + max + " " + count);
           
        
    }
    public static HashSet<Integer> visited = new HashSet<Integer>();
    
    public static void recursiveCheck(int start) {
        
        for (int i = start; i < N; i++) {
            if (okay(i)) { // if it isn't a neighbor of anything in the hashset "visited"...
                visited.add(i);
                available[i] = false;
                calculateSum(); //automatically +1s that sum in possibleValues.
                
                recursiveCheck(i+1);
                
                visited.remove(i);
                available[i] = true;
            }
        }
    }
    
    public static boolean okay(int i) {
        Iterator<Integer> iterator = visited.iterator();
        
        boolean isOk = true;
        
        while (iterator.hasNext()) {
            if (roads.contains("" + i + " " + iterator.next())) {
                isOk = false;
                break;
            }
        }
        return isOk;
    }
    
    public static void calculateSum() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (!available[i]) {
                sum += C[i];
            }
        }
        possibleValues[sum]++;
    }
    
    public static void setAvailable(int n) { // sets everything after a certain value to be available.
        for (int i = 0; i < N; i++) {
            if (i < n) {
                available[i] = false;
            }
            else {
                available[i] = true;
            }
        }
    }
}