import java.util.Scanner;
public class Solution {

    private int [] heights;

    private void printWinner() {

        int result = 0;

        for( int i : heights )
            result ^= i;

        System.out.println( result == 0 ? "Second" : "First" );
    }
    private void readInput() {

        Scanner s = new Scanner( System.in );
        int t = s.nextInt(); // t test cases

        for( int i = 0; i < t; i ++ ) {

            int n = s.nextInt();
            heights = new int[ n ];

            for( int j = 0; j < n ; j ++ )
                heights[ j ] = s.nextInt();

            printWinner();

        }

        s.close();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.readInput();
    }
}