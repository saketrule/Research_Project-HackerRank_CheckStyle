import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc= new Scanner(System.in);
        int m= sc.nextInt();
        int n= sc.nextInt();
        int r= sc.nextInt();
        int i,j;
        long arr[][]= new long[m][];
        for(i=0; i< m; i++){
            arr[i]= new long[n];
            for(j=0; j< n; j++){
                arr[i][j]= sc.nextLong();
            }
        }
        
        float midM= (m-1)/2;
        float midN= (n-1)/2;
        int mRadius, nRadius;
        int mMinusOne= m-1;
        int nMinusOne= n-1;
        int maxRotation= 2*(m+n)-4;
        int currentRotation;
        int leastRadius;
        
        //Prints output;
        for(int loop_i=0; loop_i< m; loop_i++){
            if(loop_i>midM)
                mRadius= mMinusOne-loop_i;
            else
                mRadius= loop_i;
            
            for(int loop_j=0; loop_j< n; loop_j++){
                i= loop_i;
                j= loop_j;
                if(j>midN)
                    nRadius= nMinusOne-j;
                else
                    nRadius= j;
                
                if(nRadius>mRadius)
                    leastRadius= mRadius;
                else
                    leastRadius= nRadius;
                
                currentRotation= r%(maxRotation-8*leastRadius);
                
                //Finding initial Quadrant
                int initialQuadrant;
                if(i-leastRadius == 0 && j+leastRadius != nMinusOne)
                    initialQuadrant= 0;
                else if(j+leastRadius == nMinusOne && i+leastRadius != mMinusOne)
                    initialQuadrant= 1;
                else if(i+leastRadius == mMinusOne && j-leastRadius != 0)
                    initialQuadrant= 2;
                else //if(j-leastRadius == 0 && i-leastRadius != 0)
                    initialQuadrant= 3;
                    

                int mMinusOneMinusleastRadius= mMinusOne - leastRadius;
                int nMinusOneMinusleastRadius= nMinusOne - leastRadius;
                
  //              System.out.format("([%d][%d])", loop_i, loop_j);
     //           System.err.format("([%d][%d])", loop_i, loop_j);
                outerLoop: for(int relativeQuadrant=0; relativeQuadrant<5; relativeQuadrant++){
                    switch((initialQuadrant+relativeQuadrant)%4){//Movement in each quadrant.
                        case 0:
                        if(currentRotation+j <= nMinusOneMinusleastRadius){
                            System.out.print(arr[i][currentRotation+j]);
                            break outerLoop;
                        }
                        currentRotation-=(nMinusOneMinusleastRadius-j);
                        j= nMinusOneMinusleastRadius;
                        break;
                            
                        case 1:
                        if(currentRotation+i <= mMinusOneMinusleastRadius){
                            System.out.print(arr[currentRotation+i][j]);
                            break outerLoop;
                        }
                        currentRotation-=(mMinusOneMinusleastRadius-i);
                        i= mMinusOneMinusleastRadius;
                        break;
                        
                        case 2:
                        if(j-currentRotation >= 0+leastRadius){
                            System.out.print(arr[i][j-currentRotation]);
                            break outerLoop;
                        }
                        currentRotation-=(j-leastRadius);
                        j= leastRadius;
                        break;
                        
                        case 3:
                        if(i-currentRotation >= 0+leastRadius){
                            System.out.print(arr[i-currentRotation][j]);
                            break outerLoop;
                        }
                        currentRotation-=(i-leastRadius);
                        i= leastRadius;
                        break;
                    }
                }
                System.out.print(" ");
                System.err.print(leastRadius+"."+initialQuadrant+" ");
                
            }
            System.err.println("");
            System.out.println("");
        }
        
    }
}