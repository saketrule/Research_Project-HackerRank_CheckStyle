import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int g = scanner.nextInt();
        int[] n = new int[g];
        int[][] s = new int[g][];
        for (int i = 0; i < g; i++) {
            n[i] = scanner.nextInt();
            s[i] = new int[n[i]];
            for (int j = 0; j < n[i]; j++) {
                s[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < g; i++) {
            printTheWinner(n[i], s[i]);
        }
    }

    public static void printTheWinner(int n, int[] s) {
        int x = 0;
        for (int i = 0; i < n; i++) {
            x = x ^ findGrundyNumber(s[i]);
        }
        if(x == 0){
            System.out.println(2);
        }else{
            System.out.println(1);
        }
    }

    private static int findGrundyNumber(int input){
        int count = 0, countOf2 = 0;
        while(input%2 ==0){
            input = input/2;
            count++;
            countOf2++;
        }
        for (int i=3;i<=Math.sqrt(input); i = i+2){
            while(input%i ==0){
                input = input/i;
                count++;
            }
        }
        if (input > 2){
            count++;
        }
        if(countOf2>0){
            count = count - countOf2 +1;
        }
        return count;
    }
}