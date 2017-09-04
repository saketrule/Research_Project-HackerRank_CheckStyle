/* Enter your code here. Read input from STDIN. Print output to STDOUT */import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class Solution {
 
 long[] maxValueTable;
 int k;
 long[] latestKValues;
 int posiInStreak = 0;
 
 public void createMaxValueHeper(long x, int index)
 {
  if(index == 0)
  {
   maxValueTable[0] = x;
   latestKValues[0] = x;
   return;
  }
  
  if(posiInStreak + 1  < k)
  {
   maxValueTable[index] = maxValueTable[index-1] + x;
   posiInStreak++;
   latestKValues[index%k] = x;
  }
  else
  {
   long maxValue = maxValueTable[index - 1];
   int maxIndex = 0;
   long streakSum = 0;

   long tmpValue;
   for(int i = 1; i <= k; i++)
   {
    if(index - i - 1 < 0)
    {
     tmpValue = x + streakSum;
    }
    else
    {
     tmpValue = maxValueTable[index - i - 1] + x + streakSum;
    }
    if(tmpValue > maxValue)
    {
     maxValue = tmpValue;
     maxIndex = i;
    }
    
    streakSum += latestKValues[(index - i)%k];
   }
   
   maxValueTable[index] = maxValue;
   if(maxIndex == 0)
   {
    posiInStreak = 1;
   }
   else
   {
    posiInStreak = maxIndex;
   }  
  }
  
  latestKValues[index%k] = x;
 }
 
 
 public void createMaxValue(BufferedReader br)
 {
  try {
   String strLine = br.readLine();
   String[] strSplit= strLine.split(" ");
   int numLine = Integer.valueOf(strSplit[0]);
   k = Integer.valueOf(strSplit[1]);
   latestKValues = new long[k];
   maxValueTable = new long[numLine + 2];
   long v;
   for(int i = 0; i < numLine ; i++)
   {
    strLine = br.readLine();
    v = Long.valueOf(strLine);
    createMaxValueHeper(v, i);
   }
   System.out.print(maxValueTable[numLine - 1]);   
   
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  
 }
 
 public static void main(String[] args)
 {
  Solution s = new Solution();
  FileInputStream fstream;
  try {
 //  fstream = new FileInputStream("d:\\input00.txt");
 //  DataInputStream in = new DataInputStream(fstream);
   InputStreamReader in = new InputStreamReader(System.in);
 //  BufferedReader br = new BufferedReader(new InputStreamReader(in)); 
   BufferedReader br = new BufferedReader(in); 
     s.createMaxValue(br);
  } catch (Exception e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  
  
 }
 

}