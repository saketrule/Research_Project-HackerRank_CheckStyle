import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sr = new Scanner(System.in);
        int  T = sr.nextInt();
        int N,min=Integer.MAX_VALUE;
        int [] skills;
        Queue<Team> minHeap=new PriorityQueue<Team>();
        for(int t = 0 ; t < T ;t++){
            N=sr.nextInt();
            if(N==0){
                System.out.println(0);
                continue;
            }
            minHeap=new PriorityQueue<Team>();
            skills=new int[N];
            for(int n =0;n<N;n++){
                skills[n]=sr.nextInt();
            }
            Arrays.sort(skills);
            min=Integer.MAX_VALUE;
            for(int n =0;n<N;n++){
                //System.out.println("skill:" +skills[n]);
                while(!minHeap.isEmpty()&&minHeap.peek().lastSkill+1<skills[n]){
                    if(min>minHeap.peek().count)min=minHeap.peek().count;
                    
                    minHeap.poll();
                }
                
                if(minHeap.isEmpty()){
                    minHeap.add(new Team(1,skills[n]));
                    
                }else{
                    if(minHeap.peek().lastSkill==skills[n]){
                        minHeap.add(new Team(1,skills[n]));
                        //System.out.println(minHeap.size()+" "+minHeap.peek().count);
                    }else if(minHeap.peek().lastSkill+1==skills[n]){
                        Team temp = minHeap.poll();
                        minHeap.add(new Team(temp.count+1,skills[n]) );
                    }
                }
                
            }
            while(!minHeap.isEmpty()){
                if(min>minHeap.peek().count)min=minHeap.peek().count;
                minHeap.poll();
            }
            System.out.println(min);
        }
    }
}
class Team implements Comparable{
    int count=1;
    int lastSkill;
    Team(int count,int firstSkill){
        this.lastSkill=firstSkill;
        this.count=count;
    }
    public int compareTo(Object team){
        if(((Team)team).lastSkill!=lastSkill){
            return lastSkill-((Team)team).lastSkill;
        }else{
            return count-((Team)team).count;
        }
    }
}