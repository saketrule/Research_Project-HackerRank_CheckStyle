import java.util.Scanner;


public class Solution
{
 static long modVal = 1000000007;
 public static void main(String[] args)
 {
  Scanner in = new Scanner(System.in);
  
  int numCases = in.nextInt();
  long[] answers = new long[numCases];
  for(int i = 0; i < numCases; i ++)
  {
   long total = 1;
   int numColors = in.nextInt();
   int[] colors = new int[numColors];
   for(int j = 0; j < numColors; j ++)
   {
    colors[j] = in.nextInt();
    long partialPower = 1;
    for(int k = 0; k < colors[j]-2; k ++)
    {
     partialPower = (partialPower * colors[j])%modVal;
    }
    total = (total * partialPower)%modVal;
   }
   if(numColors == 1)
   {
    answers[i] = total;
   }
   else
   {
    for(int j = 0; j < numColors; j ++)
    {
     total = (total * colors[j])%modVal;
    }
    answers[i] = (total* getSum(colors)) % modVal;
   }
  }
  for(int i = 0; i < numCases; i ++)
  {
   System.out.println(answers[i]);
  }
 }
 
 public static long getSum(int[] colors)
 {
  long sum = 0;
  for(int i = 0; i < colors.length; i ++)
  {
   sum += colors[i];
  }
  long finalSum = 1;
  for(int i = 0; i < colors.length-2;i++)
  {
   finalSum = (finalSum * sum)%modVal;
  }
  return finalSum;
 }
}