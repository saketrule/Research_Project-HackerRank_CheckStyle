import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        int games;
        int piles;
        int count=0;
        games=sc.nextInt();
        while(games>0)
            {
            piles=sc.nextInt();
            int st[]=new int[piles];
            for(int i=0;i<piles;i++)
                {
                st[i]=sc.nextInt();
                count=count^st[i];
                }
            if(count==0)
                {
                System.out.println("Second");
                }
            else
                {
                System.out.println("First");
                }
            games--;
            count=0;
            }
                /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}