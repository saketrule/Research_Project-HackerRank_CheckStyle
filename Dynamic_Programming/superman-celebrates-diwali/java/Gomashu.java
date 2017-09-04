import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //get N, number of buildings
        int N=in.nextInt();
        //get H, height of buildings
        int H=in.nextInt();
        //get I, the number of levels supaman must jump down to switch
        int I=in.nextInt();
        //make buildings[N][H] all = 0
        int[][] buildings=new int[N+1][H+2];
        //put buildings info in buildings
        int u;
        //for 1 to N (for each building)
        for(int i=1;i<=N;i++){
            //read in u, the number of people
            u=in.nextInt();
            //for 1 to u
            for(int j=1;j<=u;j++){
                //put person on floor ui for each next number read in
                buildings[i][in.nextInt()]++;
            }
        }//make maxScoreForLevel[H+2]=0
        int[] maxScoreForLevel=new int [H+I+2];
        //make scores array,starting from y=H
        int[][] scores=new int[N+1][H+I+2];
        
        int bestTotal;
        int curScore;
        //each cell shows max persons savable after visiting that pos.
        for (int y=H;y>0;y--){
            for (int x=1;x<=N;x++){
                
                /*
                System.out.println("H:"+H);
                System.out.println("N:"+N);
                System.out.println("x:"+x);
                System.out.println("y:"+y);
                */
                
                bestTotal= Math.max(maxScoreForLevel[y+I],scores[x][y+1]);
                curScore=buildings[x][y]+bestTotal;
                scores[x][y]=curScore;
                if (curScore>maxScoreForLevel[y]){
                    maxScoreForLevel[y]=curScore;
                }
            }
        }
        System.out.println(maxScoreForLevel[1]);
    }
}