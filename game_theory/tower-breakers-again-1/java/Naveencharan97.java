import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class towerbreak{

    public static void main(String[] args) {
         Scanner in = new Scanner(System.in);
  int t = in.nextInt();
  while(t>0){
     int n = in.nextInt();
     long count=0;
      for(int i=0;i<n;i++){
      long num=in.nextLong();
      if(num!=1)
       count++;
    }
    if(count%2==0)
     System.out.println(2);
    else
     System.out.println(1);
       t--;
  }
    }
}