package practice;

/**
 * 变态跳台阶
 * <p>
 * 题目描述
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
public class Prac9 {
    /**
     * 思路
     * 递归求解
     */
    public int JumpFloorII(int target) {
        if (target == 0) {
            return 0;
        } else if (target == 1) {
            return 1;
        } else {
            int res = 1;
            for (int i = 1; i <= target; i++) {
                res += JumpFloorII(target - i);
            }
            return res;
        }
    }

    /**
     * 思路
     * 动态规划
     */
    public int JumpFloorII2(int target) {
        if (target == 0) {
            return 0;
        } else if (target == 1) {
            return 1;
        } else {
            int[] dp = new int[target + 1];
            dp[1] = 1;
            for (int i = 2; i <= target; i++) {
                int res = 1;
                for (int j = 1; j <= target; j++) {
                    res += dp[target - j];
                }
                dp[i] = res;
            }
            return dp[target];
        }
    }
}
