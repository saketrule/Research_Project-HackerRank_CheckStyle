import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int i=0;i<t;i++)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();
            boolean n_EO,list_EO;
            
            if(m==1){   System.out.println("2");    }
            else
            {    if(n%2==0){ n_EO=true;   }
                else{  n_EO=false;   }

                int x=2,cnt;
                LinkedList list = new LinkedList();
                cnt=1;
                while(x <= m/2)
                {
                    if(m%x == 0){   cnt++; }
                    x++;
                }
                if(cnt%2 == 0){  list_EO=true; }
                else{  list_EO=false; }

                if((n_EO==false && list_EO==true) || (n_EO==false && list_EO==false))
                {
                    System.out.println("1");
                }else{  System.out.println("2");    }
            }
        }    
    }
}