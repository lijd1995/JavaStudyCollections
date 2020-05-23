package minDepth;

import isValidBST.TreeNode;

/**
 * @program: 递归
 * @description:
 * @author: ljd
 * @create: 2020-05-23 11:15
 */
public class MinDepth {
    public int minDepth(TreeNode root) {
        // terminal
        if (root == null) return 0;
        // process current logic

        // drill down
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        return root.left == null || root.right == null?leftDepth + rightDepth + 1:Math.min(leftDepth,rightDepth)+1;
    }
}
