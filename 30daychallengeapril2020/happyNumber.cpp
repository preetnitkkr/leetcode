class Solution {
public:
    bool isHappy(int n) {
        long N = n;
        unordered_set<long> visited; 
        while(N != 1 && visited.find(N) == visited.end()) {
            visited.insert(N);
            long M = 0;
            while(N) {
                int digit = N%10;
                N /= 10;
                M += digit*digit;
            }
            N = M;
        }
        if(N == 1) return true;
        return false;
    }
};
