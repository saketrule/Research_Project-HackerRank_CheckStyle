import java.util.Scanner;
public class PrimeSieve {
    public static void main(String[] args) { 
        Scanner ob=new Scanner(System.in);
        int c=ob.nextInt();
        while(c>0){
            int n=ob.nextInt();
        boolean[] isPrime = new boolean[n+1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }
        int count=0;
        for (int factor = 2; factor<= n; factor++) {
            
            if (isPrime[factor]) {
                count++;
                for (int j = 1; j*factor<= n; j++) {
                    isPrime[factor*j] = false;
                }
            }
        }
        if(count%2==0){
            System.out.println("Bob");
        }else{
            System.out.println("Alice");
        }
        c--;
        }
    }
}
        
    