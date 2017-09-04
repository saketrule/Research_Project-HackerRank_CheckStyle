import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        int t;
        Scanner sc = new Scanner(System.in);
        t=sc.nextInt();
        while(t-->0){
            int n=sc.nextInt();
            boolean grt=false,less=true;
            int xor=0;
            for(int i=1; i<=n; i++){
                int x=sc.nextInt();
                xor^=x;
                if(x>1){
                    grt=true;
                    less=false;
                }
            }
            if((xor==1 && less)||(xor==0 && grt)){
                System.out.println("Second");
            }else{
                System.out.println("First");
            }
        }
    }
}