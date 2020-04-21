package practice;

import java.util.HashMap;
import java.util.Map;

/**
 * 删除链表中重复的结点
 * <p>
 * 题目描述
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5
 * 处理后为 1->2->5
 */
public class Prac56 {
    /**
     * 个人解法，不需要重复结点连续出现，较为普适的一种解法。
     */
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode copy = pHead;
        // 记录重复结点的值
        Map<Integer, Integer> map = new HashMap<>();
        while (copy != null) {
            if (map.containsKey(copy.val)) {
                map.put(copy.val, map.get(copy.val) + 1);
            } else {
                map.put(copy.val, 1);
            }
            copy = copy.next;
        }

        // 找到非重复结点作为开头，防止直接从phead出发导致出错，直接从pHead出发的错误情况例如{1，1，1，1，1，2} -> {1,2}。
        while (pHead != null) {
            if (map.get(pHead.val) != 1) {
                pHead = pHead.next;
            } else {
                break;
            }
        }

        // 判断pHead是否为null，如果是，直接返回null，例如{1，1，1，1，1} -> null。
        if (pHead != null) {
            ListNode pre = pHead;
            ListNode head = pHead;
            pHead = pHead.next;
            while (pHead != null) {
                if (map.get(pHead.val) == 1) {
                    pre.next = pHead;
                    pre = pHead;
                } else {
                    pre.next = pHead.next;
                }
                pHead = pHead.next;
            }
            return head;
        }
        return null;
    }

    /**
     * 思路：
     * 1、首先添加一个头节点，以方便碰到第一个，第二个节点就相同的情况
     * 2、设置 pre，last 指针， pre指针指向当前确定不重复的那个节点，而last指针相当于工作指针，一直往后面搜索。
     * <p>
     * 注：这是他人解法，添加首结点这一点很有创意，避免了直接从头结点开始寻找非重复结点的一步。但是这种解法默认重复结点连续出现，所
     * 以不推荐。
     */
    public ListNode deleteDuplication1(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode Head = new ListNode(0);
        Head.next = pHead;
        ListNode pre = Head;
        ListNode last = Head.next;
        while (last != null) {
            if (last.next != null && last.val == last.next.val) {
                // 找到最后的一个相同节点
                while (last.next != null && last.val == last.next.val) {
                    last = last.next;
                }
                pre.next = last.next;
                last = last.next;
            } else {
                pre = pre.next;
                last = last.next;
            }
        }
        return Head.next;
    }

    /**
     * 递归解法
     */
    public ListNode deleteDuplication2(ListNode pHead) {
        if (pHead == null || pHead.next == null) { // 只有0个或1个结点，则返回
            return pHead;
        }
        if (pHead.val == pHead.next.val) { // 当前结点是重复结点
            ListNode pNode = pHead.next;
            while (pNode != null && pNode.val == pHead.val) {
                // 跳过值与当前结点相同的全部结点,找到第一个与当前结点不同的结点
                pNode = pNode.next;
            }
            return deleteDuplication2(pNode); // 从第一个与当前结点不同的结点开始递归
        } else { // 当前结点不是重复结点
            pHead.next = deleteDuplication2(pHead.next); // 保留当前结点，从下一个结点开始递归
            return pHead;
        }
    }
}
