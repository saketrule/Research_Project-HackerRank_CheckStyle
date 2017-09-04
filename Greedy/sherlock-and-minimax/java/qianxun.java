import java.util.Arrays;
import java.util.Scanner;

public class Solution {

 public static void main(String[] args) {
  
  Scanner scanner = new Scanner(System.in);
  int n = scanner.nextInt();
  long[] num = new long[n];
  for (int i = 0; i < n; i++) {
   num[i] = scanner.nextLong();
  }
  Arrays.sort(num);
  long start = scanner.nextLong();
  long end = scanner.nextLong();
  int startIndex = -1;
  int endIndex = -1;
  
  for (int i = 0; i < n; i++) {
   if (num[i] >= start) {
    startIndex = i;
    break;
   } 
  }
  if (startIndex == -1) {
   startIndex = n;
  }
  for (int i = 0; i < n; i++) {
   if (num[i] <= end) {
    endIndex = i;
   } else {
    break;
   }
  }
  long min = 0;
  //System.out.println("start: " + startIndex + " end : " + endIndex);
  if (startIndex == n) {
   min = end;
  } else if (endIndex == -1) {
   min = start;
  } else if (startIndex == 0 && endIndex < n - 1) {
   long max = num[0] - start;
   min = start;
   for (int i = 0; i < endIndex; i++) {
    long median = (num[i] + num[i + 1]) / 2;
    if (median - num[i] > max) {
     max = median - num[i];
     min = median;
    }
   }
   long median = (num[endIndex] + num[endIndex + 1]) / 2;
   if (end >= median) {
    if (median - num[endIndex] > max) {
     max = median - num[endIndex];
     min = median;
    }
   } else {
    if (end - num[endIndex] > max) {
     max = end - num[endIndex];
     min = end;
    }
   }   
  } else if (startIndex > 0 && endIndex == n - 1) {
   long median = (num[startIndex] + num[startIndex - 1]) / 2;
   long max;
   if (start <= median) {
    max = median - num[startIndex - 1];
    min = median;
   } else {
    max = num[startIndex] - start;
    min = start;
   }
   for (int i = startIndex; i < n - 1; i++) {
    median = (num[i] + num[i + 1]) / 2;
    if (median - num[i] > max) {
     max = median - num[i];
     min = median;
    }
   }
   if (end - num[n - 1] > max) {
    min = end;
   }   
  } else if (startIndex > 0 && endIndex < n - 1) {
   long median = (num[startIndex] + num[startIndex - 1]) / 2;
   long max;
   if (start <= median) {
    max = median - num[startIndex - 1];
    min = median;
   } else {
    max = num[startIndex] - start;
    min = start;
   }
   for (int i = startIndex; i < endIndex; i++) {
    median = (num[i] + num[i + 1]) / 2;
    if (median - num[i] > max) {
     max = median - num[i];
     min = median;
    }
   }
   median = (num[endIndex] + num[endIndex + 1]) / 2;
   if (end >= median) {
    if (median - num[endIndex] > max) {
     max = median - num[endIndex];
     min = median;
    }
   } else {
    if (end - num[endIndex] > max) {
     max = end - num[endIndex];
     min = median;
    }
   }
  } else if (startIndex ==0 && endIndex == n - 1){
   long max = num[0] - start;
   min = start;
   for (int i = 0; i < n - 1; i++) {
    long median = (num[i] + num[i + 1]) / 2;
    if (median - num[i] > max) {
     max = median - num[i];
     min = median;
    }
   }
   if (end - num[n - 1] > max) {
    min = end;
   }
  }
  System.out.println(min);
 }

}