package exercise_4;

//输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
//假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
//例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。


import BinaryTree.BinaryTree;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args){
//        int[] pre = {10, 6, 4, 8, 14, 12, 16}, in = {4, 6, 8, 10, 12, 14, 16};
//        TreeNode treeNode = new Solution().reConstructBinaryTree(pre, in);
        TreeNode treeNode1 = new Solution().reConstructBinaryTree(new int[]{1, 2}, new int[]{2, 1});

        System.out.println();
    }

    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre==null || in == null || (pre.length != in.length)){  //异常的输入
            return null;
        }
        if(pre.length == 0){
            return null;
        }
        if(pre.length == 1){  //递归的出口
            if(pre[0] != in[0]){
                return null;
            }

            TreeNode treeNode = new TreeNode(pre[0]);
            treeNode.left = treeNode.right = null;
            return treeNode;
        }

        int rootVal = pre[0], rootPosition = 0;  //根节点的值，pre中根节点的位置

        while (rootPosition < in.length && in[rootPosition] != rootVal){  //寻找根节点在pre中的位置
            rootPosition++;
        }
        if(rootPosition == in.length){
            return null;  //未在in中找到根节点
        }
        //左子树、右子树的前序遍历数组、中序遍历数组
        int[] preLeft = new int[rootPosition];
        int[] inLeft = new int[rootPosition];
        int[] preRight = new int[pre.length -rootPosition - 1];
        int[] inRight = new int[pre.length - rootPosition - 1];
        System.arraycopy(in, 0, inLeft, 0, inLeft.length);  //截取in中的左子树
        System.arraycopy(in, inLeft.length+1, inRight, 0, inRight.length);  //截取in中的右子树
        System.arraycopy(pre, 1, preLeft, 0, preLeft.length);  //截取pre中的左子树
        System.arraycopy(pre, preLeft.length+1, preRight, 0, preRight.length);  //截取pre中的右子树

        TreeNode treeNode = new TreeNode(pre[0]);  //根节点
        treeNode.left = treeNode.right = null;
        treeNode.left = reConstructBinaryTree(preLeft, inLeft);  //递归构建左子树
        treeNode.right = reConstructBinaryTree(preRight, inRight);  //递归构建右子树

        return treeNode;
    }
}