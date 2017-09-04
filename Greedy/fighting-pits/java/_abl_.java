import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    static Cache cache = new Cache();
    
    static int currentDate = 0;

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        int numFighters = sc.nextInt();
        int numTeams = sc.nextInt();
        int numQueries = sc.nextInt();
        
        // Initialize teams
        List<Team> teams = new ArrayList<>();
        for(int i=0; i<numTeams; i++) {
            teams.add(new Team(i+1));
        }
        
        // Add fighters to the teams
        for(int i=0; i<numFighters; i++) {
            int strength = sc.nextInt();
            int teamNumber = sc.nextInt();
            teams.get(teamNumber - 1).addInitial(strength);
        }
        
        // Sort the fighter strengths
        for(Team team : teams) {
            team.sort();
        }
        
        // Process the queries
        for(int i=0; i<numQueries; i++) {
            currentDate++;
            int queryType = sc.nextInt();
            if(queryType == 1) {
                int strength = sc.nextInt();
                int teamNumber = sc.nextInt();
                teams.get(teamNumber - 1).add(strength);
            } else {
                int teamA = sc.nextInt();
                int teamB = sc.nextInt();
                Match match = new Match(teams.get(teamA-1), teams.get(teamB-1));
                if(match.computeResult().firstWins) {
                    System.out.println(teamA);
                } else {
                    System.out.println(teamB);
                }
            }
        }
        
    }
    
    
    
    private static class Team {
        
        private final int number;
        private final List<Integer> fighters = new ArrayList<>();
        private final List<Long> prefixSum = new ArrayList<>();
        private long totalStrength = 0;
        private int lastImproved = 0;
        
        public Team(int number) {
            this.number = number;
        }
        
        public void sort() {
            Collections.sort(fighters);
            List<Integer> copy = new ArrayList<>(fighters);
            fighters.clear();
            for(int f : copy) {
                add(f);
            }
        }
        
        public void addInitial(int fighter) {
            fighters.add(fighter);
        }
        
        public void add(int fighter) {
            fighters.add(fighter);
            totalStrength += fighter;
            prefixSum.add(totalStrength);
            lastImproved = currentDate;
        }
        
        public int get(int index) {
            return fighters.get(index);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(number);
        }
        
        @Override
        public boolean equals(Object other) {
            return this == other;
        }
        
    }
    
    private static class Match {
        
        private final Team teamA;
        private final Team teamB;
        
        public Match(Team teamA, Team teamB) {
            this.teamA = teamA;
            this.teamB = teamB;
        }
        
        public Result computeResult() {
            
            //System.out.println("Computing " + teamA.fighters + " " + teamB.fighters);
            
            Result resultO = null;
            Result predictedResult = cache.attemptPredictResult(this);
            if(predictedResult != null) {
                resultO = predictedResult;
            } else {
                
                int lastAliveA = teamA.fighters.size() - 1;
                int lastAliveB = teamB.fighters.size() - 1;

                Boolean result = null;
                long totalA = teamA.totalStrength;
                long totalB = teamB.totalStrength;
                long prevPrefix;
                while(true) {

                    // turn A
                    if(totalA >= totalB) {
                        result = true;
                        break;
                    }
                    prevPrefix = teamB.prefixSum.get(lastAliveB);
                    lastAliveB -= teamA.get(lastAliveA);
                    if(lastAliveB >= 0) {
                        long newPrefix = teamB.prefixSum.get(lastAliveB);
                        totalB -= (prevPrefix - newPrefix);
                    }
                    if(lastAliveB < 0) {
                        result = true;
                        break;
                    }

                    // turn B
                    if(totalB >= totalA) {
                        result = false;
                        break;
                    }
                    for(int i=0; i<teamB.get(lastAliveB) && (lastAliveA>=0); i++) {
                        totalA -= teamA.get(lastAliveA);
                        lastAliveA--;
                    }
                    if(lastAliveA < 0) {
                        result = false;
                        break;
                    }
                }

                resultO = new Result(result, currentDate);
            }
            
            cache.remember(this, resultO);
            
            return resultO;

        }
        
        @Override
        public int hashCode() {
            return Objects.hash(teamA, teamB);
        }
        
        @Override
        public boolean equals(Object other) {
            Match o = (Match)other;
            return o.teamA.equals(teamA) && o.teamB.equals(teamB);
        }
        
    }
    
    private static class Result {
        
        private final boolean firstWins;
        private final int date;
        
        public Result(boolean firstWins, int date) {
            this.firstWins = firstWins;
            this.date = date;
        }
    }
    
    private static class Cache {
        
        final Map<Match, Result> map = new HashMap<>();
        
        public void remember(Match match, Result result) {
            map.put(match, result);
        }
        
        public Result attemptPredictResult(Match match) {
            
            // If the losing team hasn't been improved since the last match
            // took place, then the result is going to be the same.
            if(!map.containsKey(match)) {
                return null;
            }
            Result lastResult = map.get(match);
            Team losingTeam = (lastResult.firstWins ? match.teamB : match.teamA);
            if(losingTeam.lastImproved < lastResult.date) {
                //System.out.println("using cache");
                return new Result(lastResult.firstWins, currentDate);
            }
            
            return null;
            
        }
        
    }
    
   
}