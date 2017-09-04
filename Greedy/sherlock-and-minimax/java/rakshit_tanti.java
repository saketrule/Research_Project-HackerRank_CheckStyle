import java.util.*;
class Solution {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        Arrays.sort(arr);
        
        int p = sc.nextInt();
        int q = sc.nextInt();
        int m = 0;
        int maxMinDistance = 0;
        
        if(p < arr[0]) {
            int temp = Math.abs(p - arr[0]);
            if(temp > maxMinDistance) {
                maxMinDistance = temp;
                m = p;
            }
        }
        
        if(q > arr[n - 1]) {
            int temp = Math.abs(q - arr[n - 1]);
            if(temp > maxMinDistance) {
                maxMinDistance = temp;
                m = q;
            }
        }
        int i = 0;
        while(arr[i] < p) {
            i++;
        }
        i = i == 0 ? 1 : i;
        while(i < n) {
            
            int temp_m = arr[i] + arr[i - 1];
            temp_m = temp_m / 2;
            if(temp_m <= q){
                int distance = Math.abs(arr[i - 1] - temp_m);
                if(distance > maxMinDistance) {
                    maxMinDistance = distance;
                    m = temp_m;
                }
                i++;
            } else if(temp_m > q) {
                int distance = Math.abs(arr[i - 1] - q);
                if(distance > maxMinDistance) {
                    maxMinDistance = distance;
                    m = q;
                }
                break;
            }
        }
        System.out.println(m);
    }
}