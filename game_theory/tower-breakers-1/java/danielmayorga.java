import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        int n,m;
        for(int t=0; t<test; t++){
            n=input.nextInt();
            m=input.nextInt();
            if(m==1 || n%2==0)
                System.out.println(2);
            else
                System.out.println(1);
        }
    }
}