//题目描述
//输入一个链表，按链表从尾到头的顺序返回一个ArrayList。

package exercise_3;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class Solution {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);

        new Solution().printList(listNode);
        System.out.println(new Solution().printListFromTailToHead(listNode));
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        int count = 0, i = 0;
        ArrayList<Integer> vals = new ArrayList<>();
        ListNode move = listNode;

        //获取链表长度
        while (move != null) {
            vals.add(move.val);
            count++;
            move = move.next;
        }

        move = listNode;
        while (move != null) {
            vals.set(count - i++ - 1, move.val);
            move = move.next;
        }

        return vals;
    }

    public void printList(ListNode listNode){
        if(listNode == null){  //链表为空，直接结束
            return;
        }

        if(listNode.next != null){
            printList(listNode.next);
        }

        System.out.println(listNode.val);
    }
}