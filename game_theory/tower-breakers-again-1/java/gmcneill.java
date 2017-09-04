import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); 
        int testCases = Integer.parseInt(scan.nextLine()); 
        int towers = 0;
        String[] temp;
        for (int i=0; i<testCases; i++){
            towers = Integer.parseInt(scan.nextLine()); 
            temp = scan.nextLine().split(" ");
            int[] heights = new int[temp.length];
            for (int j=0; j< temp.length; j++){
                heights[i] = Integer.parseInt(temp[i]);
            }
            
            if (towers%2==0 || heights[0]==1){
                System.out.println("1");
            }
            else{
                System.out.println("2");
            }
        }
    }
}