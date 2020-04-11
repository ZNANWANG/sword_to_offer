package practice;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 从头到尾打印链表
 *
 * 题目描述
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 */
public class Prac3 {

    /**
     * 思路
     * 递归
     */
    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        ArrayList<Integer> arrayList=new ArrayList<Integer>();
        if(listNode!=null){
            this.printListFromTailToHead1(listNode.next);
            arrayList.add(listNode.val);
        }
        return arrayList;
    }

    /**
     * 思路
     * 利用stack先入后出的性质
     */
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }

        ArrayList<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;
    }
}

class ListNode{
    int val;
    ListNode next = null;
    ListNode(int val){
        this.val = val;
    }
}
