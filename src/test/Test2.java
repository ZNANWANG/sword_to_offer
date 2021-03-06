package test;

import java.util.*;

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
        array = new int[]{1, 3, 4, 5, 2, 2, 2, 2, 2};
//        array = new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2};
//        array = new int[]{49, 38, 65, 97, 76, 13, 27, 49};
//        array = new int[]{-1, -3, -9, -4, 0, 6, 2, 8, 6, 4};
        quickSort2(array, 0, array.length - 1);
//        mergeSort(array, 0, array.length - 1);
//        quickSort4(array);
//        comparisonCountingSort(array);
//        distributionCountingSort(array);
//        bucketSort(array);
        radixSort(array);
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
        int index = random(start, end);
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
        int index = partition2(array, middle, start, end);
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
     * 双指针partition
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
     * 这版partition和剑指Offer版本的区别在于pivot选在首位。
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
     * 受广发银行笔试题目启发，测试一下另一个版本的快排。
     */
    public static  void quickSort4(int[] array){
        if(array == null || array.length < 2){
            return;
        }
        quickSortHelper(array, 0, array.length - 1);
    }

    public static void quickSortHelper(int[] array, int start, int end){
        if(start < 0 || end >= array.length || start > end){
            return;
        }

        int i = start;
        int j = end;
        int pivot = array[start];
        while(i < j){
            while (i < j && array[j] > pivot){
                j--;
            }
            if(i < j){
                array[i] = array[j];
            }
            while(i < j && array[i] < pivot){
                i++;
            }
            if(i < j){
                array[j--] = array[i];
            }
            System.out.println(Arrays.toString(array));
        }
        array[i] = pivot;
        quickSortHelper(array, start, i - 1);
        quickSortHelper(array, i + 1, end);
    }

    /**
     * 比较计数排序 —— 稳定但不在位
     * 能解决含重复数的数组，思想很精妙，自底向上。
     */
    public static void comparisonCountingSort(int[] array){
        if(array == null || array.length < 2){
            return;
        }
        int[] counts = new int[array.length];
        for(int i = 0; i < array.length - 1; i++){
            int count = 0;
            for(int j = i + 1; j < array.length; j++){
                if(array[j] < array[i]){
                    counts[i]++;
                } else {
                    counts[j]++;
                }
            }
        }
        int[] copy = Arrays.copyOf(array, array.length);
        System.out.println("counts: " + Arrays.toString(counts));
        for(int i = 0; i < counts.length; i++){
            int count = counts[i];
            array[count] = copy[i];
        }
    }

    /**
     * 统计计数排序 —— 稳定但不在位
     */
    public static void distributionCountingSort(int[] array){
        if(array == null || array.length < 2){
            return;
        }
        // 首先寻找数组最大值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int a : array){
            max = max > a ? max : a;
            min = min < a ? min : a;
        }
        // 初始化一个最大值 - 最小值 + 1长度的数组用于存放计数
        int[] counts = new int[max - min + 1];
        for(int i = 0; i < array.length; i++){
            counts[array[i] - min]++;
        }
        // 计数数组累加，使每个数找到相应的位置
        for(int i = 1; i < counts.length; i++){
            counts[i] += counts[i - 1];
        }
        System.out.println("counts: " + Arrays.toString(counts));

        // 复制一个参照数组，从后往前遍历是为了保持排序稳定性，针对重复数字，每放入一个数字，相应计数减一
        int[] copy = Arrays.copyOf(array, array.length);
        for(int i = copy.length - 1; i >= 0; i--){
            int current = copy[i];
            int index = counts[current - min];
            counts[current- min]--;
            array[index - 1] = current;
        }
    }

    /**
     * 桶排序 —— 稳定但不在位
     */
    public static void bucketSort(int[] array){
        if(array == null || array.length < 2){
            return;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int a : array){
            max = max > a ? max : a;
            min = min < a ? min : a;
        }

        // 初始化桶的集合，因为桶内元素会频繁地插入，所以选择 LinkedList 作为桶的数据结构。
        ArrayList<LinkedList<Integer>>  bucketList = new ArrayList<>();
        for(int i  = 0; i < array.length; i++){
            bucketList.add(new LinkedList<>());
        }

        // 向桶内添加数字，并插入排序
        for(int i = 0; i < array.length; i++){
            int num = getBucketNum(array, i, min);
            bucketList.get(num).add(array[i]);
            insertionSort(bucketList.get(num));
        }

        // 将桶内数字填入到原数组中
        int index = 0;
        for(int i = 0; i < bucketList.size(); i++){
            for(int j = 0;j < bucketList.get(i).size(); j++){
                array[index++] = bucketList.get(i).get(j);
            }
        }
    }

    /**
     * 基数排序（List) —— 稳定但不在位
     */
    public static void radixSort(int[] array){
        if(array == null || array.length < 2){
            return;
        }
        int max = Integer.MIN_VALUE;
        for(int a : array){
            max = max > a ? max : a;
        }
        int radix = 1;
        // 初始化桶的集合
        ArrayList<LinkedList<Integer>> buckets = new ArrayList<LinkedList<Integer>>();
        for(int i = 0; i < 10; i++){
            buckets.add(new LinkedList<>());
        }
        while(radix <= max){
            // 将数字根据基数放入桶中
            for(int i = 0; i < array.length; i++){
                int num = (array[i] / radix) % 10;
                buckets.get(num).add(array[i]);
            }
            // 将本轮基数排序的结果放入数组中，以便下轮使用，这一步是计数排序的精髓。
            int index = 0;
            for(int i = 0; i < buckets.size(); i++){
                for(int j = 0; j < buckets.get(i).size(); j++){
                    array[index++] = buckets.get(i).get(j);
                }
            }
            // 清空桶，为下一轮做准备。
            for(int i = 0; i < 10; i++){
                buckets.get(i).clear();
            }
            // 基数前移
            radix *= 10;
        }
    }

    /**
     * 基数排序（int[][]) —— 稳定但不在位
     */
    public static void radixSort1(int[] array){
        if(array == null || array.length < 2){
            return;
        }
        int max = 0;
        for(int a : array){
            max = max > a ? max : a;
        }
        int radix = 1;
        // 不放入循环内初始化桶数组和计数数组是为了降低空间复杂度
        int[][] buckets = new int[10][array.length];
        int[] counts = new int[10];
        while (radix <= max){
            // 将数字根据基数放入桶内
            for(int i = 0; i < array.length; i++){
                int num = (array[i] / radix) % 10;
                buckets[num][counts[num]++] = array[i];
            }
            System.out.println(Arrays.toString(counts));
            // 将本轮基数排序的结果放入数组中，以便下轮使用，这一步是计数排序的精髓。
            int index = 0;
            for(int i = 0; i < buckets.length; i++){
                for(int j = 0; j < counts[i]; j++){
                    array[index++] = buckets[i][j];
                }
                // 清空计数数组各单元，以便下轮使用。
                counts[i] = 0;
            }
            System.out.println(Arrays.toString(array));
            // 基数前移
            radix *= 10;
        }
    }

    /**
     * 计算得到输入元素应该放到哪个桶内，实际开发中需要根据场景具体设计。
     */
    public static int getBucketNum(int[] array, int i, int min){
        return (array[i] - min) / array.length;
    }

    /**
     * 对每个桶进行排序
     */
    public static void insertionSort(LinkedList<Integer> list){
        for(int i = 1; i < list.size(); i++){
            int temp = list.get(i);
            int preIndex = i - 1;
            while(preIndex >= 0 && list.get(preIndex) > temp){
                list.set(preIndex + 1, list.get(preIndex));
                preIndex--;
            }
            list.set(preIndex + 1, temp);
        }
    }

    /**
     * 生成x和y之间的随机数，包括x和y。
     */
    public static int random(int x, int y) {
        int max = Math.max(x, y);
        int min = Math.min(x, y);
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
