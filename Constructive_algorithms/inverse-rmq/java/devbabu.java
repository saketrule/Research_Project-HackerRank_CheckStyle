import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.input();
    }
    
    void input(){
        Scanner sin = new Scanner(System.in);
        int n = sin.nextInt();
        int size = n*2-1;
        int []segTree = new int[size];
        for(int i = 0; i < size; i++){
            segTree[i] = sin.nextInt();
        }
        System.out.println("NO");
    }
}