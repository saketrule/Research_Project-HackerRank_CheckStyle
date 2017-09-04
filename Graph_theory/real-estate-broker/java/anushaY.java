import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Spec{
    long area;
    long price;
    public Spec(long area, long price){
        this.area = area;
        this.price = price;
    }
}

class Count{
    int count;
    int index;
}

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Spec[] clients = new Spec[n];
        for(int clients_i=0; clients_i < n; clients_i++){
            clients[clients_i] = new Spec(in.nextLong(),in.nextLong());
        }
        Spec[] houses = new Spec[m];
        for(int houses_i=0; houses_i < m; houses_i++){
            houses[houses_i] = new Spec(in.nextLong(),in.nextLong());
        }
        
        int[][] matrix = new int[n][m];
        Count[] rowCount = new Count[n];
        Count[] columnCount = new Count[m];
        for(int j=0; j< m ; j++){
            columnCount[j] = new Count();
        }
        for(int i=0;i<n;i++){
            rowCount[i] = new Count();
            for(int j=0; j< m ; j++){
                if(clients[i].area < houses[j].area && clients[i].price >= houses[j].price){
                    matrix[i][j] = 1;
                    rowCount[i].count++;
                    rowCount[i].index = j;
                    columnCount[j].count++;
                    columnCount[j].index = i;
                }
            }
        }
        
        for(int i=0;i<n ;i++){
            if(rowCount[i].count==1){
                for(int j=i+1;j<n;j++){
                    if(rowCount[j].count == 1 && rowCount[i].index == rowCount[j].index){
                        matrix[j][rowCount[i].index] = 0;
                        rowCount[j].count =0;
                        columnCount[rowCount[i].index].count--;
                    }
                }
            }
        }
        
         for(int i=0;i<m ;i++){
            if(columnCount[i].count==1){
                for(int j=i+1;j<m;j++){
                    if(columnCount[j].count == 1 && columnCount[i].index == columnCount[j].index){
                        matrix[columnCount[j].index][j] = 0;
                        columnCount[j].count =0;
                        rowCount[columnCount[i].index].count--;
                    }
                }
            }
        }
        
        
        
        int clientsCount = 0;
        for(int i=0;i<n;i++){
            for(int j=0; j< m ; j++){
                if(matrix[i][j]==1){
                    clientsCount++;
                    break;
                }
            }
        }
        
        int housesCount = 0;
        for(int j=0; j< m ; j++){
            for(int i=0;i<n;i++){
                if(matrix[i][j]==1){
                    housesCount++;
                    break;
                }
            }
        }
        
        System.out.println(Math.min(clientsCount,housesCount));
        
    }
}