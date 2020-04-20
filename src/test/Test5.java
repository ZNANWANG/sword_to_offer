package test;

import java.util.*;

public class Test5 {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String str = sc.nextLine();
        testWhitespace();
        testListDelete();
        testLinkedHashMap();
    }

    public static void testLinkedHashMap(){
        Map<Character, Integer> map = new LinkedHashMap<>();
        map.put('b', 1);
        map.put('a', 2);
        map.put('c', 1);
        System.out.println(map);
        System.out.println(map.keySet());
    }

    /**
     * 测试列表删除
     */
    public static void testListDelete(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("b");
        System.out.println(list);
        Object o = "a";
        list.remove(o); // 只删除一个
        System.out.println(list);
    }

    /**
     * 测试空白字符
     */
    public static void testWhitespace(){
        String str = " ";
        System.out.println(str.length());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            System.out.println(c);
            System.out.println(String.valueOf(c).trim().equals(""));
        }
    }
}
