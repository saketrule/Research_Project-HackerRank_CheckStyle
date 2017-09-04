import java.io.*;
import java.util.*;

public class Solution
{
    public static void main(String[] args) {
        // Read problem input
        Scanner s = new Scanner(System.in);
        int N = s.nextInt(); // # of buildings
        int H = s.nextInt(); // # height of each building
        int I = s.nextInt(); // # height lost
        int P[][] = new int[N][H]; // # of people in building B at floor F [B=0..N-1][F=0..H-1]
        int S[][] = new int[N][H]; // max # of people saveable from this building/floor
        int maxF[] = new int[H]; // max # saveable on each floor
        for (int n = 0; n < N; n++) {  // for each building
            int T = s.nextInt(); // total people in this building
            for (int t = 0; t < T; t++) { // for each person in this building
                int u = s.nextInt() - 1; // make the floor they're on base-0 instead of base-1
                P[n][u]++; // This person is in building n at floor u
            }
        }
        
        // Initialize the bottom floor of each building
        for (int b = 0; b < N; b++) {
            S[b][0] = P[b][0];
            if (S[b][0] > maxF[0]) {
                maxF[0] = S[b][0];
            }
        }
        
        // Calculate max savings of each floor above [b][f]
        for (int f = 1; f < H; f++) {
            for (int b = 0; b < N; b++) {
                // If he stays in this building
                int maxSaved = P[b][f] + S[b][f-1];
                // Can he jump to another building?
                if (f >= I) {
                    // Then if he jumps...
                    int jumpSave = P[b][f] + maxF[f-I];
                    if (jumpSave > maxSaved) {
                        maxSaved = jumpSave; // sped up
                    }
                }
                
                S[b][f] = maxSaved;
                
                // If this is the max saveable on this floor, save it to speed things up later
                if (S[b][f] > maxF[f]) {
                    maxF[f] = S[b][f];
                }
            }
        }
        
        // Print the max # of people he can save
        int maxSaved = 0;
        for (int b = 0; b < N; b++) {
            if (S[b][H-1] > maxSaved) {
                maxSaved = S[b][H-1];
            }
        }
        System.out.println("" + maxSaved);
        
        /* DEBUG
        for (int b = 0; b < N; b++) {
            for (int f = 0; f < H; f++) {
                System.err.print(S[b][f] + " ");
            }
            System.err.println();
        }
        
        System.err.println();
        for (int f = 0; f < H; f++) {
            System.err.print(maxF[f] + " ");
        }
        System.err.println();
        
        System.err.println();
        for (int b = 0; b < N; b++) {
            for (int f = 0; f < H; f++) {
                System.err.print(P[b][f] + " ");
            }
            System.err.println();
        }*/
    }
}