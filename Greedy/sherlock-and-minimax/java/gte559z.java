import java.util.*;

public class Solution {


    private static DistanceObject fDistanceObject;

    public static void main(String[] arguments) {

        Scanner scanner = new Scanner(System.in);
        int sizeOfData = scanner.nextInt();
        int[] data = new int[sizeOfData];

        buildDataArray(data, scanner, sizeOfData);

        int lowOfRange = scanner.nextInt();
        int highOfRange = scanner.nextInt();

        int lowBoundIndexOfData = calculateLowBound(data, lowOfRange);

        fDistanceObject = new DistanceObject(Integer.MIN_VALUE, lowOfRange);
               
        // 1. Deal with off the left side
        int highBoundIndexOfLowRange  = findHighBoundIndex(data, lowOfRange);

        if (isWithInBounds(data, lowOfRange)) {
            for(int value = lowOfRange; value <= data[highBoundIndexOfLowRange]; value++){
                DistanceObject distanceObject = findMinDistanceToItemInArray(data, value);

                if (fDistanceObject.isGreaterDistance(distanceObject.distance)) {
                    fDistanceObject.resetObject(distanceObject);
                }
            }
        } else {
            int distanceFromEdge = Math.abs(lowOfRange - data[0]);

            if (fDistanceObject.isGreaterDistance(distanceFromEdge)) {
                fDistanceObject.resetObject(distanceFromEdge,lowOfRange);
            }
        }

        int highBoundIndexOfData  = findHighBoundIndex(data, highOfRange);

        // 2. Only need to iterate through the data items, while calculating distance 
        // of the distance
        // between the sorted items and dividing by 2. 
        for (int i = lowBoundIndexOfData + 1; i < highBoundIndexOfData; i++) {
            int distance = Math.abs(data[i] - data[i - 1]) / 2;

            if (fDistanceObject.isGreaterDistance(distance)) {
                fDistanceObject.resetObject(distance, distance + data[i - 1]);
            }
        }

        // 3. Deal with off the right side
        if (isWithInBounds(data, highOfRange)) {
           
            highBoundIndexOfData = findHighBoundIndex(data, highOfRange);

            // 2a, get distances for item leaning over the side
            int lowSideOfHeight = findLowBoundIndexInArray(data, highOfRange);
                        
            for (int valueInRange = data[lowSideOfHeight]; valueInRange <= highOfRange;
                 valueInRange++) {
                
                DistanceObject distanceObject = findMinDistanceToItemInArray(data, valueInRange);

                if (fDistanceObject.isGreaterDistance(distanceObject.distance)) {
                    fDistanceObject.resetObject(distanceObject);
                }
            }

        } else {
            highBoundIndexOfData = data.length - 1;

            int distanceFromEdge = Math.abs(highOfRange - data[data.length - 1]);
            if (fDistanceObject.isGreaterDistance(distanceFromEdge)) {
                fDistanceObject.resetObject(distanceFromEdge,highOfRange);
            }

        }
        
        System.out.println(fDistanceObject.itemAtDistance);
    }

    private static int calculateLowBound(int[] data, int lowOfRange) {
        int lowBoundIndexOfData;
        if (isWithInBounds(data, lowOfRange)) {
            lowBoundIndexOfData = findHighBoundIndex(data, lowOfRange);
        } else {
            lowBoundIndexOfData = 0;
        }

        return lowBoundIndexOfData;
    }


    private static DistanceObject findMinDistanceToItemInArray(int[] data, int value) {
        int lowBound = findLowBoundIndexInArray(data, value);
        int minValue = 0;
        int indexOfMin = 0;

        if (lowBound == data.length - 1) {
            minValue = data[lowBound] - value;
            indexOfMin = lowBound;
        } else {
            if (Math.abs(data[lowBound] - value) > Math.abs(data[lowBound + 1] - value)) {
                minValue = Math.abs(data[lowBound + 1] - value);
                indexOfMin = lowBound + 1;
            } else {
                minValue = Math.abs(data[lowBound] - value);
                indexOfMin = lowBound;
            }
        }

        return new DistanceObject(Math.abs(minValue), value);
    }

    private static class DistanceObject {
        private int distance;
        private int itemAtDistance;

        private DistanceObject(int distance, int itemAtDistance) {
            this.distance = distance;
            this.itemAtDistance = itemAtDistance;
        }

        private boolean isGreaterDistance(int distance){
            return distance > this.distance;
        }

        private void resetObject(int distance, int itemAtDistance) {
            this.distance = distance;
            this.itemAtDistance = itemAtDistance;
        }

        public void resetObject(DistanceObject distanceObject) {
            this.distance = distanceObject.distance;
            this.itemAtDistance = distanceObject.itemAtDistance;
        }
        
        public String toString(){
            return "Distance: " + this.distance + " Item: " + this.itemAtDistance;
        }
    }

    private static boolean isWithInBounds(int[] data, int value) {
        return data[0] <= value && data[data.length - 1] >= value;
    }

    private static int findHighBoundIndex(int[] data, int value) {
        int low = 0;
        int high = data.length - 1;

        while (low <= high) {
            int middle = (high - low) / 2 + low;

            if (data[middle] == value) {
                return middle;
            } else if (data[middle] > value) {
                if (middle > 0 && data[middle - 1] < value) {
                    return middle;
                } else {
                    high = middle - 1;
                }
            } else {
                low = middle + 1;
            }
        }

        return low;
    }

    private static int findLowBoundIndexInArray(int[] data, int value) {

        int low = 0;
        int high = data.length - 1;

        int mid = (high - low) / 2 + low;

        while (mid >= 0 && mid <= data.length) {
            mid = (high - low) / 2 + low;

            if (mid == 0) {
                return 0;
            } else if (mid > data.length - 1) {
                return mid - 1;
            } else if (data[mid] < value) {
                low = mid + 1;
            } else if (data[mid] >= value && data[mid - 1] <= value) {
                return mid - 1;
            } else if (data[mid - 1] > value) {
                high = mid - 1;
            }
        }

        return mid == 0 ? mid : mid - 1;
    }

    private static void buildDataArray(int[] data, Scanner scanner, int sizeOfData) {
        // Build array
        for (int i = 0; i < sizeOfData; i++) {
            data[i] = scanner.nextInt();
        }

        Arrays.sort(data);
    }
}