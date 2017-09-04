import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static class CharInfo {
       private char c;
       private List<Integer> positions;
       private int pointer = 0;
        
       public CharInfo(char c, int i) {
           this.c = c;
           this.positions = new ArrayList<Integer>();
           this.push(i);
       }
        
       public void push(int i) {
          this.positions.add(i);
       }
       
       public void finalize() {
           this.pointer = this.positions.size()/2;
       }
        
       public boolean pop() {
           return ++pointer == this.positions.size();
       }
                                                   
       public int lastMin() {
           return this.positions.get(pointer);
       }
        
       public List<Integer> allPositions() {
           return new ArrayList<Integer>(positions);
       }
    }
    
    public static void main(String[] args) throws IOException {
        String line = (new BufferedReader(new InputStreamReader(System.in))).readLine().trim();
        String reversed = new StringBuilder(line).reverse().toString();
        Map<Character, CharInfo> rm = new TreeMap<Character, CharInfo>();
        char[] rc = reversed.toCharArray();
        for(int i = 0; i < rc.length; i++) {
            if(!rm.containsKey(rc[i])) {
                rm.put(rc[i], new CharInfo(rc[i], i));
            }     
            else {
                rm.get(rc[i]).push(i);
            }
        }
        Set<Character> snapshot = rm.keySet();
        for(Character c : snapshot) {
            rm.get(c).finalize();
        }
        StringBuilder result = new StringBuilder();
        int last = -1;
        while(rm.size() > 0) {
            int metaMin = Integer.MAX_VALUE;
            Set<Character> keys = new TreeSet<Character>(rm.keySet());
            for(Character c : keys) {
                metaMin = Math.min(metaMin, rm.get(c).lastMin());
            }
            for(Character c : keys) {
                CharInfo curr = rm.get(c);
                boolean stop = false;
                for(Integer i : curr.allPositions()) {
                    if(i <= metaMin && i > last) {
                        result.append(c);
                        if(curr.pop()) {
                           rm.remove(c);
                        }
                        stop = true;
                        last = i;
                        break;
                    }
                }
                if(stop)
                    break;
            }
        }
        System.out.println(result.toString());
    }
}