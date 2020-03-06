package BinaryTree;

import java.util.ArrayList;

public class BinarySearchTreeNode{

    private int val;
    private BinarySearchTreeNode left = null;
    private BinarySearchTreeNode right = null;

    public BinarySearchTreeNode(int x) {
        this.val = x;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public BinarySearchTreeNode getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = new BinarySearchTreeNode(left);
    }

    public BinarySearchTreeNode getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = new BinarySearchTreeNode(right);
    }

    public void setLeft(BinarySearchTreeNode left) {
        this.left = left;
    }

    public void setRight(BinarySearchTreeNode right) {
        this.right = right;
    }

//    @Override
//    public int compareTo(Object o) {
//        BinarySearchTreeNode node = (BinarySearchTreeNode) o;
//        if(node == null)
//            throw new IllegalArgumentException("参数不合法！");
//
//        if(val < node.getVal())
//            return -1;
//        else if(val > node.getVal())
//            return 1;
//
//        return 0;
//    }
}
