// 题目描述
// 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
// 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。

package exercise_23;

public class Solution {

    public static void main(String[] args){
        Solution solution = new Solution();
        int[] sequence1 = {1, 4, 3, 2, 6, 7, 9, 8, 5};
        int[] sequence2 = {1, 4, 3, 2, 6, 10, 9, 8, 5};
        int[] sequence3 = {6, 7, 9, 8, 5};

        solution.VerifySquenceOfBST(sequence1);
        solution.VerifySquenceOfBST(sequence2);
        System.out.println(solution.VerifySquenceOfBST(sequence1));
        System.out.println(solution.VerifySquenceOfBST(sequence2));
        System.out.println(solution.VerifySquenceOfBST(sequence3));
    }

    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence == null || sequence.length == 0)
            return false;

        return VeryfySquenceOfBSTCore(sequence, 0, sequence.length);
    }

    private boolean VeryfySquenceOfBSTCore(int[] sequence, int start, int end){
        if(start >= end)
            return true;

        int root = sequence[end-1];  //根据后序遍历的性质，根节点是最后一个元素
        //将数组划分为左子树（元素全部比root小）和右子树（元素全部比root大）
        int leftStart = start, leftEnd, rightStart, rightEnd;
        for(leftEnd = leftStart; leftEnd < end-1 && sequence[leftEnd] < root; leftEnd++);  //左子树
        rightStart = leftEnd;
        for(rightEnd = rightStart; rightEnd < end-1; rightEnd++){
            if(sequence[rightEnd] <= root)
                return false;  //root前面的序列无法被划分为相邻的序列A、B，其中A所有的元素比root小，B所有的元素比root大
        }

        //若两数组为左右子树的后序遍历序列，则返回true，否则返回false
        return VeryfySquenceOfBSTCore(sequence, leftStart, leftEnd)
                && VeryfySquenceOfBSTCore(sequence, rightStart, rightEnd);
    }
}
