import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
class House
    {
        int area;
        int price;
        public House(int area,int price){this.area = area;this.price=price;}
        @Override
        public boolean equals(Object o)
            {
                if(this==o){return true;}
                if(o==null){return false;}
                if(this.getClass()!=o.getClass()){return false;}
                House h = (House)o;
                if(this.area<h.area&&this.price>=h.price)
                    {
                        return true;
                }
                return false;
        }
        @Override
        public String toString()
            {
                return "area : "+this.area+"price : "+this.price;
        }
}
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //clients
        int m = sc.nextInt(); //houses
        ArrayList<House> clients = new ArrayList<House>();
        for(int i=0;i<n;i++)
            {
                clients.add(new House(sc.nextInt(),sc.nextInt()));
        }
        ArrayList<House> houseList = new ArrayList<House>();
        for(int i=0;i<n;i++)
            {
                houseList.add(new House(sc.nextInt(),sc.nextInt()));
        }
        int max = Integer.MIN_VALUE;
        for(House client : clients)
            {
                int count = 0;
                for(House h : houseList)
                    {
                        if(client.equals(h))
                            {
                                count++;
                        }
                }
                max = count>max?count:max;
        }
        System.out.println(max);
    }
}