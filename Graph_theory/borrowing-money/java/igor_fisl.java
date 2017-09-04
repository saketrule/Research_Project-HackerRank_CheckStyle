import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int calcMaxMoney(Set<Integer>cleanNodes, int[] cash, List<Integer>[] branches, Map<Set<Integer>, Integer> cachedResults, Set<Long> visitedNodes, Map<Set<Integer>, Set<Long>> cachedVisited) {
        if (cachedResults.containsKey(cleanNodes)) {
            Set<Long> cachedVisitedNodes = cachedVisited.get(cleanNodes);
            visitedNodes.clear();
            visitedNodes.addAll(cachedVisitedNodes);
            return cachedResults.get(cleanNodes);
        }
        int currMax = 0;
        HashMap<Integer, Set<Long>> bestNodes = new HashMap<>();
        Set<Long> visitedLater = new HashSet<>();
        visitedLater.add(0l);
        for (int currNode: cleanNodes) {
            int currCash = cash[currNode];
            HashSet<Integer> currCleanNodes = new HashSet<>(cleanNodes);
            currCleanNodes.remove(currNode);
            currCleanNodes.removeAll(branches[currNode]);
            if (!currCleanNodes.isEmpty()) {
                currCash += calcMaxMoney(currCleanNodes, cash, branches, cachedResults, visitedLater, cachedVisited);
            }
            if (currCash > currMax) {
                currMax = currCash;
                bestNodes.clear();
                bestNodes.put(currNode, new HashSet<>(visitedLater));
            }
            else if (currCash == currMax) {
                bestNodes.put(currNode, new HashSet<>(visitedLater));
            }
            visitedLater.clear();
            visitedLater.add(0l);
        }

        Set<Long> newVisitedNodes = new HashSet<>();
        for (int bestNode: bestNodes.keySet()) {
            for (long visitedAfterBest: bestNodes.get(bestNode)) {
                long allVisited = visitedAfterBest | (1l << (bestNode-1));
                newVisitedNodes.add(allVisited);
            }
        }
        visitedNodes.clear();
        visitedNodes.addAll(newVisitedNodes);
        if (currMax == 0) {
            visitedNodes.add(0l);
        }

        cachedVisited.put(cleanNodes, newVisitedNodes);
        cachedResults.put(cleanNodes, currMax);
        return currMax;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nodes = in.nextInt();
        int nBranches = in.nextInt();
        List<Integer>[] adjacents = new List[nodes + 1];
        int[] cash = new int[nodes+1];
        HashSet<Integer> cleanNodes = new HashSet<>();
        for (int i = 1; i <= nodes; i++) {
            cash[i] = in.nextInt();
            adjacents[i] = new ArrayList<Integer>();
            cleanNodes.add(i);
        }
        for (int i = 0; i < nBranches; i++) {
            int startNode = in.nextInt();
            int endNode = in.nextInt();
            adjacents[startNode].add(endNode);
            adjacents[endNode].add(startNode);
        }

        Map<Set<Integer>, Integer> cachedResults = new HashMap<>();
        Set<Long> visitedNodes = new HashSet<>();
        visitedNodes.add(0l);
        Map<Set<Integer>, Set<Long>> cachedVisited = new HashMap<>();
        int maxMoney = calcMaxMoney(cleanNodes, cash, adjacents, cachedResults, visitedNodes, cachedVisited);
        int waysOfCollecting = cachedVisited.get(cleanNodes).size();
        System.out.println(maxMoney + " " + waysOfCollecting);
    }
}