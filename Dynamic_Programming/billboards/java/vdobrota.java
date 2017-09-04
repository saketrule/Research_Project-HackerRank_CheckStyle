/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

/**
 *
 * @author ze
 */
public class Solution {

    static class Case {
        int start;
        int gap;
        
        public Case(int start, int gap) {
            this.start = start;
            this.gap = gap;
        }
        
        long profit() {
            long res = partials[i]-partials[start]+profits[start];
            int ind = start-gap-1;
            if(ind>=0)
                res+=best[ind];
            return res;
        }
    }
    
    static long[] profits;
    static long[] best;          
    static long[] partials;
    static int i;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        profits = new long[n];
        best = new long[n];
        partials = new long[n];
        long sum = 0;
        for(i=0; i<n; i++) { 
            profits[i] = scanner.nextLong();
            best[i] = 0;        
            sum+=profits[i];
            partials[i] = sum;
        }
        
        PriorityQueue<Case> set = new PriorityQueue<Case>((k*2+100), new Comparator<Case>() {
            @Override
            public int compare(Case o1, Case o2) {
                int res =  (int) (-o1.profit() + o2.profit());
                if(i==4) {
                    int a =0;
                }
                return res;
            }
        });
        
        for(i=0; i<n; i++) {
            Case caz = new Case(i, 1);
            Case caz2 = new Case(i, 2);
            set.add(caz);
            set.add(caz2);
            while(true) {
                Case bestCase = set.peek();
                if(i-bestCase.start<k) {
                    best[i] = bestCase.profit();
                    break;
                }
                set.poll();
            }
        }

        System.out.println(Math.max(best[n-1], best[n-2])); 
    }
}