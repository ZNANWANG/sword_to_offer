package interview;

import java.util.*;

/**
 * 列表单区间转换
 * <p>
 * 字节跳动算法笔试题
 * 题目描述
 * 测试能否将一个列表中的一段区间加上一个非负数k(k >= 0)转变为第二个列表。
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            getResult(sc);
        }
    }

    /**
     * 50%，超时错误
     */
    public static void getResult(Scanner sc) {
        int n = sc.nextInt(); // 代表n组数据
        for (int i = 0; i < n; i++) {
            int len = sc.nextInt(); //两个列表长度相等
            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = new ArrayList<>();

            for (int j = 0; j < len; j++) {
                list1.add(sc.nextInt());
            }
            for (int j = 0; j < len; j++) {
                list2.add(sc.nextInt());
            }
            boolean inNumber = false;
            int count = 0;
            for (int j = 0; j < len; j++) {
                int temp = list1.get(j) - list2.get(j);
                if (temp != 0) {
                    if (!inNumber) {
                        inNumber = true;
                    }
                } else if (temp > 0) {
                    count = Integer.MAX_VALUE;
                    break;
                } else {
                    if (inNumber) {
                        count++;
                        inNumber = false;
                        if (count > 1) {
                            break;
                        }
                    }
                }
            }
            //System.out.println(count);

            if (count < 2)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}