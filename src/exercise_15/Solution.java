// 题目描述
// 输入一个链表，反转链表后，输出新链表的表头。

package exercise_15;

public class Solution {
    public static void main(String[] args){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = null;

        ListNode newHead = new Solution().ReverseList(head);
        System.out.println(newHead.val);
    }

    public ListNode ReverseList(ListNode head) {
        if(head == null)
            return head;
        ListNode prev = head, next = head.next, nextOfNext; //与递归实现中定义的三个指针作用完全相同
        prev.next = null;
        while (next != null){
            nextOfNext = next.next;
            next.next = prev;
            prev = next;
            next = nextOfNext;
        }

        return prev;
    }

    public ListNode ReverseList2(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode nextOfHead = head.next;  //记住头结点的下一个节点
        head.next = null;  //将头结点置为尾结点
        return ReverseListCore(head, nextOfHead);  //开始递归反转
    }

    private ListNode ReverseListCore(ListNode prev, ListNode next){
        if(next == null)
            return prev;

        ListNode nextOfNext = next.next;
        next.next = prev;

        return ReverseListCore(next, nextOfNext);
    }
}
