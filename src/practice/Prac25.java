package practice;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 复杂链表的复制
 *
 * 题目描述
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表
 * 的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */
public class Prac25 {
    public static void main(String[] args){
        RandomListNode pHead = new RandomListNode(1);
        RandomListNode node1 = new RandomListNode(2);
        RandomListNode node2 = new RandomListNode(3);
        RandomListNode node3 = new RandomListNode(4);
        RandomListNode node4 = new RandomListNode(5);

        pHead.next = node1;
        pHead.random = node2;

        node1.next = node2;
        node1.random = node4;

        node2.next = node3;

        node3.next = node4;
        node3.random = node1;

        RandomListNode head = Clone3(pHead);
        while(head != null){
            System.out.printf("node:%-5d", head.label);
            if(head.next != null) {
                System.out.printf(" next:%-5d", head.next.label);
            } else {
                System.out.printf(" next:%-5s", null);
            }
            if(head.random != null){
                System.out.printf(" random:%-5d", head.random.label);
            } else {
                System.out.printf(" random:%-5s", null);
            }
            System.out.println();
            head = head.next;
        }
    }

    /**
     * 时间复杂度O(n^2)
     * 思路：先尾插法复制只含next指针的链表，期间记录random指针的位置，再根据位置列表遍历链表添加指针。
     */
    public static RandomListNode Clone(RandomListNode pHead) {
        if(pHead != null){
            ArrayList<Integer> list = new ArrayList<>();
            RandomListNode head = null;
            RandomListNode tail = null;
            RandomListNode newNode = null;
            RandomListNode next = null;
            RandomListNode current = pHead;
            while(pHead!=null){
                newNode = new RandomListNode(pHead.label);
                if(head == null){
                    head = tail = newNode;
                } else {
                    tail.next = newNode;
                    tail = newNode;
                }

                if(pHead.random != null) {
                    int count = 0;
                    next = current.next;
                    while (next != null) {
                        count++;
                        if (next == pHead.random) {
                            break;
                        }
                        next = next.next;
                    }
                    list.add(count);
                } else {
                    list.add(-1);
                }
                //System.out.println(list);

                pHead = pHead.next;
            }

            current = head;
            for(Integer a : list){
                next = head;
                if(a != -1){
                    for (int i = 0; i < a; i++) {
                        next = next.next;
                    }
                    current.random = next;
                }
                current = current.next;
            }
            return head;
        }
        return null;
    }

    /**
     * 时间复杂度O(n^2)
     * 思路：先尾插法复制只含next指针的链表，再并行查找并添加random指针。
     */
    public static RandomListNode Clone1(RandomListNode pHead) {
        if(pHead != null){
            ArrayList<Integer> list = new ArrayList<>();
            RandomListNode head = null;
            RandomListNode tail = null;
            RandomListNode newNode = null;
            RandomListNode current1 = pHead;
            RandomListNode copy1 = pHead;
            RandomListNode next1 = pHead;

            while(pHead!=null) {
                newNode = new RandomListNode(pHead.label);
                if (head == null) {
                    head = tail = newNode;
                } else {
                    tail.next = newNode;
                    tail = newNode;
                }
                pHead = pHead.next;
            }

            RandomListNode current2 = head;
            RandomListNode copy2 = head; //其实不用备份，为了看起来美观。
            RandomListNode next2 = head;
            while(current2!=null){
                next1 = copy1;
                next2 = copy2;
                if(current1.random != null) {
                    while (next1 != current1.random){
                        next1 = next1.next;
                        next2 = next2.next;
                    }
                    current2.random = next2;
                }
                current1 = current1.next;
                current2 = current2.next;
            }

            return head;
        }
        return null;
    }

    /**
     * 时间复杂度O(n)
     */
    public static RandomListNode Clone2(RandomListNode pHead) {
        if(pHead != null) {
            CloneNodes(pHead);
            ConnectSiblingNodes(pHead);
            return ReconnectNodes(pHead);
        }
        return null;
    }

    public static void CloneNodes(RandomListNode pHead){
        RandomListNode head = pHead;
        RandomListNode next = null;
        while(head != null){
            next = head.next;
            RandomListNode cloned = new RandomListNode(head.label);
            head.next = cloned;
            cloned.next = next;
            head = cloned.next;
        }
    }

    public static void ConnectSiblingNodes(RandomListNode pHead){
        RandomListNode head = pHead;
        RandomListNode cloned = null;
        while(head!=null){
            cloned = head.next;
            if(head.random != null) {
                cloned.random = head.random.next;
            }
            head = cloned.next;
        }
    }

    public static RandomListNode ReconnectNodes(RandomListNode pHead){
        RandomListNode head = pHead;
        RandomListNode clonedHead = head.next;
        RandomListNode clonedTail = null;
        RandomListNode next = null;
        while(head != null){
            clonedTail = head.next;
            next = clonedTail.next;
            head.next = next;
            clonedTail.next = next == null ? null : next.next;
            head = head.next;
        }
        return clonedHead;
    }

    public static RandomListNode ReconnectNodes1(RandomListNode pHead){
        RandomListNode head = pHead;
        RandomListNode tail = head.next;
        RandomListNode cloned = head.next;
        RandomListNode next = tail.next;
        while(next != null){
            // 假如不加下面这两句的话，大链表其实没有分开成为两个相同的子链表(而且改变了原来的链表，pHead指针指向改变），所以不能AC。
            head.next = next;
            head = next;

            tail.next = next.next;
            tail = next.next;
            next = tail.next;
        }
        return cloned;
    }

    public static RandomListNode Clone3(RandomListNode pHead) {
        if (pHead == null) return null;

        // target 作为将要返回的头，记住要new的
        RandomListNode target = new RandomListNode(pHead.label);
        // preHead 复制链表头
        RandomListNode preHead = pHead;
        // newHead 复制新链表头
        RandomListNode newHead = target;

        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();

        // 由pHead将所有值存入map，每一个结点都要new的
        while (pHead != null) {
            map.put(pHead, new RandomListNode(pHead.label));
            pHead = pHead.next;
        }

        // target作为新链表的头，由newHaed, preHead移动来复制链表
        while (preHead != null) {
            newHead.next = map.get( preHead.next );
            newHead.random = map.get( preHead.random );

            preHead = preHead.next;
            newHead = newHead.next;
        }

        return target;
    }
}

class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
