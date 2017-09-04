import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
 class Skill {
    public Skill(int count, int skill) {
        this.count = count;
        this.skill = skill;
    }
    public int count;
    public int skill;
}
public class Solution {

 
    public static void main(String[] args) {
        try {
         BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
         int T = Integer.parseInt(buf.readLine());
         for (int t = 0; t < T; t++) {
             String[] nums = buf.readLine().split(" ");
             int N = Integer.parseInt(nums[0]);
             ArrayList<Integer> skills = new ArrayList<Integer>();
             LinkedList<Skill> sorted = new LinkedList<Skill>();
             HashMap<Integer, Integer> cts = new HashMap<Integer, Integer>();
             for (int i = 1; i <= N; i++) {
                 int v = Integer.parseInt(nums[i]);
                 if (cts.containsKey(v)) {
                     cts.put(v, cts.get(v) + 1);
                 } else {
                     skills.add(v);
                     cts.put(v, 1);
                 }
             }
             Collections.sort(skills);
             
             for (int i = 0; i < skills.size(); i++) {
                 sorted.add(new Skill(cts.get(skills.get(i)), skills.get(i)));
             }
             
             int min = (N == 0) ? 0 : 10000000;
             while(!sorted.isEmpty()) {
                int prevCt = 0;
                int prevVal = 0;
                int length = 0;
                boolean first = true;
                for (Iterator<Skill> iter = sorted.iterator(); iter.hasNext(); ) {  
                    Skill el = iter.next();
                    if ((!first &&  prevVal + 1 != el.skill) || prevCt >= el.count) {
                        if (length < min) {
                            min = length;
                        }
                        break;
                    }
                    
                    length++;
                    
                    first = false;
                    prevVal = el.skill;
                    el.count -= 1;
                    prevCt = el.count;
                    if (el.count == 0) {
                        iter.remove();
                    }
                    
                       
                    
                }
                 if (length < min)
                    min = length;
             }
             
             System.out.println(min);
              
         }
        }
        catch (Exception e) {
            
        }
    }
}