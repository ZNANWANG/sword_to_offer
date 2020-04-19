package practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 第一个只出现一次的字符
 * <p>
 * 题目描述
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）。
 */
public class Prac34 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input a string: ");
        while (sc.hasNext()) {
            String str = sc.next();
            System.out.println(FirstNotRepeatingChar(str));
            System.out.println("Please input a string: ");
        }
    }

    public static int FirstNotRepeatingChar(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (map.get(c) == 1) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 思路：
     * 蛮力遍历字符串，时间复杂度为O(n^2)。
     */
    public static int FirstNotRepeatingChar1(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int count = 0;
            for (int j = 0; j < str.length(); j++) {
                if (c == str.charAt(j)) {
                    count++;
                }
            }
            if (count == 1) {
                return i;
            }
        }
        return -1;
    }
}
