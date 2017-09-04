import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void Matrix() {

        Scanner scanner = new Scanner(System.in);

        String s = scanner.nextLine();

        String[] input = s.split(" ");

        int  m = Integer.parseInt(input[0]);

        int n = Integer.parseInt(input[1]);

        int R = Integer.parseInt(input[2]);

        int a[][] = new int[m][n];

        for(int i = 0;i<m;i++) {

            s = scanner.nextLine();

            input = s.split(" ");

            for(int j = 0;j<n;j++) {

                a[i][j] = Integer.parseInt(input[j]);

            }
        }
        int row = m;
        int col = n;

        int clRow = 0;

        int clCol = 0;

        int anRow = m - 1;

        int anCol = n - 1;

        int TotRot = (m<n)?(m/2):(n/2);

        while(TotRot>0) {

            int rot = (row + col - 2) * 2;

            for (int i = 1; i <= (R % rot); i++) {

                    int temp1 = a[clRow][clCol];

                    int p;

                    for (p = clCol; p < anCol; p++) {

                        a[clRow][p] = a[clRow][p + 1];
                    }

                    int temp2 = a[anRow][clCol];

                    for (p = anRow; p > clRow + 1; p--) {

                        a[p][clCol] = a[p - 1][clCol];

                    }
                    a[p][clCol] = temp1;

                    temp1 = a[anRow][anCol];

                    for (p = anCol; p > clCol + 1; p--) {

                        a[anRow][p] = a[anRow][p - 1];

                    }

                    a[anRow][p] = temp2;

                    for (p = clRow; p < anRow - 1; p++) {

                        a[p][anCol] = a[p + 1][anCol];

                    }

                    a[p][anCol] = temp1;
            }
            anCol--;
            anRow--;
            clRow++;
            clCol++;
            row = row-2;
            col = col-2;
            TotRot--;
        }
        print(a,m,n);
    }

    public static void print(int[][] a,int m,int n) {
        for(int i=0;i<m;i++) {

            for(int j=0;j<n;j++) {

                System.out.print(a[i][j]+" ");

            }

            System.out.println();
        }
    }

    public static void main(String args[]) {
        Matrix();
    }
}