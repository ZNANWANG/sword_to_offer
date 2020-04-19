package practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 孩子们的游戏（圆圈中剩下的数） —— 约瑟夫环问题
 * <p>
 * 题目描述
 * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游
 * 戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。每次喊到m-1的那个小朋友要出列唱首歌,然
 * 后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可
 * 以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从
 * 0到n-1)
 * <p>
 * 如果没有小朋友，请返回-1
 */
public class Prac46 {
    /**
     * Queue —— 约瑟夫环问题
     */
    public int LastRemaining_Solution(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.offer(i);
        }
        int count = 0;
        while (queue.size() > 1) {
            int current = queue.poll();
            count++;
            if (count % m != 0) {
                queue.offer(current);
            }
        }
        return queue.poll();
    }

    public int LastRemaining_Solution1(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }
        int leavers = 0;
        int index = 0;
        int leaverCount = 0;
        int[] children = new int[n];
        while (leavers < n - 1) {
            // 判断当前小朋友是否已经被淘汰，如果是，继续报数，且累计淘汰的人数（相当于包含了淘汰的小朋友报数次数和无效的报数次数）；
            // 如果不是，判断当前小朋友是否是第m个报数人，如果是，淘汰当前小朋友，继续报数且累计淘汰的人数；如果不是，继续报数。
            // 形象比喻就是小朋友即使淘汰了也不出队，而是继续捣乱报数，这个时候必须记录无效的报数次数（leaverCount - leaver），才
            // 能确定当前小朋友是否是第m个报数人。
            if (children[index % n] == 1) {
                index++;
                leaverCount++;
            } else {
                if ((index + 1 - leaverCount + leavers) % m == 0) {
                    children[index % n] = 1;
                    index++;
                    leavers++;
                    leaverCount++;
                } else {
                    index++;
                }
            }
        }
        int winner = -1;
        for (int i = 0; i < children.length; i++) {
            if (children[i] == 0) {
                winner = i;
            }
        }
        return winner;
    }

    /**
     * 思路：
     * java中直接使用一个list来模拟，并使用一个索引cur指向删除的位置，当cur的值为list的size，就让cur到头位置。
     */
    public int LastRemaining_Solution2(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }
        List<Integer> list = new ArrayList<>();
        //构建list
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int cur = -1;
        while (list.size() > 1) {
            for (int i = 0; i < m; i++) {
                cur++;
                if (cur == list.size()) {
                    cur = 0;
                }
            }
            list.remove(cur);
            cur--;//cur--的原因，因为新的list中cur指向了下一个元素，为了保证移动m个准确性，所以cur向前移动一位。
        }
        return list.get(0);
    }

    /**
     * 思路：
     * 列表模拟
     */
    public int LastRemaining_Solution3(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }
        // 初始化人数，人数排号从1开始；
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        // 从第target（数组下标）个开始重新数数；
        int target = 0;
        while (list.size() > 1) {
            target = (target + m) % list.size();
            // 当target=0时，target-1就是-1了，数组越界，其意思就是返回倒数第一个元素，即list.size()-1；
            if (target != 0) {
                target--; // 从删除的元素索引位置开始继续查找
                list.remove(target);
            } else {
                list.remove(list.size() - 1);
            }
        }
        return list.get(0);
    }

    /**
     * 优化版列表模拟
     */
    public int LastRemaining_Solution6(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }
        // 初始化人数，人数排号从1开始；
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        // 从第target（数组下标）个开始重新数数；
        int target = 0;
        while (list.size() > 1) {
            // target代表将要删除的元素索引
            target = (target + m - 1) % list.size();
            list.remove(target);
        }
        return list.get(0);
    }

    /**
     * 思路：
     * 如果只求最后一个报数胜利者的话，我们可以用数学归纳法解决该问题，为了讨论方便，先把问题稍微改变一下，并不影响原意：
     * 问题描述：n个人（编号0~(n-1))，从0开始报数，报到(m-1)的退出，剩下的人继续从0开始报数。求胜利者的编号。
     * 我们知道第一个人(编号一定是m%n-1) 出列之后，剩下的n-1个人组成了一个新的约瑟夫环（以编号为k=m%n的人开始）:
     * k  k+1  k+2  ... n-2, n-1, 0, 1, 2, ... k-2并且从k开始报0。
     * 现在我们把他们的编号做一下转换：
     * k     --> 0
     * k+1   --> 1
     * k+2   --> 2
     * ...
     * ...
     * k-2   --> n-2
     * k-1   --> n-1
     * 变换后就完完全全成为了(n-1)个人报数的子问题，假如我们知道这个子问题的解：例如x是最终的胜利者，那么根据上面这个表把这个x变回去
     * 不刚好就是n个人情况的解吗？！！变回去的公式很简单，相信大家都可以推出来：x'=(x+k)%n。
     * <p>
     * 令f[i]表示i个人玩游戏报m退出最后胜利者的编号，最后的结果自然是f[n]。
     * <p>
     * 递推公式:
     * f[1]=0;
     * f[i]=(f[i-1]+m)%i;  (i>1)
     * <p>
     * 有了这个公式，我们要做的就是从1-n顺序算出f[i]的数值，最后结果是f[n]。 因为实际生活中编号总是从1开始，我们输出f[n]+1。
     */
    public int LastRemaining_Solution4(int n, int m) {
        if (n < 1 || m < 1)
            return -1;
        if (n == 1)
            return 0;
        else
            return (LastRemaining_Solution(n - 1, m) + m) % n;
    }

    /**
     * 思路：
     * n个数字中第一个被删除的数字索引为(m - 1) % n，之后的数字索引为 m % n;
     */
    public int LastRemaining_Solution5(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = (res + m) % i;
        }
        return res;
    }
}
