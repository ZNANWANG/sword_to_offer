package practice;

import java.net.Inet4Address;
import java.util.*;

/**
 * 丑数
 * <p>
 * 题目描述
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。
 * 求按从小到大的顺序的第N个丑数。
 */
public class Prac33 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input a number (input 'q' to exit the program): ");
        while (sc.hasNext()) {
            int number = sc.nextInt();
//            System.out.println(getAllPrimeFactors(number));
            System.out.println(GetUglyNumber_Solution1(number));
            System.out.println("Please input a number: ");
        }
    }

    /**
     * 思路：
     * 蛮力查找每个数的质因数，然后判断是不是丑数，算法复杂度较高，不推荐。
     */
    public static int GetUglyNumber_Solution(int index) {
        int count = 0;
        for (int i = 30; i < Integer.MAX_VALUE; i++) {
            Set<Integer> primeFactors = getAllPrimeFactors(i);
            if (isUglyNumber(primeFactors)) {
                count++;
                if (count == index) {
                    return i;
                }
            }
        }
        return 0;
    }

    public static boolean isUglyNumber(Set<Integer> primeFactors) {
        for (Integer a : primeFactors) {
            if (a != 2 && a != 3 && a != 5) {
                return false;
            }
        }
        return true;
    }

    public static Set<Integer> getAllPrimeFactors(int number) {
        Set<Integer> primeFactors = new TreeSet<>();
        int prime = 2;
        while (number >= prime) {
            if (number % prime == 0) {
                primeFactors.add(prime);
                number /= prime;
            } else {
                prime++;
            }
        }
        return primeFactors;
    }

    /**
     * 剑指Offer版算法
     */
    public static int GetUglyNumber_Solution1(int index) {
        int number = 0;
        int count = 0;
        while (true) {
//            System.out.println(number);
            if (isUglyNumber(number)) {
                count++;
                if (count == index) {
                    return number;
                }
            }
            number++;
        }
    }

    public static boolean isUglyNumber(int number) {
        // 注意0会陷入死循环，习惯上我们把1当做是第一个丑数。
        if (number > 0) {
            while (number % 2 == 0) {
                number /= 2;
            }
            while (number % 3 == 0) {
                number /= 3;
            }
            while (number % 5 == 0) {
                number /= 5;
            }
            return number == 1 ? true : false;
        }
        return false;
    }

    public static int GetUglyNumber_Solution2(int index) {
        if (index > 0) {
            int count = 0;
            int[] uglyNumbers = new int[index];
            uglyNumbers[count] = 1;
            int i2 = 0;
            int i3 = 0;
            int i5 = 0;

            while (count + 1 < index) {
                int min = min(uglyNumbers[i2] * 2, uglyNumbers[i3] * 3, uglyNumbers[i5] * 5);
                if (min == uglyNumbers[i2] * 2)
                    i2++;
                if (min == uglyNumbers[i3] * 3)
                    i3++;
                if (min == uglyNumbers[i5] * 5)
                    i5++;
                uglyNumbers[++count] = min;
            }
            return uglyNumbers[count];
        }
        return 0;
    }

    public static int min(int x, int y, int z) {
        int min = x > y ? y : x;
        min = min > z ? z : min;
        return min;
    }

    /**
     * 思路：
     * 很巧妙的思想，丑数是另一个丑数的2倍、3倍或者5倍（1除外），代码中i2，i3，i5表示接下来要乘的丑数索引，并且用过一次不能再用，所以每回加一。
     */
    public static int GetUglyNumber_Solution3(int index) {
        if (index > 0) {
            int count = 0;
            int[] uglyNumbers = new int[index];
            uglyNumbers[count] = 1;
            int i2 = 0;
            int i3 = 0;
            int i5 = 0;

            while (true) {
                // 很有意思的一种while格式的变换，实际上和上面写法大同小异，记录下来以供玩味。
                if (count + 1 == index) {
                    return uglyNumbers[count];
                }

                int min = min(uglyNumbers[i2] * 2, uglyNumbers[i3] * 3, uglyNumbers[i5] * 5);
                if (min == uglyNumbers[i2] * 2)
                    i2++;
                if (min == uglyNumbers[i3] * 3)
                    i3++;
                if (min == uglyNumbers[i5] * 5)
                    i5++;
                uglyNumbers[++count] = min;
            }
        }
        return 0;
    }
}
