// 题目描述
// 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点）
// 返回结果为复制后复杂链表的head。
//（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）

package exercise_25;

public class Solution {
    public static void main(String[] args){
        RandomListNode listNode1 = new RandomListNode(1);
        RandomListNode listNode2 = new RandomListNode(2);
        RandomListNode listNode3 = new RandomListNode(3);
        RandomListNode listNode4 = new RandomListNode(4);
        RandomListNode listNode5 = new RandomListNode(5);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode1.random = listNode5;
        listNode3.random = listNode1;

        RandomListNode clonedList = new Solution().Clone(listNode1);
        System.out.println();
    }

    //解法二
    public RandomListNode Clone(RandomListNode pHead) {
        if(pHead == null)
            return null;

        //将复制的节点插入原链表，放置在被复制的节点后面
        RandomListNode mov = pHead, mov2, clonedNode;
        while (mov != null){
            clonedNode = new RandomListNode(mov.label);  //复制节点
            clonedNode.next = mov.next;
            mov.next = clonedNode;  //插入复制的节点
            mov = clonedNode.next;  //跳过两个节点，到底原链表的下一节点
        }

        //复制random指针的值
        mov = pHead;  //指向原链表的头结点
        clonedNode = pHead.next;  //复制的头结点
        while (mov != null){
            clonedNode.random = mov.random != null ? mov.random.next : null;
            mov = clonedNode.next;  //跳过两个节点
            clonedNode = mov != null ? mov.next : null;  //跳过两个节点（防止mov1为空，要做一下判断）
        }

        //拆分成两个链表
        mov = pHead;  //原链表的头结点
        mov2 = pHead.next;  //定位到复制的头结点
        clonedNode = pHead.next;  //记住复制的头结点，用于返回
        while (mov != null){
            mov.next = mov2.next;
            mov = mov.next;  //跳过两个节点

            mov2.next = mov != null ? mov.next : null;
            mov2 = mov2.next;  //跳过两个节点
        }

        return clonedNode;
    }

    //解法一
    public RandomListNode Clone2(RandomListNode pHead) {
        if(pHead == null)
            return null;

        RandomListNode mov1 = pHead.next, clonedList = new RandomListNode(pHead.label);
        RandomListNode mov2 = clonedList, mov4, mov5;

        //复制节点以及next指针
        while (mov1 != null){
            mov2.next = new RandomListNode(mov1.label);
            mov1 = mov1.next;
            mov2 = mov2.next;
        }
        //辅助random指针
        mov1 = pHead;
        mov2 = clonedList;
        while (mov1 != null){
            if(mov1.random != null){
                mov4 = pHead;
                mov5 = clonedList;
                while (mov4 != mov1.random){
                    mov4 = mov4.next;
                    mov5 = mov5.next;
                }
                mov2.random = mov5;
            }
            mov1 = mov1.next;
            mov2 = mov2.next;
        }

        return clonedList;
    }
}