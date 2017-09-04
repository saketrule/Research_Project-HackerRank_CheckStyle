import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        for(int currQ = 0; currQ < q; currQ++){
            int n = scan.nextInt();
            int[] A = new int[n];
            for(int i = 0; i < n; i++){
                A[i] = scan.nextInt();
            }
            int result = attempt1(A);
            System.out.print(result + "\n");
        }
        
    }
    public static int attempt1(int[] A){
        //fill HashMap
        Map<Integer, Integer> freqOfNums = fillHashMap(A);
        
        //how many numbers occur 2 times?
        int occurTwice = calculateOccurTwice(freqOfNums);
        return occurTwice;
    }
    public static int calculateOccurTwice(Map<Integer, Integer> m){
        int count = 0;
        for(Map.Entry<Integer, Integer> entry : m.entrySet()){
            if(entry.getValue() == 2){ count++; }
        }
        return count;
    }
    
    public static Map<Integer, Integer> fillHashMap(int[] A){
        Map<Integer, Integer> thing = new HashMap<Integer, Integer>();
        for(int index = 0; index < A.length; index++){
            int currNum = A[index];
            if(thing.containsKey(currNum)){
                thing.put(currNum, (thing.get(currNum) + 1) );
            }
            else{
                thing.put(currNum, 1);
            }
        }
        return thing;
    }
}