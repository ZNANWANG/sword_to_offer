package interview;

import java.util.Scanner;
import java.util.Stack;

/**
 * 折木棍问题
 * <p>
 * 字节跳动算法笔试题
 * 题目描述
 * 在你的面前从左到右摆放着n根长短不一的木棍，你每次可以折断一根木棍，并将折断后得到的两根木棍一左一右放在原来的位置（即若原木棍有左
 * 邻居，则两根新木棍必须放在左邻居的右边，若原木棍有右邻居，新木棍必须放在右邻居的左边，所有木棍保持左右排列）。折断后的两根木棍的长
 * 度必须为整数，且它们之和等于折断前的木棍长度。你希望最终从左到右的木棍长度单调不减，那么你需要折断多少次呢？
 * <p>
 * 输入描述：
 * 第一行是一个数n，表示开始时有多少根木棍(1<=n<=3000)第二行有n个数，从第1个到第n个分别表示从左到右的木棍长度。对任意木棍的长度l，有1<=l<=3000。
 * 输出描述
 * 输出一行，一个数，你最少所需的折断木棍的次数x
 * <p>
 * 示例1
 * 输入:
 * 5
 * 3 5 13 9 12
 * 输出
 * 1
 * 说明
 * 你可以将长度为13的木棍折成长度分别为5和8的两根木棍，最终得到的排列是3 5 5 8 9 12
 * <p>
 * 注意下面用例：
 * 4
 * 3 5 9 8
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            getResult(sc);
        }
    }

    public static void getResult(Scanner sc) {
        int n = sc.nextInt();
        int[] tubs = new int[n];
        for (int i = 0; i < n; i++) {
            tubs[i] = sc.nextInt();
        }
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            int top = stack.peek();
            if (!stack.isEmpty() && top < tubs[i]) {
                int t = (tubs[i] - 1) / top;
                count += t;
                stack.push(tubs[i] / (top + 1));
                continue;
            }
            stack.push(tubs[i]);
        }
    }

    public static void getResult1(Scanner sc) {
        int n = sc.nextInt();
        int[] tubs = new int[n];
        for (int i = 0; i < n; i++) {
            tubs[i] = sc.nextInt();
        }
        int count = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (tubs[i + 1] < tubs[i]) {
                int t = (tubs[i] - 1) / tubs[i + 1];
                count += t;
                tubs[i] /= (t + 1);
            }
        }
    }
}
