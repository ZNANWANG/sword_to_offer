package practice;

import java.util.*;

/**
 * 翻转单词顺序列
 *
 * 题目描述
 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来
 * 翻看，但却读不懂它的意思。例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a
 * student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 */
public class Prac44 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.nextLine();
            System.out.println(ReverseSentence4(str));
            System.out.println(reverse(str, 0, str.length() - 1));
        }
        sc.close();
    }

    /**
     * 思路：
     * 正则表达式分割
     */
    public static String ReverseSentence(String str) {
        if(str == null) {
            return null;
        } else if(str.trim().equals("")){ // 如果字符串是由一个或者多个空格组成，那么返回原字符串。
            return str;
        }
        String[] strs = str.split("\\s+");
        System.out.println(strs.length);
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < strs.length; i++){
            sb.append(strs[strs.length - 1 - i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    /**
     * 思路：
     * 利用StringBuffer的insert()方法（用Stack代替sb也可以）
     */
    public static String ReverseSentence1(String str) {
        if(str == null){
            return null;
        } else if (str.trim().equals("")){
            return str;
        }
        StringBuffer sb = new StringBuffer();
        StringBuffer word = new StringBuffer();
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c != ' '){
                word.append(c);
            } else {
                if(word.length() > 0){
                    sb.insert(0, " ");
                    sb.insert(0, word.toString());
                }
                word = new StringBuffer();
            }
            if(i == str.length() - 1){
                sb.insert(0, " ");
                sb.insert(0, word.toString());
            }
        }
        return sb.toString().trim();
    }

    /**
     * 思路：
     * 利用Stack先入后出的性质
     */
    public static String ReverseSentence2(String str) {
        if(str == null){
            return null;
        } else if (str.trim().equals("")){
            return str;
        }
        Stack<String> stack = new Stack<>();
        StringBuffer word = new StringBuffer();
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c != ' '){
                word.append(c);
            } else {
                if(word.length() > 0){
                    stack.push(word.toString());
                }
                word = new StringBuffer();
            }
            if(i == str.length() - 1){
                stack.push(word.toString());
            }
        }
        StringBuffer sb = new StringBuffer();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public static String ReverseSentence3(String str) {
        if(str == null){
            return null;
        } else if (str.trim().equals("")){
            return str;
        }
        String reversed = reverse(str, 0, str.length() - 1);
        int start = 0;
        int end = 0;
        for(int i = 0; i < reversed.length(); i++){
            char c = reversed.charAt(i);
            if(!String.valueOf(c).trim().equals("")){
                end++;
            } else {
                if(start < end - 1) {
                    reversed = reverse(reversed, start, end - 1);
                }
                start = i + 1;
                end = i + 1;
            }
            // 针对末尾单词没有空格触发翻转的情况
            if(i == reversed.length() - 1){
                reversed = reverse(reversed, start, end - 1);
            }
        }
        return reversed;
    }

    public static String ReverseSentence4(String str) {
        if(str == null){
            return null;
        } else if (str.trim().equals("")){
            return str;
        }
        // 整体翻转
        String reversed = reverse(str, 0, str.length() - 1);
        int start = 0;
        int end = 0;
        int i = 0;
        while(i < reversed.length()){
            // 空格跳过
            while(i < reversed.length() && String.valueOf(reversed.charAt(i)).trim().equals("")){
                i++;
            }
            // 记录单词的第一个字符的位置
            start = end = i;
            // 不是空格 找单词最后一个字符的位置
            while(i < reversed.length() && !String.valueOf(reversed.charAt(i)).trim().equals("")){
                end++;
                i++;
            }
            // 单个字符不必翻转
            if(start < end - 1) {
                // 局部翻转
                reversed = reverse(reversed, start, end - 1);
            }
        }
        return reversed;
    }

    /**
     * 翻转字符串，例如abc efg -> gfe cba
     */
    public static String reverse(String str, int i, int j){
        if(i == j){
            return str;
        }
        StringBuffer sb = new StringBuffer(str);
        while(i < j){
            char temp = sb.charAt(i);
            sb.setCharAt(i, str.charAt(j));
            sb.setCharAt(j, temp);
            i++;
            j--;
        }
        return sb.toString();
    }
}
