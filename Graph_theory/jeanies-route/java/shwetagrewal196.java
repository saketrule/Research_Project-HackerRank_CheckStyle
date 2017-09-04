import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc=new Scanner(System.in);
          String  line=sc.nextLine();
            for(int i=0;i<line.split(" ")[0];i++){
                String let=sc.nextLine();
                int sum=0;
                for(int j=0;j<line.split(" ")[1];j++)
                    sum+=let.split(" ");
                System.out.println(sum);
            }
            
    }
}