import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Solution {

 /**
  * @param args
  */
 public static void main(String[] args) {
  // TODO Auto-generated method stub
  Scanner scan = new Scanner(System.in);
  
  int t; //number of test cases
  int n; //number of coordinates
  
  t = scan.nextInt();
  
  HashMap coords = new HashMap();
  
  while(t>0)
  {
   n = scan.nextInt();
   
   int i;
   
   for(i=0;i<n;i++)
   {
    int x,y;
    x = scan.nextInt();
    y = scan.nextInt();
    
    coords.put(x, y);
    
   }//end of for
   
   //minimum number of turns = size of hashmap
   //number of ways of removing in minimum turns = n*minimum number of turns
      
   System.out.println(coords.size()+" "+n*coords.size());
   
   coords.clear();
   
   t--;
  }
  
  
  
  
 }

}