import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] str  = scanner.nextLine().trim().toCharArray();
        int[] left = new int[26];
        int[] required = new int[26];
        for (char c : str) {
            left[c - 'a'] += 1;
        }

        for (int i = 0; i < 26; i++) {
            required[i] = left[i] / 2;
        }
        Stack<Character> stack = new Stack();

        int index = str.length - 1;
        int requiredIndex = getNextIndex(required, 0);;
        while (true) {
            if (requiredIndex == 26 || index < 0) {
                break;
            }
            char xda = str[index];
            if (str[index] - 'a' == requiredIndex || left[str[index] - 'a'] == required[str[index] - 'a']) {
                if(str[index] - 'a' != requiredIndex) {
                    boolean alreadyAdded = false;
                    while(!stack.isEmpty() && stack.peek() <= str[index]) {
                        char x = stack.pop();
                        if(required[x - 'a'] > 0) {

                            if(x != str[index]) {
                                required[x - 'a'] -= 1;
                                System.out.print(x);
                            }
                            else {
                                alreadyAdded = true;
                                break;
                            }
                        }

                    }
                    stack.clear();
                    System.out.print(str[index]);
                    required[str[index] - 'a'] -= 1;
                    if(alreadyAdded) stack.add(str[index]);
                } else {
                    System.out.print(str[index]);
                    stack.clear();
                    required[str[index] - 'a'] -= 1;
                    if(required[requiredIndex] == 0) {
                        requiredIndex = getNextIndex(required, str[index] - 'a');
                    }
                }
            } else {
                char c = str[index];
                if (required[c - 'a'] > 0)addToStack(stack, c);
            }
            left[str[index] - 'a'] -= 1;
            index--;
        }

    }

    private static int getNextIndex(int[] required, int i) {
        for (int j = i; j < 26; j++) {
            if(required[j] > 0) return j;
        }
        return 26;
    }

    private static void addToStack(Stack<Character> stack, char c) {
        Stack<Character> tempStack = new Stack<Character>();
        while(!stack.empty() && stack.peek() <= c) {
            tempStack.add(stack.pop());
        }
        stack.clear();
        stack.add(c);
        while(!tempStack.isEmpty()) stack.add(tempStack.pop());
    }

}