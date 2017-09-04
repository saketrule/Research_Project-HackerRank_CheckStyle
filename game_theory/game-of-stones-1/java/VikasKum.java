import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameOfStone {
 int[] arr = {1,7,8};
 final static String SEC = "Second";
 final static String FIR = "First";
 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int N = in.nextInt();
  List<String> list = new ArrayList<String>();
  while (N-- > 0) {
    list.add(solve(in.nextInt()));
  }
  for(String inp: list)
  System.out.println(inp);
 }

 private static String solve(int num) {
  // TODO Auto-generated method stub
  if(num == 1 || num%7==0 || num%7==1)
  return SEC;
  else
   return FIR;
 }

 private static String solve(int num, boolean person) {
  // TODO Auto-generated method stub
  if((num <=1 || num ==7 || num ==8 ) && person){
   return SEC;
  }else if((num <=1 || num ==7 || num ==8) && !person){
   return FIR;
  }else{
   if(num-5>0 && solve(num-5, !person).equalsIgnoreCase(FIR)){
    return FIR;
   }else if(num -3 > 0 && solve(num-3, !person).equalsIgnoreCase(FIR)){
    return FIR;
   }else if(num -2 >0 && solve(num-2, !person).equalsIgnoreCase(FIR)){
    return FIR;
   }else
   return SEC;
  }
 }
}