import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();
        int[][] nn=new int[n][2];
        int[][] mm=new int[m][2];
        for(int i=0;i<n;i++)
        {
            nn[i][0]=in.nextInt();
            nn[i][1]=in.nextInt();
        }
        for(int i=0;i<m;i++)
        {
            mm[i][0]=in.nextInt();
            mm[i][1]=in.nextInt();
        }
        List<Integer>[] nnn=new List[n];
        List<Integer>[] mmm=new List[m];
        for(int i=0;i<n;i++)
            nnn[i]=new ArrayList();
        for(int j=0;j<m;j++)
            mmm[j]=new ArrayList();
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(nn[i][1]>=mm[j][1]&&mm[j][0]>nn[i][0])
                {
                    nnn[i].add(j);
                    mmm[j].add(i);
                }
            }
        }
        boolean check=true;
        int count=0,addN=0,addM=0;
        while(check)
        {
            check=false;
            for(int i=0;i<n;i++)
            {
                if(nnn[i].size()==1)
                {
                    count++;
                    check=true;
                    int num=nnn[i].get(0);
                    nnn[i].remove(0);
                    for(int j=0;j<mmm[num].size();j++)
                        nnn[mmm[num].get(j)].remove(new Integer(num));
                    mmm[num].clear();    
                }
            }
            for(int i=0;i<m;i++)
            {
                if(mmm[i].size()==1)
                {
                    count++;
                    check=true;
                    int num=mmm[i].get(0);
                    mmm[i].remove(0);
                    for(int j=0;j<nnn[num].size();j++)
                        mmm[nnn[num].get(j)].remove(new Integer(num));
                    nnn[num].clear();
                }
            }
        }
        for(int i=0;i<n;i++)
            if(nnn[i].size()>1)
                addN++;
        for(int j=0;j<m;j++)
            if(mmm[j].size()>1)
                addM++;
        count+=Math.min(addN,addM);
        System.out.println(count);
    }
}