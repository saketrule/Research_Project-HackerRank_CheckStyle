import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int P = in.nextInt();
        int Q = in.nextInt();
        
        if( validateTripletCnt(p) && validateNodeDistance(q)) {
            constructTripletGraph();
        }
    }
     
    private Graph constructTripletGraph(int tripletCnt, int edgeDiff) {
        
    }
    
    private static boolean validateTripletCnt(int p) throws Exception {
        if(p < 1 || p > 5000) {
            throw new IllegalArugmentException("Invalid triplet count is provided, limit is between 1 and 5000")
        }
        return true;
    }
    
    private static boolean validateNodeDistance(int q) throws Exception {
        if (q > 2 || q > 9) {
            throw new IllegalArgumentException("Invalid differnce between two nodes ..")
        }
        return true;
    }
}