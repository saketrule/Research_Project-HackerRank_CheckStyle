import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Grid{
    private int[][] grid;
    private final int rows;
    private final int cols;


    
    public Grid(int[][] grid, int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.grid = grid;
        
    }
    private void swap(int r1, int c1, int r2, int c2){
        int tmp = grid[r1][c1];
        grid[r1][c1] = grid[r2][c2];
        grid[r2][c2] = tmp;
    }
    
    private void shiftColLeft(int row, int col1, int col2){
        for (int col=col1; col<col2; col++)
            swap(row, col, row, col+1);
    }
    private void shiftColRight(int row, int col1, int col2){
        for (int col=col1-1; col>=col2; col--)
            swap(row, col, row, col+1);
    }
    
    private void shiftRowUp(int col, int row1, int row2){
        for (int row=row1; row<row2; row++)
            swap(row, col, row+1, col);
    }
    private void shiftRowDown(int col, int row1, int row2){
        for (int row=row1-1; row>=row2; row--)
            swap(row, col, row+1, col);
    }
    
    
    private void rotateStripe(int row1, int col1, int row2, int col2){
        shiftColLeft(row1, col1, col2);
        shiftRowUp(col2, row1, row2);
        shiftColRight(row2, col2, col1);
        shiftRowDown(col1, row2, row1+1);
    }
    
    
    public void rotateGrid(int count){
        int stripesNum = Math.min(cols, rows)/2;
        for (int stripeNum=0; stripeNum<stripesNum; stripeNum++){
            int colMax = cols - stripeNum-1;
            int rowMax = rows - stripeNum-1;
            int p = (colMax+ rowMax - 2*stripeNum+2)*2 - 4;
            for (int j=0; j< count % p; j++)
                rotateStripe(stripeNum, stripeNum, rowMax, colMax);
            //for (int col=stripeNum+1; col<=colMax; col++){
            //    swap(stripeNum, col, stripeNum, col-1);
            //}
//            for (int row=stripeNum+1; row <= rowMax; row++){
//                swap(row, colMax, row-1, colMax);
//            }
//            for (int col=colMax; col>stripeNum; col--){
//                swap(rowMax, col, rowMax, col-1);
//            }
            
//            for (int row=rowMax; row>stripeNum; row--){
//                swap(row, stripeNum, row-1, stripeNum);
//            }
        }
    }   
    
    public void printGrid(){
        for (int row=0; row<rows; row++){
            System.out.print(grid[row][0]);
            for (int col=1; col<cols; col++)
                System.out.print(" " + grid[row][col]);
            System.out.println();
        }
        
    }
    
}


public class Solution {

    private static int[][] readGrid(Scanner stdin,int rows,int cols){
        int[][] grid = new  int[rows][cols];
        //stdin.nextLine();
        for (int i=0; i<rows; i++){
            //String row = stdin.nextLine();
            
            for (int j=0; j<cols; j++)
                grid[i][j]= stdin.nextInt();
        }
        return grid;
    }        

   

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int rows = stdin.nextInt();
        int cols = stdin.nextInt();
        int r = stdin.nextInt();
        Grid grid = new Grid(readGrid(stdin, rows, cols), rows, cols);
//        int p = (rows+cols)*2 -4;
////        if (r> p)
////            r = r%p;
        grid.rotateGrid(r);
        
        grid.printGrid();
            
        }
    }