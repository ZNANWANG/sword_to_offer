package practice;

import com.sun.source.tree.Tree;

import javax.swing.*;
import java.util.*;

/**
 * 求两个结点的最低公共祖先
 */
public class Prac68 {
    public static void main(String[] args){
        TreeNode root = initTree();
        System.out.println(getTreeDepth(root));
        System.out.println(getTreeDepth1(root));
        System.out.println(getTreeDepth2(root));
        display(preorderTraverse(root));
        display(inorderTraverse(root));
        display(postorderTraverse(root));
        display(levelorderTraverse(root));
        System.out.println(isBalanced(root));
    }

    public static void display(List<TreeNode> list){
        for(TreeNode node : list){
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
        TreeNode node6 = new TreeNode(7);

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;
        return root;
    }

    /**
     * 求二叉搜索树两个结点的最低公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
        }
        if(root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }

    /**
     * 求二叉树两个结点的最低公共祖先
     */
    public TreeNode getLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        if(root == null || root == p || root == q){
            return root;
        }
        TreeNode left = getLowestCommonAncestor(root.left, p, q);
        TreeNode right = getLowestCommonAncestor(root.right, p, q);
        if(left != null && right != null){
            return root;
        }
        return left == null ? right : left;
    }

    /**
     * 递归 —— 计算二叉树的深度
     */
    public static int getTreeDepth(TreeNode root){
        if(root == null){
            return -1;
        }
        int left = 1 + getTreeDepth(root.left);
        int right = 1 + getTreeDepth(root.right);
        return left > right ? left : right;
    }

    /**
     * Queue —— 计算二叉树的深度
     */
    public static int getTreeDepth1(TreeNode root){
        if(root == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 1;
        int depth = -1;
        List<Integer> level = new ArrayList<>();
        level.add(count);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
            count--;
            if(count == 0){
                count = queue.size();
                depth++;
                if(count != 0)
                    level.add(count);
            }
        }
        System.out.println(level);
        return depth;
    }

    /**
     * 尾递归 —— 计算二叉树的深度
     */
    public static int getTreeDepth2(TreeNode root){
        return helper2(root, -1);
    }

    public static int helper2(TreeNode root, int depth){
        if(root == null){
            return depth;
        }
        int left = helper2(root.left, depth + 1);
        int right = helper2(root.right, depth + 1);
        return left > right ? left : right;
    }

    /**
     * 判断二叉树是否平衡
     */
    public static boolean isBalanced(TreeNode root){
        if(root == null){
            return false;
        }
        return helper(root) != -1;
    }

    public static int helper(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = getTreeDepth(root.left);
        if(left == -1){
            return -1;
        }
        int right = getTreeDepth(root.right);
        if(right == -1){
            return -1;
        }
        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);
    }

    /**
     * 前序遍历
     */
    public static List<TreeNode> preorderTraverse(TreeNode root){
        List<TreeNode> preorder = new ArrayList<>();
        if(root == null){
            return preorder;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode target = root;
        while(target != null || !stack.isEmpty()){
            if(target!=null){
                stack.push(target);
                preorder.add(target);
                target = target.left;
            } else {
                TreeNode node = stack.pop();
                target = node.right;
            }
        }
        return preorder;
    }

    /**
     * 中序遍历
     */
    public static List<TreeNode> inorderTraverse(TreeNode root){
        List<TreeNode> inorder = new ArrayList<>();
        if(root == null){
            return inorder;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode target = root;
        while(target != null || !stack.isEmpty()){
            if(target!=null){
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
     * 后序遍历
     */
    public static List<TreeNode> postorderTraverse(TreeNode root){
        List<TreeNode> postorder = new ArrayList<>();
        if(root == null){
            return postorder;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> outStack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            outStack.push(node);
            if(node.left != null){
                stack.push(node.left);
            }
            if(node.right != null){
                stack.push(node.right);
            }
        }
        while(!outStack.isEmpty()){
            postorder.add(outStack.pop());
        }
        return postorder;
     }

    /**
     * 层序遍历
     */
     public static List<TreeNode> levelorderTraverse(TreeNode root){
        List<TreeNode> levelorder = new ArrayList<>();
        if(root == null){
            return levelorder;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            levelorder.add(node);
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
        return levelorder;
     }
}
