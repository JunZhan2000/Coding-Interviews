// 题目描述
// 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动
// 每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
// 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
// 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？

package exercise_66;

public class Solution {
    public static void main(String[] args){
        System.out.println(new Solution().movingCount(10, 1, 100));
        System.out.println(new Solution().getDigitSum(10));
    }

    public int movingCount(int threshold, int rows, int cols) {
        if(rows <= 0 || cols <= 0){
            return 0;
        }

        boolean[] isVisited = new boolean[rows * cols];

        return movingCountCore(threshold, rows, cols, 0, 0, isVisited);
    }

    private int movingCountCore(int threshold, int rows, int cols, int row, int col, boolean[] isVisited){
        int index = row * cols + col;
        if(row >= 0 && row < rows && col >= 0 && col < cols &&
               !isVisited[index] && (getDigitSum(row) + getDigitSum(col) <= threshold)){
            isVisited[index] = true;
            int sum = 1 + movingCountCore(threshold, rows, cols, row+1, col, isVisited)
                    + movingCountCore(threshold, rows, cols, row-1, col, isVisited)
                    + movingCountCore(threshold, rows, cols, row, col+1, isVisited)
                    + movingCountCore(threshold, rows, cols, row, col-1, isVisited);
            return sum;
        }

        return 0;
    }

    private int getDigitSum(int num){
        if(num < 0){
            return -1;
        }

        int sum = 0;
        while (num > 0){
            sum += (num % 10);
            num /= 10;
        }

        return sum;
    }
}
