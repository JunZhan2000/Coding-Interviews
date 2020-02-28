// 题目描述
// 输入一个整数数组，实现一个函数来调整该数组中数字的顺序
// 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分
// 并保证奇数和奇数，偶数和偶数之间的相对位置不变。

package exercise_13;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args){
        int[] array = {1, 2, -4, -2, -1, 3, 4, 5, 6, 7};
        new Solution().reOrderArray(array);
    }

    public void reOrderArray(int [] array) {
        int length = array.length;
        if(array == null || length <= 1){
            return;
        }

        int[] newArray = new int[length];
        int oddNums = 0, evenNums = 0;  //奇数数量，偶数数量
        //遍历一遍获取奇数的数量
        for(int num : array){
            if(num % 2 != 0)
                oddNums++;
        }
        //按顺序复制奇偶数到新数组
        int oddIndex = 0, evenIndex = oddNums;
        for (int num : array){
            if(num % 2 == 0)
                newArray[evenIndex++] = num;
            else
                newArray[oddIndex++] = num;
        }
        //赋给原数组
        System.arraycopy(newArray, 0, array, 0, newArray.length);
    }
}
