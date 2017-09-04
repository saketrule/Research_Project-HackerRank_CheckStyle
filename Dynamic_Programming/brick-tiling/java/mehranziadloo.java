/**
 * Created by mehran on 6/17/16.
 */
import java.util.Scanner;

public class Solution {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t=0; t<T; t++) {
            int N = in.nextInt();
            int M = in.nextInt();
            in.nextLine();
            char[][] board = new char[N][M];
            for (int n=0; n<N; n++) {
                String l = in.nextLine();
                for (int m=0; m<M; m++) {
                    board[n][m] = l.charAt(m);
                }
            }
            System.out.println(bfs(board, 0, 0));
        }
    }

    static long bfs(char[][] board, int i, int j)
    {
        int nextI, nextJ;
        nextI = i + (j + 1) / board[0].length;
        nextJ = (j + 1) % board[0].length;

        if (board[i][j] != '.') {
            if (nextI < board.length && nextJ < board[0].length) {
                return bfs(board, nextI, nextJ);
            }
            return 1;
        }

        long counter = 0;
        board[i][j] = '*';

        // ***
        // *
        if (i+1 < board.length && j+2 < board[0].length
                && board[i][j+1] == '.'
                && board[i][j+2] == '.'
                && board[i+1][j] == '.') {
            board[i][j+1] = '*';
            board[i][j+2] = '*';
            board[i+1][j] = '*';

            if (nextI < board.length && nextJ < board[0].length) {
                counter += bfs(board, nextI, nextJ);
            }
            else {
                counter++;
            }

            board[i][j+1] = '.';
            board[i][j+2] = '.';
            board[i+1][j] = '.';
        }

        // **
        //  *
        //  *
        if (i+2 < board.length && j+1 < board[0].length
                && board[i][j+1] == '.'
                && board[i+1][j+1] == '.'
                && board[i+2][j+1] == '.') {
            board[i][j+1] = '*';
            board[i+1][j+1] = '*';
            board[i+2][j+1] = '*';

            if (nextI < board.length && nextJ < board[0].length) {
                counter += bfs(board, nextI, nextJ);
            }
            else {
                counter++;
            }

            board[i][j+1] = '.';
            board[i+1][j+1] = '.';
            board[i+2][j+1] = '.';
        }

        //   *
        // ***
        if (i+1 < board.length && j-2 >= 0
                && board[i+1][j] == '.'
                && board[i+1][j-1] == '.'
                && board[i+1][j-2] == '.') {
            board[i+1][j] = '*';
            board[i+1][j-1] = '*';
            board[i+1][j-2] = '*';

            if (nextI < board.length && nextJ < board[0].length) {
                counter += bfs(board, nextI, nextJ);
            }
            else {
                counter++;
            }

            board[i+1][j] = '.';
            board[i+1][j-1] = '.';
            board[i+1][j-2] = '.';
        }

        // *
        // *
        // **
        if (i+2 < board.length && j+1 < board[0].length
                && board[i+1][j] == '.'
                && board[i+2][j] == '.'
                && board[i+2][j+1] == '.') {
            board[i+1][j] = '*';
            board[i+2][j] = '*';
            board[i+2][j+1] = '*';

            if (nextI < board.length && nextJ < board[0].length) {
                counter += bfs(board, nextI, nextJ);
            }
            else {
                counter++;
            }

            board[i+1][j] = '.';
            board[i+2][j] = '.';
            board[i+2][j+1] = '.';
        }

        // ***
        //   *
        if (i+1 < board.length && j+2 < board[0].length
                && board[i][j+1] == '.'
                && board[i][j+2] == '.'
                && board[i+1][j+2] == '.') {
            board[i][j+1] = '*';
            board[i][j+2] = '*';
            board[i+1][j+2] = '*';

            if (nextI < board.length && nextJ < board[0].length) {
                counter += bfs(board, nextI, nextJ);
            }
            else {
                counter++;
            }

            board[i][j+1] = '.';
            board[i][j+2] = '.';
            board[i+1][j+2] = '.';
        }

        //  *
        //  *
        // **
        if (i+2 < board.length && j-1 >= 0
                && board[i+1][j] == '.'
                && board[i+2][j] == '.'
                && board[i+2][j-1] == '.') {
            board[i+1][j] = '*';
            board[i+2][j] = '*';
            board[i+2][j-1] = '*';

            if (nextI < board.length && nextJ < board[0].length) {
                counter += bfs(board, nextI, nextJ);
            }
            else {
                counter++;
            }

            board[i+1][j] = '.';
            board[i+2][j] = '.';
            board[i+2][j-1] = '.';
        }

        // *
        // ***
        if (i+1 < board.length && j+2 < board[0].length
                && board[i+1][j] == '.'
                && board[i+1][j+1] == '.'
                && board[i+1][j+2] == '.') {
            board[i+1][j] = '*';
            board[i+1][j+1] = '*';
            board[i+1][j+2] = '*';

            if (nextI < board.length && nextJ < board[0].length) {
                counter += bfs(board, nextI, nextJ);
            }
            else {
                counter++;
            }

            board[i+1][j] = '.';
            board[i+1][j+1] = '.';
            board[i+1][j+2] = '.';
        }

        // **
        // *
        // *
        if (i+2 < board.length && j+1 < board[0].length
                && board[i][j+1] == '.'
                && board[i+1][j] == '.'
                && board[i+2][j] == '.') {
            board[i][j+1] = '*';
            board[i+1][j] = '*';
            board[i+2][j] = '*';

            if (nextI < board.length && nextJ < board[0].length) {
                counter += bfs(board, nextI, nextJ);
            }
            else {
                counter++;
            }

            board[i][j+1] = '.';
            board[i+1][j] = '.';
            board[i+2][j] = '.';
        }

        board[i][j] = '.';

        return counter;
    }
}