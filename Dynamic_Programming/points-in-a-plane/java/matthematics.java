/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/

import java.io.*;
import java.util.*;
import java.math.*;

class Rational {
    public int N;
    public int D;
    
    public boolean equals(Object o)
    {
        Rational r=(Rational)o;
        
        return N*r.D==D*r.N;
    }
}

class Line {
    public int A;
    public int B;
    public int C;
    //Ax + By = C
    
    public boolean contains(int x, int y)
    {
        return A*x+B*y==C;
    }
    
    public boolean equals(Object o)
    {
        Line L=(Line)o;
        
        if(B*L.A!=A*L.B)
            return false;
        if(C*L.B!=B*L.C)
            return false;
        if(C*L.A!=A*L.C)
            return false;
        return true;
    }
    
    public static Line fromPoints(int x0, int y0, int x1, int y1)
    {
        Line ret=new Line();
        ret.A=-(y1-y0);
        ret.B=x1-x0;
        ret.C=(x1-x0)*y0-(y1-y0)*x0;
        return ret;
    }
    
    public int numContains(Vector<Integer> x, Vector<Integer> y)
    {
        int count=0;
        for(int i=0;i<x.size();i++)
        {
            if(contains(x.get(i).intValue(),y.get(i).intValue()))
               count++;
        }
        return count;
    }
    
    public int hashCode()
    {
        return A*B+B*C+A+C;
    }
    
    public Set<Integer> whichContains(Vector<Integer> vx, Vector<Integer> vy)
    {
        Set<Integer> points=Collections.synchronizedSet(new HashSet());
        
        try{
                synchronized(vx){
                    synchronized(vy) {
                        for(int i=0;i<vx.size();i++)
                        {
                            if(contains(vx.get(i).intValue(),vy.get(i).intValue()))
                            {
                                try{
                                    synchronized(points){
                                points.add(new Integer(i));
                                    }
                                }catch(Exception e){}
                            }
                        }
                    }
                
            }
        }catch(Exception e) {}
        return points;
    }
        
    public String toString()
    {
        return ""+A+"x + "+B+"y = "+C;
    }
}

public class Solution {
    public static void main(String args[]) throws Exception
    {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
       
        int T=Integer.parseInt(in.readLine());
        
        try{
        for(int i=0;i<T;i++)
        {
          int N=Integer.parseInt(in.readLine());
           int x[]=new int[N], y[]=new int[N];
           Vector<Integer> vx=new Vector(),vy=new Vector();
            
           Set<Line> lines=Collections.synchronizedSet(new HashSet());
            
           for(int j=0;j<N;j++)
           {
               String line=in.readLine();
               x[j]=Integer.parseInt(line.substring(0,line.indexOf(" ")));
               y[j]=Integer.parseInt(line.substring(line.indexOf(" ")+1));
               try{
                   synchronized(vx){
                       synchronized(vy) {
               vx.add(new Integer(x[j]));
               vy.add(new Integer(y[j]));
                       }
                   }
               }catch(Exception e){}
           }
           for(int j=0;j<N;j++)
           {
                   for(int k=j+1;k<N;k++)
                   {
                       try{
                           synchronized(lines) {
                               lines.add(Line.fromPoints(x[j],y[j],x[k],y[k]));
                           }
                       }catch(Exception e) {}
                   }
           }
            int count=0;
            
            while(vx.size()!=0)
            {
                Line max=new Line();
                int nmax=-1;
                try{
                    synchronized(lines) {
                        for(Line L:lines)
                        {
                            try{
                                synchronized(vx) {
                                    try{
                                        synchronized(vy) {
                                            if(L.numContains(vx,vy)>Math.max(nmax,0))
                                            {
                                                max=L;
                                                nmax=L.numContains(vx,vy);
                                            }
                                        }
                                    }catch(Exception e) {}
                                }
                            } catch(Exception e) {}
                        }
                    }
                } catch(Exception e) {}
                count++;
                
                try{
                    synchronized(vx) {
                        synchronized(vy) {
                            boolean found=true;
                            while(found)
                            {
                                found=false;
                                for(int j=0;j<vx.size();j++)
                                {
                                    if(max.contains(vx.get(j).intValue(),vy.get(j).intValue()))
                                    {
                                        vx.remove(j);
                                        vy.remove(j);
                                        found=true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }catch(Exception e) { System.out.println("fasoibn "+e); }
            }
            //now we have count
            try{
            synchronized(vx) {
                synchronized(vy){
                    vx=new Vector();
            vy=new Vector();
            
            for(int p=0;p<N;p++)
            {
                vx.add(new Integer(x[p]));
                vy.add(new Integer(y[p]));
            }
            System.out.println(count+" "+waysToRemove(lines,vx,vy,count).mod(new BigInteger("1000000007")));
                }
            }
        }catch(Exception e){System.out.println(e+" agsioh"); }
    }
}catch(Throwable e) { System.out.println(e+" is what happened");  }
    }
    //number of ways to remove n lines to get rid of all points                           

    public static Set<Set<Integer>> nonEmptySubsets(Set<Integer> s)
    {
        Set<Set<Integer>> ret=Collections.synchronizedSet(new HashSet());
        
        try{
        synchronized(ret){
        if(s.size()==1)
        {
            ret.add(s);
        }
        else if(s.size()==0)
        {
            return ret;
        }
        else
        {
            Integer firstElement=(Integer)s.toArray()[0];
            Set<Integer> one=Collections.synchronizedSet(new HashSet());
            
            synchronized(one){
                one.add(firstElement);
                ret.add(one);
            }
            Set<Integer> smaller=Collections.synchronizedSet(new HashSet());
            synchronized(smaller){
            smaller.addAll(s);
            smaller.remove(firstElement);
            
            Set<Set<Integer>> ret2=nonEmptySubsets(smaller);
                synchronized(ret2){
                    for(Set<Integer> s2:ret2)
                    {
                        ret.add(s2);
                        Set<Integer> ns2=Collections.synchronizedSet(new HashSet());
                        synchronized(ns2){
                        ns2.addAll(s2);
                        ns2.add(firstElement);
                        ret.add(ns2);
                        }
                    }
                }
                }
        }
        }
        }catch(Exception e){}
        return ret;
    }

    public static BigInteger waysToRemove(Set<Line> lines, Vector<Integer> vx, Vector<Integer> vy, int n)
    {
        
        BigInteger sum=new BigInteger("0");
        
        if(vx.size()==0&&n==0)
            return new BigInteger("1");
        
        try{
        
                
        Set<Set<Integer>> pointSets=Collections.synchronizedSet(new HashSet());
            try{
                synchronized(lines) {
                for(Line L:lines)
                {
                    try{
                        synchronized(pointSets){
                    synchronized(vx){
                        synchronized(vy) {
                            Set<Integer> s=L.whichContains(vx,vy);
                            if(s.size()>0)
                            {
                                //System.out.println(L+" contains "+s.size()+" out of "+vx.size());
                                pointSets.addAll(nonEmptySubsets(s));
                            }
                        }
                    }
                }
                }catch(Exception e){}
                }                
            
        }
        }catch(Exception e){}
            try{
            synchronized(pointSets) {
                for(Set<Integer> s:pointSets)
                {
                    Vector<Integer> vx2=new Vector(),vy2=new Vector();
                    try{
                    synchronized(vx2){
                        synchronized(vy2){
                            synchronized(s){
                                synchronized(vx){
                                    synchronized(vy){
                                        for(int index=0;index<vx.size();index++)
                                        {
                                            if(!s.contains(new Integer(index)))
                                            {
                                                vx2.add(vx.get(index));
                                                vy2.add(vy.get(index));
                                            }
                                        }
                                        if(vx2.size()!=vx.size()-s.size())
                                            System.out.println("Whoa");
                                        sum=sum.add(waysToRemove(lines,vx2,vy2,n-1));
                                        //System.out.println("Adding ");
                                    }
                                }
                               
                            }
                        }
                    }
                }catch(Exception e){ System.out.println(e+"afspoj");}
                }
        
            }
    }catch(Exception e){}
        } catch(Exception e) { System.out.println(e+" asf"); }
        
    
        return sum;
    }                          
}