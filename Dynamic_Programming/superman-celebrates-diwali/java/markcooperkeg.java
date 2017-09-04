import java.util.*;

public class MaxPeople {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numBuildings = s.nextInt();
        int numFloors = s.nextInt();
        int transitDrop = s.nextInt();
        int[][] peopleFloorMap = new int[numFloors][numBuildings];
        for (int i = 0; i < numBuildings; i++) {
            int numPeople = s.nextInt();
            for (int j = 0; j < numPeople; j++) {
                peopleFloorMap[s.nextInt()-1][i]++;
            }
        }
        System.out.println(maxPeople(numBuildings, numFloors,
                                     transitDrop, peopleFloorMap));
    }

    private static int
    maxPeople(int numBuildings, int numFloors,
              int transitDrop, int[][] peopleFloorMap) {
        int[][] maxPeopleSaved = new int[numFloors][numBuildings+1];
        for (int f = 0; f < numFloors; f++) {
            int floorMax = 0;
            for (int b = 0; b < numBuildings; b++) {
                int t = peopleFloorMap[f][b];
                int maxNextStop = 0;
                if (f > 0)
                    maxNextStop = maxPeopleSaved[f-1][b];
                if ((f - transitDrop) >= 0)
                    maxNextStop = Math.max(maxNextStop,
                                           maxPeopleSaved[f-transitDrop][numBuildings]);
                maxPeopleSaved[f][b] = t + maxNextStop;
                floorMax = Math.max(floorMax, maxPeopleSaved[f][b]);
            }
            maxPeopleSaved[f][numBuildings] = floorMax;
        }
        return maxPeopleSaved[numFloors-1][numBuildings];
    }
}