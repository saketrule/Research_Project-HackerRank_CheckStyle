import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        boolean[] fw = new boolean[101];
        for(int i=2;i<=5;i++)fw[i]=true;
        for(int i=6;i<=100;i++)fw[i] = !(fw[i-2]&&fw[i-3]&&fw[i-5]);
        
        Scanner in = new Scanner(System.in);
        int ts = in.nextInt();
        for(int t=0;t<ts;t++){
            int n = in.nextInt();
            if(fw[n])System.out.println("First");
            else System.out.println("Second");
        }
    }
}