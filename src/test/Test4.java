package test;

import java.util.ArrayList;

/**
 * 面试中遇到的代码问题
 */
public class Test4 {
    public static void main(String[] args) {
//        integerTest();
//        stringTest();
//        test();
        test1();
    }

    // 编译没问题但是运行有问题
    public static void test(){
        String[] strs = new String[2];
        Object[] objs = strs;
        strs[0] = "hi";
        objs[1] = Integer.valueOf(42);
    }

    public static void test1(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        // 会报错：java.util.ConcurrentModificationException
        for(Integer a : list){
            list.remove(a);
        }

//        for(int i = 0; i < list.size(); i++){
//            if(list.get(i) == 2){
//                list.remove(i);
//                i--;
//            }
//        }
        System.out.println(list);
    }

    public static void integerTest(){
        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);

        System.out.println(i1 == i2); //true
        System.out.println(i1 == i2 + i3); //true
        System.out.println(i4 == i5); //false
        System.out.println(i4 == i5 + i6); //true
    }

    public static void stringTest(){
        String s1 = "abc";
        String s2 = new String("abc");
        String s3 = "ab";
        String s4 = "c";
        String s5 = "ab" + "c";
        final String s6 = "ab";
        String s7 = s6 + "c";
        String s8 = new String("abc");

        System.out.println(s1 == s2); //false
        System.out.println(s1.equals(s2)); //true
        System.out.println(s1 == s3 + s4); //false
        System.out.println(s1.equals(s3 + s4)); //true
        System.out.println(s1 == s5); //true
        System.out.println(s1.equals(s5)); //true
        System.out.println(s1 == s7); //true
        System.out.println(s1.equals(s7)); //true
        System.out.println(s2 == s8); //false
        System.out.println(s1 == s6 + "c"); //true
        System.out.println(s1 == s6 + s4); //false
    }
}
