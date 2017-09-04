import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        List<Client> clientList=new LinkedList<Client>();
        List<House> houseList=new LinkedList<House>();
        Set<House> soldHouseList=new LinkedHashSet<House>();
        for(int i=0;i<n;i++)
        {
         clientList.add(new Client(in.nextLong(),in.nextLong()));
        }
        for(int i=0;i<m;i++)
        {
         houseList.add(new House(in.nextLong(),in.nextLong()));
        }
        
        for(Client client:clientList)
         for(House house:houseList)
         {
          if(client.getArea()<house.getArea() && client.getPrice()>=house.getPrice())
          {
           soldHouseList.add(house);
          }
         }
       
        System.out.println(soldHouseList.size());}

    
}
class House{
 long area;
 public long getArea() {
  return area;
 }
 public void setArea(long area) {
  this.area = area;
 }
 public long getPrice() {
  return price;
 }
 public void setPrice(long price) {
  this.price = price;
 }
 long price;
 House(long area,long price){
  this.area=area;
  this.price=price;
 }
}
class Client{
 long area;
 public long getArea() {
  return area;
 }
 public void setArea(long area) {
  this.area = area;
 }
 public long getPrice() {
  return price;
 }
 public void setPrice(long price) {
  this.price = price;
 }
 long price;
 Client(long area,long price){
  this.area=area;
  this.price=price;
 }
}