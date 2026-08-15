import java.util.Arrays;

public class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXor = 0;
        boolean allZeros = true;

        // Single pass to calculate total XOR and check for non-zero elements
        for (int num : nums) {
            totalXor ^= num;
            if (num != 0) {
                allZeros = false;
            }
        }

        // 1. If every element is 0, a non-zero XOR is impossible
        if (allZeros) {
            return 0;
        }

        // 2. If the entire array's XOR is non-zero, use the whole array
        if (totalXor != 0) {
            return nums.length;
        }

        // 3. If total XOR is 0, remove exactly 1 non-zero element
        return nums.length - 1;
    }
}
