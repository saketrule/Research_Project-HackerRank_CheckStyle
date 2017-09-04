import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int n= scanner.nextInt();
        
        for(int i=0;i<n;i++){
            int m = scanner.nextInt();
            int [][] points= new int[m][2];
            
            for(int j=0;j<m;j++){
                points[j][0] = scanner.nextInt();
                points[j][1] = scanner.nextInt();
            }
            
            HashSet<Double>slopeSet = new HashSet();
            HashSet<int[]>pointSet = new HashSet();
            
            int turn = 0;
            for(int j = 0;j < m;j++) {
                int x1 = points[j][0];
                int y1 = points[j][1];
                boolean found = false;
                HashSet<Double>temp = new HashSet();
                if(j == 0){
                    turn ++;
                }
                for(int [] point : pointSet){
                    int x2 = point[0];
                    int y2 = point[1];
                    double slope = 0.0;
                    if(x2 == x1) {
                        slope = Double.MAX_VALUE;
                    }
                    slope = (double)(y2 - y1)/(double)(x2 - x1);
                    if(slopeSet.contains(slope)){
                        found = true;
                        break;
                    }
                    temp.add(slope);
                }
                if(!found){
                    if(pointSet.size() > 1){
                        turn ++;
                    }
                    
                    slopeSet.addAll(temp);
                }
                temp.clear();
                pointSet.add(points[j]);
            }
            System.out.println(turn+" "+slopeSet.size()*2);
            
        }
    }
}