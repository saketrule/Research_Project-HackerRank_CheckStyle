import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            scanner.nextInt(); // We don't really need k
            int s = 0;
            for (int j = 0; j < n; j++) {
                s ^= scanner.nextInt();
            }
            System.out.println(s !=0 ? "First" : "Second");
        }
        scanner.close();
    }
    
}