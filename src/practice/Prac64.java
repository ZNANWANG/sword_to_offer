package practice;

import java.util.ArrayList;

/**
 * 滑动窗口的最大值
 * <p>
 * 题目描述
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存
 * 在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}，
 * {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 */
public class Prac64 {
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        if(num == null || num.length == 0 || size == 0 || size > num.length){
            return list;
        }
        for(int i = 0; i <= num.length - size; i++){
            list.add(getMax(num, i, size));
        }
        return list;
    }

    public int getMax(int[] num, int from, int size){
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < size; i++){
            max = max > num[from + i] ? max : num[from + i];
        }
        return max;
    }
}
