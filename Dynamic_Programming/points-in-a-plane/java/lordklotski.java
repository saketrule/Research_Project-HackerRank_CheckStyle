/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import java.io.*;
import java.math.*;

/************************
 *                      *
 *    Lord Klotski      *
 *                      *
 ***********************/
public class Solution
{
    static class ez
    {
        int cost,mask; long num ;
        public ez(int a, long b, int c) {cost = a ; num = b ;mask = c;}
    }
    static int[][] pts ;
    static ez[] DP ;
    static int N ;
    static int[] nextMasks ;
    static int n ;
    static int[] dist ;
    static long[] ways ;
    static int mask ;
    static long mod =1000000007;
    
    
    static long[] solve()
    {
        // BFS
        // START FROM 0
        // GET TO (1<<N) - 1
        dist = new int[1<<N] ; Arrays.fill(dist,9999) ;
        ways = new long[1<<N] ; Arrays.fill(ways,(long)0) ;
        dist[0] = 0 ; ways[0] = 1 ;
        Queue<ez> q = new LinkedList<ez>() ;
        ez cur = new ez(0,1,0) ;
        q.offer(cur) ;
        while(!q.isEmpty())
        {
            cur = q.poll() ;
            n = 0 ;
            mask = cur.mask ;
            fNM(cur.mask,0,-1,-1) ;
            for (int i = 0 ; i < n ; i ++)
            {
                if (dist[nextMasks[i]] > cur.cost + 1)
                {
                    ways[nextMasks[i]] += ways[cur.mask] ;
                    ways[nextMasks[i]] %= mod ;
                    dist[nextMasks[i]] = cur.cost + 1 ;
                    q.offer(new ez(cur.cost + 1 ,0,nextMasks[i] )) ; // LOL forget the counting part..
                }
                else if (dist[nextMasks[i]] == cur.cost + 1)
                {
                    ways[nextMasks[i]] += ways[cur.mask] ;
                    ways[nextMasks[i]] %= mod ;
                }
            }
        }
        
        long[] res = new long[2] ;
        res[0] = dist[(1<<N) - 1] ;
        res[1] = ways[(1<<N) - 1] % mod ;
        return res ;
    }
    
    static void fNM(int M, int K, int f, int g)
    {
        //System.out.println(M + " " + K + " " + (1<<N));
        if (K == N)
        {
            nextMasks[n++] = mask ;
            return ;
        }
        // try adding K
        if ((M & (1<<K)) == 0)
        {
            if (f == -1)
            {
                // success! LOL
                mask += (1<<K) ;
                fNM(M,K+1,K,-1) ;
                mask -= (1<<K) ;
            }
            else if (g == -1)
            {
                mask += (1<<K) ;
                fNM(M,K+1,f,K) ;
                mask -= (1<<K) ;
            }
            else
            {
                if (comp(f,g,K))
                {
                    mask += (1<<K) ;
                    fNM(M,K+1,f,g) ;
                    mask -= (1<<K) ;
                }
            }
        }
        fNM(M,K+1,f,g) ;
    }
    static boolean comp(int A, int B, int C)
    {
        // is the slope from A to B the same as the slope from A to C ?
        int dx1 = pts[B][0] - pts[A][0] ;
        int dx2 = pts[C][0] - pts[A][0] ;
        int dy1 = pts[B][1] - pts[A][1] ;
        int dy2 = pts[C][1] - pts[A][1] ;
        
        if (dx1 == 0 && dx2 == 0) return true ;
        if (dx1 == 0 || dx2 == 0) return false ;
        double eps = Math.pow(10,-10) ;
        
        double s1 = (double)dy1/(double)dx1 ;
        double s2 = (double)dy2/(double)dx2 ;
        
        
        if (Math.abs(s2-s1) < eps) return true ;
        return false ;
    }
    
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt() ;
        while (T --> 0)
        {
            N = input.nextInt() ;
            pts = new int[N][2] ; for (int i = 0 ; i < N ; i ++) {pts[i][0] = input.nextInt() ; pts[i][1] = input.nextInt() ;}
            DP = new ez[1<<N+5] ; ez EZ = new ez(-1,-1,-1) ; Arrays.fill(DP,EZ) ;
            
            nextMasks = new int[1<<N] ;
            
            long[] x = solve() ;
            System.out.println(x[0] + " " + x[1]);
        }
    }
}