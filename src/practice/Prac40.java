package practice;

import java.util.*;

/**
 * 数组中只出现一次的数字
 * <p>
 * 题目描述
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 */
public class Prac40 {

    /**
     * 思路：
     * HahMap记录次数，普通解法，没有利用数组的性质。
     */
    public static void FindNumsAppearOnce(int[] array, int[] num1, int num2[]) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : array) {
            if (map.containsKey(a)) {
                map.put(a, map.get(a) + 1);
            } else {
                map.put(a, 1);
            }
        }

        int count = 0;
        for (int a : array) {
            if (map.get(a) == 1) {
                if (count == 0) {
                    num1[0] = a;
                    count++;
                } else {
                    num2[0] = a;
                }
            }
        }
    }

    /**
     * 思路：
     * 1、Arrays.sort 数组排序
     * 2、使用栈进行从小到大入栈，一样的则弹出，不一样则入栈
     * 3、取栈中剩余的元素
     */
    public void FindNumsAppearOnce1(int[] array, int[] num1, int num2[]) {
        Arrays.sort(array);
        Stack<Integer> sta = new Stack<Integer>();


        for (int i = 0; i < array.length; i++) {
            if (sta.empty() || sta.peek() != array[i]) {
                sta.push(array[i]);
            } else if (sta.peek() == array[i]) {
                sta.pop();
            }
        }

        num1[0] = sta.pop();
        num2[0] = sta.pop();
    }

    /**
     * 思路：
     * 首先判断ArrayList中是否已存在，如果存在，则删除。
     * 删除时需要注意一下，如果直接传入当前数作为参数，它会按照下标进行删除，不会按照对象进行删除，可能会出现越界。
     */
    public void FindNumsAppearOnce2(int[] array, int[] num1, int num2[]) {
        if (array.length < 2) {
            return;
        }
        ArrayList<Integer> list = new ArrayList();
        for (int i = 0; i < array.length; i++) {
            if (list.contains(array[i])) {
                Object o = array[i];
                list.remove(o); // list.remove(new Integer(array[i]); 也可以
            } else {
                list.add(array[i]);
            }
        }
        num1[0] = list.get(0);
        num2[0] = list.get(1);
    }

    /**
     * 思路：
     *
     * 首先：位运算中异或的性质：
     * 1.两个相同数字异或=0；
     * 2.一个数和0异或还是它本身；
     * 3.异或运算没有顺序。
     *
     * 运算法则参考如下：
     * 1. a ⊕ a = 0
     * 2. a ⊕ b = b ⊕ a
     * 3. a ⊕ b ⊕ c = a ⊕ (b ⊕ c) = (a ⊕ b) ⊕ c;
     * 4. d = a ⊕ b ⊕ c 可以推出 a = d ⊕ b ⊕ c.
     * 5. a ⊕ b ⊕ a = b.
     *
     * 当只有一个数出现一次时，我们把数组中所有的数，依次异或运算，最后异或的结果就等于两个落单的数直接异或的得数，因为成对儿出现的
     * 其他数都抵消了。
     *
     * 依照这个思路，我们来看两个数（我们假设是AB）出现一次的数组。我们首先还是先异或，剩下的数字肯定是A、B异或的结果，这个结果的二
     * 进制中的1，表现的是A和B的不同的位。我们就取最后一个1所在的位数，假设是第3位，接着把原数组分成两组，分组标准是第3位是否为1。
     * 如此，相同的数肯定在一个组，因为相同数字所有位都相同，而不同的数，肯定不在一组。然后把这两个组按照最开始的思路，依次异或，剩
     * 余的两个结果就是这两个只出现一次的数字。
     */
    public void FindNumsAppearOnce3(int[] array, int[] num1, int num2[]) {
        if (array == null || array.length == 0) {
            return;
        }
        if (array.length == 1) {
            num1[0] = array[0];
        } else if (array.length == 2) {
            num1[0] = array[0];
            num2[0] = array[1];
        } else {
            int xor = 0;
            for (int i = 0; i < array.length; i++) {
                xor ^= array[i];
            }
            int index = 1;
            while ((xor & index) == 0) {
                index = index << 1; // 取最右边的1作为划分位
            }
            for (int i = 0; i < array.length; i++) {
                if ((array[i] & index) == 0) {
                    num1[0] ^= array[i];
                } else {
                    num2[0] ^= array[i];
                }
            }
        }
    }

    /**
     * 思路：
     * 异或运算
     */
    public static void FindNumsAppearOnce4(int[] array, int[] num1, int[] num2)    {
        if (array == null || array.length == 0) {
            return;
        }
        if (array.length == 1) {
            num1[0] = array[0];
        } else if (array.length == 2) {
            num1[0] = array[0];
            num2[0] = array[1];
        } else {
            int length = array.length;
            int bitResult = 0;
            for (int i = 0; i < length; ++i) {
                bitResult ^= array[i];
            }
            int index = findRightmost1(bitResult);
            for (int i = 0; i < length; ++i) {
                if (isBit1(array[i], index)) {
                    num1[0] ^= array[i];
                } else {
                    num2[0] ^= array[i];
                }
            }
        }
    }

    // 计算需要右移的位数
    private static int findRightmost1(int bitResult){
        int index = 0;
        while(((bitResult & 1) == 0) && index < 32){
            bitResult >>= 1;
            index++;
        }
        return index; // 表示需要右移的位数
    }

    // 根据右移之后和1与运算的结果是否为1来划分不同的两组元素
    private static boolean isBit1(int target, int index){
        return ((target >> index) & 1) == 1;
    }
}
