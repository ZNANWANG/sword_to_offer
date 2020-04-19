package practice;

import java.util.*;

/**
 * 和为S的连续正数序列
 * <p>
 * 题目描述
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。但是他并不满足于此,他在想究竟有多少种连续的
 * 正数序列的和为100(至少包括两个数)。没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很快的
 * 找出所有和为S的连续正数序列? Good Luck!
 * 输出描述:
 * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 */
public class Prac42 {
    public static void main(String[] args){
        Scanner sc=  new Scanner(System.in);
        while (sc.hasNext()){
            int sum = sc.nextInt();
            System.out.println(FindContinuousSequence3(sum));
        }
        sc.close();
    }
    /**
     * 思路：
     * 滑动窗口方法，假如当前窗口（即list)所有的值等于sum，判断窗口的长度是否大于一，如果大于一，添加这一窗口序列到result中，如果不
     * 大于一，开始下一轮计算；假如当前窗口所有的值小于sum，移除窗口最左边元素（即最小元素），head加一（表示当前数字已被查找过），
     * 然后继续循环判断；假如当前窗口所有的值大于sum，tail加一，窗口添加tail元素（即最大元素），然后继续循环判断。核心思想是通过调整窗口大小来判断是否等于目标值，
     */
    public static ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        int head = 1;
        int tail = head + 1;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(head);
        list.add(tail);
        while (head <= tail) { // while (head < (sum + 1) / 2)也可以
            if (sum(list) == sum) {
                if (list.size() > 1) {
                    result.add(list);
                }
                head++;
                tail = head + 1;
                list = new ArrayList<>();
                list.add(head);
                list.add(tail);
            } else if (sum(list) > sum) {
                head++;
                list.remove(0);
            } else {
                tail++;
                list.add(tail);
            }
        }
        return result;
    }

    /**
     * 优化版窗口滑动算法
     * <p>
     * 注意初始窗口长度为2，如果一开始的窗口总值就大于目标值，那么循环结束，因为窗口继续向后移动只会越来越大，总数必然大于目标值，
     * 如果去掉首位元素，那么窗口长度就会等于一，即使窗口此时唯一的元素正好等于总值，但还是不符合条件， 例如head = 2，tail = 3，
     * sum = 3.
     * 结论：初始窗口总值大于目标值时，循环结束。（因为窗口继续向后移动只会越来越大）
     */
    public static ArrayList<ArrayList<Integer>> FindContinuousSequence1(int sum) {
        int head = 1;
        int tail = head + 1;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(head);
        list.add(tail);
        // 窗口长度要大于2，head代表窗口首位元素，tail代表窗口末尾元素。
        while (head < tail) {
            // 等差求和公式：Sn = n(a1 + an) / 2
            int total = (head + tail) * (tail - head + 1) / 2;
            if (total == sum) {
                result.add(list);
                head++;
                tail = head + 1;
                list = new ArrayList<>();
                list.add(head);
                list.add(tail);
            } else if (total > sum) {
                head++;
                list.remove(0);
            } else {
                tail++;
                list.add(tail);
            }
        }
        return result;
    }

    /**
     * 滑动窗口算法
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence2(int sum) {
        //存放结果
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        //两个起点，相当于动态窗口的两边，根据其窗口内的值的和来确定窗口的位置和大小
        int plow = 1, phigh = 2;
        while (phigh > plow) {
            //由于是连续的，差为1的一个序列，那么求和公式是(a0+an)*n/2
            int cur = (phigh + plow) * (phigh - plow + 1) / 2;
            //相等，那么就将窗口范围的所有数添加进结果集
            if (cur == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = plow; i <= phigh; i++) {
                    list.add(i);
                }
                result.add(list);
                plow++;
                //如果当前窗口内的值之和小于sum，那么右边窗口右移一下
            } else if (cur < sum) {
                phigh++;
            } else {
                //如果当前窗口内的值之和大于sum，那么左边窗口右移一下
                plow++;
            }
        }
        return result;
    }

    /**
     * 思路：
     * 数学公式求解 ：a1 = (S / n) - (n - 1) / 2
     * <p>
     * 注：还有一点需要注意，S = （a1 + a1 + n - 1）*  n / 2，等差序列公式自然是不必说的。对其进行放缩，就有S > n^2 / 2；即n < √2S。
     * 这样做的话可以减少遍历次数。 在for循环中就有体现。
     */
    public static ArrayList<ArrayList<Integer>> FindContinuousSequence3(int sum) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        for (int n = 2; n * n < 2 * sum; n++) {
            double m = ((2.0 * sum) / n - n + 1) / 2.0;
//            double m = (double)sum / n - (n - 1) / 2.0; 也可以
            System.out.println("m: " + m);
            int temp = (int) m;
            System.out.println("temp: " + temp);
            if (m == temp) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int i = 0; i < n; i++) {
                    list.add(i + temp);
                }
                lists.add(0, list);
            }
        }
        return lists;
    }

    public static int sum(ArrayList<Integer> list) {
        int sum = 0;
        for (Integer a : list) {
            sum += a;
        }
        return sum;
    }
}
