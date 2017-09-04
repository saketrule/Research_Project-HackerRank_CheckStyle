import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        int t=0;
        int n=0;
        int c=0;
        int solution=0;
        
        Scanner scan = new Scanner(System.in); 
        t= scan.nextInt();
        for(int i =0; i<t;i++){
            solution=0;
            n=scan.nextInt();
            
            for(int j=0; j<n;j++){
                if(scan.nextInt()%2==1)
                    solution^=j;
            }
            if(solution==0){
                System.out.println("Second");
            } else{
                System.out.println("First");
             }
                
            }
            
        }
        
    }