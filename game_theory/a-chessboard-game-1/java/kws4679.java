import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while(t > 0) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            if(!win(x,y)){
                System.out.println("First");
            } else {
                System.out.println("Second");
            }
            t--;
        }
    }
    public static boolean win(int x, int y){
        int dx[] = {-2, -2, 1, -1};
        int dy[] = {1, -1, -2, -2};
        for(int k = 0; k < 4; k++){
            if(x + dx[k] <= 0 || y + dy[k] <= 0 || x + dx[k] > 15 || y + dy[k] > 15) continue;
            if(win(x + dx[k], y + dy[k])){
                return false;
            }
        }
        return true;
    }
}