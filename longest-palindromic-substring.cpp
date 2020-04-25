class Solution {
public:
    string longestPalindrome(string s) {
        int n = s.length();
        if(n==0) return s;
        int maxlen = 0;
        int curr_len;
        int index;
        // for every odd size of substring.
        for(int i=0;i<n;i++) {
            int j = i,k = i;
            while(j>=0 && k<n && s[j] == s[k]) {
                j--; k++;
            }
            j++; k--;
            curr_len = k-j+1;
            if(maxlen < curr_len) { 
                maxlen = curr_len;
                index = j;
            }
        }
        
        // for every even size of substring
        for(int i=1;i<n;i++) {
            int j = i-1,k=i;
            while(j>=0 && k<n && s[j] == s[k]) {
                j--;
                k++;
            }
            j++; k--;
            curr_len = k-j+1;
            if(maxlen < curr_len) { 
                maxlen = curr_len;
                index = j;
            }
        }
        return s.substr(index,maxlen);
    }
};
