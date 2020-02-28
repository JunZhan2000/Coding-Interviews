// 题目描述
// 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
// 保证base和exponent不同时为0

package exercise_12;

public class Solution {
    public static void main(String[] args){
        System.out.println(new Solution().Power(2, -3));
    }

    public double Power(double base, int exponent) {
        if(base == 0 && exponent <= 0){
            throw new IllegalArgumentException("Invalid args!");
        }

        if(exponent < 0){
            base = 1 / base;
            exponent *= -1;
        }
        if(exponent == 0){
            return 1;
        }
        if(exponent == 1){
            return base;
        }

        double result = Power(base, exponent / 2);
        result *= result;
        if(exponent % 2 == 1){
            result *= base;
        }

        return result;
    }

    public double Power2(double base, int exponent) {
        if(base == 0 && exponent <= 0){
            throw new IllegalArgumentException("Invalid args!");
        }
        if(exponent < 0){
            base = 1 / base;
            exponent *= -1;
        }

        double returnNum = 1;
        for(int i = 0; i < exponent; i++){
            returnNum *= base;
        }

        return returnNum;
    }
}
