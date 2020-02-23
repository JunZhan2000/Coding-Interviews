package BinaryTree;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class BinaryTree {
    private int val;
    private BinaryTree left;
    private BinaryTree right;

    public BinaryTree(int x) { val = x; }

    //前序遍历的循环实现
    public void preOrderTraversalCycle(){
        ArrayList<Integer> list = new ArrayList();
        Stack<BinaryTree> stack = new Stack();

        stack.push(this);  //根节点入栈
        while (!stack.isEmpty()){//当栈不为空，表示遍历未结束
            BinaryTree temNode = stack.pop();
            list.add(temNode.getVal());  //先访问根节点
            if(temNode.getLeft() != null){
                stack.push(temNode.getRight());  //右节点入栈
            }
            if(temNode.getRight() != null){
                stack.push(temNode.getLeft());  //左节点入栈
            }
        }

        for(Integer value : list){
            System.out.print(value + " ");
        }
        System.out.println();
    }

    //前序遍历的递归实现
    public void preOrderTraversalRecursive(){
        System.out.print(this.val + " ");
        if(left != null){
           left.preOrderTraversalRecursive();
        }
        if(right != null){
            right.preOrderTraversalRecursive();
        }
    }

    //中序遍历的递归实现
    public void InOrderTraversalRecursive(){
        if(left != null){
            left.InOrderTraversalRecursive();
        }
        System.out.print(val + " ");
        if(right != null){
            right.InOrderTraversalRecursive();
        }
    }

    //后序遍历的递归实现
    public void postOrderTraversalRecursive(){
        if(left != null){
            left.postOrderTraversalRecursive();
        }
        if(right != null){
            right.postOrderTraversalRecursive();
        }
        System.out.print(val + " ");
    }

    //广度优先遍历，利用队列实现即可

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(int val) {
        this.left = new BinaryTree(val);
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(int val) {
        this.right = new BinaryTree(val);
    }
}
