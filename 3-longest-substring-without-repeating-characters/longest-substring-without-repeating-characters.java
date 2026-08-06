import java.util.HashMap;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Map to store the last seen index of each character
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int left = 0; // Left boundary of the sliding window

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // If character was seen inside current window, jump left boundary
            if (map.containsKey(currentChar)) {
                left = Math.max(left, map.get(currentChar) + 1);
            }

            // Update last seen index of the character
            map.put(currentChar, right);

            // Calculate window size and update max length
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
