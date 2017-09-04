import java.util.Scanner;


public class Solution {
 
 static char[][] floor;
 static int height;
 static int width;
 static long[][] res;
 static int currentLayer, currentProfile, nextProfile;
 
 public static boolean isBlockedNextLayer(int cell, int layer, int currentProfile, int nextProfile) {
  if (cell >= height || cell < 0) return true;
  if (layer >= width || layer < 0) return true;
  return floor[cell][layer] == '#' || (currentProfile & (1 << (cell + height))) != 0 || (nextProfile & (1 << cell)) != 0;
 }
 
 public static boolean isBlockedCurrentLayer(int cell, int layer, int currentProfile) {
  if (cell >= height || cell < 0) return true;
  if (layer >= width || layer < 0) return true;
  return floor[cell][layer] == '#' || (currentProfile & (1 << cell)) != 0;
 }
 
 public static boolean isBlockedNextNextLayer(int cell, int layer, int nextProfile) {
  if (cell >= height || cell < 0) return true;
  if (layer >= width || layer < 0) return true;
  return floor[cell][layer] == '#' || (nextProfile & (1 << (cell + height))) != 0;
 }
 
 public static void search(int currentCell) {
  
  if (currentCell == height) {
   res[nextProfile][currentLayer + 1] = (res[nextProfile][currentLayer + 1] + res[currentProfile][currentLayer]) % 1000000007;
   return;
  }
  
  if (isBlockedCurrentLayer(currentCell, currentLayer, currentProfile)) {
   search(currentCell + 1);
   return;
  }
  
  // 1
  if (!isBlockedCurrentLayer(currentCell + 1, currentLayer, currentProfile) &&
   !isBlockedCurrentLayer(currentCell + 2, currentLayer, currentProfile) &&
   !isBlockedNextLayer(currentCell, currentLayer + 1, currentProfile, nextProfile)) {
   nextProfile ^= 1 << currentCell;
   search(currentCell + 3);
   nextProfile ^= 1 << currentCell;
  }
  
  // 2
  if (!isBlockedCurrentLayer(currentCell + 1, currentLayer, currentProfile) &&
   !isBlockedCurrentLayer(currentCell + 2, currentLayer, currentProfile) &&
   !isBlockedNextLayer(currentCell + 2, currentLayer + 1, currentProfile, nextProfile)) {
   nextProfile ^= 1 << (currentCell + 2);
   search(currentCell + 3);
   nextProfile ^= 1 << (currentCell + 2);
  }
  
  // 3
  if (!isBlockedNextLayer(currentCell + 1, currentLayer + 1, currentProfile, nextProfile) &&
   !isBlockedNextLayer(currentCell + 2, currentLayer + 1, currentProfile, nextProfile) &&
   !isBlockedNextLayer(currentCell, currentLayer + 1, currentProfile, nextProfile)) {
   nextProfile ^= 1 << currentCell;
   nextProfile ^= 1 << (currentCell + 1);
   nextProfile ^= 1 << (currentCell + 2);
   search(currentCell + 1);
   nextProfile ^= 1 << currentCell;
   nextProfile ^= 1 << (currentCell + 1);
   nextProfile ^= 1 << (currentCell + 2);
  }
  
  // 4
  if (!isBlockedNextLayer(currentCell - 1, currentLayer + 1, currentProfile, nextProfile) &&
   !isBlockedNextLayer(currentCell - 2, currentLayer + 1, currentProfile, nextProfile) &&
   !isBlockedNextLayer(currentCell, currentLayer + 1, currentProfile, nextProfile)) {
   nextProfile ^= 1 << currentCell;
   nextProfile ^= 1 << (currentCell - 1);
   nextProfile ^= 1 << (currentCell - 2);
   search(currentCell + 1);
   nextProfile ^= 1 << currentCell;
   nextProfile ^= 1 << (currentCell - 1);
   nextProfile ^= 1 << (currentCell - 2);
  }
  
  // 5
  if (!isBlockedCurrentLayer(currentCell + 1, currentLayer, currentProfile) &&
   !isBlockedNextLayer(currentCell + 1, currentLayer + 1, currentProfile, nextProfile) &&
   !isBlockedNextNextLayer(currentCell + 1, currentLayer + 2, nextProfile)) {
   nextProfile ^= 1 << (currentCell + 1);
   nextProfile ^= 1 << ((currentCell + 1 + height));
   search(currentCell + 2);
   nextProfile ^= 1 << (currentCell + 1);
   nextProfile ^= 1 << ((currentCell + 1 + height));
  }
  
  // 6
  if (!isBlockedNextNextLayer(currentCell - 1, currentLayer + 2, nextProfile) &&
   !isBlockedNextLayer(currentCell, currentLayer + 1, currentProfile, nextProfile) &&
   !isBlockedNextNextLayer(currentCell, currentLayer + 2, nextProfile)) {
   nextProfile ^= 1 << (currentCell);
   nextProfile ^= 1 << (currentCell + height);
   nextProfile ^= 1 << ((currentCell - 1 + height));
   search(currentCell + 1);
   nextProfile ^= 1 << (currentCell);
   nextProfile ^= 1 << (currentCell + height);
   nextProfile ^= 1 << ((currentCell - 1 + height));
  }
  
  // 7
  if (!isBlockedCurrentLayer(currentCell + 1, currentLayer, currentProfile) &&
   !isBlockedNextLayer(currentCell, currentLayer + 1, currentProfile, nextProfile) &&
   !isBlockedNextNextLayer(currentCell, currentLayer + 2, nextProfile)) {
   nextProfile ^= 1 << currentCell;
   nextProfile ^= 1 << (currentCell + height);
   search(currentCell + 2);
   nextProfile ^= 1 << currentCell;
   nextProfile ^= 1 << (currentCell + height);
  }
  
  // 8
  if (!isBlockedNextNextLayer(currentCell + 1, currentLayer + 2, nextProfile) &&
   !isBlockedNextLayer(currentCell, currentLayer + 1, currentProfile, nextProfile) &&
   !isBlockedNextNextLayer(currentCell, currentLayer + 2, nextProfile)) {
   nextProfile ^= 1 << (currentCell);
   nextProfile ^= 1 << (currentCell + height);
   nextProfile ^= 1 << ((currentCell + 1) + height);
   search(currentCell + 1);
   nextProfile ^= 1 << (currentCell);
   nextProfile ^= 1 << (currentCell + height);
   nextProfile ^= 1 << ((currentCell + 1) + height);
  }
 }
 
 public static void main(String[] args) {
  Scanner scanner = new Scanner(System.in);
  int numberOfTestCases = scanner.nextInt();
  
  for (int i = 0; i < numberOfTestCases; i++) {
   width = scanner.nextInt();
   height = scanner.nextInt();
   
   floor = new char[height][width];
   
   for (int j = 0; j < width; j++) {
    String nextLine = scanner.next();
    for (int k = 0; k < height; k++) {
     floor[k][j] = nextLine.charAt(k);
    }
   }
   
   res = new long [1 << (height * 2)][width + 1];
   res[0][0] = 1;
   
   for (currentLayer = 0; currentLayer < width; currentLayer++) {
    for (currentProfile = 0; currentProfile < res.length; currentProfile++) {
     if (res[currentProfile][currentLayer] == 0) continue;
     nextProfile = currentProfile >> height;
     search(0);
    }
   }
   
   /*for (int j = 0; j < res.length; j++) {
    
    for (int k = 0; k < res[0].length; k++) {
     System.out.print(res[j][k] + "  ");
    }
    System.out.println();
   }*/
   
   long answer = res[0][width];
   System.out.println(answer);
  }
  
  scanner.close();
 }

}