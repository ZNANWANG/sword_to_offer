package practice;

/**
 * 求1+2+3+...+n
 * <p>
 * 题目描述
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class Prac47 {
    /**
     * 不满足条件，纯属自娱自乐。
     */
    public int Sum_Solution(int n) {
        return n * (n + 1) / 2;
    }

    /**
     * 取巧之法
     */
    public int Sum_Solution1(int n) {
        int res = (int) Math.pow(n, 2) + n;
        return res >> 1;
    }

    /**
     * 思路：
     * 短路求值（short-circuit evaluation）作为递归结束条件。
     * 作为"&&"和"||"操作符的操作数表达式，这些表达式在进行求值时，只要最终的结果已经可以确定是真或假，求值过程便告终止，这称之为短
     * 路求值（short-circuit evaluation）。
     * 例子
     * 假如expr1和expr2都是表达式，并且expr1的值为0，在下面这个逻辑表达式的求值过程中：
     * expr1 && expr2
     * expr2将不会进行求值，因为整个逻辑表达式的值已经可以确定为0。
     * 类似地，如果expr1的值不是0，那么在下面的这个逻辑表达式的求值过程中：
     * expr1 || expr2
     * expr2将不会进行求值，因为整个逻辑表达式的值已经确定为1。
     */
    public int Sum_Solution2(int n) {
        int sum = n;
        //n=0时直接返回0，n=1时返回1。
        boolean flag = (sum > 0) && ((sum += Sum_Solution(n - 1)) > 0);
        return sum;
    }
}
