package test;

import java.util.Arrays;

/**
 * 表示数值的字符串
 * <p>
 * 题目描述
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。 但
 * 是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 */
public class Test6 {

    public static void main(String[] args){
        String str = "12345e";
//        String[] strs = str.split("e");
//        System.out.println(Arrays.toString(strs));
//        System.out.println(str.contains("E"));
//        double f = 4.;
//        f = .123;
        str = "+500";
        str = "123.45e+6";
        System.out.println(isNumeric(str.toCharArray()));
    }

    private static int index = 0;
    public static boolean isNumeric(char[] str){
        if(str.length < 1 || str == null){
            return false;
        }
        index = 0; // 全局变量，多测试用例的情况需要在测试每个用例时将index清零。
        /**
         * 1.e或者E前面必须是有符号整数
         * 2..前面可以没有整数
         */
        boolean flag = isSignedNumber(str);
        if(index < str.length && str[index] == '.'){
            index++;
            /**
             * .前面可以没有整数
             * 注意有个大坑，不能写作flag = flag || isUnsignedNumber(str); 因为短路求值原理，isUnsignedNumber(str)不会被调用。
             */
            flag = isUnsignedNumber(str) || flag;
        }
        if(index < str.length && (str[index] == 'e' || str[index] == 'E')){
            index++;
            /**
             * e或者E前后都必须有有符号整数
             */
            flag = flag && isSignedNumber(str);
        }
        return flag && index == str.length;
    }

    public static boolean isSignedNumber(char[] str){
        if(index < str.length && (str[index] == '+' || str[index] == '-')){
            index++;
        }
        return isUnsignedNumber(str);
    }

    public static boolean isUnsignedNumber(char[] str){
        int start = index;
        while (index < str.length && str[index] >= '0' && str[index] <= '9'){
            index++;
        }
        return start < index;
    }
}
