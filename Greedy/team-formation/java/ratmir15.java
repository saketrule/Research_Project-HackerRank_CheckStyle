import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tn = sc.nextInt();
        for (int ti=0;ti<tn;ti++) {
            int n = sc.nextInt();
            List<Integer> a = new ArrayList<Integer>(n);
            for (int i=0;i<n;i++) {
                a.add(sc.nextInt());
            }
            if (n==0) {
                System.out.println(0);
                continue;
            }
            Collections.sort(a);
            int min_length = Integer.MAX_VALUE;
            int cr = 1;
            List<Integer> state = new ArrayList<Integer>();
            state.add(1);
            for (int i=1; i < n; i++) {
                //System.out.println(a.get(i));
                if (a.get(i)>a.get(i-1)+1) {
                    for (Integer st: state) {
                        if (st<min_length) {
                            min_length = st;
                        }
                    }
                    state = new ArrayList<Integer>();
                    state.add(1);
                    //System.out.println("jump state:" + state);            
                } else if (a.get(i)==a.get(i-1)+1) {
                    int cnt = state.size()-cr;
                    for (int j=0;j<cnt;j++) {
                        int x = state.get(0);
                        if (x<min_length) {
                            min_length = x;
                        }
                        state.remove(0);
                    }
                    cr = 1;
                    state.set(state.size()-1, state.get(state.size()-1) + 1);
                    //System.out.println("next state: "+state);
                } else {
                    if (cr>=state.size()) {
                        state.add(1);
                    } else {
                        state.set(state.size()-cr-1,state.get(state.size()-cr-1)+1);
                    }
                    //System.out.println("same state:" + state);            
                    cr++;
                }
                //System.out.println(a.get(i)+":a "+cr+" "+state);
            }
            //System.out.println("last state:" + state);            
            for (Integer st: state) {
                if (st<min_length) {
                    min_length = st;
                }
            }
            System.out.println(min_length);
        }
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}