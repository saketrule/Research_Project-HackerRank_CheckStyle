import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Main {

static int MOD = (int) 1e9 + 7;

public static void main(String[] args) throws IOException {
    int N, K, mask;
    int stains[][];
    int arr[];
    int DP[][][];
    int vals[];

    InputStreamReader sr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(sr);

    String cad[] = br.readLine().split(" ");
    N = Integer.parseInt(cad[0]);
    K = Integer.parseInt(cad[1]);

    K = N - K;

            if(K == 0) {
                System.out.print(1);
                System.exit(0);
            }

    stains = new int[N+2][2];
    arr = new int[N+2];
    vals = new int[]{0,100000,0,100000};

    for(int i = 1;i<=N;i++) {
        cad = br.readLine().split(" ");
        stains[i][0] = Integer.parseInt(cad[0]);
        stains[i][1] = Integer.parseInt(cad[1]);

        vals[0] = Math.max(vals[0], stains[i][0]);
        vals[1] = Math.min(vals[1], stains[i][0]);
        vals[2] = Math.max(vals[2], stains[i][1]);
        vals[3] = Math.min(vals[3], stains[i][1]);
    }


    for(int i = 1;i<=N;i++) {
        mask = 0;
        for(int j = 0;j<4;j++) {
            if(vals[j] == stains[i][j/2])
                mask |= (1<<j);
        }
        arr[i] = mask;          
    }

    DP = new int[K+2][N+2][18];

    for(int j = 1;j<=N;j++) {
        DP[1][j][ arr[j] ] = 1;
        for(int k = 0;k<16;k++){
            DP[1][j][k] += DP[1][j-1][k]; 
        }
    }   

    for(int i=1;i<K;i++) {
        for(int j = i;j<N;j++) {
            for(int k = 0;k<16;k++){
                DP[i+1][j+1][k | arr[j+1]] = (DP[i+1][j+1][k | arr[j+1]] + DP[i][j][k] ) % MOD; 
                DP[i+1][j+1][k] = (DP[i+1][j+1][k] + DP[i+1][j][k] ) % MOD;
            }
        }
    }

    int sol = 0;
    for(int k = 0;k<15;k++)
        sol = (sol + DP[K][N][k]) % MOD;

    System.out.println(sol);        
}
}