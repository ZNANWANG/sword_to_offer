package practice;

import java.util.ArrayList;
import java.util.List;

/**
 * 链表中倒数第k个结点
 * <p>
 * 题目描述
 * 输入一个链表，输出该链表中倒数第k个结点
 */
public class Prac14 {
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head != null && k != 0) {
            List<ListNode> list = new ArrayList<>();
            list.add(head);
            while (head.next != null) {
                list.add(head.next);
                head = head.next;
            }
            if (list.size() - k >= 0) {
                return list.get(list.size() - k);
            }
        }
        return null;
    }

    /**
     * 一次遍历即可
     */
    public ListNode FindKthToTail1(ListNode head, int k) {
        ListNode pre = null, p = null;
        //两个指针都指向头结点
        p = head;
        pre = head;
        //记录k值
        int a = k;
        //记录节点的个数
        int count = 0;
        //p指针先跑，并且记录节点数，当p指针跑了k-1个节点后，pre指针开始跑，（注意pre是从head.next开始跑的）
        //当p指针跑到最后时，pre所指指针就是倒数第k个节点
        while (p != null) {
            p = p.next;
            count++;
            if (k < 1) {
                pre = pre.next;
            }
            k--;
        }
        //如果节点个数小于所求的倒数第k个节点，则返回空
        if (count < a) return null;
        return pre;
    }

    /**
     * 简化版一次遍历
     */
    public ListNode FindKthToTail2(ListNode head, int k) { //5,{1,2,3,4,5}
        ListNode p, q;
        p = q = head;
        int i = 0;
        for (; p != null; i++) {
            if (i >= k)
                q = q.next;
            p = p.next;
        }
        return i < k ? null : q;
    }
}
