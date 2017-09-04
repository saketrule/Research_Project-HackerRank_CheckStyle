import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static final BigInteger modulo = new BigInteger(Integer.toString(1000000007));
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int cases = in.nextInt();
        int[] beads;
        int beadCount;
        BigInteger configs, term;
        
        for(int i = 0; i < cases; i++) {
            beads = new int[in.nextInt()];
            configs = new BigInteger(Integer.toString(1));
            beadCount = 0;
            
            for(int j = 0; j < beads.length; j++) {
                beads[j] = in.nextInt();
                term = new BigInteger(Integer.toString(beads[j])).pow(beads[j] - 1);
                configs = configs.multiply(term);
            }

            if(beads.length > 2) {
                for(int j = 0; j < beads.length; j++)
                    beadCount += beads[j];

                term = new BigInteger(Integer.toString(beadCount)).pow(beads.length - 2);
                configs = configs.multiply(term);
            }
            else if(beads.length == 1)
                configs = configs.divide(new BigInteger(Integer.toString(beads[0])));
            
            System.out.println(configs.mod(modulo).longValue());
        }
    }
}