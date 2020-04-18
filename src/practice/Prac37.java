package practice;

import java.util.*;

/**
 * 统计数字在排序数组中出现的次数
 * <p>
 * 题目描述
 * 统计一个数字在排序数组中出现的次数。
 */
public class Prac37 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        binarySearchTest(sc);
        getNumberOfKTest(sc);
        sc.close();
    }

    public static void getNumberOfKTest(Scanner sc) {
        System.out.println("Please input a number (input -1 to exit the program): ");
        int[] array = generateArray(10);
        System.out.println("array: " + Arrays.toString(array));
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (n == -1) {
                break;
            }
            System.out.println("number: " + GetNumberOfK4(array, n));
            System.out.println("Please input a number (input -1 to exit the program): ");
            System.out.println("array: " + Arrays.toString(array));
        }
    }

    public static void binarySearchTest(Scanner sc) {
        System.out.println("Please input a number (input -1 to exit the program): ");
        int[] array = generateArray(10);
        System.out.println("array: " + Arrays.toString(array));
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (n == -1) {
                break;
            }
            int index1 = binarySearchFirstK(array, n, 0, array.length - 1);
            int index2 = binarySearchLastK(array, n, 0, array.length - 1);
            System.out.println("first index: " + index1);
            System.out.println("last index: " + index2);
            System.out.println("Please input a number (input -1 to exit the program): ");
            System.out.println("array: " + Arrays.toString(array));
        }
    }

    /**
     * 生成指定长度的有序数组（从小到大排序）
     */
    public static int[] generateArray(int length) {
        int[] result = new int[length];
        Random random = new Random();
        for (int i = 0; i < result.length; i++) {
            result[i] = random.nextInt(20);
        }
        Arrays.sort(result);
        return result;
    }

    /**
     * 思路：
     * 蛮力遍历
     */
    public static int GetNumberOfK(int[] array, int k) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int count = 0;
        for (int a : array) {
            if (a == k) {
                count++;
            }
        }
        return count;
    }

    /**
     * 思路：
     * 蛮力遍历加判断及时跳出循环（利用有序数组的特性）
     */
    public static int GetNumberOfK1(int[] array, int k) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int count = 0;
        for (int a : array) {
            if (a == k) {
                count++;
            } else {
                if (count > 0) {
                    break;
                }
            }
        }
        return count;
    }

    /**
     * 思路：
     * 利用List的indexOf()和lastIndexOf()方法
     */
    public static int GetNumberOfK2(int[] array, int k) {
        if (array == null || array.length == 0) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        for (int a : array) {
            list.add(a);
        }
        return list.indexOf(k) == -1 ? 0 : list.lastIndexOf(k) - list.indexOf(k) + 1;
    }

    /**
     * 思路：
     * 利用hashmap统计各个字符出现的次数
     */
    public static int GetNumberOfK3(int[] array, int k) {
        if (array == null || array.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : array) {
            if (map.containsKey(a)) {
                map.put(a, map.get(a) + 1);
            } else {
                map.put(a, 1);
            }
        }
        return map.containsKey(k) == true ? map.get(k) : 0;
    }

    public static int binarySearch(int[] array, int k) {
        if (array == null || array.length == 0) {
            return -1;
        }
        return binarySearch1(array, k, 0, array.length - 1);
    }

    /**
     * 递归版二分查找
     */
    public static int binarySearch(int[] array, int k, int start, int end) {
        if (start > end || start < 0 || end >= array.length || array[start] > array[end]) {
            return -1;
        }
        int middle = (start + end) >> 1;
//        System.out.println("middle: " + middle);
        if (array[middle] == k) {
            return middle;
        } else if (array[middle] > k) {
            return binarySearch(array, k, start, middle - 1);
        } else {
            return binarySearch(array, k, middle + 1, end);
        }
    }

    /**
     * 非递归版二分查找
     */
    public static int binarySearch1(int[] array, int k, int start, int end) {
        if (start > end || start < 0 || end >= array.length || array[start] > array[end]) {
            return -1;
        }
        while (start <= end) {
            int middle = (start + end) >> 1;
            if (array[middle] == k) {
                return middle;
            } else if (array[middle] < k) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return -1;
    }

    /**
     * 思路：
     * 二分查找第一个k所在的索引和最后一个k所在的索引。
     */
    public static int GetNumberOfK4(int[] array, int k) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int first = binarySearchFirstK(array, k, 0, array.length - 1);
        if (first == -1) {
            return 0;
        }
        int last = binarySearchLastK(array, k, 0, array.length - 1);
        return last - first + 1;
    }

    /**
     * 查找第一个k所在的索引
     */
    public static int binarySearchFirstK(int[] array, int k, int start, int end) {
        if (array == null || array.length == 0 || start < 0 || end > array.length - 1 || array[start] > array[end]) {
            return -1;
        }
        while (start <= end) {
            int middle = (start + end) >> 1;
            if (array[middle] == k) {
                if (middle - 1 >= 0 && array[middle - 1] == k) {
                    end = middle - 1;
                } else {
                    return middle;
                }
            } else if (array[middle] > k) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个k所在的索引
     */
    public static int binarySearchLastK(int[] array, int k, int start, int end) {
        if (array == null || array.length == 0 || start < 0 || end > array.length - 1 || array[start] > array[end]) {
            return -1;
        }
        while (start <= end) {
            int middle = (start + end) >> 1;
            if (array[middle] == k) {
                if (middle + 1 < array.length && array[middle + 1] == k) {
                    start = middle + 1;
                } else {
                    return middle;
                }
            } else if (array[middle] > k) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }
}
