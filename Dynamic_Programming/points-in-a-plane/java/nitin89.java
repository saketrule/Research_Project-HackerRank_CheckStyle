/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Solution {

 public Solution() {
  // TODO Auto-generated constructor stub
  Scanner sc=new Scanner(System.in);
  int T=sc.nextInt();
  for(int i=0;i<T;i++)
  {
   int N=sc.nextInt();
   ArrayList<Point> points=new ArrayList<Point>();
   for(int j=0;j<N;j++)
   {
    int x=sc.nextInt();
    int y=sc.nextInt();
    points.add(new Point(x,y));
   }
   ArrayList<Point> tempPoints=new ArrayList<Point>(points);
   int count=0;
   while(tempPoints.size()>0)
   {
    Point p=tempPoints.get(0);
    tempPoints.remove(0);
    ArrayList<Point> maxPoints=p.findMax(tempPoints);
    tempPoints.removeAll(maxPoints);
    count++;
   }
   System.out.println(count+" "+(points.size()*count));
  }
  
 }

 /**
  * @param args
  */
 public static void main(String[] args) {
  // TODO Auto-generated method stub
  Solution s=new Solution();
 }


 public class Point
 {
  int x,y;
  public Point(int x,int y)
  {
   this.x=x;
   this.y=y;
  }

  public ArrayList<Point> findMax(ArrayList<Point> input)
  {
   ArrayList<Point> tempPoints1=inLine1(input);
   ArrayList<Point> tempPoints2=inLine2(input);
   ArrayList<Point> tempPoints3=inLine3(input);
   ArrayList<Point> tempPoints4=inLine4(input);
   ArrayList<Integer> siz=new ArrayList<Integer>();
   siz.add(tempPoints1.size());
   siz.add(tempPoints2.size());
   siz.add(tempPoints3.size());
   siz.add(tempPoints4.size());
   Integer max=Collections.max(siz);
   max=new Integer(siz.indexOf(max));
   if(max.intValue()==0)//System.out.println("1="+max.intValue());
    return tempPoints1;
   if(max.intValue()==1)//System.out.println("2="+max.intValue());
    return tempPoints2;
   if(max.intValue()==2)//System.out.println("3="+max.intValue());
    return tempPoints3;
   else //System.out.println("4="+max.intValue());
    return tempPoints4;

  }

  public ArrayList<Point> inLine1(ArrayList<Point> input)
  {
   ArrayList<Point> output=new ArrayList<Point>();
   for(int i=0;i<input.size();i++)
   {
    if(input.get(i).x==x)
    {
     output.add(input.get(i));
    }
   }
   return output;
  }
  public ArrayList<Point> inLine2(ArrayList<Point> input)
  {
   ArrayList<Point> output=new ArrayList<Point>();
   for(int i=0;i<input.size();i++)
   {
    if(input.get(i).y==y)
    {
     output.add(input.get(i));
    }
   }
   return output;
  }
  public ArrayList<Point> inLine3(ArrayList<Point> input)
  {
   ArrayList<Point> output=new ArrayList<Point>();
   for(int i=0;i<input.size();i++)
   {
    if((input.get(i).x+input.get(i).y)==(x+y))
    {
     output.add(input.get(i));
    }
   }
   return output;
  }
  public ArrayList<Point> inLine4(ArrayList<Point> input)
  {
   ArrayList<Point> output=new ArrayList<Point>();
   for(int i=0;i<input.size();i++)
   {
    if((input.get(i).x-input.get(i).y)==(x-y))
    {
     output.add(input.get(i));
    }
   }
   return output;
  }

 }

}