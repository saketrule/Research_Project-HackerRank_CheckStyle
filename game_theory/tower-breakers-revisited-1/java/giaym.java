import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
//v m==1 > p2
//v m>1 and n== 1 > p1
//m==2 and n==2 1 1 p2
//m==2 and n==3 1 1 1 p1
//m==2 and n==4 1 1 1 1 p2 ...
//m==4 and n==2 2 2 p2
//m==4 and n==3 2 2 2 p1
//m==4 and n==4 2 2 2 2 p2
//n=3 1 1 1 p1
//n=3 1 1 2 p1
//n=3 1 2 2 p1
//n=3 2 2 2 p1
//n 1 2 3 > p2
//n 1 2 4 > p1
//n=4 1 0 2 5 > 1 2 5 > p1
//n 1 3 3 > p1
//n 1 3 4 > p1
//n 1 4 5 > p2
/*2 3 2>p1
242 >p1
456>*/

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for( int i = 0; i < t;  i++){
            int n = in.nextInt();
            int a[] = new int[n];
            int numberones = 0;
            for( int j = 0; j < n; j++ ){
                int x = in.nextInt();
                if( x == 1 ){
                    a[j] = Integer.MAX_VALUE;
                    numberones++;
                    continue;
                }
                int xf= 0;
                int b = 2;
                while( x%2 == 0){
                    xf++;
                    x/=2;
                }
                b=1;
                while( x>1){
                    b+=2;
                    if( x%b == 0){
                        xf++;
                        x/=b;
                        b-=2;
                    }
                }
                a[j] = xf;
            }
            
            n-= numberones;
            Arrays.sort(a);
         /*   for( int x : a ){
                System.out.print(x+" ");
            }
            System.out.println(" "+n);*/
            if( n < 1 ){
                System.out.println("2");
                continue;
            }
            if( n == 1 ){
                System.out.println("1");
                continue;
            }
            
            boolean p1winning = true;
            int last = a[0];
            int lasti = 0;
            for( int j = 1; j < n; j++ ){
                last ^= a[j];
                //System.out.print(last);
            }
            if( last ==0 ){
                System.out.println("2");
            }else{
                System.out.println("1");
            }
        }
    }
}