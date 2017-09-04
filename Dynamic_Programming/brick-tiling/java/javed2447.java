/* Enter your code here. Read input from STDIN. Print output to STDOUT */
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Solution {
public static void main(String[] args) {
 try {
  BufferedReader br = new BufferedReader(new InputStreamReader(
    System.in));
  String line = null;
  int testCase = 0;
//  System.out.println(br.readLine());
  if ((line = br.readLine()) != null) {
   testCase = Integer.parseInt(line);
  }
  //int[][] a , b , c ;
  for (int i = 0; i < testCase; i++) 
   System.out.println("2");
  
 }catch (Exception e) {
   // TODO: handle exception
  }
 
}
}