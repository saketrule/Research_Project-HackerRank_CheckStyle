import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Team implements Comparable{
        int size;
        int maxSkill;
        List<Integer> lst;
        public Team(){
            size = 0;
            maxSkill = Integer.MIN_VALUE;
            lst = new ArrayList();
        }
        
        public void setSize(int sz){
            size = sz;
        }
        public int getSize(){
            return size;
        }
        public int getMaxSkill(){
            return maxSkill;
        }
    
        public void add(int i){
            size++;
            lst.add(i);
            maxSkill = i;
        }
   
        public int compareTo(Object obj){
            Team other = (Team) obj;
            if(other.getMaxSkill() < this.getMaxSkill())
                return 1;
            else if (other.getMaxSkill() > this.getMaxSkill())
                return -1;
            else {
                if(other.getSize() == this.getSize())
                    return 0;
                else if(other.getSize() < this.getSize())
                    return 1;
                else 
                    return -1;
            }
        }
        
        public void print() {
            StringBuffer s = new StringBuffer("(");
            for(Integer i : lst)
                s.append(i+" ");
            s.append(")");
            System.out.println(s);
        } 
}

public class Solution {
    static PriorityQueue<Team> teamsOfInterest = new PriorityQueue<Team>();
   
    static int addToCorrectTeam(int i,  boolean prune){
        
        int retVal = -1;
        if(prune){
            
            int tSkill = teamsOfInterest.peek().getMaxSkill();
            if(tSkill < (i-1))
                retVal = teamsOfInterest.peek().getSize();
            while(tSkill < (i-1)){
                
                teamsOfInterest.poll();
                tSkill = teamsOfInterest.peek().getMaxSkill();
            }
        }
        
        if(teamsOfInterest.size() > 0){
            int tSkill = teamsOfInterest.peek().getMaxSkill();
            
            if(tSkill == (i-1)){
                Team teamToAppend = teamsOfInterest.poll();
                teamToAppend.add(i);
                teamsOfInterest.add(teamToAppend);
                return retVal;
            }
        }
        //create a new team if we reach here.
        Team newTeam = new Team();
        newTeam.add(i);
        teamsOfInterest.add(newTeam);
        return retVal;
    }
    
    static int findMinSize(){
        int sz = Integer.MAX_VALUE;
        Iterator<Team> itr = teamsOfInterest.iterator();
        while(itr.hasNext()){
            Team t = (Team)itr.next();
            if(t.getSize() < sz)
                sz = t.getSize();
        }
        return sz;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numTests = in.nextInt();
       
        for(int i=0; i < numTests; i++){
            int numContestants = in.nextInt();
                       
            if((numContestants == 0) || (numContestants == 1)){
                if(numContestants == 1) 
                    in.nextInt();
                System.out.println(numContestants);
                continue;
            }
            
            int[] skills = new int[numContestants];
            
            //read the input
            for(int j=0; j < numContestants; j++){
                skills[j] = in.nextInt();   
            }
            
            //sort the skills array
            Arrays.sort(skills);
                      
            //Go through the sorted array and form teams
            
            Team team = new Team();
            team.add(skills[0]);
            teamsOfInterest.add(team);
            int minTeamLen = Integer.MAX_VALUE;
            
            for(int j=1; j < numContestants; j++){
                
                if(skills[j] == skills[j-1])
                    addToCorrectTeam(skills[j],  false);
                else{
                    if(skills[j] == (skills[j-1]+1)) {
                        int len = addToCorrectTeam(skills[j],  true);
                        if((len != -1) && (minTeamLen > len))
                            minTeamLen = len;
                    }
                    else{
                        int len = findMinSize();
                        if(minTeamLen > len)
                            minTeamLen = len;
                        teamsOfInterest.clear();
                        Team newTeam = new Team();
                        newTeam.add(skills[j]);
                        teamsOfInterest.add(newTeam);
                    }
                }
                /*System.out.println("Processing contestant.. "+(j+1)+" number of teams= "+teamsOfInterest.size()+" skill "+skills[j]);
                int k=1;
                Iterator<Team> itr = teamsOfInterest.iterator();
                while(itr.hasNext()){
                    Team t = itr.next();
                    System.out.print(k++);
                    t.print();
                }*/
            }
            
            int len = findMinSize();
            if(minTeamLen > len)
                minTeamLen = len;
            teamsOfInterest.clear();
            
            System.out.println(minTeamLen);
        }                      
    }    
}