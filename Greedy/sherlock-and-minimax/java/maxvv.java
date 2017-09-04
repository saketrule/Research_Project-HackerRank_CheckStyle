import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int[] a=new int[n];
        for (int i=0; i<n; i++) {
            a[i]=in.nextInt();
        }
        int p=in.nextInt();
        int q=in.nextInt();
        Arrays.sort(a);        
        int inf=0;
        while (a[inf]<p && inf<n) {
            inf++;
        }
        if (inf==n) {
            System.out.println(0);
            return;
        }
        int sup=n-1;
        while (a[sup]>q && sup>=inf) {
            sup--;
        }
        if (sup<inf) {
            System.out.println(0);
            return;            
        }
        int max=0;
        int maxVal=p;
        if (inf==0) {
            max=a[inf]-p;
        } else {
            int mid=(a[inf]+a[inf-1])/2;
            if (mid<p) {
                mid=p;
            }
            max=a[inf]-mid;
            maxVal=mid;
        }
        if (sup==n-1) {
            if (q-a[sup]>max) {
                max=q-a[sup];
                maxVal=q;
            }            
        } else {
            int mid=(a[sup]+a[sup+1])/2;
            if (mid>q) {
                mid=q;
            }
            if (mid-a[sup]>max) {
                max=mid-a[sup];
                maxVal=mid;
            }            
        }
        for (int i=inf; i<sup; i++) {
            int val=(a[i]+a[i+1])/2;
            if (val-a[i]>max) {
                max=val-a[i];
                maxVal=val;
            }
        }
        System.out.println(maxVal);
    }
}