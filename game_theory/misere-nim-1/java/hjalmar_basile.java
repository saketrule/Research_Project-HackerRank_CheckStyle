import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int g = scanner.nextInt();
        
        for (int i = 0; i < g; i++) {
            int n = scanner.nextInt();
            int count = 0;
            int s = scanner.nextInt();
            if(s > 1)
                count = 1;
            for (int k = 1; k < n; k++) {
                int s_k = scanner.nextInt();
                if(s_k > 1)
                    count = 1;
                s ^= s_k;
            }
            if(count == 1) {
                System.out.println(s !=0 ? "First" : "Second");    
            } else {
                System.out.println((n % 2 == 0) ? "First" : "Second");
            }
        }
    }
    
}