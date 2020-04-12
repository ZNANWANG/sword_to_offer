package Interview;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * 逛街
 * <p>
 * 腾讯面试题
 * 小Q在周末的时候和他的小伙伴来到大城市逛街，一条步行街上有很多高楼，共有n座高楼排成一行。
 * 小Q从第一栋一直走到了最后一栋，小Q从来都没有见到这么多的楼，所以他想知道他在每栋楼的位置处能看到多少栋楼呢？（当前面的楼的高度大于
 * 等于后面的楼时，后面的楼将被挡住）
 * <p>
 * 输入描述:
 * 输入第一行将包含一个数字n，代表楼的栋数，接下来的一行将包含n个数字wi(1<=i<=n)，代表每一栋楼的高度。
 * 1<=n<=100000;
 * 1<=wi<=100000;
 * <p>
 * <p>
 * 输出描述:
 * 输出一行，包含空格分割的n个数字vi，分别代表小Q在第i栋楼时能看到的楼的数量。
 * 示例1
 * 输入
 * 6
 * 5 3 8 3 2 5
 * 输出
 * 3 3 5 4 4 4
 * 说明
 * 当小Q处于位置3时，他可以向前看到位置2,1处的楼，向后看到位置4,6处的楼，加上第3栋楼，共可看到5栋楼。当小Q处于位置4时，他可以向前看
 * 到位置3处的楼，向后看到位置5,6处的楼，加上第4栋楼，共可看到4栋楼。
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] buildings = new int[n];
            for (int i = 0; i < n; i++) {
                buildings[i] = sc.nextInt();
            }
            getResult1(buildings);
        }
    }

    /**
     * 思路：维持两个单调栈，时间复杂度O(n)
     */
    public static void getResult(int[] buildings) {
        int len = buildings.length;
        Stack<Integer> stack = new Stack<>();
        int[] rightLook = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            rightLook[i] = stack.size();
            while (!stack.isEmpty() && buildings[i] >= stack.peek()) {
                stack.pop();
            }
            stack.push(buildings[i]);
        }

        stack.clear();
        int[] leftLook = new int[len];
        for (int i = 0; i < len; i++) {
            leftLook[i] = stack.size();
            while (!stack.isEmpty() && buildings[i] >= stack.peek()) {
                stack.pop();
            }
            stack.push(buildings[i]);
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            sb.append(leftLook[i] + rightLook[i] + 1);
            sb.append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    /**
     * 时间复杂度O(n^2)
     */
    public static void getResult1(int[] buildings) {
        int len = buildings.length;
        int[] leftLook = new int[len];
        for (int i = 1; i < len; i++) {
            int[] temp = Arrays.copyOfRange(buildings, 0, i);
            int[] sub = new int[temp.length];
            for(int j = 0; j  < temp.length; j++){
                sub[j] = temp[temp.length - j - 1];
            }
            System.out.println("Sub: " + Arrays.toString(sub));
            int count = findMaxDescendingSub(sub);
            leftLook[i] = count;
        }
        System.out.println(Arrays.toString(leftLook));

        int[] rightLook = new int[len];
        for (int i = len - 2; i >= 0; i--) {
            int[] sub = Arrays.copyOfRange(buildings, i + 1, len);
            System.out.println("Sub: " + Arrays.toString(sub));
            int count = findMaxDescendingSub(sub);
            rightLook[i] = count;
        }
        System.out.println(Arrays.toString(rightLook));

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            sb.append(leftLook[i] + rightLook[i] + 1);
            sb.append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    public static int findMaxDescendingSub(int[] buildings){
        int count = 0;
        if(buildings.length > 0) {
            int current = buildings[0];
            count++;
            for(int i = 1; i < buildings.length; i++){
                int pre = buildings[i];
                if(pre > current){
                    count++;
                    current = pre;
                }
            }
            return count;
        }
        return count;
    }
}
