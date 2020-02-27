// 题目描述
// 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。

package exercise_11;

public class Solution {
    public static void main(String[] args){
        System.out.println(new Solution().NumberOf1Second(1231341234));
        System.out.println(new Solution().NumberOf1Second(-1));

    }

    public int NumberOf1(int n) {
        int counts = 0;
        for(int i = 0; i < 32; i++){
            if(Math.abs(n % 2) == 1){
                counts++;
            }
            n >>= 1;
        }
        return counts;
    }

    //实现版本2，更快且简洁
    public int NumberOf1Second(int n){
        int count = 0;
        while (n != 0){
            count++;
            n = (n-1) & n;
        }

        return count;
    }
}
