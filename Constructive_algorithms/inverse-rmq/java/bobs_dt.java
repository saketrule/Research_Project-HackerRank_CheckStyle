import java.util.*;

public class Solution {

    private int n;
    private int p;

    private List<TreeSet<Integer>> levels;

    boolean debug = false;

    private void index(int n, Scanner in){
        this.n = n;

        p = getPower(n);

        Map<Integer, Integer> countMap = new TreeMap<>();
        for(int i=0; i<2*n-1; i++){
            int a = in.nextInt();
            Integer count = countMap.get(a);
            if(count == null){
                count = 0;
            }
            count++;
            countMap.put(a, count);
        }


        levels = new ArrayList<>(p+1);
        for(int i=0; i<=p; i++){
            levels.add(new TreeSet<Integer>());
        }

        for(Map.Entry<Integer, Integer> entry: countMap.entrySet()){
            int index = p+1-entry.getValue();
            if(index>=0 && index<=p){
                levels.get(index).add(entry.getKey());
            }
        }

        if(debug) {
            for(int i=0; i<=p; i++){
                System.out.println(i + ": " + levels.get(i));
            }
            System.out.println();
        }

    }

    public int[] solve(){

        if(levels.get(0).size()!=1){
            return null;
        }

        int[] result = new int[2*n-1];
        result[0] = levels.get(0).first();

        if(debug){
            System.out.println(result[0]);
        }

        int b = 1;
        int f = 0;
        for(int i=1; i<=p; i++){
            TreeSet<Integer> level = levels.get(i);
            for(int k=0; k<(1<<(i-1)); k++){
                Integer value = level.ceiling(result[f]);

                if(value == null){
                    return null;
                }

                result[b] = result[f];
                result[b+1] = value;

                level.remove(value);

                if(debug){
                    System.out.print(result[b] + " ");
                    System.out.print(result[b+1] + " ");
                }


                b+=2;
                f++;
            }

            if(debug){
                System.out.println("");
            }
        }

        return result;
    }

    private int getPower(int n){
        int p=0;
        int n1 = n;
        while (n1>1){
            p++;
            n1 = n1>>1;
        }
        return p;
    }



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Solution solution = new Solution();
        int n = in.nextInt();

        solution.index(n, in);

        int[] result = solution.solve();

        if(result == null){
            System.out.println("NO");
        }
        else {
            System.out.println("YES");
            for(int i=0; i<result.length; i++){
                System.out.print(result[i] + " ");
            }
        }
    }
}