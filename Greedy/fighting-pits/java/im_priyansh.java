import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        int q = scan.nextInt();
        int[] a1,a2;
        a1= new int[n];
        a2= new int[n];
        int i=0,j=0;
        while(n>0){
            int strength = scan.nextInt();
            int team = scan.nextInt();
            if(team==1){
                a1[i]=strength;
                ++i;
            }
            else if(team==2){
                a2[j]=strength;
                ++j;
            }
            --n;
        }
        int g,m;
        for(g=0;g<i;++g){
            for(m=0;m<i;++m){
                if(a1[g]<a1[m]){
                    a1[g]+=a1[m];
                    a1[m]=a1[g]-a1[m];
                    a1[g]-=a1[m];
                }
            }
        }
        for(g=0;g<j;++g){
            for(m=0;m<j;++m){
                if(a2[g]<a2[m]){
                    a2[g]+=a2[m];
                    a2[m]=a2[g]-a2[m];
                    a2[g]-=a2[m];
                }
            }
        }
        for(g=0;g<q;++g){
            int option= scan.nextInt();
            int z= scan.nextInt();
            int r= scan.nextInt();
            if(option==1){
                if(r==1){
                    a1[i]=z;
                    ++i;
                }
                else if(r==2){
                    a2[j]=z;
                    ++j;
                }
            }
            else if(option==2){
                int g1=0,g2=0,i1=i,j1=j;
                if(z==1){
                    while(true){
                        if(i1==0){
                            System.out.println("2");
                            break;
                        }
                        else if(j1==0){
                            System.out.println("1");
                            break;
                        }
                        else{
                           if(g1==g2){
                               j1-=a1[i1-1];
                               ++g1;
                           }
                           else{
                               i1-=a2[j1-1];
                               ++g2;
                           }
                        }
                    }
                }
                else if(r==1){
                    while(true){
                        if(i1==0){
                            System.out.println("2");
                            break;
                        }
                        else if(j1==0){
                            System.out.println("1");
                            break;
                        }
                        else{
                           if(g1<g2){
                               j1-=a1[i1-1];
                               ++g1;
                           }
                           else{
                               i1-=a2[j1-1];
                               ++g2;
                           }
                        }
                    }
                }
            }
        }
    }
}