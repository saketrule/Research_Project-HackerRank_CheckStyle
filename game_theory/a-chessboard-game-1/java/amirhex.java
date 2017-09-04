import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void findWinner(int[][] res, int x, int y){
        if(x <= 1 && y <= 1){
            res[x][y] = 2;
            return;
        }
        if(res[x][y] > 0)
            return;
        if(x > 1){
            if(y > 0){
                if(res[x - 2][y - 1] == 0){
                    findWinner(res, x - 2, y - 1);
                }
                if(res[x - 2][y - 1] == 2){
                    res[x][y] = 1;
                    return;
                }
            }
            if(y < res[x].length - 1){
                if(res[x - 2][y + 1] == 0){
                    findWinner(res, x - 2, y + 1);
                }
                if(res[x - 2][y + 1] == 2){
                    res[x][y] = 1;
                    return;
                }
            }
        }
        if(y > 1){
            if(x > 0){
                if(res[x - 1][y - 2] == 0){
                    findWinner(res, x - 1, y - 2);
                }
                if(res[x - 1][y - 2] == 2){
                    res[x][y] = 1;
                    return;
                }
            }
            if(x < res.length - 1){
                if(res[x + 1][y - 2] == 0){
                    findWinner(res, x + 1, y - 2);
                }
                if(res[x + 1][y - 2] == 2){
                    res[x][y] = 1;
                    return;
                }
            }
        }
        res[x][y] = 2;        
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int[][] res = new int[15][15];
        findWinner(res, 14, 14);
        /*
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
        */
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while(T > 0){
            int x = in.nextInt();
            int y = in.nextInt();
            if(res[x - 1][y - 1] == 0)
                findWinner(res, x - 1, y - 1);
            if(res[x - 1][y - 1] == 1)
                System.out.println("First");
            else
                System.out.println("Second");
            T--;
        }
    }
}