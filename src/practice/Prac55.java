package practice;

import java.util.*;

/**
 * 链表中环的入口结点
 * <p>
 * 题目描述
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 */
public class Prac55 {
    /**
     * 思路：
     * 不存在环的链表中每个结点的指针应该都是唯一的，而存在环的链表中入口结点的指针会重复出现，用任何可以判断是否包含当前指针的数据
     * 结构都可以。
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        List<ListNode> list = new ArrayList<>();
        while (pHead != null) {
            if (list.contains(pHead)) {
                return pHead;
            } else {
                list.add(pHead);
            }
            pHead = pHead.next;
        }
        return null;
    }

    public ListNode EntryNodeOfLoop1(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        while (pHead != null) {
            if (set.contains(pHead)) {
                return pHead;
            } else {
                set.add(pHead);
            }
            pHead = pHead.next;
        }
        return null;
    }

    public ListNode EntryNodeOfLoop2(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        Map<ListNode, Integer> map = new HashMap<>();
        while (pHead != null) {
            if (map.containsKey(pHead)) {
                return pHead;
            } else {
                map.put(pHead, 1);
            }
            pHead = pHead.next;
        }
        return null;
    }

    /**
     * 剑指Offer版解法 —— 双指针
     * 思路：
     * 1、先判断是否有环，如果有，找出环中任意结点，如果没有，直接返回null；
     * 2、然后依靠找到的环中结点计算出环中所有的结点数n；
     * 3、最后p1先移动n步之后p2开始移动，当两者相遇时，相遇结点即为目标结点。（p1和p2所经过的结点数相同，p2会绕环一周，所以要先跳过
     * n个结点）
     */
    public ListNode EntryNodeOfLoop3(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        // 判断是否有环，如果有，找出环中任意结点，如果没有，返回null
        ListNode meetingNode = FindMeetingNode(pHead);
        if (meetingNode == null) {
            return null;
        }
        // 计算环的结点数量
        int loop = 1;
        ListNode copyOfMeetingNode = meetingNode;
        while (copyOfMeetingNode.next != meetingNode) {
            loop++;
            copyOfMeetingNode = copyOfMeetingNode.next;
        }

        ListNode p1 = pHead;
        for (int i = 0; i < loop; i++) {
            p1 = p1.next;
        }
        ListNode p2 = pHead;
        while (true) {
            if (p1 == p2) {
                return p1;
            } else {
                p1 = p1.next;
                p2 = p2.next;
            }
        }
    }

    /**
     * 双指针判断链表是否有环，快指针一次走两步，慢指针一次走一步，假如能相遇，证明有环，而且相遇处处于环内。
     */
    public ListNode FindMeetingNode(ListNode pHead) {
        if (pHead == null) {
            return null;
        }

        ListNode slow = pHead.next;
        if (slow == null) {
            return null;
        }

        ListNode fast = slow.next;
        if (fast == null) {
            return null;
        }

        while (slow != null && fast != null) {
            if (fast == slow) {
                return fast;
            }

            slow = slow.next;

            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }
        return null;
    }
}
