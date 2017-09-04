import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
                Scanner input = new Scanner(System.in);
        String S = input.nextLine();
        char[] buffer = S.toCharArray();
        char temp;
        for(int i=0;i<(buffer.length/2);i++){
            temp = buffer[i];
            buffer[i] = buffer[buffer.length-1-i];
            buffer[buffer.length-1-i]=temp;
        }
        
        S = new String(buffer);
        
        int len = S.length(),index,front,front1;
        int[][] DP = new int[26][10005];
        ArrayList<Queue<Integer>> Q = new ArrayList<Queue<Integer>>();
        int[] C = new int[26];
        int[] D = new int[26];
        char t;
        boolean flag;
        Arrays.fill(C, 0);
        Arrays.fill(D, 0);
        for(int i=0;i<26;i++){
            Arrays.fill(DP[i], 0);
            Q.add(i, new LinkedList<Integer>());
        }
        
        index = (int)S.charAt(0)-(int)'a';
        DP[index][0] = 1;
        
        for(int i=0;i<len;i++){
            index = (int)S.charAt(i)-(char)'a';
            DP[index][i] = 1;
        }
        
        for(int i=0;i<26;i++){
            for(int j=1;j<len;j++){
                DP[i][j]=DP[i][j-1]+DP[i][j];
            }
        }
        
        for(int i=0;i<len;i++){
            index = (int)S.charAt(i)-(char)'a';
            C[index]++;
            Q.get(index).add(i);
        }
        
        
        for(int i=0;i<26;i++){
            D[i] = C[i]/2;
        } 
        StringBuilder SB = new StringBuilder();
        front = -1;
        for(int i=0;i<(len/2);i++){
            for(int j=0;j<26;j++){
                if(D[j] <= 0){
                    continue;
                }
                while(Q.get(j).peek() <= front){
                    Q.get(j).poll();
                }
                
                front1 = Q.get(j).peek();
                D[j]--;
                flag = false;
                for(int k=0;k<26;k++){
                    if((DP[k][len-1]-DP[k][front1]) < D[k]){
                        flag = true;
                        break;
                    }
                }
                if(flag){
                    D[j]++;
                }
                else{
                    Q.get(j).poll();
                    front = front1;
                    t = (char)((int)'a' + j);
                    SB.append(t);
                    break;
                }
            }
        }
        
        System.out.println(SB);       
    }
}