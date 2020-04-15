package practice;

/**
 * 旋转数组的最小数字
 *
 * 题目描述
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *
 * 分析：
 * 旋转之后的数组实际上可以划分成两个有序的子数组：前面子数组的大小都大于后面子数组中的元素
 * 注意到实际上最小的元素就是两个子数组的分界线。
 * 按照题目的旋转的规则，假如旋转前的数组是单调递增的数组，那么旋转数组的第一个元素应该是大于最后一个元素的（因为没有重复的元素）。
 */
public class Prac6 {
    public int minNumberInRotateArray(int[] rotateArray) {
        if(rotateArray.length == 0)
            return 0;

        int low = 0;
        int high = rotateArray.length - 1;
        int mid = 0;

        while(low < high){
            // 子数组是非递减的数组，10111
            if (rotateArray[low] < rotateArray[high])
                return rotateArray[low];
            mid = (low + high) / 2; // 也可以写成 mid = low + (high - low) / 2;
            if(rotateArray[mid] > rotateArray[low])
                low = mid + 1;
            else if(rotateArray[mid] < rotateArray[high])
                high = mid;
            else low++;
        }
        return rotateArray[low];
    }

    public int minNumberInRotateArray1(int[] rotateArray) {
        if(rotateArray.length == 0)
            return 0;

        int start = 0;
        int end = rotateArray.length - 1;
        int mid = 0;
        // 假如是顺序数组，不会进入while循环，直接结束。
        while(rotateArray[start] >= rotateArray[end]){
            // end是第二个递增子数组里面的索引，start是第一个递增子数组里面的索引。
            if(end - start == 1){
                mid = end;
                break;
            }
            mid = (start + end) / 2;
            // 如果遇到特殊情况(例如[1,0,1,1,1])，则采取顺序查找。
            if(rotateArray[start] == rotateArray[mid] && rotateArray[mid] == rotateArray[end]){
                return findMinInOrder(rotateArray, start, end);
            } else if(rotateArray[mid] >= rotateArray[start]){
                start = mid; // start最终会到达第一递增子数组的最后一个元素上。
            } else if (rotateArray[mid] <= rotateArray[end]){
                end = mid; // end最终会到达第二递增小组的第一个元素上，即最小元素。
            }
        }
        return rotateArray[mid];
    }

    /**
     * 顺序查找数组种的最小数
     */
    public static int findMinInOrder(int[] array, int start, int end){
        int result = 0;
        for(int i = start; i <= end; i++){
            if(result > array[i]){
                result = array[i];
            }
        }
        return result;
    }
}
