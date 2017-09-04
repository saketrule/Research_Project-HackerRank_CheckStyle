/* Enter your code here. Read input from STDIN. Print output to STDOUT 
Classname must be Solution
*/
import java.io.*;
import java.util.*;

public class Solution {
 public static void main(String[] args) throws IOException {
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  String input = "";
  HashMap<Integer, Integer> rows = new HashMap<Integer, Integer>();
  HashMap<Integer, Integer> cols = new HashMap<Integer, Integer>();
  int numCases;
  String str;
  while((str = br.readLine()) != null && str.length() != 0) {
   input += str + '\n';
  }
  input = input.trim();
  
  if (input.length() != 0) {
   String[] inputArray = input.split("\n");
   numCases = Integer.parseInt(inputArray[0]);
   int curr = 0;
   for (int i = 0; i < numCases; i++) {
    int turn = 1;
    int numPoints = Integer.parseInt(inputArray[++curr]);
    for (int j = 0; j < numPoints; j++) {
     String[] tmp = inputArray[++curr].split(" ");
     rows.put(j, Integer.parseInt(tmp[0]));
     cols.put(j, Integer.parseInt(tmp[1]));
    }
    for (int j = 1; j < numPoints; j++) {
     int prevRow = rows.get(j - 1);
     int currRow = rows.get(j);
     int prevCol = cols.get(j - 1);
     int currCol = cols.get(j);
     if (prevRow != currRow && prevCol != currCol) {
      turn++;
     }
    }
    System.out.println(turn + " " + numPoints*2);
   }
   
   
  }
 }
}