import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        int numIndependentNodes = 0;
        Scanner in = new Scanner(System.in);
        int numOp = in.nextInt();
        for (int i=0; i<numOp; i++){
            in.nextLine();
            String operation = in.next();
            if (operation.equals("A")){
                int numInSet = in.nextInt();
                if (numInSet>1)
                    numIndependentNodes += numInSet;
            }
            else if (operation.equals("B")){
                int set1 = in.nextInt();
                int set2 = in.nextInt();
            }
            else {
                int set = in.nextInt();
            }
                
        }
        System.out.println(numIndependentNodes);
    }
}