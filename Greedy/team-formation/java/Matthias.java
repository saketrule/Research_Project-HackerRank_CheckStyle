import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;


public class Solution {
 
 public static void main(String[] args) throws NumberFormatException, IOException {
  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  int nTestCases = Integer.parseInt(reader.readLine().trim());
  
  for (int i = 0; i < nTestCases; ++i) {
   String testCase = reader.readLine();
   try (Scanner scanner = new Scanner(testCase)) {
    int[] skillLevels = new int[scanner.nextInt()];
    for (int j = 0; j < skillLevels.length && scanner.hasNextInt(); ++j) {
     skillLevels[j] = scanner.nextInt(); 
    }
    System.out.println(getSmallestTeamNumberCount(skillLevels));
   }
  }
 }
 
 static int getSmallestTeamNumberCount(int[] skillLevels) {  
  if (skillLevels.length < 1) {
   return 0;
  }
  
  Arrays.sort(skillLevels);
  
  int[] duplicateFreeLevels = new int[skillLevels.length];
  int[] counts = new int[skillLevels.length];
  
  int nDuplicateFreeLevels = 1;
  int previousLevel = skillLevels[0];
  duplicateFreeLevels[0] = previousLevel;
  counts[0] = 1;
  for (int i = 1; i < skillLevels.length; ++i) {
   int level = skillLevels[i];
   if (level != previousLevel) {
    duplicateFreeLevels[nDuplicateFreeLevels] = level;
    ++nDuplicateFreeLevels;
    previousLevel = level;
   }
   ++counts[nDuplicateFreeLevels - 1];
  }
  
  int index = 0;
     
     int smallestTeam = Integer.MAX_VALUE;
  
  while (index < nDuplicateFreeLevels) {
   int minDuplicateCount = 1;
   int lastSkillLevel = 0;
   int teamSize = 0;
   for (int subIndex = index; subIndex < nDuplicateFreeLevels; ++subIndex) {
    int currentSkillLevel = duplicateFreeLevels[subIndex];
    if (teamSize > 0 && lastSkillLevel + 1 != currentSkillLevel) {
     break;
    }
    int nDuplicates = counts[subIndex];
    if (nDuplicates < minDuplicateCount) {
     break;
    }
    --counts[subIndex];
    if (counts[index] == 0) {
     ++index;
    }
    ++teamSize;
       lastSkillLevel = currentSkillLevel;
       minDuplicateCount = nDuplicates;
   }
      smallestTeam = Math.min(smallestTeam, teamSize);
  }
      
  return smallestTeam;
 }

}