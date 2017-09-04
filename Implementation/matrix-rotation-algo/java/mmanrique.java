import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        int K = scanner.nextInt();
        int[][] matrix = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = scanner.nextInt();
            }

        }
        //Generate Lists of levels.
        LinkedList<LinkedList<Integer>> levels = getLevelsLists(rows, columns, matrix);

        for (LinkedList<Integer> integers : levels) {
            rotateList(integers, K);
        }

        //Put it all back again
        matrix = regenerateMatrix(levels, rows, columns);
        for (int i = 0; i < rows; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < columns; j++) {
                stringBuilder.append(matrix[i][j]).append(" ");
            }
            System.out.println(stringBuilder.substring(0, stringBuilder.length() - 1));
        }

        scanner.close();
    }

    private static int[][] regenerateMatrix(LinkedList<LinkedList<Integer>> levels, int rows, int columns) {

        int matrix[][] = new int[rows][columns];
        int height = rows;
        int width = columns;
        int level = 0;
        while (height >= 2 && width >= 2) {
            LinkedList<Integer> integers = levels.removeFirst();

            for (int i = level; i < level + width - 1; i++) {
                matrix[level][i] = integers.removeFirst();
            }
            for (int i = level; i < level + height - 1; i++) {
                matrix[i][columns - level - 1] = integers.removeFirst();
            }
            for (int i = level + width - 1; i > level; i--) {
                matrix[rows - level - 1][i] = integers.removeFirst();
            }

            for (int i = level + height - 1; i > level; i--) {
                matrix[i][level] = integers.removeFirst();
            }
            height -= 2;
            width -= 2;
            level++;
        }
        return matrix;
    }


    private static LinkedList<LinkedList<Integer>> getLevelsLists(int rows, int columns, int[][] matrix) {
        LinkedList<LinkedList<Integer>> levels = new LinkedList<>();
        int height = rows;
        int width = columns;
        int level = 0;
        //There are two problems here. From a matrix, form levels

        while (height >= 2 && width >= 2) {
            //means we have a level
            LinkedList<Integer> integers = new LinkedList<>();
            for (int i = level; i < level + width - 1; i++) {
                integers.add(matrix[level][i]);
            }
            for (int i = level; i < level + height - 1; i++) {
                integers.add(matrix[i][columns - level - 1]);
            }
            for (int i = level + width - 1; i > level; i--) {
                integers.add(matrix[rows - level - 1][i]);
            }

            for (int i = level + height - 1; i > level; i--) {
                integers.add(matrix[i][level]);
            }
            levels.add(integers);

            height -= 2;
            width -= 2;
            level++;
        }
        return levels;
    }

    private static void rotateList(LinkedList<Integer> integers, int k) {
        int rotations = k % integers.size();
        for (int i = 0; i < rotations; i++) {
            integers.add(integers.removeFirst());
        }
    }

}