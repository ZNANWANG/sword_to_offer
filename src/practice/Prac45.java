package practice;

import java.util.*;

/**
 * 扑克牌顺子
 * <p>
 * 题目描述
 * LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...他随机从中抽出了5张牌,想测测自己的
 * 手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！“红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高
 * 兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),
 * “So Lucky!”。LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何， 如果牌能组成顺子就输出true，
 * 否则就输出false。为了方便起见,你可以认为大小王是0。
 */
public class Prac45 {
    /**
     * 思路：
     * 注意到0可以补足数字之间的空缺数字，首先把数组排序，再统计数组中0的个数，再统计剩余数字之间的空缺数字个数，如果0的个数大于空缺
     * 数，则可以构成顺子，否则不行。另外，假如除0以外的数字中有重复的数字（至少一个对子），那么同样不能构成顺子。
     */
    public static boolean isContinuous(int[] numbers) {
        if (numbers == null) {
            return false;
        } else if (numbers.length < 2) {
            return false;
        }
        Arrays.sort(numbers);
        int start = 0;
        int count = 0;
        int gap = 0;
        int index = 0;
        while (index < numbers.length) {
            while (numbers[index] == 0) {
                count++;
                index++;
            }
            start = index;
            index++;
            if (index < numbers.length) {
                if (numbers[index] == numbers[start]) {
                    return false;
                } else if (numbers[index] > numbers[start]) {
                    gap += numbers[index] - numbers[start] - 1;
                }
            }
        }
        return gap <= count ? true : false;
    }

    /**
     * 思路同上
     */
    public static boolean isContinuous1(int[] numbers) {
        if (numbers == null || numbers.length < 2) {
            return false;
        }
        Arrays.sort(numbers);
        int start = 0;
        int count = 0;
        int gap = 0;
        for (int i = 0; i < numbers.length; i++) {
            int current = numbers[i];
            if (current == 0) {
                count++;
            } else {
                if (start == 0) {
                    start = current;
                } else {
                    if (start == current) {
                        return false;
                    } else {
                        gap += current - start - 1;
                        start = current;
                    }
                }
            }
        }
        return gap <= count ? true : false;
    }

    /**
     * 思路一样，参考他人不同的写法，觉得还是自己的写法比较清晰。
     */
    public boolean isContinuous2(int[] numbers) {
        if (numbers == null)
            return false;
        Arrays.sort(numbers);  //先排序
        int zero = 0, i = 0;
        for (; i < numbers.length && numbers[i] == 0; i++) {
            zero++;  //统计0的个数
        }
        for (; i < numbers.length - 1 && zero >= 0; i++) {
            if (numbers[i] == numbers[i + 1]) //有对子，则返回false
                return false;
            if (numbers[i] + 1 + zero >= numbers[i + 1]) {  //可以继续匹配
                zero -= numbers[i + 1] - numbers[i] - 1;
            } else
                return false;
        }
        if (i == numbers.length - 1)
            return true;
        else
            return false;
    }
}
