import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int towers = in.nextInt();
            boolean s1 = false, s2 = false, s4 = false, s8 = false, s16 = false, s32 = false, s64 = false;
            int currentTower;
            int maxHeight = 0;
            for(int a1 = 0; a1 < towers; a1++){
                currentTower = in.nextInt();
                if(currentTower > maxHeight){
                    maxHeight = currentTower;
                }
                if(currentTower >= 64){
                    currentTower -= 64;
                    if(s64) s64 = false;
                    else s64 = true;
                }
                if(currentTower >= 32){
                    currentTower -= 32;
                    if(s32) s32 = false;
                    else s32 = true;
                }
                if(currentTower >= 16){
                    currentTower -= 16;
                    if(s16) s16 = false;
                    else s16 = true;
                }
                if(currentTower >= 8){
                    currentTower -= 8;
                    if(s8) s8 = false;
                    else s8 = true;
                }
                if(currentTower >= 4){
                    currentTower -= 4;
                    if(s4) s4 = false;
                    else s4 = true;
                }
                if(currentTower >= 2){
                    currentTower -= 2;
                    if(s2) s2 = false;
                    else s2 = true;
                }
                if(currentTower >= 1){
                    currentTower -= 1;
                    if(s1) s1 = false;
                    else s1 = true;
                }
            }
            if(maxHeight == 1){
                if(s1)System.out.println("Second");
                else System.out.println("First");
            }else
                if(!s64 && !s32 && !s16 && !s8 && !s4 && !s2 && !s1){
                    System.out.println("Second");
                }else{
                    System.out.println("First");
                }
        }
        
    }
}