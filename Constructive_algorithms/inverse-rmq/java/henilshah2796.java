import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
 
public class Solution {
    
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] ar=new int[2*n-1];
        sc.nextLine();
        for(int i=0;i<(2*n-1);i++){
            ar[i]=sc.nextInt();
            //System.out.println("Here "+(2*n-1)+i);
        }
        //System.out.println(ar[0]);
        //int[] sortedar=new int[2*n-1];
        Arrays.sort(ar);
        //System.out.println(ar[0]);
        int[][] twod=new int[n][2];
        int current=ar[0];
        int k=0;
        twod[0][1]=ar[0];
        twod[0][0]=0;
        for(int i=0;i<2*n-1;i++){
            if(ar[i]==current){
                twod[k][0]++;
            }
            else{
                current=ar[i];
                k++;
                twod[k][0]=1;
                twod[k][1]=current;
            }
            
        }
        
        Arrays.sort(twod, new TwoDComparator());      
        int height=(int)(Math.log10(n)/Math.log10(2)) + 1;
        //System.out.println(max_height);
        LinkedList<Integer> segtree = new LinkedList<Integer>();
        LinkedList<Integer> finalSegtree=new LinkedList<Integer>();
        int flag=1;
        int counter=0, start=0;
        int temploop=0,tempnext=0;
        //segtree.add(twod[0][1]);
        //twod[0][0]--;
        if(twod[0][0]==height){
            segtree.add(twod[0][1]);
            twod[0][0]--;
        }
        else{
            System.out.println("NO");
            System.exit(0);
        }
        int loopheight=height-1;
        //coutnter++;
        for(int i=0;i<height-1;i++){
            temploop=0;
            tempnext=(int)(Math.pow(2,i));
            //System.out.println(temploop+" "+tempnext+" "+segtree.size());
            //counter++;
            for(int j=0;j<(int)(Math.pow(2,i+1)/2);j++){
                if(twod[tempnext][0]==loopheight && segtree.get(temploop)<twod[tempnext][1]){
                    segtree.add(segtree.get(temploop));
                    segtree.add(twod[tempnext][1]);
                    twod[temploop][0]--;
                    twod[tempnext][0]--;
                    temploop++;
                    tempnext++;
                    //System.out.println(temploop+" "+tempnext);
                    
                }
                else{
                    flag=0;
                    break;
                }
            }
            if(flag==0){
                break;
            }
            for(int d=0;d<(int)Math.pow(2,i);d++){
                finalSegtree.add(segtree.get(0));
                segtree.remove(0);
                
            }
            loopheight--;
        }
        
        
        
        int flag2=1;
        if(flag==0){
            System.out.println("NO");
        }
        else{
            for(int i=0;i<n;i++){
                if(twod[i][0]!=0){
                    System.out.println("NO");
                    System.exit(0);
                }
                else{
                    finalSegtree.add(segtree.get(0));
                    segtree.remove(0);
                }
            }
            System.out.println("YES");
            for(int i=0;i<2*n-1;i++){
                System.out.print(finalSegtree.get(i)+" ");
            }
        }
        //for(int i=0; i<2*n-1;i++){
            //for
            /*for(int k=0;k<start;k++){
                segtree.add(twod[k][1]);
                twod[k][0]--;
            }
            for(int j=start;j<Math.pow(2,counter);j++){
                if(twod[j][0]!=height){
                    flag=0;
                    break;
                }
                else{
                    segtree.add(twod[j][1]);
                    twod[j][0]--;
                }
            }
            start+=pow(2,counter);
            counter++;
            height--;
            //temploop=2*(segtree.size);           
            */
        //}
    }
}
 
class TwoDComparator implements Comparator<int[]>{
     @Override
            public int compare(final int[] entry1, final int[] entry2) {
                final int time1 = entry1[0];
                final int time2 = entry2[0];
                final int time3 = entry1[1];
                final int time4 = entry2[1];
                int answer=0;
                if(time1>time2)
                    answer=-1;
                else if(time1<time2)
                    answer=1;
                else if(time1==time2){
                    if(time3 > time4)
                        answer=1;
                    else
                        answer=-1;
                }
                return answer;
            }
        
}