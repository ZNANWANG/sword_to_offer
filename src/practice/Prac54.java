package practice;

import jdk.dynalink.linker.LinkerServices;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 字符流中第一个不重复的字符
 * <p>
 * 题目描述
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。当从
 * 该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 * 输出描述:
 * 如果当前字符流没有存在出现一次的字符，返回#字符。
 */
public class Prac54 {
    public static void main(String[] args) {
        Prac54 test = new Prac54();
        test.Insert('g');
        System.out.println(test.FirstAppearingOnce());
        test.Insert('o');
        System.out.println(test.FirstAppearingOnce());
        test.Insert('o');
        System.out.println(test.FirstAppearingOnce());
        test.Insert('g');
        System.out.println(test.FirstAppearingOnce());
        test.Insert('l');
        System.out.println(test.FirstAppearingOnce());
        test.Insert('e');
        System.out.println(test.FirstAppearingOnce());
    }

    /**
     * 思路：
     * 利用HashMap记录每个字符出现的次数。
     */
    Map<Character, Integer> map = new LinkedHashMap<>();

    //Insert one char from stringstream
    public void Insert(char ch) {
        if (map.containsKey(ch)) {
            map.put(ch, map.get(ch) + 1);
        } else {
            map.put(ch, 1);
        }
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        char result = '#';
        for (Character c : map.keySet()) {
            if (map.get(c) == 1) {
                result = c;
                break; // 好蠢，一开始没加break，导致结果错误，例如google -> gog#le
            }
        }
        return result;
    }

    /**
     * 思路：
     * 仿照HashMap思想记录次数
     */
    int[] frequency = new int[256];
    List<Character> list = new ArrayList<>();

    public void Insert1(char ch) {
        frequency[ch]++;
        if (!list.contains(ch)) {
            list.add(ch);
        }
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce1() {
        char result = '#';
        for (Character c : list) {
            if (frequency[c] == 1) {
                result = c;
                break;
            }
        }
        return result;
    }

    /**
     * 思路：
     * 维持两个数组，一个记录重复字符，一个记录单词出现字符。
     * 每次有新的字符进来，先判断是否是重复字符，如果是，直接忽略，如果不是，看单词出现的字符列表中是否存在相同的字符，如果存在，证
     * 明新读入的字符是新的重复字符，那么从单次出现的字符列表中删除当前字符，并且将其加入到重复字母列表中，每轮第一个只出现一次的字
     * 符就是单词出现字符列表的首位元素，这种方法体现了自底向上的思想并且利用了列表有序的特性。
     */
    List<Character> duplicates = new ArrayList<>();
    List<Character> singles = new ArrayList<>();

    public void Insert2(char ch) {
        if (!duplicates.contains(ch)) {
            if (singles.contains(ch)) {
                Object o = ch;
                singles.remove(o);
                duplicates.add(ch);
            } else {
                singles.add(ch);
            }
        }
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce2() {
        char result = '#';
        if (singles.size() > 0) {
            result = singles.get(0);
        }
        return result;
    }
}
