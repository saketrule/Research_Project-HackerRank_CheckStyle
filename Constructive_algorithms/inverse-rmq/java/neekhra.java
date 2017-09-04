import java.util.*;
import java.util.Map.Entry;
public class Solution
{
 public static void main(String gg[])
 {
  Scanner sc=new Scanner(System.in);
  int n=sc.nextInt();
  int[] arr=new int[2*n-1];
  HashMap<Integer,Integer> hm=new HashMap<Integer,Integer>();
  for(int i=0;i<2*n-1;i++)
  {
   arr[i]=sc.nextInt();
   if(hm.containsKey(arr[i]))
   {
    int value=hm.get(arr[i]);
    value++;
    hm.put(arr[i], value);
   }
   else
   {
    hm.put(arr[i],1);
   }
  }
  if(hm.size()==n)
  {
     ArrayList<point> al=new ArrayList<point>();
     for(Entry e:hm.entrySet())
     {
      int key=(Integer)e.getKey();
      int value=(Integer)e.getValue();
      al.add(new point(key,value));
     }
     Collections.sort(al);
     if(fill(arr,al)==1)
     {
      System.out.println("YES");
      for(int i=0;i<arr.length;i++)
      {
       System.out.print(arr[i]+" ");
      }
     }
     else
     {
      System.out.println("NO");
     }
  }
  else
  {
   System.out.println("NO");
  }
 }
 public static int fill(int[] arr,ArrayList<point> al)
 {
  for(int i=0;i<al.size();i++)
  {
   int index=2*i;
   int count=0;
   while(index<arr.length)
   {
    arr[index]=al.get(i).a;
    index=index*2+1;
    count++;
   }
   if(count>al.get(i).b) 
   {
    return 0;
   }
  }
  return 1;
 }
}
class point implements Comparable<point>
{
 int a;
 int b;
 point(int a,int b)
 {
  this.a=a;
  this.b=b;
 }
 @Override
 public int compareTo(point o) {
  if(b>o.b)return -1;
  if(b<o.b)return 1;
  else
  {
   if(a>o.a)return 1;
   if (a<o.a)return -1;
   else return 0;
  }
    
 }
}