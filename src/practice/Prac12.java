package practice;

import java.util.*;

/**
 * 数值的整数次方
 * <p>
 * 题目描述
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * <p>
 * 保证base和exponent不同时为0
 */
public class Prac12 {
    public double Power(double base, int exponent) {
        return Math.pow(base, exponent);
    }

    public double Power1(double base, int exponent) {
        double res = 0;
        if (exponent > 0) {
            res = calculate(base, exponent);
        } else if (exponent < 0) {
            if (base > 1e15 || base < 1e-15) { //判断double型变量是否为0
                throw new RuntimeException("分母不可为0");
            } else {
                res = 1 / calculate(base, -exponent);
            }
        } else {
            res = 1;
        }
        return res;
    }

    /**
     * 折半运算，效率比较高。
     */
    public double calculate(double base, int exponent) {
        int res = 1;
        while (exponent != 0) {
            if ((exponent & 1) == 1) {
                res *= base;
            }
            base *= base;
            exponent = exponent >> 1;
        }
        return res;
    }

    /**
     * 折半运算，效率比较高
     */
    public double calculate1(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        } else if (exponent == 1) {
            return base;
        }
        double res = calculate1(base, exponent >> 1); //相当于exponnet / 2，折半运算
        res *= res;
        if ((exponent & 0x1) == 1) { //相当于exponent % 2，判断奇偶
            res *= base;
        }
        return res;
    }

    /**
     * 循环计算，效率比较低。
     */
    public double calculate2(double base, int exponent) {
        int res = 1;
        for (int i = 0; i < exponent; i++) {
            res *= base;
        }
        return res;
    }
}
