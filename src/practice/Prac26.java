package practice;

import com.sun.source.tree.Tree;

import java.util.*;

/**
 * 二叉搜索树与双向链表
 * <p>
 * 题目描述
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
public class Prac26 {
    TreeNode head, tail;

    public static void main(String[] args) {
        TreeNode root = testNodes2();

        // 中序遍历
        ArrayList<TreeNode> inorder = new ArrayList<>();
        InorderTraversal1(CloneTree(root), inorder);
        for (TreeNode node : inorder) {
            System.out.print(node.val + " ");
        }
        System.out.println();

        // 前序遍历
        ArrayList<TreeNode> preorder = new ArrayList<>();
        PreorderTraversal1(CloneTree(root), preorder);
        for (TreeNode node : preorder) {
            System.out.print(node.val + " ");
        }
        System.out.println();

        // 后序遍历
        ArrayList<TreeNode> postorder = new ArrayList<>();
        PostorderTraversal1(CloneTree(root), postorder);
        for (TreeNode node : postorder) {
            System.out.print(node.val + " ");
        }
        System.out.println();

        // 层序遍历
        ArrayList<TreeNode> levelorder = new ArrayList<>();
        LevelorderTraversal(CloneTree(root), levelorder);
        for (TreeNode node : levelorder) {
            System.out.print(node.val + " ");
        }
        System.out.println();

        // 输出二叉树的全部左结点
        ArrayList<TreeNode> lefts = new ArrayList<>();
        findLeft1(CloneTree(root), lefts);
        for (TreeNode node : lefts) {
            System.out.print(node.val + " ");
        }
        System.out.println();

        // 输出二叉树的全部右结点
        ArrayList<TreeNode> rights = new ArrayList<>();
        findRight(CloneTree(root), rights);
        for (TreeNode node : rights) {
            System.out.print(node.val + " ");
        }
        System.out.println();

        Prac26 prac26 = new Prac26();
        prac26.Convert(root);
        System.out.println("head: " + prac26.head.val);
        System.out.println("tail: " + prac26.tail.val);
        displayDoubleLink(prac26.head, prac26.tail);
        //test();
    }

    public static TreeNode testNodes1() {
        TreeNode node1 = new TreeNode(10);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(14);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(8);
        TreeNode node6 = new TreeNode(12);
        TreeNode node7 = new TreeNode(16);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        return node1;
    }

    public static TreeNode testNodes2() {
        TreeNode node1 = new TreeNode(10);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(14);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(8);
        TreeNode node6 = new TreeNode(12);
        TreeNode node7 = new TreeNode(16);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        return node1;
    }

    /**
     * 二叉树转双向链表
     * 注意：head和tail需使用全局变量（静态全局变量和成员变量都可以），否则会出现引用参数问题（见图prac26_引用参数问题.png）
     */
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree != null) {
            ConvertHelper(pRootOfTree);
            return head;
        }
        return null;
    }

    /**
     * 递归 + 头插法——二叉树转双向链表
     */
    public TreeNode Convert1(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return pRootOfTree;

        Convert(pRootOfTree.right);
        if (head == null) {
            head = pRootOfTree;
        } else {
            head.left = pRootOfTree;
            pRootOfTree.right = head;
            head = pRootOfTree;
        }
        Convert(pRootOfTree.left);

        return head;
    }

    /**
     * 递归 + 尾插法（版本一）——二叉树转双向链表
     */
    public TreeNode Convert2(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return pRootOfTree;

        Convert(pRootOfTree.left);
        if (head == null) {
            head = tail = pRootOfTree;
        } else {
            tail.right = pRootOfTree;
            pRootOfTree.left = tail;
            tail = pRootOfTree;
        }
        Convert(pRootOfTree.right);

        return head;
    }

    /**
     * 递归 + 尾插法（版本二）——二叉树转双向链表
     */
    public TreeNode Convert3(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return pRootOfTree;

        Convert(pRootOfTree.left);
        if (tail == null) {
            tail = pRootOfTree;
        } else {
            tail.right = pRootOfTree;
            pRootOfTree.left = tail;
            tail = pRootOfTree;
        }
        Convert(pRootOfTree.right);

        // 从链表尾结点回找头结点
        head = tail;
        while (head != null && head.left != null)
            head = head.left;

        return head;
    }

    /**
     * 递归 + 尾插法——二叉树转双向链表
     */
    public void ConvertHelper(TreeNode root) {
        if (root.left != null)
            ConvertHelper(root.left);
        if (head == null) {
            head = tail = root;
        } else {
            tail.right = root;
            root.left = tail;
            tail = root;
        }
        if (root.right != null)
            ConvertHelper(root.right);
    }

    /**
     * Stack——二叉树转双向链表
     */
    public void ConvertHelper1(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            if (current.left == null && current.right == null) {
                if (head == null) {
                    head = tail = current;
                } else {
                    tail.right = current;
                    current.left = tail;
                    tail = current;
                }
            } else {
                if (current.right != null) {
                    stack.push(current.right);
                    current.right = null;
                }
                stack.push(current);
                if (current.left != null) {
                    stack.push(current.left);
                    current.left = null;
                }
            }
        }
    }

    /**
     * 注意：
     * <p>
     * Java使用值调用，而且只有值调用。也就是说方法得到的是参数值的一个拷贝，并不是参数值本身，所以，方法不能修改传递给它的的任何参
     * 数变量本身。
     * <p>
     * 一个方法不可能修改一个基本数据类型的参数。而对象引用作为参数就不同了。这时，方法得到一个对象引用的拷贝。对象引用和其拷贝，同时
     * 引用着一个对象。
     * <p>
     * 总结：
     * （1）一个方法不能修改一个基本数据类型的参数（即数值型和布尔类型值）。
     * （2）一个方法可以通过对象参数对其引用的对象状态进行操控。
     * （3）一个方法不能通过让对象参数引用一个新的对象，进而使原来的对象引用也指向新的对象。(方法里面变但是外面不跟着变，要想一起变
     * 就得用全局变量）
     * <p>
     * 举例如下：
     * 当changeNode方法中head参数指向新的对象时，test方法中head引用并没有指向新的对象。
     */
    public static void test() {
        TreeNode head = new TreeNode(100);
        changeNode(head);
        System.out.println("node: " + head.val);
    }

    public static void changeNode(TreeNode head) {
        head = new TreeNode(99);
    }

    /**
     * 打印双向链表
     */
    public static void displayDoubleLink(TreeNode head, TreeNode tail) {
        if (head != null) {
            TreeNode copyHead = head;
            while (copyHead != null) {
                if (copyHead.right != null) {
                    System.out.print(copyHead.right.val + " ");
                } else {
                    System.out.print("null ");
                }
                copyHead = copyHead.right;
            }
            System.out.println("\n");

            TreeNode copyTail = tail;
            while (copyTail != null) {
                if (copyTail.left != null) {
                    System.out.print(copyTail.left.val + " ");
                } else {
                    System.out.print("null ");
                }
                copyTail = copyTail.left;
            }
            System.out.println("\n");
        }
    }

    /**
     * 递归——中序遍历
     */
    public static void InorderTraversal(TreeNode root, ArrayList<TreeNode> inorder) {
        if (root != null) {
            if (root.left != null) {
                InorderTraversal(root.left, inorder);
            }
            inorder.add(root);
            if (root.right != null) {
                InorderTraversal(root.right, inorder);
            }
        }
    }

    /**
     * 递归——前序遍历
     */
    public static void PreorderTraversal(TreeNode root, ArrayList<TreeNode> preorder) {
        if (root != null) {
            preorder.add(root);
            if (root.left != null) {
                PreorderTraversal(root.left, preorder);
            }
            if (root.right != null) {
                PreorderTraversal(root.right, preorder);
            }
        }
    }

    /**
     * 递归——后序遍历
     */
    public static void PostorderTraversal(TreeNode root, ArrayList<TreeNode> postorder) {
        if (root != null) {
            if (root.left != null) {
                PostorderTraversal(root.left, postorder);
            }
            if (root.right != null) {
                PostorderTraversal(root.right, postorder);
            }
            postorder.add(root);
        }
    }

    /**
     * Stack——前序遍历
     */
    public static void PreorderTraversal1(TreeNode root, ArrayList<TreeNode> inorder) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode current = stack.pop();
                inorder.add(current);
                if (current.right != null) {
                    stack.push(current.right);
                }
                if (current.left != null) {
                    stack.push(current.left);
                }
            }
        }
    }

    /**
     * Stack——中序遍历
     */
    public static void InorderTraversal1(TreeNode root, ArrayList<TreeNode> inorder) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode current = stack.pop();
                if (current.left == null && current.right == null) {
                    inorder.add(current);
                } else {
                    if (current.right != null) {
                        stack.push(current.right);
                        current.right = null;
                    }
                    stack.push(current);
                    if (current.left != null) {
                        stack.push(current.left);
                        current.left = null;
                    }
                }
            }
        }
    }

    /**
     * 打印栈
     */
    public static void displayStack(Stack<TreeNode> stack) {
        for (TreeNode node : stack)
            System.out.print(" stack: " + node.val);
        System.out.println("\n");
    }

    /**
     * Stack——复制二叉树
     */
    public static TreeNode CloneTree(TreeNode root) {
        if (root != null) {
            TreeNode clonedRoot = new TreeNode(root.val);
            Map<TreeNode, TreeNode> map = new HashMap<>();
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            map.put(root, clonedRoot);
            while (!stack.isEmpty()) {
                TreeNode current = stack.pop();
                if (current.left != null) {
                    map.put(current.left, new TreeNode(current.left.val));
                    stack.push(current.left);
                }
                if (current.right != null) {
                    map.put(current.right, new TreeNode(current.right.val));
                    stack.push(current.right);
                }
            }

            stack.clear();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode current = stack.pop();
                if (current.left != null) {
                    map.get(current).left = map.get(current.left);
                    stack.push(current.left);
                }
                if (current.right != null) {
                    map.get(current).right = map.get(current.right);
                    stack.push(current.right);
                }
            }
            return clonedRoot;
        }
        return null;
    }

    /**
     * Stack——后序遍历
     */
    public static void PostorderTraversal1(TreeNode root, ArrayList<TreeNode> postorder) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode current = stack.peek();
                if (current.left == null && current.right == null) {
                    postorder.add(stack.pop());
                } else {
                    if (current.right != null) {
                        stack.push(current.right);
                        current.right = null;
                    }
                    if (current.left != null) {
                        stack.push(current.left);
                        current.left = null;
                    }
                }
            }
        }
    }

    /**
     * Stack——层序遍历（从左到右）
     */
    public static void LevelorderTraversal(TreeNode root, ArrayList<TreeNode> levelorder) {
        if (root != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode current = queue.poll();
                levelorder.add(current);
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }
    }

    /**
     * Stack——层序遍历（从右到左）
     */
    public static void LevelorderTraversal1(TreeNode root, ArrayList<TreeNode> levelorder) {
        if (root != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode current = queue.poll();
                levelorder.add(current);
                if (current.right != null) {
                    queue.offer(current.right);
                }
                if (current.left != null) {
                    queue.offer(current.left);
                }
            }
        }
    }

    /**
     * 递归——输出二叉树最左边的点
     */
    public static void findLeft(TreeNode root, ArrayList<TreeNode> lefts) {
        if (root != null) {
            lefts.add(root);
            if (root.left != null) {
                findLeft(root.left, lefts);
            } else if (root.right != null) {
                findLeft(root.right, lefts);
            }
        }
    }

    /**
     * 递归——输出二叉树最右边的点
     */
    public static void findRight(TreeNode root, ArrayList<TreeNode> rights) {
        if (root != null) {
            rights.add(root);
            if (root.right != null) {
                findLeft(root.right, rights);
            } else if (root.left != null) {
                findLeft(root.left, rights);
            }
        }
    }

    /**
     * Stack——输出二叉树最左边的点
     * 注：List、Queue，甚至数组都可以，因为每轮只存一个结点。
     */
    public static void findLeft1(TreeNode root, ArrayList<TreeNode> lefts) {
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.add(root);
            while (!stack.isEmpty()) {
                TreeNode current = stack.pop();
                lefts.add(current);
                if (current.left != null) {
                    stack.push(current.left);
                    continue;
                }
                if (current.right != null) {
                    stack.push(current.right);
                    continue;
                }
            }
        }
    }

    /**
     * Stack——输出二叉树最右边的点
     * 注：List、Queue，甚至数组都可以，因为每轮只存一个结点。
     */
    public static void findRight1(TreeNode root, ArrayList<TreeNode> lefts) {
        if (root != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode current = queue.poll();
                lefts.add(current);
                if (current.right != null) {
                    queue.offer(current.right);
                    continue;
                }
                if (current.left != null) {
                    queue.offer(current.left);
                    continue;
                }
            }
        }
    }
}
