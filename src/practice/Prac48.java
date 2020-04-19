package practice;

import java.math.BigInteger;

/**
 * 不用加减乘除做加法
 * <p>
 * 题目描述
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 */
public class Prac48 {
    /**
     * 思路：
     * 第一步：相加各位的值，不算进位，二进制每位相加就相当于各位做异或操作；
     * 第二步：计算进位值，相当于各位做位与操作，再向左移一位；
     * 第三步：重复上述两步， 各位相加 ，计算进位值。直到进位值为0，跳出循环。
     */
    public int Add(int num1, int num2) {
        int result = 0;
        int carry = 0;
        do {
            result = num1 ^ num2;       //不带进位的加法
            carry = (num1 & num2) << 1; //进位
            num1 = result;
            num2 = carry;
        } while (carry != 0); // 进位不为0则继续执行加法处理进位；注意不能改为carry > 0，因为carry有可能成为负数。
        return result;
    }
}
