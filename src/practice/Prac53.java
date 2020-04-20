package practice;

/**
 * 表示数值的字符串
 * <p>
 * 题目描述
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但
 * 是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 */
public class Prac53 {
    public static void main(String[] args){
        String str = "1.79769313486232E+308";
        str = "12e";
//        str = "123.45e+6";
        System.out.println(isNumeric(str.toCharArray()));
    }

    /**
     * 思路：
     * 1、如果字符串包含e或者E，并且e或者E不在首位或者末位，则分割字符串
     * a、假如分割后的字符串数组长度为1，检查是否是小数。
     * b、假如分割后的字符串数组长度为2，检查前半部分是否是小数，后半部分是否是整数。
     * c、假如分割后的字符串数组长度大于2，直接返回false。
     * 2.如果字符串不包含e和E，检查字符串是否为小数。
     */
    public static boolean isNumeric(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }
        String s = String.valueOf(str);
        if(s.contains("E") && s.indexOf("E") != 0 && s.indexOf("E") != s.length() - 1) {
            boolean bool1 = false;
            String[] strs1 = s.split("E");
//            System.out.println("E: " + Arrays.toString(strs1));
            if (strs1.length == 1) {
                bool1 = isSignedDecimal(strs1[0].toCharArray());
            } else if (strs1.length == 2) {
                bool1 = isSignedDecimal(strs1[0].toCharArray()) && isSignedNumber(strs1[1].toCharArray());
            } else {
                bool1 = false;
            }
            return bool1;
        } else if(s.contains("e") && s.indexOf("e") != 0 && s.indexOf("e") != s.length() - 1) {
            boolean bool2 = false;
            String[] strs2 = s.split("e");
//            System.out.println("e: " + Arrays.toString(strs2));
            if (strs2.length == 1) {
                bool2 = isSignedDecimal(strs2[0].toCharArray());
            } else if (strs2.length == 2) {
                bool2 = isSignedDecimal(strs2[0].toCharArray()) && isSignedNumber(strs2[1].toCharArray());
            } else {
                bool2 = false;
            }
            return bool2;
        } else {
//            System.out.println("decimal check");
            return isSignedDecimal(str);
        }
    }

    public static boolean isSignedDecimal(char[] str){
        String s = String.valueOf(str);
        String[] strs = s.split("\\.");
        if(strs.length == 1){
            return isSignedNumber(strs[0].toCharArray());
        } else if(strs.length == 2){
            return isSignedNumber(strs[0].toCharArray()) && isUnsignedNumber(strs[1].toCharArray());
        } else {
            return false;
        }
    }

    public static boolean isUnsignedNumber(char[] str){
        int index = 0;
        char first = str[index];
        int isNegative = 1;
        boolean result = false;
        if (!Character.isDigit(first)) {
            return false;
        }

        result = getResult(str, index, isNegative);
        return result;
    }

    public static boolean isSignedNumber(char[] str){
        int index = 0;
        char first = str[index];
        int isNegative = 1;
        boolean result = false;
        if (first == '+') {
            index++;
        } else if (first == '-') {
            index++;
            isNegative = -1;
        } else if (!Character.isDigit(first)) {
            return false;
        }

        result = getResult(str, index, isNegative);
        return result;
    }

    public static boolean getResult(char[] str, int index, int isNegtive){
        long result = 0;
        while(index < str.length){
            char temp = str[index];
            if(Character.isDigit(temp)) {
                int digit = temp - '0';
                result = result * 10 + isNegtive * digit;
            } else {
                return false;
            }
            index++;
        }
        return true;
    }

    /**
     * 思路：
     * 正则表达式匹配
     */
    public boolean isNumeric1(char[] str) {
        String s=String.valueOf(str);
        return s.matches("[+-]?[0-9]*(\\.[0-9]*)?([eE][+-]?[0-9]+)?");
    }

    private int index = 0;

    /**
     * 推荐剑指Offer版写法
     */
    public boolean isNumeric2(char[] str) {
        if (str.length < 1 || str == null)
            return false;

        boolean flag = scanInteger(str);

        if (index < str.length && str[index] == '.') {
            index++;
            /**
             * 下面一行代码用||的原因：
             * 1、小数可以没有整数部分，如.123等于0.123
             * 2、小数点后面可以没有数字，如233.等于0.233
             * 3、当然，小数点前面和后面都可以有数字，如233.666
             */
            flag = scanUnsignedInteger(str) || flag;
        }

        if (index < str.length && (str[index] == 'E' || str[index] == 'e')) {
            index++;
            /**
             * 下面一行代码用&&的原因：
             * 1、当e或者E前面没有整数时，整个字符串不能表示数字，如.e23、e23
             * 2、当e或者E后面没有整数时，整个字符串不能表示数字，如12e、12e+5.4
             */
            flag = flag && scanInteger(str);
        }

        return flag && index == str.length;

    }

    private boolean scanInteger(char[] str) {
        if (index < str.length && (str[index] == '+' || str[index] == '-') )
            index++;
        return scanUnsignedInteger(str);

    }

    private boolean scanUnsignedInteger(char[] str) {
        int start = index;
        while (index < str.length && str[index] >= '0' && str[index] <= '9')
            index++;
        return start < index; //是否存在整数
    }
}
