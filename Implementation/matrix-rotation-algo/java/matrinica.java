import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int rows = input.nextInt();
        int cols = input.nextInt();
        int rotation = input.nextInt();
        int[][] mat = new int[rows][];
        for (int row = 0; row < rows; row++) {
            mat[row] = new int[cols];
            for (int col = 0; col < cols; col++) {
                mat[row][col] = input.nextInt();
            }
        }

        MatrixRotation matrixRotation = new MatrixRotation(mat, rows, cols, rotation);
        int[][] result = matrixRotation.rotate();

        printMatrix(result);
    }
    
    private static void printMatrix(int[][] result) {
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[0].length; col++) {
                System.out.printf("%d ", result[row][col]);
            }
            System.out.println();
        }
    }
}


/**
 * Created by martincai on 7/7/15.
 */
class MatrixRotation {
    private final int[][] mat;
    private final int rows;
    private final int cols;
    private final int rotation;
    private int[][] result;

    public MatrixRotation(int[][] mat, int rows, int cols, int rotation) {
        this.mat = mat;
        this.rows = rows;
        this.cols = cols;
        this.rotation = rotation;

        result = new int[rows][];
        for (int row = 0; row < rows; row++) {
            result[row] = new int[cols];
        }
    }

    public int[][] rotate() {
        for (int layer = 0; layer < getNumLayers(); layer++) {
            int rowsInLayer = rows - layer * 2;
            int colsInLayer = cols - layer * 2;
            int numElementsInLayer = 2 * (rowsInLayer + colsInLayer - 2);
            int actualRotations = rotation % numElementsInLayer;
            Map<Pair<Integer, Integer>, Integer> mapFromIndexToRotation = createMap(rowsInLayer, colsInLayer, layer);
            Map<Integer, Pair<Integer, Integer>> mapFromRotationToIndex = inverse(mapFromIndexToRotation);
            for (int row = layer; row < layer + rowsInLayer; row++) {
                rotate(numElementsInLayer, mapFromIndexToRotation, mapFromRotationToIndex, row, layer, actualRotations);
            }
            for (int col = layer + 1; col < layer + colsInLayer; col++) {
                rotate(numElementsInLayer, mapFromIndexToRotation, mapFromRotationToIndex, layer + rowsInLayer - 1, col, actualRotations);
            }
            for (int row = layer + rowsInLayer - 2; row >= layer; row--) {
                rotate(numElementsInLayer, mapFromIndexToRotation, mapFromRotationToIndex, row, layer + colsInLayer - 1, actualRotations);
            }
            for (int col = layer + colsInLayer - 2; col > layer; col--) {
                rotate(numElementsInLayer, mapFromIndexToRotation, mapFromRotationToIndex, layer, col, actualRotations);
            }
        }
        return result;
    }

    private void rotate(int numElementsInLayer, Map<Pair<Integer, Integer>, Integer> mapFromIndexToRotation, Map<Integer, Pair<Integer, Integer>> mapFromRotationToIndex, int row, int col, int rotation) {
        Integer newRotation = (mapFromIndexToRotation.get(new Pair<>(row, col)) + rotation) % numElementsInLayer;
        Pair<Integer, Integer> index = mapFromRotationToIndex.get(newRotation);
        result[index.fst][index.snd] = mat[row][col];
    }

    private int getNumLayers() {
        return Math.min(rows, cols) / 2;
    }

    private Map<Pair<Integer, Integer>, Integer> createMap(int rows, int cols, int layer) {
        Map<Pair<Integer, Integer>, Integer> map = new HashMap<>();
        int rotation = 0;
        for (int row = layer; row < layer + rows; row++) {
            map.put(new Pair<>(row, layer), rotation);
            rotation++;
        }
        for (int col = layer + 1; col < layer + cols; col++) {
            map.put(new Pair<>(layer + rows - 1, col), rotation);
            rotation++;
        }
        for (int row = layer + rows - 2; row >= layer; row--) {
            map.put(new Pair<>(row, layer + cols - 1), rotation);
            rotation++;
        }
        for (int col = layer + cols - 2; col > layer; col--) {
            map.put(new Pair<>(layer, col), rotation);
            rotation++;
        }
        return map;
    }

    private <S, T> Map<S, T> inverse(Map<T, S> map) {
        Map<S, T> result = new HashMap<>();
        for (Map.Entry<T, S> entry : map.entrySet()) {
            result.put(entry.getValue(), entry.getKey());
        }
        return result;
    }
}

class Pair<A, B> {
    public final A fst;
    public final B snd;

    public Pair(A var1, B var2) {
        this.fst = var1;
        this.snd = var2;
    }

    public String toString() {
        return "Pair[" + this.fst + "," + this.snd + "]";
    }

    public boolean equals(Object var1) {
        return var1 instanceof Pair && Objects.equals(this.fst, ((Pair) var1).fst) && Objects.equals(this.snd, ((Pair)var1).snd);
    }

    public int hashCode() {
        return this.fst == null?(this.snd == null?0:this.snd.hashCode() + 1):(this.snd == null?this.fst.hashCode() + 2:this.fst.hashCode() * 17 + this.snd.hashCode());
    }

    public static <A, B> Pair<A, B> of(A var0, B var1) {
        return new Pair(var0, var1);
    }
}