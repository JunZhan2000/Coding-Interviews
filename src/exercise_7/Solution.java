// 题目描述
// 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
// n<=39

package exercise_7;

public class Solution {
    public static void main(String[] args){
        System.out.println(new Solution().Fibonacci(40));
        // 102334155
    }

    public int Fibonacci(int n) {
        if(n < 0){
            throw new IndexOutOfBoundsException("n不符合条件");
        }
        if (n == 0 || n == 1) {
            return n;
        }

        int num1 = 0, num2 = 1, num3 = 1;
        for(int i = 2; i <= n; i++){
            num3 = num1 + num2;
            num1 = num2;
            num2 = num3;
        }

        return num3;
    }



//    public int Fibonacci(int n) {
//        if (n == 0) {
//            return 0;
//        }
//        if (n == 1) {
//            return 1;
//        }
//        return Fibonacci(n - 1) + Fibonacci(n - 2);
//    }
}