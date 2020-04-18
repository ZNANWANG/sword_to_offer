package practice;

import java.util.*;

/**
 * 二叉树的深度
 * <p>
 * 题目描述
 * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 * <p>
 * 注：这道题二叉树的深度有问题，应该减一。
 */
public class Prac38 {
    public static void main(String[] args) {
        TreeNode root = initTree();
        int depth = TreeDepth1(root);
        System.out.println("depth: " + depth);
    }

    public static TreeNode initTree() {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(1);

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;

        node3.left = node5;
        return root;
    }

    /**
     * 思路：
     * 递归求解二叉树的深度，二叉树的深度等于左半树的深度和右半树的深度两者的最大值。
     */
    public static int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = 1 + TreeDepth(root.left);
        int right = 1 + TreeDepth(root.right);
        return left > right ? left : right;
    }

    /**
     * 思路：
     * BFS求解二叉树深度
     */
    public static int TreeDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> depths = new LinkedList<>();
        queue.offer(root);
        depths.offer(1);
        int max = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int depth = depths.poll();
            if (node.left == null && node.right == null) {
                max = max > depth ? max : depth;
            }
            if (node.left != null) {
                queue.offer(node.left);
                depths.offer(depth + 1);
            }
            if (node.right != null) {
                queue.offer(node.right);
                depths.offer(depth + 1);
            }
        }
        return max;
    }

    /**
     * 思路：
     * DFS求解二叉树深度
     */
    public static int TreeDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> depths = new Stack<>();
        stack.push(root);
        depths.push(1);
        int max = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int depth = depths.pop();
            if (node.left == null && node.right == null) {
                max = max > depth ? max : depth;
            }
            if (node.left != null) {
                stack.push(node.left);
                depths.push(depth + 1);
            }
            if (node.right != null) {
                stack.push(node.right);
                depths.push(depth + 1);
            }
        }
        return max;
    }

    /**
     * 思路：
     * 本质上还是BFS，但是这种写法记录了每层总共的结点数(levelTotal)，这样就可以确定结点所在的层数，有意思但是不推荐。
     */
    public static int TreeDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelCount = 0, levelTotal = 1, depth = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            levelCount++;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (levelCount == levelTotal) {
                levelCount = 0;
                levelTotal = queue.size();
                depth++;
            }
        }
        return depth;
    }

    /**
     * 思路：
     * 尾递归
     */
    public int TreeDepth4(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getTreeDepth(root, 0);
    }

    public static int getTreeDepth(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        int left = getTreeDepth(root.left, 1 + depth);
        int right = getTreeDepth(root.right, 1 + depth);
        return left > right ? left : right;
    }
}
