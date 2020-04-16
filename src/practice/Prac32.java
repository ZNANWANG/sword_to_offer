package practice;

import java.util.*;

public class Prac32 {
    public static void main(String[] args) {
        int[] numbers = new int[]{1, 2, 3, 4};
        TreeSet<String> permutations = new TreeSet<>();
        permutation(numbers, permutations, 0);
        System.out.println(permutations);
    }

    /**
     * 思路：
     * 蛮力全排列，输出最小值。
     * <p>
     * 注意拼接后的数字有可能是大数，所以采用字符串存储，排序之后输出最小值。
     */
    public static String PrintMinNumber(int[] numbers) {
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
     */
    public String PrintMinNumber1(int[] numbers) {
        String result = "";
        ArrayList<Integer> list = new ArrayList<Integer>();

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
}
