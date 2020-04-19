package practice;

import java.util.*;

/**
 * 和为S的两个数字
 * <p>
 * 题目描述
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 * 输出描述:
 * 对应每个测试案例，输出两个数，小的先输出。
 */
public class Prac41 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] array = new int[]{1, 2, 4, 7, 11, 15};
        System.out.println("arary: " + Arrays.toString(array));
        System.out.println("Please input a number (input -1 to exit):");
        while (sc.hasNext()) {
            try {
                int sum = sc.nextInt();
                if (sum == -1) {
                    break;
                }
                System.out.println(FindNumbersWithSum(array, sum));
                System.out.println("arary: " + Arrays.toString(array));
                System.out.println("Please input a number (input -1 to exit):");
            } catch (InputMismatchException e) {
                String s = sc.next();
                System.out.println("Invalid number, please input a number.");
                System.out.println("arary: " + Arrays.toString(array));
                System.out.println("Please input a number (input -1 to exit):");
            }
        }
        sc.close();
    }

    /**
     * 思路：
     * 因为数组为递增排序，所以考虑使用双指针，head指向数组第一个数，tail指向最后一个数，初始化一个空列表result用来存放满足要求的两
     * 个数；判断array[head] + array[tail]与sum的关系：若大于sum，则将tail指针左移；若小于sum，则将head指针右移；若相等，则判断
     * 两数相乘是否为当前最小值，如果是，可以添加到result里，如果不是，双指针继续往中间扫。
     */
    public static ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        if (array == null) {
            return null;
        }
        ArrayList<Integer> result = new ArrayList<>();
        int head = 0;
        int tail = array.length - 1;
        int min = Integer.MAX_VALUE;
        while (head <= tail) {
            System.out.println("head: " + head);
            System.out.println("tail: " + tail);
            int left = array[head];
            int right = array[tail];
            if (left + right == sum) {
                if (left * right < min) {
                    min = left * right;
                    if (result.size() > 0) {
                        result.remove(0);
                        result.remove(1);
                    }
                    result.add(0, left);
                    result.add(1, right);
                }
                head++;
                tail--;
            } else if (left + right > sum) {
                tail--;
            } else {
                head++;
            }
        }
        return result;
    }
}
