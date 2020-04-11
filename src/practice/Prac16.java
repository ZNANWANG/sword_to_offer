package practice;

/**
 * 合并两个排序的链表
 * <p>
 * 题目描述
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 */
public class Prac16 {

    /**
     * 尾插法 - 改变指针（用next赋予指针，指向下一个结点）
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else {
            ListNode head = null;
            ListNode tail = null;
            while (list1 != null && list2 != null) {
                if (list1.val >= list2.val) {
                    if (head == null) {
                        head = tail = list2;
                    } else {
                        tail.next = list2; // 赋予指针，指向下一个结点
                        tail = list2; // 移动到最新结点
                    }
                    list2 = list2.next;
                } else {
                    if (head == null) {
                        head = tail = list1;
                    } else {
                        tail.next = list1;
                        tail = list1;
                    }
                    list1 = list1.next;
                }
            }

            if (list1 == null) {
                tail.next = list2;
            } else if (list2 == null) {
                tail.next = list1;
            }
            return head;
        }
    }

    /**
     * 尾插法 - 创建新结点
     */
    public ListNode Merge1(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else {
            ListNode head = null;
            ListNode tail = null;
            while (list1 != null && list2 != null) {
                if (list1.val >= list2.val) {
                    ListNode newNode = new ListNode(list2.val); //创造新结点
                    if (head == null) {
                        head = tail = newNode;
                    } else {
                        tail.next = newNode;
                        tail = newNode;
                    }
                    list2 = list2.next;
                } else {
                    ListNode newNode = new ListNode(list1.val); //创造新结点
                    if (head == null) {
                        head = tail = newNode;
                    } else {
                        tail.next = newNode;
                        tail = newNode;
                    }
                    list1 = list1.next;
                }
            }

            if (list1 == null) {
                tail.next = list2;
            } else if (list2 == null) {
                tail.next = list1;
            }
            return head;
        }
    }

    /**
     * 递归求解
     */
    public ListNode Merge2(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else {
            if (list1.val >= list2.val) {
                list2.next = Merge(list1, list2.next);
                return list2;
            } else {
                list1.next = Merge(list1.next, list2);
                return list1;
            }
        }
    }

    /**
     * 尾插法变种
     */
    public ListNode Merge3(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else {
            ListNode head = null;
            ListNode tail = null;
            while (list1 != null || list2 != null) {
                if (list1 == null) {
                    tail.next = list2;
                    return head;
                } else if (list2 == null) {
                    tail.next = list1;
                    return head;
                } else if (list1.val >= list2.val) {
                    ListNode newNode = new ListNode(list2.val);
                    if (head == null) {
                        head = tail = newNode;
                    } else {
                        tail.next = newNode;
                        tail = newNode;
                    }
                    list2 = list2.next;
                } else {
                    ListNode newNode = new ListNode(list1.val);
                    if (head == null) {
                        head = tail = newNode;
                    } else {
                        tail.next = newNode;
                        tail = newNode;
                    }
                    list1 = list1.next;
                }
            }
            return head;
        }
    }
}
