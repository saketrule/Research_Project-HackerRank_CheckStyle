import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static class Numbera implements Comparable<Numbera>{
        int factorsnon2 = 0;
        int factors2 = 0;
        Numbera(){}
        Numbera(int a, int b){
            factorsnon2 = a;
            factors2 = b;
        }
        public int compareTo(Numbera n){
            if( this.factorsnon2 < n.factorsnon2 ) return -1;
            if( this.factorsnon2 > n.factorsnon2 ) return 1;
            if( this.factors2 < n.factors2 ) return -1;
            if( this.factors2 > n.factors2 ) return 1;
            return 0;
        }
    }
    public static void main(String[] args) {
        
        int g[] = new int[100000+1];
        g[0]=0;
        g[1]=0;
        g[2]=1;
        g[3]=1;
        /*g[4]=2;
        g[5]=1;
        g[6]=2;
        g[7]=1;
        g[8]=2;*/
        int gg[] = new int[g.length];
        int ggi = 0;
        for( int i = 4; i < g.length; i++){
            int t = 2;
            ggi=0;
            //gg[ggi++]=g[2];//add ·1
            gg[ggi++]=g[1];//add ·0??
            //int sq = (int)Math.sqrt(i)+1;
            //System.out.println(sq);
            //while( t < sq ){
            boolean isprime=true;
            while( t*t <= i ){
                if( i%t==0 ){
                    isprime=false;
                    if( i/t%2==1){
                        gg[ggi++]=g[t];
                    }else{
                        gg[ggi++]=0;
                    }
                    if( t%2==1){
                        gg[ggi++]=g[i/t];
                    }else{
                        gg[ggi++]=0;
                    }
                }
                t++;
            }
            //if(isprime){g[i]=0;continue;}
            Arrays.sort(gg,0,ggi);

                int minmissing = -1;
                int last = -1;
                for( int j = 0; j < ggi; j++ ){
                    minmissing = last;
                    last = gg[j];
                    if( last - minmissing > 1){
                        break;
                    }
                }
                if( last - minmissing < 2 ){
                    g[i] = last+1;
                }else{
                    g[i] = minmissing+1;
                }
        }
      /*  int yy = 0;
        for( int i : g){
            System.out.print("("+yy+":"+i+")");
            yy++;
        }
        if( 1==1)return;*/
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        
        for( int t_i= 0; t_i < t; t_i++){
            int n = in.nextInt();
            
       //     Numbera a[] = new Numbera[n];
            int a[] = new int[n];
            int numberones = 0;
            for( int i = 0; i < n; i++ ){
                int x = in.nextInt();
                
                if( x == 1 ){
               //     a[i] = new Numbera(Integer.MAX_VALUE,0);
                    a[i] = Integer.MAX_VALUE;
                    numberones++;
                    continue;
                }
                a[i]=x;
                
                /*Numbera na = new Numbera();
                
                int xf= 0;
                int b = 2;
                while( x%2 == 0){
                    xf++;
                    x/=2;
                }
                na.factors2 = xf;
                
                xf =0;
                b=1;
                while( x>1){
                    b+=2;
                    if( x%b == 0){
                        xf++;
                        x/=b;
                        b-=2;
                    }
                }
                na.factorsnon2 = xf;
               a[i] = na;
            //    a[i] = xf;
            */
            }
            n-= numberones;
            Arrays.sort(a);
         /*   for( Numbera z : a ){
                System.out.println(z.factors2+"/"+z.factorsnon2);
            }*/
            
            if( n < 1 ){
                System.out.println("2");
                continue;
            }
            if( n == 1 ){
                System.out.println("1");
                continue;
            }
            
            int numberAllOdd = 0;
            int numberAllPrimeOrEven = 0;
            int numberMixed = 0;
            int z = 0;
            for( int j = 0; j < n; j++ ){
                //System.out.println("("+a[j]+":"+g[a[j]]+")");
                z ^= g[a[j]];
                //Numbera te = a[j];
                /*int zz =0;
                if( te.factors2 == 0 ){
                    zz= 0;
                    if( te.factorsnon2 == 0){
                        //
                    /*}else if(te.factorsnon2==1){
                        //+1
                        zz++;
                    }else if(te.factorsnon2==1){
                        //·0
                        z=0;*/
/*                    }else{
                        //·(n-1)
                        zz+=te.factorsnon2;
                    }
                }else if( te.factors2 == 1 ){
                    zz= 1;
                    if( te.factorsnon2 == 0){
                        //
                    }else{
                        zz+=te.factorsnon2;
                    }
                }else{
                    zz = 2;//doesnt go above 2
                    if( te.factorsnon2 == 0){
                        //
                    }else{
                        zz+=te.factorsnon2;
                    }
                }
                
                z ^= zz;*/
            }
            /*z^=numberAllOdd%2;
            z^=numberMixed%2;*/
            if( z > 0 ){
                System.out.println("1");
            }else{
                System.out.println("2");
            }
            
            //allodds can let p1 or p2 win
            //all even make p1 win
            //mixed may or not flip based on whos turn and what he needs, as such to counter a mixed you leave alive you need another mixed or an odd
           /* if( numberAllOdd + numberMixed > 0 ){
                if( (numberAllOdd + numberMixed)%2 == 1 ){
                    System.out.println("1");
                } else {
                    System.out.println("2");
                }
            }else{
                if( numberAllPrimeOrEven%2 ==0  ){
                    System.out.println("2");
                }
                else{
                    System.out.println("1");
                }
            }*/
                /*
            System.out.println(numberAllEven+" " + numberAllOdd+ " " + numberMixed);
            if( ( numberAllEven%2 + numberAllOdd + numberMixed)%2 ==0  ){
                System.out.println("2");
            }else{
                System.out.println("1");
            }*/
            
        }
    }
}