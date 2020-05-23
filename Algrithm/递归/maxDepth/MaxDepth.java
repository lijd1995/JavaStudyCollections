package maxDepth;

/**
 * @program: 递归
 * @description:
 * @author: ljd
 * @create: 2020-05-23 11:08
 */
public class MaxDepth {
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        int leftLength = maxDepth(root.left);
        int rightLength = maxDepth(root.right);
        return Math.max(leftLength,rightLength)+1;
    }

    public int maxDepth1(TreeNode root){
        return root == null?0:Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }
}
