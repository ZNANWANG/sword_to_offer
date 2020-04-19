package practice;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组中重复的数字
 * <p>
 * 题目描述
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。
 * 请找出数组中第一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 */
public class Prac50 {
    /**
     * 思路：
     * HashMap记数
     */
    public boolean duplicate(int[] numbers, int length, int[] duplication) {
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
}
