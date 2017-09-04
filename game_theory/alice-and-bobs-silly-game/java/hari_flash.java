import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static Map<Integer,Boolean> map = new HashMap<Integer,Boolean>();
    public static boolean isPrime(int a) {
        if(map.get(a)!=null){
            return map.get(a);
        }
        int limit = (int) Math.sqrt(a);
        boolean flag =true;
        for(int i=2;i<=limit;i++){
            if(i!=a && a%i==0){
                flag =false;
                break;
            }
        }
        map.put(a,flag);
        return flag;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            // your code goes here
            
            
            int count = 0;
            for(int i =2;i<=n;i++){
                //System.out.println(i);
                if(isPrime(i)){
                   //System.out.println(i);
                   count ++; 
                }
            }
            if(count%2==0){
                System.out.println("Bob");
            }else{
                System.out.println("Alice");
            }
            
        }
    }
}