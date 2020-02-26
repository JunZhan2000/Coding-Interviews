// 题目描述
// 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
// 求该青蛙跳上一个n级的台阶总共有多少种跳法。

package exercise_9;

public class Solution {
    public static void main(String[] args){
        System.out.println(new Solution().JumpFloorII(-1));
        System.out.println(new Solution().JumpFloorII(1));
        System.out.println(new Solution().JumpFloorII(2));
        System.out.println(new Solution().JumpFloorII(3));
        System.out.println(new Solution().JumpFloorII(4));
        System.out.println(new Solution().JumpFloorII(5));
    }

    public int JumpFloorII(int target) {
        return (int) Math.pow(2, target-1);
    }


    public int JumpFloorII2(int target) {
        if(target <= 0){
            return 0;
        }
        if(target == 1){
            return 1;
        }

        int kindsOfJump = 1;
        for(int i = 1; i < target; i++){
            kindsOfJump += JumpFloorII(i);
        }
        return kindsOfJump;
    }
}
