// 题目描述
// 输入一棵二叉树，求该树的深度。
// 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。

package exercise_38;

public class Solution {

    public static void main(String[] args){
        Solution solution = new Solution();
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(2);
        tree.left.left = new TreeNode(2);
        tree.left.right = new TreeNode(2);
        tree.left.left.right = new TreeNode(2);

        System.out.println(solution.TreeDepth(tree));
    }

    //分治法
    public int TreeDepth(TreeNode root) {
        //一棵树的深度等于 左子树深度与右子树深度 中的较大值 + 1
        if(root == null)
            return 0;

        return Math.max(TreeDepth(root.left), TreeDepth(root.right)) + 1;
    }
}
