// 题目描述
// 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
// 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。

package exercise_8;

public class Solution {
    public static void main(String[] args){
        System.out.println(new Solution().JumpFloor(-1));
        System.out.println(new Solution().JumpFloor(2));
        System.out.println(new Solution().JumpFloor(3));
        System.out.println(new Solution().JumpFloor(4));
        System.out.println(new Solution().JumpFloor(5));
    }

    public int JumpFloor(int target) {
        if(target <= 0){
            return 0;
        }
        if(target == 1 || target == 2){
            return target;
        }

        int num1 = 1, num2 = 2, num3 = 3;
        for(int i = 0; i < target - 2; i++){
            num3 = num1 + num2;
            num1 = num2;
            num2 = num3;
        }

        return num3;
    }


//    public int JumpFloor(int target) {
//        if(target <= 0){
//            return 0;
//        }
//        if(target == 1 || target == 2){
//            return target;
//        }
//
//        return JumpFloor(target - 1) + JumpFloor(target - 2);
//    }
}
