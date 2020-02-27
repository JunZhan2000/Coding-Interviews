package sort;

import com.sun.java.accessibility.util.EventID;

public class SortFunction {
    public static int[] bubbleSort(int[] nums){
        if(nums == null || nums.length < 2){
            return nums;
        }
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < nums.length-i-1; j++){
                if(nums[j] > nums[j+1]){
                    int tmp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = tmp;
                }
            }
        }

        return nums;
    }

    public static int[] insertSort(int[] nums){
        if(nums == null || nums.length < 2){
            return nums;
        }
        for(int i = 1; i < nums.length; i++){
            int index = i;
            int temp = nums[i];
            for(int j = i-1; j >= 0 && nums[j] > temp; j--){
                nums[j+1] = nums[j];
                index = j;
            }
            nums[index] = temp;  //index为最后找到的合适的位置
        }
        return nums;
    }

    //希尔排序
    public static int[] shellSort(int[] nums){
        int length = nums.length;
        if(nums == null || length < 2){
            return nums;
        }

        int temp, index;
        for(int k = length / 2; k > 1; k /= 2){
            //以k为增量进行k次插排
            for(int start = 0; start < k; start++){
                //此处每一次循环为一次以k为增量的插排
                for(int i = start; i < length; i++){
                    temp = nums[i];
                    index = i;
                    for(int j = i-1; j >= 0 && nums[j] > nums[j+1]; j--){
                        nums[j+1] = nums[j];
                        index = j;
                    }
                    nums[index] = temp;
                }
            }
        }

        return nums;
    }

    //快速排序
    public static void quickSort(int[] nums){
        if(nums == null || nums.length <= 1){
            return;
        }

//        quickSort(nums, 0, nums.length-1);  //原始版本实现
        quickSort2(nums, 0, nums.length-1);  //《Introduction To Algorithm》版本实现
    }

    //原始版本的实现
    private static void quickSort(int[] nums, int start, int end){
        if(start >= end){  //递归出口
            return;
        }

        int key = nums[start];  //选择数组第一个元素为key
        //分成两个数组
        int empty = start, lIndex = start, rIndex = end;
        while (lIndex < rIndex){
            while (lIndex < rIndex && nums[rIndex] > key){
                rIndex--;
            }
            nums[empty] = nums[rIndex];
            empty = rIndex;

            while (lIndex < rIndex && nums[lIndex] <= key){
                lIndex++;
            }
            nums[empty] = nums[lIndex];
            empty = lIndex;
        }
        nums[empty] = key;
        quickSort(nums, start, empty-1);  //对左边的数组进行快排
        quickSort(nums, empty+1, end);  //对右边的数组进行快排
    }

    //《算法导论》的实现
    public static void quickSort2(int[] nums, int start, int end){
        if(start < end){
            int pivotIndex = partition(nums, start, end);  //划分数组，并获取基准的下标

            quickSort(nums, start, pivotIndex-1);
            quickSort(nums, pivotIndex+1, end);
        }
    }


    /**
     * 快排中的划分数组
     * @return pivot即key的下标
     */
    private static int partition(int[] nums, int p, int r){
        int pivotValue = nums[r];  //基准值
        int i = p - 1;

        for(int j = p; j < r; j++){
            if(nums[j] < pivotValue){
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, r, i+1);
        return i+1;
    }

    //交换数组中两个元素的位置
    private static void swap(int[] nums, int index1, int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    //归并排序
    public static int[] mergeSort(int[] nums){
        if(nums == null || nums.length == 0){
            return null;
        }
        if(nums.length == 1){
            return nums;
        }

        int[] result = new int[nums.length];
        merge(nums, result, 0, nums.length-1);
        return result;
    }

    /**
     * 归并操作
     * @return 合并后的数组
     */
    private static void merge(int[] arr, int[] result, int start, int end){
        if(start >= end){
            return;
        }

        int mid = (end + start) / 2;
        merge(arr, result, start, mid);
        merge(arr, result, mid+1, end);
        int start1 = start, end1 = mid, start2 = mid+1, end2 = end, k = start;

        //将start1:end1、start2:end2中的两个数组合并到result中
        while (start1 <= end1 && start2 <= end2)
            result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
        while (start1 <= end1)
            result[k++] = arr[start1++];
        while (start2 <= end2)
            result[k++] = arr[start2++];
        //将排好序的部分数组赋到arr中
        for(k = start; k <= end; k++)
            arr[k] = result[k];
    }
}
