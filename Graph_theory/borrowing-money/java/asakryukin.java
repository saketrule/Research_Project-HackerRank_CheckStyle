import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in=new Scanner(System.in);
        
        int houses=in.nextInt();
        int roads=in.nextInt();
        
        int[] money=new int[houses];
        
        for(int i=0;i<houses;i++){
            money[i]=in.nextInt();
        }
        
        List<List<Integer>> adj=new ArrayList<List<Integer>>();
        
        for(int i=0;i<houses;i++){
            List<Integer> t=new ArrayList<Integer>();
            
            t.add(i);
            adj.add(t);
            
        }
        
        for(int i=0;i<roads;i++){
            int a,b;
            a=in.nextInt();
            b=in.nextInt();
            
            adj.get(a-1).add(b-1);
            adj.get(b-1).add(a-1);
            
        }
        
        List<Integer> comb;
        List<List<Integer>> sol=new ArrayList<List<Integer>>();
        comb=findCombinations(adj,money,sol);
        
        int max=0;
        long cmb=0;
        
        List<Integer> zeros;
        for(int i=0;i<comb.size();i++){
            if(comb.get(i)>max){
                List<List<Integer>> var=new ArrayList<List<Integer>>();
                max=comb.get(i);
                cmb=1;
                zeros=findZeros(money,adj,sol.get(i));
                //System.out.print(i+":");
                //display(zeros);
                if(zeros!=null && zeros.size()>0){
                    for(int j=0;j<zeros.size();j++){
                        cmb+=zeroC(adj,sol.get(i),zeros,0,j,var);
                    }
                }
                    //System.out.println(cmb);
            }else if(comb.get(i)==max){
                List<List<Integer>> var=new ArrayList<List<Integer>>();
                cmb++;
                zeros=findZeros(money,adj,sol.get(i));
                //System.out.print(i+":");
                //display(zeros);
                if(zeros!=null && zeros.size()>0){
                    for(int j=0;j<zeros.size();j++){
                        cmb+=zeroC(adj,sol.get(i),zeros,0,j,var);
                    }
                }
                //System.out.println(cmb);
            }
            
        }
        
        System.out.println(max+" "+cmb);
    }
    
    private static List<Integer> findZeros(int[] m,List<List<Integer>> adj,List<Integer> in){
        List<Integer> list=new ArrayList<Integer>();
        for(int i=0;i<m.length;i++){
            if(m[i]==0){
                boolean f=true;
                for(int j=0;j<adj.get(i).size();j++){
                    if(in.contains(adj.get(i).get(j)))
                        {
                        f=false;
                        break;
                    }
                }
                    if(f)
                        list.add(i);
                
            }
                
        }
        return list;
    }
    
    private static int zeroC(List<List<Integer>> adj,List<Integer> nn,List<Integer> zeros,int res,int ind,List<List<Integer>> var){
        //System.out.print("1stage:");
        //display(nn);
        List<Integer> in;
        in=copyList(nn);
        
        in.add(zeros.get(ind));
        
        
            
        
        //System.out.print("2stage:");
        //display(in);
        
        for(int i=0;i<adj.get(zeros.get(ind)).size();i++){
            if(adj.get(zeros.get(ind)).get(i)>ind && !in.contains(adj.get(zeros.get(ind)).get(i)))
                in.add(adj.get(zeros.get(ind)).get(i));
        }
        
        
            
            var.add(in);
            
            res++;
        

        
        for(int i=ind;i<zeros.size();i++){
            if(!in.contains(zeros.get(i))){
                res=zeroC(adj,in,zeros,res,i,var);
            }
        }
        
        return res;
        
        
    }
    
    
    
    private static void display(List<Integer> c){
        for(int i=0;i<c.size();i++){
            System.out.print(c.get(i)+" ");
        }
        System.out.println();
    }
    
    private static List<Integer> findCombinations(List<List<Integer>> adj,int[] money,List<List<Integer>> comb){
        List<Integer> result=new ArrayList<Integer>();
        
        int ml=calcMl(money);
        
        for(int i=0;i<money.length;i++){
            int total=0;
            if(money[i]>0 ){
                result=calculateSum(adj,money,new ArrayList<Integer>(),total,i,result,ml,new ArrayList<Integer>(),comb);
                
            }
                
        }
        
        
        
        return result;
        
    }
    
    private static int calcMl(int[] m){
        int res=0;
        for(int i=0;i<m.length;i++){
            if(m[i]>0)
                res++;
        }
        
        return res;
    }
    
    private static List<Integer> copyList(List<Integer> l){
        List<Integer> res=new ArrayList<Integer>();
        
        if(l!=null){
            for(int i=0;i<l.size();i++){
                res.add(l.get(i));
                
            }
            return res;
        }else{
            return res;
        }
        
    }
    
    private static List<Integer> calculateSum(List<List<Integer>> adj,int[] money,List<Integer> nn,int total,int s,List<Integer> res,int ml,List<Integer> v,List<List<Integer>> comb){
        List<Integer> in;
        List<Integer> vis;
        vis=copyList(v);
        in=copyList(nn);
        in.add(s);
        vis.add(s);
        for(int i=0;i<adj.get(s).size();i++){
            if(adj.get(s).get(i)>s && !in.contains(adj.get(s).get(i)))
                in.add(adj.get(s).get(i));
        }
        total+=money[s];
        if(in.size()==money.length){
            
            
                res.add(total);
                comb.add(vis);
            
            
            return res;
        }else{
            boolean check=true;
            for(int i=s;i<money.length;i++){
                if(!in.contains(i) && money[i]>0){
                    check=false;
                    res=calculateSum(adj,money,in,total,i,res,ml,vis,comb);
                }
            }
            
            if(check){
                    res.add(total);
                    comb.add(vis);
            }
            return res;
        }
        
        
    }
}