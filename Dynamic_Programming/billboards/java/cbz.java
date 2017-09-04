/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
import java.util.Scanner;
public class Solution {
 public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
  int N = scanner.nextInt(), K = scanner.nextInt();
  int[] profit_input = new int[N];
  Node[] BillBoards = new Node[N];
  long[] helper = new long[N];
  helper[0] = 0;
  for (int i=0; i<N; i++) profit_input[i] = scanner.nextInt();
  helper[0] = profit_input[0];
  for (int i=1; i<N; i++) helper[i] = helper[i-1] + profit_input[i];
  for (int i=0; i<N; i++) BillBoards[i] = new Node();
  //Base case
  BillBoards[0].in = profit_input[0];
  BillBoards[0].out = 0;
  long previous = 1;
  for (int i=1; i<N; i++){
   //calculate out
   BillBoards[i].out = (BillBoards[i-1].in > BillBoards[i-1].out ? BillBoards[i-1].in : BillBoards[i-1].out);
   //calculate in
   if (previous + 1 <= K){
    if (BillBoards[i-1].out < BillBoards[i-1].in){
     BillBoards[i].in = profit_input[i] + BillBoards[i-1].in;
     previous ++; 
    }else{
     BillBoards[i].in = profit_input[i] + BillBoards[i-1].out;
     previous = 1;
    }
   }else{
    int start_point = i < K ? 0 : i-K+1;
    long max, sum = (i < K ? helper[i] : (helper[i]-helper[start_point-1]));
    max = i < K ? sum : (sum + BillBoards[start_point-1].out);
    previous = K;
    for (int j = start_point+1; j <= i; j++){
     sum -= profit_input[j-1];
     if (sum+BillBoards[j-1].out > max){
      max = sum+BillBoards[j-1].out;
      previous = i-j+1;
     }
    }
    BillBoards[i].in = max;
   }
  }
  if (BillBoards[N-1].in > BillBoards[N-1].out) System.out.println(BillBoards[N-1].in);
  else System.out.println(BillBoards[N-1].out);
 }
}

class Node{
 long in, out;
}