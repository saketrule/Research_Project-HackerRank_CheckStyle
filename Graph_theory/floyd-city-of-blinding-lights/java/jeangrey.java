import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
 
public class Solution {
 
    private static int toInteger (String value ){ return Integer.parseInt(value);}
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        String tokens[] = scanner.nextLine().split( " " );
        int numberOfNodes = toInteger( tokens [0] ) , numberOfEdges = toInteger( tokens [1] );
        double matrix [][] = new double[numberOfNodes][numberOfNodes];
        double distance[][] = new double[numberOfNodes][numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                matrix[i][j] = Integer.MAX_VALUE;
                distance[i][j] =Integer.MAX_VALUE;
            }
            distance[i][i] = 0 ;
        }
        for (int i = 0; i < numberOfEdges; i++) {
            tokens = scanner.nextLine().split( " " );
            int source = toInteger( tokens [0] ) -1 , destination = toInteger( tokens [1] ) - 1 , weight = toInteger( tokens[2] );
            matrix[source][destination] = weight;
            distance[source][destination] = weight;
        }
 
 
        for (int k = 0; k < numberOfNodes; k++) {
for (int j = 0; j < numberOfNodes; j++) {
for (int i = 0; i < numberOfNodes; i++) {
if (distance[i][j] > distance[i][k] + distance[k][j])
distance[i][j] = distance[i][k] + distance[k][j];
}
}
}
        int queries = toInteger( scanner.nextLine() );
        StringBuilder builder = new StringBuilder();
        for (int i = 0 ; i < queries ; i++){
            tokens = scanner.nextLine().split(" ");
            int source = toInteger( tokens[0] ) - 1 ,destination = toInteger( tokens[1] ) - 1;
            if (source == destination){
                builder.append ("0");
            }
            else {
                if (distance[source][destination] != Integer.MAX_VALUE)
                    builder.append((int)distance[source][destination]);
                else
                    builder.append("-1");
            }
            if ( i != queries - 1)
                builder.append("\n");
        }
        System.out.println(builder);
    }
}