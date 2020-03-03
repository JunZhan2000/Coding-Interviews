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


    public RandomListNode Clone(RandomListNode pHead) {
        if(pHead == null)
            return null;

        RandomListNode mov1 = pHead.next, clonedList = new RandomListNode(pHead.label);
        RandomListNode mov2 = clonedList, mov4, mov5;

        while (mov1 != null){
            mov2.next = new RandomListNode(mov1.label);
            mov1 = mov1.next;
            mov2 = mov2.next;
        }
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