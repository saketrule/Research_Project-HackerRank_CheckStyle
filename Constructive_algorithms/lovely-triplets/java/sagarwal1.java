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
        int nodes = 1;
        for(int i=0;i<P;i++){
            nodes+=Q;
        }
        System.out.println(nodes+" "+nodes);
        for(int i=1;i<nodes-1;i++){
           System.out.println(i+" "+ (i+1)); 
        }
        System.out.println((nodes-1)+" 1");
        System.out.println("1 "+nodes);
    }
}