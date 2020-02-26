package sort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        int[] nums = {3, 1, 5, 8, 9, 6, 7, 2, 4};
        System.out.println("冒泡排序：");
        System.out.println(Arrays.toString(SortFunction.bubbleSort(nums)));

        nums = new int[]{3, 1, 5, 8, 9, 6, 7, 2, 4};
        System.out.println("插入排序：");
        System.out.println(Arrays.toString(SortFunction.insertSort(nums)));

        nums = new int[]{3, 1, 5, 8, 9, 6, 7, 2, 4};
        System.out.println("希尔排序：");
        System.out.println(Arrays.toString(SortFunction.shellSort(nums)));

        nums = new int[]{3, 1, 5, 8, 9, 6, 7, 2, 4};
        SortFunction.quickSort(nums);
        System.out.println("快速排序：");
        System.out.println(Arrays.toString(nums));
    }
}
