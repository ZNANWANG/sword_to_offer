package practice;

/**
 * 斐波那契数列
 * <p>
 * 题目描述
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 */
public class Prac7 {
    /**
     * 思路
     * 递归求解
     */
    public int Fibonacci1(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            return Fibonacci1(n - 1) + Fibonacci1(n - 2);
        }
    }

    /**
     * 思路
     * 赋值求解
     */
    public int Fibonacci2(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        } else {
            int a = 1;
            int b = 1;
            int c = 1;
            while (n > 2) {
                c = a + b;
                a = b;
                b = c;
                n--;
            }
            return c;
        }
    }
}
