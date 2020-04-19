package practice;

import java.util.ArrayList;
import java.util.List;

/**
 * 把字符串转换成整数
 * <p>
 * 题目描述
 * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
 * 输入描述:
 * 输入一个字符串,包括数字字母符号,可以为空
 * 输出描述:
 * 如果是合法的数值表达则返回该数字，否则返回0
 * 示例1
 * 输入
 * +2147483647
 * 1a33
 * 输出
 * 2147483647
 * 0
 */
public class Prac49 {
    public static void main(String[] args) {
        String str = "+123";
        System.out.println(StrToInt4(str));
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 10; i++) {
            System.out.println(max + i);
        }
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < 10; i++) {
            System.out.println(max - i);
        }
    }

    /**
     * 思路：
     * 利用正负变换判断是否越界
     */
    public static int StrToInt(String str) {
        if (str == null) {
            return 0;
        }
        // 用list的索引作为数字的值
        char[] dict = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        List<Character> list = new ArrayList<>();
        for (char c : dict) {
            list.add(c);
        }
        boolean isNegative = false;
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                int current = list.indexOf(c);
                if (isNegative) {
                    result = result * 10 - current;
                    if (result > 0) { // 判断是否越界
                        return 0;
                    }
                } else {
                    result = result * 10 + current;
                    if (result < 0) { // 判断是否越界
                        return 0;
                    }
                }
            } else {
                if (i == 0) {
                    if (c == '+') {

                    } else if (c == '-') {
                        isNegative = true;
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            }
        }
        return result;
    }

    /**
     * 思路：
     * 一、关于最小负数：从true、false到 -1、1
     * 一般而言，我们习惯性地使用 boolean 类型来表示数字是否为负数（标志位 isNegtive），并在计算过程中使用正数来表示中间结果，只有
     * 在最后一步才根据标志 isNegtive 将结果取负，这样一样，因为最大正数的绝对值小于最小负数绝对值，所以当值为 INT_MIN 时，将导致
     * 结果出错。事实上， 只要将正负标志的类型由 bool 类型改成 int 类型，当符号为正时，标志为1，符号为负时，标志为 -1，从而使正负
     * 标志位可以参与到运算过程中去，就可以巧妙地解决这一问题，：
     * <p>
     * 原先中间值的计算：value = value * 10 + digit;
     * 此时中间值的计算：value = value * 10 + isNegtive * digit;
     * 如此一来，当数值为负时，中间结果也为负，并执行的是减法操作，就可以取得 -2147483648 的值
     * 而当数值为正时，中间结果为正，执行的是加法操作。
     * <p>
     * 二、关于数值越界：合理利用 Integer.MAX_VALUE/10
     * 数值越界，即大于 2147483647，或小于 -2147483648。通过观察程序结构，我们发现，每次循环时 value 的值都会扩大10倍（乘以10），
     * 那么，我们是否就可以利用 Integer.MAX_VALUE/10 的值来提前一步判断是否会越界呢？这里我们以正数的越界为例进行解释：
     * <p>
     * 当 value > Integer.MAX_VALUE/10 时，说明本轮扩大10倍后，value 必将越界（超过 Integer.MAX_VALUE）；
     * 当 value == Integer.MAX_VALUE/10 时，说明扩大10倍后，value 可能越界，也可能不越界，需要利用当前的加数 digit 来进行进一步
     * 的判断：当 digit > 7 时（以正数为例），越界；
     * 否则，当 value < INT_MAX/10 时，本轮循环必不越界（扩大10倍后也小于 Integer.MAX_VALUE）；
     * <p>
     * 三、将正数、负数的越界判断合并起来：
     * 为了保证代码简洁高效，这里我们不得不寻求一种方法，使正数、负数的越界判断可以合并起来进行（同样，这里我们也利用了数值化的正负
     * 标记位 isNegtive）：
     * 我们设置一个变量 overValue 来表示当前的值和 Integer.MAX_VALUE/10 的差，因为 Integer.MAX_VALUE/10 为正数，所以当当前值为
     * 负数时，需要统一转化为正数，故而有：
     * overValue = isNegtive*value - Integer.MAX_VALUE/10;
     * 这样，当 overValue > 0 时，越界，overValue < 0 时，不越界，而当 overValue == 0 时：
     * 正数时（isNegtive == 1），digit > 7 越界，负数时（isNegtive == -1），digit > 8 越界，通过 (isNegtive+1)/2 来将 -1、1
     * 转换为0、1，从而使有关 digit 的判断统一转化为：当 (isNegtive+1)/2 + digit > 8 时，数值越界；
     * 综上，令：
     * overValue = isNegtive*value - Integer.MAX_VALUE/10 + (((isNegtive+1)/2 + digit > 8) ? 1:0);
     * 则当 overValue > 0 时，数值将会越界，反之，则不会。
     */
    public static int StrToInt1(String str) {
        if (str == null) {
            return 0;
        }

        int length = str.length();
        int isNegtive = 1, overValue = 0, digit = 0, value = 0;
        if (length == 0) {
            return 0;
        } else {
            int index = 0;
            if (str.charAt(0) == '-') {
                isNegtive = -1;
                index = 1;
            } else if (str.charAt(0) == '+') {
                index = 1;
            }

            while (index < length) {
                digit = str.charAt(index) - '0';
                // overValue表示本轮循环是否会越界，提前判断。
                overValue = isNegtive * value - Integer.MAX_VALUE / 10
                        + (((isNegtive + 1) / 2 + digit > 8) ? 1 : 0);

                if (digit < 0 || digit > 9) {
                    return 0;
                } else if (overValue > 0) {
                    return 0;
                }
                value = value * 10 + isNegtive * digit;
                index++;
            }
            return value;
        }
    }

    /**
     * 思路：
     * 利用正则表达式，效率较低。
     */
    public int StrToInt2(String str) {
        // \d代表[0-9] 但是要写成\\d才行。
        if (!str.matches("[+,-]?\\d+")) return 0;
        int len = str.length();
        int i = len - 1;
        long res = 0;  //long类型，避免溢出。不能用int

        while (i >= 0 && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            res += Math.pow(10, len - 1 - i) * (str.charAt(i) - '0');
            i--;
        }
        res = (str.charAt(0) == '-' ? -res : res);
        //溢出就返回0，用long类型的res来比较，
        //如果定义为int res,那再比较就没有意义了，int范围为[-2147483648,2147483647]
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) return 0;
        return (int) res;
    }

    /**
     * 剑指Offer版解法，设置全局变量标志字符串是否合法。
     */
    static boolean isInvalidNumber = false;

    public static int StrToInt3(String str) {
        if (str == null || str.length() == 0) {
            isInvalidNumber = true;
            return 0;
        }
        long result = 0;
        boolean isNegative = false;
        int index = 0;
        if (str.charAt(index) == '+') {
            index++;
        } else if (str.charAt(index) == '-') {
            isNegative = true;
            index++;
        } else if (!Character.isDigit(str.charAt(index))) {
            System.out.println("1");
            isInvalidNumber = true;
            return 0;
        }
        result = StrToIntCore(str, index, isNegative);
        return (int) result;
    }

    public static long StrToIntCore(String str, int index, boolean isNegative) {
        long result = 0;
        while (index < str.length()) {
            char current = str.charAt(index);
            if (Character.isDigit(current)) {
                int digit = current - '0';
                result = result * 10 + digit;
            } else {
                isInvalidNumber = true;
                return 0;
            }
            index++;
        }
        result = isNegative ? -result : result;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            ;
            return 0;
        }
        return result;
    }

    public static int StrToInt4(String str) {
        if (str == null || str.length() == 0) {
            isInvalidNumber = true;
            return 0;
        }
        int result = 0;
        int isNegative = 1;
        int index = 0;
        if (str.charAt(index) == '+') {
            index++;
        } else if (str.charAt(index) == '-') {
            isNegative = -1;
            index++;
        } else if (!Character.isDigit(str.charAt(index))) {
            System.out.println("1");
            isInvalidNumber = true;
            return 0;
        }
        result = StrToIntCore1(str, index, isNegative);
        return result;
    }

    public static int StrToIntCore1(String str, int index, int isNegative) {
        int result = 0;
        while (index < str.length()) {
            char current = str.charAt(index);
            if (Character.isDigit(current)) {
                int digit = current - '0';
                if (isNegative == 1 && (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7))) {
                    return 0;
                } else if (isNegative == -1 && (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 8))) {
                    return 0;
                }
                result = result * 10 + digit;
            } else {
                isInvalidNumber = true;
                return 0;
            }
            index++;
        }
        return isNegative * result;
    }

    public static int StrToIntCore2(String str, int index, int isNegative) {
        int result = 0;
        int overNumber = 0;
        while (index < str.length()) {
            char current = str.charAt(index);
            if (Character.isDigit(current)) {
                int digit = current - '0';
                overNumber = isNegative * result - Integer.MAX_VALUE / 10 + (isNegative + 1) / 2 + digit > 8 ? 1 : 0;
                if (overNumber > 0) {
                    return 0;
                }
                result = result * 10 + isNegative * digit;
            } else {
                isInvalidNumber = true;
                return 0;
            }
            index++;
        }
        return result;
    }
}
