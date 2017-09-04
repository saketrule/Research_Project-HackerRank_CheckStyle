import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        int M=in.nextInt(),N=in.nextInt(),R=in.nextInt(), i,j,k,m,n,r;
        int[][] a=new int[M][N],b=new int[M][N], dir=new int[M][N];
        int min=Math.min(M, N), i_, j_;
        for(i=0; i<M; i++) 
            for(j=0; j<N; j++) a[i][j]=in.nextInt();
        for(i=0; i<min/2; i++) {
            int p=1;
            i_=i; j_=i;
            dir[i][i]=p;
            do {
                dir[i_][j_]=p;
                int[] old=new int[]{i_, j_};
                switch(p) {
                    case 1:
                        i_++; if(i_==M-i-1){p=2;}
                        break;
                    case 2:
                        j_++; if(j_==N-i-1){p=3;}
                        break;
                    case 3:
                        i_--; if(i_==i){p=4;}
                        break;
                    case 4:
                        j_--; if(j_==i){p=1;}
                        break;
                }
            } while(i!=i_ || i!=j_);
        }
        for(i=0; i<M; i++) {
            for(j=0; j<N; j++) {
                int v=a[i][j], len=0;
                i_=i;j_=j;
                if(i_>=M/2)i_=M-i_-1;
                if(j_>=N/2)j_=N-j_-1;
                i_=Math.min(i_,j_);
                len=2*(M-2*i_) + 2*(N-2*i_) - 4;
                r = R%len;
                i_=i;j_=j;
                for(; r>0; r--) {
                    switch(dir[i_][j_]) {
                        case 1: i_++; break;
                        case 2: j_++; break;
                        case 3: i_--; break;
                        case 4: j_--; break;
                    }
                }
                b[i_][j_]=v;
            }
        }

        for(i=0; i<M; i++) {
            for(j=0; j<N; j++) System.out.print(b[i][j] + " ");
            System.out.println();
        }
    }
}