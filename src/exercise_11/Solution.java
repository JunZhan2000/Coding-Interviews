// 题目描述
// 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。

package exercise_11;

public class Solution {
    public static void main(String[] args){
        System.out.println(new Solution().NumberOf1First(1231341234));
        System.out.println(new Solution().NumberOf1First(-1));

    }

    //弊端，要求int类型必须为32位
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

    //实现版本2，参数的位数不一定要32位
    public int NumberOf1First(int n){
        int num = 1, count = 0;
        while (num != 0){
            if((n & num) != 0){
                count++;
            }
            num <<= 1;
        }
        return count;
    }

    //实现版本3，更快且简洁
    public int NumberOf1Second(int n){
        int count = 0;
        while (n != 0){
            count++;
            n = (n-1) & n;
        }

        return count;
    }
}
