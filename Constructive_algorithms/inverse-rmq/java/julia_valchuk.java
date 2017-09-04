import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int to = 2 * n - 1;
        Map<Integer, Cell> map = new HashMap<>(n);

        for (int i = 0; i < to; i++) {
            int value = in.nextInt();
            Cell cell = map.get(value);
            if (cell == null) {
                cell = new Cell(value);
                map.put(value, cell);
            }
            cell.count++;
        }

        if (map.size() != n){
            System.out.println("NO");
            return;
        }

        Cell[] sorted = new TreeSet<>(map.values()).toArray(new Cell[1]);
        int min = Integer.MIN_VALUE;
        int[] result = new int[to];

        for (int i = 0; i < sorted.length; i++) {
            Cell cell = sorted[i];
            if (cell.element < min) {
                System.out.println("NO");
                return;
            }

            int index = 2 * i;
            int count = 0;
            for (int j = 0; index < to; j++) {
                result[index] = cell.element;
                index = 2 * index + 1;
                count++;
            }
            
            if (count != cell.count) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
        outArr(result);
    }

    private static void outArr(int[] arr) {
        int n = arr.length;
        System.out.print(arr[0]);
        for (int i = 1; i < n; i++) {
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }

    private static class Cell implements Comparable<Cell>{
        int element;
        int count;

        public Cell(int element) {
            this.element = element;
        }

        @Override
        public int compareTo(Cell o) {
            if(o.count == this.count) {
                return this.element - o.element;
            } else {
                return o.count - this.count;
            }
        }
    }
}