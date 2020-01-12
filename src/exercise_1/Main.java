/*
题目描述
在一个二维数组中（每个一维数组的长度相同）
每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
*/

package exercise_1;

public class Main {

    public static void main(String[] args) {
        int[][] arrays = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                arrays[i][j] = i * 10 + j;
            }
        }
    }

    //最优解？: 对二维数组的二分法
    public boolean Find(int target, int[][] array) {
        int row = array.length - 1, column = 0;  //左下角数字所在的行数和列数

        while (row >= 0 && column <= array[0].length - 1) {
            if (array[row][column] == target) {
                return true;
            } else if (array[row][column] < target) {
                column++;
            } else {
                row--;
            }
        }

        return false;
    }


    //暴力枚举
    public boolean Find1(int target, int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] == target) {
                    return true;
                }
            }
        }

        return false;
    }

    //分组，每组采用二分法
    public static boolean Find2(int target, int[][] array) {
        for (int[] nums : array) {
            int head = 0, fail = array[0].length - 1, mid = (head + fail) / 2;
            while (head <= fail) {
                mid = (head + fail) / 2;
                if (nums[mid] == target) {
                    return true;
                } else if (nums[mid] < target) {
                    head = mid + 1;
                } else {
                    fail = mid - 1;
                }
            }
        }

        return false;
    }
}

