package test;

import java.util.Arrays;
import java.util.Random;

/**
 * 排序算法练习集锦
 */
public class Test2 {
    public static void main(String[] args) {
        Random random = new Random();
        int[] array = new int[10];
        for (int i = 0; i < 10; i++) {
            array[i] = random.nextInt(100);
        }
        System.out.println("former array: " + Arrays.toString(array));
//        bubbleSort(array);
//        insertionSort(array);
//        selectionSort(array);
//        quickSort(array);
//        array = mergeSort(array);
//        heapSort(array);
//        shellSort(array);
//        array = new int[]{2, 2, 2, 2, 2, 1, 3, 4, 5};
//        array = new int[]{1, 3, 4, 5, 2, 2, 2, 2, 2};
//        array = new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2};
//        array = new int[]{49, 38, 65, 97, 76, 13, 27, 49};
//        quickSort2(array, 0, array.length - 1);
        mergeSort(array, 0, array.length - 1);
        System.out.println("sorted array: " + Arrays.toString(array));
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Stable
    public static void bubbleSort(int[] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    // Stable
    public static void insertionSort(int[] array) {
        int length = array.length;
        for (int i = 1; i < length; i++) {
            int temp = array[i];
            int preIndex = i - 1;
            while (preIndex >= 0 && array[preIndex] > temp) { // 注意不能写成array[preIndex] > temp && preIndex 》= 0，否则会出现数组越界的情况。
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = temp;
        }
    }

    // Unstable
    // 例如：[5,8,8,4]
    public static void selectionSort(int[] array) {
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
    }

    public static void quickSort(int[] array) {
        int start = 0;
        int end = array.length - 1;
        quickSort(array, start, end);
    }

    // Unstable
    // 例如： [5,8,8,4]
    public static void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int pivot = array[start];
            int i = start;
            int j = end;
            while (i < j) {
                // 注意必须j先走，i后走，否则无法完成排序。
                while (array[j] >= pivot && i < j) {
                    j--;
                }
                while (array[i] <= pivot && i < j) {
                    i++;
                }
                swap(array, i, j);
            }
            swap(array, start, i);
            quickSort(array, start, j - 1);
            quickSort(array, j + 1, end);
        }
    }

    /**
     * 这版合并排序稳定但是空间复杂度高，每回都会复制左右两个子数组，合并的时候又会复制数组。
     */
    public static int[] mergeSort(int[] array) {
        int length = array.length;
        if (length < 2) {
            return array;
        } else {
            int middle = length / 2;
            int[] left = Arrays.copyOfRange(array, 0, middle);
            int[] right = Arrays.copyOfRange(array, middle, length);
            return merge(mergeSort(left), mergeSort(right));
        }
    }

    public static int[] merge(int[] left, int[] right) {
        int length = left.length + right.length;
        int[] res = new int[length];
        int i = 0, j = 0, k = 0;
        while (k < length) {
            if (i >= left.length) {
                res[k++] = right[j++];
            } else if (j >= right.length) {
                res[k++] = left[i++];
            } else if (left[i] > right[j]) {
                res[k++] = right[j++];
            } else {
                res[k++] = left[i++];
            }
        }
        return res;
    }

    /**
     * 这版合并排序空间复杂度低且稳定。
     */
    public static void mergeSort(int[] array, int start, int end) {
        if (array == null || array.length < 2) {
            return;
        }
        if (start == end) {
            return;
        }
        int middle = (start + end) / 2;
        mergeSort(array, start, middle);
        mergeSort(array, middle + 1, end);
        merge(array, start, middle, end);
    }

    public static void merge(int[] array, int start, int middle, int end) {
        int i = start;
        int j = middle + 1;
        int index = 0;
        int[] temp = new int[end - start + 1];
        while (index <= end - start) {
            if (i > middle) {
                temp[index++] = array[j++];
            } else if (j > end) {
                temp[index++] = array[i++];
            } else if (array[i] > array[j]) {
                temp[index++] = array[j++];
            } else {
                temp[index++] = array[i++];
            }
        }
        for (int k = 0; k < temp.length; k++) {
            array[start + k] = temp[k];
        }
    }

    // Unstable
    public static void heapSort(int[] array) {
        int length = array.length;
        for (int i = length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, length);
        }
        for (int i = 0; i < length - 1; i++) { // i < length - 1是因为最后一个顶点不用调，i < length也可以。
            swap(array, 0, length - 1 - i);
            adjustHeap(array, 0, length - 1 - i);
        }
    }

    // 排序思想感觉的冒泡排序一样，都是从最小的单元开始，不断累加调整。
    public static void adjustHeap(int[] array, int parent, int length) {
        int lChild = 2 * parent + 1;
        while (lChild < length) {
            int rChild = lChild + 1;
            if (rChild < length && array[rChild] > array[lChild]) {
                lChild++;
            }
            if (array[parent] <= array[lChild]) {
                swap(array, lChild, parent);
                parent = lChild;
                lChild = 2 * lChild + 1;
            } else {
                break;
            }
        }
    }

    // Unstable
    public static void shellSort(int[] array) {
        if (array.length > 1) {
            int gap = array.length / 2;
            while (gap > 0) {
                for (int i = gap; i < array.length; i++) {
                    int preIndex = i - gap;
                    while (preIndex >= 0 && array[preIndex] > array[preIndex + gap]) {
                        swap(array, preIndex, preIndex + gap);
                        preIndex--;
                    }
                }
                gap /= 2;
            }
        }
    }

    /**
     * 剑指Offer上的快速排序
     */
    public static void quickSort1(int[] array, int start, int end) {
        if (start == end) {
            return;
        }
        int index = partition1(array, start, end);
        if (index > start) {
            quickSort1(array, start, index - 1);
        }
        if (index < end) {
            quickSort1(array, index + 1, end);
        }
    }

    public static int partition1(int[] array, int start, int end) {
        int index = Random(start, end);
        System.out.println("index: " + index);
        swap(array, index, end);
        int small = start - 1;
        for (index = start; index < end; index++) {
            if (array[end] > array[index]) { //if(array[index] <= array[end])也可以
                small++;
                if (small != index) {
                    swap(array, index, small);
                }
            }
        }
        small++;
        swap(array, small, end);
        System.out.println("small: " + small);
        System.out.println(Arrays.toString(array));
        return small;
    }

    /**
     * 测试选取中间位置元素作为pivot后运用不同partition算法进行一轮partition之后的结果。
     */
    public static void quickSort2(int[] array, int start, int end) {
        if (start == end) {
            return;
        }
        int middle = (start + end) / 2;
        int index = partition3(array, middle, start, end);
        if (index > start) {
            quickSort2(array, start, index - 1);
        }
        if (index < end) {
            quickSort2(array, index + 1, end);
        }
    }

    // 剑指Offer版partitino
    public static int partition(int[] array, int index, int start, int end) {
        System.out.println("index: " + index);
        swap(array, index, end);
        int small = start - 1;
        for (index = start; index < end; index++) {
            if (array[end] > array[index]) { //if(array[index] <= array[end])也可以
                small++;
                if (small != index) {
                    swap(array, index, small);
                }
            }
        }
        small++;
        swap(array, small, end);
        System.out.println("small: " + small);
        System.out.println(Arrays.toString(array));
        return small;
    }

    /**
     * 这种双指针partition有问题，例如[1,3,4,5,2,2,2,2,2] -> [1,3,4,5,2,2,2,2,2]
     */
    public static int partition2(int[] array, int index, int start, int end) {
        int pivot = array[index];
        swap(array, start, index);
        int i = start;
        int j = end;

        while (i < j) {
            while (array[j] >= pivot && i < j) {
                j--;
            }
            while (array[i] <= pivot && i < j) {
                i++;
            }
            swap(array, i, j);
            System.out.println("i: " + i + " j: " + j);
        }
        swap(array, i, start);
        System.out.println("index: " + j);
        System.out.println("array: " + Arrays.toString(array));
        return j;
    }

    /**
     * 这版artition和剑指Offer版本的区别在于pivot选在首位。
     */
    public static int partition3(int[] array, int index, int start, int end) {
        swap(array, index, start);
        int pivot = array[start];
        int small = start;
        for (index = start + 1; index <= end; index++) {
            if (array[index] < pivot) {
                small++;
                if (index > small) {
                    swap(array, index, small);
                }
            }
        }
        if (small != start) {
            swap(array, small, start);
        }
        System.out.println("index: " + small);
        System.out.println("array: " + Arrays.toString(array));
        return small;
    }

    /**
     * 牛客网上的快排
     */
    public static void quickSort3(int[] array, int start, int end) {
        if (start < end) {
            int pivot = array[start];
            int i = start + 1;
            int j = end;
            while (i < j) {
                // 注意必须j先走，i后走，否则无法完成排序。
                while (array[j] > pivot && i < j) {
                    j--;
                }
                while (array[i] < pivot && i < j) {
                    i++;
                }
                swap(array, i, j);
            }
            swap(array, start, i);
            System.out.println("array: " + Arrays.toString(array));
            quickSort3(array, start, j - 1);
            quickSort3(array, j + 1, end);
        }
    }

    /**
     * 生成x和y之间的随机数，包括x和y。
     */
    public static int Random(int x, int y) {
        int max = Math.max(x, y);
        int min = Math.min(x, y);
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
