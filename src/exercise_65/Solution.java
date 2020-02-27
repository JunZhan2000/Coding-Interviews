// 题目描述
// 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
// 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
// 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
package exercise_65;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args){
        char[] matrix = {'b'};
        char[] str = {'b'};
        System.out.println(new Solution().hasPath(matrix, 1, 1, str));
    }

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if(matrix == null || matrix.length == 0 || matrix.length != rows * cols || rows < 0 || cols < 0){
            return false;
        }

        boolean[] isVisited = new boolean[rows * cols];
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                if(judge(matrix, rows, cols, row, col, str, 0, isVisited)){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean judge(char[] matrix, int rows, int cols, int row, int col, char[] str, int roadIndex, boolean[] isVisited){
        if(roadIndex == str.length){
            return true;
        }
        boolean hasPath = false;
        int matrixIndex = row * cols + col;
        if(row >= 0 && row < rows && col >= 0 && col < cols &&
                !isVisited[matrixIndex] && matrix[matrixIndex] == str[roadIndex]){
            //如果matrix中这个节点符合要求，则判断该节点的上下左右四个节点
            isVisited[matrixIndex] = true;  //暂时确定该节点被加入路径中
            //判断以相邻节点（上下左右各四个）为str[roadIndex+1]是否满足要求
            hasPath = judge(matrix, rows, cols, row-1, col, str, roadIndex+1, isVisited)
            || judge(matrix, rows, cols, row+1, col, str, roadIndex+1, isVisited)
            || judge(matrix, rows, cols, row, col-1, str, roadIndex+1, isVisited)
            || judge(matrix, rows, cols, row, col+1, str, roadIndex+1, isVisited);
            if(!hasPath){
                isVisited[matrixIndex] = false;
            }
        }

        return hasPath;
    }
}
