import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int count = Integer.parseInt(scan.nextLine());
        for (int c = 0; c < count; c++) {
            String line = scan.nextLine();

            // Retreive count of people.
            String[] split = line.split(" ");
            int peopleCount = Integer.parseInt(split[0]);

            // Intialize scores.
            int[] scores = new int[peopleCount];
            for (int i = 0; i < peopleCount; i++) {
                scores[i] = Integer.parseInt(split[i + 1]);
            }
            Arrays.sort(scores);
            System.out.println(getSmallestGroup(scores));
        }
    }

    static int getSmallestGroup(int[] people) {
        List<Integer> columns = new ArrayList<>();
        int minCount = Integer.MAX_VALUE;
        if (people.length > 1) {
            int columnIndex = 0;
            int consecutiveCount = 1;
            for (int i = 1; i < people.length; i++) {
                if (people[i] == people[i - 1]) {
                    // same number.
                    
                    consecutiveCount ++;

                } else {
                    // next number or disconnected

                    // fill columns with previous consecutive numbers
                    if (consecutiveCount < columns.size() - columnIndex) {
                        columnIndex = columns.size() - consecutiveCount;
                    }
                    for (int j = 0; j < consecutiveCount; j++) {
                        int index = j + columnIndex;
                        if (columns.isEmpty() || columns.size() - 1 < index) {
                            columns.add(1);
                        } else {
                            columns.set(index, columns.get(index) + 1);
                        }
                    }
                    consecutiveCount = 1;

                }
                // if disconnected
                if (people[i] != people[i - 1] + 1 && people[i] != people[i - 1]) {
                    for (int j = 0; j < columns.size(); j++) {
                        if (columns.get(j) < minCount) {
                            minCount = columns.get(j);
                        }
                    }
                    columnIndex = 0;
                    consecutiveCount = 1;
                    columns.clear();

                }

                // if last.
                if (i == people.length - 1) {
                    if (consecutiveCount < columns.size() - columnIndex) {
                        columnIndex = columns.size() - consecutiveCount;
                    }
                    for (int j = 0; j < consecutiveCount; j++) {
                        int index = j + columnIndex;
                        if (columns.isEmpty() || columns.size() - 1 < index) {
                            columns.add(1);
                        } else {
                            columns.set(index, columns.get(index) + 1);
                        }
                    }
                    for (int j = 0; j < columns.size(); j++) {
                        if (columns.get(j) < minCount) {
                            minCount = columns.get(j);
                        }
                    }
                }
            }
            return minCount;
        } else {
            return people.length;
        }
    }
}