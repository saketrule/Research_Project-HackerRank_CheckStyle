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
        
        int[][] array = new int[m][n];
        
        int numSnakes = (int) Math.ceil(Math.min(m,n) / (double) 2);
        ArrayList<LinkedList<Integer>> snakeList = new ArrayList<LinkedList<Integer>>(numSnakes);
        int[] lenArray = new int[numSnakes];
        
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = sc.nextInt();
            }
        }
        
        for(int i=0; i < numSnakes; i++) {
            LinkedList<Integer> list = new LinkedList<Integer>();
            int len = readWriteSnake(i, array, m, n, list, false);

            lenArray[i] = len;
            snakeList.add(i, list);
        }
        
        for (int i =0; i < numSnakes; i++) {
            LinkedList<Integer> list = snakeList.get(i);
            int tmp = r;
            tmp = tmp % lenArray[i];
            
            for(int j=0; j < tmp; j++) {
                list.addLast(list.removeFirst());
            }
            
            readWriteSnake(i, array, m, n, list, true);
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            boolean first = true;
            for (int j = 0; j < n; j++) {
                if (!first)
                    sb.append(" ");
                first = false;
                sb.append(array[i][j]);
            }
            
            if (i != m-1)
                sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    
    public static int readWriteSnake(int i, int[][] array, int m, int n, LinkedList<Integer> ret, boolean write) {
        int x = 0+i;
        int y = 0+i;
        
        boolean horiz = false;
        boolean vert = false;
        int count = 0;
        
        //lines
        if (n != 2 && m != 2) {
            if (n - i == 2 && m - i > 2) {
                //vertical line
                while (y < m-i) {
                    if (!write)
                        ret.addLast(array[y][x]);
                    else
                        array[y][x] = ret.removeFirst();
                    count++;
                    y++;
                }
            } else if (m-i == 2 && n-i > 2) {
                while (x < n - i) {
                    if (!write)
                        ret.addLast(array[y][x]);
                    else
                        array[y][x] = ret.removeFirst();
                    count++;
                    x++;
                }
            } else if (m-i == 2 && n-i == 2) {
                if (!write)
                    ret.addLast(array[x][y]);
                else
                    array[y][x] = ret.removeFirst();
                count++;
            }
            
            if (count > 0)
                return count;
        }
                
        //rectangles
        while (x < n - i) {
            if (!write)
                ret.addLast(array[y][x]);
            else
                array[y][x] = ret.removeFirst();
            count++;
            x++;
        }
        x--;
        y++;
        while (y < m - i) {
            if (!write)
                ret.addLast(array[y][x]);
            else
                array[y][x] = ret.removeFirst();
            count++;
            y++;
        }
        y--;
        x--;
        
        while (x >= 0+i) {
            if (!write)
                ret.addLast(array[y][x]);
            else
                array[y][x] = ret.removeFirst();
            count++;
            x--;
        }
        x++;
        y--;
        while (y > 0+i) {
            if (!write)
                ret.addLast(array[y][x]);
            else
                array[y][x] = ret.removeFirst();
            count++;
            y--;
        }
        
        return count;
    }
}