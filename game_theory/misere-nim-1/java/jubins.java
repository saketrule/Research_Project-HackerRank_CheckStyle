import java.util.Scanner;

public class testsums {


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
     @SuppressWarnings("resource")
  Scanner in = new Scanner(System.in);

     int T = in.nextInt(); //Taking the number of test cases
     T = T+1;

     for(int k=1;k<T;k++){ //Starting the test cases from 1
      int n = in.nextInt(); //Taking number of piles
      int[] a = new int[n];
      int count = 0;
      int stones = 0;
      
      for(int i=0;i<n;i++){
       a[i] = in.nextInt();
      }
      
      for(int i=0;i<n;i++){
       stones = stones ^ a[i]; 
       //System.out.println("stones"+i+": "+stones);
       if(a[i] <= 1){ 
        count = count+1;
       }
      }
      //If n=2
      //p1: 1, p2: 1, count=2, stones=0 Hence p1 wins.
      //System.out.println("count"+count);
      if((count==n && stones==1) || (count<n && stones==0)){
       System.out.println("Second");
      }
      else{
       System.out.println("First");
      }
     }
    }
}