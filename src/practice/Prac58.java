package practice;

import java.util.*;

/**
 * 对称的二叉树
 * <p>
 * 题目描述
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 */
public class Prac58 {
    public static void main(String[] args) {
        TreeNode root = initTree();
        List<TreeNode> list = preorderTraverse(root);
        display(list);
        list = preorderTraverseReversed(root);
        display(list);
        System.out.println(isSymmetrical(root));
    }

    public static void display(List<TreeNode> list) {
        for (TreeNode node : list) {
            if (node != null) {
                System.out.print(node.val + " ");
            } else {
                System.out.print(node + " ");
            }
        }
        System.out.println();
    }

    public static TreeNode initTree() {
        TreeNode root = new TreeNode(8);
        TreeNode node1 = new TreeNode(6);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(7);
        TreeNode node6 = new TreeNode(5);

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;
        return root;
    }

    /**
     * 思路：
     * 首先写两个遍历方法，一种是前序遍历方法，一种是根右左的遍历方法，注意这两个遍历方法都保存null结点。
     * 用两种遍历方法遍历对称二叉树，所得的两个遍历序列中结点值应该是一一对应并完全相同的，非而对称二叉树的遍历序列结点值不相同。
     */
    public static boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }

        List<TreeNode> preorder = preorderTraverse(pRoot);
        List<TreeNode> preorderR = preorderTraverseReversed(pRoot);
        for (int i = 0; i < preorder.size(); i++) {
            if (preorder.get(i) == null && preorderR.get(i) != null) {
                return false;
            } else if (preorder.get(i) != null && preorderR.get(i) == null) {
                return false;
            } else if (preorder.get(i) != null && preorderR.get(i) != null) {
                if (preorder.get(i).val != preorderR.get(i).val) {
                    return false;
                }
            }
        }
        return true;
    }

    public static List<TreeNode> preorderTraverse(TreeNode root) {
        List<TreeNode> preorder = new ArrayList<>();
        if (root == null) {
            return preorder;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            preorder.add(current);
            if (current != null) {
                stack.push(current.right);
                stack.push(current.left);
            }
        }
        return preorder;
    }

    public static List<TreeNode> preorderTraverseReversed(TreeNode root) {
        List<TreeNode> preorderR = new ArrayList<>();
        if (root == null) {
            return preorderR;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            preorderR.add(current);
            if (current != null) {
                stack.push(current.left);
                stack.push(current.right);
            }
        }
        return preorderR;
    }

    /**
     * 思路：
     * 先构建对称二叉树，然后一一比对。
     */
    public boolean isSymmetrical1(TreeNode pRoot) {
        TreeNode node = getMirror(pRoot);
        return isSymmetrical1(pRoot, node);
    }

    public boolean isSymmetrical1(TreeNode pRoot, TreeNode node) {
        if (pRoot == null && node == null) {
            return true;
        } else if (pRoot == null || node == null) {
            return false;
        }
        if (pRoot.val == node.val) {
            return isSymmetrical1(pRoot.left, node.left) && isSymmetrical1(pRoot.right, node.right);
        }
        return false;
    }

    public TreeNode getMirror(TreeNode pRoot) {
        if (pRoot == null) {
            return null;
        }
        TreeNode root = new TreeNode(pRoot.val);
        root.right = getMirror(pRoot.left);
        root.left = getMirror(pRoot.right);
        return root;
    }

    /**
     * 思路：首先根节点以及其左右子树，左子树的左子树和右子树的右子树相同，左子树的右子树和右子树的左子树相同即可，采用递归。
     * 非递归也可，采用栈或队列存取各级子树根节点
     */
    boolean isSymmetrical2(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        return comRoot(pRoot.left, pRoot.right);
    }

    private boolean comRoot(TreeNode left, TreeNode right) {
        // TODO Auto-generated method stub
        if (left == null)
            return right == null;
        if (right == null)
            return false;
        if (left.val != right.val)
            return false;
        return comRoot(left.right, right.left) && comRoot(left.left, right.right);
    }

    /**
     * 思路同上
     */
    boolean isSymmetrical3(TreeNode pRoot)
    {
        if(pRoot == null) return true;
        return isSymmetrical3(pRoot.left, pRoot.right);
    }

    private boolean isSymmetrical3(TreeNode left, TreeNode right) {
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        return left.val == right.val //为镜像的条件：左右节点值相等
                && isSymmetrical3(left.left, right.right) //2.对称的子树也是镜像
                && isSymmetrical3(left.right, right.left);
    }

    public boolean isSymmetricalDFS(TreeNode pRoot) {
        if (pRoot == null) return true;
        Stack<TreeNode> s = new Stack<>();
        s.push(pRoot.left);
        s.push(pRoot.right);
        while (!s.empty()) {
            TreeNode right = s.pop();//成对取出
            TreeNode left = s.pop();
            if (left == null && right == null) continue;
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;
            //成对插入
            s.push(left.left);
            s.push(right.right);
            s.push(left.right);
            s.push(right.left);
        }
        return true;
    }

    public boolean isSymmetricalBFS(TreeNode pRoot) {
        if (pRoot == null) return true;
        Queue<TreeNode> s = new LinkedList<>();
        s.offer(pRoot.left);
        s.offer(pRoot.right);
        while (!s.isEmpty()) {
            TreeNode left = s.poll();//成对取出
            TreeNode right = s.poll();
            if (left == null && right == null) continue;
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;
            //成对插入
            s.offer(left.left);
            s.offer(right.right);
            s.offer(left.right);
            s.offer(right.left);
        }
        return true;
    }
}
