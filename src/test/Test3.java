package test;

import java.util.Scanner;

/**
 * 统计数组中逆序对的个数
 */
public class Test3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

    }

    public static int InversePairs(int[] array) {
        if (array == null || array.length == 0)
            return 0;
        return mergeSort(array, 0, array.length - 1);
    }

    public static int mergeSort(int[] array, int start, int end) {
        if (start == end) {
            return 0;
        }
        int middle = (start + end) >> 1;
        int left = mergeSort(array, start, middle) % 1000000007;
        int right = mergeSort(array, middle + 1, end) % 1000000007;
        return (left + right + merge(array, start, middle , end)) % 1000000007;
    }

    public static int merge(int[] array, int start, int middle, int end) {
        int i = start, j = middle + 1, index = 0, count = 0;
        int[] temp = new int[end - start + 1];
        while (index <= end - start) {
            if (i > middle) {
                temp[index++] = array[j++];
            } else if (j > end) {
                temp[index++] = array[i++];
            } else if (array[i] > array[j]) {
                count += j - middle;
                count %= 1000000007;
                temp[index++] = array[j++];
            } else {
                temp[index++] = array[i++];
            }
        }
        for (int k = 0; k < temp.length; k++) {
            array[start + k] = temp[k];
        }
        return count;
    }
}
