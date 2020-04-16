package practice;

import java.util.Arrays;

/**
 * 连续子数组的最大和
 *
 * 题目描述
 * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最
 * 大和,当向量全为正数的时候,问题很好解决。但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？
 * 例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。给一个数组，返回它的最大连续子序列的和，你会不会被他
 * 忽悠住？(子向量的长度至少是1)
 */
public class Prac30 {
    public static void main(String[] args){
        int[] array = new int[]{1,-2,3,10,-4,7,2,-5};
        System.out.println(FindGreatestSumOfSubArray(array));
        //System.out.println(Arrays.toString(Arrays.copyOfRange(array,0,0)));
    }

    /**
     * 蛮力对比所有子数组的和并找出最大
     */
    public static int FindGreatestSumOfSubArray(int[] array) {
        if(array != null && array.length > 0){
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < array.length; i++){
                for(int j = i; j < array.length; j++){
                    int[] temp = Arrays.copyOfRange(array, i, j + 1);
                    max = Math.max(max, sum(temp));
                }
            }
            return max;
        }
        return 0;
    }

    public static int sum(int[] array){
        int sum = 0;
        for(int a : array){
            sum += a;
        }
        return sum;
    }

    /**
     * 动态规划
     * 注意是连续子数组，假如不是连续子数组，直接返回所有正数的和即可，因为是连续子数组，所以要比较当前值和之前最大连续子数组的和，
     * 如果当前值大，则相当于舍弃之前最大连续子数组的和，并从当前值开始重新规划最大连续子数组。
     */
    public static int FindGreatestSumOfSubArray1(int[] array) {
        if(array != null && array.length > 0){
            int max = array[0];
            int[] dp = new int[array.length + 1];
            dp[1] = array[0];
            for(int i = 2; i <= array.length; i++){
                dp[i] = Math.max(dp[i-1] + array[i - 1], array[i - 1]);
                max = Math.max(dp[i], max);
                System.out.println("dp: " + Arrays.toString(dp));
                System.out.println("max: " + max);
            }
            return max;
        }
        return 0;
    }

    /**
     * 动态规划，和FindGreatestSumOfSubArray1基本一样，只是dp[0] = array[0]。
     */
    public static int FindGreatestSumOfSubArray2(int[] array) {
        if(array != null && array.length > 0){
            int max = array[0];
            int[] dp = new int[array.length];
            dp[0] = array[0];
            for(int i = 1; i < array.length; i++){
                dp[i] = Math.max(dp[i - 1] + array[i], array[i]);
                max = Math.max(dp[i], max);
                System.out.println("dp: " + Arrays.toString(dp));
                System.out.println("max: " + max);
            }
            return max;
        }
        return 0;
    }
}
