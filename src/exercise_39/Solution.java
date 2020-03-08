// 题目描述
// 输入一棵二叉树，判断该二叉树是否是平衡二叉树。

package exercise_39;

public class Solution {

    //自下而上，避免重复计算
    public boolean IsBalanced_Solution(TreeNode root) {
        //为null

        if(IsBalanced_SolutionCore(root) < 0)
            return false;
        else
            return true;
    }

    public int IsBalanced_SolutionCore(TreeNode root) {
        if(root == null)
            return 0;
        int leftHeight = IsBalanced_SolutionCore(root.left);
        int rightHeight = IsBalanced_SolutionCore(root.right);

        if(leftHeight < 0 || rightHeight < 0 || Math.abs(leftHeight - rightHeight) > 1)
            return -1;  //不是平衡二叉树，返回一个负数

        return Math.max(leftHeight, rightHeight) + 1;  //返回当前树的高度
    }


    //自上而下，看起来明了一些，但有大量重复计算，增加时间复杂度
    public boolean IsBalanced_Solution2(TreeNode root) {
        //按理来说要加上下面两行，但是牛客通不过
        //        if(root == null)
        //            return false;

        return IsBalancedSolutionCore(root);
    }

    private boolean IsBalancedSolutionCore(TreeNode root) {
        if (root == null)
            return true;

        if (Math.abs(TreeDepth(root.left) - TreeDepth(root.right)) > 1)
            return false;
        return IsBalanced_Solution(root.left) && IsBalanced_Solution(root.right);
    }

    //分治法
    public int TreeDepth(TreeNode root) {
        //一棵树的深度等于 左子树深度与右子树深度 中的较大值 + 1
        if (root == null)
            return 0;

        return Math.max(TreeDepth(root.left), TreeDepth(root.right)) + 1;
    }
}
