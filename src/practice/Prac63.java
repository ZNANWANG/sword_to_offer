package practice;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 数据流中的中位数
 * <p>
 * 题目描述
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数
 * 个数值，那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的
 * 中位数。
 */
public class Prac63 {
    public static void main(String[] args) {
        int[] array = new int[]{4, 7, 2, 9, 3, 1, 5};
        Prac63 test = new Prac63();
//        test.heapSort(array);
        System.out.println(Arrays.toString(array));
        ArrayList<Integer> list = new ArrayList<>();
        for (int a : array) {
            list.add(a);
        }
        System.out.println(list);
//        bubbleSort(list);
        insertionSort(list);
        System.out.println(list);
    }

    /**
     * 思路：
     * 利用了冒泡排序自底向上的思想，每读入一个数字，插入到列表首位并进行一次冒泡。
     */
    ArrayList<Integer> list = new ArrayList<>();

    public void Insert(Integer num) {
        list.add(0, num);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                swap(list, i, i + 1);
            }
        }
    }

    /**
     * 思路：
     * 利用插入排序方法将新读入的数字插入到合适的位置，同样是自底向上的思想。
     */
    public void Insert1(Integer num) {
        list.add(num);
        int preIndex = list.size() - 2;
        while (preIndex >= 0 && list.get(preIndex) > num) {
            list.set(preIndex + 1, list.get(preIndex));
            preIndex--;
        }
        list.set(preIndex + 1, num);
    }

    public Double GetMedian() {
        double result = 0.0;
        int temp = list.size() >> 1;
        if (list.size() % 2 == 0) {
            result = (list.get(temp) + list.get(temp - 1)) / 2.0;
        } else {
            result = list.get(temp);
        }
        return result;
    }

    public static void swap(ArrayList<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     * 插入排序
     */
    public static void insertionSort(ArrayList<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            int preIndex = i - 1;
            int temp = list.get(i);
            while (preIndex >= 0 && list.get(preIndex) > temp) {
                list.set(preIndex + 1, list.get(preIndex));
                preIndex--;
            }
            list.set(preIndex + 1, temp);
        }
    }

    /**
     * 冒泡排序
     */
    public static void bubbleSort(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    swap(list, j + 1, j);
                }
            }
        }
    }

    /**
     * 堆排序
     */
    public void heapSort(int[] array) {
        if (array == null || array.length == 1) {
            return;
        }
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }
        for (int i = array.length - 1; i >= 0; i--) {
            swap(array, 0, i);
            adjustHeap(array, 0, i);
        }
    }

    /**
     * 构造最大堆
     */
    public void adjustHeap(int[] array, int parent, int length) {
        int lChild = parent * 2 + 1;
        while (lChild < length) {
            int rChild = lChild + 1;
            if (rChild < length && array[rChild] > array[lChild]) {
                lChild = rChild;
            }

            if (array[parent] > array[lChild]) {
                break;
            }

            swap(array, parent, lChild);
            parent = lChild;
            lChild = 2 * lChild + 1;
        }
    }

    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
