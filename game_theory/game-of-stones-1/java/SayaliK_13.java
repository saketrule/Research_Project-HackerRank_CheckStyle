import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int no_of_testcases=Integer.parseInt(br.readLine());
        while(no_of_testcases-->0){
            int number=Integer.parseInt(br.readLine());
            if(number%7==0 || number%7==1){
                System.out.println("Second");
            }
            else{
                System.out.println("First");
            }
        }
    }
}