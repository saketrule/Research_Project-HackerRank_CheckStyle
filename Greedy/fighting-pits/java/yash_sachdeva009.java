import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] values = br.readLine().split(" ");
        int n = Integer.parseInt(values[0]);
        int k = Integer.parseInt(values[1]);
        int q = Integer.parseInt(values[2]);
        Map<Integer,List<Integer>> hm = new HashMap<>();
        for(int i = 0;i < n;i++){
            String [] warrior = br.readLine().split(" ");
            int team = Integer.parseInt(warrior[1]);
            if(hm.containsKey(team)){
                List<Integer> g = hm.get(team);
                g.add(Integer.parseInt(warrior[0]));
            }else{
                List<Integer> g = new ArrayList<>();
                g.add(Integer.parseInt(warrior[0]));
                hm.put(team,g);
            }
        }
        for(int i  = 0;i<q;i++){
            String[] query = br.readLine().split(" ");
            if(query[0].equals("1")){
                int strength = Integer.parseInt(query[1]);
                int team = Integer.parseInt(query[2]);
                List<Integer> s = hm.get(team);
                s.add(strength);
            }else{
                int teamx  = Integer.parseInt(query[1]);
                int teamy = Integer.parseInt(query[2]);
                List<Integer> x = new ArrayList<>(hm.get(teamx));
                List<Integer> y = new ArrayList<>(hm.get(teamy));
                Collections.sort(x,Collections.reverseOrder());
                Collections.sort(y,Collections.reverseOrder());
                while(!x.isEmpty() && !y.isEmpty()){
                    int tx = x.get(0);
                    while(tx>0){
                       
                        if(y.isEmpty()){
                            break;
                        }else{
                             y.remove(0);
                        }
                        tx--;
                    }
                    if(!y.isEmpty()){
                        int ty = y.get(0);
                        while(ty>0){
                            if(x.isEmpty()){
                                break;
                            }else{
                                x.remove(0);
                            }
                            ty--;
                        }
                    }
                }
                if(x.isEmpty()){
                    System.out.println(teamy);
                }else{
                    System.out.println(teamx);
                }
            }
        }
        
    }
}