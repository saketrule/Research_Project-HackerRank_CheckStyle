import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

 public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        ArrayList<ArrayList<Integer>> counters = new ArrayList<ArrayList<Integer>>();
        int inputCounters[] = new int[26];
        int remainingLetters[] = new int[26];
        int finalSolutionLength = str.length()/2;
        StringBuilder solution = new StringBuilder(finalSolutionLength);
        int solutionCounters[] = new int[26];
        int index = str.length()-1;
        int charIndex = 0;
        int endIndex = str.length()-1;
        int startIndex = finalSolutionLength-1;
        int tempCounters[] = new int[26];
        boolean passChar = true;
        
        for(int i=0; i<26; i++){
            counters.add(new ArrayList<Integer>());
            
        }
        for(int i=0; i<str.length(); i++){
            counters.get(str.charAt(i)-97).add(i);
            inputCounters[str.charAt(i)-97]++;
        }
        for(int i=0; i<26; i++){
            remainingLetters[i] = inputCounters[i]/2; 
        }
        
        while(solution.length() < finalSolutionLength){
            for(int i=0; i<counters.size(); i++){
                passChar = true;
                if(counters.get(i).size() < 1){
                 continue;
                }
                charIndex = counters.get(i).get(counters.get(i).size()-1);
                if(charIndex < startIndex || charIndex > endIndex || remainingLetters[str.charAt(charIndex)-97] == 0){
                    continue;
                }
                tempCounters = new int[26];
                for(int j = 0; j <= charIndex; j++){
                    tempCounters[str.charAt(j)-97] ++;
                }
                for(int j=0; j<tempCounters.length; j++){
                    if(tempCounters[j] < remainingLetters[j]){
                        passChar = false;
                        break;
                    }
                }
                if(passChar){
                    solution.append((char)(i+97));
                    remainingLetters[i]--;
                    endIndex = charIndex-1;
                    startIndex = finalSolutionLength - solution.length()-1;
                    counters.get(i).remove(counters.get(i).size()-1);
                    for(int j=0; j<counters.size(); j++){
                     for(int k=counters.get(j).size()-1; k>=0; k--){
                      if(counters.get(j).get(k) >= charIndex){
                       counters.get(j).remove(k);
                      }
                      else{
                       break;
                      }
                     }
                    }
                    break;
                }
                else{
                    startIndex = charIndex+1;
                    continue;
                }
                
            }
        }
        
        System.out.println(solution);
    }
}