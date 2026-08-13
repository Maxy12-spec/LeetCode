import java.util.Arrays;

class Solution {
    // Segment Tree Node structure
    class Node {
        char leftChar, rightChar;
        int leftMax, rightMax, maxLen, length;

        Node(char c, int len) {
            this.leftChar = c;
            this.rightChar = c;
            this.leftMax = len;
            this.rightMax = len;
            this.maxLen = len;
            this.length = len;
        }

        Node() {}
    }

    private Node[] tree;
    private char[] str;

    // Merge two child nodes into a parent node
    private Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;

        Node parent = new Node();
        parent.length = left.length + right.length;
        parent.leftChar = left.leftChar;
        parent.rightChar = right.rightChar;

        // Default layout without bridging across the middle
        parent.leftMax = left.leftMax;
        parent.rightMax = right.rightMax;
        parent.maxLen = Math.max(left.maxLen, right.maxLen);

        // Check if characters at the boundary match and bridge them
        if (left.rightChar == right.leftChar) {
            int bridge = left.rightMax + right.leftMax;
            parent.maxLen = Math.max(parent.maxLen, bridge);

            // If the left child is entirely made of the same character
            if (left.leftMax == left.length) {
                parent.leftMax = left.length + right.leftMax;
            }
            // If the right child is entirely made of the same character
            if (right.rightMax == right.length) {
                parent.rightMax = right.length + left.rightMax;
            }
        }

        return parent;
    }

    // Build the segment tree initially
    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(str[start], 1);
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    // Update a specific index with a new character
    private void update(int node, int start, int end, int idx, char val) {
        if (start == end) {
            tree[node] = new Node(val, 1);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;
        str = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char val = queryCharacters.charAt(i);
            str[idx] = val; // Synchronize original array representation
            update(1, 0, n - 1, idx, val);
            result[i] = tree[1].maxLen; // The root node always contains the global max
        }

        return result;
    }
}
