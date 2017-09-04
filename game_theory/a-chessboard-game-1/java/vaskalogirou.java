import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static List<String> total = new ArrayList<String>();
 public static List<String> losers = new ArrayList<String>();
 public static List<String> winners = new ArrayList<String>();
    public static void main(String[] args) {
  losers.add("1-1");
  losers.add("1-2");
  losers.add("2-1");
  losers.add("2-2");
  total.addAll(losers);
  while (changeFound()) {
  }
  Scanner in = new Scanner(System.in);
  int n = in.nextInt();
  for (int i = 0; i < n; i++) {
   int x = in.nextInt();
   int y = in.nextInt();
   String temp = x + "-" + y;
   if (losers.contains(temp)) {
    System.out.println("Second");
   }
   else {
    System.out.println("First");
   }
  }
 }

 public static boolean changeFound() {
  for (int i = 1; i <= 15; i++) {
   for (int j = 1; j <= 15; j++) {
    String temp = i + "-" + j;
    if (total.contains(temp)) {
     continue;
    }
    boolean one = isLoser(temp);
    if (one) {
     losers.add(temp);
     total.add(temp);
     return true;
    }
    boolean two = isWinner(temp);
    if (two) {
     winners.add(temp);
     total.add(temp);
     return true;
    }
   }
  }
  return false;
 }

 public static boolean isWinner(String str) {
  List<String> possibleMoves = getPossibleMoves(str);
  for (String foo : possibleMoves) {
   if (losers.contains(foo)) {
    return true;
   }
  }
  return false;
 }

 public static boolean isLoser(String str) {
  List<String> possibleMoves = getPossibleMoves(str);
  for (String foo : possibleMoves) {
   if (!winners.contains(foo)) {
    return false;
   }
  }
  return true;
 }

 public static List<String> getPossibleMoves(String str) {
  List<String> result = new ArrayList<String>();
  String[] tokens = str.split("-");
  int x = Integer.parseInt(tokens[0]);
  int y = Integer.parseInt(tokens[1]);
  if (x - 2 >= 1 && y + 1 <= 15) {
   String one = String.valueOf(x - 2) + "-" + String.valueOf(y + 1);
   result.add(one);
  }
  if (x - 2 >= 1 && y - 1 >= 1) {
   String two = String.valueOf(x - 2) + "-" + String.valueOf(y - 1);
   result.add(two);
  }
  if (x + 1 <= 15 && y - 2 >= 1) {
   String three = String.valueOf(x + 1) + "-" + String.valueOf(y - 2);
   result.add(three);
  }
  if (x - 1 >= 1 && y - 2 >= 1) {
   String four = String.valueOf(x - 1) + "-" + String.valueOf(y - 2);
   result.add(four);
  }
  return result;
 }
}