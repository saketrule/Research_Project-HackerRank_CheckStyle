import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Solution {

    private static final int _26=26;

    static int c2i(char c) {
        return (int)(c-'a');
    }
    
    static void run_stream(InputStream ins) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(ins));
        String st=br.readLine(); int n=st.length();
        char[] s=new char[n];
        for (int i=0; i<n; i++) {
            s[i]=st.charAt(n-1-i);
        }
        
        int[] can_use=new int[_26], can_skip=new int[26];
        for (int i=0; i<n; i++) {
            can_use[c2i(s[i])]++;
        }
        
        for (int i=0; i<_26; i++) {
            can_use[i]=can_use[i]>>1;
            can_skip[i]=can_use[i];
        }
        
        int pos=0; StringBuilder ret=new StringBuilder(n>>1);
        while(true) {
            int best_pos=-1;
            int[] can_skip2=can_skip.clone();
            for (int i=pos; i<n; i++) {
                int char_idx=c2i(s[i]);

                if(can_skip2[char_idx]==0) {
                    if((best_pos<0||s[best_pos]>s[i])&&can_use[char_idx]>0) {
                        if(best_pos>=0) {
                            if(can_skip2[c2i(s[best_pos])]>0) {
                                can_skip2[c2i(s[best_pos])]--;
                            } else {
                                break;
                            }
                        }
                        best_pos=i;
                    }
                    break;
                }

                if((best_pos<0||s[best_pos]>s[i])&&can_use[char_idx]>0) {
                    if(best_pos>=0) {
                        if(can_skip2[c2i(s[best_pos])]>0) {
                            can_skip2[c2i(s[best_pos])]--;
                        } else {
                            break;
                        }
                    }
                    best_pos=i;
                } else {
                    can_skip2[char_idx]--;
                }
            }
            
            for (int i=pos; i<best_pos; i++) {
                if(s[i]==s[best_pos]) {
                    best_pos=i;
                    break;
                }
            }
            for (int i=pos; i<best_pos; i++) {
                int char_idx=c2i(s[i]);
                if(can_skip[char_idx]==0) {
                    throw new IllegalStateException();
                }
                can_skip[char_idx]--;
            }
            
            if(best_pos>=0) {
                ret.append(s[best_pos]);
                int char_idx=c2i(s[best_pos]);
                can_use[char_idx]--;
                pos=best_pos+1;
            } else {
                break;
            }
        }
        
        System.out.println(ret);
    }

    public static void main(String[] args) throws IOException {
        run_stream(System.in);
    }

}