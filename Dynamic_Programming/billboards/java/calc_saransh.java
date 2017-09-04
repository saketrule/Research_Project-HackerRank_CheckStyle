/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*//*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Saransh
 */
import java.io.*;
import java.util.*;
import java.math.*;
import java.lang.*;

public class Solution {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Parserdoubt pd = new Parserdoubt(System.in);
            int n = pd.nextInt();
            int k = pd.nextInt();
            long arr[] = new long[n];
            long sum=0;
            for (int i = 0; i < n; i++) {
                arr[i] = pd.nextInt();
                sum+=arr[i];
            }
            if(n==k)
            {
                System.out.println(sum);
                return;
            }
            SortedMap<Long, Integer> map = new TreeMap<Long, Integer>();
            Queue<Long> q = new LinkedList<Long>();
            long dp[]=new long[n];
            for (int i = 0; i <=k; i++)
            {
                q.add(arr[i]);
                if (map.containsKey(arr[i]))
                    map.put(arr[i], map.get(arr[i]) + 1);
                 else
                    map.put(arr[i], 1);
                dp[i]=arr[i];
                //System.out.println(i+" "+dp[i]);
            }
            for(int i=k+1;i<n;i++)
            {
                
                long min=map.firstKey();
                dp[i]=min+arr[i];
                if (map.containsKey(arr[i]+min))
                    map.put(arr[i]+min, map.get(arr[i]+min) + 1);
                 else
                    map.put(arr[i]+min, 1);
                q.add(arr[i]+min);
                long tmp=q.poll();
                if(map.get(tmp)>1)
                {
                    map.put(tmp, map.get(tmp)-1);
                }
                else
                {
                    map.remove(tmp);
                }
                //System.out.println(i+" "+dp[i]);
            }
            long min=Long.MAX_VALUE;
            for(int i=n-1;i>=(n-1-k);i--)
            {
                min=Math.min(min,dp[i]);
            }
            System.out.println(sum-min);
        } catch (Exception e) {
        }

    }
}

class Parserdoubt {

    final private int BUFFER_SIZE = 1 << 17;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public Parserdoubt(InputStream in) {
        din = new DataInputStream(in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public String nextString() throws Exception {
        StringBuffer sb = new StringBuffer("");
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        do {
            sb.append((char) c);
            c = read();
        } while (c > ' ');
        return sb.toString();
    }

    public char nextChar() throws Exception {
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        return (char) c;
    }

    public int nextInt() throws Exception {
        int ret = 0;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = c == '-';
        if (neg) {
            c = read();
        }
        do {
            ret = ret * 10 + c - '0';
            c = read();
        } while (c > ' ');
        if (neg) {
            return -ret;
        }
        return ret;
    }

    public long nextLong() throws Exception {
        long ret = 0;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = c == '-';
        if (neg) {
            c = read();
        }
        do {
            ret = ret * 10 + c - '0';
            c = read();
        } while (c > ' ');
        if (neg) {
            return -ret;
        }
        return ret;
    }

    private void fillBuffer() throws Exception {
        bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
        if (bytesRead == -1) {
            buffer[0] = -1;
        }
    }

    private byte read() throws Exception {
        if (bufferPointer == bytesRead) {
            fillBuffer();
        }
        return buffer[bufferPointer++];
    }
}