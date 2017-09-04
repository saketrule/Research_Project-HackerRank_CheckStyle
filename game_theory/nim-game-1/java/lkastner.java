import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    private static boolean winner(int[] game){
        int result = 0;
        for(int i: game){
            result ^= i;
        }
        return result!=0;
    }

    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        for(int i=0; i<N; i++){
            int T = scan.nextInt();
            int[] game = new int[T];
            for(int j=0; j<T; j++){
                game[j] = scan.nextInt();
            }
            if(winner(game)){
                System.out.println("First");
            } else {
                System.out.println("Second");
            }
        }
        scan.close();
    }
}