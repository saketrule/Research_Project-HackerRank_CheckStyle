#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>

#include <cstring>
using namespace std;

#define MOD 1000000007

int n, m, bugs, dots;
char dat[21][16];
char p[9], q[9];
int num[2][65536];

const int four[] = {1, 4, 16, 64, 256, 1024, 4096, 16384, 65536};
char sbits[65536][10];

int GS, lastp, newOE, oldOE;

int getNum (char * pch)
{
    int rv = 0, nb = 0;
    for (int i = 0; i < m; ++i, nb+=2) // 1 << nb
        rv += (pch[i] << nb);
    return rv;
}

void getBits()
{
    int st;
    for (int i = 0; i < 65536; ++i) {
        st = i;
        for (int j = 0; j < 8; ++j) {
            sbits[i][j] = st % 4;
            st = st >> 2;
        }
    }
}

void dfs(int row, int b, int podd)
{
    for (int k = b; k < m; ++k) {

       
        if (k == lastp && podd == m) {
            num[newOE][ getNum(q) ] += num[oldOE][GS];
            num[newOE][ getNum(q) ] %= MOD;
        }
       
        if (k+2 < m && q[k] == 0 && 0 == (q[k+1] & 1) && 0 == (q[k+2] & 1) ) {

            q[k] = 3; ++q[k+1]; ++q[k+2]; p[k] += 2; p[k+1] += 2; p[k+2] += 2;
            if(k+1 < m) dfs (row, k+1, podd);
            q[k] = 0; --q[k+1]; --q[k+2]; p[k] -= 2; p[k+1] -= 2; p[k+2] -= 2;
        }

       

        if (k+2 < m && 0 == (q[k] & 1) && 0 == (q[k+1] & 1) && q[k+2]==0 ) {
            ++q[k]; ++q[k+1]; q[k+2] = 3; p[k] += 2; p[k+1] += 2; p[k+2] += 2;
            if(k+2 < m) dfs (row, k+2, podd);
            --q[k]; --q[k+1]; q[k+2] = 0; p[k] -= 2; p[k+1] -= 2; p[k+2] -= 2;
        }
       
        if (k+2 < m && q[k] == 0 && q[k+1] < 2 && q[k+2] < 2 ) {
            p[k]+= 2; q[k] = 3; q[k+1] += 2; q[k+2] += 2;
            if(k+1 < m) dfs (row, k+1, podd);
            p[k]-=2; q[k] = 0; q[k+1] -= 2; q[k+2] -= 2;
        }
       
        if(k+2 < m && q[k] < 2 && q[k+1] < 2 && q[k+2] == 0 ) {
            q[k] += 2; q[k+1] += 2; q[k+2] = 3; p[k+2] += 2;
            if(k+2 < m) dfs (row, k+2, podd);
            q[k] -= 2; q[k+1] -= 2; q[k+2] = 0; p[k+2] -= 2;
        }

        ////////////////////////////////////////////////////////////////////
       
        if (k+1 < m && p[k] == 0 && q[k] == 0 && 0 == (p[k+1] &1)) {
            p[k] = q[k] = 3; ++p[k+1];
            if(k+1 < m) dfs (row, k+1, podd + 2);
            p[k] = q[k] = 0; --p[k+1];
        }

       
        if (k+1 < m && 0 == (p[k]&1) && p[k+1] == 0 && q[k+1] == 0) {
            ++p[k]; p[k+1] = q[k+1] = 3;
            if(k+1 < m) dfs (row, k+1, podd+2);
            --p[k]; p[k+1] = q[k+1] = 0;
        }

       
        if (k+1 < m && p[k] == 0 && q[k] == 0 && q[k+1] < 2) {
            p[k] = q[k] = 3; q[k+1] += 2;
            if(k+1 < m) dfs (row, k+1, podd + 1);
            p[k] = q[k] = 0; q[k+1] -= 2;
        }

       
        if (k+1 < m && q[k]<2 && p[k+1] == 0 && q[k+1] == 0) {
            q[k] += 2; p[k+1] = q[k+1] = 3;
            if(k+1 < m) dfs (row, k+1, podd+1);
            q[k] -= 2; p[k+1] = q[k+1] = 0;
        }
    }
}

int main()
{
    int t, r, ps;
    getBits ();
    scanf ("%d", &t);

    for (int cs = 0; cs < t; ++cs) {
        scanf ("%d %d", &n, &m); getchar();
        bugs = 0;
        lastp = m - 1;
        for (r = 0; r < n; ++r) {
            gets (dat[r]);
            for (ps = 0; ps < m; ++ps) if ('#' == dat[r][ps]) ++bugs;
        }
        if (bugs == n*m) {
            printf ("1\n");
            continue;
        }

        if ( n<2 || m < 2 || (n*m-bugs) % 4) {
            printf ("0\n");
            continue;
        }

       

        for (ps = 0; ps < m; ++ps) { // the -1th row and 0th row
            if (dat[0][ps] == '#')
                p[ps] = 3;
            else p[ps] = 1;
        }

        memset (num[0], 0, sizeof num[0]);
        num[0][getNum(p)] = 1;

        for (int rid = 1; rid < n; ++rid) {
            memset (num[rid&1], 0, sizeof num[rid&1]);
            for (GS = 0; GS < four[m]; ++GS) {
                if (num[ (rid+1)&1 ][GS] == 0) continue;

                for (ps = 0; ps < m; ++ps) {
                    p[ps] = sbits[GS][ps];
                    if (dat[rid][ps] == '#') {
                        if (p[ps] > 1) // ? -> x
                            q[ps] = 3;
                        else q[ps] = 2;
                    } else {
                        if (p[ps] > 1)
                            q[ps] = 1;
                        else q[ps] = 0;
                    }
                }
                int podd = 0;
                for (int pd = 0; pd < m; ++pd) if (1 & p[pd]) ++podd;
                newOE = rid & 1, oldOE = 1 - newOE;
                dfs (rid, 0, podd);
            }
        }
        printf ("%d\n", num[(n+1)&1][ four[m] - 1 ]);
    }
    return 0;
}