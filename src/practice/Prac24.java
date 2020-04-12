package practice;

import java.util.*;

/**
 * 二叉树中和为某一值的路径
 * <p>
 * 题目描述
 * 输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的
 * 结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class Prac24 {
    public static void main(String[] args) {
        // Java八大基本数据类型分别是byte, short, int, long, char, float, double, boolean.
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        ArrayList<Integer> list2 = new ArrayList<>(list1); // 对于元素是基础类型的列表进行深拷贝
        list2.set(0, 2);
        System.out.println(list1);
        System.out.println(list2);

        ArrayList<ListNode> list3 = new ArrayList<>();
        list3.add(new ListNode(1));
        ArrayList<ListNode> list4 = new ArrayList<>(list3); // 对于元素不是基础或String类型的列表进行浅拷贝
        list4.get(0).val = 2;
        System.out.println(list3.get(0).val);
        System.out.println(list4.get(0).val);

        ArrayList<String> list5 = new ArrayList<>();
        list5.add("a");
        ArrayList<String> list6 = new ArrayList<>(list5); // 对于元素是String类型的列表进行深拷贝
        list6.set(0, "b");
        System.out.println(list5);
        System.out.println(list6);
    }

    /**
     * DFS
     */
    public ArrayList<ArrayList<Integer>> FindPath1(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);

            Stack<ArrayList<Integer>> paths = new Stack<ArrayList<Integer>>();
            ArrayList<Integer> path = new ArrayList<>();
            path.add(root.val);
            paths.push(path);

            while (!stack.isEmpty()) {
                TreeNode curNode = stack.pop();
                ArrayList<Integer> curPath = paths.pop();
                if (sum(curPath) != target) {
                    // 为了AC，先右后左（LIFO)，保证路径都是从左到右。
                    if (curNode.right != null) {
                        stack.push(curNode.right);
                        ArrayList<Integer> newPath = new ArrayList<>(curPath);
                        newPath.add(curNode.right.val);
                        paths.push(newPath);
                    }
                    if (curNode.left != null) {
                        stack.push(curNode.left);
                        ArrayList<Integer> newPath = new ArrayList<>(curPath);
                        newPath.add(curNode.left.val);
                        paths.push(newPath);
                    }
                } else {
                    if (curNode.left == null && curNode.right == null)
                        res.add(curPath);
                }
            }
        }
        return res;
    }

    /**
     * BFS(顺序问题，但所有路径都可以找到）
     */
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

        if (root != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            Queue<ArrayList<Integer>> paths = new LinkedList<ArrayList<Integer>>();
            ArrayList<Integer> path = new ArrayList<>();
            path.add(root.val);
            paths.offer(path);

            while (!queue.isEmpty()) {
                TreeNode curNode = queue.poll();
                ArrayList<Integer> curPath = paths.poll();
                if (sum(curPath) != target) {
                    if (curNode.left != null) {
                        queue.offer(curNode.left);
                        ArrayList<Integer> newPath = new ArrayList<>(curPath);
                        newPath.add(curNode.left.val);
                        paths.offer(newPath);
                    }
                    if (curNode.right != null) {
                        queue.offer(curNode.right);
                        ArrayList<Integer> newPath = new ArrayList<>(curPath);
                        newPath.add(curNode.right.val);
                        paths.offer(newPath);
                    }
                } else {
                    if (curNode.left == null && curNode.right == null)
                        res.add(curPath);
                }
            }
        }
        return res;
    }

    public int sum(ArrayList<Integer> path) {
        int sum = 0;
        for (Integer a : path) {
            sum += a;
        }
        return sum;
    }

}