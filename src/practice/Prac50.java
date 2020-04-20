package practice;

import java.util.*;

/**
 * 数组中重复的数字
 * <p>
 * 题目描述
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。
 * 请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 */
public class Prac50 {
    public static void main(String[] args){
        int[] array = new int[]{2,1,3,1,4};
        System.out.println(duplicate3(array, array.length, new int[1]));
    }
    /**
     * 思路：
     * HashMap记数
     */
    public static boolean duplicate(int[] numbers, int length, int[] duplication) {
        if(numbers == null || duplication == null || duplication.length < 1 || numbers.length < 1){
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < length; i++){
            int cur = numbers[i];
            if(map.containsKey(cur)){
                map.put(cur, map.get(cur) + 1);
                if(map.get(cur) > 1){
                    duplication[0] = cur;
                    return true;
                }
            } else {
                map.put(cur, 1);
            }
        }
        return false;
    }

    /**
     * 思路：
     * list排序之后利用lastIndexOf()和indexOf()方法，差值不小于一表示重复。
     */
    public static boolean duplicate1(int[] numbers, int length, int[] duplication){
        if(numbers == null || duplication == null || duplication.length < 1 || numbers.length < 1){
            return false;
        }
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < numbers.length; i++){
            list.add(numbers[i]);
        }
        Collections.sort(list);
        for(int i = 0;i < numbers.length; i++){
            if(list.lastIndexOf(numbers[i]) - list.indexOf(numbers[i]) >= 1){
                duplication[0] = numbers[i];
                return true;
            }
        }
        return false;
    }

    /**
     * 思路：
     * 双重遍历，时间复杂度为O(n^2)。
     */
    public static boolean duplicate2(int[] numbers, int length, int[] duplication){
        if(numbers == null || duplication == null || duplication.length < 1 || numbers.length < 1){
            return false;
        }
        for(int i = 0; i < numbers.length - 1; i++){
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] == numbers[j]) {
                    duplication[0] = numbers[i];
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 思路：
     * 在一个长度为n的数组里的所有数字都在0到n-1的范围内，维持一个boolean数组，以每个元素值作为数组索引查询值是否重复。
     */
    public static boolean duplicate3(int[] numbers, int length, int[] duplication){
        if(numbers == null || duplication == null || duplication.length < 1 || numbers.length < 1){
            return false;
        }
        boolean[] array = new boolean[length];
        int index = 0;
        while(index < length){
            if(array[numbers[index]] == true){
                duplication[0] = numbers[index];
                return true;
            }
            array[numbers[index]] = true;
            index++;
        }
        return false;
    }
}
