import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int testcases = scanner.nextInt();
        //System.out.println(testcases);
        for(int i=0; i<testcases; i++){
            int coin_x = scanner.nextInt(); 
            int coin_y = scanner.nextInt();
            findWinner(coin_x, coin_y);
        }
    }
    
    static void findWinner(int x, int y){
        x=x%4; 
        y=y%4;
        if((y==0)||(y==3)||(x==0)||(x==3)){
            System.out.println("First");
        }
        else
            System.out.println("Second");  
    }
}