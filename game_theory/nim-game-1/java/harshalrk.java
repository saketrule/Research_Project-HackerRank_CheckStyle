import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int noOfGames = reader.nextInt();
        
        reader.nextLine();
        
        for (int games = 0; games < noOfGames; games++){
        int noOfPiles = reader.nextInt();
        
        reader.nextLine();
        int result = 0;
        for (int i = 0; i < noOfPiles;i++){
            result ^= reader.nextInt();
        }
        System.out.println((result==0?"Second":"First"));
        }
        
        
    }
}