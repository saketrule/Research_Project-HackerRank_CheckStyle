import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws IOException{
        Reader.init(System.in);
        int t = Reader.nextInt();
        while(t>0){
            int n=Reader.nextInt();
            int x=Reader.nextInt();
            while(n>1){
                x=x^Reader.nextInt();
                n--;
            }
            int y=x;
            while(x>1){
                x/=2;
                //System.out.println("x = "+x);
            }
            if(x==1)
                System.out.println("First ");
            else
                System.out.println("Second ");
            t--;
        }
    }
}
class Reader{
    static BufferedReader br;
    static StringTokenizer st;
    static void init(InputStream in){
        br = new BufferedReader(new InputStreamReader(in));
        st = new StringTokenizer("");
    }
    static String next()throws IOException{
        while(!st.hasMoreTokens()){
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }
    static int nextInt()throws IOException{
        return Integer.parseInt(next());
    }
    static long nextLong()throws IOException{
        return Long.parseLong(next());
    }
}