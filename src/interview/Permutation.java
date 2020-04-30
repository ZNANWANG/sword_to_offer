package interview;

/**
 * 全排列算法
 */

import java.util.*;

public class Permutation {
    public static void main(String[] args) {
        ArrayList<String> permutations1 = new ArrayList<>();
        int[] a = {4, 7, 2, 9};
        permute(a, 0, permutations1);
        System.out.println(permutations1);

        ArrayList<String> permutations2 = new ArrayList<>();
        int[] b = {3, 5, 2, 2};
        permute2(b, 0, permutations2);
        System.out.println(permutations2);
    }

    // 交换
    public static void swap(int a[], int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // DFS - 不包含重复数的全排列
    public static void permute(int array[], int index, ArrayList<String> permutations) {
        int length = array.length;
        if (index >= length - 1) {
            //displayArray(a);// 如果到达数组最后一个元素，则输出数组
            permutations.add(toString(array));
        } else {
            for (int i = index; i < length; i++) {
                swap(array, index, i);
                permute(array, index + 1, permutations);// 递归
                swap(array, index, i);
            }
        }
    }

    // DFS - 包含重复数的全排列，例如{2, 2} -> 2 2, 2 2
    public static void permute2(int array[], int index, ArrayList<String> permutations) {
        int length = array.length;
        List<Integer> exist = new ArrayList<Integer>();// 存放第零个位置已经有过的数字
        if (index >= length - 1) { // 注意改为index >= length同样成立，因为最后一轮实际上是最后一个字符自己和自己调换，可以省去来提高效率。
            //displayArray(array);
            permutations.add(toString(array));
        } else {
            for (int i = index; i < length; i++) {
                if (!exist.contains(array[i])) { // 如果arr[i]不在exist中，则可以把arr[i]放在第零个位置
                    exist.add(array[i]);// 将arr[i]放进exist
                    swap(array, i, index);
                    permute2(array, index + 1, permutations);
                    swap(array, i, index);
                }
            }
        }
    }

    public static String toString(int[] array) {
        StringBuffer sb = new StringBuffer();
        for (int a : array) {
            sb.append(a);
        }
        return sb.toString();
    }

    // 输出数组
    public static void displayArray(int a[]) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("");
    }

}