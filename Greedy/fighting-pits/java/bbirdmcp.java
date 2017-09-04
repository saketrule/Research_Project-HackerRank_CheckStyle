import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

 public static  long iterations = 0;
 public static final long MASKLOW =  0x000000000003FFFFL;
 public static final long MASKMID =  0x0000000FFFFC0000L;
 public static final long MASKHIGH = 0x0000001000000000L;
 
 public static void fillQueueFromInput(Scanner scan, ArrayDeque<Long> queue, int[] teamSizes, int N, int Q) {
        // Read each fighter's attributes
        int cmd, op1, op2, strength, team;
        for (int n=0; n<N; n++) {
            strength = scan.nextInt();
            team = scan.nextInt();
            queue.addLast(pack(strength, team));
            teamSizes[team]++;
        }

        // Read all the queries
        for (int q=0; q<Q; q++) {
            cmd = scan.nextInt();
            op1 = scan.nextInt();
            op2 = scan.nextInt();

            if (cmd == 1) {
                // Add fighter of strength (op1) to team (op2)
                queue.addLast(pack(op1, op2));
                teamSizes[op2]++;
            } else if (cmd == 2) {
                // Print winning team; (op1 goes first, vs. op2)
                queue.addLast(MASKHIGH + pack(op1, op2));
            }
        }  
 }
 
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
     Scanner scan = new Scanner(System.in);
        int N = scan.nextInt(); // number of fighters
        int K = scan.nextInt(); // number of teams
        int Q = scan.nextInt(); // number of queries

     //long startTime = System.nanoTime();

        long queueItem = 0;
        int op1, op2, strength, team;
                
        // Figure out the optimal size for our data structures
        // Holds the queries to execute
        ArrayDeque<Long> queue = new ArrayDeque<Long>(Q+N);        
        int[] teamSizes = new int[K+1]; // 1-based indexing
        int[] teamCompressedSizes = new int[K+1];  // 1-based indexing
        
        fillQueueFromInput(scan, queue, teamSizes, N, Q);
        // scan.close();
        //System.out.println("After Queue Filled: " + ((System.nanoTime() - startTime) /1000000));

        // Allocate sufficient space & init all teams
        long[][] teams = new long[K+1][];   // 1-based indexing
        long[][] teamIndex = new long[K+1][];  // 1-based indexing
        
        for (int k=1; k<=K; k++) {
            teams[k] = new long[(teamSizes[k] == 0 ? 1 : teamSizes[k])];
            teamIndex[k] = new long[(teamSizes[k] == 0 ? 1 : teamSizes[k])];
        }

        //System.out.println("After Teams Allocated: " + ((System.nanoTime() - startTime) /1000000));

        // Reset the team sizes to zero
        teamSizes = new int[K+1];
        
        // Process queue until first (print) is found        
        while (!queue.isEmpty()) {
            queueItem = queue.removeFirst().longValue();
            if (queueItem >= MASKHIGH) {
                break;
            }
            strength = unpackhigh(queueItem);
            team = unpacklow(queueItem);
            teams[team][teamSizes[team]] = strength; 
            teamSizes[team]++;
        }

        //System.out.println("After Queue 1st Processed: " + ((System.nanoTime() - startTime) /1000000));
       
        // Sort each team by fighter strength (weak to strong)
        // Also, convert the (teams[]) and (teamSizes[]) arrays to a compressed format after sorting.
        for (int k=1; k<=K; k++) {
         if (teamSizes[k] > 0) {
             Arrays.sort(teams[k], 0, teamSizes[k]);
             // Step thru the array and count the number of duplicate values
             int searchPos = 1;
             int storePos = -1;
             int maxSearchPos = teamSizes[k];
             int indexPos = 1;
             int prevValue = (int) teams[k][0];
             int repeatCount = 1;
             teamIndex[k][0] = pack(storePos+1, repeatCount);
             while (searchPos < maxSearchPos) {
              if (teams[k][searchPos] == prevValue) {
               repeatCount++;
                  teamIndex[k][indexPos++] = pack(storePos+1, repeatCount);
              } else {
               // Save the compressed version
               storePos++;
               teams[k][storePos] = pack(prevValue, repeatCount);
               repeatCount = 1;
               prevValue = (int) teams[k][searchPos];
                  teamIndex[k][indexPos++] = pack(storePos+1, repeatCount);
              }
              searchPos++;
             }
             // Save the compressed version
       storePos++;
             teams[k][storePos] = pack(prevValue, repeatCount);
       // Save the compressed version of (teamSizes)
       teamCompressedSizes[k] = storePos;
         } else {
          teams[k][0] = 0; // Empty team
          teamIndex[k][0] = 0;
         }
        }

        //System.out.println("After Sort/Compress: " + ((System.nanoTime() - startTime) /1000000));
        
        StringBuilder sb = new StringBuilder(Q * 4);
        
        // process queries (continue with previously read queueItem)
        while (true) {            
            if (queueItem >= MASKHIGH) {
                // Print winning team (op1 goes first, vs. op2)
                queueItem -= MASKHIGH;
                op1 = unpackhigh(queueItem);
                op2 = unpacklow(queueItem);
                //int expectedWinner = scan.nextInt();
                int winner = determineWinner(teams, teamIndex, teamSizes, teamCompressedSizes, op1, op2);
                //if (winner != expectedWinner) {
                // System.out.println("ERROR: " + expectedWinner + " should have won.");
                //}

                sb.append(winner);
                sb.append("\n");
            } else {
                // Add fighter of strength (op1) to team (op2)
                // (op1) is guaranteed to be >= strongest fighter
                strength = unpackhigh(queueItem);
                team = unpacklow(queueItem);
                int compSize = teamCompressedSizes[team];
                int repValue = unpackhigh(teams[team][compSize]);
                int repCount = unpacklow(teams[team][compSize]);
                if (strength == repValue) {
                 // if this value already exists, just increment its repeat count
                 teams[team][compSize]++;
                 teamIndex[team][teamSizes[team]++] = pack(compSize, repCount + 1);
                } else {
                 // value doesn't exist. We need to store it in next location
                 // Handle case where no one is on this team yet. (repCount ==0 and repValue == 0)
                 if (repValue != 0) {
                        teamCompressedSizes[team]++;
                        compSize++;
                 }
                 teams[team][compSize] = pack(strength, 1);
                 teamIndex[team][teamSizes[team]++] = pack(compSize, 1);
                }
            }
            if (queue.isEmpty()) {
                break;
            }
            queueItem = queue.removeFirst().longValue();
        }
        System.out.println(sb);
        //System.out.println("End: " + ((System.nanoTime() - startTime) /1000000));

        //System.out.println("Iterations = " + iterations);
    }
    
    public static int determineWinner(long[][] teams, long[][] teamIndex, int[] teamSizes, int[] compressedSizes, int firstTeam, int secondTeam) {
        // teamX always goes first
        long[] teamX = teams[firstTeam];
        long[] teamY = teams[secondTeam];
        long[] teamXIndex = teamIndex[firstTeam];
        long[] teamYIndex = teamIndex[secondTeam];
        
        // Track which living fighter is strongest on each team
        int onX = teamSizes[firstTeam] - 1; 
        int onY = teamSizes[secondTeam] - 1;  
        int onXcomp = compressedSizes[firstTeam];
        int onYcomp = compressedSizes[secondTeam];
        
        int onXstrength = unpackhigh(teamX[onXcomp]);
        int onXrepeat = unpacklow(teamX[onXcomp]);
        int onYstrength = unpackhigh(teamY[onYcomp]);
        int onYrepeat = unpacklow(teamY[onYcomp]);
        
        // Fight
        while (true) {
         iterations++;

         // Since the list of strengths is sorted, when we reach strengths of 1
         // on both teams, we can just calculate the winner in some
         // decisive cases.
         int reductionCount = min(((onYrepeat-1) / onXstrength), ((onXrepeat-1) / onYstrength));
         if (reductionCount > 0) {
          onXrepeat -= (onYstrength * reductionCount);
          onYrepeat -= (onXstrength * reductionCount);
           onX -= (onYstrength * reductionCount);
          onY -= (onXstrength * reductionCount);
            }
         
         
         // teamX's strongest fighter kills strongest opponents
         onY -= onXstrength;
         if (onY < 0) {
          return firstTeam;
         }
         onYcomp = unpackhigh(teamYIndex[onY]);
         onYrepeat = unpacklow(teamYIndex[onY]);
         onYstrength = unpackhigh(teamY[onYcomp]);
                        
         reductionCount = min(((onYrepeat-1) / onXstrength), ((onXrepeat-1) / onYstrength));
         if (reductionCount > 0) {
          onXrepeat -= (onYstrength * reductionCount);
          onYrepeat -= (onXstrength * reductionCount);
           onX -= (onYstrength * reductionCount);
          onY -= (onXstrength * reductionCount);
            }

         // teamY's strongest fighter kills
         onX -= onYstrength;
         if (onX < 0) {
          return secondTeam;
         }
         onXcomp = unpackhigh(teamXIndex[onX]);
         onXrepeat = unpacklow(teamXIndex[onX]);
         onXstrength = unpackhigh(teamX[onXcomp]);
        }
    }
    
    public static final int min(int a, int b) {
     return (a<b ? a: b);
    }
    public static final long pack(int a, int b) {
     return ((long)a << 18) + (long)b;
    }
    public static final int unpacklow(long l) {
     return (int)(l & MASKLOW);
    }
    public static final int unpackhigh(long l) {
     return (int)(l >> 18);
    }
    
}