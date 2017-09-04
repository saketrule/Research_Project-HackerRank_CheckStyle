import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        //int sqr[10000][100], n[100];
        int[][] sqr = new int[10000][100];
        int[] n= new int[10000];
        int T=0;
        int cnt1=0, cnt2=0;
        int tcoin = 0, result = 0;
        Scanner in = new Scanner(System.in);
        //Scanner in = new Scanner(new File("C:\\testdata\\testdata.txt"));
        //FileWriter out = new FileWriter("C:\\testdata\\abc.txt");
        T = in.nextInt();
        //System.out.println(T);
        
        //cin >> T;
        for (cnt1=0; cnt1<T; cnt1++)
        {
         //cin >> n[cnt1];
         n[cnt1] = in.nextInt();
         for(cnt2=0; cnt2<n[cnt1]; cnt2++)
         {
          sqr[cnt1][cnt2] = in.nextInt();             
         }
        }
    
        for (cnt1=0; cnt1<T; cnt1++)
        {
         for(cnt2=0; cnt2<n[cnt1]; cnt2++)
         {
          if(sqr[cnt1][cnt2]%2 == 1)
           tcoin = tcoin ^ cnt2;             
         }
        
        //result = tcoin%2;
        
         if (tcoin == 0)
         {
          //out.write("Second");
          System.out.println("Second");
          //cout << "First" << endl;
                
         }
         else
         { 
          //out.write("First");
          System.out.println("First");
          //cout << "Second" << endl;
         }
         tcoin = 0;
        //result = 0;
        }
        
        //in.close();
        //out.close();
        
        
        
     
}
}