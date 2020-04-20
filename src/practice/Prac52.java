package practice;

import java.util.*;

/**
 * 正则表达式匹配
 * <p>
 * 题目描述
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 */
public class Prac52 {
    public static void main(String[] args) {
        char[] str = {};
        char[] pattern = {'1', '2'};
        System.out.println(match(str, pattern));
    }

    public boolean match1(char[] str, char[] pattern) {
        int[][] dp = new int[str.length + 1][pattern.length + 1];
        for (int i = 0; i <= str.length; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= pattern.length; i++) {
            if (pattern[i] == '*') {
                dp[0][i] = dp[0][i - 1];
            }
        }
        for (int i = 1; i <= str.length; i++) {
            for (int j = 1; j <= pattern.length; j++) {

            }
        }
        return false;
    }

    public static boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            System.out.println("1");
            return false;
        }
        return matchCore(str, pattern, 0, 0);
    }

    /**
     * 思路：
     * 首先，考虑特殊情况：
     * 1> 两个字符串都为空，返回true
     * 2> 当第一个字符串不空，而第二个字符串空了，返回false（因为这样，就无法匹配成功了,而如果第一个字符串空了，第二个字符串非空，
     * 还是可能匹配成功的，比如第二个字符串是“a*a*a*a*”,由于‘*’之前的元素可以出现0次，所以有可能匹配成功）之后就开始匹配第一个字符，
     * 这里有两种可能：匹配成功或匹配失败。但考虑到pattern下一个字符可能是‘*’， 这里我们分两种情况讨论：pattern下一个字符为‘*’或不
     * 为‘*’：
     * 1> pattern下一个字符不为‘*’：这种情况比较简单，直接匹配当前字符。如果匹配成功，继续匹配下一个；如果匹配失败，直接返回false。
     * 注意这里的“匹配成功”，除了两个字符相同的情况外，还有一种情况，就是pattern的当前字符为‘.’,同时str的当前字符不为‘\0’。
     * 2> pattern下一个字符为‘*’时，稍微复杂一些，因为‘*’可以代表0个或多个。这里把这些情况都考虑到：
     * a> 当‘*’匹配0个字符时，str当前字符不变，pattern当前字符后移两位，跳过这个‘*’符号；
     * b> 当‘*’匹配1个或多个时，str当前字符移向下一个，pattern当前字符不变。（这里匹配1个或多个可以看成一种情况，因为当匹配一个时，
     * 由于str移到了下一个字符，而pattern字符不变，就回到了上边的情况a；当匹配多于一个字符时，相当于从str的下一个字符继续开始匹配）
     * 之后再写代码就很简单了。
     * <p>
     * 注意测试str的边界条件：strIndex != str.length
     */
    public static boolean matchCore(char[] str, char[] pattern, int strIndex, int patternIndex) {
        if (strIndex == str.length && patternIndex == pattern.length) { //str和pattern同时匹配完则返回true
            return true;
        } else if (strIndex != str.length && patternIndex == pattern.length) { //str匹配完之前假如pattern提前消耗完则返回false
            return false;
        }
        if (patternIndex + 1 < pattern.length) {
            if (pattern[patternIndex + 1] == '*') {
                // 加入边界判断条件，防止例如str=[]，pattern=['1','*']的情况下报错。
                if ((strIndex != str.length && str[strIndex] == pattern[patternIndex]) || (strIndex != str.length && pattern[patternIndex] == '.')) {
                    return matchCore(str, pattern, strIndex + 1, patternIndex)
                            || matchCore(str, pattern, strIndex, patternIndex + 2);
                } else {
                    // 例如str=[]，pattern=['1','*']的情况会返回false。
                    return matchCore(str, pattern, strIndex, patternIndex + 2);
                }
            } else {
                // 加入边界判断条件，防止例如str=[]，pattern=['1','2']的情况下报错。
                if (strIndex != str.length && str[strIndex] == pattern[patternIndex] || pattern[patternIndex] == '.') {
                    return matchCore(str, pattern, strIndex + 1, patternIndex + 1);
                } else {
                    return false;
                }
            }
        } else {
            // 加入边界判断条件，防止例如str=[]，pattern=['1']的情况下报错。
            // 注意由于一开始会判断pattern不会在str匹配为空之前消耗完，所以不必假如边界判断条件：patternIndex != pattern.length。
            if (strIndex != str.length && str[strIndex] == pattern[patternIndex] || pattern[patternIndex] == '.') {
                return matchCore(str, pattern, strIndex + 1, patternIndex + 1);
            } else {
                return false;
            }
        }
    }

    public boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex) {
        //有效性检验：str到尾，pattern到尾，匹配成功
        if (strIndex == str.length && patternIndex == pattern.length) {
            return true;
        }
        //pattern先到尾，匹配失败
        if (strIndex != str.length && patternIndex == pattern.length) {
            return false;
        }
        //模式第2个是*，且字符串第1个跟模式第1个匹配,分3种匹配模式；如不匹配，模式后移2位
        if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
            if ((strIndex != str.length && pattern[patternIndex] == str[strIndex]) || (pattern[patternIndex] == '.' && strIndex != str.length)) {
                return matchCore(str, strIndex, pattern, patternIndex + 2)//模式后移2，视为x*匹配0个字符
                        || matchCore(str, strIndex + 1, pattern, patternIndex + 2)//视为模式匹配1个字符
                        || matchCore(str, strIndex + 1, pattern, patternIndex);//*匹配1个，再匹配str中的下一个
            } else {
                return matchCore(str, strIndex, pattern, patternIndex + 2);
            }
        }
        //模式第2个不是*，且字符串第1个跟模式第1个匹配，则都后移1位，否则直接返回false
        if ((strIndex != str.length && pattern[patternIndex] == str[strIndex]) || (pattern[patternIndex] == '.' && strIndex != str.length)) {
            return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
        }
        return false;
    }

    /**
     * 思路：
     * 动态规划
     * 如果 pattern[j] == str[i] || pattern[j] == '.', 此时dp[i][j] = dp[i-1][j-1];
     * 如果 pattern[j] == '*'
     * 分两种情况:
     * 1、如果pattern[j-1] != str[i] && pattern[j-1] != '.', 此时dp[i][j] = dp[i][j-2] //a*匹配0次
     * 2、如果pattern[j-1] == str[i] || pattern[j-1] == '.'
     * 此时dp[i][j] = dp[i][j-2] // a*匹配0次
     * 或者 dp[i][j] = dp[i][j-1] // a*匹配1次
     * 或者 dp[i][j] = dp[i-1][j] // a*匹配多次
     */
    public static boolean match2(char[] str, char[] pattern) {
        boolean[][] dp = new boolean[str.length + 1][pattern.length + 1];
        dp[0][0] = true;
        for (int i = 1; i < dp[0].length; i++) {
            if (pattern[i - 1] == '*')
                dp[0][i] = dp[0][i - 2];
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (pattern[j - 1] == '.' || pattern[j - 1] == str[i - 1])
                    dp[i][j] = dp[i - 1][j - 1];
                else if (pattern[j - 1] == '*') {
                    if (pattern[j - 2] != str[i - 1] && pattern[j - 2] != '.')
                        dp[i][j] = dp[i][j - 2]; // 不匹配，跳过
                    else
                        dp[i][j] = dp[i][j - 1] || dp[i][j - 2] || dp[i - 1][j];
//                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j]; // 匹配一次和匹配多次可以合并起来
                }
            }
        }
        display(dp);
        return dp[str.length][pattern.length];
    }

    public static void display(boolean[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
    }

    /**
     * 练习
     */
    public boolean matchTest(char[] str, char[] pattern) {
        if (str == null && pattern == null) {
            return false;
        }
        boolean[][] dp = new boolean[str.length + 1][pattern.length + 1];
        dp[0][0] = true;
        for (int i = 1; i <= pattern.length; i++) {
            if (pattern[i - 1] == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (str[i - 1] == pattern[j - 1] || pattern[j - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pattern[j - 1] == '*') {
                    if (pattern[j - 2] == str[i - 1] || pattern[j - 2] == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }
        return dp[str.length][pattern.length];
    }
}
