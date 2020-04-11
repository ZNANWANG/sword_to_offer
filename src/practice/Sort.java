package practice;
/*
 * 排序算法集锦
 */
import java.util.*;

public class Sort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] str = sc.nextLine().split("\\s+");
            int[] array = new int[str.length];
            for (int i = 0; i < str.length; i++) {
                array[i] = Integer.parseInt(str[i]);
            }
//			bubbleSort(array);
//			selectionSort(array);
//			insertionSort(array);
//			System.out.println(Arrays.toString(mergeSort(array)));
//			quickSort(array);
//			shellSort(array);
//			heapSort(array);
            countingSort(array);

        }
    }

    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j + 1, j);
                }
            }
        }

        System.out.println(Arrays.toString(array));
    }

    public static void selectionSort(int[] array) {
        int minIndex = 0;
        for (int i = 0; i < array.length; i++) {
            minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }

        System.out.println(Arrays.toString(array));
    }

    public static void insertionSort(int[] array) {
        int preIndex = 0;
        int temp = 0;
        for (int i = 1; i < array.length; i++) {
            preIndex = i - 1;
            temp = array[i];
            while (preIndex >= 0 && array[preIndex] > temp) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = temp;
        }

        System.out.println(Arrays.toString(array));
    }

    public static int[] mergeSort(int[] array) {
        if (array.length < 2) {
            //System.out.println(Arrays.toString(array));
            return array;
        }
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j++];
            } else if (j >= right.length) {
                result[index] = left[i++];
            } else if (left[i] > right[j]) {
                result[index] = right[j++];
            } else {
                result[index] = left[i++];
            }
        }

        //System.out.println(Arrays.toString(result));
        return result;
    }

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    public static void quickSort(int[] array, int start, int end) {
        int i, j, pivot;
        if (start > end) {
            return;
        }
        i = start;
        j = end;

        // pivot就是基准位
        pivot = array[start];

        while (i < j) {
            // 先看右边，依次往左递减
            while (pivot <= array[j] && i < j) {
                j--;
            }
            // 再看左边，依次往右递增
            while (pivot >= array[i] && i < j) {
                i++;
            }
            // 如果满足条件则交换
            if (i < j) {
                swap(array, i, j);
            }
        }

        // 最后将基准为与i和j相等位置的数字交换
        swap(array, start, i);
        // 递归调用左半数组
        quickSort(array, start, j - 1);
        // 递归调用右半数组
        quickSort(array, j + 1, end);

    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }

    public static void shellSort(int[] array) {
        int gap = array.length / 2;
        int preIndex = 0;
        while (gap > 0) {
            for (int i = gap; i < array.length; i++) {
                preIndex = i - gap;
                while (preIndex >= 0 && array[preIndex] > array[i]) {
                    swap(array, preIndex + gap, preIndex);
                    preIndex -= gap;
                    System.out.println(Arrays.toString(array));
                }
            }
            gap /= 2;
        }
        System.out.println(Arrays.toString(array));
    }


    /**
     * 创建堆
     * @param arr 待排序列
     */
    public static void heapSort(int[] arr) {
        //创建堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }

        //调整堆结构+交换堆顶元素与末尾元素
        for (int i = arr.length - 1; i > 0; i--) {
            //将堆顶元素与末尾元素进行交换
            swap(arr, 0 ,i);

            //重新对堆进行调整
            adjustHeap(arr, 0, i);
        }

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 调整堆
     * @param arr 待排序列
     * @param parent 父节点
     * @param length 待排序列尾元素索引
     */
    private static void adjustHeap(int[] arr, int parent, int length) {
        //将temp作为父节点
        int temp = arr[parent];
        //左孩子
        int lChild = 2 * parent + 1;

        while (lChild < length) {
            //右孩子
            int rChild = lChild + 1;
            // 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
            if (rChild < length && arr[lChild] < arr[rChild]) {
                lChild++;
            }

            // 如果父结点的值已经大于孩子结点的值，则直接结束
            if (temp >= arr[lChild]) {
                break;
            }

            // 把孩子结点的值赋给父结点
            arr[parent] = arr[lChild];

            //选取孩子结点的左孩子结点,继续向下筛选
            parent = lChild;
            lChild = 2 * lChild + 1;
        }
        arr[parent] = temp;
    }

    /**
     * 计数排序
     *
     * @param array
     * @return
     */
    public static void countingSort(int[] array) {
        if (array.length == 0)
            System.out.println(Arrays.toString(array));

        int bias, min = array[0], max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max)
                max = array[i];
            if (array[i] < min)
                min = array[i];
        }
        bias = 0 - min;
        int[] bucket = new int[max - min + 1];
        Arrays.fill(bucket, 0); // 初始化全部元素为0
        for (int i = 0; i < array.length; i++) {
            bucket[array[i] + bias]++;
        }
        int index = 0, i = 0;
        while (index < array.length) {
            if (bucket[i] != 0) {
                array[index] = i - bias;
                bucket[i]--;
                index++;
            } else
                i++;
        }

        System.out.println(Arrays.toString(array));
    }
}