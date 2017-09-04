import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        boolean memo[]=new boolean[101];
        memo[2]=true; memo[5]=true; memo[4]=true; memo[3]=true;
        boolean first,second,third;
        for(int i=6; i<101;i++){
            memo[i]=!memo[i-2] || !memo[i-5] ||!memo[i-3];
        }
        int size=input.nextInt();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<size;i++){
            if (memo[input.nextInt()])
                sb.append("First\n");
            else
                sb.append("Second\n");
        }
        System.out.println(sb.toString());
    }
}