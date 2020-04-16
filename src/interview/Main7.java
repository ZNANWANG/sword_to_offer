package interview;

import java.util.*;

/**
 * 最后赢家
 * <p>
 * 360安卓笔试题
 * 题目描述：
 * 最强的不一定是最后的赢家。
 * <p>
 * 某赛事有n名选手参加，但是不同于其他的比赛，本比赛采取的是擂台赛的形式，n名选手排成一排，每次队伍的第一位和第二位选手进行比赛，输的一方会排到队尾。
 * <p>
 * 当某位选手取得m连胜时，他将成为最后的赢家，且游戏结束，请问截止到游戏结束，共会进行多少次比赛。
 * <p>
 * 两位选手的比赛结果由他们的战斗力决定，n位选手的战斗力是一个1~n的排列，也就是说他们的战斗力两两不同，不会有平局的情况。
 * <p>
 * 输入
 * 输入第一行包含两个正整数n，m，分别代表参赛选手数量和取得连胜的要求。(1<=n<=100000，1<=m<=10^9)
 * <p>
 * 输入第二行包含n个正整数，中间用空格隔开，第i个数表示队伍的第i位选手的战斗力，整体是一个1~n的排列。
 * <p>
 * 输出
 * 输出仅包含一个正整数，表示截止到游戏终止，共进行多少场比赛。
 * <p>
 * <p>
 * 样例输入
 * 4 2
 * 1 3 2 4
 * 样例输出
 * 2
 * <p>
 * 提示
 * 样例解释
 * 显然第一局应该是战斗力为3的选手获胜，第二局同样是战斗力为3的选手获胜，2连胜终止游戏，所以答案是2。此时若修改m为3，则结果是5。
 */
public class Main7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
//            Queue<Integer> fighters =new LinkedList<>();
            LinkedList<Integer> fighters = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                fighters.offer(sc.nextInt());
            }
            if (m > 0 && n > 1) {
                Map<Integer, Integer> map = new HashMap<>();
//                getResult(fighters, map, m);
//                System.out.println(sum(map));
                System.out.println(getResult2(fighters, map, m, n));
            } else {
                System.out.println(0);
            }
        }
    }

    /**
     * 思路：
     * n个人比n-1场比赛后，实力最高的人就将位于列表首位，剩余场次将会一直连胜。
     */
    public static int getResult2(LinkedList<Integer> fighters, Map<Integer, Integer> map, int m, int n) {
        for (int i = 0; i < n - 1; i++) {
            int x = fighters.get(0);
            int y = fighters.get(1);
            fighters.remove(1);
            fighters.remove(0);
            int max = Math.max(x, y);
            int min = Math.min(x, y);
            fighters.add(0, max);
            if (map.containsKey(max)) {
                map.put(max, map.get(max) + 1);
            } else {
                map.put(max, 1);
            }
            if (map.get(max) == m) {
                return sum(map);
            }
            fighters.add(min);
        }

        int winner = fighters.get(0);
        return m - map.get(winner) + n - 1;
    }

    /**
     * 思路：
     * LinkedList —— 蛮力模拟比赛
     */
    public static void getResult(LinkedList<Integer> fighters, Map<Integer, Integer> map, int m) {
        while (true) {
            int x = fighters.get(0);
            int y = fighters.get(1);
            fighters.remove(1);
            fighters.remove(0);
            int max = Math.max(x, y);
            int min = Math.min(x, y);
            fighters.add(0, max);
            if (map.containsKey(max)) {
                map.put(max, map.get(max) + 1);
            } else {
                map.put(max, 1);
            }
            if (map.get(max) == m) {
                return;
            }
            fighters.add(min);
        }
    }

    /**
     * 思路：
     * Queue —— 蛮力模拟比赛
     */
    public static void getResult(Queue<Integer> fighters, Map<Integer, Integer> map, int m) {
        while (true) {
            int x = fighters.poll();
            int y = fighters.poll();
            Queue<Integer> rest = new LinkedList<>();
            while (!fighters.isEmpty()) {
                rest.offer(fighters.poll());
            }
            int max = Math.max(x, y);
            int min = Math.min(x, y);
            fighters.offer(max);
            if (map.containsKey(max)) {
                map.put(max, map.get(max) + 1);
            } else {
                map.put(max, 1);
            }
            if (map.get(max) == m) {
                return;
            }
            while (!rest.isEmpty()) {
                fighters.offer(rest.poll());
            }
            fighters.offer(min);
        }
    }

    public static int sum(Map<Integer, Integer> map) {
        int sum = 0;
        for (Integer k : map.values()) {
            sum += k;
        }
        return sum;
    }
}
