// 题目描述
// 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
// 要求不能创建任何新的结点，只能调整树中结点指针的指向。

package exercise_26;

import sun.plugin2.message.Conversation;

public class Solution {

    public static void main(String[] args){
        Solution solution = new Solution();

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        root.right.right.right.right = new TreeNode(5);

        TreeNode answer = new Solution().Convert(root);

        System.out.println();
    }

    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree == null)
            return null;
        if(pRootOfTree.left == null && pRootOfTree.right == null)  //叶节点，直接返回
            return pRootOfTree;

        TreeNode leftList = Convert(pRootOfTree.left);    //左子树展成的双向链表
        if(leftList != null){
            //将左链表的末尾与当前节点链接
            TreeNode mov;
            for(mov = leftList; mov.right != null; mov = mov.right);  //定位到末尾节点
            mov.right = pRootOfTree;
            pRootOfTree.left = mov;
        }

        TreeNode rightList = Convert(pRootOfTree.right);  //右子树展成的双向链表
        if(rightList != null){  //将当前节点与右链表链接
            pRootOfTree.right = rightList;
            rightList.left = pRootOfTree;
        }

        return leftList == null ? pRootOfTree : leftList;  //返回拼接后的双向链表
    }
}
