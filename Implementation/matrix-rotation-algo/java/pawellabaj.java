import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();
        int r = sc.nextInt();
        sc.nextLine();

        int[][] a = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
            }
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
        }
        sc.close();

        rotate(a, r);
        printArray(a);

    }

    private static void rotate(int[][] a, int rotates) {
        int rows = a.length;
        int cols = a[0].length;

        int l = Math.min(rows, cols) / 2;

        int x;
        int y;

        List<List<Integer>> snakes = new ArrayList<List<Integer>>();
        for (int i = 0; i < l; i++) {
            List<Integer> snake = new ArrayList<Integer>();
            snakes.add(snake);

            y = i;
            for (x = i; x < cols - i; x++) {
                snake.add(a[y][x]);
            }

            x = cols - i - 1;
            for (y = i + 1; y < rows - i - 1; y++) {
                snake.add(a[y][x]);
            }

            y = rows - i - 1;
            for (x = cols - i - 1; x >= i; x--) {
                snake.add(a[y][x]);
            }

            x = i;
            for (y = rows - i - 2; y >= i + 1; y--) {
                snake.add(a[y][x]);
            }

            Collections.rotate(snake, -rotates);
        }

        for (int i = 0; i < l; i++) {
            List<Integer> snake = snakes.get(i);

            int index = 0;

            y = i;
            for (x = i; x < cols - i; x++) {
                a[y][x] = snake.get(index++);
            }

            x = cols - i - 1;
            for (y = i + 1; y < rows - i - 1; y++) {
                a[y][x] = snake.get(index++);
            }

            y = rows - i - 1;
            for (x = cols - i - 1; x >= i; x--) {
                a[y][x] = snake.get(index++);
            }

            x = i;
            for (y = rows - i - 2; y >= i + 1; y--) {
                a[y][x] = snake.get(index++);
            }
        }

        // System.out.println("------------------");
        // printArray(a);
        // System.out.println("------------------");

    }

    private static void printArray(int[][] a) {
        int m = a.length;
        int n = a[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j]);
                if (j < n - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}