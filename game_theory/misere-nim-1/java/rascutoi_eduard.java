import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s=new Scanner(System.in);
        int T=s.nextInt();
        int n;
        ArrayList<Integer> array;
        boolean allOne;
        for(int i=0;i<T;i++){
            allOne=true;
            array=new ArrayList<Integer>();
            n = s.nextInt();
            for(int j=0;j<n;j++){
                int el=s.nextInt();
                if(el!=1){
                    allOne=false;                 
                }
                array.add(el);
            }
            if(allOne && xor(array)==1){             
                System.out.println("Second");
            }else if(!allOne && xor(array)==0){
                System.out.println("Second");
            }else{
                System.out.println("First");
            }
        }
    }
    private static int xor(ArrayList<Integer> array){
        int xor;
        if(array.size()>1){
         xor=array.get(0)^array.get(1);
        }else{
            return 1;
        }
         for(int i=2;i<array.size();i++){
            xor^=array.get(i);
        }
        return xor;
    }
}