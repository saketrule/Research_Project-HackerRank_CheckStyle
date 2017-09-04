import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution { 

 public int formTeamsDups(int[] arr) {
  if (arr.length == 0)
   return 0;
  int minSize = Integer.MAX_VALUE;
  Arrays.sort(arr);
  Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

  int sizeOfSeqToAddTo = 0;
  for (int index = 0; index < arr.length; index++) {
   int curValue = arr[index];
   int predValue = curValue - 1;
   if (map.containsKey(predValue)) {
    sizeOfSeqToAddTo = map.get(predValue).remove(0);
    if (map.get(predValue).size() == 0) {
     map.remove(predValue);
    }
   } else {
    sizeOfSeqToAddTo = 0;
   }
   if (map.containsKey(curValue)) {
    int listIndex = getIndexToInsert(map.get(curValue),
      sizeOfSeqToAddTo + 1);
    List<Integer> list = map.get(curValue);
    if (listIndex + 1 > list.size()) {
     list.add(sizeOfSeqToAddTo + 1);
    } else {
     list.add((listIndex + 1), sizeOfSeqToAddTo + 1);
    }
   } else {
    List<Integer> list = new ArrayList<Integer>();
    list.add(sizeOfSeqToAddTo + 1);
    map.put(curValue, list);
   }
  }

  for (Integer key : map.keySet()) {
   List<Integer> list = map.get(key);
   for (int value : list) {
    if (value < minSize) {
     minSize = value;
    }
   }
  }
  return minSize;
 }

 // 1. index of any occurance
 // 2. index of first element greater than ele
 public static int getIndexToInsert(List<Integer> list, int ele) {
  return getIndexToInsert(list, 0, list.size() - 1, ele);
 }

 private static int getIndexToInsert(List<Integer> list, int first,
   int last, int ele) {
  if (first > last) {
   return -1;
  }

  if (first == last) {
   if (list.get(first) == ele) {
    return first;
   } else if (list.get(first) > ele) {
    return first - 1;
   } else {
    return first + 1;
   }
  } else {
   int mid = (first + last) / 2;
   if (list.get(mid) == ele) {
    return mid;
   } else if (list.get(mid) > ele) {
    return getIndexToInsert(list, first, mid - 1, ele);
   } else {
    return getIndexToInsert(list, mid + 1, last, ele);
   }
  }
 }

 public static void main_hr() {
  try {
   BufferedReader br = new BufferedReader(new InputStreamReader(
     System.in));

   String input;
   String[] arrStr;
   int[] arr;
   int count = 0;
   while ((input = br.readLine()) != null) {
    if (count > 0) {
     arrStr = input.split(" ");
     arr = new int[arrStr.length - 1];
     for (int i = 1; i < arrStr.length; i++) {
      arr[i - 1] = Integer.parseInt(arrStr[i].trim());
     }
     System.out.println(new Solution().formTeamsDups(arr));
    }
    count++;
   }

  } catch (IOException io) {
   io.printStackTrace();
  }
 }

 
 /**
  * @param args
  */
 public static void main(String[] args) {
  // System.out.println(new Solution().formTeams(new int[] {1,2, 32, 31,
  // 3,4, 6,7, 8, 9}));
  main_hr();
  /*
   * List<Integer> list = new ArrayList<Integer>(); list.add(1);
   * list.add(3); list.add(3); list.add(5); list.add(6); list.add(7);
   * list.add(8); System.out.println(getIndexToInsert(list, 9));
   */

 }

}