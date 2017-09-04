import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class TeamFormation3 {

  public static void main(String[] args) {
         Scanner in = new Scanner(System.in);
         int t = in.nextInt(), n;
         for (int i = 0; i < t; i++) {
             n = in.nextInt();
             if (n == 0) {
                 System.out.println(0);
                 continue;
             }
             TeamBuilder tb = new TeamBuilder();
             for (int j = 0; j < n; j++) {
                 tb.place(in.nextInt());
             }
             System.out.println(tb.getSmallestTeam());
         }
         
         in.close();
     }
     
     private static class TeamBuilder {
         TreeMap<Integer, Integer> orderedSkillMap;
         HashMap<Integer, Integer> skillMap;
         
         public TeamBuilder() {
          orderedSkillMap = new TreeMap<Integer, Integer>();
          skillMap = new HashMap<Integer, Integer>();
         }
         
         public void place(int skill) {
          int skillCount = skillMap.get(skill) == null ? 1 : skillMap.get(skill) + 1;
          skillMap.put(skill, skillCount);
          orderedSkillMap.put(skill, skillCount);
         }
         
         public int getSmallestTeam() {
          Integer[] bucketIndexes = orderedSkillMap.keySet().toArray(new Integer[orderedSkillMap.size()]);
          ArrayList<Integer> teams= new ArrayList<Integer>();
          int minTeamSize = Integer.MAX_VALUE; 
          int bucketIndex = bucketIndexes[0];
          int bucketSize = skillMap.get(bucketIndex);
          for (int j = 0; j < bucketSize; j++) {
           teams.add(bucketIndex);
          }
          
          for (int i = 1; i < bucketIndexes.length; i++) {
           bucketIndex = bucketIndexes[i];
           
           int completedTeams = getNumberOfCompletedTeamsAtBucket(bucketIndexes[i - 1], bucketIndex);
           
           for (int j = 0; j < completedTeams; j++) {
            if (bucketIndexes[i - 1] - teams.get(0) + 1 < minTeamSize) {
             minTeamSize = bucketIndexes[i - 1] - teams.get(0) + 1;
            }
            teams.remove(0);
           }
           
           int startedTeams = getNumberOfStartedTeamsAtBucket(bucketIndexes[i - 1], bucketIndex);
           for (int j = 0; j < startedTeams; j++) {
            teams.add(bucketIndex);
           }
          }
          
          for (int j = 0; j < teams.size(); j++) {
           if (bucketIndexes[bucketIndexes.length - 1] - teams.get(j) < minTeamSize) {
            minTeamSize = bucketIndexes[bucketIndexes.length - 1] - teams.get(j) + 1;
           }
          }
          
          return minTeamSize;
         }

   private int getNumberOfStartedTeamsAtBucket(int prevBucketIndex, int bucketIndex) {
    // No consecutive buckets
    if (prevBucketIndex + 1 != bucketIndex)
     return skillMap.get(bucketIndex);
    
    // In case we had multiple teams one over each other
    int sizeDiff = skillMap.get(bucketIndex) - skillMap.get(prevBucketIndex);
    return sizeDiff > 0 ? sizeDiff : 0;
   }

   private int getNumberOfCompletedTeamsAtBucket(int prevBucketIndex, int bucketIndex) {
    // No consecutive buckets
    if (prevBucketIndex + 1 != bucketIndex)
     return skillMap.get(prevBucketIndex);

    // In case we had multiple teams one over each other
    int sizeDiff = skillMap.get(prevBucketIndex) - skillMap.get(bucketIndex);
    return sizeDiff > 0 ? sizeDiff : 0;
   }
     }
   
}