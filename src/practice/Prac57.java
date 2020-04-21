package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的下一结点
 * <p>
 * 题目描述
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的
 * 指针。
 */
public class Prac57 {
    public static void main(String[] args) {
        TreeNode root = initTree();
        List<TreeNode> list = inorderTraverse(root);
        System.out.print("Inorder:");
        display(list);
        list = postorderTraverse(root);
        System.out.print("Postorder:");
        display(list);
        list = inorderTraverse1(root);
        System.out.print("Inorder:");
        display(list);
    }

    public static void display(List<TreeNode> list) {
        for (TreeNode node : list) {
            System.out.print(node.val);
        }
        System.out.println();
    }

    public static TreeNode initTree() {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;

        node3.left = node5;
        return root;
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) {
            return null;
        }

        // 查找二叉树的根结点
        TreeLinkNode head = pNode;
        while (head != null) {
            if (head.next != null) {
                head = head.next;
            } else {
                break;
            }
        }

        // 中序遍历
        Stack<TreeLinkNode> stack = new Stack<>();
        List<TreeLinkNode> inorder = new ArrayList<>();
        TreeLinkNode target = head;
        while (target != null || !stack.isEmpty()) {
            if (target != null) {
                stack.push(target);
                target = target.left;
            } else {
                TreeLinkNode node = stack.pop();
                target = node.right;
                inorder.add(node);
            }
        }

        for (int i = 0; i < inorder.size(); i++) {
            if (inorder.get(i) == pNode) {
                if (i + 1 < inorder.size()) {
                    return inorder.get(i + 1);
                } else {
                    return null;
                }
            }
        }

        return null;
    }

    /**
     * 剑指Offer版解法（见图prac57_binarytree)
     * 分析：
     * 以该二叉树为例，中序遍历为：{D,B,H,E,I,A,F,C,G}
     * 仔细观察，可以把中序下一结点归为几种类型：
     * 1、有右子树，下一结点是右子树中的最左结点，例如 B，下一结点是 H。
     * 2、无右子树，且结点是该结点父结点的左子树，则下一结点是该结点的父结点，例如 H，下一结点是 E。
     * 3、无右子树，且结点是该结点父结点的右子树，则我们一直沿着父结点追朔，直到找到某个结点是其父结点的左子树，如果存在这样的结点，
     * 那么这个结点的父结点就是我们要找的下一结点。例如 I，下一结点是 A；例如 G，并没有符合情况的结点，所以 G 没有下一结点。
     */
    public TreeLinkNode GetNext1(TreeLinkNode node) {
        if (node == null) return null;
        // 如果有右子树，找右子树最左结点。
        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;

            }
            return node;
        }
        // 如果没有右子树，一直向上找，直到找到一个是它父结点的左子节点
        while (node.next != null) {
            if (node.next.left == node) return node.next;
            node = node.next;
        }
        return null;
    }

    /**
     * 中序遍历
     */
    public static List<TreeNode> inorderTraverse(TreeNode root) {
        List<TreeNode> inorder = new ArrayList<>();
        if (root == null) {
            return inorder;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> outStack = new Stack<>();
        TreeNode target = root;
        while (target != null || !stack.isEmpty()) {
            if (target != null) {
                stack.push(target);
                target = target.right;
            } else {
                TreeNode node = stack.pop();
                outStack.push(node);
                target = node.left;
            }
        }

        while (!outStack.isEmpty()) {
            inorder.add(outStack.pop());
        }
        return inorder;
    }

    /**
     * 后序遍历
     */
    public static List<TreeNode> postorderTraverse(TreeNode root) {
        List<TreeNode> postorder = new ArrayList<>();
        if (root == null) {
            return postorder;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> inStack = new Stack<>();
        TreeNode target = root;
        while (target != null || !stack.isEmpty()) {
            if (target != null) {
                stack.push(target);
                inStack.push(target);
                target = target.right;
            } else {
                TreeNode node = stack.pop();
                target = node.left;
            }
        }

        while (!inStack.isEmpty()) {
            postorder.add(inStack.pop());
        }
        return postorder;
    }

    /**
     * 中序遍历
     */
    public static List<TreeNode> inorderTraverse1(TreeNode root) {
        List<TreeNode> inorder = new ArrayList<>();
        if (root == null) {
            return inorder;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode target = root;
        while (target != null || !stack.isEmpty()) {
            if (target != null) {
                stack.push(target);
                target = target.left;
            } else {
                TreeNode node = stack.pop();
                target = node.right;
                inorder.add(node);
            }
        }
        return inorder;
    }
}

class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
