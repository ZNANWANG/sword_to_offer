package practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 两个链表的第一个公共结点
 * <p>
 * 题目描述
 * 输入两个链表，找出它们的第一个公共结点。（注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
 */
public class Prac36 {
    /**
     * 思路：
     * 蛮力遍历，时间复杂度为O(n^2)。
     */
    public ListNode FindFirstCommonNode1(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }

        while (pHead2 != null) {
            ListNode head1 = pHead1;
            while (head1 != null) {
                if (pHead2 == head1) {
                    return head1;
                } else {
                    head1 = head1.next;
                }
            }
            pHead2 = pHead2.next;
        }
        return null;
    }

    /**
     * 思路：
     * 首先找公共结点的特征，由于两个链表都是单向链表，所以假如两个链表有公共结点，那么，两个链表在公共结点之后呈现一种重合的状态，
     * 类似字母'Y'而非'X'的形态，那么任务就转化为从后往前查找最后一个相同的结点，后面的结点要先比对，那么就得利用Stack的后入先出的
     * 特性，用两个辅助栈记录两条链表各自所有的节点，最后每轮弹出栈顶元素进行比对，最终输出目标结点。
     * 注意用Queue是不行的，因为两个链表可能是不等长的，不能一对一对比较。
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        while (pHead1 != null) {
            stack1.push(pHead1);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null) {
            stack2.push(pHead2);
            pHead2 = pHead2.next;
        }
        ListNode target = null;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            ListNode node1 = stack1.pop();
            ListNode node2 = stack2.pop();
            if (node1 == node2) {
                target = node1;
            } else {
                break;
            }
        }
        return target == null ? null : target;
    }
}
