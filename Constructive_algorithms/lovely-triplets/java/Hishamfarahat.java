import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {


    static void q2(int p){
        ArrayList<Integer> comb = new ArrayList<Integer>();
        int n = 3;
        while((n*(n-1)*(n-2))/6 <p){
            comb.add((n*(n-1)*(n-2))/6);
            n++;
        }
        // for(int x:comb)
        //    System.out.println(x);
        ArrayList<Integer> stars = new ArrayList<Integer>();
        int r =p;
        int sum=0;
        while(r>=1){
            int l = 0;
            int nn=3;
            for(int i=0;i<comb.size();i++){
                if(comb.get(i)>r)
                {
                    l = comb.get(i-1);
                    // nn++;
                    break;
                }
                nn++;
            }
            nn--;
            if(l==0)
                l = comb.get(comb.size()-1);
            stars.add(nn);
            sum+=(nn+1);
            r = r-l;
            //System.out.println("Combi: "+ l+" size="+(nn)+" r= "+r);

        }
        System.out.println((5*r+sum) +" "+(5*r+sum-stars.size()));
        // f(2,r); 
        int index = 5*r;

        for(int x: stars){
            int cen = ++index;
            for(int i=0;i<x;i++){
                edge(cen,++index);
            } 
        }


    }


    static void edge(int n1,int n2){
        System.out.println((n1)+" "+(n2));
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int P = in.nextInt();
        int Q = in.nextInt();


        if(Q==2){
            q2(P);
            return;
        }
        func(Q,P);
    }

    static void printEdges(int x,int y,int z, int q, int index, int core,int coreIndex){
        int k = index;
        int c = 3*q-6;
        for(int i=0;i<x;i++){
            edge(core+coreIndex,index);
            index = index + 1;
        }
        for(int i=0;i<y;i++){
            edge(q-1+core-1+coreIndex,index);
            index = index + 1;
        }
        for(int i=0;i<z;i++){
            edge(2*q-3+core-1+coreIndex,index);
            index = index + 1;
        }

    }
    static void printCore(int q,int index){
        int n = 3*q-6;
        for(int i=1;i<n;i++){
            edge(index-1 + i,index-1 +i+1);
        }
        edge(index-1 +n,index-1 +1);
    }
    static Triplet getClosest(TreeMap<Integer,Triplet> map, int r){
        int prev = 0;
        for(int x: map.keySet()){
            //System.out.println(x);
            if(x>r){
                break;
            }
            if(x==r)
            {
                prev = x;
                break;
            }
            prev = x;
        }
        return map.get(prev);
    }
    static void func(int Q,int P){
        int x=1,y=1,z=1;
        int c = 3*Q-6;
        TreeMap<Integer,Triplet> map = new TreeMap<Integer,Triplet>();
        while(x+y+z+c<=100){
            while(x+y+z+c<=100){
                while(x+y+z+c<=100){
                    if(x*y*z<=P){ 
                        Triplet t = map.get(x*y*z);
                        if(t!=null && t.sum()>x+y+z)
                            map.put(x*y*z,new Triplet(x,y,z));
                        else if (t==null)
                            map.put(x*y*z,new Triplet(x,y,z));
                    }
                    z++;
                }
                z=1;
                y++;
            }
            y=1;
            x++;
        }

        int r = P;
        ArrayList<Triplet> ts = new ArrayList<Triplet>();
        int sum = 0;
        int ctr2=0;
        while(r>=1){
            Triplet t = getClosest(map,r);
            ts.add(t);
            r = r-t.product();
                //  System.out.println(t+" r="+r);

            sum+=t.sum();
            
            if(ctr2%(Q-2)==0)
                sum+=c;
            
            if(sum>100)
            {
                int x2=1;
                while(x2>0)
                    System.out.println(t+" r="+r);
            }
            ctr2++;
        }
        edge(sum,sum);
        int index =1;
        int ctr =0;
        for(Triplet t: ts){
            if(ctr%(Q-2)==0){
                printCore(Q,index);
                index +=c;
            }
            printEdges(t.x,t.y,t.z,Q,index,ctr/(Q-2)*(index-c-1)+1,ctr%(Q-2)); 
            index += t.sum();
            ctr++;
        }
    }
}
class Triplet{
    int x,y,z;
    public Triplet(int a,int b, int c){
        x = a;
        y =b;
        z = c;
    }
    public int product(){
        return x*y*z;
    }
    public int sum(){
        return x+y+z;
    }
    public String toString(){
        return x+" "+y+" "+z+" "+product();
    }
}