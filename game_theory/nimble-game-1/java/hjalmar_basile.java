import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            scanner.nextInt(); // We don't really need c_0
            
            int nimsum = 0;
            for (int k = 1; k < n; k++) {
                if(scanner.nextInt() % 2 != 0)
                    nimsum ^= k;                    
            }
            System.out.println(nimsum != 0 ? "First" : "Second");            
        }
        scanner.close();
    }
    
}