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
    public ListNode deleteDuplication(ListNode pHead) {
        ListNode copy = pHead;
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
}
