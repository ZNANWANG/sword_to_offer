package practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的镜像
 * <p>
 * 题目描述
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 * <p>
 * 输入描述:
 * 二叉树的镜像定义：源二叉树
 * 8
 * /  \
 * 6   10
 * / \  / \
 * 5  7 9 11
 * 镜像二叉树
 * 8
 * /  \
 * 10   6
 * / \  / \
 * 11 9 7  5
 */
public class Prac18 {
    /**
     * 递归求解
     */
    public void Mirror(TreeNode root) {
        if (root != null) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            Mirror(root.left);
            Mirror(root.right);
        }
    }

    /**
     * 非递归版本BFS(Queue)
     */
    public void Mirror1(TreeNode root) {
        if(root != null){
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while(!queue.isEmpty()){
                TreeNode cur = queue.poll();
                TreeNode temp = cur.left;
                cur.left = cur.right;
                cur.right = temp;
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
            }
        }
    }

    /**
     * 非递归版DFS（Stack)
     */
    public void Mirror2(TreeNode root) {
        if(root != null){
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while(!stack.isEmpty()){
                TreeNode cur = stack.pop();
                TreeNode temp = cur.left;
                cur.left = cur.right;
                cur.right = temp;
                if(cur.left != null){
                    stack.push(cur.left);
                }
                if(cur.right != null){
                    stack.push(cur.right);
                }
            }
        }
    }
}
