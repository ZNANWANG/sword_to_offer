package practice;

import java.util.Stack;

/**
 * 包含Min函数的栈
 *
 * 题目描述
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 * 注意：保证测试中不会当栈为空的时候，对栈调用pop()或者min()或者top()方法。
 */
public class Prac20 {

    /**
     * 时间复杂度高，不推荐。
     */
//    static ArrayList<Integer> list = new ArrayList<>();
//
//    public void push(int node) {
//        Prac20.list.add(node);
//    }
//
//    public void pop() {
//        Prac20.list.remove(Prac20.list.size() - 1);
//    }
//
//    public int top() {
//        return Prac20.list.get(0);
//    }
//
//    public int min() {
//        ArrayList<Integer> temp = new ArrayList<>(Prac20.list);
//        Collections.sort(temp);
//        return temp.get(0);
//    }

    /**
     * 时间复杂度O(1)
     * 思路：增加一个辅助栈，每次向原栈内添加元素时，比对出最小元素并添加到辅助栈里面，这样保证了辅助栈和原栈长度相等，用peek方法就
     * 可以得到最小元素。
     */
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> minStack = new Stack<Integer>();

    public void push(int node) {
        stack.push(node);
        if(minStack.empty()){
            minStack.push(node);
        }else{
            if(node <= minStack.peek()){
                minStack.push(node);
            }else{
                minStack.push(minStack.peek());
            }
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
