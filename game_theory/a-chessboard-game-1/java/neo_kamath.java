import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    private static Boolean[][] chessTable = new Boolean[16][16];
    
    private static long[] xFractions  ={-2, -2, 1, -1};
    
    private static long[] yFractions = {1, -1, -2, -2};
    
    public static void main(String[] args) {
        int x, y ;
        
        chessTable[1][1] = chessTable[1][2] = chessTable[2][1] = chessTable[2][2] = false;
        
        Scanner sc=  new Scanner(System.in);
        
        int T = sc.nextInt();
        
        for(int i=0;i<T;i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            
            System.out.println(DynPro(x, y)?"First" : "Second");
        }
    }
    
    private static boolean DynPro(long x, long y) {
        if(chessTable[(int)x][(int)y]!=null) return chessTable[(int)x][(int)y];
        
        boolean res = false;
        
        for(int i=0; i<4; i++) {
            long xx = x+xFractions[i], yy = y+yFractions[i];
            if(isSafe(xx,yy)) res = res|(!DynPro(xx,yy));
        }
        chessTable[(int) x][(int) y] = res;
        return res;
    }
    
    static boolean isSafe(long u, long v) {
        return (u>0 &&v>0 && u<=15 && v<=15);
    }
}