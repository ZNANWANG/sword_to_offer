package practice;

import java.util.*;

/**
 * 按之子形顺序打印二叉树
 * <p>
 * 题目描述
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，
 * 其他行以此类推。
 */
public class Prac59 {
    public static void main(String[] args) {
        TreeNode root = initTree();
        List<TreeNode> list = levelorderTraverse(root);
        display(list);
    }

    public static TreeNode initTree() {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;
        return root;
    }

    public static void display(List<TreeNode> list) {
        for (TreeNode node : list) {
            System.out.print(node.val + " ");
        }
        System.out.println();
    }

    /**
     * 思路：
     * 从左到右层级遍历，每层统计结点总数，奇数层用Queue，偶数层用Stack，每当每层遍历完后，将Queue或者Stack中的存储的所有结点弹出
     * 加入到每层的列表中。
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> traversal = new ArrayList<>();
        if (pRoot == null) {
            return traversal;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Stack<TreeNode> even = new Stack<>();
        Queue<TreeNode> odd = new LinkedList<>();
        queue.offer(pRoot);
        int count = queue.size();
        int flag = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (flag == 1) {
                odd.offer(node);
            } else if (flag == -1) {
                even.push(node);
            }

            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);

            count--;
            if (count == 0) {
                ArrayList<Integer> list = new ArrayList<>();
                if (flag == 1) {
                    while (!odd.isEmpty()) {
                        list.add(odd.poll().val);
                    }
                } else if (flag == -1) {
                    while (!even.isEmpty()) {
                        list.add(even.pop().val);
                    }
                }
                traversal.add(list);
                count = queue.size();
                flag *= -1;
            }
        }
        return traversal;
    }

    /**
     * Queue —— 层级遍历
     */
    public static List<TreeNode> levelorderTraverse(TreeNode root) {
        List<TreeNode> levelorder = new ArrayList<>();
        if (root == null) {
            return levelorder;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            levelorder.add(current);
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        return levelorder;
    }
}
