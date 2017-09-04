import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/*no of ways to remove the points = no of points * turns!(factorial)      */
class Point 
{
 int x;
 int y;
 Point(int x, int y)
 {
  this.x = x;
  this.y = y;
 }
}

public class Solution {
 
 int turns, ways;
 boolean areCollinear(Point a, Point b , Point c)
 {
  int twicearea = a.x * (b.y - c.y ) - a.y *(b.x - c.x) + b.x*c.y - b.y*c.x ;
  if(twicearea == 0)
   return true;
  else
   return false;
 }
 int factorial (int n)
 {
  if(n==0)
  return 1;
  else
  return n*factorial(n-1);
 }
    public static void main(String[] args) {
  
  
      int T , N  ;
   Point[] p = new Point[16];
   Scanner in = new Scanner(System.in);
   T = in.nextInt();
 
   while(T>=1 && T <= 50)
   {
  N = in.nextInt();
  int num = N;
  
  if(N>=1 && N <=100)
  {
   
   
   
   for (int i = 0 ; i < N ; i++)
   {
    int x = in.nextInt();
   int y = in.nextInt();  
   if((x>=0 && x<=100 )&& (y>=0 && y<=100))
   {
    p[i]= new Point(x,y) ;
   }
   /*else
    System.out.println("Invalid input");*/
   /*System.out.println(p[N].x+" "+p[N].y);*/
   
 
   
   }
  
  
   Solution s = new Solution();
   
   ArrayList< ArrayList<Point>> Lines = new ArrayList<ArrayList<Point>>(16);
   if(N>3)
   {
    for(int i = 0 ; i < N ; i++)
    {
    for(int j = 1;j < N ; j++)
    {
     if(j <= i)
      continue;
     for(int k = 1 ; k < N ; k++)
     {
       if(k<=j)
       continue;
       /*System.out.println(p[i].x+" "+p[i].y +"    "+p[j].x+" "+p[j].y+"    "+p[k].x+" "+p[k].y +" coll:"+s.areCollinear(p[i],p[j],p[k]));*/
       if(s.areCollinear(p[i],p[j],p[k]))
       {
        if(Lines.isEmpty())
        {
        Lines.add(new ArrayList<Point>());
        Lines.get(Lines.size()-1).add(p[i]);
        Lines.get(Lines.size()-1).add(p[j]);
        Lines.get(Lines.size()-1).add(p[k]);
        }
        for(int a = 0 ; a < Lines.size(); a++)
        {
        if(Lines.get(a).contains(p[i])||Lines.get(a).contains(p[j])||Lines.get(a).contains(p[k]))
        {
         if(!Lines.get(a).contains(p[i]))
          Lines.get(a).add(p[i]);
         if(!Lines.get(a).contains(p[j]))
          Lines.get(a).add(p[j]);
         if(!Lines.get(a).contains(p[k]))
          Lines.get(a).add(p[k]);
        }
        }
       
       }
       
     }
    }
    }
    if(!Lines.isEmpty())
    {
    for(int i = 0; i < Lines.size(); i++)
    {
     
     N =  N - Lines.get(i).size() ;
    s.turns++;
    }
    s.turns = s.turns + N ; 
    System.out.print(s.turns%1000000007 + " " + num*s.factorial(s.turns)%1000000007);
    }
    else{
      System.out.print(N%1000000007+" "+ num*s.factorial(s.turns)%1000000007);
    }
   }
   else if(N == 3)
   {
    if(s.areCollinear(p[0],p[1],p[2]))
     System.out.println("1 1");
    else
     System.out.println("2 6");
   }
   else if(N == 2)
    System.out.println("1 1");
   else
   {
    System.out.println("1 1");
   }
   
   
  }
  T--;
   
 }
    }
}