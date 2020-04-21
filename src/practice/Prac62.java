package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉搜索树的第k小结点
 * <p>
 * 题目描述
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如，（5，3，7，2，4，6，8）中，按结点数值大小顺序第三小结点的值为4。
 */
public class Prac62 {
    /**
     * 思路：
     * 注意到二叉搜索树的中序遍历序列单调递增，所以先中序遍历二叉搜索树，最后返回第k个结点。
     */
    public TreeNode KthNode(TreeNode pRoot, int k) {
        if (k == 0 || pRoot == null) {
            return null;
        }
        List<TreeNode> inorder = inorderTraverse(pRoot);
        if (k > inorder.size()) {
            return null;
        }
        return inorder.get(k);
    }

    public List<TreeNode> inorderTraverse(TreeNode root) {
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
                inorder.add(node);
                target = node.right;
            }
        }
        return inorder;
    }

    /**
     * 简化版上述解法，及时返回，效率更高。
     */
    public TreeNode KthNode1(TreeNode pRoot, int k) {
        if (pRoot == null || k == 0) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode target = pRoot;
        int count = 0;
        while (target != null || !stack.isEmpty()) {
            if (target != null) {
                stack.push(target);
                target = target.left;
            } else {
                TreeNode node = stack.pop();
                target = node.right;
                count++;
                if (count == k) {
                    return node;
                }
            }
        }
        return null;
    }
}
