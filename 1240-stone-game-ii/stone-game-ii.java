import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        
        // Precompute suffix sums to quickly find total remaining stones
        int[] suffixSums = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffixSums[i] = suffixSums[i + 1] + piles[i];
        }
        
        // Memoization map using a combined key for (index, M)
        Map<String, Integer> memo = new HashMap<>();
        
        return dp(0, 1, piles, suffixSums, memo);
    }
    
    private int dp(int i, int m, int[] piles, int[] suffixSums, Map<String, Integer> memo) {
        int n = piles.length;
        
        // Base case: no piles left
        if (i >= n) {
            return 0;
        }
        
        // If the current player can take all remaining piles, do so
        if (i + 2 * m >= n) {
            return suffixSums[i];
        }
        
        // Check memoization cache
        String key = i + "," + m;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        int maxStones = 0;
        
        // Explore all possible choices of X piles
        for (int x = 1; x <= 2 * m; x++) {
            // Stones opponent will get from the next state
            int opponentStones = dp(i + x, Math.max(m, x), piles, suffixSums, memo);
            // Current player's score is total remaining minus opponent's maximum score
            maxStones = Math.max(maxStones, suffixSums[i] - opponentStones);
        }
        
        memo.put(key, maxStones);
        return maxStones;
    }
}
