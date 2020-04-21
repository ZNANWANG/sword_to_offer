package practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 把二叉树打印成多行
 * <p>
 * 题目描述
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 */
public class Prac60 {
    /**
     * 思路：
     * 层级遍历，每层记录结点总数，并用辅助Queue存储每层结点，当每层遍历完时，辅助Queue中全部结点出队列并加入到每层结点列表(list)中。
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> level = new LinkedList<>();
        queue.offer(pRoot);
        int count = queue.size();
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            level.offer(current);
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
            count--;
            if (count == 0) {
                ArrayList<Integer> list = new ArrayList<>();
                while (!level.isEmpty()) {
                    list.add(level.poll().val);
                }
                result.add(list);
                count = queue.size();
            }
        }
        return result;
    }

    /**
     * 参考大佬的递归解法
     */
    public ArrayList<ArrayList<Integer>> Print1(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        depth(pRoot, 1, list);
        return list;
    }

    private void depth(TreeNode root, int depth, ArrayList<ArrayList<Integer>> list) {
        if (root == null) return;
        if (depth > list.size())
            list.add(new ArrayList<Integer>());
        list.get(depth - 1).add(root.val);

        depth(root.left, depth + 1, list);
        depth(root.right, depth + 1, list);
    }

    /**
     * 仅供参考，这种解法对于每层结点数的计算较复杂。
     */
    public ArrayList<ArrayList<Integer>> Print2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        LinkedList<TreeNode> q = new LinkedList<TreeNode>();
        if (pRoot == null)
            return ret;
        q.add(pRoot);
        int now = 1, next = 0;
        while (!q.isEmpty()) {
            TreeNode t = q.remove();
            now--;
            tmp.add(t.val);
            if (t.left != null) {
                q.add(t.left);
                next++;
            }
            if (t.right != null) {
                q.add(t.right);
                next++;
            }
            if (now == 0) {
                ret.add(new ArrayList<Integer>(tmp));
                tmp.clear();
                now = next;
                next = 0;
            }
        }
        return ret;
    }
}
