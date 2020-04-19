package practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 左旋转字符串
 * <p>
 * 题目描述
 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，请你
 * 把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
 */
public class Prac43 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.next();
            int n = sc.nextInt();
            System.out.println(LeftRotateString2(str, n));
        }
        sc.close();
    }

    /**
     * 思路：
     * 运用StringBuffer的deleteCharAt()和append()方法。
     */
    public static String LeftRotateString(String str, int n) {
        if(str == null){
            return null;
        } else if(str.length() < 2){
            return str;
        }
        StringBuffer sb = new StringBuffer(str);
        for(int i = 0; i < n; i++){
            char c = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 思路：
     * 运用Queue先入先出（FIFO）的特性
     */
    public static String LeftRotateString1(String str, int n) {
        if(str == null){
            return null;
        } else if(str.length() < 2){
            return str;
        }
        Queue<Character> queue = new LinkedList<>();
        for(int i = 0; i < str.length(); i++){
            queue.offer(str.charAt(i));
        }
        for(int i = 0; i < n; i++){
            char c = queue.poll();
            queue.offer(c);
        }
        StringBuffer sb = new StringBuffer();
        while(!queue.isEmpty()){
            sb.append(queue.poll());
        }
        return sb.toString();
    }

    /**
     * 思路：
     * 记录要截取的位置，然后拼接左右字符串即可。
     */
    public static String LeftRotateString2(String str, int n) {
        if(str == null){
            return null;
        } else if(str.length() < 2){
            return str;
        }
        int length = str.length();
        int start = 0;
        for(int i = 0; i < n; i++){
            start++;
            start %= length;
        }
        String right = str.substring(start, length);
        String left = str.substring(0, start);
        return right + left;
    }
}
