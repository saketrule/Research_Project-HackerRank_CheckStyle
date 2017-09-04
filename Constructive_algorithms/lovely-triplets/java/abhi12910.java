import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

 import java.util.*;
import java.io.*;
public class Sol{
     public int r;
       public  int c;
    boolean issolve(int[][] grid,Sol ob){
       
        if(!isunsigned(grid,ob))
        return true;
        for(int i=1;i<=9;i++){
            if(issafe(grid,i,ob)){
                grid[ob.r][ob.c]=i;
            
        if(issolve(grid,ob))
        return true;
                grid[ob.r][ob.c]=0; }
               
           
      }     
        
       return false;  
               }           
boolean isunsigned(int[][] grid,Sol ob){
    for( ob.r=0;ob.r<9;ob.r++){
        for(ob.c=0;ob.c<9;ob.c++){
        if(grid[ob.r][ob.c]==0)
        return true;
        
    }
}
return false;  
}
boolean isrow(int[][] grid,int num,Sol ob){
    for( ob.c=0;ob.c<9;ob.c++)
{
        if(grid[ob.r][ob.c]==num)
            return true;
        
    }
    return false;
}
boolean iscol(int[][] grid,int num,Sol ob){
    for( ob.r=0;ob.r<9;ob.r++)
{
        if(grid[ob.r][ob.c]==num)
            return true;
        
    }
    return false;
}
boolean isbox(int[][] grid, int num,int str,int str1,Sol ob) {
     for( ob.r=0;ob.r<3;ob.r++){
        for( ob.c=0;ob.c<3;ob.c++){
            if(grid[ob.r+str][ob.c+str1]==num)
                return true;
        }   
     }
return false;
} 
boolean issafe(int[][]grid,int num,Sol ob) {
    return !isrow( grid, num,ob)&&
        !iscol(grid, num,ob)&&
        !isbox(grid, num,ob.r-ob.r%3,ob.c-ob.r%3,ob);
        
}              
public void  printgri(int[][] grid){             
for(int i=0;i<9;i++){
    for( int j=0;j<9;j++){
        System.out.print(grid[i][j]);
    }
System.out.println(" ");
}  
               }
public static void main(String [] args)throws Exception {
      int[][] grid = new int[][]{{3, 0, 6, 5, 0, 8, 4, 0, 0},
                      {5, 2, 0, 0, 0, 0, 0, 0, 0},
                      {0, 8, 7, 0, 0, 0, 0, 3, 1},
                      {0, 0, 3, 0, 1, 0, 0, 8, 0},
                      {9, 0, 0, 8, 6, 3, 0, 0, 5},
                      {0, 5, 0, 0, 9, 0, 6, 0, 0},
                      {1, 3, 0, 0, 0, 0, 2, 5, 0},
                      {0, 0, 0, 0, 0, 0, 0, 7, 4},
                      {0, 0, 5, 2, 0, 6, 3, 0, 0}};
    Sol ob=new Sol();
    
    if(ob.issolve(grid,ob))
        ob.printgri(grid);
   else
    System.out.println(" Solution does not exist");
}  
}