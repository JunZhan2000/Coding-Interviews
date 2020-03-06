package BinaryTree;

public class BinarySearchTree{
    public static void main(String[] args){
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(6);
        tree.insert(4);
        tree.insert(9);
        tree.insert(3);
        tree.insert(1);
        tree.insert(5);
        tree.insert(7);
        tree.insert(10);

        BinarySearchTreeNode foundNode1 = tree.search(9);
        BinarySearchTreeNode foundNode2 = tree.search(11);
        tree.remove(9);

        System.out.println();
    }

    private BinarySearchTreeNode root;  //根节点

    //查找最小值节点
    public BinarySearchTreeNode findMin(){
        return BinarySearchTree.findMin(this.root);
    }

    //查找最大值节点
    public BinarySearchTreeNode findMax(){
        return BinarySearchTree.findMax(this.root);
    }

    //查找节点
    public BinarySearchTreeNode search(int value){
        return BinarySearchTree.search(this.root, value);
    }

    //插入节点
    public void insert(int value){
        root = BinarySearchTree.insert(root, value);
    }

    //删除节点
    public void remove(int value){
        root = BinarySearchTree.remove(root, value);
    }

    //查找最小值节点
    public static BinarySearchTreeNode findMin(BinarySearchTreeNode root){
        if(root == null)
            return null;
        BinarySearchTreeNode mov = root;
        while (mov.getLeft() != null)
            mov = mov.getLeft();
        return mov;
    }

    //查找最大值节点
    public static BinarySearchTreeNode findMax(BinarySearchTreeNode root){
        if(root == null)
            return null;
        BinarySearchTreeNode mov = root;
        while (mov.getRight() != null)
            mov = mov.getRight();
        return mov;
    }

    //查找节点
    public static BinarySearchTreeNode search(BinarySearchTreeNode root, int value){
        if(root == null)
            return null;

        if(value == root.getVal())
            return root;
        else if(value < root.getVal())
            return search(root.getLeft(), value);
        else
            return search(root.getRight(), value);
    }

    /**
     * 向BST中插入节点
     * @param root 根节点
     * @param value 插入的新值
     * @return 插入节点后的根节点
     */
    public static BinarySearchTreeNode insert(BinarySearchTreeNode root, int value){
        if(root == null)
            return new BinarySearchTreeNode(value);

        if(value < root.getVal())
            root.setLeft(insert(root.getLeft(), value));
        else
            root.setRight(insert(root.getRight(), value));

        return root;
    }

    //删除节点
    public static BinarySearchTreeNode remove(BinarySearchTreeNode root, int value){
        if(root == null)
            return null;

        if(value < root.getVal())
            root.setLeft(remove(root.getLeft(), value));
        else if(value > root.getVal())
            root.setRight(remove(root.getRight(), value));
        else if(root.getLeft() != null && root.getRight() != null){  //要删除的就是根节点且左右子树均不为空
            root.setVal(findMin(root.getRight()).getVal());  //将右子树中最小值的节点赋到根节点
            root.setRight(remove(root.getRight(), root.getVal()));  //删除右子树中最小值的节点
        } else
            root = root.getLeft() == null ? root.getRight() : root.getLeft();

        return root;
    }

    public BinarySearchTreeNode getRoot() {
        return root;
    }

    public void setRoot(BinarySearchTreeNode root) {
        this.root = root;
    }

}
