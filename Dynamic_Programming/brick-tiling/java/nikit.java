import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
        String tmp="";
        if(t>0 && t<51)
        for(int i=0;i<t;i++)
        {
            int n=in.nextInt();
            int m=in.nextInt();
            int a=0,b=0;
            char d[][]=new char[n][m];
            if(n>0 && m>0 && n<21 && m<9)
            {
                for(int j=0;j<n;j++)
                {
                   tmp=in.next();
                   d[j]=tmp.toCharArray();
                    //System.out.println();
                    for(int k=0;k<m;k++)
                    {
                        if(d[j][k]=='.' && k>0 && d[j][k-1]=='.')
                        {
                            a++;
                        }
                        if(d[j][k]=='.' && j>0 && d[j-1][k]=='.')
                        {
                            b++;
                        }
                    }
                }
                int mod=1000000007;
                if(a==0 && b==0)
                {
                    System.out.println("1");
                }
                else if(a==0 || b==0)
                {
                    System.out.println("0");
                }
                else
                {
                    System.out.println((gcd(a,b)%mod));
                }

            }

        }
    }
    public static int gcd(int m, int n) {

        if (m < n) {
            int t = m;
            m = n;
            n = t;
        }

        int r = m % n;

        if (r == 0) {
            return n;
        } else {
            return gcd(n, r);
        }

    }
}/* Enter your code here. Read input from STDIN. Print output to STDOUT */