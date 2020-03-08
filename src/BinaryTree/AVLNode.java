package BinaryTree;



public class AVLNode<AnyType extends Comparable> {

    public AVLNode( AnyType theElement){
        element = theElement;
        left = right = null;
        height = 0;  //新插入的节点必定成为叶节点，而叶节点的高度为0
    }

    public AnyType element;            //节点存储的元素
    public AVLNode<AnyType> left;      //左节点
    public AVLNode<AnyType> right;     //右节点
    public int height;                 //树的高度

}
