import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {
            int currentNum = nums[right];
            counts.put(currentNum, counts.getOrDefault(currentNum, 0) + 1);

            // Shrink the window from the left if the current element's frequency exceeds k
            while (counts.get(currentNum) > k) {
                int leftNum = nums[left];
                counts.put(leftNum, counts.get(leftNum) - 1);
                left++;
            }

            // Update the maximum length found so far
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
