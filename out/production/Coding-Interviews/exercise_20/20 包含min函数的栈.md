#### 20 包含min函数的栈

***



##### 题目描述

定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。

注意：保证测试中不会当栈为空的时候，对栈调用pop()或者min()或者top()方法。



ps：牛客上面这道题实在是做得太糟糕了，题目没讲清楚，接口也让人摸不着头脑.jpg



**解析**：

* 题目的意思是要实现一个特别的栈类，该栈类相较于普通的栈多了一个min()方法，调用该方法可以获取栈中最小的元素，且要求min()方法的时间复杂度为O(1)
* 我们可以在该类中内置两个栈，一个栈作为数据栈提供push、pop等方法
* 另一个栈作为辅助栈存储一系列最小值，使得辅助栈的从下到上的第n个元素 即为 数据栈从下到上前n个元素的最小值
* 每次类栈push、pop时均要更新一次辅助栈，调用min方法时返回辅助栈的栈顶元素即可
* 私以为这个题出的并不好。。不仅多了O(n)的空间复杂度，而且每次push、pop都有额外的时间复杂度，除非调用min方法的次数很多，否则不如直接计算栈的最小值



**代码实现**

```java
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
```

