import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        boolean [][] firstWins = new boolean[15][15];
        for(int i=0;i<2*15-1;i++) {
            int x = i < 15?i:14;
            int y = i < 15?0:i-14;
            do {
                if (x-2>=0 && y+1<15 && !firstWins[x-2][y+1]) {
                    firstWins[x][y]=true;
                } else if (x-2>=0 && y-1>=0 && !firstWins[x-2][y-1]) {
                    firstWins[x][y]=true;
                } else if (x+1<15 && y-2>=0 && !firstWins[x+1][y-2]) {
                    firstWins[x][y]=true;
                } else if (x-1>=0 && y-2>=0 && !firstWins[x-1][y-2]) {
                    firstWins[x][y]=true;
                } else {
                    firstWins[x][y]=false;
                }
                x--;
                y++;
            } while (x>=0 && y<15);
        }
        
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i=0;i<t;i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            System.out.println(firstWins[x-1][y-1]?"First":"Second");
        }
    }
}