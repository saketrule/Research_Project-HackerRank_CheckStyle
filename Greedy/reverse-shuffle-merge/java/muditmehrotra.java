import java.util.Scanner;

/**
 * Created by mudit on 5/26/15.
 */
public class ReverseShuffleMerge
{
    public static String findA(String s)
    {
        String sRev = new StringBuffer(s).reverse().toString();
        int []d = new int[10010];
        int[] c = new int[26];
        int[] w = new int[26];
        int n = sRev.length();

        for (int i = 0; i < n; i++)
            d[i] = sRev.charAt(i) - 'a';

        for (int i = 0; i < n; i++)
            c[d[i]]++;

        for (int i = 0; i < 26; i++)
            w[i] = c[i]/2;

        int[] ret = new int[n/2];

        for (int i = 0; 2*i < n; i++)
        {
            for (int k = 0; k < 26; k++) {
                ret[i] = k;
                int p = 0;
                int l = 0;
                for (int j = 0; j < n; j++) {
                    if (ret[p] == d[j]) {
                        p++;
                        l = j;
                        if (p > i) break;
                    }
                }
                if(p <= i)
                    continue;

                int want[] = new int[26];

                for(int j = 0; j < 26; j++)
                    want[j] = w[j];

                for(int j = 0; j <= i; j++)
                    want[ret[j]]--;

                int[] have = new int[26];

                for(int j = l + 1; j < n; j++)
                    have[d[j]]++;

                boolean ok = true;

                for(int j = 0; j < 26; j++)
                    if(want[j] < 0 || want[j] > have[j])
                        ok = false;

                if(ok)
                    break;
            }
        }

        StringBuffer  sb = new StringBuffer();
        for(int i = 0; 2 * i < n; i++)
            sb.append((char) (ret[i] + 'a'));

        return sb.toString();


    }
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.nextLine();
        System.out.println(findA(S));
    }
}