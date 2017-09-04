import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt(),n,c=0,i;
        int a[]=new int[(int)1e5];
        for(i=1;i<1e5;i++){
            if(isPrime(i+1))
                c++;
            a[i]=c;
        }
        while(g-->0){
            n=in.nextInt();
            System.out.println((a[n-1]%2==0)?"Bob":"Alice");
        }
    }
    private static boolean isPrime(int n){
        int s=(int)Math.sqrt(n);
        for(int i=2;i<s+1;i++)
            if(n%i==0)
            return false;
        return true;
    }
}