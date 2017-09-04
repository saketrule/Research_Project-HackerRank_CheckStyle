import java.util.*;

public class Solution
{
 public static void main(String args[] ) throws Exception
 {
  Scanner in = new Scanner(System.in);
  int n = in.nextInt();
  int a[] = new int[2*n-1];
  for(int i=0; i<2*n-1; i++)
   a[i] = in.nextInt();
  if(n==1)
  {
   System.out.println("YES");
   System.out.println(a[0]);
  }
  else
  {
   int i = 2*n-2;
   while(i!=0)
   {
    int x = a[i];
    //System.out.println("x:"+x+" i:"+i);
    int y = a[i-1];
    int p = parent(i);
    int mini = i;
    if(a[mini]>y)
     mini=i-1;
    
    if(a[mini]>a[p])
     mini = p;
    if(mini!=p)
    {
     int t = a[mini];
     a[mini] = a[p];
     a[p] = t;
    }
    if(a[i-1]>a[i])
    {
     int t = a[i-1];
     a[i-1] = a[i];
     a[i] = t;
    }
    i-=2;
   }
   HashSet<Integer> set = new HashSet<Integer>();
   boolean flag = true;
   for(i= 2*n-2; i>=2*n-1-n; i--)
   {
    if(set.contains(a[i]))
    {
     flag = false;
     break;
    }
    else
    {
     set.add(a[i]);
    }
   }
   if(flag)
   {
    System.out.println("YES");
    for(i=0; i<2*n-1; i++)
     System.out.print(a[i]+" ");;
   }
   else
   {
    System.out.println("NO");
   }
   
  }
 }
 static int parent(int i)
 {
  return (i-1)/2;
 }
}