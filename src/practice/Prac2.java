package practice;

/**
 * 替换空格
 *
 * 题目描述
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class Prac2 {
    public String replaceSpace(StringBuffer str) {
        String s = str.toString();
        s = s.replaceAll("\\s", "%20"); //注意必须赋值
        return s;
    }

    public static void main(String[] args){
        System.out.println("hello world");
    }
}
