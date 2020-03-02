// 题目描述
// 输入两个整数序列，第一个序列表示栈的压入顺序
// 请判断第二个序列是否可能为该栈的弹出顺序
// 假设压入栈的所有数字均不相等
// 例如序列1,2,3,4,5是某栈的压入顺序
// 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列
// （注意：这两个序列的长度是相等的）

package exercise_21;

import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] pushA = {1, 2, 3, 4, 5};
        int[] popA = {4, 5, 3, 2, 1};
        int[] popB = {4, 3, 5, 1, 2};

        solution.IsPopOrder(pushA, popA);
        solution.IsPopOrder(pushA, popB);
    }


    //牛客网上的一个实现版本
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA.length == 0 || popA.length == 0)
            return false;
        Stack<Integer> s = new Stack<Integer>();
        int popIndex = 0;  //用于标识弹出序列的位置
        for (int value : pushA) {
            s.push(value);
            //如果栈不为空，且栈顶元素等于弹出序列
            while (!s.empty() && s.peek() == popA[popIndex]) {
                s.pop();  //出栈
                popIndex++;  //弹出序列向后一位
            }
        }
        return s.empty();
    }


    //自己的实现版本
    public boolean IsPopOrder2(int[] pushA, int[] popA) {
        if (pushA == null || popA == null || pushA.length == 0 || pushA.length != popA.length)
            return false;

        Stack<Integer> utilStack = new Stack();  //辅助栈，用于复原压入与弹出的过程
        int index = 0, length = pushA.length;  //用于遍历pushA的变量

        for (int num : popA) {
            if (!utilStack.isEmpty() && utilStack.peek() == num) {  //判断当前需要弹出的元素是否正好在栈顶
                utilStack.pop();
                continue;
            }
            while (index < length && pushA[index] != num)
                utilStack.push(pushA[index++]);

            index++; //在pushA中找到了要弹出的元素，直接跳过
        }

        return utilStack.isEmpty();
    }
}
