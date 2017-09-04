import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class DemandingMoney2 {

 static boolean[][] roads;
 static int[] C;
 static int N;
 static int M;
 static long maxAmountGlobal = 0;
 static HashMap<Long, Long> memorySol = new HashMap<>();

 public static void main(String[] args)   {

  

   Scanner sc = new Scanner(System.in);
   N = sc.nextInt();
   M = sc.nextInt();
   C = new int[N];
   for (int i = 0; i < N; i++) {
    C[i] = sc.nextInt();
   }
   roads = new boolean[N][N];
   for (int i = 0; i < M; i++) {
    int a = sc.nextInt();
    int b = sc.nextInt();
    roads[a - 1][b - 1] = true;
   }

   long sol[] = DemandingMoney2.solve();
   double time2 = System.currentTimeMillis();
   // System.out.println();
   System.out.println(sol[0] + " " + sol[1]);
  
  

 }

 static boolean hasNeigbor(int house) {
  for (int j = 0; j < N; j++) {
   if (roads[j][house] || roads[house][j]) {
    return true;
   }
  }
  return false;
 }

 static long[] solve() {
  // status false, if house not robbed, true if house has been robbed
  boolean[] statusHouses = new boolean[N];
  // if house has no neigbors, than status house to true if Ci >0
  long amountHousesWithoutNeigbors = 0;
  int countNumOfHouseWithoutMoneyWithoutNeig = 0;
  for (int i = 0; i < N; i++) {
   if (!hasNeigbor(i)) {

    statusHouses[i] = true;
    amountHousesWithoutNeigbors += C[i];
    if (C[i] == 0) {
     countNumOfHouseWithoutMoneyWithoutNeig++;
    }
   }
  }
  if (countNumOfHouseWithoutMoneyWithoutNeig == N) {
   long sol[] = new long[2];
   sol[0] = 0;
   sol[1] =  (long) Math.pow(2, countNumOfHouseWithoutMoneyWithoutNeig) - 1;
   return sol;
  }

  maxAmountGlobal = calcMaxDemandingMoney(statusHouses, -1);
  // statusHouses = new boolean[N];
  memorySol = new HashMap<>();
  long[] dummy = countMaxDemandingMoney(statusHouses, -1, 0, 0);
  maxAmountGlobal += amountHousesWithoutNeigbors;

  dummy[0] = maxAmountGlobal;
  if (dummy[1] != 0) {
   long mult = (long) Math.pow(2, countNumOfHouseWithoutMoneyWithoutNeig);
   dummy[1] *= mult;
  } else {
   if (dummy[1] == 0) {
    dummy[1] = 1;
   } else {
    dummy[1] = (long) Math.pow(2, countNumOfHouseWithoutMoneyWithoutNeig) - 1;
   }
  }
  return dummy;
 }

 private static long[] countMaxDemandingMoney(boolean[] statusHouses, int lastRobbed, long currentAmount, long keyCur) {
  long maxAmount = 0;
  long numOfMaxSol = 0;
  for (int i = lastRobbed + 1; i < N; i++) {
   if (!statusHouses[i]) {
    boolean[] copyStatusHouse = copyArraySetNeighborsAlreadyRobbed(statusHouses, i);
    long copyAmount = 0;
    copyAmount += C[i];
    long key = keyCur;
    key += Math.pow(2, i);

    long partSol[] = new long[2];

    if (copyAmount + currentAmount == maxAmountGlobal) {
     numOfMaxSol++;
    }

    if (key != 0) {
     partSol = countMaxDemandingMoney(copyStatusHouse, i, currentAmount + C[i], key);
     copyAmount += partSol[0];
     numOfMaxSol += partSol[1];
     if (copyAmount >= maxAmount) {
      maxAmount = copyAmount;
     }
    }
   }
  }
  long[] sol = new long[2];
  sol[0] = maxAmount;
  sol[1] = numOfMaxSol;
  return sol;
 }

 private static long calcMaxDemandingMoney(boolean[] statusHouses, int lastRobbed) {
  long maxAmount = 0;
  for (int i = lastRobbed + 1; i < N; i++) {
   if (!statusHouses[i]) {
    boolean[] copyStatusHouse = copyArraySetNeighborsAlreadyRobbed(statusHouses, i);
    long copyAmount = 0;
    copyAmount += C[i];
    long key = calcKey(copyStatusHouse, i);
    long partSol = 0;
    if (key != 0) {
     if (memorySol.containsKey(key)) {
      partSol = memorySol.get(key);
     } else {
      partSol = calcMaxDemandingMoney(copyStatusHouse, i);
      memorySol.put(key, partSol);
     }
     copyAmount += partSol;
    }
    if (copyAmount >= maxAmount) {
     maxAmount = copyAmount;
    }
   }
  }

  return maxAmount;
 }

 private static long calcKey(boolean[] statusHouses, int lastRobbed) {
  long key = 0;
  for (int i = lastRobbed; i < statusHouses.length; i++) {
   if (!statusHouses[i]) {
    key += Math.pow(2, i);
   }
  }
  return key;

 }

 private static boolean[] copyArraySetNeighborsAlreadyRobbed(boolean[] arrayToCopy, int robbedHouse) {
  boolean[] copy = new boolean[arrayToCopy.length];
  for (int i = 0; i < copy.length; i++) {
   if (arrayToCopy[i]) {
    copy[i] = true;
   }
   if (i == robbedHouse) {
    copy[i] = true;
   }
   if (roads[robbedHouse][i] || roads[i][robbedHouse]) {
    copy[i] = true;
   }
  }
  return copy;
 }
}