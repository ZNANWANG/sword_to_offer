package practice;

/**
 * 二进制中1的个数
 * <p>
 * 题目描述
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 */
public class Prac11 {
    /**
     * 思路
     * 暴力遍历
     */
    public int NumberOf1(int n) {
        String str = Integer.toBinaryString(n);
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '1') {
                count++;
            }
        }
        return count;
    }

    /**
     * 思路
     * 左移比较
     * 注：1.一直右移n和1做与运算只适用于非负数，负数右移会一直补1，导致无限循环。
     * 2.int总位数为32，因此只需要循环32次。
     * 3.把整数右移一位相当于把整数除以二，但是除法运算效率低，所以用移位运算。
     */
    public int NumberOf12(int n) {
        int count = 0;
        int flag = 1;
        int loop = 32;
        while (loop > 0) {
            if ((n & flag) == flag) {
                count++;
            }
            flag = flag << 1;
            loop--;
        }
        return count;
    }

    /**
     * 思路
     * 把一个整数减去一，再把得到的数和原来的整数做与运算，最后得到的数和原来的整数相比最右边的1变为了0
     * 例如：1100 & （1100 - 1） = 1100 & 1011 = 1000，可以看出最右边的1变为了0.
     */
    public int NumberOf13(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }
}
