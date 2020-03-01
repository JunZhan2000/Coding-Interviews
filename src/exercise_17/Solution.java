// 题目描述
// 输入两棵二叉树A，B，判断B是不是A的子结构。
//（ps：我们约定空树不是任意一个树的子结构）

package exercise_17;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();

        TreeNode tree1 = new TreeNode(8);
        tree1.left = new TreeNode(8);
        tree1.right = new TreeNode(7);
        tree1.left.left = new TreeNode(9);
        tree1.left.right = new TreeNode(2);
        tree1.left.right.left = new TreeNode(4);
        tree1.left.right.right = new TreeNode(7);

        TreeNode tree2 = new TreeNode(8);
        tree2.right = new TreeNode(2);
        tree2.left = new TreeNode(9);

        solution.HasSubtree(tree1, tree2);
        System.out.println(solution.HasSubtree(tree1, tree2));
    }

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if(root2 == null || root1 == null)
            return false;

        if(hasSubtreeCore(root1, root2))
            return true;  //树B属于当前节点的同根子结构
        else  //判断树B是否是左子树或者是右子树的同根子结构
            return hasSubtreeCore(root1.left, root2) || hasSubtreeCore(root1.right, root2);
    }

    //判断树B是否是树A的子结构且树B和树A具有相同的根节点，即树B是树A的同根子结构
    public boolean hasSubtreeCore(TreeNode root1, TreeNode root2) {
        if(root2 == null)
            return true;
        if(root1 == null)
            return false;

        if(root1.val == root2.val)  //当前节点相等，则判断树B的左子树与右子树是否分别是树A的左子树和右子树的同根子结构
            return hasSubtreeCore(root1.left, root2.left) && hasSubtreeCore(root1.right, root2.right);
        else
            return false;
    }
}