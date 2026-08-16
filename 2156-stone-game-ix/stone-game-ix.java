class Solution {
    public boolean stoneGameIX(int[] stones) {
        // Count stones based on their remainder when divided by 3
        int[] count = new int[3];
        for (int stone : stones) {
            count[stone % 3]++;
        }
        
        // Case 1: The number of remainder 0 stones is EVEN
        if (count[0] % 2 == 0) {
            return Math.min(count[1], count[2]) > 0;
        }
        
        // Case 2: The number of remainder 0 stones is ODD
        return Math.abs(count[1] - count[2]) > 2;
    }
}
