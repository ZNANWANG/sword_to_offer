package practice;

import java.util.Arrays;

/**
 * 二叉搜索树的后序遍历序列
 * <p>
 * 题目描述
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 */
public class Prac23 {
    /**
     *
     */
    public boolean VerifySequenceOfBST(int[] sequence) {
        if (sequence != null && sequence.length > 0) {
            int root = sequence[sequence.length - 1];
            // 找出左右子树的分界线
            int i = 0;
            for (; i < sequence.length - 1; i++) {
                if (sequence[i] > root) {
                    break;
                }
            }
            //判断右子树是否包含比根结点值小的结点，如果存在则证明不是后序遍历，不存在则继续
            int j = i;
            for (; j < sequence.length - 1; j++) {
                if (sequence[j] < root) {
                    return false;
                }
            }
            boolean left = true;
            if (i > 0)
                left = VerifySequenceOfBST(Arrays.copyOfRange(sequence, 0, i));

            boolean right = true;
            if (i < j)
                right = VerifySequenceOfBST(Arrays.copyOfRange(sequence, i, j));

            return left && right;
        }
        return false;
    }
}
