import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
        for(int i=0;i<t;i++){
            int N=in.nextInt();
            int M=in.nextInt();
            if(M==1)
                System.out.println(2);
            else if(N%2==0)
                System.out.println(2);
            else if(N%2==1)
                System.out.println(1);
        }
    }
}