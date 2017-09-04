import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    enum WINNER {P1, P2};
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int i = 0; i< tc; i++){
            int num = sc.nextInt();
            Solution sol = new Solution();
            sol.removeStone (num);
        }
    }
    
    public void removeStone(int num){
        WINNER winner; 
        if (num%7 == 0 || num%7 == 1){
            winner = WINNER.P2;
        } else{
            winner = WINNER.P1;
        }
        switch (winner){
            case P1:
                System.out.println ("First");
                break;
            case P2:
                System.out.println ("Second");
                break;
        }
    }
}