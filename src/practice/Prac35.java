package practice;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 数组中的逆序对
 * <p>
 * 题目描述
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P
 * 对1000000007取模的结果输出。 即输出P%1000000007
 * <p>
 * 输入描述:
 * 题目保证输入的数组中没有的相同的数字
 * <p>
 * 数据范围：
 * 对于%50的数据,size<=10^4
 * <p>
 * 对于%75的数据,size<=10^5
 * <p>
 * 对于%100的数据,size<=2*10^5
 * <p>
 * 示例1
 * 输入
 * 1,2,3,4,5,6,7,0
 * 输出
 * 7
 */
public class Prac35 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input an array: ");
        while (sc.hasNext()) {
            String[] strs = sc.nextLine().split("\\s+");
            int[] array = new int[strs.length];
            for (int i = 0; i < strs.length; i++) {
                array[i] = Integer.parseInt(strs[i]);
            }
            System.out.println("array: " + Arrays.toString(array));
//            System.out.println(InversePairs2(array));
            mergeSort(array, 0, array.length - 1);
            System.out.println("sorted array: " + Arrays.toString(array));
            System.out.println("Please input an array: ");
        }
    }

    /**
     * 思路：
     * 蛮力遍历，时间复杂度为O(n^2)。
     */
    public static int InversePairs1(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    count++;
                    count %= 1000000007;
                }
            }
        }
        return count % 1000000007;
    }

    /**
     * 思路：
     * 归并排序，自底向上，时间复杂度O(n * logn)。
     */
    public static int InversePairs(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        return mergeSortCore(array, 0, array.length - 1);
    }

    public static int mergeSortCore(int[] array, int low, int high) {
        if (low == high) { // <----- 安全带
            return 0;
        }
        int mid = ((high - low) >> 1) + low;
        // 数据量大，需要取余。
        int left = mergeSortCore(array, low, mid) % 1000000007;
        int right = mergeSortCore(array, mid + 1, high) % 1000000007;
        return (left + right + merge(array, low, mid, high)) % 1000000007;
    }

    public static int merge(int[] array, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int index = 0;
        int count = 0;
        int p1 = low, p2 = mid + 1;
        while (p1 <= mid && p2 <= high) {
            // 与归并排序不同的地方，在merge过程中统计逆序对数
            if (array[p1] > array[p2]) {
                count += (mid - p1 + 1);
                count %= 1000000007;
                temp[index++] = array[p2++];
            } else {
                temp[index++] = array[p1++];
            }
        }
        while (p1 <= mid) {
            temp[index++] = array[p1++];
        }
        while (p2 <= high) {
            temp[index++] = array[p2++];
        }
        for (int i = 0; i < temp.length; i++) {
            array[low++] = temp[i];
        }

        return count;
    }

    /**
     * 剑指Offer版
     * 较难理解，不推荐。
     */
    public static int InversePairs2(int[] array) {
        if (array == null || array.length == 0)
            return 0;

        int[] copy = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        int count = InversePairsCore(array, copy, 0, array.length - 1) % 1000000007;
        return count;
    }

    public static int InversePairsCore(int[] array, int[] copy, int start, int end) {
        if (start == end) {
            copy[start] = array[start];
            return 0;
        }
        int length = (end - start) / 2;
        int left = InversePairsCore(copy, array, start, start + length) % 1000000007;
        int right = InversePairsCore(copy, array, start + length + 1, end) % 1000000007;
        System.out.println("copy: " + Arrays.toString(copy));
        System.out.println("array: " + Arrays.toString(array));
        int i = start + length; // middle
        int j = end;
        int indexCopy = end;
        int count = 0;
        while (i >= start && j >= start + length + 1) {
            if (array[i] > array[j]) {
                copy[indexCopy--] = array[i--];
                // 每一轮都是排好序的数组之间比较，假如i位置上的元素大于j位置上的元素，因为middle（即start + length）到j之间的元素
                //（不包括middle元素）都小于j位置上的元素， 所以i位置上面的元素都大于这些元素，因此count += j - start - length。
                count += j - start - length;
                count %= 1000000007;
            } else {
                copy[indexCopy--] = array[j--];
            }
        }
        for (; i >= start; i--) {
            copy[indexCopy--] = array[i];
        }

        for (; j >= start + length + 1; j--) {
            copy[indexCopy--] = array[j];
        }

        return left + right + count;
    }

    public static void mergeSort(int[] array, int start, int end) {
        if (start == end) {
            return;
        }
        int middle = (end + start) / 2;
        mergeSort(array, start, middle);
        mergeSort(array, middle + 1, end);
        merge1(array, start, middle, end);
    }

    public static void merge1(int[] array, int start, int middle, int end) {
        int i = start;
        int j = middle + 1;
        int[] temp = new int[end - start + 1];
        for (int index = 0; index <= end - start; index++) {
            if (i > middle) {
                temp[index] = array[j++];
            } else if (j > end) {
                temp[index] = array[i++];
            } else if (array[i] > array[j]) {
                temp[index] = array[j++];
            } else {
                temp[index] = array[i++];
            }
        }

        for (int k = 0; k < temp.length; k++) {
            array[start + k] = temp[k];
        }
    }
}
