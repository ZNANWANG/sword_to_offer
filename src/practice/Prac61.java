package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化二叉树
 *
 * 题目描述
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 *
 * 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，从而使得内存中建立起来的二叉树可以持久保存。序列化
 * 可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，序列化的结果是一个字符串，序列化时通过某种符号表示空节点（#），以 ！表
 * 示一个结点值的结束（value!）。
 *
 * 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
 */
public class Prac61 {
    public static void main(String[] args){
        TreeNode root = initTree();
        Prac61 test = new Prac61();
        String str = test.Serialize1(root);
        TreeNode newRoot = test.Deserialize1(str);
        test.display(newRoot);
    }

    public void display(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode curNode = queue.poll();
            if(curNode!=null){
                System.out.print(curNode.val);
                queue.offer(curNode.left);
                queue.offer(curNode.right);
            } else {
                System.out.print("#");
            }
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
     * 按照前序遍历序列化
     */
    String Serialize(TreeNode root) {
        if(root == null)
            return "";
        StringBuilder sb = new StringBuilder();
        Serialize2(root, sb);
        return sb.toString();
    }

    void Serialize2(TreeNode root, StringBuilder sb) {
        if(root == null) {
            sb.append("#!");
            return;
        }
        sb.append(root.val);
        sb.append('!');
        Serialize2(root.left, sb);
        Serialize2(root.right, sb);
    }

    int index = -1;

    TreeNode Deserialize(String str) {
        if(str.length() == 0 || str == null)
            return null;
        String[] strs = str.split("!");
        return Deserialize2(strs);
    }

    TreeNode Deserialize2(String[] strs) {
        index++;
        if(!strs[index].equals("#")) {
            TreeNode root = new TreeNode(0);
            root.val = Integer.parseInt(strs[index]);
            root.left = Deserialize2(strs);
            root.right = Deserialize2(strs);
            return root;
        }
        return null;
    }

    /**
     * 层序遍历
     */
    String Serialize1(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        if(root == null){
            return sb.toString();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode curNode = queue.poll();
            if(curNode!=null){
                sb.append(curNode.val);
                sb.append("!");
                queue.offer(curNode.left);
                queue.offer(curNode.right);
            } else {
                sb.append("#!");
            }
        }
        return sb.toString();
    }

    TreeNode Deserialize1(String str) {
        if(str == null || str.length() == 0){
            return null;
        }
        String[] strs = str.split("!");
        System.out.println(Arrays.toString(strs));
        ArrayList<TreeNode> list = new ArrayList<>();
        for(int i = 0; i < strs.length; i++){
            if(!strs[i].equals("#")) {
                list.add(new TreeNode(Integer.parseInt(strs[i])));
            } else {
                list.add(null);
            }
        }
        System.out.println();

        for(int i = 0, j = 1; j < strs.length; i++){
            if(!strs[i].equals("#")){
                TreeNode curNode = list.get(i);
                curNode.left = list.get(j++);
                curNode.right = list.get(j++);
            }
        }
        return list.get(0);
    }
}
