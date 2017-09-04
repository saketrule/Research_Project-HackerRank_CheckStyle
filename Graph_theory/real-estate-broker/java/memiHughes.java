import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static class Something{
  private int a;
  private int b;
  
  public Something(int a, int b){
   this.a = a;
   this.b = b;
  }
 }
 
 static class Client implements Comparable<Client>{
  private int a;
  private int b;
  private List<Something> houses;
  
  public Client(int a, int b){
   this.a = a;
   this.b = b;
   this.houses = new ArrayList<Something>();
  }

  @Override
  public int compareTo(Client c) {
  
   return ((Integer)this.houses.size()).compareTo((Integer)c.houses.size());
  }
 }
 
 public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int c = in.nextInt();
        int h = in.nextInt();
        List<Client> clients = new ArrayList<Client>();        
        for(int i=0; i < c; i++){
         clients.add(new Client(in.nextInt(), in.nextInt()));         
        }
        List<Something> houses = new ArrayList<Something>();        
        for(int i=0; i < h; i++){
         Something house =new Something(in.nextInt(), in.nextInt()); 
         houses.add(house);
         for(int j=0; j < c; j++){
          if((clients.get(j).a < houses.get(i).a) && (clients.get(j).b >= houses.get(i).b)){
           clients.get(j).houses.add(house);
          }          
         }
        }
        Collections.sort(clients);
        Set<Something> set = new HashSet<Something>();
        for(int i=0; i < c; i++){
         if(!clients.get(i).houses.isEmpty()){
          for(int hi=0; hi < clients.get(i).houses.size(); hi++){
           if (!set.contains(clients.get(i).houses.get(hi))){
            set.add(clients.get(i).houses.get(hi));
            break;
           }
          }          
         }         
        }
        System.out.println(set.size());
        
 }
}