import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static ArrayList [] teams;
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int Q = sc.nextInt();
        
        teams = new ArrayList[K];
        for (int i=0;i<K;i++) teams[i]=new ArrayList();
        
        for (int n=0;n<N;n++) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            teams[t-1].add(s);
        }
        
        for (int q=0;q<Q;q++) {
            int p = sc.nextInt();
            
            if (p==1) {
                int s = sc.nextInt();
                int t = sc.nextInt();
                teams[t-1].add(s);
                
            } else { // p=2
                int t1 = sc.nextInt();
                int t2 = sc.nextInt();
                System.out.println(fight(t1,t2));
            }
        }
    }
    public static int fight(int t1, int t2) {
        
        int curr1, curr2;
        curr1=0;
        curr2=0;
        
        while (curr1<teams[t1-1].size() && curr2<teams[t2-1].size()) {
            curr2+=(int)teams[t1-1].get(curr1);
            if (curr2>teams[t2-1].size()) return t1;
            curr1+= (int)teams[t2-1].get(curr2);
            if (curr1>teams[t1-1].size()) return t2;
        }
        return t1;
    }
}