import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        int t;
        Scanner sc = new Scanner(System.in);
        t=sc.nextInt();
        boolean arr[][] = {{false,false,true,true,false,false,true,true,false,false,true,true,false,false,true},
                          {false,false,true,true,false,false,true,true,false,false,true,true,false,false,true},
                          {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
                          {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
                          {false,false,true,true,false,false,true,true,false,false,true,true,false,false,true},
                          {false,false,true,true,false,false,true,true,false,false,true,true,false,false,true},
                          {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
                          {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
                          {false,false,true,true,false,false,true,true,false,false,true,true,false,false,true},
                          {false,false,true,true,false,false,true,true,false,false,true,true,false,false,true},
                          {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
                          {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
                          {false,false,true,true,false,false,true,true,false,false,true,true,false,false,true},
                          {false,false,true,true,false,false,true,true,false,false,true,true,false,false,true},
                          {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true}};
        while(t-->0){
            int x,y;
            x=sc.nextInt();
            y=sc.nextInt();
            if(arr[x-1][y-1]){
                System.out.println("First");
            }else{
                System.out.println("Second");
            }
        }
    }
}