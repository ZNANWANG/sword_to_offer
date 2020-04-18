package practice;

import java.util.*;

/**
 * 把数组排成最小的数
 * <p>
 * 题目描述
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，则打印出这
 * 三个数字能排成的最小数字为321323。
 */
public class Prac32 {
    public static void main(String[] args) {
        int[] numbers = new int[]{1, 2, 3, 4};
        TreeSet<String> permutations = new TreeSet<>();
        permutation(numbers, permutations, 0);
        System.out.println(permutations);
        String[] strs = new String[]{"31", "3"};
        Arrays.sort(strs);
        System.out.println(Arrays.toString(strs));
    }

    /**
     * 思路：
     * 蛮力全排列，输出最小值。
     * <p>
     * 注意拼接后的数字有可能是大数，所以采用字符串存储，排序之后输出最小值。
     */
    public static String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) return "";
        TreeSet<String> permutations = new TreeSet<>();
        permutation(numbers, permutations, 0);
        if (!permutations.isEmpty()) {
            return String.valueOf(permutations.iterator().next());
        } else {
            return "";
        }
    }

    public static void permutation(int[] numbers, TreeSet<String> permutations, int count) {
        if (numbers != null && numbers.length > 0) {
            if (count == numbers.length - 1) {
                permutations.add(appendNumbers(numbers));
            } else {
                for (int i = count; i < numbers.length; i++) {
                    count++;
                    for (int j = i; j < numbers.length; j++) {
                        swap(numbers, i, j);
                        permutation(Arrays.copyOf(numbers, numbers.length), permutations, count);
                        swap(numbers, i, j);
                    }
                }
            }
        }
    }

    public static String appendNumbers(int[] numbers) {
        StringBuffer sb = new StringBuffer();
        for (int a : numbers) {
            sb.append(a);
        }
        return sb.toString();
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 思路：
     * 自定义比较规则，证明见剑指Offer。
     * 排序规则如下：
     * 若ab > ba 则 a > b，
     * 若ab < ba 则 a < b，
     * 若ab = ba 则 a = b；
     * 解释说明：
     * 比如 "3" < "31"但是 "331" > "313"，所以要将二者拼接起来进行比较
     */
    public String PrintMinNumber1(int[] numbers) {
        if (numbers == null || numbers.length == 0) return "";
        String result = "";
        ArrayList<Integer> list = new ArrayList<>();

        for (int a : numbers) {
            list.add(a);//将数组放入arrayList中
        }
        //实现了Comparator接口的compare方法，将集合元素按照compare方法的规则进行排序
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer str1, Integer str2) {
                // TODO Auto-generated method stub
                String s1 = str1 + "" + str2;
                String s2 = str2 + "" + str1;

                return s1.compareTo(s2);
            }
        });

        for (int j : list) {
            result += j;
        }
        return result;
    }

    public String PrintMinNumber2(int[] numbers) {
        if (numbers == null || numbers.length == 0) return "";
        int len = numbers.length;
        String[] str = new String[len];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            str[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(str, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String c1 = s1 + s2;
                String c2 = s2 + s1;
                return c1.compareTo(c2);
            }
        });
        for (int i = 0; i < len; i++) {
            sb.append(str[i]);
        }
        return sb.toString();
    }
}
