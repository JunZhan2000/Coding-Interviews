// 题目描述
// 从上往下打印出二叉树的每个节点，同层节点从左至右打印。



package exercise_22;

import java.util.*;

public class Solution {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        if(root == null)
            return new ArrayList<>();
//            throw new IllegalArgumentException("二叉树为空树！");

        Queue<TreeNode> queue = new LinkedList<>();  //队列
        ArrayList<Integer> answer = new ArrayList<>();
        TreeNode treeNode;

        queue.add(root);
        while (!queue.isEmpty()){
            treeNode = queue.remove();  //pop出一个节点
            answer.add(treeNode.val);  //访问该节点
            if(treeNode.left != null)
                queue.add(treeNode.left);  //左节点入栈，注意顺序，必须是左节点先进
            if(treeNode.right != null)
                queue.add(treeNode.right);
        }

        return answer;
    }
}
