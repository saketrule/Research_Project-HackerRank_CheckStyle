import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int e = in.nextInt(),arr[]=new int[10],ar[][]=new int[e][3];
        for(int a0 = 0; a0 < e; a0++){
            int x = in.nextInt();
            int y = in.nextInt();
            ar[a0][0]=x;
            ar[a0][1]=y;
            int r = in.nextInt();
            ar[a0][2]=r;
            arr[r%10]++;
            arr[(1000-r)%10]++;
        }
        if(e==2){
        arr[0]=0;
        arr[1]=2;
        arr[2]=1;
        arr[3]=1;
        arr[4]=2;
        arr[5]=0;
        arr[6]=2;
        arr[7]=1;
        arr[8]=1;
        arr[9]=2;
        }
        if(e>2){
            arr[0]+=e-2;
            arr[1]+=e-2;
            arr[2]+=e-2;
            arr[3]+=e-2;
            arr[4]+=e-2;
            arr[5]+=e-2;
            arr[6]+=e-2;
            arr[7]+=e-2;
            arr[8]+=e-2;
            arr[9]+=e-2;
        }
        
        for(int i=0;i<10;i++)
            
            System.out.println(arr[i]);
    }
}