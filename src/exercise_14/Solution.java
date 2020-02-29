// 题目描述
// 输入一个链表，输出该链表中倒数第k个结点。

package exercise_14;

public class Solution {
    public ListNode FindKthToTail(ListNode head, int k) {
        if(head == null || k <= 0)
            return null;
        ListNode mov = head;
        int listLength = 0;
        while (mov != null){  //获取链表长度
            mov = mov.next;
            listLength++;
        }
        if(listLength < k) //判断k是否小于链表长度
            return null;
        mov = head;
        for(int i = 0; i < listLength-k; i++){  //移至倒数第k个节点，也就是第length - k + 1个节点
            mov = mov.next;
        }
        return mov;
    }


    public ListNode FindKthToTail2(ListNode head,int k) {
        if(head == null || k <= 0)
            return null;
        ListNode p, q;
        p = q = head;
        int i = 0;
        for (; p != null; i++) {
            if (i >= k)
                q = q.next;
            p = p.next;
        }
        return i < k ? null : q;
    }
}
