package practice;

import java.util.Arrays;

/**
 * 调整数组顺序使奇数位于偶数前面
 * <p>
 * 题目描述
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变
 */
public class Prac13 {
    /**
     * 利用冒泡排序的稳定性
     */
    public void reOrderArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] % 2 == 0 && array[j + 1] % 2 == 1) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 利用插入排序的方式可以分开奇偶两组，但是相对顺序会被破坏，不稳定
     * 例如：[1,2,3,4,5,6,7] -> [1,3,5,7,6,4,2]
     */
    public void reOrderArray1(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int preIndex = i - 1;
            int temp = array[i];
            while (preIndex >= 0 && array[preIndex] % 2 == 0) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = temp;
        }
    }

    /**
     * 利用快速排序的方式可以分开奇偶两组，但是相对顺序会被破坏，不稳定
     * 例如：[1,2,3,4,5,6,7] -> [1,7,3,5,4,6,2]
     */
    public void reOrderArray2(int[] array) {
        int start = 0;
        int end = array.length;
        sort(array, start, end);
    }

    public void sort(int[] array, int start, int end) {
        if (start > end) {
            return;
        }

        int i = start;
        int j = end;
        while (i < j) {
            while (array[i] % 2 == 1 && i < j) {
                i++;
            }
            while (array[j] % 2 == 0 && i < j) {
                j--;
            }
            swap(array, i, j);
        }
    }

    /**
     * 插入排序稳定版
     */
    public void reOrderArray3(int[] array) {
        int m = array.length;
        int k = 0; //记录已经摆好位置的奇数的个数
        for (int i = 0; i < m; i++) {
            if (array[i] % 2 == 1) {
                int j = i;
                while (j > k) {
                    swap(array, j, j - 1);
                    j--;
                }
                k++;
            }
        }
    }
}
