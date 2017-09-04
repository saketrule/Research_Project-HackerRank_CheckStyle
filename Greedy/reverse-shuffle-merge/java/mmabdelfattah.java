import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        char[] ar = input.toCharArray();
        int[] chars = new int[26];
        for(int i=0; i<ar.length; i++)
            chars[ar[i] - 'a']++;

        int[] reversed = new int[26];
        int[] shuffled = new int[26];
        for(int i=0; i<26; i++)
        {
            reversed[i] = shuffled[i] = chars[i]/2;
        }
        //System.out.println(Arrays.toString(shuffled));
        StringBuilder sb = new StringBuilder();
        int idx = ar.length -1;
        for(int j=0; j<reversed.length; j++)
        {
            if(reversed[j] <= 0)
                continue;
            char c = (char)('a' + j);
            //System.out.println("Searching for : "+ c);
            while(reversed[j] > 0)
            {
                boolean bAccept =false;
                boolean bMandatory = false;
                if(ar[idx] == c)
                    bAccept = true;
                else if(shuffled[ar[idx] - 'a'] <= 0)
                    bMandatory = true;
                else if(reversed[ar[idx] - 'a'] > 0)
                {
                    //System.out.println(Arrays.toString(shuffled));
                    boolean bCorrect = false;
                    int i=idx-1;
                    for(; i>= 0; i--)
                        {
                        if(ar[i] == c)
                            break;
                        else if(ar[i] < ar[idx] && reversed[ar[i] - 'a'] > 0)
                            break;
                        else if(shuffled[ar[i] - 'a'] <= 0)
                            {
                            
                            if(ar[i] > ar[idx])
                                bCorrect = true;
                            //System.out.println("Found final case :" + ar[i] + " "+ bCorrect);
                            break;
                        }
                        else
                            shuffled[ar[i] - 'a']--;
                    }
                    i++;
                    for(;i<idx; i++)
                        shuffled[ar[i] - 'a']++;
                    bMandatory = bCorrect;
                    //System.out.println(Arrays.toString(shuffled) + "\n");
                }
                if(bAccept)
                  {
                      sb.append(c);
                      reversed[j]--;
                  }
                else if(bMandatory)
                    {
                    sb.append(ar[idx]);
                    reversed[ar[idx] - 'a']--;
                }
                else
                    {
                    shuffled[ar[idx] - 'a']--;
                }
                idx--;
                //System.out.println(Arrays.toString(shuffled));
                //System.out.println(Arrays.toString(reversed));
                //System.out.println(sb);
            }

        }
        System.out.println(sb);
    }
}