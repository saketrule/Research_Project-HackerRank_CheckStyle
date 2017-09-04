import java.util.*;


public class SupermanDiwali {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
     Scanner s=new Scanner(System.in);
     int n=s.nextInt();
     int h=s.nextInt();
     int delta=s.nextInt();
     int c[][]=new int[n][h+1];
     int d[][]=new int[n][h+1];
     int k;
       for(int j=0;j<n;j++)
     {
        k=s.nextInt();
       for(int l=0;l<k;l++)
       {  
        c[j][s.nextInt()]++;
       }
     }
        for (int i = 1; i <= h; ++i) {
            for (int j = 0; j < n; ++j) {
                d[j][i] = d[j][i - 1] + c[j][i];
            }
            if (i >delta) {
                int max = 0;
                for (int j = 0; j < n; ++j) {
                    max = Math.max(max, d[j][i - delta]);
                }
                for (int j = 0; j < n; ++j) {
                    d[j][i] = Math.max(d[j][i], max + c[j][i]);
                }
            }
        }
        
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, d[i][h]);
        }
        System.out.println(ans);
        s.close();
    }
}