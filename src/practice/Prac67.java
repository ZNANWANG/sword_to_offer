package practice;

/**
 * 剪绳子
 * <p>
 * 题目描述
 * 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为k[0],k[1],...,k[m]。请问
 * k[0]xk[1]x...xk[m]可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * 输入描述:
 * 输入一个数n，意义见题面。（2 <= n <= 60）
 * 输出描述:
 * 输出答案。
 * 示例1
 * 输入
 * 8
 * 输出
 * 18
 */
public class Prac67 {
    /**
     * 动态规划
     */
    public int cutRope(int target) {
        if (target < 1) {
            return 0;
        } else if (target == 1) {
            return 1;
        }
        int[] dp = new int[target + 1];
        dp[1] = 1; // dp[1]代表着只分一段，当i > 1时，dp[i]代表分成多段。
        for (int i = 2; i <= target; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= i / 2; j++) {
                int temp = dp[i - j] * dp[j];
                max = max > temp ? max : temp;
                temp = j * (i - j); // dp代表分段后相乘最大值但不包括本值
                max = max > temp ? max : temp;
            }
            dp[i] = max;
        }
        return dp[target];
    }

    public int cutRope1(int target) {
        if (target < 1) {
            return 0;
        } else if (target == 1) {
            return 1;
        } else if(target == 2){
            return 1;
        } else if(target == 3){
            return 2;
        }
        int[] dp = new int[target + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= target; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= i / 2; j++) {
                int temp = dp[i - j] * dp[j];
                max = max > temp ? max : temp;
            }
            dp[i] = max;
        }
        return dp[target];
    }

    /**
     * 剑指Offer版解法
     */
    public static int cutRope2(int target){
        if(target < 2) {
            return 0;
        } else if(target == 2){
            return 1;
        } else if(target == 3){
            return 2;
        }

        int timesOf3 = target / 3;
        if(target - timesOf3 * 3 == 1){
            timesOf3 -= 1;
        }

        int timesOf2 = (target - timesOf3 * 3) / 2;
        return (int)Math.pow(3, timesOf3) * (int)Math.pow(2, timesOf2);
    }
}
