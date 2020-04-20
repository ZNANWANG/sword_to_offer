package practice;

import java.util.Arrays;

/**
 * 构建乘积数组
 * <p>
 * 题目描述
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用
 * 除法。（注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）
 */
public class Prac51 {
    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 0, 4, 5};
        System.out.println(Arrays.toString(multiply(A)));
    }

    /**
     * 思路：
     * 双重遍历，时间复杂度为O(n^2)
     */
    public static int[] multiply(int[] A) {
        if (A == null) {
            return null;
        }
        int[] B = new int[A.length];
        for (int i = 0; i < B.length; i++) {
            int result = 1;
            for (int j = 0; j < A.length; j++) {
                if (j != i) {
                    result = result * A[j];
                }
            }
            B[i] = result;
        }
        return B;
    }

    /**
     * 不推荐的解法，不能解决有0的数组，例如[1，2，0，4，5] -> [0, 0, 40, 0, 0]
     */
    public static int[] mutiply1(int[] A) {
        if (A == null) {
            return null;
        }
        int[] B = new int[A.length];
        int product = 1;
        for (int a : A) {
            product *= a;
        }
        for (int i = 0; i < A.length; i++) {
            if (A[i] != 0) {
                B[i] = product / A[i];
            } else {
                B[i] = 1;
                for (int j = 0; j < A.length; j++) {
                    if (i != j) {
                        B[i] *= A[i];
                    }
                }
            }
        }
        return B;
    }

    /**
     * 思路：
     * 时间复杂度O(n)
     * 可以把B[i]=A[0]A[1]....A[i-1]A[i+1]....A[n-1]。看成C[i]=A[0]A[1].....A[i-1]和D[i]=A[i+1].....A[n-2]A[n-1]两部分的乘积。
     * 即通过A[i]项将B[i]分为两部分的乘积。效果相当于是个对角矩阵。
     * 左下半部分矩阵递推公式：C[i+1] = C[i] * A[i-1]
     * 右上半部分矩阵递推公式：D[i] = D[i+1] * A[i+1]
     */
    public static int[] mutiply2(int[] A) {
        if (A == null) {
            return null;
        }
        int[] B = new int[A.length];
        B[0] = 1;
        for (int i = 1; i < B.length; i++) {
            B[i] = B[i - 1] * A[i - 1];
        }

        int temp = 1;
        for (int i = A.length - 2; i >= 0; i--) {
            temp = temp * A[i + 1];
            B[i] = temp * B[i];
        }
        return B;
    }
}
