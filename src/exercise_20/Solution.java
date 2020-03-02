// 题目描述
// 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
// 注意：保证测试中不会当栈为空的时候，对栈调用pop()或者min()或者top()方法。

package exercise_20;

import java.util.ArrayList;
import java.util.Stack;

public class Solution {

    private Stack<Integer> dataStack = new Stack<>();  //存储数据
    private Stack<Integer> minStack = new Stack<>();   //存储历史最小值

    public void push(int node) {
        if(dataStack.isEmpty())
            minStack.push(node);  //数据栈中没有元素，新压入的元素即为最小值
        else {
            int minData = minStack.push(minStack.pop());  //之前的最小值
            minData = Math.min(minData, node);  //新的最小值
            minStack.push(minData);  //在辅助栈中压入新的最小值
        }

        dataStack.push(node);
    }

    public void pop() {
        if(dataStack.isEmpty())
            throw new IndexOutOfBoundsException("栈为空！");
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        if(dataStack.isEmpty())
            throw new IndexOutOfBoundsException("栈为空！");
        return dataStack.push(dataStack.pop());
    }

    public int min() {
        if(dataStack.isEmpty())
            throw new IndexOutOfBoundsException("栈为空！");
        return minStack.push(minStack.pop());
    }
}
