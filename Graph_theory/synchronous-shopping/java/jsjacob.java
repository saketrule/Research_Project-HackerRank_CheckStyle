import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    private static Map<Integer, ShoppingCenter> shoppingCenters;
    private static int N;
    private static int K;
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        
        N = n;
        K = k;
        
        shoppingCenters = new HashMap<Integer, ShoppingCenter>();

        // Initialize shopping centers
        for (int i = 1; i <= n; i++) {
            ShoppingCenter sc = new ShoppingCenter();
            sc.number = i;
            sc.isLast = (i == n);
            sc.fishAvailable = new HashSet<Integer>();
            sc.roads = new HashMap<Integer, Road>();
            shoppingCenters.put(sc.number, sc);
        }

        // Read and save fish types
        for (int i = 1; i <= n; i++) {
            ShoppingCenter sc = shoppingCenters.get(i);
            
            int typeCount = in.nextInt();
            for (int j = 0; j < typeCount; j++) {
                int type = in.nextInt();
                sc.fishAvailable.add(type);
            }
        }

        // Read and save roads and travel time
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int t = in.nextInt();
            
            ShoppingCenter scx = shoppingCenters.get(x);
            Road rx = new Road();
            rx.nextShoppingCenter = y;
            rx.travelTime = t;
            rx.travelTimeToLastShoppingCenter = -1;
            scx.roads.put(y, rx);
            
            ShoppingCenter scy = shoppingCenters.get(y);
            Road ry = new Road();
            ry.nextShoppingCenter = x;
            ry.travelTime = t;
            ry.travelTimeToLastShoppingCenter = -1;
            scy.roads.put(x, ry);            
        }
        
        // Calculate and save travel time to last shopping center
        ShoppingCenter scn = shoppingCenters.get(N);
        markTravelTimeToLastShoppingCenter(scn, 0);
        
        ShoppingCenter scFirst = shoppingCenters.get(1);
        int travelTime = findShortestTripUnderMax(scFirst, new HashSet<Integer>(), 0, -1);
        System.out.println(travelTime);
    }
    
    public static int findShortestTripUnderMax(ShoppingCenter sc, Set<Integer> types, int runningTime, int maxTime) {
        // System.out.println("findShortestTripUnderMax: " + sc.number + ", " + types.size() + ", " + runningTime + ", " + maxTime);

        Set<Integer> nextTypes = new HashSet<Integer>();
        nextTypes.addAll(types);
        nextTypes.addAll(sc.fishAvailable);
        
        // Sort roads by travel time to last shopping center  
        ArrayList<Road> roads = new ArrayList<Road>();
        roads.addAll(sc.roads.values());
        Collections.sort(roads, new RoadTravelTimeToLastShoppingCenterComparator());

        // Check if have all fish types
        if (nextTypes.size() == K) {
            int totalTime = runningTime + roads.get(0).travelTimeToLastShoppingCenter;
            
            // Is this Big or Little?
            if (maxTime < 0) {
                // This is Big
                ShoppingCenter scFirst = shoppingCenters.get(1);
                int tripTime = findShortestTripUnderMax(scFirst, nextTypes, 0, totalTime);
                return tripTime;
            } else {
                // This is Little
                return Math.max(maxTime, totalTime);
            }
        }
        
        // Check if exceeded max time
        if ((maxTime > 0) && (runningTime > maxTime))
            return -1;

        // Check if reached last shopping center (only matter if this is Big)
        if ((maxTime < 0) && (sc.isLast)) {
            ShoppingCenter scFirst = shoppingCenters.get(1);
            int tripTime = findShortestTripUnderMax(scFirst, nextTypes, 0, runningTime);
            if (tripTime >= 0)
                return tripTime;
        }
        
        for (Road r : roads) {
            ShoppingCenter scNext = shoppingCenters.get(r.nextShoppingCenter);
            int nextRunningTime = runningTime + r.travelTime;
            int tripTime = findShortestTripUnderMax(scNext, nextTypes, nextRunningTime, maxTime);
            if (tripTime >= 0)
                return tripTime;
        }
        
        return -1;
    }
    
    public static void markTravelTimeToLastShoppingCenter(ShoppingCenter sc, int runningTime) {
        // System.out.println("markTravelTimeToLastShoppingCenter: " + sc.number + ", " + runningTime);
        
        Set<Integer> nextShoppingCenters = new HashSet<Integer>();
        for (Integer i : sc.roads.keySet()) {
            Road rLocal = sc.roads.get(i);
            // System.out.println(" Local: " + rLocal.nextShoppingCenter + ": " + rLocal.travelTime + " / " + rLocal.travelTimeToLastShoppingCenter);

            ShoppingCenter scRemote = shoppingCenters.get(rLocal.nextShoppingCenter);
            Road rRemote = scRemote.roads.get(sc.number);

            int travelTimeToLastShoppingCenter = runningTime + rRemote.travelTime;

            // System.out.println(" Remote: " + rRemote.nextShoppingCenter + ": " + rRemote.travelTime + " / " + rLocal.travelTimeToLastShoppingCenter + " ==> " + travelTimeToLastShoppingCenter);
            
            if (rRemote.travelTimeToLastShoppingCenter < 0 || rRemote.travelTimeToLastShoppingCenter > travelTimeToLastShoppingCenter) {
                rRemote.travelTimeToLastShoppingCenter = travelTimeToLastShoppingCenter;
                nextShoppingCenters.add(i);
            }
        }
        
        for (Integer i : nextShoppingCenters) {
            ShoppingCenter scNext = shoppingCenters.get(i);
            Road rNext = scNext.roads.get(sc.number);
            markTravelTimeToLastShoppingCenter(scNext, rNext.travelTimeToLastShoppingCenter);
        }
    }
    
    public static class Road {
        int nextShoppingCenter;
        int travelTime;
        int travelTimeToLastShoppingCenter;
    }
    
    private static class RoadTravelTimeToLastShoppingCenterComparator implements Comparator<Road> {
        @Override
        public int compare(Road a, Road b) {
            return (a.travelTimeToLastShoppingCenter < b.travelTimeToLastShoppingCenter) ? -1 : (a.travelTimeToLastShoppingCenter == b.travelTimeToLastShoppingCenter) ? 0 : 1;
        }
    }

    public static class ShoppingCenter {
        int number;
        boolean isLast;
        Set<Integer> fishAvailable;
        Map<Integer, Road> roads;
    }
}