package BinaryTree;

public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(10);
        tree.setLeft(6);
        tree.getLeft().setLeft(4);
        tree.getLeft().setRight(8);
        tree.setRight(14);
        tree.getRight().setLeft(12);
        tree.getRight().setRight(16);

        System.out.println("前序遍历：");
        tree.preOrderTraversalCycle();  //循环
        tree.preOrderTraversalRecursive();  //递归
        System.out.println("\n中序遍历：");
        tree.InOrderTraversalRecursive();
        System.out.println("\n后序遍历：");
        tree.postOrderTraversalRecursive();
    }
}
