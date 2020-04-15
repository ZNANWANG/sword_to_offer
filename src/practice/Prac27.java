package practice;

import java.util.*;

/**
 * 字符串的排列
 * <p>
 * 题目描述
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * 输入描述:
 * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 */
public class Prac27 {
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> permutations = new ArrayList<>();
        if (!str.equals("")) {
            char[] chars = str.toCharArray();
            helper(chars, 0, permutations);
        }
        Collections.sort(permutations);
        return permutations;
    }

    public void helper(char[] arr, int index, ArrayList<String> permutations) {
        int length = arr.length;
        ArrayList<Character> exist = new ArrayList<>();
        if (index >= length) {
            String res = String.valueOf(arr);
            permutations.add(res);
        } else {
            for (int i = index; i < length; i++) {
                if (!exist.contains(arr[i])) {
                    exist.add(arr[i]);
                    swap(arr, i, index);
                    helper(arr, index + 1, permutations);
                    swap(arr, i, index);
                }
            }
        }
    }

    public void helper1(char[] arr, int index, ArrayList<String> permutations) {
        int length = arr.length;
        if (index >= length) {
            String res = String.valueOf(arr);
            if (!permutations.contains(res)) {
                permutations.add(res);
            }
        } else {
            for (int i = index; i < length; i++) {
                swap(arr, i, index);
                helper1(arr, index + 1, permutations);
                swap(arr, i, index);
            }
        }
    }

    /**
     * 利用了TreeSet自动排序加去重的功能
     */
    public ArrayList<String> Permutation1(String str) {
        Set<String> permutations = new TreeSet<>();
        if (!str.equals("")) {
            char[] chars = str.toCharArray();
            helper2(chars, 0, permutations);
        }
        return new ArrayList<>(permutations);
    }

    public void helper2(char[] arr, int index, Set<String> permutations) {
        int length = arr.length;
        if (index >= length) {
            String res = String.valueOf(arr);
            permutations.add(res);
        } else {
            for (int i = index; i < length; i++) {
                swap(arr, i, index);
                helper2(arr, index + 1, permutations);
                swap(arr, i, index);
            }
        }
    }

    public void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 2、字典序排列算法
     * <p>
     * 可参考解析： http://www.cnblogs.com/pmars/archive/2013/12/04/3458289.html  （感谢作者）
     * <p>
     * 一个全排列可看做一个字符串，字符串可有前缀、后缀。
     * 生成给定全排列的下一个排列.所谓一个的下一个就是这一个与下一个之间没有其他的。
     * 这就要求这一个与下一个有尽可能长的共同前缀，也即变化限制在尽可能短的后缀上。
     * <p>
     * 【例]】839647521是1--9的排列。1—9的排列最前面的是123456789，最后面的987654321，
     * 从右向左扫描若都是增的，就到了987654321，也就没有下一个了。否则找出第一次出现下降的位置。
     * <p>
     * 【例】 如何得到346987521的下一个
     * 1，从尾部往前找第一个P(i-1) < P(i)的位置
     * 3 4 6 <- 9 <- 8 <- 7 <- 5 <- 2 <- 1
     * 最终找到6是第一个变小的数字，记录下6的位置i-1
     * <p>
     * 2，从i位置往后找到最后一个大于6的数
     * 3 4 6 -> 9 -> 8 -> 7 5 2 1
     * 最终找到7的位置，记录位置为m
     * <p>
     * 3，交换位置i-1和m的值
     * 3 4 7 9 8 6 5 2 1
     * 4，倒序i位置后的所有数据
     * 3 4 7 1 2 5 6 8 9
     * 则347125689为346987521的下一个排列
     */
    public ArrayList<String> Permutation2(String str) {
        ArrayList<String> list = new ArrayList<String>();
        if (str == null || str.length() == 0) {
            return list;
        }
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        list.add(String.valueOf(chars));
        int len = chars.length;
        while (true) {
            int lIndex = len - 1;
            int rIndex;
            while (lIndex >= 1 && chars[lIndex - 1] >= chars[lIndex]) {
                lIndex--;
            }
            if (lIndex == 0)
                break;
            rIndex = lIndex;
            while (rIndex < len && chars[rIndex] > chars[lIndex - 1]) {
                rIndex++;
            }
            swap(chars, lIndex - 1, rIndex - 1);
            reverse(chars, lIndex);

            list.add(String.valueOf(chars));
        }

        return list;
    }

    private void reverse(char[] chars, int k) {
        if (chars == null || chars.length <= k)
            return;
        int len = chars.length;
        for (int i = 0; i < (len - k) / 2; i++) {
            int m = k + i;
            int n = len - 1 - i;
            if (m <= n) {
                swap(chars, m, n);
            }
        }
    }
}
