import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Buyer {
    long area= 0;
    long price = 0;
    boolean selected =false;
}

class House {
    long area=0;
    long price=0;
    boolean selected=false;;
}

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int no_house,no_buyer;
        int count=0;
        int count_b=0;
        int count_h=0;
        Scanner in = new Scanner(System.in);
        no_buyer = in.nextInt();
        no_house = in.nextInt();
        
        Buyer[] b = new Buyer[no_buyer];
        House[] h = new House[no_house];
        
        for(int i=0;i<no_buyer;i++) {
            b[i] = new Buyer();
            b[i].area = in.nextLong();
            b[i].price = in.nextLong();
        }
        for(int i=0;i<no_house;i++) {
            h[i] = new House();
            h[i].area = in.nextLong();
            h[i].price = in.nextLong();
        }
        
        for(int i=0;i<no_buyer;i++) {
            for(int j=0;j<no_house;j++) {
                if((b[i].area<=h[j].area)&&(b[i].price>=h[j].price))    {
                    h[j].selected=true;
                    b[i].selected=true;
                }
            }
        }
        
        for(int j=0;j<no_house;j++) {
                if(h[j].selected==true)    {
                    count_h++;
                }
            }
        for(int j=0;j<no_buyer;j++) {
                if(b[j].selected==true)    {
                    count_b++;
                }
            }
        
        if(count_b<count_h)
            count=count_b;
        else
            count=count_h;
        
        System.out.println(count);
    }   
}