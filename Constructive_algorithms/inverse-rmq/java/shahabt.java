import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N=sc.nextLong();
        int n=(int)N;
        if(n!=N){
            System.out.println("NO");
            return;
        }
        TreeMap<Integer, Integer> map = new TreeMap();
        int d = 31-Integer.numberOfLeadingZeros(n);
        for(int i=0;i<2*n-1;i++){
            int num=sc.nextInt();
            if(map.containsKey(num)){
                map.put(num, map.get(num)+1);
            }else{
                map.put(num, 1);
            }
        }
        if(map.size()!=n){
            System.out.println("NO");
            return;
        }
        int[] cnts= new int[n];
        int[] nums= new int[n];
        int k=0;
        List<TreeSet<Integer>> levels = new ArrayList<TreeSet<Integer>>();
        for(int i=0;i<=d;i++)
            levels.add(new TreeSet<Integer>());
        for(Map.Entry<Integer,Integer> e: map.entrySet()){
            if(e.getValue()>d+1){
                System.out.println("NO");
                return;
            }
            int l = e.getValue();
            cnts[k]=l;
            levels.get(l-1).add(k);
            nums[k++]=e.getKey();
        }
        int visited=0;
        for(int i=n-1;i>=0;i--){
            if(visited + 1 < 1 << (cnts[i]-1)){
                System.out.println("NO");
                return;
            }
            visited+=cnts[i];
        }
        int[] fixed = new int[2*n-1];
        k=0;
        fixed[k++]=0;
        //cnts[0]=0;
        for(int j=0;j<d+1;j++){
            //int i = 0;
            while(k<(1<<(j+1))-1){
                if(k%2==1){
                    fixed[k]=fixed[k/2];
                    //i=fixed[k++];
                }else{
                    if(levels.get(d-j).isEmpty()){
                        System.out.println("NO");
                        return;
                    }
                    for(int x: levels.get(d-j)){
                        if(x>fixed[k-1]){
                            fixed[k]=x;
                            break;
                        }
                    }
                    levels.get(d-j).remove(fixed[k]);
                    //cnts[i]=0;
                }
                k++;
                //if(i>=negs){
                    //i=(i+1)%n;
                  //  if(i>=n)
                    //    i=negs-1;
                //}else
                  //  i--;
            }
        }
        StringBuilder buf = new StringBuilder("YES\n");
        k=0;
        for(int i=0;i<2*n-1;i++){
            buf.append(nums[fixed[i]]).append(" ");
        }
        System.out.println(buf.toString());
    }
}