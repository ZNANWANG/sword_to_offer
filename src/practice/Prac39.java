package practice;

import com.sun.source.tree.Tree;

import javax.swing.tree.TreeCellRenderer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 平衡二叉树
 * <p>
 * 题目描述
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树
 */
public class Prac39 {
    public static void main(String[] args) {
        TreeNode root = initTree();
        List<TreeNode> postTraversal = postorderTraversal(root);
        for (TreeNode node : postTraversal) {
            System.out.print(node.val + " ");
        }
        System.out.println();
        List<TreeNode> inorderTraversal = inorderTraversal1(root);
        for (TreeNode node : inorderTraversal) {
            System.out.print(node.val + " ");
        }
        System.out.println();
        List<TreeNode> preorderTraversal = preorderTraversal(root);
        for (TreeNode node : preorderTraversal) {
            System.out.print(node.val + " ");
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

    /**
     * 思路：
     * 递归求解，底层结点会被多次遍历，非最优解。
     */
    public static boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean isBalanced = Math.abs(getTreeDepth(root.left, 0) - getTreeDepth(root.right, 0)) < 2;
        boolean isLeftBalanced = IsBalanced_Solution(root.left);
        boolean isRightBalanced = IsBalanced_Solution(root.right);
        return isLeftBalanced && isRightBalanced && isBalanced;
    }

    /**
     * 尾递归求二叉树的深度
     */
    public static int getTreeDepth(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        int left = getTreeDepth(root.left, 1 + depth);
        int right = getTreeDepth(root.right, 1 + depth);
        return left > right ? left : right;
    }

    /**
     * 思路：
     * 递归算法，自底向上，所有结点只需遍历一次。
     */
    public static boolean IsBalanced_Solution1(TreeNode root) {
        return getDepth(root) != -1;
    }

    public static int getDepth(TreeNode root) {
        if (root == null) return 0;
        int left = getDepth(root.left);
        if (left == -1) return -1;
        int right = getDepth(root.right);
        if (right == -1) return -1;
        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);
    }

    /**
     * 递归版后序遍历
     */
    public static List<TreeNode> postorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<TreeNode> postorderTraversal = new ArrayList<>();
        return postorderTraversal(root, postorderTraversal);
    }

    public static List<TreeNode> postorderTraversal(TreeNode root, List<TreeNode> postorderTraversal) {
        if (root.left == null && root.right == null) {
            postorderTraversal.add(root);
        } else {
            if (root.left != null) {
                postorderTraversal(root.left, postorderTraversal);
            }
            if (root.right != null) {
                postorderTraversal(root.right, postorderTraversal);
            }
            postorderTraversal.add(root);
        }
        return postorderTraversal;
    }

    /**
     * Stack —— 后序遍历（改变了原有左右结点关系）
     */
    public static List<TreeNode> postorderTraversal3(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<TreeNode> postTraversal = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node.left == null && node.right == null) {
                node = stack.pop();
                postTraversal.add(node);
            } else {
                if (node.right != null) {
                    stack.push(node.right);
                    node.right = null;
                }
                if (node.left != null) {
                    stack.push(node.left);
                    node.left = null;
                }
            }
        }
        return postTraversal;
    }

    /**
     * Stack —— 后序遍历（不改变原有左右结点关系）
     * 注意：关键在于判断当前结点是否被遍历过
     */
    public static List<TreeNode> postorderTraversal1(TreeNode root) {
        if (root == null)
            return null;
        List<TreeNode> postTraversal = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (!stack.isEmpty() && node == stack.peek()) {
                if (node.right != null) {
                    stack.push(node.right);
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                    stack.push(node.left);
                }
            } else {
                postTraversal.add(node);
            }
        }
        return postTraversal;
    }

    /**
     * Stack —— 后序遍历（运用辅助栈）
     * 注：先左后右，入栈元素入辅助栈，最后反转辅助栈，即为后序遍历列表。
     */
    public static List<TreeNode> postorderTraversal2(TreeNode root) {
        if (root == null)
            return null;
        List<TreeNode> postorderTraversal = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> outStack = new Stack<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                outStack.push(current);
//                display(outStack);
                current = current.right;
            } else {
                TreeNode node = stack.pop();
                current = node.left;
            }
        }

        while (!outStack.isEmpty()) {
            postorderTraversal.add(outStack.pop());
        }
        return postorderTraversal;
    }

    /**
     * 打印Stack
     */
    public static void display(Stack<TreeNode> stack) {
        List<TreeNode> list = new ArrayList<>(stack);
        for (TreeNode node : list) {
            System.out.print(node.val + " ");
        }
        System.out.println();
    }

    /**
     * Stack —— 中序遍历（运用辅助栈）
     * 注：先左后右，弹栈元素入辅助栈，最后反转辅助栈，即为中序遍历列表。
     */
    public static List<TreeNode> inorderTraversal1(TreeNode root) {
        if (root == null)
            return null;
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> outStack = new Stack<>();
        List<TreeNode> inprderTraversal = new ArrayList<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.right;
            } else {
                TreeNode node = stack.pop();
                current = node.left;
                outStack.push(node);
//                display(outStack);
            }
        }
        while (!outStack.isEmpty()) {
            inprderTraversal.add(outStack.pop());
        }
        return inprderTraversal;
    }

    /**
     * Stack —— 中序遍历
     * 注：先右后左，弹栈元素入中序遍历列表。
     */
    public static List<TreeNode> inorderTraversal(TreeNode root) {
        if (root == null)
            return null;
        List<TreeNode> inorderTraversal = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                TreeNode node = stack.pop();
                current = node.right;
                inorderTraversal.add(node);
            }
        }
        return inorderTraversal;
    }

    /**
     * Stack —— 先序遍历
     */
    public static List<TreeNode> preorderTraversal(TreeNode root) {
        if (root == null)
            return null;
        List<TreeNode> preorderTraversal = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            preorderTraversal.add(node);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return preorderTraversal;
    }
}
