class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        
        for (int i = 1; i <= n; i++) {
            // Check all possible non-zero square numbers k*k <= i
            for (int k = 1; k * k <= i; k++) {
                if (!dp[i - k * k]) {
                    dp[i] = true;
                    break; // Found a winning move, no need to check further
                }
            }
        }
        
        return dp[n];
    }
}
