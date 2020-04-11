package practice;

/**
 * 树的子结构
 * <p>
 * 题目描述
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */
public class Prac17 {
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        boolean hasSubtree = false;
        if (root1 != null && root2 != null) {
            // 假如两个结点的取值相等，则判断从当前两结点root1和root2出发，Tree1是否包含Tree2。
            if (root1.val == root2.val) {
                hasSubtree = DoesTree1HasTree2(root1, root2);
            }
            // 如果从当前两结点root1和root2出发，子树匹配不成功，则从root1的左子结点和root2出发再次递归匹配。
            if (!hasSubtree) {
                hasSubtree = HasSubtree(root1.left, root2);
            }
            // 如果从root1的左子结点和root2出发，子树匹配不成功，则从root1的右子结点和root2出发再次递归匹配。
            if (!hasSubtree) {
                hasSubtree = HasSubtree(root1.right, root2);
            }
        }
        return hasSubtree;
    }

    public boolean DoesTree1HasTree2(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return true;
        } else if (root1 == null) {
            return false;
        } else if (root1.val != root2.val) {
            return false;
        } else {
            // 保证root1的左右子树和root2的左右子树完全匹配
            return DoesTree1HasTree2(root1.left, root2.left)
                    && DoesTree1HasTree2(root1.right, root2.right);
        }
    }
}
