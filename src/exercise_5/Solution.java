// 题目描述
// 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。

package exercise_5;

import java.util.Stack;

public class Solution {
    //stack1用于存储数据，stack2用于每次pop时的调转方向
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if(stack1.isEmpty() && stack2.isEmpty()){
            throw new IndexOutOfBoundsException("queue is empty!");
        }

        if(stack2.isEmpty()){
            while (!stack1.isEmpty()){  //调转stack1中数据的方向
                stack2.push(stack1.pop());
            }
        }

        return stack2.pop();
    }
}