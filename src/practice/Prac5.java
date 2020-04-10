package practice;

import java.util.Stack;

/**
 * 用两个栈实现队列
 * <p>
 * 题目描述
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 */
public class Prac5 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        while (stack1.size() > 0) {
//        while(!stack1.isEmpty(){
            stack2.push(stack1.pop());
        }
        int first = stack2.pop();
        while (stack2.size() > 0) {
//        while(!stack12.isEmpty(){
            stack1.push(stack2.pop());
        }
        return first;
    }

/**注意会报错：java.util.ConcurrentModificationException，因为遍历的时候一直在出栈
 * 参考：https://blog.csdn.net/m0_37204491/article/details/70208831
 */
//    Stack<Integer> stack1 = new Stack<Integer>();
//    Stack<Integer> stack2 = new Stack<Integer>();
//
//    public void push(int node) {
//        stack1.push(node);
//    }
//
//    public int pop() {
//        for(Integer a : stack1){
//            stack1.pop();
//            stack2.push(a);
//        }
//        int first = stack2.pop();
//        for(Integer a : stack2){
//            stack2.pop();
//            stack1.push(a);
//        }
//        return first;
//    }
}
