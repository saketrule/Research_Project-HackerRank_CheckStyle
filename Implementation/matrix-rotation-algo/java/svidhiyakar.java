import java.util.Scanner;
public class Solution {
    static int[][] arr,b;
    static int m,n,len,count=0,no,x=0,y=1,k=0,z;
    static int[] c;
    static Scanner scan=new Scanner(System.in);
    public static void getmatrix()
    {
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                arr[i][j]=scan.nextInt();
            }
        }
    }
    public static void split()
    {
        while(len>0)
        {
        for(int j=x,i=x;i<=m-y&&len>0;i++)
        {
            b[k][c[k]]=arr[i][j];
            c[k]++;
            len--;
        }
        for(int i=m-y,j=y;j<=n-y&&len>0;j++)
        {
            b[k][c[k]]=arr[i][j];
            c[k]++;
            len--;
        }
        for(int j=n-y,i=m-y-1;i>=x&&len>0;i--)
        {
            b[k][c[k]]=arr[i][j];
            c[k]++;
            len--;
        }
        for(int i=x,j=n-y-1;j>=y&&len>0;j--)
        {
            b[k][c[k]]=arr[i][j];
            c[k]++;
            len--;
        }
        k++;
        x++;
        y++;
        }
        count=k;
    } 
    public static void rotate(int i)
    {
            int f=b[i][c[i]-1];
            for(int j=c[i]-2;j>=0;j--)
            {
                b[i][j+1]=b[i][j];
            }
            b[i][0]=f;
    }
    public static void fun(int i,int j)
    {
        arr[i][j]=b[k][z];
        z++;
        len--;
    }
    public static void set()
    {
        x=0;
        y=1;
        k=0;
        while(len>0)
        {
        z=0;
        for(int j=x,i=x;i<=m-y&&len>0;i++)
        {
            arr[i][j]=b[k][z];
            z++;
            len--;
        }
        for(int i=m-y,j=y;j<=n-y&&len>0;j++)
        {
            arr[i][j]=b[k][z];
            z++;
            len--;
        }
        for(int j=n-y,i=m-y-1;i>=x&&len>0;i--)
        {
            arr[i][j]=b[k][z];
            z++;
            len--;
        }
        for(int i=x,j=n-y-1;j>=y&&len>0;j--)
        {
            arr[i][j]=b[k][z];
            z++;
            len--;
        }
        k++;
        x++;
        y++;
        }
    }
    public static void main(String[] args) {
        m=scan.nextInt();
        n=scan.nextInt();
        no=scan.nextInt();
        arr=new int[m][n];
        c=new int[m+n];
        b=new int[m+n][2*(m+n)];
        len=m*n;
        getmatrix();
        split();
        for(int j=0;j<count;j++)
        {
            int o=no%c[j];
            for(int i=0;i<o;i++)
            {
                rotate(j);
            }
        }
        len=m*n;
        set();
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
}