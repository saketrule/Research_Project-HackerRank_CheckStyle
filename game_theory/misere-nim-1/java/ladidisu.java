import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner console = new Scanner(System.in);
        int T = console.nextInt();
        for(int i=0; i<T; i++){
            int n = console.nextInt();
            int ans = 0;
            boolean ones = true;
            for(int j=0; j<n; j++){
                int curr = console.nextInt();
                if(curr!=1){
                    ones = false;
                }
                ans = ans^curr;
            }
            if((ans==0 && !ones)||(ans==1&&ones)){
                System.out.println("Second");
            }
            else{
                System.out.println("First");
            }
        }
           
    }
}