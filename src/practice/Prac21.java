package practice;

import java.util.Stack;

/**
 * 栈的压入、弹出序列
 * <p>
 * 题目描述
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列
 * 1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 * （注意：这两个序列的长度是相等的）
 */
public class Prac21 {
    /**
     * 注意到压入数组和弹出数组的长度保持相等，避免了数组越界的情况。
     */
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        for (int a : pushA) {
            stack.push(a);

            while (!stack.isEmpty() && stack.peek() == popA[count]) {
                stack.pop();
                count++;
            }
        }
        return stack.isEmpty();
    }
}
