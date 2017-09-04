import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner steve = new Scanner(System.in);
        int T = steve.nextInt();
        
        for(int i =0; i < T; i++){
            int x = steve.nextInt() % 4;
            int y = steve.nextInt() % 4;
            
            if(x == 0 || x == 3 || y == 0 || y == 3){
                System.out.println("First");
            } 
            else
                System.out.println("Second");
            
                
            
            
}
    }
}