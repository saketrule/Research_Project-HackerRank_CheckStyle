import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int rotation = scanner.nextInt();
        int[][] grid = new int[rows][cols];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }
        int layers = Math.min(rows, cols) / 2;
        for (int i = 0; i < layers; i++) {
            rotateGridLayer(grid, rotation, i);
        }
        // Print the grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j]);
                if (j < grid[i].length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    private static void rotateGridLayer(int[][] grid, int rotation, int layer) {
        int rows = grid.length - (layer * 2);
        int cols = grid[0].length - (layer * 2);
        // Place in list
        LinkedList<Integer> items = new LinkedList<>();
        for (int i = cols - 1; i >= 0; i--) { // top row - right to left
            items.add(0, grid[layer][i + layer]);
        }
        for (int i = 1; i < rows; i++) { // left column - top to bottom
            items.add(0, grid[i + layer][layer]);
        }
        for (int i = 1; i < cols; i++) { // bottom row - left to right
            items.add(0, grid[rows + layer - 1][i + layer]);
        }
        for (int i = rows - 2 ; i > 0; i--) { // right column - bottom to top
            items.add(0, grid[i + layer][cols + layer - 1]);
        }
        // Rotate list
        for (int i = 0; i < rotation % items.size(); i++) {
            items.add(items.remove(0));
        }
        // Place back in grid
        for (int i = cols - 1; i >= 0; i--) { // top row - right to left
            grid[layer][i + layer] = items.removeLast();
        }
        for (int i = 1; i < rows; i++) { // left column - top to bottom
            grid[i + layer][layer] = items.removeLast();
        }
        for (int i = 1; i < cols; i++) { // bottom row - left to right
            grid[rows + layer - 1][i + layer] = items.removeLast();
        }
        for (int i = rows - 2 ; i > 0; i--) { // right column - bottom to top
            grid[i + layer][cols + layer - 1] = items.removeLast();
        }
    }

}