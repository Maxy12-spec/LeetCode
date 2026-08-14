class Solution {
    public int maximumLengthSubstring(String s) {
        int[] count = new int[26];
        int left = 0;
        int maxLen = 0;
        int n = s.length();

        for (int right = 0; right < n; right++) {
            int rightIdx = s.charAt(right) - 'a';
            count[rightIdx]++;

            // Shrink the window from the left if a character count exceeds 2
            while (count[rightIdx] > 2) {
                int leftIdx = s.charAt(left) - 'a';
                count[leftIdx]--;
                left++;
            }

            // Update the maximum length found so far
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
