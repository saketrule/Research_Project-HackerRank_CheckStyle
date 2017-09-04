import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class House implements Comparable<House>
{
    int area;
    int price;
    int used=0;
    
    House(int a, int p){
        this.area = a;
        this.price = p;
    }
    
    public int compareTo(House h){
        if(this.area == h.area){
            return this.price-h.price;
        }
        return h.area-this.area;
    }
        
}

class Client implements Comparable<Client>
{
    int area;
    int price;
    
    Client(int a , int p){
        this.area = a;
        this.price = p;
    }
    
    public int compareTo(Client c){
        if(this.area == c.area){
            return this.price-c.price;
        }
        return c.area-this.area;
    }
}

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan=new Scanner(System.in);
        int n=scan.nextInt();
        int m=scan.nextInt();
        
        House[] h = new House[m];
        Client[] c = new Client[n];
        
        for(int i=0;i<n;i++)
            c[i] = new Client(scan.nextInt(),scan.nextInt());
        
        for(int i=0;i<m;i++)
            h[i] = new House(scan.nextInt(),scan.nextInt());
        
        Arrays.sort(h);
        Arrays.sort(c);
        
        int i,j,sum=0,max=0;
        for(i=0;i<n;i++){
            max=-1;
            for(j=0;j<m;j++){
                if(h[j].area <= c[i].area){
                    if(max != -1){
                        h[max].used=1;
                        sum++;
//                        System.out.println(max);
                    }
                    break;
                }
                if(h[j].used==0 && h[j].price <= c[i].price){
                    if(max==-1)
                        max=j;
                    else if(h[j].price > h[max].price)
                        max=j;
                }
            }
            if(j==m && max != -1){
                h[max].used=1;
                sum++;
//                System.out.println(max);
            }
        }
        
        System.out.println(sum);
        
        
        /*
        int[] a=new int[n];
         int[] p=new int[n];
         int[] x=new int[n];
         int[] y=new int[n];
        for(int i=0;i<n;i++)
        {
            a[i]=scan.nextInt();
            p[i]=scan.nextInt();
        }
        for(int i=0;i<m;i++)
        {
            x[i]=scan.nextInt();
            y[i]=scan.nextInt();
        }
        */
        
    }
}