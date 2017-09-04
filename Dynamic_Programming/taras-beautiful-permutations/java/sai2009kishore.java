from sys import stdin

mod = 10**9+7

# C[i][j] = Comb(i, j) mod 10**9+7
C = [[0 for _ in xrange(i+1)] for i in xrange(2001)]
C[0][0] = 1
for i in xrange(1, 2001):
    C[i][0] = 1
    for j in xrange(1, i):
        C[i][j] = C[i-1][j-1] + C[i-1][j]
        C[i][j] %= mod
    C[i][i] = 1

# P[i] = Perm(i) mod 10**9+7
P = [1 for _ in xrange(2001)]
for i in xrange(1, 2001):
    P[i] = P[i-1]*i
    P[i] %= mod

# A[i] = 2^(-i) mod 10**9+7
A = [1 for _ in xrange(2001)]
for i in xrange(1, 2001):
    if A[i-1] % 2 == 0:
        A[i] = A[i-1]/2
    else:
        A[i] = (A[i-1]+mod)/2
    A[i] %= mod

def f(n, a):
    a.sort()
    k = 0
    for i in xrange(1, n):
        if a[i] == a[i-1]:
            k += 1     
    ans = 0
    for i in xrange(1, k+1):
        t = C[k][i]*P[n-i]*A[k-i]
        t %= mod
        if i % 2 == 0:
            ans -= t
        else:
            ans += t
        ans %= mod
    ans = P[n]*A[k] - ans
    ans %= mod
    return ans

q = int(stdin.readline().strip())
for _ in xrange(q):
    n = int(stdin.readline().strip())
    a = map(int, stdin.readline().strip().split())
    print f(n, a)