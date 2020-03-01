// 题目描述
// 输入两个单调递增的链表，输出两个链表合成后的链表
// 当然我们需要合成后的链表满足单调不减规则。

package exercise_16;

public class Solution {
    public ListNode Merge(ListNode list1, ListNode list2){
        if(list1 == null)
            return list2;
        if(list2 == null)
            return list1;

        ListNode head;
        if(list1.val <= list2.val){
            head = list1;
            head.next = Merge(list1.next, list2);
        } else {
            head = list2;
            head.next = Merge(list1, list2.next);
        }

        return head;
    }

    public ListNode Merge2(ListNode list1, ListNode list2) {
        if(list1 == null)
            return list2;
        if(list2 == null)
            return list1;

        ListNode head, mov, mov1 = list1, mov2 = list2;

        //确定头结点
        if(mov1.val <= mov2.val){
            head = mov1;
            mov1 = mov1.next;
        } else {
            head = mov2;
            mov2 = mov2.next;
        }
        mov = head;

        while (mov1 != null && mov2 != null){
            if(mov1.val <= mov2.val){
                mov.next = mov1;
                mov1 = mov1.next;
            } else {
                mov.next = mov2;
                mov2 = mov2.next;
            }
            mov = mov.next;
        }
        while (mov1 != null){
            mov.next = mov1;
            mov = mov.next;
            mov1 = mov1.next;
        }
        while (mov2 != null){
            mov.next = mov2;
            mov = mov.next;
            mov2 = mov2.next;
        }

        return head;
    }
}
