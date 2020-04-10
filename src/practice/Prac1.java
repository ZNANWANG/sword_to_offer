package practice;

/**
 * 二维数组中的查找
 *
 * 题目描述
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，
 * 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class Prac1 {

    /**
     * 思路
     * 暴力查找
     */
    public boolean Find1(int target, int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 思路
     * 矩阵是有序的，从左下角来看，向上数字递减，向右数字递增，
     * 因此从左下角开始查找，当要查找数字比左下角数字大，右移
     * 要查找数字比左下角数字小时，上移
     * 注：从右上角开始查找也可以
     */
    public boolean Find2(int target, int[][] array) {
        int row = 0;
        int col = array[0].length - 1;
        while (row < array.length && col >= 0) {
            if (array[row][col] == target) {
                return true;
            } else if (array[row][col] > target) {
                col--;
            } else if (array[row][col] < target) {
                row++;
            }
        }
        return false;
    }

    /**
     * 思路
     * 对数组每一行用二叉查找
     */
    public boolean Find3(int target, int[][] array) {
        int row = 0;
        while(row < array.length){
            int start = 0;
            int end = array[0].length - 1;
            while(start <= end){
                int middle = (start + end)/2;
                if(array[row][middle] == target){
                    return true;
                } else if(array[row][middle] > target){
                    end = middle - 1;
                } else if(array[row][middle] < target){
                    start = middle + 1;
                }
            }
            row++;
        }
        return false;
    }

}
